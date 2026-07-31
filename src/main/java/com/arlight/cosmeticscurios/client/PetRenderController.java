package com.arlight.cosmeticscurios.client;

import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.Mth;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;

/**
 * Simula una mascota visual en coordenadas del mundo y convierte su posición a
 * la traslación local que necesita el renderizador Curios. No crea entidades ni
 * hitboxes en el servidor, pero sí consulta el suelo y las colisiones del mundo
 * cliente para que el compañero no atraviese paredes ni flote sobre desniveles.
 */
public final class PetRenderController {
    public enum Motion { IDLE, LOOK, SIT, WALK, RUN, JUMP, SWIM, FLY, SHOULDER }

    public record Transform(float x, float y, float z, float yaw,
                            float appearanceScale, Motion motion,
                            boolean teleported) { }

    private record Placement(double x, double y, double z, boolean valid) {
        static Placement invalid() { return new Placement(0.0D, 0.0D, 0.0D, false); }
    }

    private static final class State {
        double worldX, worldY, worldZ;
        double lastOwnerX, lastOwnerY, lastOwnerZ;
        float yaw;
        float appearanceScale = 1.0F;
        float lastRenderTick;
        ResourceKey<Level> dimension;
        long lastSeen;
        long ambientUntilTick;
        long shoulderUntilTick;
        int stuckTicks;
        boolean initialized;
        String modelId = "";
        Motion ambientMotion = Motion.IDLE;
    }

    private static final Map<UUID, State> STATES = new HashMap<>();
    private static long clock;

    public static Transform update(AbstractClientPlayer player, String modelId, float partialTick) {
        clock++;
        PetRenderProfile profile = PetRenderProfile.forModel(modelId);
        PetPositionSettings.Offset custom = PetPositionSettings.get(modelId);
        State state = STATES.computeIfAbsent(player.getUUID(), ignored -> new State());
        ResourceKey<Level> dimension = player.level().dimension();

        double px = Mth.lerp(partialTick, player.xo, player.getX());
        double py = Mth.lerp(partialTick, player.yo, player.getY());
        double pz = Mth.lerp(partialTick, player.zo, player.getZ());
        double ownerJump = state.initialized
                ? Math.sqrt(sq(px - state.lastOwnerX) + sq(py - state.lastOwnerY)
                + sq(pz - state.lastOwnerZ)) : 0.0D;
        boolean changedModel = modelId != null && !modelId.equals(state.modelId);
        boolean dimensionChanged = state.initialized && !dimension.equals(state.dimension);
        boolean teleported = !state.initialized || changedModel || dimensionChanged || ownerJump > 4.25D;

        float bodyYaw = player.yBodyRot * Mth.DEG_TO_RAD;
        float cos = Mth.cos(bodyYaw);
        float sin = Mth.sin(bodyYaw);
        float preferredSide = preferredSide(player);
        Motion ownerMotion = ownerMotion(player);

        float desiredSide = preferredSide * profile.sideDistance() + custom.x();
        float desiredBack = profile.backDistance() + custom.z();
        Placement goal = chooseGoal(player, profile, px, py, pz, cos, sin,
                desiredSide, desiredBack);

        boolean forcedShoulder = ownerMotion == Motion.FLY || ownerMotion == Motion.SWIM;
        if (!goal.valid()) {
            state.stuckTicks++;
            if (state.stuckTicks > 12) state.shoulderUntilTick = player.tickCount + 50L;
        }
        boolean shoulder = forcedShoulder || player.tickCount < state.shoulderUntilTick;
        if (shoulder) goal = shoulderPlacement(px, py, pz, cos, sin, preferredSide, custom);

        if (teleported || !state.initialized) {
            Placement initial = goal.valid() ? goal
                    : shoulderPlacement(px, py, pz, cos, sin, preferredSide, custom);
            state.worldX = initial.x();
            state.worldY = initial.y();
            state.worldZ = initial.z();
            state.modelId = modelId == null ? "" : modelId;
            state.appearanceScale = 0.72F;
            state.stuckTicks = 0;
        } else {
            state.appearanceScale = Mth.lerp(0.13F, state.appearanceScale, 1.0F);
            float renderTick = player.tickCount + partialTick;
            float dt = state.lastRenderTick <= 0.0F
                    ? 0.05F : Mth.clamp(renderTick - state.lastRenderTick, 0.0F, 1.0F);
            if (goal.valid()) {
                boolean moved = moveToward(player, state, goal, profile, dt, shoulder);
                double goalDistance = horizontalDistance(state.worldX, state.worldZ,
                        goal.x(), goal.z());
                if (!moved && goalDistance > 0.65D) state.stuckTicks++;
                else if (goalDistance < 0.45D || moved) state.stuckTicks = Math.max(0, state.stuckTicks - 2);

                if (state.stuckTicks > 28 || goalDistance > 6.0D) {
                    state.shoulderUntilTick = player.tickCount + 45L;
                    Placement recovery = shoulderPlacement(px, py, pz, cos, sin,
                            preferredSide, custom);
                    state.worldX = recovery.x();
                    state.worldY = recovery.y();
                    state.worldZ = recovery.z();
                    state.appearanceScale = 0.78F;
                    state.stuckTicks = 0;
                    teleported = true;
                    shoulder = true;
                }
            }
            state.lastRenderTick = renderTick;
        }

        Motion motion = selectMotion(player, state, ownerMotion, shoulder, profile);
        double dx = state.worldX - px;
        double dz = state.worldZ - pz;
        float localX = (float) (dx * cos + dz * sin);
        float localZ = (float) (-dx * sin + dz * cos);
        float localY = shoulder
                ? (float) (py - state.worldY)
                : (float) (py - state.worldY) + profile.baseYOffset() + custom.y();

        float targetYaw = motion == Motion.LOOK
                ? Mth.sin((player.tickCount + partialTick) * 0.12F) * 24.0F
                : Mth.clamp(-localX * 22.0F, -32.0F, 32.0F);
        state.yaw = Mth.rotLerp(0.18F, state.yaw, targetYaw);
        state.lastOwnerX = px;
        state.lastOwnerY = py;
        state.lastOwnerZ = pz;
        state.dimension = dimension;
        state.initialized = true;
        state.lastSeen = clock;
        if ((clock & 127L) == 0L) cleanup();

        return new Transform(localX, localY, localZ, state.yaw,
                state.appearanceScale, motion, teleported);
    }

    /** Pose estable usada exclusivamente por el maniquí del ropero. */
    public static Transform preview(String modelId) {
        PetRenderProfile profile = PetRenderProfile.forModel(modelId);
        PetPositionSettings.Offset custom = PetPositionSettings.get(modelId);
        return new Transform(profile.previewSide() + custom.x(),
                profile.previewYOffset() + custom.y(),
                profile.previewBack() + custom.z(), -8.0F, profile.previewScale(),
                Motion.IDLE, false);
    }

    public static void clear() { STATES.clear(); }

    public static void clear(UUID owner) {
        if (owner != null) STATES.remove(owner);
    }

    private static Placement chooseGoal(AbstractClientPlayer player, PetRenderProfile profile,
                                        double px, double py, double pz, float cos, float sin,
                                        float desiredSide, float desiredBack) {
        if (profile.floating()) {
            return floatingPlacement(player, px, py, pz, cos, sin,
                    desiredSide, desiredBack, profile);
        }

        float[][] candidates = {
                {desiredSide, desiredBack},
                {-desiredSide, desiredBack + 0.10F},
                {desiredSide * 0.72F, -0.18F},
                {-desiredSide * 0.72F, -0.18F},
                {0.0F, desiredBack + 0.38F}
        };
        for (float[] candidate : candidates) {
            double wx = px + cos * candidate[0] - sin * candidate[1];
            double wz = pz + sin * candidate[0] + cos * candidate[1];
            Placement placement = groundedPlacement(player.level(), wx, wz, py,
                    profile.collisionRadius(), profile.collisionHeight());
            if (placement.valid()) return placement;
        }
        return Placement.invalid();
    }

    private static Placement floatingPlacement(AbstractClientPlayer player,
                                                double px, double py, double pz,
                                                float cos, float sin, float side, float back,
                                                PetRenderProfile profile) {
        double height = Math.max(0.55D, -profile.baseYOffset() + 0.18D);
        float[][] candidates = {
                {side, back}, {-side, back + 0.12F},
                {side * 0.65F, -0.16F}, {-side * 0.65F, -0.16F}
        };
        for (float[] candidate : candidates) {
            double wx = px + cos * candidate[0] - sin * candidate[1];
            double wz = pz + sin * candidate[0] + cos * candidate[1];
            double wy = py + height;
            if (hasRoom(player.level(), wx, wy, wz,
                    profile.collisionRadius(), profile.collisionHeight())) {
                return new Placement(wx, wy, wz, true);
            }
        }
        return Placement.invalid();
    }

    private static Placement shoulderPlacement(double px, double py, double pz,
                                                float cos, float sin, float side,
                                                PetPositionSettings.Offset custom) {
        float localX = side * 0.30F + custom.x() * 0.35F;
        float localZ = -0.02F + custom.z() * 0.25F;
        return new Placement(px + cos * localX - sin * localZ,
                py + 1.33D - custom.y() * 0.30D,
                pz + sin * localX + cos * localZ, true);
    }

    private static boolean moveToward(AbstractClientPlayer player, State state,
                                      Placement goal, PetRenderProfile profile,
                                      float dt, boolean shoulder) {
        double dx = goal.x() - state.worldX;
        double dz = goal.z() - state.worldZ;
        double distance = Math.sqrt(dx * dx + dz * dz);
        if (distance < 0.012D) {
            state.worldY = Mth.lerp(Math.min(1.0F, dt * 0.35F),
                    state.worldY, goal.y());
            return true;
        }

        double blocksPerTick = shoulder ? 0.42D
                : player.isSprinting() ? 0.31D : 0.19D;
        double step = Math.min(distance, blocksPerTick * Math.max(0.05F, dt));
        double nx = state.worldX + dx / distance * step;
        double nz = state.worldZ + dz / distance * step;
        if (shoulder || profile.floating()) {
            if (shoulder || hasRoom(player.level(), nx, goal.y(), nz,
                    profile.collisionRadius(), profile.collisionHeight())) {
                state.worldX = nx;
                state.worldZ = nz;
                state.worldY = Mth.lerp(Math.min(1.0F, dt * 0.28F),
                        state.worldY, goal.y());
                return true;
            }
            return false;
        }

        Placement direct = groundedPlacement(player.level(), nx, nz, state.worldY,
                profile.collisionRadius(), profile.collisionHeight());
        if (canStep(state, direct, profile)) {
            applyGroundStep(state, direct, dt);
            return true;
        }

        Placement xOnly = groundedPlacement(player.level(), nx, state.worldZ, state.worldY,
                profile.collisionRadius(), profile.collisionHeight());
        if (canStep(state, xOnly, profile)) {
            applyGroundStep(state, xOnly, dt);
            return true;
        }
        Placement zOnly = groundedPlacement(player.level(), state.worldX, nz, state.worldY,
                profile.collisionRadius(), profile.collisionHeight());
        if (canStep(state, zOnly, profile)) {
            applyGroundStep(state, zOnly, dt);
            return true;
        }
        return false;
    }

    private static boolean canStep(State state, Placement placement, PetRenderProfile profile) {
        if (!placement.valid()) return false;
        double rise = placement.y() - state.worldY;
        return rise <= profile.maxStepHeight() && rise >= -profile.maxSafeDrop();
    }

    private static void applyGroundStep(State state, Placement placement, float dt) {
        state.worldX = placement.x();
        state.worldZ = placement.z();
        state.worldY = Mth.lerp(Math.min(1.0F, Math.max(0.24F, dt * 0.55F)),
                state.worldY, placement.y());
    }

    private static Placement groundedPlacement(Level level, double x, double z,
                                               double referenceY, float radius, float height) {
        int centerY = Mth.floor(referenceY + 1.25D);
        for (int y = centerY + 1; y >= centerY - 4; y--) {
            BlockPos support = BlockPos.containing(x, y, z);
            BlockState state = level.getBlockState(support);
            if (!state.getFluidState().isEmpty()) continue;
            VoxelShape shape = state.getCollisionShape(level, support);
            if (shape.isEmpty()) continue;
            double top = support.getY() + shape.max(Direction.Axis.Y);
            if (Math.abs(top - referenceY) > 2.25D) continue;
            if (hasRoom(level, x, top + 0.02D, z, radius, height)) {
                return new Placement(x, top, z, true);
            }
        }
        return Placement.invalid();
    }

    private static boolean hasRoom(Level level, double x, double y, double z,
                                   float radius, float height) {
        double[][] samples = {
                {0.0D, 0.0D}, {radius, radius}, {radius, -radius},
                {-radius, radius}, {-radius, -radius}
        };
        for (double[] sample : samples) {
            if (blocked(level, x + sample[0], y + 0.08D, z + sample[1])) return false;
            if (blocked(level, x + sample[0], y + height, z + sample[1])) return false;
        }
        return true;
    }

    private static boolean blocked(Level level, double x, double y, double z) {
        BlockPos pos = BlockPos.containing(x, y, z);
        BlockState state = level.getBlockState(pos);
        if (!state.getFluidState().isEmpty()) return true;
        return !state.getCollisionShape(level, pos).isEmpty();
    }

    private static Motion selectMotion(AbstractClientPlayer player, State state,
                                       Motion ownerMotion, boolean shoulder,
                                       PetRenderProfile profile) {
        if (shoulder) return Motion.SHOULDER;
        if (ownerMotion == Motion.FLY || ownerMotion == Motion.SWIM) return ownerMotion;

        double ownerSpeed = player.getDeltaMovement().horizontalDistanceSqr();
        double distance = horizontalDistance(state.worldX, state.worldZ,
                player.getX(), player.getZ());
        if (distance > profile.backDistance() + profile.sideDistance() + 0.42D) {
            return ownerSpeed > 0.055D || player.isSprinting() ? Motion.RUN : Motion.WALK;
        }
        if (Math.abs(state.worldY - player.getY()) > 0.28D && ownerSpeed > 0.001D) {
            return Motion.JUMP;
        }
        if (ownerSpeed > 0.055D || player.isSprinting()) return Motion.RUN;
        if (ownerSpeed > 0.0012D) return Motion.WALK;

        if (player.tickCount >= state.ambientUntilTick) {
            long seed = player.getUUID().getLeastSignificantBits() ^ (player.tickCount / 40L);
            int choice = Math.floorMod((int) (seed ^ (seed >>> 32)), 7);
            state.ambientMotion = choice <= 2 ? Motion.LOOK
                    : choice == 3 ? Motion.SIT : Motion.IDLE;
            state.ambientUntilTick = player.tickCount + 55L + Math.floorMod(choice * 23, 75);
        }
        return state.ambientMotion;
    }

    private static Motion ownerMotion(AbstractClientPlayer player) {
        if (player.isFallFlying()) return Motion.FLY;
        if (player.isSwimming() || player.isInWater()) return Motion.SWIM;
        double speed = player.getDeltaMovement().horizontalDistanceSqr();
        if (speed > 0.055D || player.isSprinting()) return Motion.RUN;
        if (speed > 0.0012D) return Motion.WALK;
        return Motion.IDLE;
    }

    private static float preferredSide(AbstractClientPlayer player) {
        return switch (ClientCosmeticSettings.petSide()) {
            case "left" -> -1.0F;
            case "right" -> 1.0F;
            default -> (player.getUUID().hashCode() & 1) == 0 ? 1.0F : -1.0F;
        };
    }

    private static void cleanup() {
        Iterator<State> iterator = STATES.values().iterator();
        while (iterator.hasNext()) {
            if (clock - iterator.next().lastSeen > 600L) iterator.remove();
        }
    }

    private static double horizontalDistance(double ax, double az, double bx, double bz) {
        return Math.sqrt(sq(ax - bx) + sq(az - bz));
    }

    private static double sq(double value) { return value * value; }

    private PetRenderController() { }
}
