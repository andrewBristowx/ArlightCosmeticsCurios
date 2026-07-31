package com.arlight.cosmeticscurios.client;

import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.Mth;
import net.minecraft.world.level.Level;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;

/**
 * Posiciona una sola mascota visual por propietario. El seguimiento se mantiene
 * separado del modelo para que cada criatura pueda tener distancia, altura y
 * comportamiento propios sin crear entidades ni hitboxes en el servidor.
 */
public final class PetRenderController {
    public enum Motion { IDLE, WALK, RUN, SWIM, FLY }
    public record Transform(float x, float y, float z, float yaw,
                            float appearanceScale, Motion motion,
                            boolean teleported) { }

    private static final class State {
        float x, y, z, yaw;
        double lastX, lastY, lastZ;
        ResourceKey<Level> dimension;
        long lastSeen;
        boolean initialized;
        String modelId = "";
        float appearanceScale = 1.0F;
    }

    private static final Map<UUID, State> STATES = new HashMap<>();
    private static long clock;

    public static Transform update(AbstractClientPlayer player, String modelId, float partialTick) {
        clock++;
        PetRenderProfile profile = PetRenderProfile.forModel(modelId);
        State state = STATES.computeIfAbsent(player.getUUID(), ignored -> new State());
        ResourceKey<Level> dimension = player.level().dimension();
        double px = Mth.lerp(partialTick, player.xo, player.getX());
        double py = Mth.lerp(partialTick, player.yo, player.getY());
        double pz = Mth.lerp(partialTick, player.zo, player.getZ());
        double jump = state.initialized
                ? Math.sqrt(sq(px - state.lastX) + sq(py - state.lastY) + sq(pz - state.lastZ))
                : 0.0D;
        boolean changedModel = modelId != null && !modelId.equals(state.modelId);
        boolean teleported = !state.initialized || !dimension.equals(state.dimension) || jump > 3.25D;
        if (changedModel || teleported) {
            state.modelId = modelId == null ? "" : modelId;
            state.appearanceScale = 0.72F;
        } else {
            state.appearanceScale = Mth.lerp(0.13F, state.appearanceScale, 1.0F);
        }

        Motion motion = motion(player);
        float speed = (float) Math.sqrt(player.getDeltaMovement().horizontalDistanceSqr());
        float bodyYaw = player.yBodyRot * Mth.DEG_TO_RAD;
        float vx = (float) player.getDeltaMovement().x;
        float vz = (float) player.getDeltaMovement().z;
        float localSide = vx * Mth.cos(bodyYaw) + vz * Mth.sin(bodyYaw);
        float localForward = -vx * Mth.sin(bodyYaw) + vz * Mth.cos(bodyYaw);

        float preferredSide = switch (ClientCosmeticSettings.petSide()) {
            case "left" -> -1.0F;
            case "right" -> 1.0F;
            default -> (player.getUUID().hashCode() & 1) == 0 ? 1.0F : -1.0F;
        };

        // La versión anterior usaba 0.16 bloques y las mascotas parecían pegadas
        // a la pierna. Los perfiles actuales conservan un pasillo visible entre
        // el jugador y la criatura, y retrasan más a las mascotas grandes.
        PetPositionSettings.Offset custom = PetPositionSettings.get(modelId);
        float targetX = preferredSide * profile.sideDistance() + custom.x()
                - Mth.clamp(localSide * 1.25F, -0.16F, 0.16F);
        float targetZ = profile.backDistance() + custom.z()
                + Mth.clamp(-localForward * 1.8F, -0.08F,
                motion == Motion.RUN ? 0.30F : 0.20F);
        float targetY = profile.baseYOffset() + custom.y();

        if (motion == Motion.SWIM) {
            targetY = profile.floating() ? -0.36F : -0.22F;
            targetZ -= 0.08F;
        } else if (motion == Motion.FLY) {
            targetY = profile.floating() ? -0.44F : 0.22F;
            targetZ += 0.10F;
        } else if (player.isCrouching()) {
            targetY += 0.10F;
        }

        float targetYaw = Mth.clamp(-localSide * 180.0F, -30.0F, 30.0F);
        float smoothing = teleported ? 1.0F
                : Mth.clamp(ClientCosmeticSettings.petSmoothing()
                + Math.min(0.11F, speed * 0.72F), 0.08F, 0.42F);
        state.x = Mth.lerp(smoothing, state.x, targetX);
        state.y = Mth.lerp(smoothing, state.y, targetY);
        state.z = Mth.lerp(smoothing, state.z, targetZ);
        state.yaw = Mth.lerp(smoothing * 0.70F, state.yaw, targetYaw);
        state.lastX = px;
        state.lastY = py;
        state.lastZ = pz;
        state.dimension = dimension;
        state.initialized = true;
        state.lastSeen = clock;

        if ((clock & 127L) == 0L) cleanup();
        return new Transform(state.x, state.y, state.z, state.yaw,
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

    public static void clear() {
        STATES.clear();
    }

    public static void clear(UUID owner) {
        if (owner != null) STATES.remove(owner);
    }

    private static Motion motion(AbstractClientPlayer player) {
        if (player.isFallFlying()) return Motion.FLY;
        if (player.isSwimming() || player.isInWater()) return Motion.SWIM;
        double speed = player.getDeltaMovement().horizontalDistanceSqr();
        if (speed > 0.055D || player.isSprinting()) return Motion.RUN;
        if (speed > 0.0012D) return Motion.WALK;
        return Motion.IDLE;
    }

    private static void cleanup() {
        Iterator<State> iterator = STATES.values().iterator();
        while (iterator.hasNext()) {
            if (clock - iterator.next().lastSeen > 600L) iterator.remove();
        }
    }

    private static double sq(double value) {
        return value * value;
    }

    private PetRenderController() { }
}
