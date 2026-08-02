package com.arlight.weaponskins.mixin;

import com.arlight.weaponskins.WeaponSkinResolver;
import com.arlight.weaponskins.RenderOwnerContext;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = ItemRenderer.class, priority = 2000)
public abstract class ItemRendererMixin {
    @Inject(
            method = "renderStatic(Lnet/minecraft/world/entity/LivingEntity;Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/item/ItemDisplayContext;ZLcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;Lnet/minecraft/world/level/Level;III)V",
            at = @At("HEAD"), cancellable = true, require = 1)
    private void arlightWeaponSkins$renderForLivingEntity(LivingEntity entity, ItemStack original,
            ItemDisplayContext context, boolean leftHand, PoseStack poseStack,
            MultiBufferSource buffers, Level level, int light, int overlay, int seed,
            CallbackInfo callback) {
        LivingEntity owner = entity == null ? localPlayer() : entity;
        ItemStack replacement = WeaponSkinResolver.replacement(original, owner);
        if (replacement == original) return;

        ((ItemRenderer) (Object) this).renderStatic(entity, replacement, context, leftHand,
                poseStack, buffers, level, light, overlay, seed);
        callback.cancel();
    }

    @Inject(
            method = "renderStatic(Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/item/ItemDisplayContext;IILcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;Lnet/minecraft/world/level/Level;I)V",
            at = @At("HEAD"), cancellable = true, require = 1)
    private void arlightWeaponSkins$renderWithoutEntity(ItemStack original,
            ItemDisplayContext context, int light, int overlay, PoseStack poseStack,
            MultiBufferSource buffers, Level level, int seed, CallbackInfo callback) {
        ItemStack replacement = WeaponSkinResolver.replacement(original, localPlayer());
        if (replacement == original) return;

        ((ItemRenderer) (Object) this).renderStatic(replacement, context, light, overlay,
                poseStack, buffers, level, seed);
        callback.cancel();
    }

    /**
     * Ultimo hook real. Herramientas con renderer propio pueden saltarse renderStatic y
     * entregar directamente su BakedModel a ItemRenderer#render. Aqui recalculamos el modelo
     * para el ItemStack visual de GeckoLib antes de que se dibuje el objeto original.
     */
    @Inject(
            method = "render(Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/item/ItemDisplayContext;ZLcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;IILnet/minecraft/client/resources/model/BakedModel;)V",
            at = @At("HEAD"), cancellable = true, require = 1)
    private void arlightWeaponSkins$renderLowLevel(ItemStack original,
            ItemDisplayContext context, boolean leftHand, PoseStack poseStack,
            MultiBufferSource buffers, int light, int overlay, BakedModel originalModel,
            CallbackInfo callback) {
        LivingEntity owner = RenderOwnerContext.currentEntity();
        ItemStack replacement = WeaponSkinResolver.replacement(original, owner);
        if (replacement == original) return;

        Minecraft minecraft = Minecraft.getInstance();
        Level level = owner != null ? owner.level()
                : minecraft == null ? null : minecraft.level;
        BakedModel replacementModel = ((ItemRenderer) (Object) this)
                .getModel(replacement, level, owner, 0);
        ((ItemRenderer) (Object) this).render(replacement, context, leftHand,
                poseStack, buffers, light, overlay, replacementModel);
        callback.cancel();
    }

    private static LivingEntity localPlayer() {
        Minecraft minecraft = Minecraft.getInstance();
        return minecraft == null ? null : minecraft.player;
    }
}
