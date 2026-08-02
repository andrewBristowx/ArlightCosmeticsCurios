package com.arlight.weaponskins;

public enum ToolType {
    SWORD("sword", "WEAPON_SWORD", "arlight_weapon_sword"),
    PICKAXE("pickaxe", "WEAPON_PICKAXE", "arlight_weapon_pickaxe"),
    AXE("axe", "WEAPON_AXE", "arlight_weapon_axe"),
    SHOVEL("shovel", "WEAPON_SHOVEL", "arlight_weapon_shovel"),
    HOE("hoe", "WEAPON_HOE", "arlight_weapon_hoe"),
    BOW("bow", "WEAPON_BOW", "arlight_weapon_bow");

    private final String assetId;
    private final String cosmeticSlot;
    private final String curiosSlot;

    ToolType(String assetId, String cosmeticSlot, String curiosSlot) {
        this.assetId = assetId;
        this.cosmeticSlot = cosmeticSlot;
        this.curiosSlot = curiosSlot;
    }
    public String assetId() { return assetId; }
    public String cosmeticSlot() { return cosmeticSlot; }
    public String curiosSlot() { return curiosSlot; }
}
