package com.arlight.cosmeticscurios;

import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.CustomData;

import java.util.UUID;

public final class CosmeticOwnership {
    public static final String ROOT = "ArlightCosmetic";
    public static final String OWNER_UUID = "OwnerUUID";
    public static final String OWNER_NAME = "OwnerName";
    public static final String CLAIM_ID = "ClaimId";

    public static void bind(ItemStack stack, UUID owner, String ownerName, String claimId) {
        CompoundTag root = stack.getOrDefault(DataComponents.CUSTOM_DATA, CustomData.EMPTY).copyTag();
        CompoundTag data = new CompoundTag();
        data.putString(OWNER_UUID, owner.toString());
        data.putString(OWNER_NAME, ownerName == null ? "" : ownerName);
        data.putString(CLAIM_ID, claimId == null ? "" : claimId);
        root.put(ROOT, data);

        // Arclight/Bukkit PersistentDataContainer bridge.
        CompoundTag bukkit = root.contains("PublicBukkitValues", 10)
                ? root.getCompound("PublicBukkitValues") : new CompoundTag();
        bukkit.putString("arlightcosmetics:owner_uuid", owner.toString());
        bukkit.putString("arlightcosmetics:owner_name", ownerName == null ? "" : ownerName);
        bukkit.putString("arlightcosmetics:claim_id", claimId == null ? "" : claimId);
        root.put("PublicBukkitValues", bukkit);
        stack.set(DataComponents.CUSTOM_DATA, CustomData.of(root));
    }

    public static UUID owner(ItemStack stack) {
        CompoundTag data = data(stack);
        if (data == null || !data.contains(OWNER_UUID, 8)) return null;
        try { return UUID.fromString(data.getString(OWNER_UUID)); }
        catch (IllegalArgumentException ignored) { return null; }
    }

    public static String ownerName(ItemStack stack) {
        CompoundTag data = data(stack);
        return data == null ? "" : data.getString(OWNER_NAME);
    }

    public static boolean isBoundCosmetic(ItemStack stack) {
        return stack != null && !stack.isEmpty() && data(stack) != null;
    }

    public static boolean canUse(ItemStack stack, LivingEntity entity) {
        UUID owner = owner(stack);
        return owner != null && entity != null && owner.equals(entity.getUUID());
    }

    private static CompoundTag data(ItemStack stack) {
        if (stack == null || stack.isEmpty()) return null;
        CustomData custom = stack.get(DataComponents.CUSTOM_DATA);
        if (custom == null) return null;
        CompoundTag root = custom.copyTag();
        return root.contains(ROOT, 10) ? root.getCompound(ROOT) : null;
    }

    private CosmeticOwnership() { }
}
