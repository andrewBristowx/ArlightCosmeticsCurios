package com.arlight.weaponskins.client;

import com.arlight.weaponskins.WeaponSkinItem;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public final class WeaponSkinItemRenderer extends GeoItemRenderer<WeaponSkinItem> {
    public WeaponSkinItemRenderer() { super(new WeaponSkinItemModel()); }
}
