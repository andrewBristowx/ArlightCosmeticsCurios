package com.arlight.cosmeticscurios;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Mth;
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

    private int stuckTicks;
    private double lastDistanceSq = Double.MAX_VALUE;

    public CompanionEntity(EntityType<? extends CompanionEntity> type, Level level) {
        super(type, level);
        setInvulnerable(true);
        setSilent(true);
        setPersistenceRequired();
        xpReward = 0;
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 1.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.34D)
                .add(Attributes.FOLLOW_RANGE, 32.0D);
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
        super.tick();
        clearFire();
        if (level().isClientSide) return;

        ServerPlayer owner = resolveOwner();
        if (owner == null || owner.isSpectator() || !owner.isAlive()
                || !CompanionManager.isEquipped(owner, modelId())) {
            discard();
            return;
        }

        double distanceSq = distanceToSqr(owner);
        if (distanceSq > 144.0D) {
            teleportNear(owner);
            setMotion(MOTION_IDLE);
            return;
        }

        Vec3 target = followTarget(owner);
        double targetDistanceSq = distanceToSqr(target);
        if (targetDistanceSq > 8.0D) {
            double speed = owner.isSprinting() || distanceSq > 49.0D ? 1.42D : 1.12D;
            getNavigation().moveTo(target.x, target.y, target.z, speed);
            setMotion(speed > 1.2D ? MOTION_RUN : MOTION_WALK);
        } else {
            getNavigation().stop();
            setDeltaMovement(getDeltaMovement().multiply(0.55D, 1.0D, 0.55D));
            setMotion(idleMotion());
        }

        if (!onGround() && getDeltaMovement().y > 0.015D) setMotion(MOTION_JUMP);
        if (isInWater()) setMotion(MOTION_SWIM);

        if (distanceSq > 16.0D && distanceSq >= lastDistanceSq - 0.025D) stuckTicks++;
        else stuckTicks = Math.max(0, stuckTicks - 2);
        lastDistanceSq = distanceSq;
        if (stuckTicks > 60) {
            teleportNear(owner);
            stuckTicks = 0;
        }
    }

    private ServerPlayer resolveOwner() {
        if (!(level() instanceof ServerLevel serverLevel)) return null;
        UUID id = ownerId();
        if (id == null) return null;
        ServerPlayer owner = serverLevel.getServer().getPlayerList().getPlayer(id);
        return owner != null && owner.level() == level() ? owner : null;
    }

    private Vec3 followTarget(ServerPlayer owner) {
        Vec3 look = owner.getLookAngle();
        Vec3 horizontal = new Vec3(look.x, 0.0D, look.z);
        if (horizontal.lengthSqr() < 0.001D) horizontal = new Vec3(0.0D, 0.0D, 1.0D);
        horizontal = horizontal.normalize();
        double side = (owner.getUUID().hashCode() & 1) == 0 ? 1.0D : -1.0D;
        Vec3 lateral = new Vec3(-horizontal.z * side, 0.0D, horizontal.x * side);
        return owner.position().subtract(horizontal.scale(2.15D)).add(lateral.scale(1.05D));
    }

    public boolean teleportNear(ServerPlayer owner) {
        Vec3 target = followTarget(owner);
        int[] verticalChecks = {0, 1, -1, 2, -2, 3, -3};
        double[][] horizontalChecks = {
                {0.0D, 0.0D}, {1.0D, 0.0D}, {-1.0D, 0.0D},
                {0.0D, 1.0D}, {0.0D, -1.0D}
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
                setYRot(owner.getYRot());
                setYHeadRot(owner.getYRot());
                return true;
            }
        }
        return false;
    }

    private boolean safeAt(double x, double y, double z) {
        BlockPos feet = BlockPos.containing(x, y, z);
        BlockPos support = feet.below();
        if (!level().getBlockState(support).isFaceSturdy(level(), support, Direction.UP)) return false;
        AABB moved = getBoundingBox().move(x - getX(), y - getY(), z - getZ());
        return level().noCollision(this, moved) && level().getFluidState(feet).isEmpty();
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
    }

    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        UUID owner = ownerId();
        if (owner != null) tag.putUUID("Owner", owner);
        tag.putString("Model", modelId());
    }
}
