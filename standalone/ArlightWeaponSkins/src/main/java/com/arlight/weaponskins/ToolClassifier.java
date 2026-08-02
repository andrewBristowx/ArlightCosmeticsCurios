package com.arlight.weaponskins;

import java.util.Locale;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.ItemStack;

public final class ToolClassifier {
    private ToolClassifier() { }

    public static ToolType classify(ItemStack stack) {
        if (stack == null || stack.isEmpty() || stack.getItem() instanceof WeaponSkinItem) return null;
        String id = stack.getItem().toString().toLowerCase(Locale.ROOT);
        if (ToolCompatibilityConfig.excluded(id)) return null;
        ToolType explicit = ToolCompatibilityConfig.explicitType(id);
        if (explicit != null) return explicit;

        if (stack.getItem() instanceof BowItem || contains(id, "bow", "longbow", "shortbow")) return ToolType.BOW;
        if (stack.is(ItemTags.SWORDS) || contains(id, "sword", "katana", "rapier", "saber", "greatblade", "claymore")) return ToolType.SWORD;
        if (stack.is(ItemTags.PICKAXES) || contains(id, "pickaxe", "pick")) return ToolType.PICKAXE;
        if (stack.is(ItemTags.AXES) || contains(id, "battleaxe", "_axe", ":axe")) return ToolType.AXE;
        if (stack.is(ItemTags.SHOVELS) || contains(id, "shovel", "spade")) return ToolType.SHOVEL;
        if (stack.is(ItemTags.HOES) || contains(id, "_hoe", ":hoe", "scythe")) return ToolType.HOE;
        return null;
    }

    private static boolean contains(String id, String... fragments) {
        for (String fragment : fragments) if (id.contains(fragment)) return true;
        return false;
    }
}
