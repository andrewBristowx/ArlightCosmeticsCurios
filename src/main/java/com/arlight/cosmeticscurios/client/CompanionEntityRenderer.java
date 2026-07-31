package com.arlight.cosmeticscurios.client;

import com.arlight.cosmeticscurios.CompanionEntity;
import com.arlight.cosmeticscurios.CosmeticItemCatalog;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

/** Dibuja la mascota en la posición real sincronizada por el servidor. */
public final class CompanionEntityRenderer extends EntityRenderer<CompanionEntity> {
    private static final ResourceLocation FALLBACK = ResourceLocation.fromNamespaceAndPath(
            "arlightcosmeticscurios", "textures/cosmetics/swansito_mini_trex.png");

    public CompanionEntityRenderer(EntityRendererProvider.Context context) {
        super(context);
        shadowRadius = 0.0F;
    }

    @Override
    public void render(CompanionEntity entity, float yaw, float partialTick,
                       PoseStack poseStack, MultiBufferSource buffers, int light) {
        if (entity.isInvisible() || entity.modelId().isBlank()) return;
        ProfessionalCosmeticModel model = ProfessionalCosmeticModels.get(entity.modelId());
        CosmeticItemCatalog.Definition definition = CosmeticItemCatalog.byId(entity.modelId());
        if (model == null || definition == null || !definition.companion()) return;

        Minecraft minecraft = Minecraft.getInstance();
        double distanceSq = minecraft.player == null ? 0.0D
                : minecraft.player.distanceToSqr(entity);
        int renderDistance = Math.min(ClientCosmeticSettings.renderDistance(), definition.renderDistance());
        if (distanceSq > (double) renderDistance * renderDistance) return;
        int fullDistance = Math.min(ClientCosmeticSettings.fullAnimationDistance(),
                definition.fullAnimationDistance());
        boolean simplified = distanceSq > (double) fullDistance * fullDistance;

        float age = entity.tickCount + partialTick;
        PetRenderController.Motion motion = PetRenderController.motion(entity.motionId());
        float limbAmount = switch (motion) {
            case RUN -> 1.0F;
            case WALK -> 0.55F;
            case SWIM, FLY -> 0.35F;
            default -> 0.0F;
        };
        ProfessionalCosmeticAnimations.apply(model, entity, partialTick, age * 0.72F,
                limbAmount, age, motion, simplified);

        CompanionVisualProfile visual = CompanionVisualProfile.forModel(entity.modelId());
        float entityYaw = Mth.rotLerp(partialTick, entity.yRotO, entity.getYRot());
        float scale = definition.scale();

        poseStack.pushPose();
        poseStack.mulPose(Axis.YP.rotationDegrees(180.0F - entityYaw));
        // Misma base espacial que LivingEntityRenderer, sin volver a anclar el
        // modelo a la rotación ni a la pose del jugador.
        poseStack.scale(-1.0F, -1.0F, 1.0F);
        poseStack.translate(visual.originX() + definition.offsetX(),
                -1.501F + visual.yOffset() + definition.offsetY(),
                visual.originZ() + definition.offsetZ());
        poseStack.scale(scale, scale, scale);
        model.render(CosmeticAnchor.ROOT, poseStack, buffers, light, false);
        poseStack.popPose();
    }

    @Override
    public ResourceLocation getTextureLocation(CompanionEntity entity) {
        return FALLBACK;
    }
}
