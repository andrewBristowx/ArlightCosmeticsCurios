package com.arlight.weaponskins.mixin;

import com.arlight.weaponskins.WeaponSkinResolver;
import net.minecraft.client.Minecraft;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(targets = "net.minecraft.client.renderer.ItemInHandRenderer", priority = 2000)
public abstract class ItemInHandRendererMixin {
    @ModifyVariable(method = "renderItem", at = @At("HEAD"), argsOnly = true,
            ordinal = 0, require = 1)
    private ItemStack arlightWeaponSkins$replaceFirstPersonItem(ItemStack original) {
        Minecraft minecraft = Minecraft.getInstance();
        var owner = minecraft == null ? null : minecraft.player;
        return WeaponSkinResolver.replacement(original, owner);
    }

    /**
     * Ultimo punto de sustitucion antes de que ItemRenderer dibuje el objeto.
     * Algunos renderizadores del modpack vuelven a copiar el ItemStack original
     * despues del hook de entrada; este hook final evita que deshagan la skin.
     */
    @ModifyArg(
            method = "renderItem",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/renderer/entity/ItemRenderer;renderStatic(Lnet/minecraft/world/entity/LivingEntity;Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/item/ItemDisplayContext;ZLcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;Lnet/minecraft/world/level/Level;III)V"),
            index = 1,
            require = 1)
    private ItemStack arlightWeaponSkins$replaceFinalFirstPersonItem(ItemStack original) {
        Minecraft minecraft = Minecraft.getInstance();
        var owner = minecraft == null ? null : minecraft.player;
        return WeaponSkinResolver.replacement(original, owner);
    }
}
