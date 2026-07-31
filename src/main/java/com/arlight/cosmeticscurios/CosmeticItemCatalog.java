package com.arlight.cosmeticscurios;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public final class CosmeticItemCatalog {
    public record Definition(
            String itemId,
            String slotId,
            String modelId,
            String claimId,
            String category,
            String setId,
            boolean companion,
            float scale,
            float offsetX,
            float offsetY,
            float offsetZ,
            float slimArmScale,
            String armorPolicy,
            int renderDistance,
            int fullAnimationDistance
    ) { }
    private static final Map<String, Definition> BY_ITEM = new LinkedHashMap<>();
    private static final Map<String, List<String>> CLAIMS = new LinkedHashMap<>();

    static {
        register("pony_horn_crown", "arlight_head", "pony_horn_crown", "pony_horn_crown");
        register("pony_pastel_chest", "arlight_chest", "pony_axolotl_charm", "pony_pastel_chest");
        register("pony_pastel_legs", "arlight_aura", "pony_orbit", "pony_pastel_legs");
        register("pony_pastel_feet", "arlight_feet", "pony_axolotl_slippers", "pony_pastel_feet");
        register("pony_whale_tail", "arlight_tail", "pony_whale_tail", "pony_whale_tail");
        register("pony_axolotl_pal", "arlight_shoulder", "pony_axolotl_pal", "pony_axolotl_pal");
        register("pony_whale_stars", "arlight_aura", "pony_whale_stars", "pony_whale_stars");
        register("pony_pastel_steps", "arlight_trail", "", "pony_pastel_steps");

        register("somita_bat_clip", "arlight_head", "somita_bat_clip", "somita_bat_clip");
        register("somita_night_chest", "arlight_tail", "somita_vampire_tail", "somita_night_chest");
        register("somita_night_legs", "arlight_aura", "somita_numbers_orbit", "somita_night_legs");
        register("somita_night_feet", "arlight_feet", "somita_bat_slippers", "somita_night_feet");
        register("somita_bat_wings", "arlight_back", "somita_bat_wings", "somita_bat_wings");
        register("somita_bat_pal", "arlight_shoulder", "somita_bat_pal", "somita_bat_pal");
        register("somita_crimson_hearts", "arlight_aura", "somita_crimson_hearts", "somita_crimson_hearts");
        register("somita_night_steps", "arlight_trail", "", "somita_night_steps");

        register("bingo_corrupted_crown", "arlight_head", "bingo_corrupted_crown", "bingo_corrupted_crown");
        register("bingo_corrupted_chest", "arlight_chest", "bingo_corrupted_chest", "bingo_corrupted_outfit");
        register("bingo_corrupted_legs", "arlight_legs", "bingo_corrupted_legs", "bingo_corrupted_outfit");
        register("bingo_corrupted_feet", "arlight_feet", "bingo_corrupted_feet", "bingo_corrupted_outfit");
        register("bingo_void_wings", "arlight_back", "bingo_void_wings", "bingo_void_wings");
        register("bingo_amethyst_eye", "arlight_shoulder", "bingo_amethyst_eye", "bingo_amethyst_eye");
        register("bingo_emerald_orbit", "arlight_aura", "bingo_emerald_orbit", "bingo_emerald_orbit");
        register("bingo_gold_orbit", "arlight_aura", "bingo_gold_orbit", "bingo_gold_orbit");
        register("bingo_corruption_steps", "arlight_trail", "", "bingo_corruption_steps");
        register("bingo_amethyst_orbit", "arlight_aura", "bingo_amethyst_orbit", "bingo_amethyst_orbit");

        register("swansito_dino_tail", "arlight_tail", "swansito_dino_tail", "swansito_dino_tail");
        register("swansito_dino_hands", "arlight_hands", "swansito_dino_hands", "swansito_dino_hands");
        register("swansito_mini_trex", "arlight_companion", "swansito_mini_trex", "swansito_mini_trex");
        register("swansito_meat_chase_aura", "arlight_aura", "swansito_meat_chase_aura", "swansito_meat_chase_aura");
        register("swansito_dino_steps", "arlight_trail", "", "swansito_dino_steps");

        register("mobchibi_creeper", "arlight_companion", "mobchibi_creeper", "mobchibi_creeper");
        register("mobchibi_enderman", "arlight_companion", "mobchibi_enderman", "mobchibi_enderman");
        register("mobchibi_bee", "arlight_companion", "mobchibi_bee", "mobchibi_bee");
        register("mobchibi_axolotl", "arlight_companion", "mobchibi_axolotl", "mobchibi_axolotl");
        register("mobchibi_slime", "arlight_companion", "mobchibi_slime", "mobchibi_slime");
        register("mobchibi_warden", "arlight_companion", "mobchibi_warden", "mobchibi_warden");

        register("dino_pajama_hat", "arlight_head", "dino_pajama_hat", "dino_pajama_hat");
        register("dino_pajama_chest", "arlight_chest", "dino_pajama_chest", "dino_pajama_chest");
        register("dino_pajama_legs", "arlight_legs", "dino_pajama_legs", "dino_pajama_legs");
        register("dino_pajama_feet", "arlight_feet", "dino_pajama_feet", "dino_pajama_feet");
        register("axolotl_pajama_hat", "arlight_head", "axolotl_pajama_hat", "axolotl_pajama_hat");
        register("axolotl_pajama_chest", "arlight_chest", "axolotl_pajama_chest", "axolotl_pajama_chest");
        register("axolotl_pajama_legs", "arlight_legs", "axolotl_pajama_legs", "axolotl_pajama_legs");
        register("axolotl_pajama_feet", "arlight_feet", "axolotl_pajama_feet", "axolotl_pajama_feet");
        register("pastel_heart_backpack", "arlight_back", "pastel_heart_backpack", "pastel_heart_backpack");
        register("bunny_ear_hood", "arlight_head", "bunny_ear_hood", "bunny_ear_hood");
        register("moon_star_cardigan", "arlight_chest", "moon_star_cardigan", "moon_star_cardigan");
        register("cloud_skirt", "arlight_legs", "cloud_skirt", "cloud_skirt");
        register("cloud_slippers", "arlight_feet", "cloud_slippers", "cloud_slippers");
        register("floating_pastel_ribbon", "arlight_aura", "floating_pastel_ribbon", "floating_pastel_ribbon");
        register("dino_footprints", "arlight_trail", "", "dino_footprints");
        register("axolotl_bubbles", "arlight_trail", "", "axolotl_bubbles");
        register("pastel_star_steps", "arlight_trail", "", "pastel_star_steps");
        register("flower_petals_steps", "arlight_trail", "", "flower_petals_steps");
        register("mobchibi_chicken", "arlight_companion", "mobchibi_chicken", "mobchibi_chicken");
        register("mobchibi_fox", "arlight_companion", "mobchibi_fox", "mobchibi_fox");
        register("mobchibi_frog", "arlight_companion", "mobchibi_frog", "mobchibi_frog");
        register("mobchibi_allay", "arlight_companion", "mobchibi_allay", "mobchibi_allay");
        register("mobchibi_zombie", "arlight_companion", "mobchibi_zombie", "mobchibi_zombie");
        register("mobchibi_skeleton", "arlight_companion", "mobchibi_skeleton", "mobchibi_skeleton");

        // Compatibilidad de migración: los ids antiguos pueden desbloquearse,
        // pero el ropero equipa cada pieza mediante su id independiente.
        CLAIMS.put("pony_pastel_outfit", List.of("pony_pastel_chest", "pony_pastel_legs", "pony_pastel_feet"));
        CLAIMS.put("somita_night_outfit", List.of("somita_night_chest", "somita_night_legs", "somita_night_feet"));
        CLAIMS.put("bingo_corrupted_outfit", List.of("bingo_corrupted_chest", "bingo_corrupted_legs", "bingo_corrupted_feet"));
    }

    private static void register(String itemId, String slotId, String modelId, String claimId) {
        String category = category(itemId, slotId);
        String setId = setId(itemId);
        boolean companion = "arlight_companion".equals(slotId);
        float scale = companion ? companionScale(itemId) : 1.0F;
        float offsetY = companion ? companionYOffset(itemId) : 0.0F;
        float offsetZ = companion ? companionZOffset(itemId) : 0.0F;
        float slimArmScale = ("arlight_chest".equals(slotId) || "arlight_hands".equals(slotId)) ? 0.78F : 1.0F;
        String armorPolicy = switch (slotId) {
            case "arlight_head", "arlight_chest", "arlight_legs", "arlight_feet" -> "hide_matching";
            default -> "always";
        };
        int renderDistance = companion ? 40 : 48;
        int fullAnimationDistance = companion ? 18 : 28;
        BY_ITEM.put(itemId, new Definition(itemId, slotId, modelId, claimId,
                category, setId, companion, scale, 0.0F, offsetY, offsetZ,
                slimArmScale, armorPolicy, renderDistance, fullAnimationDistance));
        CLAIMS.putIfAbsent(claimId, List.of(itemId));
    }

    private static String category(String itemId, String slotId) {
        if ("arlight_companion".equals(slotId)) return "pets";
        if (itemId.contains("pajama") || itemId.equals("bunny_ear_hood")
                || itemId.equals("moon_star_cardigan") || itemId.equals("cloud_skirt")
                || itemId.equals("cloud_slippers")) return "pajamas";
        if ("arlight_trail".equals(slotId)) return "trails";
        if ("arlight_aura".equals(slotId)) return "auras";
        if ("arlight_back".equals(slotId) || "arlight_tail".equals(slotId)) return "back";
        if ("arlight_head".equals(slotId)) return "head";
        return "accessories";
    }

    private static String setId(String itemId) {
        if (itemId.startsWith("dino_pajama_")) return "dino_pajama";
        if (itemId.startsWith("axolotl_pajama_")) return "axolotl_pajama";
        if (itemId.equals("bunny_ear_hood") || itemId.equals("moon_star_cardigan")
                || itemId.equals("cloud_skirt") || itemId.equals("cloud_slippers")) return "cloud_bunny_pajama";
        if (itemId.startsWith("pony_pastel_")) return "pony_pastel";
        if (itemId.startsWith("somita_night_")) return "somita_night";
        if (itemId.startsWith("bingo_corrupted_")) return "bingo_corrupted";
        return "";
    }

    private static float companionScale(String itemId) {
        return switch (itemId) {
            case "swansito_mini_trex" -> 0.96F;
            case "mobchibi_creeper" -> 1.10F;
            case "mobchibi_zombie", "mobchibi_skeleton" -> 1.10F;
            case "mobchibi_chicken" -> 1.14F;
            case "mobchibi_warden" -> 0.98F;
            case "mobchibi_enderman" -> 1.02F;
            case "mobchibi_allay", "mobchibi_bee" -> 1.10F;
            case "mobchibi_axolotl", "mobchibi_fox" -> 1.08F;
            case "mobchibi_slime", "mobchibi_frog" -> 1.10F;
            default -> 1.02F;
        };
    }

    private static float companionYOffset(String itemId) {
        return switch (itemId) {
            case "mobchibi_allay", "mobchibi_bee" -> -0.10F;
            case "mobchibi_slime", "mobchibi_frog" -> 0.03F;
            default -> 0.0F;
        };
    }

    private static float companionZOffset(String itemId) {
        return itemId.equals("swansito_mini_trex") ? 0.08F : 0.0F;
    }

    public static Definition byItem(Item item) {
        ResourceLocation id = BuiltInRegistries.ITEM.getKey(item);
        return id == null ? null : BY_ITEM.get(id.getPath());
    }

    public static List<String> itemsForClaim(String claimId) {
        return CLAIMS.getOrDefault(claimId, List.of());
    }

    public static Collection<Definition> all() { return List.copyOf(BY_ITEM.values()); }
    private CosmeticItemCatalog() { }

    public static Definition byId(String itemId) { return BY_ITEM.get(itemId); }
}
