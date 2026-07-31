package com.arlight.cosmeticscurios;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import java.util.Optional;
import java.util.UUID;

/**
 * Cuerpo físico y navegación de una mascota. El modelo continúa siendo el mismo
 * cosmético Curios, pero su posición ya no se inventa en el render del cliente.
 */
public final class CompanionEntity extends PathfinderMob {
    public static final int MOTION_IDLE = 0;
    public static final int MOTION_LOOK = 1;
    public static final int MOTION_SIT = 2;
    public static final int MOTION_WALK = 3;
    public static final int MOTION_RUN = 4;
    public static final int MOTION_JUMP = 5;
    public static final int MOTION_SWIM = 6;
    public static final int MOTION_FLY = 7;

    private static final EntityDataAccessor<Optional<UUID>> OWNER =
            SynchedEntityData.defineId(CompanionEntity.class, EntityDataSerializers.OPTIONAL_UUID);
    private static final EntityDataAccessor<String> MODEL =
            SynchedEntityData.defineId(CompanionEntity.class, EntityDataSerializers.STRING);
    private static final EntityDataAccessor<Integer> MOTION =
            SynchedEntityData.defineId(CompanionEntity.class, EntityDataSerializers.INT);

    private static final double START_FOLLOWING_SQ = 10.24D;
    private static final double STOP_FOLLOWING_SQ = 5.29D;
    private static final double HARD_RESCUE_DISTANCE_SQ = 576.0D;
    private static final double STUCK_RESCUE_MIN_DISTANCE_SQ = 196.0D;
    private static final int REPATH_INTERVAL_TICKS = 10;
    private static final int PROGRESS_SAMPLE_TICKS = 10;
    private static final int STUCK_RESCUE_TICKS = 160;
    private static final int RESCUE_COOLDOWN_TICKS = 200;
    private static final int HIDDEN_RETRY_TICKS = 20;

    private CompanionPhysicsProfile physicsProfile = CompanionPhysicsProfile.forModel("");
    private Vec3 lastOwnerPosition;
    private Vec3 followDirection = new Vec3(0.0D, 0.0D, 1.0D);
    private Vec3 progressSamplePosition;
    private int stalledTicks;
    private int repathTicks;
    private int rescueCooldownTicks;
    private int hiddenRetryTicks;
    private int ownerMovementGraceTicks;
    private boolean following;
    private boolean routeRequested;
    private boolean pendingRescue;

    public CompanionEntity(EntityType<? extends CompanionEntity> type, Level level) {
        super(type, level);
        setInvulnerable(true);
        setSilent(true);
        setPersistenceRequired();
        noPhysics = false;
        setNoGravity(false);
        xpReward = 0;
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 1.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.34D)
                .add(Attributes.FOLLOW_RANGE, 32.0D)
                .add(Attributes.STEP_HEIGHT, 1.0D);
    }

    @Override
    protected void registerGoals() {
        // No explora, ataca, huye ni elige objetivos: sólo sigue a su dueño.
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(OWNER, Optional.empty());
        builder.define(MODEL, "");
        builder.define(MOTION, MOTION_IDLE);
    }

    public void configure(UUID owner, String modelId) {
        entityData.set(OWNER, Optional.ofNullable(owner));
        entityData.set(MODEL, modelId == null ? "" : modelId);
        physicsProfile = CompanionPhysicsProfile.forModel(modelId);
        applyPhysicalBounds();
    }

    public UUID ownerId() {
        return entityData.get(OWNER).orElse(null);
    }

    public String modelId() {
        return entityData.get(MODEL);
    }

    public int motionId() {
        return entityData.get(MOTION);
    }

    @Override
    public void tick() {
        applyPhysicalBounds();
        super.tick();
        applyPhysicalBounds();
        clearFire();
        if (level().isClientSide) return;

        ServerPlayer owner = resolveOwner();
        if (owner == null || owner.isSpectator() || !owner.isAlive()
                || !CompanionManager.isEquipped(owner, modelId())) {
            discard();
            return;
        }

        updateOwnerDirection(owner);
        if (rescueCooldownTicks > 0) rescueCooldownTicks--;

        if (pendingRescue) {
            tickPendingRescue(owner);
            return;
        }

        double distanceSq = distanceToSqr(owner);
        if (distanceSq > HARD_RESCUE_DISTANCE_SQ && rescueCooldownTicks <= 0) {
            requestRescue(owner, "distance");
            setMotion(MOTION_IDLE);
            return;
        }

        Vec3 target = followTarget(owner);
        double targetDistanceSq = distanceToSqr(target);
        if (!following && targetDistanceSq > START_FOLLOWING_SQ) following = true;
        else if (following && targetDistanceSq < STOP_FOLLOWING_SQ) following = false;

        if (following) {
            double speed = owner.isSprinting() || distanceSq > 49.0D ? 1.42D : 1.12D;
            if (repathTicks-- <= 0 || getNavigation().isDone()) {
                routeRequested = getNavigation().moveTo(target.x, target.y, target.z, speed);
                repathTicks = REPATH_INTERVAL_TICKS;
            }
            setMotion(speed > 1.2D ? MOTION_RUN : MOTION_WALK);
        } else {
            getNavigation().stop();
            routeRequested = false;
            repathTicks = 0;
            setDeltaMovement(getDeltaMovement().multiply(0.55D, 1.0D, 0.55D));
            setMotion(idleMotion());
        }

        if (!onGround() && getDeltaMovement().y > 0.015D) setMotion(MOTION_JUMP);
        if (isInWater()) setMotion(MOTION_SWIM);

        sampleProgress(owner, targetDistanceSq, distanceSq);
    }

    private ServerPlayer resolveOwner() {
        if (!(level() instanceof ServerLevel serverLevel)) return null;
        UUID id = ownerId();
        if (id == null) return null;
        ServerPlayer owner = serverLevel.getServer().getPlayerList().getPlayer(id);
        return owner != null && owner.level() == level() ? owner : null;
    }

    private void updateOwnerDirection(ServerPlayer owner) {
        Vec3 current = owner.position();
        if (lastOwnerPosition != null) {
            Vec3 movement = current.subtract(lastOwnerPosition);
            Vec3 horizontal = new Vec3(movement.x, 0.0D, movement.z);
            if (horizontal.lengthSqr() > 0.0025D) {
                // La formación depende exclusivamente del desplazamiento real. Mirar o
                // girar la cámara jamás cambia el punto de seguimiento de la mascota.
                followDirection = horizontal.normalize();
                ownerMovementGraceTicks = 80;
            } else if (ownerMovementGraceTicks > 0) {
                ownerMovementGraceTicks--;
            }
        }
        lastOwnerPosition = current;
    }

    private Vec3 followTarget(ServerPlayer owner) {
        Vec3 horizontal = followDirection;
        if (horizontal.lengthSqr() < 0.001D) horizontal = new Vec3(0.0D, 0.0D, 1.0D);
        double side = (owner.getUUID().hashCode() & 1) == 0 ? 1.0D : -1.0D;
        Vec3 lateral = new Vec3(-horizontal.z * side, 0.0D, horizontal.x * side);
        return owner.position().subtract(horizontal.scale(2.15D)).add(lateral.scale(1.05D));
    }

    public boolean placeSafelyNear(ServerPlayer owner, String reason) {
        if (lastOwnerPosition == null) {
            double yaw = Math.toRadians(owner.getYRot());
            followDirection = new Vec3(-Math.sin(yaw), 0.0D, Math.cos(yaw));
            lastOwnerPosition = owner.position();
        }
        applyPhysicalBounds();
        Vec3 target = followTarget(owner);
        int[] verticalChecks = {0, 1, -1, 2, -2, 3, -3, 4, -4};
        double[][] horizontalChecks = {
                {0.0D, 0.0D}, {1.0D, 0.0D}, {-1.0D, 0.0D},
                {0.0D, 1.0D}, {0.0D, -1.0D},
                {1.5D, 1.5D}, {-1.5D, 1.5D}, {1.5D, -1.5D}, {-1.5D, -1.5D},
                {2.5D, 0.0D}, {-2.5D, 0.0D}, {0.0D, 2.5D}, {0.0D, -2.5D}
        };
        for (double[] horizontal : horizontalChecks) {
            for (int vertical : verticalChecks) {
                double x = target.x + horizontal[0];
                double y = owner.getY() + vertical;
                double z = target.z + horizontal[1];
                if (!safeAt(x, y, z)) continue;
                getNavigation().stop();
                setDeltaMovement(Vec3.ZERO);
                teleportTo(x, y, z);
                applyPhysicalBounds();
                setYRot(owner.getYRot());
                setYHeadRot(owner.getYRot());
                setInvisible(false);
                pendingRescue = false;
                rescueCooldownTicks = RESCUE_COOLDOWN_TICKS;
                resetProgressTracking();
                ArlightCosmeticsCurios.LOGGER.info(
                        "Mascota {} de {} recolocada de forma segura: {}",
                        modelId(), owner.getGameProfile().getName(), reason);
                return true;
            }
        }
        return false;
    }

    private boolean safeAt(double x, double y, double z) {
        if (!(level() instanceof ServerLevel serverLevel)) return false;
        BlockPos feet = BlockPos.containing(x, y, z);
        BlockPos support = feet.below();
        if (!serverLevel.hasChunkAt(feet) || !serverLevel.getWorldBorder().isWithinBounds(feet)) return false;
        if (!level().getBlockState(support).isFaceSturdy(level(), support, Direction.UP)) return false;
        AABB moved = getBoundingBox().move(x - getX(), y - getY(), z - getZ());
        BlockPos top = BlockPos.containing(x, y + physicsProfile.height() - 0.01D, z);
        return level().noCollision(this, moved)
                && level().getFluidState(feet).isEmpty()
                && level().getFluidState(top).isEmpty();
    }

    private void applyPhysicalBounds() {
        CompanionPhysicsProfile profile = physicsProfile;
        if (profile == null) profile = CompanionPhysicsProfile.forModel("");
        double halfWidth = profile.width() * 0.5D;
        setBoundingBox(new AABB(
                getX() - halfWidth, getY(), getZ() - halfWidth,
                getX() + halfWidth, getY() + profile.height(), getZ() + halfWidth));
    }

    private void sampleProgress(ServerPlayer owner, double targetDistanceSq, double ownerDistanceSq) {
        if (tickCount % PROGRESS_SAMPLE_TICKS != 0) return;
        // No existe rescate por atasco mientras el dueño esté quieto o la mascota
        // continúe cerca. Puede esperar al otro lado de una pared sin aparecer de
        // golpe junto al jugador.
        if (!following || targetDistanceSq <= START_FOLLOWING_SQ
                || ownerMovementGraceTicks <= 0
                || ownerDistanceSq < STUCK_RESCUE_MIN_DISTANCE_SQ) {
            resetProgressTracking();
            return;
        }

        Vec3 current = position();
        if (progressSamplePosition == null) {
            progressSamplePosition = current;
            stalledTicks = 0;
            return;
        }

        double movedSq = current.distanceToSqr(progressSamplePosition);
        progressSamplePosition = current;
        if (movedSq >= 0.0064D) stalledTicks = 0;
        else stalledTicks += PROGRESS_SAMPLE_TICKS;

        if (stalledTicks >= STUCK_RESCUE_TICKS && rescueCooldownTicks <= 0) {
            requestRescue(owner, routeRequested ? "stuck-route" : "path-unavailable");
        }
    }

    private void requestRescue(ServerPlayer owner, String reason) {
        if (placeSafelyNear(owner, reason)) return;
        getNavigation().stop();
        setDeltaMovement(Vec3.ZERO);
        setInvisible(true);
        pendingRescue = true;
        hiddenRetryTicks = HIDDEN_RETRY_TICKS;
        ArlightCosmeticsCurios.LOGGER.warn(
                "No se encontró suelo seguro para la mascota {} de {}; se oculta y reintentará ({})",
                modelId(), owner.getGameProfile().getName(), reason);
    }

    private void tickPendingRescue(ServerPlayer owner) {
        getNavigation().stop();
        setDeltaMovement(Vec3.ZERO);
        setMotion(MOTION_IDLE);
        if (hiddenRetryTicks-- > 0) return;
        hiddenRetryTicks = HIDDEN_RETRY_TICKS;
        placeSafelyNear(owner, "safe-position-retry");
    }

    private void resetProgressTracking() {
        stalledTicks = 0;
        progressSamplePosition = position();
        routeRequested = false;
        following = false;
        repathTicks = 0;
    }

    private int idleMotion() {
        int phase = Math.floorMod((tickCount / 50) + getUUID().hashCode(), 9);
        return phase <= 1 ? MOTION_LOOK : phase == 2 ? MOTION_SIT : MOTION_IDLE;
    }

    private void setMotion(int motion) {
        if (entityData.get(MOTION) != motion) entityData.set(MOTION, motion);
    }

    @Override
    public boolean isInvulnerableTo(DamageSource source) { return true; }

    @Override
    public boolean canBeAffected(MobEffectInstance effect) { return false; }

    @Override
    public boolean isPickable() { return false; }

    @Override
    public boolean isAttackable() { return false; }

    @Override
    public boolean isPushable() { return false; }

    @Override
    public boolean canBeCollidedWith() { return false; }

    @Override
    public boolean canCollideWith(Entity other) { return false; }

    @Override
    public void push(Entity other) { }

    @Override
    public boolean isIgnoringBlockTriggers() { return true; }

    @Override
    public PushReaction getPistonPushReaction() { return PushReaction.IGNORE; }

    @Override
    protected InteractionResult mobInteract(Player player, InteractionHand hand) {
        return InteractionResult.PASS;
    }

    @Override
    public void checkDespawn() { }

    @Override
    public boolean removeWhenFarAway(double distanceToClosestPlayer) { return false; }

    @Override
    public boolean causeFallDamage(float distance, float multiplier, DamageSource source) { return false; }

    @Override
    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        if (tag.hasUUID("Owner")) entityData.set(OWNER, Optional.of(tag.getUUID("Owner")));
        entityData.set(MODEL, tag.getString("Model"));
        physicsProfile = CompanionPhysicsProfile.forModel(modelId());
        applyPhysicalBounds();
    }

    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        UUID owner = ownerId();
        if (owner != null) tag.putUUID("Owner", owner);
        tag.putString("Model", modelId());
    }
}
