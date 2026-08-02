package com.arlight.weaponskins;

import java.util.EnumMap;
import java.util.Map;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import java.util.ArrayList;
import java.util.List;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public final class WeaponSkinItems {
    public static final String CURIOS_NAMESPACE = "arlightcosmeticscurios";
    private static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(Registries.ITEM, CURIOS_NAMESPACE);
    private static final Map<WeaponSkinTheme, Map<ToolType, DeferredHolder<Item, WeaponSkinItem>>> SKINS =
            new EnumMap<>(WeaponSkinTheme.class);

    static {
        for (WeaponSkinTheme theme : WeaponSkinTheme.values()) {
            Map<ToolType, DeferredHolder<Item, WeaponSkinItem>> byType = new EnumMap<>(ToolType.class);
            for (ToolType type : ToolType.values()) {
                String id = theme.assetId() + "_weapon_" + type.assetId();
                byType.put(type, ITEMS.register(id,
                        () -> new WeaponSkinItem(theme, type, new Item.Properties().stacksTo(1))));
            }
            SKINS.put(theme, byType);
        }
    }

    private WeaponSkinItems() { }

    public static void register(IEventBus bus) { ITEMS.register(bus); }


    public static Item[] registeredItems() {
        List<Item> items = new ArrayList<>();
        for (Map<ToolType, DeferredHolder<Item, WeaponSkinItem>> byType : SKINS.values()) {
            for (DeferredHolder<Item, WeaponSkinItem> holder : byType.values()) {
                items.add(holder.get());
            }
        }
        return items.toArray(Item[]::new);
    }

    public static ItemStack stack(WeaponSkinTheme theme, ToolType type) {
        Map<ToolType, DeferredHolder<Item, WeaponSkinItem>> byType = SKINS.get(theme);
        if (byType == null || byType.get(type) == null) return ItemStack.EMPTY;
        return byType.get(type).get().getDefaultInstance();
    }
}
