package com.arlight.weaponskins;

import com.arlight.weaponskins.client.WeaponSkinItemRenderer;
import com.arlight.cosmeticscurios.OwnerBoundCosmeticItem;
import java.util.function.Consumer;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.animatable.SingletonGeoAnimatable;
import software.bernie.geckolib.animatable.client.GeoRenderProvider;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.AnimatableManager;
import software.bernie.geckolib.animation.AnimationController;
import software.bernie.geckolib.animation.RawAnimation;
import software.bernie.geckolib.util.GeckoLibUtil;

public final class WeaponSkinItem extends OwnerBoundCosmeticItem implements GeoItem {
    private final WeaponSkinTheme theme;
    private final ToolType toolType;
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    public WeaponSkinItem(WeaponSkinTheme theme, ToolType toolType, Properties properties) {
        super(properties, toolType.curiosSlot(), theme.assetId() + "_weapon_" + toolType.assetId());
        this.theme = theme;
        this.toolType = toolType;
        SingletonGeoAnimatable.registerSyncedAnimatable(this);
    }

    public WeaponSkinTheme theme() { return theme; }
    public ToolType toolType() { return toolType; }
    public String visualId() { return theme.assetId() + "_" + toolType.assetId(); }

    @Override
    public void createGeoRenderer(Consumer<GeoRenderProvider> consumer) {
        consumer.accept(new GeoRenderProvider() {
            private WeaponSkinItemRenderer renderer;

            @Override
            public net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer getGeoItemRenderer() {
                if (renderer == null) renderer = new WeaponSkinItemRenderer();
                return renderer;
            }
        });
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "idle", 2,
                state -> state.setAndContinue(RawAnimation.begin()
                        .thenLoop("animation.arlightweapons." + visualId() + ".idle"))));
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() { return cache; }
}
