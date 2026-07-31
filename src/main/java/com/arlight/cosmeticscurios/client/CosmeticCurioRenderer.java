package com.arlight.cosmeticscurios.client;

import com.arlight.cosmeticscurios.CosmeticItemCatalog;
import com.arlight.cosmeticscurios.CosmeticOwnership;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.client.ICurioRenderer;

/** Renderizador con ajuste slim/clásico, LOD y mascotas suavizadas. */
public final class CosmeticCurioRenderer implements ICurioRenderer {
    @Override
    @SuppressWarnings({"unchecked", "rawtypes"})
    public <T extends LivingEntity, M extends EntityModel<T>> void render(
            ItemStack stack, SlotContext slotContext, PoseStack poseStack,
            RenderLayerParent<T, M> renderLayerParent, MultiBufferSource buffers,
            int light, float limbSwing, float limbSwingAmount, float partialTicks,
            float ageInTicks, float netHeadYaw, float headPitch) {
        if (!(slotContext.entity() instanceof AbstractClientPlayer player)) return;
        if (!(renderLayerParent.getModel() instanceof PlayerModel playerModel)) return;
        if (!CosmeticOwnership.canUse(stack, player)) return;

        CosmeticItemCatalog.Definition definition = CosmeticItemCatalog.byItem(stack.getItem());
        if (definition == null || definition.modelId().isBlank()) return;
        if (shouldHideForArmor(player, definition)) return;
        if (player.isInvisible() && Minecraft.getInstance().player != player) return;

        Minecraft minecraft = Minecraft.getInstance();
        AbstractClientPlayer viewer = minecraft.player;
        double distanceSq = viewer == null ? 0.0D : viewer.distanceToSqr(player);
        int renderDistance = Math.min(ClientCosmeticSettings.renderDistance(), definition.renderDistance());
        if (distanceSq > (double) renderDistance * renderDistance) return;
        int fullDistance = Math.min(ClientCosmeticSettings.fullAnimationDistance(), definition.fullAnimationDistance());
        boolean simplified = distanceSq > (double) fullDistance * fullDistance;

        ProfessionalCosmeticModel model = ProfessionalCosmeticModels.get(definition.modelId());
        if (model == null) return;

        boolean wardrobePreview = WardrobePreviewController.isPreviewing(definition.itemId());
        PetRenderController.Transform petTransform = definition.companion()
                ? (wardrobePreview
                    ? PetRenderController.preview(definition.modelId())
                    : PetRenderController.update(player, definition.modelId(), partialTicks))
                : new PetRenderController.Transform(0, 0, 0, 0, 1.0F, PetRenderController.Motion.IDLE, false);
        if (definition.companion() && petTransform.appearanceScale() <= 0.01F) return;
        ProfessionalCosmeticAnimations.apply(model, player, partialTicks, limbSwing,
                limbSwingAmount, ageInTicks, petTransform.motion(), simplified);

        boolean slim = PlayerModelType.isSlim(player);
        for (CosmeticAnchor anchor : model.anchors()) {
            poseStack.pushPose();
            applyAnchor((PlayerModel) playerModel, anchor, poseStack);

            if (definition.companion()) {
                poseStack.translate(
                        definition.offsetX() + petTransform.x(),
                        definition.offsetY() + petTransform.y(),
                        definition.offsetZ() + petTransform.z());
                poseStack.mulPose(com.mojang.math.Axis.YP.rotationDegrees(petTransform.yaw()));
            }

            float scale = definition.scale() * (definition.companion() ? petTransform.appearanceScale() : 1.0F);
            if (slim && (anchor == CosmeticAnchor.LEFT_ARM || anchor == CosmeticAnchor.RIGHT_ARM)) {
                poseStack.scale(definition.slimArmScale(), 1.0F, definition.slimArmScale());
            }
            poseStack.scale(scale, scale, scale);

            boolean armorFit = definition.slotId().equals("arlight_chest")
                    || definition.slotId().equals("arlight_legs")
                    || definition.slotId().equals("arlight_feet")
                    || definition.slotId().equals("arlight_head");
            if (armorFit) {
                // Las prendas funcionan como una capa de armadura cosmética real.
                // El margen por ranura evita que la skin atraviese capuchas, mangas,
                // cintura, rodillas o zapatillas incluso con skins de segunda capa.
                float armorScale = switch (definition.slotId()) {
                    case "arlight_head" -> 1.040F;
                    case "arlight_chest" -> 1.034F;
                    case "arlight_legs" -> 1.032F;
                    case "arlight_feet" -> 1.030F;
                    default -> 1.012F;
                };
                poseStack.scale(armorScale, armorScale, armorScale);
            }
            model.render(anchor, poseStack, buffers, light, armorFit);
            poseStack.popPose();
        }
    }

    private static boolean shouldHideForArmor(AbstractClientPlayer player,
                                              CosmeticItemCatalog.Definition definition) {
        // Los pijamas son una apariencia completa: deben seguir visibles encima
        // de la armadura para que ésta conserve estadísticas sin reemplazar el look.
        if ("pajamas".equals(definition.category())) return false;
        if (!ClientCosmeticSettings.hideUnderArmor()) return false;
        if (!"hide_matching".equals(definition.armorPolicy())) return false;
        EquipmentSlot slot = switch (definition.slotId()) {
            case "arlight_head" -> EquipmentSlot.HEAD;
            case "arlight_chest" -> EquipmentSlot.CHEST;
            case "arlight_legs" -> EquipmentSlot.LEGS;
            case "arlight_feet" -> EquipmentSlot.FEET;
            default -> null;
        };
        return slot != null && !player.getItemBySlot(slot).isEmpty();
    }

    private static void applyAnchor(PlayerModel<?> model, CosmeticAnchor anchor, PoseStack poseStack) {
        switch (anchor) {
            case ROOT -> { }
            case HEAD -> model.head.translateAndRotate(poseStack);
            case BODY -> model.body.translateAndRotate(poseStack);
            case LEFT_ARM -> model.leftArm.translateAndRotate(poseStack);
            case RIGHT_ARM -> model.rightArm.translateAndRotate(poseStack);
            case LEFT_LEG -> model.leftLeg.translateAndRotate(poseStack);
            case RIGHT_LEG -> model.rightLeg.translateAndRotate(poseStack);
        }
    }
}
