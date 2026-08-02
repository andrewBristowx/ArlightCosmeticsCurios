package com.arlight.weaponskins;

import java.util.Locale;

public enum WeaponSkinTheme {
    SOMITA("somita", "somita_weapon_set"),
    PONY("pony", "pony_weapon_set");

    private final String assetId;
    private final String cosmeticId;

    WeaponSkinTheme(String assetId, String cosmeticId) {
        this.assetId = assetId;
        this.cosmeticId = cosmeticId;
    }

    public String assetId() { return assetId; }
    public String cosmeticId() { return cosmeticId; }

    public static WeaponSkinTheme fromCosmetic(String id) {
        if (id == null) return null;
        String normalized = id.trim().toLowerCase(Locale.ROOT);
        for (WeaponSkinTheme value : values()) {
            if (value.cosmeticId.equals(normalized)
                    || normalized.startsWith(value.assetId + "_weapon_")) return value;
        }
        return null;
    }
}
