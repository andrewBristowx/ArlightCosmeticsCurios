package com.arlight.weaponskins;

import com.arlight.chatclient.ClientCosmeticsState;
import com.arlight.chatclient.CosmeticSlot;
import com.arlight.chatclient.WardrobeState;
import java.util.UUID;
import net.minecraft.client.Minecraft;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.CuriosApi;

public final class WeaponSkinSelection {
    private WeaponSkinSelection() { }

    public static WeaponSkinTheme selectedForCurrentRender(ToolType type) {
        Minecraft minecraft = Minecraft.getInstance();
        LivingEntity owner = minecraft == null ? null : minecraft.player;
        return selectedFor(owner, type);
    }

    /** Curios es la fuente principal. ChatClient queda como migración/preview. */
    public static WeaponSkinTheme selectedFor(LivingEntity owner, ToolType type) {
        if (owner == null || type == null) return null;
        try {
            ItemStack equipped = CuriosApi.getCuriosInventory(owner)
                    .flatMap(handler -> handler.getStacksHandler(type.curiosSlot()))
                    .map(handler -> handler.getStacks().getStackInSlot(0))
                    .orElse(ItemStack.EMPTY);
            if (!equipped.isEmpty()) {
                var registryId = BuiltInRegistries.ITEM.getKey(equipped.getItem());
                String itemId = registryId == null ? equipped.getItem().toString() : registryId.getPath();
                WeaponSkinTheme theme = WeaponSkinTheme.fromCosmetic(itemId);
                if (theme != null) {
                    WeaponSkinDiagnostics.curiosSelection(theme, type, itemId);
                    return theme;
                }
                WeaponSkinDiagnostics.invalidCuriosItem(type, itemId);
            }
        } catch (Throwable error) {
            WeaponSkinDiagnostics.curiosFailure(owner.getUUID(), type, error);
        }
        return selectedFor(owner.getUUID(), type);
    }

    public static WeaponSkinTheme selectedFor(UUID owner, ToolType type) {
        if (owner == null || type == null) return null;
        try {
            CosmeticSlot slot = CosmeticSlot.valueOf(type.cosmeticSlot());
            String selectedId = ClientCosmeticsState.cosmetic(owner, slot);
            WeaponSkinTheme theme = WeaponSkinTheme.fromCosmetic(selectedId);
            if (theme != null) return theme;

            String wardrobeId = null;
            Minecraft minecraft = Minecraft.getInstance();
            if (minecraft != null && minecraft.player != null
                    && owner.equals(minecraft.player.getUUID())) {
                wardrobeId = WardrobeState.equipped().get(type.cosmeticSlot());
                theme = WeaponSkinTheme.fromCosmetic(wardrobeId);
                if (theme != null) {
                    WeaponSkinDiagnostics.wardrobeFallback(theme, type);
                    return theme;
                }
            }

            String legacyId = ClientCosmeticsState.cosmetic(owner, CosmeticSlot.WEAPON_SKIN);
            theme = WeaponSkinTheme.fromCosmetic(legacyId);
            if (theme == null) {
                WeaponSkinDiagnostics.missingSelection(owner, type, selectedId, wardrobeId, legacyId);
            }
            return theme;
        } catch (Throwable error) {
            WeaponSkinDiagnostics.bridgeFailure(owner, type, error);
            return null;
        }
    }
}
