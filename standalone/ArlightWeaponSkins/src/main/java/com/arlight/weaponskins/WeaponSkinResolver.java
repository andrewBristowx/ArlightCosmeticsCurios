package com.arlight.weaponskins;

import java.util.UUID;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

public final class WeaponSkinResolver {
    private WeaponSkinResolver() { }

    public static ItemStack replacement(ItemStack original) {
        return replacement(original, (LivingEntity)null);
    }

    public static ItemStack replacement(ItemStack original, LivingEntity owner) {
        ToolType type = ToolClassifier.classify(original);
        if (type == null) return original;
        WeaponSkinDiagnostics.renderHook(type);
        WeaponSkinTheme theme = owner == null
                ? WeaponSkinSelection.selectedForCurrentRender(type)
                : WeaponSkinSelection.selectedFor(owner, type);
        return replacement(original, type, theme);
    }

    public static ItemStack replacement(ItemStack original, UUID owner) {
        ToolType type = ToolClassifier.classify(original);
        if (type == null) return original;
        WeaponSkinDiagnostics.renderHook(type);
        WeaponSkinTheme theme = WeaponSkinSelection.selectedFor(owner, type);
        return replacement(original, type, theme);
    }

    private static ItemStack replacement(ItemStack original, ToolType type, WeaponSkinTheme theme) {
        if (theme == null) return original;
        ItemStack replacement = WeaponSkinItems.stack(theme, type);
        if (replacement.isEmpty()) {
            WeaponSkinDiagnostics.emptyReplacement(theme, type);
            return original;
        }
        WeaponSkinDiagnostics.replaced(theme, type, replacement);
        return replacement;
    }
}
