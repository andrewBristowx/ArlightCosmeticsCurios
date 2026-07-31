package com.arlight.cosmeticscurios.client;

import com.arlight.cosmeticscurios.CompanionEntity;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.util.Mth;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;

/** Convierte la posición de la entidad real a la traslación del modelo Curios. */
public final class PetRenderController {
    public enum Motion { IDLE, LOOK, SIT, WALK, RUN, JUMP, SWIM, FLY, SHOULDER }

    public record Transform(float x, float y, float z, float yaw,
                            float appearanceScale, Motion motion,
                            boolean teleported) { }

    private static final class State {
        int entityId = Integer.MIN_VALUE;
        float appearanceScale;
        long lastSeen;
    }

    private static final Map<UUID, State> STATES = new HashMap<>();
    private static long clock;

    public static Transform update(AbstractClientPlayer player, String modelId, float partialTick) {
        clock++;
        State state = STATES.computeIfAbsent(player.getUUID(), ignored -> new State());
        state.lastSeen = clock;
        CompanionEntity companion = findCompanion(player, modelId, state);
        if (companion == null || companion.isInvisible()) {
            state.appearanceScale = 0.0F;
            if ((clock & 127L) == 0L) cleanup();
            return new Transform(0.0F, 0.0F, 0.0F, 0.0F,
                    0.0F, Motion.IDLE, false);
        }

        boolean appeared = state.entityId != companion.getId();
        state.entityId = companion.getId();
        state.appearanceScale = appeared
                ? 0.72F
                : Mth.lerp(0.16F, state.appearanceScale, 1.0F);

        double px = Mth.lerp(partialTick, player.xo, player.getX());
        double py = Mth.lerp(partialTick, player.yo, player.getY());
        double pz = Mth.lerp(partialTick, player.zo, player.getZ());
        double ex = Mth.lerp(partialTick, companion.xo, companion.getX());
        double ey = Mth.lerp(partialTick, companion.yo, companion.getY());
        double ez = Mth.lerp(partialTick, companion.zo, companion.getZ());

        float bodyYawDegrees = Mth.rotLerp(partialTick, player.yBodyRotO, player.yBodyRot);
        float bodyYaw = bodyYawDegrees * Mth.DEG_TO_RAD;
        float cos = Mth.cos(bodyYaw);
        float sin = Mth.sin(bodyYaw);
        double dx = ex - px;
        double dz = ez - pz;
        float localX = (float) (dx * cos + dz * sin);
        float localZ = (float) (-dx * sin + dz * cos);

        PetRenderProfile profile = PetRenderProfile.forModel(modelId);
        PetPositionSettings.Offset custom = PetPositionSettings.get(modelId);
        float localY = (float) (py - ey) + profile.baseYOffset() + custom.y();
        float entityYaw = Mth.rotLerp(partialTick, companion.yRotO, companion.getYRot());
        float relativeYaw = Mth.wrapDegrees(entityYaw - bodyYawDegrees);

        if ((clock & 127L) == 0L) cleanup();
        return new Transform(localX, localY, localZ, relativeYaw,
                state.appearanceScale, motion(companion.motionId()), appeared);
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

    private static CompanionEntity findCompanion(AbstractClientPlayer owner, String modelId,
                                                   State state) {
        if (state.entityId != Integer.MIN_VALUE) {
            var known = owner.level().getEntity(state.entityId);
            if (known instanceof CompanionEntity companion
                    && owner.getUUID().equals(companion.ownerId())
                    && modelId.equals(companion.modelId())) {
                return companion;
            }
        }
        return owner.level().getEntitiesOfClass(CompanionEntity.class,
                        owner.getBoundingBox().inflate(32.0D),
                        companion -> owner.getUUID().equals(companion.ownerId())
                                && modelId.equals(companion.modelId()))
                .stream().findFirst().orElse(null);
    }

    private static Motion motion(int id) {
        return switch (id) {
            case CompanionEntity.MOTION_LOOK -> Motion.LOOK;
            case CompanionEntity.MOTION_SIT -> Motion.SIT;
            case CompanionEntity.MOTION_WALK -> Motion.WALK;
            case CompanionEntity.MOTION_RUN -> Motion.RUN;
            case CompanionEntity.MOTION_JUMP -> Motion.JUMP;
            case CompanionEntity.MOTION_SWIM -> Motion.SWIM;
            case CompanionEntity.MOTION_FLY -> Motion.FLY;
            default -> Motion.IDLE;
        };
    }

    private static void cleanup() {
        Iterator<State> iterator = STATES.values().iterator();
        while (iterator.hasNext()) {
            if (clock - iterator.next().lastSeen > 600L) iterator.remove();
        }
    }

    private PetRenderController() { }
}
