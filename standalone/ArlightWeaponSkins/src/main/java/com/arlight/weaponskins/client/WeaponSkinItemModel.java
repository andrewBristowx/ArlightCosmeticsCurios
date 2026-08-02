package com.arlight.weaponskins.client;

import com.arlight.weaponskins.ArlightWeaponSkins;
import com.arlight.weaponskins.WeaponSkinItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public final class WeaponSkinItemModel extends GeoModel<WeaponSkinItem> {
    @Override
    public ResourceLocation getModelResource(WeaponSkinItem item) {
        return ResourceLocation.fromNamespaceAndPath(ArlightWeaponSkins.MOD_ID,
                "geo/" + item.visualId() + ".geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(WeaponSkinItem item) {
        return ResourceLocation.fromNamespaceAndPath(ArlightWeaponSkins.MOD_ID,
                "textures/item/" + item.visualId() + ".png");
    }

    @Override
    public ResourceLocation getAnimationResource(WeaponSkinItem item) {
        return ResourceLocation.fromNamespaceAndPath(ArlightWeaponSkins.MOD_ID,
                "animations/" + item.visualId() + ".animation.json");
    }
}
