package com.arlight.weaponskins.mixin;

import com.arlight.weaponskins.RenderOwnerContext;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(targets = "net.minecraft.client.renderer.entity.layers.ItemInHandLayer", priority = 2000)
public abstract class ItemInHandLayerMixin {
    @Inject(method = "renderArmWithItem", at = @At("HEAD"), require = 0)
    private void arlightWeaponSkins$pushOwner(LivingEntity entity, ItemStack stack,
            ItemDisplayContext context, HumanoidArm arm, PoseStack poseStack,
            MultiBufferSource buffers, int light, CallbackInfo callback) {
        RenderOwnerContext.push(entity);
    }

    @Inject(method = "renderArmWithItem", at = @At("RETURN"), require = 0)
    private void arlightWeaponSkins$popOwner(LivingEntity entity, ItemStack stack,
            ItemDisplayContext context, HumanoidArm arm, PoseStack poseStack,
            MultiBufferSource buffers, int light, CallbackInfo callback) {
        RenderOwnerContext.pop();
    }
}
