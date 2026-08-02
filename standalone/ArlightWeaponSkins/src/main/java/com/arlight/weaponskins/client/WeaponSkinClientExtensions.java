package com.arlight.weaponskins.client;

import com.arlight.weaponskins.ArlightWeaponSkins;
import com.arlight.weaponskins.WeaponSkinDiagnostics;
import com.arlight.weaponskins.WeaponSkinItems;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent;

/**
 * Explicit NeoForge renderer registration.
 *
 * GeckoLib can discover GeoItem renderers dynamically, but the modpack contains several
 * render replacements. Registering a single client extension for all visual skin items makes
 * the BEWLR choice deterministic and prevents a vanilla/baked renderer from winning first.
 */
@EventBusSubscriber(modid = ArlightWeaponSkins.MOD_ID,
        value = Dist.CLIENT, bus = EventBusSubscriber.Bus.MOD)
public final class WeaponSkinClientExtensions {
    private static final IClientItemExtensions EXTENSIONS = new IClientItemExtensions() {
        private WeaponSkinItemRenderer renderer;

        @Override
        public BlockEntityWithoutLevelRenderer getCustomRenderer() {
            if (renderer == null) renderer = new WeaponSkinItemRenderer();
            return renderer;
        }
    };

    private WeaponSkinClientExtensions() { }

    @SubscribeEvent
    public static void register(RegisterClientExtensionsEvent event) {
        var items = WeaponSkinItems.registeredItems();
        event.registerItem(EXTENSIONS, items);
        WeaponSkinDiagnostics.clientExtensionsRegistered(items.length);
    }
}
