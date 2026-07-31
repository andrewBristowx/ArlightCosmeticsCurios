package com.arlight.cosmeticscurios.client;

import java.util.Map;
import java.util.LinkedHashMap;

/** Registro de modelos profesionales exportados desde las fuentes Blockbench. */
public final class ProfessionalCosmeticModels {
    private static final Map<String, ProfessionalCosmeticModel> MODELS = createModels();

    private ProfessionalCosmeticModels() { }

    public static ProfessionalCosmeticModel get(String id) {
        return id == null ? null : MODELS.get(id);
    }

    public static boolean contains(String id) {
        return id != null && MODELS.containsKey(id);
    }

    public static int size() {
        return MODELS.size();
    }

    /** Ropas divididas como una armadura visual: torso, brazos y piernas siguen el PlayerModel. */
    public static boolean isArmorFitOutfit(String id) {
        return "pony_pastel_outfit".equals(id)
                || "somita_night_outfit".equals(id)
                || "bingo_corrupted_outfit".equals(id);
    }

    private static Map<String, ProfessionalCosmeticModel> createModels() {
        Map<String, ProfessionalCosmeticModel> result = new LinkedHashMap<>();
        result.put("pony_horn_crown", ponyHornCrown());
        result.put("pony_pastel_outfit", ponyPastelOutfit());
        result.put("pony_pastel_chest", ponyPastelChest());
        result.put("pony_pastel_legs", ponyPastelLegs());
        result.put("pony_pastel_feet", ponyPastelFeet());
        result.put("pony_axolotl_charm", ponyAxolotlCharm());
        result.put("pony_orbit", ponyOrbit());
        result.put("pony_axolotl_slippers", ponyAxolotlSlippers());
        result.put("pony_whale_tail", ponyWhaleTail());
        result.put("pony_axolotl_pal", ponyAxolotlPal());
        result.put("pony_whale_stars", ponyWhaleStars());
        result.put("somita_bat_clip", somitaBatClip());
        result.put("somita_night_outfit", somitaNightOutfit());
        result.put("somita_night_chest", somitaNightChest());
        result.put("somita_night_legs", somitaNightLegs());
        result.put("somita_night_feet", somitaNightFeet());
        result.put("somita_vampire_tail", somitaVampireTail());
        result.put("somita_numbers_orbit", somitaNumbersOrbit());
        result.put("somita_bat_slippers", somitaBatSlippers());
        result.put("somita_bat_wings", somitaBatWings());
        result.put("somita_bat_pal", somitaBatPal());
        result.put("somita_crimson_hearts", somitaCrimsonHearts());
        result.put("bingo_corrupted_crown", bingoCorruptedCrown());
        result.put("bingo_corrupted_outfit", bingoCorruptedOutfit());
        result.put("bingo_corrupted_chest", bingoCorruptedChest());
        result.put("bingo_corrupted_legs", bingoCorruptedLegs());
        result.put("bingo_corrupted_feet", bingoCorruptedFeet());
        result.put("bingo_void_wings", bingoVoidWings());
        result.put("bingo_amethyst_eye", bingoAmethystEye());
        result.put("bingo_emerald_orbit", bingoEmeraldOrbit());
        result.put("bingo_gold_orbit", bingoGoldOrbit());
        result.put("bingo_amethyst_orbit", bingoAmethystOrbit());
        result.put("swansito_dino_tail", swansitoDinoTail());
        result.put("swansito_dino_hands", swansitoDinoHands());
        result.put("swansito_mini_trex", swansitoMiniTrex());
        result.put("swansito_meat_chase_aura", swansitoMeatChaseAura());
        result.put("mobchibi_creeper", mobChibiCreeper());
        result.put("mobchibi_enderman", mobChibiEnderman());
        result.put("mobchibi_bee", mobChibiBee());
        result.put("mobchibi_axolotl", mobChibiAxolotl());
        result.put("mobchibi_slime", mobChibiSlime());
        result.put("mobchibi_warden", mobChibiWarden());
        result.put("dino_pajama_hat", dinoPajamaHat());
        result.put("dino_pajama_chest", pajamaChest("dino_pajama_chest", true));
        result.put("dino_pajama_legs", pajamaLegs("dino_pajama_legs", true));
        result.put("dino_pajama_feet", pajamaFeet("dino_pajama_feet", true));
        result.put("axolotl_pajama_hat", axolotlPajamaHat());
        result.put("axolotl_pajama_chest", pajamaChest("axolotl_pajama_chest", false));
        result.put("axolotl_pajama_legs", pajamaLegs("axolotl_pajama_legs", false));
        result.put("axolotl_pajama_feet", pajamaFeet("axolotl_pajama_feet", false));
        result.put("pastel_heart_backpack", pastelHeartBackpack());
        result.put("bunny_ear_hood", bunnyEarHood());
        result.put("moon_star_cardigan", moonStarCardigan());
        result.put("cloud_skirt", cloudSkirt());
        result.put("cloud_slippers", cloudSlippers());
        result.put("floating_pastel_ribbon", floatingPastelRibbon());
        result.put("mobchibi_chicken", mobChibiChicken());
        result.put("mobchibi_fox", mobChibiFox());
        result.put("mobchibi_frog", mobChibiFrog());
        result.put("mobchibi_allay", mobChibiAllay());
        result.put("mobchibi_zombie", mobChibiZombie());
        result.put("mobchibi_skeleton", mobChibiSkeleton());
        return Map.copyOf(result);
    }

    private static ProfessionalCosmeticModel ponyHornCrown() {
        ProfessionalCosmeticModel.Builder builder = new ProfessionalCosmeticModel.Builder("pony_horn_crown", 128, 128);
        builder.bone(
                CosmeticAnchor.HEAD, "band", null,
                0.0F, 0.0F, 0.0F,
                0.0F, 0.0F, 0.0F,
                ProfessionalCosmeticModel.Box.of(
                        0, 0, -4.25F, -8.35F, -4.22F,
                        8.5F, 0.55F, 0.42F, 0.02F),
                ProfessionalCosmeticModel.Box.of(
                        32, 0, -4.25F, -8.35F, 3.8F,
                        8.5F, 0.55F, 0.42F, 0.02F),
                ProfessionalCosmeticModel.Box.of(
                        0, 0, -4.25F, -8.35F, -3.8F,
                        0.42F, 0.55F, 7.6F, 0.02F),
                ProfessionalCosmeticModel.Box.of(
                        32, 0, 3.83F, -8.35F, -3.8F,
                        0.42F, 0.55F, 7.6F, 0.02F),
                ProfessionalCosmeticModel.Box.of(
                        64, 0, -0.75F, -8.75F, -4.48F,
                        1.5F, 0.8F, 0.3F, 0.02F),
                ProfessionalCosmeticModel.Box.of(
                        0, 64, -0.28F, -8.58F, -4.73F,
                        0.56F, 0.48F, 0.2F, 0.01F)
        );
        builder.bone(
                CosmeticAnchor.HEAD, "ear_left", "band",
                3.85F, -7.55F, 0.15F,
                0.0F, 0.0F, -10.0F,
                ProfessionalCosmeticModel.Box.of(
                        64, 0, 0.0F, -2.45F, -0.95F,
                        1.05F, 2.55F, 1.9F, 0.04F),
                ProfessionalCosmeticModel.Box.of(
                        96, 0, 0.13F, -2.05F, -0.58F,
                        0.82F, 1.65F, 1.16F, 0.02F)
        );
        builder.bone(
                CosmeticAnchor.HEAD, "ear_right", "band",
                -3.85F, -7.55F, 0.15F,
                0.0F, 0.0F, 10.0F,
                ProfessionalCosmeticModel.Box.of(
                        64, 0, -1.05F, -2.45F, -0.95F,
                        1.05F, 2.55F, 1.9F, 0.04F),
                ProfessionalCosmeticModel.Box.of(
                        96, 0, -0.95F, -2.05F, -0.58F,
                        0.82F, 1.65F, 1.16F, 0.02F)
        );
        builder.bone(
                CosmeticAnchor.HEAD, "horn_left_base", "band",
                2.25F, -8.15F, 0.45F,
                -12.0F, -4.0F, -11.0F,
                ProfessionalCosmeticModel.Box.of(
                        32, 32, -0.66F, -3.1F, -0.66F,
                        1.32F, 3.2F, 1.32F, 0.04F)
        );
        builder.bone(
                CosmeticAnchor.HEAD, "horn_left_mid", "horn_left_base",
                0.0F, -3.0F, 0.0F,
                8.0F, 3.0F, 12.0F,
                ProfessionalCosmeticModel.Box.of(
                        64, 32, -0.52F, -2.45F, -0.52F,
                        1.04F, 2.55F, 1.04F, 0.025F)
        );
        builder.bone(
                CosmeticAnchor.HEAD, "horn_left_tip", "horn_left_mid",
                0.0F, -2.35F, 0.0F,
                14.0F, 4.0F, 12.0F,
                ProfessionalCosmeticModel.Box.of(
                        64, 32, -0.34F, -1.55F, -0.34F,
                        0.68F, 1.65F, 0.68F, 0.01F)
        );
        builder.bone(
                CosmeticAnchor.HEAD, "horn_right_base", "band",
                -2.25F, -8.15F, 0.45F,
                -12.0F, 4.0F, 11.0F,
                ProfessionalCosmeticModel.Box.of(
                        32, 32, -0.66F, -3.1F, -0.66F,
                        1.32F, 3.2F, 1.32F, 0.04F)
        );
        builder.bone(
                CosmeticAnchor.HEAD, "horn_right_mid", "horn_right_base",
                0.0F, -3.0F, 0.0F,
                8.0F, -3.0F, -12.0F,
                ProfessionalCosmeticModel.Box.of(
                        64, 32, -0.52F, -2.45F, -0.52F,
                        1.04F, 2.55F, 1.04F, 0.025F)
        );
        builder.bone(
                CosmeticAnchor.HEAD, "horn_right_tip", "horn_right_mid",
                0.0F, -2.35F, 0.0F,
                14.0F, -4.0F, -12.0F,
                ProfessionalCosmeticModel.Box.of(
                        64, 32, -0.34F, -1.55F, -0.34F,
                        0.68F, 1.65F, 0.68F, 0.01F)
        );
        builder.bone(
                CosmeticAnchor.HEAD, "ribbon_left", "band",
                3.45F, -8.0F, 3.95F,
                12.0F, 0.0F, -8.0F,
                ProfessionalCosmeticModel.Box.of(
                        96, 0, -1.05F, -0.1F, -0.16F,
                        1.15F, 1.75F, 0.32F, 0.015F)
        );
        builder.bone(
                CosmeticAnchor.HEAD, "ribbon_right", "band",
                -3.45F, -8.0F, 3.95F,
                12.0F, 0.0F, 8.0F,
                ProfessionalCosmeticModel.Box.of(
                        96, 0, -0.1F, -0.1F, -0.16F,
                        1.15F, 1.75F, 0.32F, 0.015F)
        );
        return builder.bake(true);
    }

    private static ProfessionalCosmeticModel ponyPastelOutfit() {
        ProfessionalCosmeticModel.Builder builder = new ProfessionalCosmeticModel.Builder("pony_pastel_outfit", 128, 128);
        builder.bone(
                CosmeticAnchor.BODY, "jacket_body", null,
                0.0F, 0.0F, 0.0F,
                0.0F, 0.0F, 0.0F,
                ProfessionalCosmeticModel.Box.of(
                        0, 0, -4.0F, -0.02F, -2.0F,
                        8.0F, 12.02F, 4.0F, 0.56F)
        );
        builder.bone(
                CosmeticAnchor.BODY, "pony_corset", "jacket_body",
                0.0F, 0.0F, 0.0F,
                0.0F, 0.0F, 0.0F,
                ProfessionalCosmeticModel.Box.of(
                        64, 0, -2.55F, 1.0F, -2.2F,
                        5.1F, 6.8F, 0.22F, 0.015F),
                ProfessionalCosmeticModel.Box.of(
                        96, 0, -0.18F, 1.2F, -2.26F,
                        0.36F, 6.1F, 0.16F, 0.008F)
        );
        builder.bone(
                CosmeticAnchor.BODY, "pony_belt", "jacket_body",
                0.0F, 0.0F, 0.0F,
                0.0F, 0.0F, 0.0F,
                ProfessionalCosmeticModel.Box.of(
                        64, 16, -4.08F, 8.25F, -2.08F,
                        8.16F, 1.05F, 4.16F, 0.03F)
        );
        builder.bone(
                CosmeticAnchor.BODY, "hood_left", "jacket_body",
                -1.45F, -0.05F, 2.08F,
                10.0F, 0.0F, 7.0F,
                ProfessionalCosmeticModel.Box.of(
                        96, 16, -1.55F, -0.35F, -0.12F,
                        1.7F, 1.95F, 0.4F, 0.025F)
        );
        builder.bone(
                CosmeticAnchor.BODY, "hood_right", "jacket_body",
                1.45F, -0.05F, 2.08F,
                10.0F, 0.0F, -7.0F,
                ProfessionalCosmeticModel.Box.of(
                        96, 16, -0.15F, -0.35F, -0.12F,
                        1.7F, 1.95F, 0.4F, 0.025F)
        );
        builder.bone(
                CosmeticAnchor.BODY, "skirt_front_left", "jacket_body",
                -2.05F, 9.25F, -2.06F,
                -3.0F, 0.0F, -3.0F,
                ProfessionalCosmeticModel.Box.of(
                        64, 24, -2.05F, 0.0F, -0.2F,
                        4.0F, 3.45F, 0.42F, 0.03F)
        );
        builder.bone(
                CosmeticAnchor.BODY, "skirt_front_right", "jacket_body",
                2.05F, 9.25F, -2.06F,
                -3.0F, 0.0F, 3.0F,
                ProfessionalCosmeticModel.Box.of(
                        64, 24, -1.95F, 0.0F, -0.2F,
                        4.0F, 3.45F, 0.42F, 0.03F)
        );
        builder.bone(
                CosmeticAnchor.BODY, "skirt_back_left", "jacket_body",
                -2.05F, 9.25F, 2.06F,
                3.0F, 0.0F, -3.0F,
                ProfessionalCosmeticModel.Box.of(
                        80, 24, -2.05F, 0.0F, -0.22F,
                        4.0F, 3.45F, 0.42F, 0.03F)
        );
        builder.bone(
                CosmeticAnchor.BODY, "skirt_back_right", "jacket_body",
                2.05F, 9.25F, 2.06F,
                3.0F, 0.0F, 3.0F,
                ProfessionalCosmeticModel.Box.of(
                        80, 24, -1.95F, 0.0F, -0.22F,
                        4.0F, 3.45F, 0.42F, 0.03F)
        );
        builder.bone(
                CosmeticAnchor.LEFT_ARM, "left_sleeve", null,
                0.0F, 0.0F, 0.0F,
                0.0F, 0.0F, 0.0F,
                ProfessionalCosmeticModel.Box.of(
                        32, 0, -2.0F, -2.0F, -2.0F,
                        4.0F, 12.0F, 4.0F, 0.55F),
                ProfessionalCosmeticModel.Box.of(
                        96, 32, -2.08F, 8.55F, -2.08F,
                        4.16F, 1.5F, 4.16F, 0.03F)
        );
        builder.bone(
                CosmeticAnchor.RIGHT_ARM, "right_sleeve", null,
                0.0F, 0.0F, 0.0F,
                0.0F, 0.0F, 0.0F,
                ProfessionalCosmeticModel.Box.of(
                        48, 0, -2.0F, -2.0F, -2.0F,
                        4.0F, 12.0F, 4.0F, 0.55F),
                ProfessionalCosmeticModel.Box.of(
                        96, 32, -2.08F, 8.55F, -2.08F,
                        4.16F, 1.5F, 4.16F, 0.03F)
        );
        builder.bone(
                CosmeticAnchor.LEFT_LEG, "left_stocking", null,
                0.0F, 0.0F, 0.0F,
                0.0F, 0.0F, 0.0F,
                ProfessionalCosmeticModel.Box.of(
                        0, 24, -2.0F, 0.0F, -2.0F,
                        4.0F, 12.0F, 4.0F, 0.54F),
                ProfessionalCosmeticModel.Box.of(
                        32, 24, -2.08F, 8.0F, -2.2F,
                        4.16F, 4.1F, 4.4F, 0.04F)
        );
        builder.bone(
                CosmeticAnchor.RIGHT_LEG, "right_stocking", null,
                0.0F, 0.0F, 0.0F,
                0.0F, 0.0F, 0.0F,
                ProfessionalCosmeticModel.Box.of(
                        16, 24, -2.0F, 0.0F, -2.0F,
                        4.0F, 12.0F, 4.0F, 0.54F),
                ProfessionalCosmeticModel.Box.of(
                        48, 24, -2.08F, 8.0F, -2.2F,
                        4.16F, 4.1F, 4.4F, 0.04F)
        );
        return builder.bake(true);
    }


    private static ProfessionalCosmeticModel ponyPastelChest() {
        ProfessionalCosmeticModel.Builder b = new ProfessionalCosmeticModel.Builder("pony_pastel_chest", 128, 128);
        b.bone(CosmeticAnchor.BODY,"jacket_body",null,0,0,0,0,0,0, ProfessionalCosmeticModel.Box.of(0,0,-4,-0.02F,-2,8,9.4F,4,0.57F));
        b.bone(CosmeticAnchor.LEFT_ARM,"left_sleeve",null,0,0,0,0,0,0, ProfessionalCosmeticModel.Box.of(32,0,-2,-2,-2,4,12,4,0.42F));
        b.bone(CosmeticAnchor.RIGHT_ARM,"right_sleeve",null,0,0,0,0,0,0, ProfessionalCosmeticModel.Box.of(48,0,-2,-2,-2,4,12,4,0.42F));
        return b.bake(true);
    }
    private static ProfessionalCosmeticModel ponyPastelLegs() { return legPiece("pony_pastel_legs", 0, 24, 16, 24, true); }
    private static ProfessionalCosmeticModel ponyPastelFeet() { return footPiece("pony_pastel_feet", 32, 24, 48, 24); }
    private static ProfessionalCosmeticModel somitaNightChest() {
        ProfessionalCosmeticModel.Builder b = new ProfessionalCosmeticModel.Builder("somita_night_chest",128,128);
        b.bone(CosmeticAnchor.BODY,"sweater_body",null,0,0,0,0,0,0, ProfessionalCosmeticModel.Box.of(0,0,-4,-0.02F,-2,8,9.3F,4,0.57F));
        b.bone(CosmeticAnchor.LEFT_ARM,"left_sleeve",null,0,0,0,0,0,0, ProfessionalCosmeticModel.Box.of(32,0,-2,-2,-2,4,12,4,0.42F));
        b.bone(CosmeticAnchor.RIGHT_ARM,"right_sleeve",null,0,0,0,0,0,0, ProfessionalCosmeticModel.Box.of(48,0,-2,-2,-2,4,12,4,0.42F));
        return b.bake(true);
    }
    private static ProfessionalCosmeticModel somitaNightLegs() { return legPiece("somita_night_legs", 0,24,16,24,true); }
    private static ProfessionalCosmeticModel somitaNightFeet() { return footPiece("somita_night_feet",32,24,48,24); }
    private static ProfessionalCosmeticModel bingoCorruptedChest() {
        ProfessionalCosmeticModel.Builder b = new ProfessionalCosmeticModel.Builder("bingo_corrupted_chest",128,128);
        b.bone(CosmeticAnchor.BODY,"corrupt_chest",null,0,0,0,0,0,0, ProfessionalCosmeticModel.Box.of(0,0,-4,-0.02F,-2,8,9.4F,4,0.57F));
        b.bone(CosmeticAnchor.LEFT_ARM,"left_corrupt_arm",null,0,0,0,0,0,0, ProfessionalCosmeticModel.Box.of(32,0,-2,-2,-2,4,12,4,0.42F));
        b.bone(CosmeticAnchor.RIGHT_ARM,"right_corrupt_arm",null,0,0,0,0,0,0, ProfessionalCosmeticModel.Box.of(48,0,-2,-2,-2,4,12,4,0.42F));
        return b.bake(true);
    }
    private static ProfessionalCosmeticModel bingoCorruptedLegs() { return legPiece("bingo_corrupted_legs",0,24,16,24,false); }
    private static ProfessionalCosmeticModel bingoCorruptedFeet() { return footPiece("bingo_corrupted_feet",32,24,48,24); }
    private static ProfessionalCosmeticModel legPiece(String id,int lu,int lv,int ru,int rv,boolean skirt) {
        ProfessionalCosmeticModel.Builder b = new ProfessionalCosmeticModel.Builder(id,128,128);
        if (skirt) b.bone(CosmeticAnchor.BODY,"skirt",null,0,9.1F,0,0,0,0, ProfessionalCosmeticModel.Box.of(64,24,-4.15F,0,-2.15F,8.3F,3.7F,4.3F,0.04F));
        else b.bone(CosmeticAnchor.BODY,"tabard",null,0,9.1F,0,0,0,0, ProfessionalCosmeticModel.Box.of(64,24,-2.4F,0,-2.15F,4.8F,4.5F,4.3F,0.04F));
        b.bone(CosmeticAnchor.LEFT_LEG,"left_leg",null,0,0,0,0,0,0, ProfessionalCosmeticModel.Box.of(lu,lv,-2,0,-2,4,8.2F,4,0.55F));
        b.bone(CosmeticAnchor.RIGHT_LEG,"right_leg",null,0,0,0,0,0,0, ProfessionalCosmeticModel.Box.of(ru,rv,-2,0,-2,4,8.2F,4,0.55F));
        return b.bake(true);
    }
    private static ProfessionalCosmeticModel footPiece(String id,int lu,int lv,int ru,int rv) {
        ProfessionalCosmeticModel.Builder b = new ProfessionalCosmeticModel.Builder(id,128,128);
        b.bone(CosmeticAnchor.LEFT_LEG,"left_boot",null,0,0,0,0,0,0, ProfessionalCosmeticModel.Box.of(lu,lv,-2.08F,8,-2.2F,4.16F,4.1F,4.4F,0.05F));
        b.bone(CosmeticAnchor.RIGHT_LEG,"right_boot",null,0,0,0,0,0,0, ProfessionalCosmeticModel.Box.of(ru,rv,-2.08F,8,-2.2F,4.16F,4.1F,4.4F,0.05F));
        return b.bake(true);
    }

    private static ProfessionalCosmeticModel ponyWhaleTail() {
        ProfessionalCosmeticModel.Builder builder = new ProfessionalCosmeticModel.Builder("pony_whale_tail", 128, 128);
        builder.bone(
                CosmeticAnchor.BODY, "tail_base", null,
                0.0F, 10.65F, 2.05F,
                14.0F, 0.0F, 0.0F,
                ProfessionalCosmeticModel.Box.of(
                        0, 0, -1.4F, -0.65F, -0.25F,
                        2.8F, 4.45F, 2.8F, 0.14F)
        );
        builder.bone(
                CosmeticAnchor.BODY, "tail_mid", "tail_base",
                0.0F, 3.45F, 1.35F,
                8.0F, 0.0F, 0.0F,
                ProfessionalCosmeticModel.Box.of(
                        32, 0, -1.12F, -0.45F, -0.25F,
                        2.24F, 4.25F, 2.3F, 0.12F)
        );
        builder.bone(
                CosmeticAnchor.BODY, "tail_tip", "tail_mid",
                0.0F, 3.45F, 1.05F,
                8.0F, 0.0F, 0.0F,
                ProfessionalCosmeticModel.Box.of(
                        64, 0, -0.88F, -0.35F, -0.2F,
                        1.76F, 3.65F, 1.85F, 0.10F)
        );
        builder.bone(
                CosmeticAnchor.BODY, "tail_fluke_connector", "tail_tip",
                0.0F, 2.70F, 0.62F,
                0.0F, 0.0F, 0.0F,
                ProfessionalCosmeticModel.Box.of(
                        32, 64, -1.60F, -1.35F, -1.02F,
                        3.2F, 2.75F, 2.04F, 0.18F)
        );
        builder.bone(
                CosmeticAnchor.BODY, "tail_fin_left", "tail_fluke_connector",
                -0.10F, 0.0F, 0.0F,
                0.0F, -10.0F, -24.0F,
                ProfessionalCosmeticModel.Box.of(
                        64, 0, -5.55F, -1.05F, -0.62F,
                        5.7F, 2.5F, 1.28F, 0.10F),
                ProfessionalCosmeticModel.Box.of(
                        96, 0, -4.65F, 1.05F, -0.52F,
                        4.75F, 1.75F, 1.08F, 0.07F),
                ProfessionalCosmeticModel.Box.of(
                        32, 0, -3.2F, 2.45F, -0.42F,
                        3.3F, 1.2F, 0.88F, 0.05F)
        );
        builder.bone(
                CosmeticAnchor.BODY, "tail_fin_right", "tail_fluke_connector",
                0.10F, 0.0F, 0.0F,
                0.0F, 10.0F, 24.0F,
                ProfessionalCosmeticModel.Box.of(
                        64, 0, -0.15F, -1.05F, -0.62F,
                        5.7F, 2.5F, 1.28F, 0.10F),
                ProfessionalCosmeticModel.Box.of(
                        96, 0, -0.1F, 1.05F, -0.52F,
                        4.75F, 1.75F, 1.08F, 0.07F),
                ProfessionalCosmeticModel.Box.of(
                        32, 0, -0.1F, 2.45F, -0.42F,
                        3.3F, 1.2F, 0.88F, 0.05F)
        );
        builder.bone(
                CosmeticAnchor.BODY, "tail_bow", "tail_base",
                0.0F, 0.35F, -0.25F,
                0.0F, 0.0F, 0.0F,
                ProfessionalCosmeticModel.Box.of(
                        0, 32, -1.0F, -1.0F, -0.8F,
                        2.0F, 2.0F, 1.2F, 0.08F),
                ProfessionalCosmeticModel.Box.of(
                        0, 32, -4.0F, -1.6F, -0.5F,
                        3.2F, 3.2F, 0.9F, 0.05F),
                ProfessionalCosmeticModel.Box.of(
                        32, 32, 0.8F, -1.6F, -0.5F,
                        3.2F, 3.2F, 0.9F, 0.05F),
                ProfessionalCosmeticModel.Box.of(
                        32, 32, -2.6F, 1.0F, -0.4F,
                        1.5F, 3.3F, 0.75F, 0.04F),
                ProfessionalCosmeticModel.Box.of(
                        0, 32, 1.1F, 1.0F, -0.4F,
                        1.5F, 3.3F, 0.75F, 0.04F)
        );
        return builder.bake(true);
    }

    private static ProfessionalCosmeticModel ponyAxolotlPal() {
        ProfessionalCosmeticModel.Builder builder = new ProfessionalCosmeticModel.Builder("pony_axolotl_pal", 128, 128);
        builder.bone(
                CosmeticAnchor.BODY, "axolotl_root", null,
                13.400F, -10.600F, -3.350F,
                0.0F, -8.0F, 0.0F,
                ProfessionalCosmeticModel.Box.of(
                        0, 0, -2.2F, -2.1F, -1.9F,
                        4.4F, 3.8F, 3.8F, 0.1F),
                ProfessionalCosmeticModel.Box.of(
                        32, 0, -1.7F, 1.2F, -1.4F,
                        3.4F, 3.4F, 2.8F, 0.08F),
                ProfessionalCosmeticModel.Box.of(
                        32, 0, -1.2F, 4.2F, -1.0F,
                        2.4F, 2.0F, 2.0F, 0.05F),
                ProfessionalCosmeticModel.Box.of(
                        96, 0, -1.6F, -1.1F, -2.15F,
                        0.65F, 0.65F, 0.35F, 0.015F),
                ProfessionalCosmeticModel.Box.of(
                        96, 0, 0.95F, -1.1F, -2.15F,
                        0.65F, 0.65F, 0.35F, 0.015F),
                ProfessionalCosmeticModel.Box.of(
                        96, 64, -0.3F, -0.25F, -2.25F,
                        0.6F, 0.3F, 0.3F, 0.008F)
        );
        builder.bone(
                CosmeticAnchor.BODY, "gill_left", "axolotl_root",
                -2.0F, -0.5F, 0.0F,
                0.0F, 0.0F, -12.0F,
                ProfessionalCosmeticModel.Box.of(
                        32, 32, -0.2F, -2.7F, -0.55F,
                        0.75F, 2.8F, 1.1F, 0.03F),
                ProfessionalCosmeticModel.Box.of(
                        64, 32, 0.15F, -2.4F, -0.45F,
                        0.8F, 0.7F, 0.9F, 0.02F),
                ProfessionalCosmeticModel.Box.of(
                        64, 32, 0.15F, -1.4F, -0.45F,
                        0.9F, 0.7F, 0.9F, 0.02F),
                ProfessionalCosmeticModel.Box.of(
                        64, 32, 0.15F, -0.4F, -0.45F,
                        0.8F, 0.7F, 0.9F, 0.02F)
        );
        builder.bone(
                CosmeticAnchor.BODY, "gill_right", "axolotl_root",
                2.0F, -0.5F, 0.0F,
                0.0F, 0.0F, 12.0F,
                ProfessionalCosmeticModel.Box.of(
                        32, 32, -0.55F, -2.7F, -0.55F,
                        0.75F, 2.8F, 1.1F, 0.03F),
                ProfessionalCosmeticModel.Box.of(
                        64, 32, -0.95F, -2.4F, -0.45F,
                        0.8F, 0.7F, 0.9F, 0.02F),
                ProfessionalCosmeticModel.Box.of(
                        64, 32, -1.05F, -1.4F, -0.45F,
                        0.9F, 0.7F, 0.9F, 0.02F),
                ProfessionalCosmeticModel.Box.of(
                        64, 32, -0.95F, -0.4F, -0.45F,
                        0.8F, 0.7F, 0.9F, 0.02F)
        );
        builder.bone(
                CosmeticAnchor.BODY, "axolotl_tail", "axolotl_root",
                0.0F, 5.6F, 1.3F,
                -20.0F, 0.0F, 0.0F,
                ProfessionalCosmeticModel.Box.of(
                        0, 0, -0.7F, -0.2F, -0.4F,
                        1.4F, 4.0F, 1.0F, 0.03F),
                ProfessionalCosmeticModel.Box.of(
                        32, 32, -2.6F, 2.8F, -0.25F,
                        5.2F, 1.8F, 0.7F, 0.03F)
        );
        builder.bone(
                CosmeticAnchor.BODY, "leg_front_left", "axolotl_root",
                -1.4F, 3.1F, -0.7F,
                0.0F, 0.0F, 18.0F,
                ProfessionalCosmeticModel.Box.of(
                        32, 0, -0.35F, -0.2F, -0.35F,
                        0.7F, 2.0F, 0.7F, 0.02F)
        );
        builder.bone(
                CosmeticAnchor.BODY, "leg_front_right", "axolotl_root",
                1.4F, 3.1F, -0.7F,
                0.0F, 0.0F, -18.0F,
                ProfessionalCosmeticModel.Box.of(
                        32, 0, -0.35F, -0.2F, -0.35F,
                        0.7F, 2.0F, 0.7F, 0.02F)
        );
        return builder.bake(true);
    }

    private static ProfessionalCosmeticModel ponyWhaleStars() {
        ProfessionalCosmeticModel.Builder builder = new ProfessionalCosmeticModel.Builder("pony_whale_stars", 128, 128);
        builder.bone(
                CosmeticAnchor.ROOT, "orbit_whale", null,
                0.0F, 10.0F, 0.0F,
                0.0F, 0.0F, 0.0F,
                ProfessionalCosmeticModel.Box.of(
                        32, 0, 9.0F, -2.0F, -2.0F,
                        5.0F, 4.0F, 4.0F, 0.15F),
                ProfessionalCosmeticModel.Box.of(
                        64, 0, 7.0F, -1.4F, -1.5F,
                        2.7F, 2.8F, 3.0F, 0.1F),
                ProfessionalCosmeticModel.Box.of(
                        96, 0, 13.1F, -1.0F, -1.4F,
                        2.2F, 2.0F, 2.8F, 0.08F),
                ProfessionalCosmeticModel.Box.of(
                        96, 96, 8.3F, -0.4F, -2.35F,
                        0.5F, 0.5F, 0.4F, 0.01F),
                ProfessionalCosmeticModel.Box.of(
                        0, 32, 8.4F, 0.4F, -2.35F,
                        0.4F, 0.25F, 0.35F, 0.01F)
        );
        builder.bone(
                CosmeticAnchor.ROOT, "whale_tail_left", "orbit_whale",
                15.000F, 0.000F, 0.000F,
                0.0F, 0.0F, -25.0F,
                ProfessionalCosmeticModel.Box.of(
                        64, 0, 0.0F, -1.4F, -0.6F,
                        3.2F, 1.8F, 1.2F, 0.06F)
        );
        builder.bone(
                CosmeticAnchor.ROOT, "whale_tail_right", "orbit_whale",
                15.000F, 0.000F, 0.000F,
                0.0F, 0.0F, 25.0F,
                ProfessionalCosmeticModel.Box.of(
                        96, 0, 0.0F, -0.4F, -0.6F,
                        3.2F, 1.8F, 1.2F, 0.06F)
        );
        builder.bone(
                CosmeticAnchor.ROOT, "star_one", null,
                -10.0F, 6.0F, -3.0F,
                0.0F, 0.0F, 45.0F,
                ProfessionalCosmeticModel.Box.of(
                        64, 32, -1.0F, -1.0F, -0.35F,
                        2.0F, 2.0F, 0.7F, 0.05F),
                ProfessionalCosmeticModel.Box.of(
                        64, 32, -0.35F, -1.7F, -0.3F,
                        0.7F, 3.4F, 0.6F, 0.03F)
        );
        builder.bone(
                CosmeticAnchor.ROOT, "star_two", null,
                -6.0F, 14.0F, 7.0F,
                0.0F, 0.0F, 45.0F,
                ProfessionalCosmeticModel.Box.of(
                        0, 32, -0.8F, -0.8F, -0.3F,
                        1.6F, 1.6F, 0.6F, 0.04F),
                ProfessionalCosmeticModel.Box.of(
                        0, 32, -0.3F, -1.35F, -0.25F,
                        0.6F, 2.7F, 0.5F, 0.03F)
        );
        builder.bone(
                CosmeticAnchor.ROOT, "star_three", null,
                2.0F, 3.0F, 10.0F,
                0.0F, 0.0F, 45.0F,
                ProfessionalCosmeticModel.Box.of(
                        0, 64, -0.7F, -0.7F, -0.25F,
                        1.4F, 1.4F, 0.5F, 0.04F),
                ProfessionalCosmeticModel.Box.of(
                        0, 64, -0.25F, -1.2F, -0.22F,
                        0.5F, 2.4F, 0.44F, 0.03F)
        );
        return builder.bake(true);
    }

    private static ProfessionalCosmeticModel somitaBatClip() {
        ProfessionalCosmeticModel.Builder builder = new ProfessionalCosmeticModel.Builder("somita_bat_clip", 128, 128);
        builder.bone(
                CosmeticAnchor.HEAD, "clip_body", null,
                -2.7F, -7.8F, -4.2F,
                0.0F, 0.0F, -14.0F,
                ProfessionalCosmeticModel.Box.of(
                        0, 0, -1.1F, -0.9F, -0.35F,
                        2.2F, 1.8F, 0.7F, 0.06F),
                ProfessionalCosmeticModel.Box.of(
                        32, 0, -0.5F, -1.6F, -0.3F,
                        1.0F, 0.9F, 0.6F, 0.04F),
                ProfessionalCosmeticModel.Box.of(
                        64, 0, -0.35F, 0.7F, -0.3F,
                        0.7F, 0.8F, 0.6F, 0.03F),
                ProfessionalCosmeticModel.Box.of(
                        0, 32, -0.35F, -0.35F, -0.52F,
                        0.7F, 0.7F, 0.35F, 0.02F)
        );
        builder.bone(
                CosmeticAnchor.HEAD, "clip_wing_left", "clip_body",
                -0.900F, 0.100F, 0.000F,
                0.0F, 0.0F, -18.0F,
                ProfessionalCosmeticModel.Box.of(
                        0, 0, -3.4F, -1.0F, -0.25F,
                        3.4F, 1.1F, 0.5F, 0.04F),
                ProfessionalCosmeticModel.Box.of(
                        32, 0, -2.8F, 0.0F, -0.22F,
                        2.2F, 1.2F, 0.44F, 0.03F),
                ProfessionalCosmeticModel.Box.of(
                        64, 0, -1.8F, 0.9F, -0.2F,
                        1.2F, 1.0F, 0.4F, 0.03F)
        );
        builder.bone(
                CosmeticAnchor.HEAD, "clip_wing_right", "clip_body",
                0.900F, 0.100F, 0.000F,
                0.0F, 0.0F, 18.0F,
                ProfessionalCosmeticModel.Box.of(
                        0, 0, 0.0F, -1.0F, -0.25F,
                        3.4F, 1.1F, 0.5F, 0.04F),
                ProfessionalCosmeticModel.Box.of(
                        32, 0, 0.6F, 0.0F, -0.22F,
                        2.2F, 1.2F, 0.44F, 0.03F),
                ProfessionalCosmeticModel.Box.of(
                        64, 0, 0.6F, 0.9F, -0.2F,
                        1.2F, 1.0F, 0.4F, 0.03F)
        );
        return builder.bake(true);
    }

    private static ProfessionalCosmeticModel somitaNightOutfit() {
        ProfessionalCosmeticModel.Builder builder = new ProfessionalCosmeticModel.Builder("somita_night_outfit", 128, 128);
        builder.bone(
                CosmeticAnchor.BODY, "sweater_body", null,
                0.0F, 0.0F, 0.0F,
                0.0F, 0.0F, 0.0F,
                ProfessionalCosmeticModel.Box.of(
                        0, 0, -4.0F, -0.02F, -2.0F,
                        8.0F, 12.02F, 4.0F, 0.56F)
        );
        builder.bone(
                CosmeticAnchor.BODY, "somita_neckline", "sweater_body",
                0.0F, 0.0F, 0.0F,
                0.0F, 0.0F, 0.0F,
                ProfessionalCosmeticModel.Box.of(
                        64, 0, -2.35F, -0.08F, -2.2F,
                        4.7F, 2.2F, 0.22F, 0.015F),
                ProfessionalCosmeticModel.Box.of(
                        96, 0, -0.16F, 1.15F, -2.25F,
                        0.32F, 6.6F, 0.16F, 0.008F)
        );
        builder.bone(
                CosmeticAnchor.BODY, "somita_waist", "sweater_body",
                0.0F, 0.0F, 0.0F,
                0.0F, 0.0F, 0.0F,
                ProfessionalCosmeticModel.Box.of(
                        64, 16, -4.08F, 8.25F, -2.08F,
                        8.16F, 1.0F, 4.16F, 0.03F)
        );
        builder.bone(
                CosmeticAnchor.BODY, "somita_skirt_front_left", "sweater_body",
                -2.05F, 9.15F, -2.06F,
                -3.0F, 0.0F, -3.0F,
                ProfessionalCosmeticModel.Box.of(
                        64, 24, -2.05F, 0.0F, -0.2F,
                        4.0F, 3.65F, 0.42F, 0.03F)
        );
        builder.bone(
                CosmeticAnchor.BODY, "somita_skirt_front_right", "sweater_body",
                2.05F, 9.15F, -2.06F,
                -3.0F, 0.0F, 3.0F,
                ProfessionalCosmeticModel.Box.of(
                        64, 24, -1.95F, 0.0F, -0.2F,
                        4.0F, 3.65F, 0.42F, 0.03F)
        );
        builder.bone(
                CosmeticAnchor.BODY, "somita_skirt_back_left", "sweater_body",
                -2.05F, 9.15F, 2.06F,
                3.0F, 0.0F, -3.0F,
                ProfessionalCosmeticModel.Box.of(
                        80, 24, -2.05F, 0.0F, -0.22F,
                        4.0F, 3.65F, 0.42F, 0.03F)
        );
        builder.bone(
                CosmeticAnchor.BODY, "somita_skirt_back_right", "sweater_body",
                2.05F, 9.15F, 2.06F,
                3.0F, 0.0F, 3.0F,
                ProfessionalCosmeticModel.Box.of(
                        80, 24, -1.95F, 0.0F, -0.22F,
                        4.0F, 3.65F, 0.42F, 0.03F)
        );
        builder.bone(
                CosmeticAnchor.LEFT_ARM, "left_somita_sleeve", null,
                0.0F, 0.0F, 0.0F,
                0.0F, 0.0F, 0.0F,
                ProfessionalCosmeticModel.Box.of(
                        32, 0, -2.0F, -2.0F, -2.0F,
                        4.0F, 12.0F, 4.0F, 0.55F),
                ProfessionalCosmeticModel.Box.of(
                        96, 32, -2.12F, 8.35F, -2.12F,
                        4.24F, 1.75F, 4.24F, 0.04F)
        );
        builder.bone(
                CosmeticAnchor.RIGHT_ARM, "right_somita_sleeve", null,
                0.0F, 0.0F, 0.0F,
                0.0F, 0.0F, 0.0F,
                ProfessionalCosmeticModel.Box.of(
                        48, 0, -2.0F, -2.0F, -2.0F,
                        4.0F, 12.0F, 4.0F, 0.55F),
                ProfessionalCosmeticModel.Box.of(
                        96, 32, -2.12F, 8.35F, -2.12F,
                        4.24F, 1.75F, 4.24F, 0.04F)
        );
        builder.bone(
                CosmeticAnchor.LEFT_LEG, "left_somita_stocking", null,
                0.0F, 0.0F, 0.0F,
                0.0F, 0.0F, 0.0F,
                ProfessionalCosmeticModel.Box.of(
                        0, 24, -2.0F, 0.0F, -2.0F,
                        4.0F, 12.0F, 4.0F, 0.54F),
                ProfessionalCosmeticModel.Box.of(
                        32, 24, -2.08F, 8.0F, -2.18F,
                        4.16F, 4.1F, 4.36F, 0.04F)
        );
        builder.bone(
                CosmeticAnchor.RIGHT_LEG, "right_somita_stocking", null,
                0.0F, 0.0F, 0.0F,
                0.0F, 0.0F, 0.0F,
                ProfessionalCosmeticModel.Box.of(
                        16, 24, -2.0F, 0.0F, -2.0F,
                        4.0F, 12.0F, 4.0F, 0.54F),
                ProfessionalCosmeticModel.Box.of(
                        48, 24, -2.08F, 8.0F, -2.18F,
                        4.16F, 4.1F, 4.36F, 0.04F)
        );
        return builder.bake(true);
    }

    private static ProfessionalCosmeticModel somitaBatWings() {
        ProfessionalCosmeticModel.Builder builder = new ProfessionalCosmeticModel.Builder("somita_bat_wings", 128, 128);
        builder.bone(
                CosmeticAnchor.BODY, "wing_left_base", null,
                -3.15F, 2.35F, 2.55F,
                4.0F, 18.0F, -9.0F,
                ProfessionalCosmeticModel.Box.of(
                        0, 0, -9.0F, -0.68F, -0.68F,
                        9.2F, 1.36F, 1.36F, 0.08F),
                ProfessionalCosmeticModel.Box.of(
                        32, 0, -8.0F, 2.65F, -0.55F,
                        7.1F, 0.92F, 1.10F, 0.06F),
                ProfessionalCosmeticModel.Box.of(
                        64, 0, -6.25F, 5.60F, -0.48F,
                        5.25F, 0.82F, 0.96F, 0.05F)
        );
        builder.bone(
                CosmeticAnchor.BODY, "wing_left_membrane_top", "wing_left_base",
                0.0F, 0.0F, 0.18F,
                0.0F, 0.0F, 0.0F,
                ProfessionalCosmeticModel.Box.of(
                        96, 0, -8.35F, 0.25F, -0.12F,
                        7.45F, 2.25F, 0.24F, 0.015F),
                ProfessionalCosmeticModel.Box.of(
                        0, 32, -7.35F, 2.45F, -0.10F,
                        6.25F, 2.65F, 0.16F, 0.010F)
        );
        builder.bone(
                CosmeticAnchor.BODY, "wing_left_membrane_low", "wing_left_base",
                0.0F, 0.0F, 0.64F,
                0.0F, 0.0F, 0.0F,
                ProfessionalCosmeticModel.Box.of(
                        64, 32, -5.95F, 5.05F, -0.10F,
                        4.75F, 2.45F, 0.16F, 0.010F),
                ProfessionalCosmeticModel.Box.of(
                        96, 32, -4.55F, 7.25F, -0.09F,
                        3.25F, 1.85F, 0.14F, 0.008F)
        );
        builder.bone(
                CosmeticAnchor.BODY, "wing_right_base", null,
                3.15F, 2.35F, 2.55F,
                4.0F, -18.0F, 9.0F,
                ProfessionalCosmeticModel.Box.of(
                        0, 0, -0.2F, -0.68F, -0.68F,
                        9.2F, 1.36F, 1.36F, 0.08F),
                ProfessionalCosmeticModel.Box.of(
                        32, 0, 0.9F, 2.65F, -0.55F,
                        7.1F, 0.92F, 1.10F, 0.06F),
                ProfessionalCosmeticModel.Box.of(
                        64, 0, 1.0F, 5.60F, -0.48F,
                        5.25F, 0.82F, 0.96F, 0.05F)
        );
        builder.bone(
                CosmeticAnchor.BODY, "wing_right_membrane_top", "wing_right_base",
                0.0F, 0.0F, 0.18F,
                0.0F, 0.0F, 0.0F,
                ProfessionalCosmeticModel.Box.of(
                        96, 0, 0.90F, 0.25F, -0.12F,
                        7.45F, 2.25F, 0.24F, 0.015F),
                ProfessionalCosmeticModel.Box.of(
                        0, 32, 1.10F, 2.45F, -0.10F,
                        6.25F, 2.65F, 0.16F, 0.010F)
        );
        builder.bone(
                CosmeticAnchor.BODY, "wing_right_membrane_low", "wing_right_base",
                0.0F, 0.0F, 0.64F,
                0.0F, 0.0F, 0.0F,
                ProfessionalCosmeticModel.Box.of(
                        64, 32, 1.20F, 5.05F, -0.10F,
                        4.75F, 2.45F, 0.16F, 0.010F),
                ProfessionalCosmeticModel.Box.of(
                        96, 32, 1.30F, 7.25F, -0.09F,
                        3.25F, 1.85F, 0.14F, 0.008F)
        );
        return builder.bake(true);
    }

    private static ProfessionalCosmeticModel somitaBatPal() {
        ProfessionalCosmeticModel.Builder builder = new ProfessionalCosmeticModel.Builder("somita_bat_pal", 128, 128);
        builder.bone(
                CosmeticAnchor.BODY, "bat_pal_body", null,
                13.200F, -10.750F, -3.200F,
                0.0F, 10.0F, 0.0F,
                ProfessionalCosmeticModel.Box.of(
                        0, 0, -1.5F, -1.7F, -1.2F,
                        3.0F, 3.4F, 2.4F, 0.08F),
                ProfessionalCosmeticModel.Box.of(
                        32, 0, -1.15F, -3.5F, -1.0F,
                        2.3F, 2.2F, 2.0F, 0.06F),
                ProfessionalCosmeticModel.Box.of(
                        64, 0, -1.1F, -4.5F, -0.6F,
                        0.8F, 1.5F, 1.0F, 0.03F),
                ProfessionalCosmeticModel.Box.of(
                        64, 0, 0.3F, -4.5F, -0.6F,
                        0.8F, 1.5F, 1.0F, 0.03F),
                ProfessionalCosmeticModel.Box.of(
                        64, 64, -0.75F, -2.9F, -1.25F,
                        0.45F, 0.45F, 0.3F, 0.015F),
                ProfessionalCosmeticModel.Box.of(
                        64, 64, 0.3F, -2.9F, -1.25F,
                        0.45F, 0.45F, 0.3F, 0.015F),
                ProfessionalCosmeticModel.Box.of(
                        32, 64, -0.25F, -2.2F, -1.32F,
                        0.5F, 0.3F, 0.25F, 0.008F)
        );
        builder.bone(
                CosmeticAnchor.BODY, "bat_pal_wing_left", "bat_pal_body",
                -1.4F, -0.5F, 0.1F,
                0.0F, 0.0F, -25.0F,
                ProfessionalCosmeticModel.Box.of(
                        32, 0, -3.8F, -0.5F, -0.25F,
                        3.8F, 1.0F, 0.5F, 0.03F),
                ProfessionalCosmeticModel.Box.of(
                        96, 0, -3.2F, 0.3F, -0.22F,
                        2.7F, 1.6F, 0.44F, 0.02F),
                ProfessionalCosmeticModel.Box.of(
                        64, 0, -2.2F, 1.6F, -0.2F,
                        1.7F, 1.3F, 0.4F, 0.02F)
        );
        builder.bone(
                CosmeticAnchor.BODY, "bat_pal_wing_right", "bat_pal_body",
                1.4F, -0.5F, 0.1F,
                0.0F, 0.0F, 25.0F,
                ProfessionalCosmeticModel.Box.of(
                        32, 0, 0.0F, -0.5F, -0.25F,
                        3.8F, 1.0F, 0.5F, 0.03F),
                ProfessionalCosmeticModel.Box.of(
                        96, 0, 0.5F, 0.3F, -0.22F,
                        2.7F, 1.6F, 0.44F, 0.02F),
                ProfessionalCosmeticModel.Box.of(
                        64, 0, 0.5F, 1.6F, -0.2F,
                        1.7F, 1.3F, 0.4F, 0.02F)
        );
        return builder.bake(true);
    }

    private static ProfessionalCosmeticModel somitaCrimsonHearts() {
        ProfessionalCosmeticModel.Builder builder = new ProfessionalCosmeticModel.Builder("somita_crimson_hearts", 128, 128);
        builder.bone(
                CosmeticAnchor.ROOT, "heart_orbit_one", null,
                0.0F, 10.0F, 0.0F,
                0.0F, 0.0F, 0.0F,
                ProfessionalCosmeticModel.Box.of(
                        96, 0, 10.0F, -1.0F, -1.0F,
                        2.2F, 2.2F, 2.0F, 0.08F),
                ProfessionalCosmeticModel.Box.of(
                        96, 0, 11.3F, -1.0F, -1.0F,
                        2.2F, 2.2F, 2.0F, 0.08F),
                ProfessionalCosmeticModel.Box.of(
                        0, 32, 10.65F, 0.1F, -1.0F,
                        2.2F, 2.8F, 2.0F, 0.06F)
        );
        builder.bone(
                CosmeticAnchor.ROOT, "heart_orbit_two", null,
                0.0F, 10.0F, 0.0F,
                0.0F, 120.0F, 0.0F,
                ProfessionalCosmeticModel.Box.of(
                        64, 0, 8.0F, -0.8F, -0.8F,
                        1.8F, 1.8F, 1.6F, 0.06F),
                ProfessionalCosmeticModel.Box.of(
                        64, 0, 9.0F, -0.8F, -0.8F,
                        1.8F, 1.8F, 1.6F, 0.06F),
                ProfessionalCosmeticModel.Box.of(
                        96, 0, 8.5F, 0.1F, -0.8F,
                        1.8F, 2.3F, 1.6F, 0.05F)
        );
        builder.bone(
                CosmeticAnchor.ROOT, "heart_orbit_three", null,
                0.0F, 10.0F, 0.0F,
                0.0F, 240.0F, 0.0F,
                ProfessionalCosmeticModel.Box.of(
                        0, 32, 12.0F, -1.2F, -1.2F,
                        2.5F, 2.5F, 2.2F, 0.08F),
                ProfessionalCosmeticModel.Box.of(
                        0, 32, 13.4F, -1.2F, -1.2F,
                        2.5F, 2.5F, 2.2F, 0.08F),
                ProfessionalCosmeticModel.Box.of(
                        32, 32, 12.7F, 0.1F, -1.2F,
                        2.5F, 3.1F, 2.2F, 0.06F)
        );
        builder.bone(
                CosmeticAnchor.ROOT, "mist_crystal", null,
                0.0F, 8.0F, 0.0F,
                0.0F, 0.0F, 0.0F,
                ProfessionalCosmeticModel.Box.of(
                        32, 64, -0.6F, -6.0F, -0.6F,
                        1.2F, 4.0F, 1.2F, 0.05F),
                ProfessionalCosmeticModel.Box.of(
                        32, 96, -0.4F, -8.0F, -0.4F,
                        0.8F, 2.2F, 0.8F, 0.03F),
                ProfessionalCosmeticModel.Box.of(
                        32, 0, -0.5F, 3.0F, -0.5F,
                        1.0F, 4.0F, 1.0F, 0.04F)
        );
        return builder.bake(true);
    }

    private static ProfessionalCosmeticModel bingoCorruptedCrown() {
        ProfessionalCosmeticModel.Builder builder = new ProfessionalCosmeticModel.Builder("bingo_corrupted_crown", 128, 128);
        builder.bone(
                CosmeticAnchor.HEAD, "corrupt_band", null,
                0.0F, 0.0F, 0.0F,
                0.0F, 0.0F, 0.0F,
                ProfessionalCosmeticModel.Box.of(
                        0, 0, -4.8F, -8.9F, -4.5F,
                        9.6F, 1.2F, 0.8F, 0.1F),
                ProfessionalCosmeticModel.Box.of(
                        32, 0, -4.8F, -8.9F, 3.7F,
                        9.6F, 1.2F, 0.8F, 0.1F),
                ProfessionalCosmeticModel.Box.of(
                        0, 0, -4.9F, -8.9F, -3.7F,
                        0.8F, 1.2F, 7.4F, 0.1F),
                ProfessionalCosmeticModel.Box.of(
                        32, 0, 4.1F, -8.9F, -3.7F,
                        0.8F, 1.2F, 7.4F, 0.1F),
                ProfessionalCosmeticModel.Box.of(
                        64, 96, -1.1F, -9.6F, -4.9F,
                        2.2F, 2.0F, 0.6F, 0.04F)
        );
        builder.bone(
                CosmeticAnchor.HEAD, "emerald_spike", "corrupt_band",
                -2.6F, -8.5F, -0.5F,
                -12.0F, 0.0F, -8.0F,
                ProfessionalCosmeticModel.Box.of(
                        96, 0, -1.0F, -4.0F, -1.0F,
                        2.0F, 4.2F, 2.0F, 0.06F),
                ProfessionalCosmeticModel.Box.of(
                        0, 32, -0.7F, -6.2F, -0.7F,
                        1.4F, 2.4F, 1.4F, 0.04F),
                ProfessionalCosmeticModel.Box.of(
                        32, 32, -0.35F, -7.4F, -0.35F,
                        0.7F, 1.3F, 0.7F, 0.02F)
        );
        builder.bone(
                CosmeticAnchor.HEAD, "gold_spike", "corrupt_band",
                0.0F, -8.7F, 0.0F,
                -8.0F, 0.0F, 0.0F,
                ProfessionalCosmeticModel.Box.of(
                        96, 32, -1.1F, -5.0F, -1.1F,
                        2.2F, 5.2F, 2.2F, 0.06F),
                ProfessionalCosmeticModel.Box.of(
                        0, 64, -0.75F, -7.4F, -0.75F,
                        1.5F, 2.6F, 1.5F, 0.04F),
                ProfessionalCosmeticModel.Box.of(
                        32, 64, -0.35F, -8.7F, -0.35F,
                        0.7F, 1.4F, 0.7F, 0.02F)
        );
        builder.bone(
                CosmeticAnchor.HEAD, "amethyst_spike", "corrupt_band",
                2.6F, -8.5F, -0.5F,
                -12.0F, 0.0F, 8.0F,
                ProfessionalCosmeticModel.Box.of(
                        96, 64, -1.0F, -4.0F, -1.0F,
                        2.0F, 4.2F, 2.0F, 0.06F),
                ProfessionalCosmeticModel.Box.of(
                        0, 96, -0.7F, -6.2F, -0.7F,
                        1.4F, 2.4F, 1.4F, 0.04F),
                ProfessionalCosmeticModel.Box.of(
                        32, 96, -0.35F, -7.4F, -0.35F,
                        0.7F, 1.3F, 0.7F, 0.02F)
        );
        builder.bone(
                CosmeticAnchor.HEAD, "left_rune", "corrupt_band",
                -4.4F, -8.2F, 0.0F,
                0.0F, 0.0F, 0.0F,
                ProfessionalCosmeticModel.Box.of(
                        96, 0, -0.25F, -2.2F, -2.0F,
                        0.5F, 3.8F, 4.0F, 0.02F)
        );
        builder.bone(
                CosmeticAnchor.HEAD, "right_rune", "corrupt_band",
                4.4F, -8.2F, 0.0F,
                0.0F, 0.0F, 0.0F,
                ProfessionalCosmeticModel.Box.of(
                        96, 64, -0.25F, -2.2F, -2.0F,
                        0.5F, 3.8F, 4.0F, 0.02F)
        );
        return builder.bake(true);
    }

    private static ProfessionalCosmeticModel bingoCorruptedOutfit() {
        ProfessionalCosmeticModel.Builder builder = new ProfessionalCosmeticModel.Builder("bingo_corrupted_outfit", 128, 128);
        builder.bone(
                CosmeticAnchor.BODY, "corrupt_chest", null,
                0.0F, 0.0F, 0.0F,
                0.0F, 0.0F, 0.0F,
                ProfessionalCosmeticModel.Box.of(
                        0, 0, -4.0F, -0.02F, -2.0F,
                        8.0F, 12.02F, 4.0F, 0.56F)
        );
        builder.bone(
                CosmeticAnchor.BODY, "corrupt_trim", "corrupt_chest",
                0.0F, 0.0F, 0.0F,
                0.0F, 0.0F, 0.0F,
                ProfessionalCosmeticModel.Box.of(
                        64, 0, -3.1F, 0.7F, -2.2F,
                        0.34F, 7.2F, 0.18F, 0.01F),
                ProfessionalCosmeticModel.Box.of(
                        64, 0, -0.17F, 0.7F, -2.22F,
                        0.34F, 7.2F, 0.18F, 0.01F),
                ProfessionalCosmeticModel.Box.of(
                        64, 0, 2.76F, 0.7F, -2.2F,
                        0.34F, 7.2F, 0.18F, 0.01F)
        );
        builder.bone(
                CosmeticAnchor.BODY, "corrupt_belt", "corrupt_chest",
                0.0F, 0.0F, 0.0F,
                0.0F, 0.0F, 0.0F,
                ProfessionalCosmeticModel.Box.of(
                        64, 16, -4.1F, 8.15F, -2.1F,
                        8.2F, 1.1F, 4.2F, 0.035F)
        );
        builder.bone(
                CosmeticAnchor.BODY, "corrupt_tabard_front", "corrupt_chest",
                0.0F, 9.1F, -2.08F,
                -4.0F, 0.0F, 0.0F,
                ProfessionalCosmeticModel.Box.of(
                        64, 24, -2.3F, 0.0F, -0.2F,
                        4.6F, 4.6F, 0.42F, 0.035F)
        );
        builder.bone(
                CosmeticAnchor.BODY, "corrupt_tabard_back", "corrupt_chest",
                0.0F, 9.1F, 2.08F,
                4.0F, 0.0F, 0.0F,
                ProfessionalCosmeticModel.Box.of(
                        80, 24, -2.3F, 0.0F, -0.22F,
                        4.6F, 4.4F, 0.42F, 0.035F)
        );
        builder.bone(
                CosmeticAnchor.LEFT_ARM, "left_corrupt_arm", null,
                0.0F, 0.0F, 0.0F,
                0.0F, 0.0F, 0.0F,
                ProfessionalCosmeticModel.Box.of(
                        32, 0, -2.0F, -2.0F, -2.0F,
                        4.0F, 12.0F, 4.0F, 0.55F)
        );
        builder.bone(
                CosmeticAnchor.RIGHT_ARM, "right_corrupt_arm", null,
                0.0F, 0.0F, 0.0F,
                0.0F, 0.0F, 0.0F,
                ProfessionalCosmeticModel.Box.of(
                        48, 0, -2.0F, -2.0F, -2.0F,
                        4.0F, 12.0F, 4.0F, 0.55F)
        );
        builder.bone(
                CosmeticAnchor.LEFT_LEG, "left_corrupt_leg", null,
                0.0F, 0.0F, 0.0F,
                0.0F, 0.0F, 0.0F,
                ProfessionalCosmeticModel.Box.of(
                        0, 24, -2.0F, 0.0F, -2.0F,
                        4.0F, 12.0F, 4.0F, 0.54F)
        );
        builder.bone(
                CosmeticAnchor.RIGHT_LEG, "right_corrupt_leg", null,
                0.0F, 0.0F, 0.0F,
                0.0F, 0.0F, 0.0F,
                ProfessionalCosmeticModel.Box.of(
                        16, 24, -2.0F, 0.0F, -2.0F,
                        4.0F, 12.0F, 4.0F, 0.54F)
        );
        builder.bone(
                CosmeticAnchor.LEFT_ARM, "left_arm_crystal", "left_corrupt_arm",
                -1.9F, 1.7F, -2.15F,
                -18.0F, 0.0F, -12.0F,
                ProfessionalCosmeticModel.Box.of(
                        96, 32, -0.35F, -1.5F, -0.35F,
                        0.7F, 1.7F, 0.7F, 0.02F),
                ProfessionalCosmeticModel.Box.of(
                        112, 32, -0.2F, -2.2F, -0.2F,
                        0.4F, 0.8F, 0.4F, 0.01F)
        );
        builder.bone(
                CosmeticAnchor.RIGHT_ARM, "right_arm_crystal", "right_corrupt_arm",
                1.9F, 2.4F, -2.15F,
                -18.0F, 0.0F, 12.0F,
                ProfessionalCosmeticModel.Box.of(
                        96, 48, -0.35F, -1.5F, -0.35F,
                        0.7F, 1.7F, 0.7F, 0.02F),
                ProfessionalCosmeticModel.Box.of(
                        112, 48, -0.2F, -2.2F, -0.2F,
                        0.4F, 0.8F, 0.4F, 0.01F)
        );
        return builder.bake(true);
    }

    private static ProfessionalCosmeticModel bingoVoidWings() {
        ProfessionalCosmeticModel.Builder builder = new ProfessionalCosmeticModel.Builder("bingo_void_wings", 128, 128);
        builder.bone(
                CosmeticAnchor.BODY, "void_wing_left_base", null,
                -3.0F, 2.0F, 2.4F,
                -4.0F, 14.0F, -8.0F,
                ProfessionalCosmeticModel.Box.of(
                        0, 0, -10.0F, -0.8F, -0.8F,
                        10.2F, 1.6F, 1.6F, 0.1F),
                ProfessionalCosmeticModel.Box.of(
                        32, 0, -9.4F, 0.4F, -0.5F,
                        8.5F, 1.0F, 1.0F, 0.07F),
                ProfessionalCosmeticModel.Box.of(
                        64, 0, -8.4F, 1.3F, -0.45F,
                        7.0F, 0.9F, 0.9F, 0.06F),
                ProfessionalCosmeticModel.Box.of(
                        96, 0, -6.9F, 2.2F, -0.4F,
                        5.0F, 0.8F, 0.8F, 0.05F)
        );
        builder.bone(
                CosmeticAnchor.BODY, "void_wing_left_crystals", null,
                -11.0F, 3.0F, 2.5F,
                0.0F, 0.0F, -10.0F,
                ProfessionalCosmeticModel.Box.of(
                        32, 32, -0.5F, -0.8F, -0.45F,
                        8.5F, 2.2F, 0.9F, 0.06F),
                ProfessionalCosmeticModel.Box.of(
                        64, 32, 1.0F, 1.0F, -0.4F,
                        6.8F, 2.0F, 0.8F, 0.05F),
                ProfessionalCosmeticModel.Box.of(
                        96, 32, 2.5F, 2.6F, -0.35F,
                        5.0F, 1.8F, 0.7F, 0.04F),
                ProfessionalCosmeticModel.Box.of(
                        0, 64, 4.4F, 4.0F, -0.3F,
                        3.0F, 1.5F, 0.6F, 0.03F)
        );
        builder.bone(
                CosmeticAnchor.BODY, "void_wing_left_spikes", null,
                -13.0F, 4.0F, 2.6F,
                0.0F, 0.0F, -18.0F,
                ProfessionalCosmeticModel.Box.of(
                        96, 32, -0.45F, -4.5F, -0.45F,
                        0.9F, 4.8F, 0.9F, 0.04F),
                ProfessionalCosmeticModel.Box.of(
                        0, 64, 2.4F, -3.8F, -0.4F,
                        0.8F, 4.2F, 0.8F, 0.03F),
                ProfessionalCosmeticModel.Box.of(
                        32, 64, 4.8F, -3.0F, -0.35F,
                        0.7F, 3.4F, 0.7F, 0.03F)
        );
        builder.bone(
                CosmeticAnchor.BODY, "void_wing_right_base", null,
                3.0F, 2.0F, 2.4F,
                -4.0F, -14.0F, 8.0F,
                ProfessionalCosmeticModel.Box.of(
                        0, 0, -0.2F, -0.8F, -0.8F,
                        10.2F, 1.6F, 1.6F, 0.1F),
                ProfessionalCosmeticModel.Box.of(
                        32, 0, 0.9F, 0.4F, -0.5F,
                        8.5F, 1.0F, 1.0F, 0.07F),
                ProfessionalCosmeticModel.Box.of(
                        64, 0, 1.4F, 1.3F, -0.45F,
                        7.0F, 0.9F, 0.9F, 0.06F),
                ProfessionalCosmeticModel.Box.of(
                        96, 0, 1.9F, 2.2F, -0.4F,
                        5.0F, 0.8F, 0.8F, 0.05F)
        );
        builder.bone(
                CosmeticAnchor.BODY, "void_wing_right_crystals", null,
                11.0F, 3.0F, 2.5F,
                0.0F, 0.0F, 10.0F,
                ProfessionalCosmeticModel.Box.of(
                        32, 32, -8.0F, -0.8F, -0.45F,
                        8.5F, 2.2F, 0.9F, 0.06F),
                ProfessionalCosmeticModel.Box.of(
                        64, 32, -7.8F, 1.0F, -0.4F,
                        6.8F, 2.0F, 0.8F, 0.05F),
                ProfessionalCosmeticModel.Box.of(
                        96, 32, -7.5F, 2.6F, -0.35F,
                        5.0F, 1.8F, 0.7F, 0.04F),
                ProfessionalCosmeticModel.Box.of(
                        0, 64, -7.4F, 4.0F, -0.3F,
                        3.0F, 1.5F, 0.6F, 0.03F)
        );
        builder.bone(
                CosmeticAnchor.BODY, "void_wing_right_spikes", null,
                13.0F, 4.0F, 2.6F,
                0.0F, 0.0F, 18.0F,
                ProfessionalCosmeticModel.Box.of(
                        96, 32, -0.45F, -4.5F, -0.45F,
                        0.9F, 4.8F, 0.9F, 0.04F),
                ProfessionalCosmeticModel.Box.of(
                        0, 64, -3.2F, -3.8F, -0.4F,
                        0.8F, 4.2F, 0.8F, 0.03F),
                ProfessionalCosmeticModel.Box.of(
                        32, 64, -5.5F, -3.0F, -0.35F,
                        0.7F, 3.4F, 0.7F, 0.03F)
        );
        return builder.bake(true);
    }

    private static ProfessionalCosmeticModel bingoAmethystEye() {
        ProfessionalCosmeticModel.Builder builder = new ProfessionalCosmeticModel.Builder("bingo_amethyst_eye", 128, 128);
        builder.bone(
                CosmeticAnchor.BODY, "eye_orb", null,
                13.650F, -10.450F, -3.450F,
                0.0F, 0.0F, 0.0F,
                ProfessionalCosmeticModel.Box.of(
                        32, 0, -2.25F, -2.25F, -1.9F,
                        4.5F, 4.5F, 3.8F, 0.12F),
                ProfessionalCosmeticModel.Box.of(
                        0, 64, -1.8F, -1.8F, -2.2F,
                        3.6F, 3.6F, 0.58F, 0.04F),
                ProfessionalCosmeticModel.Box.of(
                        32, 32, -1.16F, -1.16F, -2.4F,
                        2.32F, 2.32F, 0.44F, 0.03F),
                ProfessionalCosmeticModel.Box.of(
                        96, 96, -0.5F, -0.5F, -2.55F,
                        1.0F, 1.0F, 0.34F, 0.025F)
        );
        builder.bone(
                CosmeticAnchor.BODY, "eye_crystal_top", "eye_orb",
                0.0F, -2.0F, 0.1F,
                0.0F, 0.0F, 0.0F,
                ProfessionalCosmeticModel.Box.of(
                        0, 32, -0.65F, -3.0F, -0.65F,
                        1.3F, 3.2F, 1.3F, 0.04F),
                ProfessionalCosmeticModel.Box.of(
                        64, 32, -0.36F, -4.15F, -0.36F,
                        0.72F, 1.3F, 0.72F, 0.02F)
        );
        builder.bone(
                CosmeticAnchor.BODY, "eye_crystal_left", "eye_orb",
                -1.9F, 0.0F, 0.1F,
                0.0F, 0.0F, -55.0F,
                ProfessionalCosmeticModel.Box.of(
                        96, 0, -0.55F, -2.3F, -0.55F,
                        1.1F, 2.5F, 1.1F, 0.03F),
                ProfessionalCosmeticModel.Box.of(
                        32, 32, -0.28F, -3.2F, -0.28F,
                        0.56F, 1.1F, 0.56F, 0.015F)
        );
        builder.bone(
                CosmeticAnchor.BODY, "eye_crystal_right", "eye_orb",
                1.9F, 0.0F, 0.1F,
                0.0F, 0.0F, 55.0F,
                ProfessionalCosmeticModel.Box.of(
                        96, 0, -0.55F, -2.3F, -0.55F,
                        1.1F, 2.5F, 1.1F, 0.03F),
                ProfessionalCosmeticModel.Box.of(
                        32, 32, -0.28F, -3.2F, -0.28F,
                        0.56F, 1.1F, 0.56F, 0.015F)
        );
        builder.bone(
                CosmeticAnchor.BODY, "eye_tendril_one", "eye_orb",
                -1.0F, 2.0F, 0.6F,
                25.0F, 0.0F, -20.0F,
                ProfessionalCosmeticModel.Box.of(
                        64, 0, -0.22F, -0.2F, -0.22F,
                        0.44F, 4.0F, 0.44F, 0.02F)
        );
        builder.bone(
                CosmeticAnchor.BODY, "eye_tendril_two", "eye_orb",
                1.0F, 2.0F, 0.6F,
                25.0F, 0.0F, 20.0F,
                ProfessionalCosmeticModel.Box.of(
                        64, 0, -0.22F, -0.2F, -0.22F,
                        0.44F, 4.0F, 0.44F, 0.02F)
        );
        return builder.bake(true);
    }

    private static ProfessionalCosmeticModel bingoEmeraldOrbit() {
        ProfessionalCosmeticModel.Builder builder = new ProfessionalCosmeticModel.Builder("bingo_emerald_orbit", 128, 128);
        builder.bone(
                CosmeticAnchor.ROOT, "orbit_root", null,
                0.0F, 10.0F, 0.0F,
                0.0F, 0.0F, 0.0F
        );
        builder.bone(
                CosmeticAnchor.ROOT, "gem_core", null,
                0.0F, 10.0F, 0.0F,
                0.0F, 0.0F, 0.0F,
                ProfessionalCosmeticModel.Box.of(
                        32, 0, 10.36F, -2.46F, -1.64F,
                        3.28F, 4.92F, 3.28F, 0.0984F),
                ProfessionalCosmeticModel.Box.of(
                        64, 0, 9.54F, -1.394F, -1.23F,
                        4.92F, 2.788F, 2.46F, 0.0656F),
                ProfessionalCosmeticModel.Box.of(
                        96, 0, 10.934F, -3.362F, -0.984F,
                        2.132F, 1.148F, 1.968F, 0.0492F),
                ProfessionalCosmeticModel.Box.of(
                        96, 0, 10.934F, 2.46F, -0.984F,
                        2.132F, 1.148F, 1.968F, 0.0492F),
                ProfessionalCosmeticModel.Box.of(
                        0, 32, 11.016F, -1.312F, -1.927F,
                        1.968F, 2.624F, 0.369F, 0.0246F)
        );
        builder.bone(
                CosmeticAnchor.ROOT, "corrupt_ring", null,
                9.8F, 10.0F, 0.0F,
                65.0F, 0.0F, 0.0F,
                ProfessionalCosmeticModel.Box.of(
                        64, 32, -3.744F, -0.273F, -0.273F,
                        7.488F, 0.546F, 0.546F, 0.0234F),
                ProfessionalCosmeticModel.Box.of(
                        64, 32, -0.273F, -3.744F, -0.273F,
                        0.546F, 7.488F, 0.546F, 0.0234F),
                ProfessionalCosmeticModel.Box.of(
                        96, 0, -2.652F, -2.652F, -0.234F,
                        0.507F, 0.507F, 0.468F, 0.0234F),
                ProfessionalCosmeticModel.Box.of(
                        96, 0, 2.145F, 2.145F, -0.234F,
                        0.507F, 0.507F, 0.468F, 0.0234F)
        );
        return builder.bake(true);
    }

    private static ProfessionalCosmeticModel bingoGoldOrbit() {
        ProfessionalCosmeticModel.Builder builder = new ProfessionalCosmeticModel.Builder("bingo_gold_orbit", 128, 128);
        builder.bone(
                CosmeticAnchor.ROOT, "gold_core", null,
                0.0F, 10.0F, 0.0F,
                0.0F, 0.0F, 0.0F,
                ProfessionalCosmeticModel.Box.of(
                        32, 0, 9.13F, -1.394F, -2.214F,
                        5.74F, 2.788F, 4.428F, 0.123F),
                ProfessionalCosmeticModel.Box.of(
                        64, 0, 9.786F, -1.968F, -1.722F,
                        4.428F, 0.738F, 3.444F, 0.0656F),
                ProfessionalCosmeticModel.Box.of(
                        96, 0, 9.786F, 1.23F, -1.722F,
                        4.428F, 0.738F, 3.444F, 0.0656F),
                ProfessionalCosmeticModel.Box.of(
                        0, 32, 10.524F, -0.738F, -2.501F,
                        2.952F, 1.476F, 0.41F, 0.0246F)
        );
        builder.bone(
                CosmeticAnchor.ROOT, "gold_flame_one", "gold_core",
                11.0F, -3.0F, 0.0F,
                0.0F, 0.0F, -15.0F,
                ProfessionalCosmeticModel.Box.of(
                        96, 0, -0.369F, -2.624F, -0.369F,
                        0.738F, 2.87F, 0.738F, 0.0328F),
                ProfessionalCosmeticModel.Box.of(
                        0, 32, -0.205F, -3.608F, -0.205F,
                        0.41F, 1.148F, 0.41F, 0.0164F)
        );
        builder.bone(
                CosmeticAnchor.ROOT, "gold_flame_two", "gold_core",
                14.0F, -2.0F, 0.0F,
                0.0F, 0.0F, 20.0F,
                ProfessionalCosmeticModel.Box.of(
                        64, 0, -0.328F, -1.968F, -0.328F,
                        0.656F, 2.214F, 0.656F, 0.0328F),
                ProfessionalCosmeticModel.Box.of(
                        0, 32, -0.164F, -2.706F, -0.164F,
                        0.328F, 0.902F, 0.328F, 0.0164F)
        );
        builder.bone(
                CosmeticAnchor.ROOT, "corrupt_ring", null,
                9.8F, 10.0F, 0.0F,
                70.0F, 0.0F, 25.0F,
                ProfessionalCosmeticModel.Box.of(
                        64, 32, -4.056F, -0.273F, -0.273F,
                        8.112F, 0.546F, 0.546F, 0.0234F),
                ProfessionalCosmeticModel.Box.of(
                        64, 32, -0.273F, -4.056F, -0.273F,
                        0.546F, 8.112F, 0.546F, 0.0234F)
        );
        return builder.bake(true);
    }

    private static ProfessionalCosmeticModel bingoAmethystOrbit() {
        ProfessionalCosmeticModel.Builder builder = new ProfessionalCosmeticModel.Builder("bingo_amethyst_orbit", 128, 128);
        builder.bone(
                CosmeticAnchor.ROOT, "amethyst_core", null,
                0.0F, 10.0F, 0.0F,
                0.0F, 0.0F, 0.0F,
                ProfessionalCosmeticModel.Box.of(
                        32, 0, 10.606F, -3.28F, -1.476F,
                        2.788F, 6.232F, 2.952F, 0.082F),
                ProfessionalCosmeticModel.Box.of(
                        64, 0, 9.868F, -1.476F, -2.05F,
                        4.264F, 3.28F, 4.1F, 0.0656F),
                ProfessionalCosmeticModel.Box.of(
                        96, 0, 11.016F, -4.674F, -0.984F,
                        1.968F, 1.64F, 1.968F, 0.041F),
                ProfessionalCosmeticModel.Box.of(
                        96, 0, 11.344F, 2.624F, -0.656F,
                        1.312F, 1.64F, 1.312F, 0.0328F),
                ProfessionalCosmeticModel.Box.of(
                        0, 32, 11.016F, -1.23F, -2.296F,
                        1.968F, 2.46F, 0.369F, 0.0246F)
        );
        builder.bone(
                CosmeticAnchor.ROOT, "shard_left", "amethyst_core",
                9.2F, 0.0F, 0.0F,
                0.0F, 0.0F, -28.0F,
                ProfessionalCosmeticModel.Box.of(
                        64, 0, -0.41F, -2.624F, -0.41F,
                        0.82F, 2.87F, 0.82F, 0.0328F),
                ProfessionalCosmeticModel.Box.of(
                        96, 0, -0.205F, -3.526F, -0.205F,
                        0.41F, 1.066F, 0.41F, 0.0164F)
        );
        builder.bone(
                CosmeticAnchor.ROOT, "shard_right", "amethyst_core",
                14.8F, 0.0F, 0.0F,
                0.0F, 0.0F, 28.0F,
                ProfessionalCosmeticModel.Box.of(
                        64, 0, -0.41F, -2.624F, -0.41F,
                        0.82F, 2.87F, 0.82F, 0.0328F),
                ProfessionalCosmeticModel.Box.of(
                        96, 0, -0.205F, -3.526F, -0.205F,
                        0.41F, 1.066F, 0.41F, 0.0164F)
        );
        builder.bone(
                CosmeticAnchor.ROOT, "corrupt_ring", null,
                9.8F, 10.0F, 0.0F,
                68.0F, 20.0F, 0.0F,
                ProfessionalCosmeticModel.Box.of(
                        64, 32, -3.9F, -0.273F, -0.273F,
                        7.8F, 0.546F, 0.546F, 0.0234F),
                ProfessionalCosmeticModel.Box.of(
                        64, 32, -0.273F, -3.9F, -0.273F,
                        0.546F, 7.8F, 0.546F, 0.0234F)
        );
        return builder.bake(true);
    }



    private static ProfessionalCosmeticModel ponyAxolotlCharm() {
        ProfessionalCosmeticModel.Builder b = new ProfessionalCosmeticModel.Builder("pony_axolotl_charm", 128, 128);
        b.bone(CosmeticAnchor.BODY, "charm_root", null, 0.0F, 4.7F, -2.42F, 0, 0, 0,
                ProfessionalCosmeticModel.Box.of(0, 0, -2.45F, -1.25F, -0.62F, 4.9F, 2.5F, 1.25F, 0.05F),
                ProfessionalCosmeticModel.Box.of(32, 0, -0.35F, -3.15F, -0.24F, 0.7F, 2.0F, 0.48F, 0.02F));
        b.bone(CosmeticAnchor.BODY, "axolotl_head", "charm_root", 0, -0.95F, -0.55F, 0, 0, 0,
                ProfessionalCosmeticModel.Box.of(32, 0, -2.15F, -1.75F, -0.75F, 4.3F, 3.25F, 1.5F, 0.06F),
                ProfessionalCosmeticModel.Box.of(96, 0, -1.15F, -0.45F, -0.92F, 0.55F, 0.55F, 0.18F, 0.01F),
                ProfessionalCosmeticModel.Box.of(96, 0, 0.60F, -0.45F, -0.92F, 0.55F, 0.55F, 0.18F, 0.01F),
                ProfessionalCosmeticModel.Box.of(64, 32, -0.45F, 0.45F, -0.94F, 0.9F, 0.20F, 0.18F, 0.01F));
        b.bone(CosmeticAnchor.BODY, "gill_left", "axolotl_head", -2.05F, -0.45F, 0, 0, 0, -12,
                ProfessionalCosmeticModel.Box.of(64, 0, -1.45F, -1.35F, -0.28F, 1.5F, 2.7F, 0.56F, 0.03F));
        b.bone(CosmeticAnchor.BODY, "gill_right", "axolotl_head", 2.05F, -0.45F, 0, 0, 0, 12,
                ProfessionalCosmeticModel.Box.of(64, 0, -0.05F, -1.35F, -0.28F, 1.5F, 2.7F, 0.56F, 0.03F));
        b.bone(CosmeticAnchor.BODY, "wink_lid", "axolotl_head", -0.88F, -0.48F, -0.96F, 0, 0, 0,
                ProfessionalCosmeticModel.Box.of(96, 32, -0.38F, -0.08F, -0.05F, 0.76F, 0.18F, 0.10F, 0.01F));
        b.bone(CosmeticAnchor.BODY, "arm_left", "charm_root", -1.55F, 0.55F, -0.45F, 0, 0, -20,
                ProfessionalCosmeticModel.Box.of(0, 32, -0.30F, -0.20F, -0.25F, 0.60F, 1.25F, 0.50F, 0.02F));
        b.bone(CosmeticAnchor.BODY, "arm_right", "charm_root", 1.55F, 0.55F, -0.45F, 0, 0, 20,
                ProfessionalCosmeticModel.Box.of(0, 32, -0.30F, -0.20F, -0.25F, 0.60F, 1.25F, 0.50F, 0.02F));
        return b.bake(true);
    }

    private static ProfessionalCosmeticModel ponyOrbit() {
        ProfessionalCosmeticModel.Builder b = new ProfessionalCosmeticModel.Builder("pony_orbit", 128, 128);
        b.bone(CosmeticAnchor.ROOT, "pony_orbit_root", null, 0, 12.5F, 0, 0, 0, 0);
        b.bone(CosmeticAnchor.ROOT, "pony_body", "pony_orbit_root", 8.2F, 0, 0, 0, 90, 0,
                ProfessionalCosmeticModel.Box.of(0, 0, -2.15F, -1.55F, -3.15F, 4.3F, 3.1F, 6.3F, 0.06F));
        b.bone(CosmeticAnchor.ROOT, "pony_head", "pony_body", 0, -1.65F, -3.0F, -8, 0, 0,
                ProfessionalCosmeticModel.Box.of(32, 0, -1.85F, -1.8F, -2.15F, 3.7F, 3.6F, 3.8F, 0.05F),
                ProfessionalCosmeticModel.Box.of(96, 0, -1.05F, -0.55F, -2.35F, 0.45F, 0.45F, 0.20F, 0.01F),
                ProfessionalCosmeticModel.Box.of(96, 0, 0.60F, -0.55F, -2.35F, 0.45F, 0.45F, 0.20F, 0.01F));
        b.bone(CosmeticAnchor.ROOT, "pony_ear_left", "pony_head", -1.1F, -1.65F, -0.25F, 0, 0, -12,
                ProfessionalCosmeticModel.Box.of(64, 0, -0.45F, -1.45F, -0.35F, 0.9F, 1.6F, 0.7F, 0.02F));
        b.bone(CosmeticAnchor.ROOT, "pony_ear_right", "pony_head", 1.1F, -1.65F, -0.25F, 0, 0, 12,
                ProfessionalCosmeticModel.Box.of(64, 0, -0.45F, -1.45F, -0.35F, 0.9F, 1.6F, 0.7F, 0.02F));
        b.bone(CosmeticAnchor.ROOT, "pony_mane", "pony_head", 0, -0.6F, 1.45F, 8, 0, 0,
                ProfessionalCosmeticModel.Box.of(64, 32, -0.65F, -1.7F, -0.20F, 1.3F, 3.4F, 1.0F, 0.03F));
        b.bone(CosmeticAnchor.ROOT, "pony_tail", "pony_body", 0, -0.4F, 3.0F, 15, 0, 0,
                ProfessionalCosmeticModel.Box.of(0, 64, -0.75F, -0.8F, -0.15F, 1.5F, 1.6F, 4.5F, 0.04F));
        b.bone(CosmeticAnchor.ROOT, "pony_leg_left", "pony_body", -1.25F, 1.35F, -1.55F, 0, 0, 0,
                ProfessionalCosmeticModel.Box.of(32, 32, -0.48F, 0, -0.48F, 0.96F, 2.8F, 0.96F, 0.03F));
        b.bone(CosmeticAnchor.ROOT, "pony_leg_right", "pony_body", 1.25F, 1.35F, 1.35F, 0, 0, 0,
                ProfessionalCosmeticModel.Box.of(32, 32, -0.48F, 0, -0.48F, 0.96F, 2.8F, 0.96F, 0.03F));
        return b.bake(true);
    }

    private static ProfessionalCosmeticModel ponyAxolotlSlippers() {
        ProfessionalCosmeticModel.Builder b = new ProfessionalCosmeticModel.Builder("pony_axolotl_slippers", 128, 128);
        slipperAxolotl(b, CosmeticAnchor.LEFT_LEG, "left", 0, false);
        slipperAxolotl(b, CosmeticAnchor.RIGHT_LEG, "right", 32, true);
        return b.bake(true);
    }

    private static void slipperAxolotl(ProfessionalCosmeticModel.Builder b, CosmeticAnchor anchor, String side, int u, boolean mirror) {
        b.bone(anchor, side + "_slipper", null, 0, 9.05F, -0.35F, 0, 0, 0,
                ProfessionalCosmeticModel.Box.of(u, 0, -2.15F, -0.25F, -3.25F, 4.3F, 3.25F, 5.1F, 0.08F),
                ProfessionalCosmeticModel.Box.of(96, 0, -1.12F, 0.65F, -3.48F, 0.48F, 0.48F, 0.18F, 0.01F),
                ProfessionalCosmeticModel.Box.of(96, 0, 0.64F, 0.65F, -3.48F, 0.48F, 0.48F, 0.18F, 0.01F));
        b.bone(anchor, side + "_gill_left", side + "_slipper", -2.0F, 0.75F, -1.9F, 0, 0, -18,
                ProfessionalCosmeticModel.Box.of(64, 0, -1.1F, -0.75F, -0.25F, 1.2F, 1.5F, 0.5F, 0.02F));
        b.bone(anchor, side + "_gill_right", side + "_slipper", 2.0F, 0.75F, -1.9F, 0, 0, 18,
                ProfessionalCosmeticModel.Box.of(64, 0, -0.1F, -0.75F, -0.25F, 1.2F, 1.5F, 0.5F, 0.02F));
    }

    private static ProfessionalCosmeticModel somitaVampireTail() {
        ProfessionalCosmeticModel.Builder b = new ProfessionalCosmeticModel.Builder("somita_vampire_tail", 128, 128);
        b.bone(CosmeticAnchor.BODY, "vampire_tail_base", null, 0, 8.7F, 2.1F, 14, 0, 0,
                ProfessionalCosmeticModel.Box.of(0, 0, -0.75F, -0.75F, -0.15F, 1.5F, 1.5F, 5.2F, 0.05F));
        b.bone(CosmeticAnchor.BODY, "vampire_tail_mid", "vampire_tail_base", 0, 0, 4.7F, 8, 0, 0,
                ProfessionalCosmeticModel.Box.of(32, 0, -0.58F, -0.58F, -0.15F, 1.16F, 1.16F, 4.8F, 0.04F));
        b.bone(CosmeticAnchor.BODY, "vampire_tail_tip", "vampire_tail_mid", 0, 0, 4.3F, -12, 0, 0,
                ProfessionalCosmeticModel.Box.of(64, 0, -0.42F, -0.42F, -0.15F, 0.84F, 0.84F, 3.7F, 0.03F));
        b.bone(CosmeticAnchor.BODY, "vampire_heart_tip", "vampire_tail_tip", 0, 0, 3.45F, 0, 0, 0,
                ProfessionalCosmeticModel.Box.of(96, 0, -1.15F, -1.05F, -0.25F, 2.3F, 2.1F, 0.7F, 0.04F),
                ProfessionalCosmeticModel.Box.of(96, 32, -0.45F, 0.65F, -0.22F, 0.9F, 1.25F, 0.64F, 0.03F));
        return b.bake(true);
    }

    private static ProfessionalCosmeticModel somitaNumbersOrbit() {
        ProfessionalCosmeticModel.Builder b = new ProfessionalCosmeticModel.Builder("somita_numbers_orbit", 128, 128);
        b.bone(CosmeticAnchor.ROOT, "numbers_orbit_root", null, 0, 2.1F, 0, 0, 0, 0);
        // El 6 y el 7 comparten el mismo hueso: orbitan como una sola insignia "67".
        b.bone(CosmeticAnchor.ROOT, "number_pair", "numbers_orbit_root", 12.8F, -0.3F, 0, 0, 0, 0);
        b.bone(CosmeticAnchor.ROOT, "number_six", "number_pair", -1.55F, 0, 0, 0, 0, -4,
                ProfessionalCosmeticModel.Box.of(0, 0, -1.35F, -2.55F, -0.45F, 0.62F, 5.1F, 0.9F, 0.03F),
                ProfessionalCosmeticModel.Box.of(0, 0, -0.86F, -2.55F, -0.45F, 2.05F, 0.62F, 0.9F, 0.03F),
                ProfessionalCosmeticModel.Box.of(0, 0, -0.86F, -0.31F, -0.45F, 2.05F, 0.62F, 0.9F, 0.03F),
                ProfessionalCosmeticModel.Box.of(0, 0, -0.86F, 1.93F, -0.45F, 2.05F, 0.62F, 0.9F, 0.03F),
                ProfessionalCosmeticModel.Box.of(0, 0, 0.55F, -0.02F, -0.45F, 0.62F, 2.2F, 0.9F, 0.03F));
        b.bone(CosmeticAnchor.ROOT, "number_seven", "number_pair", 1.55F, 0, 0, 0, 0, 3,
                ProfessionalCosmeticModel.Box.of(32, 0, -1.35F, -2.55F, -0.45F, 2.75F, 0.62F, 0.9F, 0.03F));
        b.bone(CosmeticAnchor.ROOT, "seven_diagonal", "number_seven", 0.62F, -1.95F, 0, 0, 0, 18,
                ProfessionalCosmeticModel.Box.of(32, 0, -0.32F, 0, -0.45F, 0.64F, 4.75F, 0.9F, 0.03F));
        b.bone(CosmeticAnchor.ROOT, "number_star", "number_pair", 0, -3.65F, 0, 0, 0, 0,
                ProfessionalCosmeticModel.Box.of(96, 0, -0.48F, -0.48F, -0.28F, 0.96F, 0.96F, 0.56F, 0.02F));
        return b.bake(true);
    }

    private static ProfessionalCosmeticModel somitaBatSlippers() {
        ProfessionalCosmeticModel.Builder b = new ProfessionalCosmeticModel.Builder("somita_bat_slippers", 128, 128);
        batSlipper(b, CosmeticAnchor.LEFT_LEG, "left", 0);
        batSlipper(b, CosmeticAnchor.RIGHT_LEG, "right", 32);
        return b.bake(true);
    }

    private static void batSlipper(ProfessionalCosmeticModel.Builder b, CosmeticAnchor anchor, String side, int u) {
        b.bone(anchor, side + "_bat_slipper", null, 0, 9.05F, -0.35F, 0, 0, 0,
                ProfessionalCosmeticModel.Box.of(u, 0, -2.15F, -0.25F, -3.2F, 4.3F, 3.25F, 5.0F, 0.08F),
                ProfessionalCosmeticModel.Box.of(96, 0, -1.15F, 0.65F, -3.42F, 0.52F, 0.52F, 0.18F, 0.01F),
                ProfessionalCosmeticModel.Box.of(96, 0, 0.63F, 0.65F, -3.42F, 0.52F, 0.52F, 0.18F, 0.01F));
        b.bone(anchor, side + "_bat_ear_left", side + "_bat_slipper", -1.2F, -0.15F, -1.85F, 0, 0, -10,
                ProfessionalCosmeticModel.Box.of(64, 0, -0.5F, -1.45F, -0.3F, 1.0F, 1.55F, 0.6F, 0.02F));
        b.bone(anchor, side + "_bat_ear_right", side + "_bat_slipper", 1.2F, -0.15F, -1.85F, 0, 0, 10,
                ProfessionalCosmeticModel.Box.of(64, 0, -0.5F, -1.45F, -0.3F, 1.0F, 1.55F, 0.6F, 0.02F));
        b.bone(anchor, side + "_bat_wing_left", side + "_bat_slipper", -2.0F, 1.1F, -0.7F, 0, 0, -18,
                ProfessionalCosmeticModel.Box.of(64, 32, -1.4F, -0.65F, -0.18F, 1.45F, 1.7F, 0.36F, 0.02F));
        b.bone(anchor, side + "_bat_wing_right", side + "_bat_slipper", 2.0F, 1.1F, -0.7F, 0, 0, 18,
                ProfessionalCosmeticModel.Box.of(64, 32, -0.05F, -0.65F, -0.18F, 1.45F, 1.7F, 0.36F, 0.02F));
    }


    private static ProfessionalCosmeticModel mobChibiCreeper() {
        ProfessionalCosmeticModel.Builder b = new ProfessionalCosmeticModel.Builder("mobchibi_creeper", 128, 128);
        b.bone(CosmeticAnchor.ROOT, "creeper_root", null, 9.0F, 20.8F, 4.4F, 0, -16, 0);
        b.bone(CosmeticAnchor.ROOT, "creeper_body", "creeper_root", 0, -3.0F, 0, 0, 0, 0,
                ProfessionalCosmeticModel.Box.of(0, 0, -2.0F, -3.0F, -1.65F, 4.0F, 6.0F, 3.3F, 0.08F),
                ProfessionalCosmeticModel.Box.of(0, 32, -1.55F, 1.25F, -1.80F, 3.1F, 1.25F, 3.6F, 0.03F));
        b.bone(CosmeticAnchor.ROOT, "creeper_head", "creeper_body", 0, -3.0F, 0, 0, 0, 0,
                ProfessionalCosmeticModel.Box.of(32, 0, -2.85F, -2.65F, -2.65F, 5.7F, 5.3F, 5.3F, 0.08F),
                ProfessionalCosmeticModel.Box.of(96, 0, -1.95F, -0.65F, -2.92F, 0.90F, 0.90F, 0.20F, 0.01F),
                ProfessionalCosmeticModel.Box.of(96, 0, 1.05F, -0.65F, -2.92F, 0.90F, 0.90F, 0.20F, 0.01F),
                ProfessionalCosmeticModel.Box.of(96, 32, -1.05F, 0.40F, -2.94F, 2.1F, 1.65F, 0.20F, 0.01F));
        b.bone(CosmeticAnchor.ROOT, "creeper_brow_left", "creeper_head", -1.45F, -1.25F, -2.7F, 0, 0, -8,
                ProfessionalCosmeticModel.Box.of(64, 64, -0.80F, -0.18F, -0.14F, 1.6F, 0.36F, 0.28F, 0.01F));
        b.bone(CosmeticAnchor.ROOT, "creeper_brow_right", "creeper_head", 1.45F, -1.25F, -2.7F, 0, 0, 8,
                ProfessionalCosmeticModel.Box.of(64, 64, -0.80F, -0.18F, -0.14F, 1.6F, 0.36F, 0.28F, 0.01F));
        for (int i = 0; i < 4; i++) {
            float x = i < 2 ? -1.35F : 1.35F;
            float z = (i % 2 == 0) ? -1.05F : 1.05F;
            b.bone(CosmeticAnchor.ROOT, "creeper_leg_" + i, "creeper_body", x, 2.35F, z, 0, 0, 0,
                    ProfessionalCosmeticModel.Box.of(64, 0, -0.75F, 0, -0.75F, 1.5F, 2.35F, 1.5F, 0.04F));
        }
        return b.bake(true);
    }


    private static ProfessionalCosmeticModel mobChibiEnderman() {
        ProfessionalCosmeticModel.Builder b = new ProfessionalCosmeticModel.Builder("mobchibi_enderman", 128, 128);
        b.bone(CosmeticAnchor.ROOT, "enderman_root", null, 9.0F, 20.2F, 4.2F, 0, -18, 0);
        b.bone(CosmeticAnchor.ROOT, "enderman_body", "enderman_root", 0, -4.0F, 0, 4, 0, 0,
                ProfessionalCosmeticModel.Box.of(0, 0, -1.65F, -3.0F, -1.05F, 3.3F, 6.0F, 2.1F, 0.05F),
                ProfessionalCosmeticModel.Box.of(0, 32, -1.95F, -2.15F, -1.18F, 3.9F, 1.15F, 2.36F, 0.025F));
        b.bone(CosmeticAnchor.ROOT, "enderman_head", "enderman_body", 0, -3.05F, -0.1F, -4, 0, 0,
                ProfessionalCosmeticModel.Box.of(32, 0, -2.85F, -2.60F, -2.25F, 5.7F, 5.2F, 4.5F, 0.07F),
                ProfessionalCosmeticModel.Box.of(96, 0, -2.20F, -0.30F, -2.52F, 1.55F, 0.52F, 0.20F, 0.01F),
                ProfessionalCosmeticModel.Box.of(96, 0, 0.65F, -0.30F, -2.52F, 1.55F, 0.52F, 0.20F, 0.01F));
        b.bone(CosmeticAnchor.ROOT, "enderman_arm_left", "enderman_body", -2.0F, -2.1F, 0, 8, 0, -9,
                ProfessionalCosmeticModel.Box.of(64, 0, -0.58F, 0, -0.58F, 1.16F, 5.8F, 1.16F, 0.04F));
        b.bone(CosmeticAnchor.ROOT, "enderman_arm_right", "enderman_body", 2.0F, -2.1F, 0, 8, 0, 9,
                ProfessionalCosmeticModel.Box.of(64, 0, -0.58F, 0, -0.58F, 1.16F, 5.8F, 1.16F, 0.04F));
        b.bone(CosmeticAnchor.ROOT, "enderman_hand_left", "enderman_arm_left", 0, 5.25F, 0, 0, 0, 0,
                ProfessionalCosmeticModel.Box.of(96, 32, -0.72F, -0.15F, -0.72F, 1.44F, 1.20F, 1.44F, 0.03F));
        b.bone(CosmeticAnchor.ROOT, "enderman_hand_right", "enderman_arm_right", 0, 5.25F, 0, 0, 0, 0,
                ProfessionalCosmeticModel.Box.of(96, 32, -0.72F, -0.15F, -0.72F, 1.44F, 1.20F, 1.44F, 0.03F));
        b.bone(CosmeticAnchor.ROOT, "enderman_leg_left", "enderman_body", -0.95F, 2.45F, 0, 0, 0, 0,
                ProfessionalCosmeticModel.Box.of(0, 32, -0.55F, 0, -0.55F, 1.10F, 4.35F, 1.10F, 0.04F));
        b.bone(CosmeticAnchor.ROOT, "enderman_leg_right", "enderman_body", 0.95F, 2.45F, 0, 0, 0, 0,
                ProfessionalCosmeticModel.Box.of(0, 32, -0.55F, 0, -0.55F, 1.10F, 4.35F, 1.10F, 0.04F));
        return b.bake(true);
    }


    private static ProfessionalCosmeticModel mobChibiBee() {
        ProfessionalCosmeticModel.Builder b = new ProfessionalCosmeticModel.Builder("mobchibi_bee", 128, 128);
        b.bone(CosmeticAnchor.ROOT, "bee_root", null, 8.8F, 14.8F, 4.8F, 0, -18, 0);
        b.bone(CosmeticAnchor.ROOT, "bee_body", "bee_root", 0, 0, 0, 0, 0, 0,
                ProfessionalCosmeticModel.Box.of(0, 0, -2.75F, -2.2F, -3.35F, 5.5F, 4.4F, 6.7F, 0.08F),
                ProfessionalCosmeticModel.Box.of(0, 32, -2.85F, -1.1F, -0.55F, 5.7F, 1.05F, 1.1F, 0.02F),
                ProfessionalCosmeticModel.Box.of(32, 32, -2.85F, 0.65F, 0.80F, 5.7F, 1.05F, 1.1F, 0.02F),
                ProfessionalCosmeticModel.Box.of(96, 0, -1.60F, -0.55F, -3.68F, 0.75F, 0.75F, 0.20F, 0.01F),
                ProfessionalCosmeticModel.Box.of(96, 0, 0.85F, -0.55F, -3.68F, 0.75F, 0.75F, 0.20F, 0.01F));
        b.bone(CosmeticAnchor.ROOT, "bee_antenna_left", "bee_body", -1.15F, -2.0F, -2.55F, -18, 0, -12,
                ProfessionalCosmeticModel.Box.of(96, 32, -0.18F, -1.8F, -0.18F, 0.36F, 2.0F, 0.36F, 0.01F));
        b.bone(CosmeticAnchor.ROOT, "bee_antenna_right", "bee_body", 1.15F, -2.0F, -2.55F, -18, 0, 12,
                ProfessionalCosmeticModel.Box.of(96, 32, -0.18F, -1.8F, -0.18F, 0.36F, 2.0F, 0.36F, 0.01F));
        b.bone(CosmeticAnchor.ROOT, "bee_wing_left", "bee_body", -2.25F, -1.65F, 0.25F, 0, 0, -22,
                ProfessionalCosmeticModel.Box.of(64, 0, -3.5F, -0.25F, -0.22F, 3.6F, 4.7F, 0.44F, 0.025F));
        b.bone(CosmeticAnchor.ROOT, "bee_wing_right", "bee_body", 2.25F, -1.65F, 0.25F, 0, 0, 22,
                ProfessionalCosmeticModel.Box.of(64, 0, -0.1F, -0.25F, -0.22F, 3.6F, 4.7F, 0.44F, 0.025F));
        b.bone(CosmeticAnchor.ROOT, "bee_stinger", "bee_body", 0, 0, 3.15F, 0, 0, 0,
                ProfessionalCosmeticModel.Box.of(64, 32, -0.42F, -0.42F, -0.1F, 0.84F, 0.84F, 1.8F, 0.02F));
        for (int i = 0; i < 3; i++) {
            float z = -1.6F + i * 1.6F;
            b.bone(CosmeticAnchor.ROOT, "bee_leg_left_" + i, "bee_body", -2.15F, 1.35F, z, 25, 0, -22,
                    ProfessionalCosmeticModel.Box.of(96, 64, -1.45F, -0.18F, -0.18F, 1.55F, 0.36F, 0.36F, 0.01F));
            b.bone(CosmeticAnchor.ROOT, "bee_leg_right_" + i, "bee_body", 2.15F, 1.35F, z, 25, 0, 22,
                    ProfessionalCosmeticModel.Box.of(96, 64, -0.10F, -0.18F, -0.18F, 1.55F, 0.36F, 0.36F, 0.01F));
        }
        return b.bake(true);
    }


    private static ProfessionalCosmeticModel mobChibiAxolotl() {
        ProfessionalCosmeticModel.Builder b = new ProfessionalCosmeticModel.Builder("mobchibi_axolotl", 128, 128);
        b.bone(CosmeticAnchor.ROOT, "pet_axolotl_root", null, 8.8F, 20.4F, 4.2F, 0, -18, 0);
        b.bone(CosmeticAnchor.ROOT, "pet_axolotl_body", "pet_axolotl_root", 0, -2.0F, 0, 0, 0, 0,
                ProfessionalCosmeticModel.Box.of(0, 0, -2.0F, -1.4F, -3.6F, 4.0F, 2.8F, 7.2F, 0.06F),
                ProfessionalCosmeticModel.Box.of(0, 32, -1.25F, 0.85F, -2.8F, 2.5F, 0.45F, 5.6F, 0.02F));
        b.bone(CosmeticAnchor.ROOT, "pet_axolotl_head", "pet_axolotl_body", 0, -0.35F, -3.25F, 0, 0, 0,
                ProfessionalCosmeticModel.Box.of(32, 0, -3.05F, -2.35F, -2.2F, 6.1F, 4.7F, 4.4F, 0.09F),
                ProfessionalCosmeticModel.Box.of(96, 0, -1.65F, -0.35F, -2.45F, 0.65F, 0.65F, 0.20F, 0.01F),
                ProfessionalCosmeticModel.Box.of(96, 0, 1.00F, -0.35F, -2.45F, 0.65F, 0.65F, 0.20F, 0.01F),
                ProfessionalCosmeticModel.Box.of(96, 32, -0.75F, 0.75F, -2.47F, 1.5F, 0.28F, 0.20F, 0.01F));
        b.bone(CosmeticAnchor.ROOT, "pet_gill_left", "pet_axolotl_head", -2.75F, -0.35F, 0, 0, 0, -8,
                ProfessionalCosmeticModel.Box.of(64, 0, -2.15F, -1.75F, -0.45F, 2.2F, 3.5F, 0.9F, 0.04F));
        b.bone(CosmeticAnchor.ROOT, "pet_gill_right", "pet_axolotl_head", 2.75F, -0.35F, 0, 0, 0, 8,
                ProfessionalCosmeticModel.Box.of(64, 0, -0.05F, -1.75F, -0.45F, 2.2F, 3.5F, 0.9F, 0.04F));
        for (int side = -1; side <= 1; side += 2) {
            String s = side < 0 ? "left" : "right";
            b.bone(CosmeticAnchor.ROOT, "pet_axolotl_front_" + s, "pet_axolotl_body", side * 1.55F, 0.95F, -2.15F, 12, 0, side * 10,
                    ProfessionalCosmeticModel.Box.of(64, 32, -0.45F, 0, -0.45F, 0.9F, 1.8F, 0.9F, 0.03F));
            b.bone(CosmeticAnchor.ROOT, "pet_axolotl_back_" + s, "pet_axolotl_body", side * 1.55F, 0.95F, 1.65F, -8, 0, side * 10,
                    ProfessionalCosmeticModel.Box.of(64, 32, -0.45F, 0, -0.45F, 0.9F, 1.8F, 0.9F, 0.03F));
        }
        b.bone(CosmeticAnchor.ROOT, "pet_axolotl_tail", "pet_axolotl_body", 0, 0, 3.15F, 0, 0, 0,
                ProfessionalCosmeticModel.Box.of(0, 32, -0.70F, -1.8F, -0.2F, 1.4F, 3.6F, 5.6F, 0.04F));
        b.bone(CosmeticAnchor.ROOT, "pet_axolotl_tail_fin", "pet_axolotl_tail", 0, 0, 4.6F, 0, 0, 0,
                ProfessionalCosmeticModel.Box.of(32, 32, -0.28F, -2.2F, -0.1F, 0.56F, 4.4F, 3.2F, 0.02F));
        return b.bake(true);
    }

    private static ProfessionalCosmeticModel mobChibiSlime() {
        ProfessionalCosmeticModel.Builder b = new ProfessionalCosmeticModel.Builder("mobchibi_slime",128,128);
        b.bone(CosmeticAnchor.ROOT,"slime_root",null,8.8F,20.4F,4.0F,0,-18,0);
        b.bone(CosmeticAnchor.ROOT,"slime_body","slime_root",0,-2.5F,0,0,0,0,
                ProfessionalCosmeticModel.Box.of(0,0,-3.0F,-3.0F,-3.0F,6.0F,6.0F,6.0F,0.04F),
                ProfessionalCosmeticModel.Box.of(96,0,-1.65F,-0.65F,-3.33F,0.75F,0.75F,0.18F,0.01F),
                ProfessionalCosmeticModel.Box.of(96,0,0.90F,-0.65F,-3.33F,0.75F,0.75F,0.18F,0.01F),
                ProfessionalCosmeticModel.Box.of(64,32,-0.75F,0.65F,-3.35F,1.5F,0.35F,0.18F,0.01F));
        b.bone(CosmeticAnchor.ROOT,"slime_core","slime_body",0,0,0,0,0,0,
                ProfessionalCosmeticModel.Box.of(32,0,-1.7F,-1.7F,-1.7F,3.4F,3.4F,3.4F,0.02F));
        return b.bake(true);
    }


    private static ProfessionalCosmeticModel mobChibiWarden() {
        ProfessionalCosmeticModel.Builder b = new ProfessionalCosmeticModel.Builder("mobchibi_warden", 128, 128);
        b.bone(CosmeticAnchor.ROOT, "warden_root", null, 9.0F, 20.3F, 4.3F, 0, -18, 0);
        b.bone(CosmeticAnchor.ROOT, "warden_body", "warden_root", 0, -3.7F, 0, 0, 0, 0,
                ProfessionalCosmeticModel.Box.of(0, 0, -3.2F, -3.4F, -2.15F, 6.4F, 6.8F, 4.3F, 0.08F),
                ProfessionalCosmeticModel.Box.of(96, 32, -1.45F, -0.15F, -2.35F, 2.9F, 2.15F, 0.25F, 0.01F),
                ProfessionalCosmeticModel.Box.of(64, 64, -0.65F, 0.25F, -2.42F, 1.3F, 1.15F, 0.18F, 0.01F));
        b.bone(CosmeticAnchor.ROOT, "warden_head", "warden_body", 0, -3.25F, 0, 0, 0, 0,
                ProfessionalCosmeticModel.Box.of(32, 0, -3.0F, -2.35F, -2.35F, 6.0F, 4.7F, 4.7F, 0.10F));
        b.bone(CosmeticAnchor.ROOT, "warden_horn_left", "warden_head", -2.55F, -1.0F, 0, 0, 0, -18,
                ProfessionalCosmeticModel.Box.of(64, 0, -2.8F, -0.42F, -0.42F, 2.9F, 0.84F, 0.84F, 0.04F));
        b.bone(CosmeticAnchor.ROOT, "warden_horn_left_tip", "warden_horn_left", -2.45F, 0, 0, 0, 0, -14,
                ProfessionalCosmeticModel.Box.of(96, 0, -2.2F, -0.32F, -0.32F, 2.3F, 0.64F, 0.64F, 0.03F));
        b.bone(CosmeticAnchor.ROOT, "warden_horn_right", "warden_head", 2.55F, -1.0F, 0, 0, 0, 18,
                ProfessionalCosmeticModel.Box.of(64, 0, -0.1F, -0.42F, -0.42F, 2.9F, 0.84F, 0.84F, 0.04F));
        b.bone(CosmeticAnchor.ROOT, "warden_horn_right_tip", "warden_horn_right", 2.45F, 0, 0, 0, 0, 14,
                ProfessionalCosmeticModel.Box.of(96, 0, -0.1F, -0.32F, -0.32F, 2.3F, 0.64F, 0.64F, 0.03F));
        b.bone(CosmeticAnchor.ROOT, "warden_arm_left", "warden_body", -2.75F, -1.55F, 0, 4, 0, -7,
                ProfessionalCosmeticModel.Box.of(0, 32, -0.70F, 0, -0.70F, 1.4F, 5.3F, 1.4F, 0.05F));
        b.bone(CosmeticAnchor.ROOT, "warden_arm_right", "warden_body", 2.75F, -1.55F, 0, 4, 0, 7,
                ProfessionalCosmeticModel.Box.of(0, 32, -0.70F, 0, -0.70F, 1.4F, 5.3F, 1.4F, 0.05F));
        b.bone(CosmeticAnchor.ROOT, "warden_leg_left", "warden_body", -1.25F, 2.65F, 0, 0, 0, 0,
                ProfessionalCosmeticModel.Box.of(32, 32, -0.70F, 0, -0.70F, 1.4F, 3.5F, 1.4F, 0.05F));
        b.bone(CosmeticAnchor.ROOT, "warden_leg_right", "warden_body", 1.25F, 2.65F, 0, 0, 0, 0,
                ProfessionalCosmeticModel.Box.of(32, 32, -0.70F, 0, -0.70F, 1.4F, 3.5F, 1.4F, 0.05F));
        return b.bake(true);
    }

    private static ProfessionalCosmeticModel swansitoDinoTail() {
        ProfessionalCosmeticModel.Builder builder = new ProfessionalCosmeticModel.Builder("swansito_dino_tail", 128, 128);
        builder.bone(
                CosmeticAnchor.BODY, "dino_tail_base", null,
                0.0F, 9.65F, 2.05F,
                7.0F, 0.0F, 0.0F,
                ProfessionalCosmeticModel.Box.of(
                        0, 0, -2.25F, -1.55F, -0.45F,
                        4.5F, 3.7F, 5.5F, 0.10F),
                ProfessionalCosmeticModel.Box.of(
                        0, 32, -1.72F, 1.35F, -0.52F,
                        3.44F, 0.72F, 5.35F, 0.04F)
        );
        builder.bone(
                CosmeticAnchor.BODY, "dino_tail_mid", "dino_tail_base",
                0.0F, 0.15F, 4.55F,
                -3.0F, 0.0F, 0.0F,
                ProfessionalCosmeticModel.Box.of(
                        32, 0, -1.72F, -1.24F, -0.32F,
                        3.44F, 2.85F, 5.4F, 0.08F),
                ProfessionalCosmeticModel.Box.of(
                        32, 32, -1.25F, 0.92F, -0.36F,
                        2.5F, 0.54F, 5.2F, 0.03F)
        );
        builder.bone(
                CosmeticAnchor.BODY, "dino_tail_tip", "dino_tail_mid",
                0.0F, 0.05F, 4.55F,
                -4.0F, 0.0F, 0.0F,
                ProfessionalCosmeticModel.Box.of(
                        64, 0, -1.14F, -0.86F, -0.24F,
                        2.28F, 2.0F, 5.3F, 0.06F)
        );
        builder.bone(
                CosmeticAnchor.BODY, "dino_tail_end", "dino_tail_tip",
                0.0F, 0.0F, 4.55F,
                -5.0F, 0.0F, 0.0F,
                ProfessionalCosmeticModel.Box.of(
                        96, 0, -0.64F, -0.48F, -0.18F,
                        1.28F, 1.12F, 4.3F, 0.04F)
        );
        builder.bone(
                CosmeticAnchor.BODY, "tail_spike_base", "dino_tail_base",
                0.0F, -1.55F, 1.45F,
                -16.0F, 0.0F, 0.0F,
                ProfessionalCosmeticModel.Box.of(
                        64, 32, -0.55F, -1.85F, -0.55F,
                        1.1F, 1.95F, 1.1F, 0.02F)
        );
        builder.bone(
                CosmeticAnchor.BODY, "tail_spike_mid", "dino_tail_mid",
                0.0F, -1.20F, 1.95F,
                -20.0F, 0.0F, 0.0F,
                ProfessionalCosmeticModel.Box.of(
                        80, 32, -0.44F, -1.52F, -0.44F,
                        0.88F, 1.6F, 0.88F, 0.02F)
        );
        builder.bone(
                CosmeticAnchor.BODY, "tail_spike_tip", "dino_tail_tip",
                0.0F, -0.82F, 2.35F,
                -23.0F, 0.0F, 0.0F,
                ProfessionalCosmeticModel.Box.of(
                        96, 32, -0.34F, -1.18F, -0.34F,
                        0.68F, 1.25F, 0.68F, 0.01F)
        );
        return builder.bake(true);
    }

    private static ProfessionalCosmeticModel swansitoDinoHands() {
        ProfessionalCosmeticModel.Builder builder = new ProfessionalCosmeticModel.Builder("swansito_dino_hands", 128, 128);
        builder.bone(
                CosmeticAnchor.LEFT_ARM, "dino_hand_left", null,
                0.0F, 8.15F, 0.0F,
                0.0F, 0.0F, 0.0F,
                ProfessionalCosmeticModel.Box.of(
                        0, 0, -2.35F, -0.35F, -2.35F,
                        4.7F, 4.45F, 4.7F, 0.10F),
                ProfessionalCosmeticModel.Box.of(
                        32, 0, -2.18F, 2.85F, -3.05F,
                        4.36F, 1.10F, 1.35F, 0.05F)
        );
        builder.bone(
                CosmeticAnchor.LEFT_ARM, "left_claw_outer", "dino_hand_left",
                -1.45F, 3.05F, -2.45F,
                -18.0F, 0.0F, -8.0F,
                ProfessionalCosmeticModel.Box.of(
                        64, 0, -0.34F, -0.20F, -1.65F,
                        0.68F, 0.68F, 1.85F, 0.015F)
        );
        builder.bone(
                CosmeticAnchor.LEFT_ARM, "left_claw_inner", "dino_hand_left",
                0.15F, 3.10F, -2.48F,
                -20.0F, 0.0F, 2.0F,
                ProfessionalCosmeticModel.Box.of(
                        80, 0, -0.34F, -0.20F, -1.75F,
                        0.68F, 0.68F, 1.95F, 0.015F)
        );
        builder.bone(
                CosmeticAnchor.RIGHT_ARM, "dino_hand_right", null,
                0.0F, 8.15F, 0.0F,
                0.0F, 0.0F, 0.0F,
                ProfessionalCosmeticModel.Box.of(
                        0, 0, -2.35F, -0.35F, -2.35F,
                        4.7F, 4.45F, 4.7F, 0.10F),
                ProfessionalCosmeticModel.Box.of(
                        32, 0, -2.18F, 2.85F, -3.05F,
                        4.36F, 1.10F, 1.35F, 0.05F)
        );
        builder.bone(
                CosmeticAnchor.RIGHT_ARM, "right_claw_outer", "dino_hand_right",
                1.45F, 3.05F, -2.45F,
                -18.0F, 0.0F, 8.0F,
                ProfessionalCosmeticModel.Box.of(
                        64, 0, -0.34F, -0.20F, -1.65F,
                        0.68F, 0.68F, 1.85F, 0.015F)
        );
        builder.bone(
                CosmeticAnchor.RIGHT_ARM, "right_claw_inner", "dino_hand_right",
                -0.15F, 3.10F, -2.48F,
                -20.0F, 0.0F, -2.0F,
                ProfessionalCosmeticModel.Box.of(
                        80, 0, -0.34F, -0.20F, -1.75F,
                        0.68F, 0.68F, 1.95F, 0.015F)
        );
        return builder.bake(true);
    }

    private static ProfessionalCosmeticModel swansitoMiniTrex() {
        ProfessionalCosmeticModel.Builder builder = new ProfessionalCosmeticModel.Builder("swansito_mini_trex", 128, 128);
        builder.bone(
                CosmeticAnchor.ROOT, "trex_root", null,
                10.6F, 19.6F, 13.8F,
                0.0F, -18.0F, 0.0F
        );
        builder.bone(
                CosmeticAnchor.ROOT, "trex_body", "trex_root",
                0.0F, -3.2F, 0.0F,
                -4.0F, 0.0F, 0.0F,
                ProfessionalCosmeticModel.Box.of(
                        0, 0, -3.25F, -2.55F, -4.1F,
                        6.5F, 5.1F, 8.2F, 0.08F),
                ProfessionalCosmeticModel.Box.of(
                        0, 32, -2.55F, 1.15F, -3.65F,
                        5.1F, 1.15F, 6.5F, 0.03F)
        );
        builder.bone(
                CosmeticAnchor.ROOT, "trex_neck", "trex_body",
                0.0F, -1.55F, -3.65F,
                -10.0F, 0.0F, 0.0F,
                ProfessionalCosmeticModel.Box.of(
                        32, 0, -2.2F, -2.8F, -2.1F,
                        4.4F, 4.8F, 4.2F, 0.06F)
        );
        builder.bone(
                CosmeticAnchor.ROOT, "trex_head", "trex_neck",
                0.0F, -2.0F, -1.25F,
                4.0F, 0.0F, 0.0F,
                ProfessionalCosmeticModel.Box.of(
                        64, 0, -3.0F, -2.55F, -3.85F,
                        6.0F, 4.65F, 6.0F, 0.07F),
                ProfessionalCosmeticModel.Box.of(
                        0, 64, -3.35F, -0.55F, -6.25F,
                        6.7F, 2.5F, 3.1F, 0.05F),
                ProfessionalCosmeticModel.Box.of(
                        96, 32, -2.62F, -1.45F, -6.55F,
                        0.72F, 0.72F, 0.36F, 0.01F),
                ProfessionalCosmeticModel.Box.of(
                        96, 32, 1.90F, -1.45F, -6.55F,
                        0.72F, 0.72F, 0.36F, 0.01F)
        );
        builder.bone(
                CosmeticAnchor.ROOT, "trex_jaw", "trex_head",
                0.0F, 1.25F, -4.55F,
                3.0F, 0.0F, 0.0F,
                ProfessionalCosmeticModel.Box.of(
                        32, 64, -3.05F, -0.20F, -1.95F,
                        6.1F, 1.1F, 3.5F, 0.03F),
                ProfessionalCosmeticModel.Box.of(
                        64, 64, -2.55F, -0.48F, -1.85F,
                        5.1F, 0.42F, 3.0F, 0.01F)
        );
        builder.bone(
                CosmeticAnchor.ROOT, "trex_arm_left", "trex_body",
                -2.65F, -0.55F, -3.15F,
                -26.0F, 0.0F, -16.0F,
                ProfessionalCosmeticModel.Box.of(
                        96, 0, -0.58F, -0.35F, -0.55F,
                        1.16F, 3.05F, 1.10F, 0.03F)
        );
        builder.bone(
                CosmeticAnchor.ROOT, "trex_arm_right", "trex_body",
                2.65F, -0.55F, -3.15F,
                -26.0F, 0.0F, 16.0F,
                ProfessionalCosmeticModel.Box.of(
                        96, 0, -0.58F, -0.35F, -0.55F,
                        1.16F, 3.05F, 1.10F, 0.03F)
        );
        builder.bone(
                CosmeticAnchor.ROOT, "trex_leg_left", "trex_body",
                -2.15F, 1.75F, 1.75F,
                2.0F, 0.0F, 0.0F,
                ProfessionalCosmeticModel.Box.of(
                        32, 32, -1.15F, -0.25F, -1.35F,
                        2.30F, 5.2F, 2.70F, 0.06F)
        );
        builder.bone(
                CosmeticAnchor.ROOT, "trex_foot_left", "trex_leg_left",
                0.0F, 4.55F, -0.25F,
                5.0F, 0.0F, 0.0F,
                ProfessionalCosmeticModel.Box.of(
                        64, 32, -1.45F, -0.35F, -3.0F,
                        2.90F, 1.35F, 4.2F, 0.04F)
        );
        builder.bone(
                CosmeticAnchor.ROOT, "trex_leg_right", "trex_body",
                2.15F, 1.75F, 1.75F,
                2.0F, 0.0F, 0.0F,
                ProfessionalCosmeticModel.Box.of(
                        32, 32, -1.15F, -0.25F, -1.35F,
                        2.30F, 5.2F, 2.70F, 0.06F)
        );
        builder.bone(
                CosmeticAnchor.ROOT, "trex_foot_right", "trex_leg_right",
                0.0F, 4.55F, -0.25F,
                5.0F, 0.0F, 0.0F,
                ProfessionalCosmeticModel.Box.of(
                        64, 32, -1.45F, -0.35F, -3.0F,
                        2.90F, 1.35F, 4.2F, 0.04F)
        );
        builder.bone(
                CosmeticAnchor.ROOT, "trex_tail_base", "trex_body",
                0.0F, -0.55F, 3.55F,
                8.0F, 0.0F, 0.0F,
                ProfessionalCosmeticModel.Box.of(
                        0, 96, -1.9F, -1.55F, -0.30F,
                        3.8F, 3.1F, 5.4F, 0.06F)
        );
        builder.bone(
                CosmeticAnchor.ROOT, "trex_tail_mid", "trex_tail_base",
                0.0F, 0.0F, 4.65F,
                2.0F, 0.0F, 0.0F,
                ProfessionalCosmeticModel.Box.of(
                        32, 96, -1.25F, -1.0F, -0.25F,
                        2.5F, 2.0F, 5.2F, 0.04F)
        );
        builder.bone(
                CosmeticAnchor.ROOT, "trex_tail_tip", "trex_tail_mid",
                0.0F, 0.0F, 4.55F,
                1.0F, 0.0F, 0.0F,
                ProfessionalCosmeticModel.Box.of(
                        64, 96, -0.68F, -0.55F, -0.18F,
                        1.36F, 1.10F, 4.3F, 0.02F)
        );
        builder.bone(CosmeticAnchor.ROOT, "trex_dorsal_plates", "trex_body",
                0.0F, -2.55F, 0.6F, 0.0F, 0.0F, 0.0F,
                ProfessionalCosmeticModel.Box.of(96, 0, -0.42F, -1.55F, -2.8F, 0.84F, 1.65F, 1.25F, 0.02F),
                ProfessionalCosmeticModel.Box.of(96, 0, -0.50F, -1.85F, -0.8F, 1.0F, 1.95F, 1.35F, 0.02F),
                ProfessionalCosmeticModel.Box.of(96, 0, -0.42F, -1.55F, 1.45F, 0.84F, 1.65F, 1.20F, 0.02F));
        builder.bone(CosmeticAnchor.ROOT, "trex_cheek_left", "trex_head",
                -2.55F, 0.20F, -3.65F, 0.0F, 0.0F, -5.0F,
                ProfessionalCosmeticModel.Box.of(0, 32, -0.55F, -0.55F, -0.32F, 1.1F, 1.1F, 0.64F, 0.02F));
        builder.bone(CosmeticAnchor.ROOT, "trex_cheek_right", "trex_head",
                2.55F, 0.20F, -3.65F, 0.0F, 0.0F, 5.0F,
                ProfessionalCosmeticModel.Box.of(0, 32, -0.55F, -0.55F, -0.32F, 1.1F, 1.1F, 0.64F, 0.02F));
        return builder.bake(true);
    }

    private static ProfessionalCosmeticModel swansitoMeatChaseAura() {
        ProfessionalCosmeticModel.Builder builder = new ProfessionalCosmeticModel.Builder("swansito_meat_chase_aura", 128, 128);
        builder.bone(
                CosmeticAnchor.ROOT, "chase_orbit", null,
                0.0F, 11.0F, 0.0F,
                0.0F, 0.0F, 0.0F
        );
        builder.bone(
                CosmeticAnchor.ROOT, "chase_dino_body", "chase_orbit",
                8.0F, 0.0F, 0.0F,
                0.0F, 90.0F, 0.0F,
                ProfessionalCosmeticModel.Box.of(
                        0, 0, -1.65F, -1.25F, -2.0F,
                        3.3F, 2.5F, 4.0F, 0.05F)
        );
        builder.bone(
                CosmeticAnchor.ROOT, "chase_dino_head", "chase_dino_body",
                0.0F, -0.8F, -1.9F,
                -5.0F, 0.0F, 0.0F,
                ProfessionalCosmeticModel.Box.of(
                        32, 0, -1.6F, -1.25F, -2.2F,
                        3.2F, 2.5F, 3.1F, 0.04F),
                ProfessionalCosmeticModel.Box.of(
                        64, 0, -1.75F, -0.15F, -3.25F,
                        3.5F, 1.2F, 1.45F, 0.03F)
        );
        builder.bone(
                CosmeticAnchor.ROOT, "chase_leg_left", "chase_dino_body",
                -1.05F, 1.0F, 0.6F,
                0.0F, 0.0F, 0.0F,
                ProfessionalCosmeticModel.Box.of(
                        96, 0, -0.42F, 0.0F, -0.5F,
                        0.84F, 2.15F, 1.0F, 0.02F)
        );
        builder.bone(
                CosmeticAnchor.ROOT, "chase_leg_right", "chase_dino_body",
                1.05F, 1.0F, 0.6F,
                0.0F, 0.0F, 0.0F,
                ProfessionalCosmeticModel.Box.of(
                        96, 0, -0.42F, 0.0F, -0.5F,
                        0.84F, 2.15F, 1.0F, 0.02F)
        );
        builder.bone(
                CosmeticAnchor.ROOT, "chase_tail", "chase_dino_body",
                0.0F, -0.25F, 1.85F,
                5.0F, 0.0F, 0.0F,
                ProfessionalCosmeticModel.Box.of(
                        0, 32, -0.72F, -0.62F, -0.20F,
                        1.44F, 1.24F, 4.4F, 0.03F)
        );
        builder.bone(
                CosmeticAnchor.ROOT, "chase_meat", "chase_orbit",
                12.4F, -0.5F, 0.0F,
                0.0F, 90.0F, 12.0F,
                ProfessionalCosmeticModel.Box.of(
                        32, 32, -1.25F, -1.05F, -1.75F,
                        2.5F, 2.1F, 3.5F, 0.04F),
                ProfessionalCosmeticModel.Box.of(
                        64, 32, -0.45F, -0.45F, -2.65F,
                        0.9F, 0.9F, 1.4F, 0.02F),
                ProfessionalCosmeticModel.Box.of(
                        80, 32, -0.45F, -0.45F, 1.35F,
                        0.9F, 0.9F, 1.4F, 0.02F)
        );
        return builder.bake(true);
    }

    private static ProfessionalCosmeticModel dinoPajamaHat() {
        ProfessionalCosmeticModel.Builder b = new ProfessionalCosmeticModel.Builder("dino_pajama_hat",128,128);
        b.bone(CosmeticAnchor.HEAD,"dino_hat_top",null,0,0,0,0,0,0,
                ProfessionalCosmeticModel.Box.of(0,0,-4.35F,-8.55F,-4.35F,8.7F,2.15F,8.7F,0.22F));
        b.bone(CosmeticAnchor.HEAD,"dino_hat_left",null,0,0,0,0,0,0,
                ProfessionalCosmeticModel.Box.of(0,24,-4.35F,-6.9F,-4.0F,0.92F,6.75F,8.0F,0.15F));
        b.bone(CosmeticAnchor.HEAD,"dino_hat_right",null,0,0,0,0,0,0,
                ProfessionalCosmeticModel.Box.of(20,24,3.30F,-6.9F,-4.0F,0.92F,6.75F,8.0F,0.15F));
        b.bone(CosmeticAnchor.HEAD,"dino_hat_back",null,0,0,0,0,0,0,
                ProfessionalCosmeticModel.Box.of(40,24,-4.0F,-6.9F,3.25F,8.0F,6.75F,0.92F,0.15F));
        b.bone(CosmeticAnchor.HEAD,"dino_hat_snout",null,0,0,0,0,0,0,
                // Hocico elevado: deja la cara completamente libre y elimina la
                // antigua franja verde que cruzaba los ojos del jugador.
                ProfessionalCosmeticModel.Box.of(64,0,-2.10F,-6.35F,-5.05F,4.2F,1.15F,0.95F,0.07F));
        b.bone(CosmeticAnchor.HEAD,"dino_spike_one",null,0,0,0,0,0,0,
                ProfessionalCosmeticModel.Box.of(96,0,-0.75F,-9.35F,-0.15F,1.5F,2.25F,0.9F,0.02F));
        b.bone(CosmeticAnchor.HEAD,"dino_spike_two",null,0,0,0,-10,0,0,
                ProfessionalCosmeticModel.Box.of(96,0,-0.65F,-8.55F,1.55F,1.3F,2.0F,0.8F,0.02F));
        b.bone(CosmeticAnchor.HEAD,"dino_spike_three",null,0,0,0,-18,0,0,
                ProfessionalCosmeticModel.Box.of(96,0,-0.55F,-7.7F,3.0F,1.1F,1.7F,0.7F,0.02F));
        b.bone(CosmeticAnchor.HEAD,"dino_hat_jaw",null,0,0,0,0,0,0,
                ProfessionalCosmeticModel.Box.of(64,32,-2.9F,-1.2F,-4.65F,5.8F,0.7F,0.7F,0.02F));
        return b.bake(true);
    }

    private static ProfessionalCosmeticModel axolotlPajamaHat() {
        ProfessionalCosmeticModel.Builder b = new ProfessionalCosmeticModel.Builder("axolotl_pajama_hat",128,128);
        b.bone(CosmeticAnchor.HEAD,"axo_hat_top",null,0,0,0,0,0,0,
                ProfessionalCosmeticModel.Box.of(0,0,-4.35F,-8.55F,-4.35F,8.7F,2.10F,8.7F,0.22F));
        b.bone(CosmeticAnchor.HEAD,"axo_hat_left",null,0,0,0,0,0,0,
                ProfessionalCosmeticModel.Box.of(0,24,-4.35F,-6.9F,-4.0F,0.92F,6.75F,8.0F,0.15F));
        b.bone(CosmeticAnchor.HEAD,"axo_hat_right",null,0,0,0,0,0,0,
                ProfessionalCosmeticModel.Box.of(20,24,3.30F,-6.9F,-4.0F,0.92F,6.75F,8.0F,0.15F));
        b.bone(CosmeticAnchor.HEAD,"axo_hat_back",null,0,0,0,0,0,0,
                ProfessionalCosmeticModel.Box.of(40,24,-4.0F,-6.9F,3.25F,8.0F,6.75F,0.92F,0.15F));
        b.bone(CosmeticAnchor.HEAD,"axo_hat_brow",null,0,0,0,0,0,0,
                ProfessionalCosmeticModel.Box.of(60,24,-3.9F,-6.9F,-4.35F,7.8F,1.0F,1.05F,0.04F),
                ProfessionalCosmeticModel.Box.of(64,0,-2.25F,-5.3F,-4.95F,4.5F,1.25F,0.8F,0.03F));
        b.bone(CosmeticAnchor.HEAD,"axo_gill_left",null,0,0,0,0,0,0,
                ProfessionalCosmeticModel.Box.of(32,32,-6.2F,-5.9F,-0.45F,2.35F,0.7F,0.9F,0.03F),
                ProfessionalCosmeticModel.Box.of(64,32,-5.85F,-7.0F,-0.35F,0.75F,1.2F,0.7F,0.02F),
                ProfessionalCosmeticModel.Box.of(64,32,-4.75F,-7.1F,-0.35F,0.75F,1.3F,0.7F,0.02F));
        b.bone(CosmeticAnchor.HEAD,"axo_gill_right",null,0,0,0,0,0,0,
                ProfessionalCosmeticModel.Box.of(32,32,3.85F,-5.9F,-0.45F,2.35F,0.7F,0.9F,0.03F),
                ProfessionalCosmeticModel.Box.of(64,32,5.10F,-7.0F,-0.35F,0.75F,1.2F,0.7F,0.02F),
                ProfessionalCosmeticModel.Box.of(64,32,4.00F,-7.1F,-0.35F,0.75F,1.3F,0.7F,0.02F));
        return b.bake(true);
    }

    private static ProfessionalCosmeticModel pajamaChest(String id, boolean dino) {
        ProfessionalCosmeticModel.Builder b = new ProfessionalCosmeticModel.Builder(id,128,128);
        b.bone(CosmeticAnchor.BODY,"pajama_body",null,0,0,0,0,0,0,
                ProfessionalCosmeticModel.Box.of(0,0,-4,-0.08F,-2,8,10.10F,4,0.56F),
                ProfessionalCosmeticModel.Box.of(64,0,-2.5F,2.1F,-2.65F,5.0F,4.4F,0.65F,0.03F));
        b.bone(CosmeticAnchor.LEFT_ARM,"pajama_left_arm",null,0,0,0,0,0,0,
                ProfessionalCosmeticModel.Box.of(32,0,-2,-2,-2,4,12.15F,4,0.54F));
        b.bone(CosmeticAnchor.RIGHT_ARM,"pajama_right_arm",null,0,0,0,0,0,0,
                ProfessionalCosmeticModel.Box.of(48,0,-2,-2,-2,4,12.15F,4,0.54F));
        b.bone(CosmeticAnchor.BODY,"pajama_belly_patch","pajama_body",0,3.4F,-2.48F,0,0,0,
                ProfessionalCosmeticModel.Box.of(64,0,-2.15F,-2.45F,-0.18F,4.3F,4.9F,0.36F,0.015F));
        b.bone(CosmeticAnchor.LEFT_ARM,"pajama_left_cuff","pajama_left_arm",0,8.8F,0,0,0,0,
                ProfessionalCosmeticModel.Box.of(80,24,-2.2F,-0.6F,-2.2F,4.4F,1.3F,4.4F,0.04F));
        b.bone(CosmeticAnchor.RIGHT_ARM,"pajama_right_cuff","pajama_right_arm",0,8.8F,0,0,0,0,
                ProfessionalCosmeticModel.Box.of(96,24,-2.2F,-0.6F,-2.2F,4.4F,1.3F,4.4F,0.04F));
        if (dino) {
            b.bone(CosmeticAnchor.BODY,"pajama_back_spikes","pajama_body",0,1.0F,2.55F,0,0,0,
                    ProfessionalCosmeticModel.Box.of(96,0,-0.65F,-1.6F,-0.2F,1.3F,2.0F,0.8F,0.02F),
                    ProfessionalCosmeticModel.Box.of(96,0,-0.55F,1.2F,-0.2F,1.1F,1.8F,0.75F,0.02F),
                    ProfessionalCosmeticModel.Box.of(96,0,-0.45F,3.8F,-0.2F,0.9F,1.5F,0.7F,0.02F));
        } else {
            b.bone(CosmeticAnchor.BODY,"pajama_bow","pajama_body",0,1.4F,-2.75F,0,0,0,
                    ProfessionalCosmeticModel.Box.of(96,32,-0.75F,-0.75F,-0.3F,1.5F,1.5F,0.6F,0.02F),
                    ProfessionalCosmeticModel.Box.of(64,32,-3.1F,-1.2F,-0.2F,2.5F,2.4F,0.45F,0.02F),
                    ProfessionalCosmeticModel.Box.of(64,32,0.6F,-1.2F,-0.2F,2.5F,2.4F,0.45F,0.02F));
        }
        return b.bake(!dino);
    }

    private static ProfessionalCosmeticModel pajamaLegs(String id, boolean dino) {
        ProfessionalCosmeticModel.Builder b = new ProfessionalCosmeticModel.Builder(id,128,128);
        b.bone(CosmeticAnchor.BODY,"pajama_waist",null,0,8.55F,0,0,0,0,
                ProfessionalCosmeticModel.Box.of(64,24,-4.15F,0,-2.15F,8.25F,3.75F,4.25F,0.12F));
        b.bone(CosmeticAnchor.LEFT_LEG,"pajama_left_leg",null,0,0,0,0,0,0,
                ProfessionalCosmeticModel.Box.of(0,24,-2,-0.10F,-2,4,9.45F,4,0.54F));
        b.bone(CosmeticAnchor.RIGHT_LEG,"pajama_right_leg",null,0,0,0,0,0,0,
                ProfessionalCosmeticModel.Box.of(16,24,-2,-0.10F,-2,4,9.45F,4,0.54F));
        if (dino) {
            b.bone(CosmeticAnchor.BODY,"pajama_dino_tail","pajama_waist",0,1.4F,2.0F,8,0,0,
                    ProfessionalCosmeticModel.Box.of(96,0,-0.9F,-0.6F,-0.15F,1.8F,1.4F,5.2F,0.04F));
        } else {
            b.bone(CosmeticAnchor.BODY,"pajama_fin_left","pajama_waist",-4.0F,1.2F,0,0,0,-18,
                    ProfessionalCosmeticModel.Box.of(64,32,-1.7F,-0.2F,-0.25F,1.8F,2.9F,0.5F,0.03F));
            b.bone(CosmeticAnchor.BODY,"pajama_fin_right","pajama_waist",4.0F,1.2F,0,0,0,18,
                    ProfessionalCosmeticModel.Box.of(64,32,-0.1F,-0.2F,-0.25F,1.8F,2.9F,0.5F,0.03F));
        }
        return b.bake(false);
    }

    private static ProfessionalCosmeticModel pajamaFeet(String id, boolean dino) {
        ProfessionalCosmeticModel.Builder b = new ProfessionalCosmeticModel.Builder(id,128,128);
        pajamaFoot(b,CosmeticAnchor.LEFT_LEG,"left",0,dino);
        pajamaFoot(b,CosmeticAnchor.RIGHT_LEG,"right",32,dino);
        return b.bake(true);
    }

    private static void pajamaFoot(ProfessionalCosmeticModel.Builder b, CosmeticAnchor anchor,
                                   String side, int u, boolean dino) {
        b.bone(anchor,side+"_pajama_slipper",null,0,8.55F,-0.35F,0,0,0,
                ProfessionalCosmeticModel.Box.of(u,0,-2.075F,-0.20F,-3.05F,4.15F,3.65F,4.75F,0.15F),
                ProfessionalCosmeticModel.Box.of(96,0,-1.15F,0.55F,-3.53F,0.55F,0.55F,0.18F,0.01F),
                ProfessionalCosmeticModel.Box.of(96,0,0.60F,0.55F,-3.53F,0.55F,0.55F,0.18F,0.01F));
        if (dino) {
            for (int i=0;i<3;i++) b.bone(anchor,side+"_toe_"+i,side+"_pajama_slipper",-1.2F+i*1.2F,2.1F,-3.2F,0,0,0,
                    ProfessionalCosmeticModel.Box.of(64,0,-0.35F,-0.25F,-0.6F,0.7F,0.5F,0.8F,0.02F));
        } else {
            b.bone(anchor,side+"_gill_left",side+"_pajama_slipper",-2.0F,0.8F,-1.0F,0,0,-18,
                    ProfessionalCosmeticModel.Box.of(64,32,-1.3F,-0.25F,-0.25F,1.4F,0.5F,0.5F,0.02F));
            b.bone(anchor,side+"_gill_right",side+"_pajama_slipper",2.0F,0.8F,-1.0F,0,0,18,
                    ProfessionalCosmeticModel.Box.of(64,32,-0.1F,-0.25F,-0.25F,1.4F,0.5F,0.5F,0.02F));
        }
    }

    private static ProfessionalCosmeticModel pastelHeartBackpack() {
        ProfessionalCosmeticModel.Builder b = new ProfessionalCosmeticModel.Builder("pastel_heart_backpack",128,128);
        b.bone(CosmeticAnchor.BODY,"heart_pack",null,0,4.0F,2.4F,0,0,0,
                ProfessionalCosmeticModel.Box.of(0,0,-3.2F,-3.0F,-0.2F,6.4F,6.0F,2.3F,0.08F),
                ProfessionalCosmeticModel.Box.of(64,0,-1.0F,2.4F,1.1F,2.0F,2.2F,1.0F,0.03F));
        b.bone(CosmeticAnchor.BODY,"pack_wing_left","heart_pack",-3.0F,-0.6F,0.8F,0,0,-18,
                ProfessionalCosmeticModel.Box.of(64,32,-2.7F,-1.2F,-0.2F,2.8F,3.2F,0.4F,0.02F));
        b.bone(CosmeticAnchor.BODY,"pack_wing_right","heart_pack",3.0F,-0.6F,0.8F,0,0,18,
                ProfessionalCosmeticModel.Box.of(64,32,-0.1F,-1.2F,-0.2F,2.8F,3.2F,0.4F,0.02F));
        return b.bake(true);
    }

    private static ProfessionalCosmeticModel bunnyEarHood() {
        ProfessionalCosmeticModel.Builder b = new ProfessionalCosmeticModel.Builder("bunny_ear_hood",128,128);
        b.bone(CosmeticAnchor.HEAD,"bunny_hat_top",null,0,0,0,0,0,0,
                ProfessionalCosmeticModel.Box.of(0,0,-4.35F,-8.55F,-4.35F,8.7F,2.05F,8.7F,0.10F));
        b.bone(CosmeticAnchor.HEAD,"bunny_hat_left",null,0,0,0,0,0,0,
                ProfessionalCosmeticModel.Box.of(0,24,-4.35F,-6.95F,-4.0F,0.90F,6.80F,8.0F,0.06F));
        b.bone(CosmeticAnchor.HEAD,"bunny_hat_right",null,0,0,0,0,0,0,
                ProfessionalCosmeticModel.Box.of(20,24,3.35F,-6.95F,-4.0F,0.90F,6.80F,8.0F,0.06F));
        b.bone(CosmeticAnchor.HEAD,"bunny_hat_back",null,0,0,0,0,0,0,
                ProfessionalCosmeticModel.Box.of(40,24,-4.0F,-6.95F,3.35F,8.0F,6.80F,0.90F,0.06F));
        b.bone(CosmeticAnchor.HEAD,"bunny_hat_brow",null,0,0,0,0,0,0,
                ProfessionalCosmeticModel.Box.of(60,24,-3.9F,-6.95F,-4.35F,7.8F,0.95F,1.0F,0.04F));
        b.bone(CosmeticAnchor.HEAD,"bunny_ear_left",null,0,0,0,0,0,0,
                ProfessionalCosmeticModel.Box.of(32,0,-3.0F,-11.9F,-0.25F,2.0F,5.8F,1.45F,0.035F));
        b.bone(CosmeticAnchor.HEAD,"bunny_ear_right",null,0,0,0,0,0,0,
                ProfessionalCosmeticModel.Box.of(48,0,1.0F,-11.9F,-0.25F,2.0F,5.8F,1.45F,0.035F));
        return b.bake(false);
    }

    private static ProfessionalCosmeticModel moonStarCardigan() {
        ProfessionalCosmeticModel.Builder b = new ProfessionalCosmeticModel.Builder("moon_star_cardigan",128,128);
        b.bone(CosmeticAnchor.BODY,"cardigan_body",null,0,0,0,0,0,0,
                ProfessionalCosmeticModel.Box.of(0,0,-4,-0.05F,-2,8,9.4F,4,0.42F),
                ProfessionalCosmeticModel.Box.of(96,0,-0.75F,2.0F,-2.62F,1.5F,1.5F,0.35F,0.02F));
        b.bone(CosmeticAnchor.LEFT_ARM,"cardigan_left_arm",null,0,0,0,0,0,0,
                ProfessionalCosmeticModel.Box.of(32,0,-2,-2,-2,4,12,4,0.42F));
        b.bone(CosmeticAnchor.RIGHT_ARM,"cardigan_right_arm",null,0,0,0,0,0,0,
                ProfessionalCosmeticModel.Box.of(48,0,-2,-2,-2,4,12,4,0.42F));
        return b.bake(true);
    }

    private static ProfessionalCosmeticModel cloudSkirt() {
        ProfessionalCosmeticModel.Builder b = new ProfessionalCosmeticModel.Builder("cloud_skirt",128,128);
        b.bone(CosmeticAnchor.BODY,"cloud_skirt",null,0,9.0F,0,0,0,0,
                ProfessionalCosmeticModel.Box.of(0,0,-4.6F,0,-2.6F,8.9F,4.0F,4.9F,0.05F));
        b.bone(CosmeticAnchor.LEFT_LEG,"cloud_left_leg",null,0,0,0,0,0,0,
                ProfessionalCosmeticModel.Box.of(32,0,-2,0,-2,4,8.15F,4,0.41F));
        b.bone(CosmeticAnchor.RIGHT_LEG,"cloud_right_leg",null,0,0,0,0,0,0,
                ProfessionalCosmeticModel.Box.of(48,0,-2,0,-2,4,8.15F,4,0.41F));
        return b.bake(false);
    }

    private static ProfessionalCosmeticModel cloudSlippers() {
        ProfessionalCosmeticModel.Builder b = new ProfessionalCosmeticModel.Builder("cloud_slippers",128,128);
        b.bone(CosmeticAnchor.LEFT_LEG,"left_cloud",null,0,9.0F,-0.4F,0,0,0,
                ProfessionalCosmeticModel.Box.of(0,0,-2.4F,-0.4F,-3.4F,4.45F,2.95F,4.8F,0.08F),
                ProfessionalCosmeticModel.Box.of(96,0,-0.4F,0.3F,-3.65F,0.8F,0.8F,0.2F,0.01F));
        b.bone(CosmeticAnchor.RIGHT_LEG,"right_cloud",null,0,9.0F,-0.4F,0,0,0,
                ProfessionalCosmeticModel.Box.of(32,0,-2.4F,-0.4F,-3.4F,4.45F,2.95F,4.8F,0.08F),
                ProfessionalCosmeticModel.Box.of(96,0,-0.4F,0.3F,-3.65F,0.8F,0.8F,0.2F,0.01F));
        return b.bake(true);
    }

    private static ProfessionalCosmeticModel floatingPastelRibbon() {
        ProfessionalCosmeticModel.Builder b = new ProfessionalCosmeticModel.Builder("floating_pastel_ribbon",128,128);
        b.bone(CosmeticAnchor.ROOT,"ribbon_orbit",null,0,10.5F,0,0,0,0);
        b.bone(CosmeticAnchor.ROOT,"ribbon_core","ribbon_orbit",8.5F,0,0,0,0,0,
                ProfessionalCosmeticModel.Box.of(0,0,-1.1F,-1.1F,-0.5F,2.2F,2.2F,1.0F,0.04F),
                ProfessionalCosmeticModel.Box.of(32,0,-4.1F,-2.0F,-0.3F,3.2F,4.0F,0.6F,0.03F),
                ProfessionalCosmeticModel.Box.of(48,0,0.9F,-2.0F,-0.3F,3.2F,4.0F,0.6F,0.03F));
        b.bone(CosmeticAnchor.ROOT,"ribbon_star","ribbon_orbit",-8.5F,-1.0F,0,0,0,0,
                ProfessionalCosmeticModel.Box.of(96,0,-0.7F,-0.7F,-0.35F,1.4F,1.4F,0.7F,0.03F));
        return b.bake(true);
    }


    private static ProfessionalCosmeticModel mobChibiChicken() {
        ProfessionalCosmeticModel.Builder b = new ProfessionalCosmeticModel.Builder("mobchibi_chicken", 128, 128);
        b.bone(CosmeticAnchor.ROOT, "chicken_root", null, 9.0F, 20.9F, 4.3F, 0, -16, 0);
        b.bone(CosmeticAnchor.ROOT, "chicken_body", "chicken_root", 0, -2.2F, 0, 0, 0, 0,
                ProfessionalCosmeticModel.Box.of(0, 0, -2.5F, -2.25F, -2.65F, 5.0F, 4.5F, 5.3F, 0.08F));
        b.bone(CosmeticAnchor.ROOT, "chicken_head", "chicken_body", 0, -2.15F, -1.8F, 0, 0, 0,
                ProfessionalCosmeticModel.Box.of(32, 0, -2.15F, -2.05F, -2.05F, 4.3F, 4.1F, 4.1F, 0.06F),
                ProfessionalCosmeticModel.Box.of(64, 0, -0.85F, -0.05F, -2.70F, 1.7F, 0.90F, 1.35F, 0.03F),
                ProfessionalCosmeticModel.Box.of(96, 0, -0.42F, 0.80F, -2.25F, 0.84F, 1.15F, 0.55F, 0.02F));
        b.bone(CosmeticAnchor.ROOT, "chicken_eye_left", "chicken_head", -1.05F, -0.45F, -2.10F, 0, 0, 0,
                ProfessionalCosmeticModel.Box.of(112, 64, -0.38F, -0.38F, -0.22F, 0.76F, 0.76F, 0.26F, 0.01F));
        b.bone(CosmeticAnchor.ROOT, "chicken_eye_right", "chicken_head", 1.05F, -0.45F, -2.10F, 0, 0, 0,
                ProfessionalCosmeticModel.Box.of(112, 64, -0.38F, -0.38F, -0.22F, 0.76F, 0.76F, 0.26F, 0.01F));
        b.bone(CosmeticAnchor.ROOT, "chicken_comb", "chicken_head", 0, -2.0F, 0, 0, 0, 0,
                ProfessionalCosmeticModel.Box.of(96, 0, -0.55F, -1.55F, -0.55F, 1.1F, 1.65F, 1.1F, 0.03F));
        b.bone(CosmeticAnchor.ROOT, "chicken_wing_left", "chicken_body", -2.25F, -0.45F, 0, 0, 0, -12,
                ProfessionalCosmeticModel.Box.of(64, 32, -2.15F, -0.35F, -0.52F, 2.25F, 3.45F, 1.04F, 0.04F));
        b.bone(CosmeticAnchor.ROOT, "chicken_wing_right", "chicken_body", 2.25F, -0.45F, 0, 0, 0, 12,
                ProfessionalCosmeticModel.Box.of(64, 32, -0.10F, -0.35F, -0.52F, 2.25F, 3.45F, 1.04F, 0.04F));
        b.bone(CosmeticAnchor.ROOT, "chicken_tail", "chicken_body", 0, -0.75F, 2.3F, -18, 0, 0,
                ProfessionalCosmeticModel.Box.of(32, 32, -1.75F, -1.10F, -0.15F, 3.5F, 2.2F, 2.7F, 0.04F));
        b.bone(CosmeticAnchor.ROOT, "chicken_leg_left", "chicken_body", -0.95F, 1.8F, 0, 0, 0, 0,
                ProfessionalCosmeticModel.Box.of(96, 32, -0.28F, 0, -0.28F, 0.56F, 2.2F, 0.56F, 0.02F));
        b.bone(CosmeticAnchor.ROOT, "chicken_leg_right", "chicken_body", 0.95F, 1.8F, 0, 0, 0, 0,
                ProfessionalCosmeticModel.Box.of(96, 32, -0.28F, 0, -0.28F, 0.56F, 2.2F, 0.56F, 0.02F));
        b.bone(CosmeticAnchor.ROOT, "chicken_foot_left", "chicken_leg_left", 0, 2.0F, -0.35F, 0, 0, 0,
                ProfessionalCosmeticModel.Box.of(96, 64, -0.85F, -0.15F, -1.35F, 1.7F, 0.38F, 2.0F, 0.02F));
        b.bone(CosmeticAnchor.ROOT, "chicken_foot_right", "chicken_leg_right", 0, 2.0F, -0.35F, 0, 0, 0,
                ProfessionalCosmeticModel.Box.of(96, 64, -0.85F, -0.15F, -1.35F, 1.7F, 0.38F, 2.0F, 0.02F));
        return b.bake(false);
    }


    private static ProfessionalCosmeticModel mobChibiFox() {
        ProfessionalCosmeticModel.Builder b = new ProfessionalCosmeticModel.Builder("mobchibi_fox", 128, 128);
        b.bone(CosmeticAnchor.ROOT, "fox_root", null, 8.9F, 20.2F, 4.2F, 0, -18, 0);
        b.bone(CosmeticAnchor.ROOT, "fox_body", "fox_root", 0, -2.25F, 0.35F, 0, 0, 0,
                ProfessionalCosmeticModel.Box.of(0, 0, -2.1F, -1.75F, -3.25F, 4.2F, 3.5F, 6.5F, 0.07F),
                ProfessionalCosmeticModel.Box.of(0, 32, -1.45F, 0.95F, -2.45F, 2.9F, 0.45F, 4.9F, 0.02F));
        b.bone(CosmeticAnchor.ROOT, "fox_head", "fox_body", 0, -1.1F, -3.15F, 0, 0, 0,
                ProfessionalCosmeticModel.Box.of(32, 0, -2.75F, -2.35F, -2.15F, 5.5F, 4.7F, 4.3F, 0.07F),
                ProfessionalCosmeticModel.Box.of(64, 0, -1.55F, -0.10F, -2.95F, 3.1F, 1.6F, 1.6F, 0.04F),
                ProfessionalCosmeticModel.Box.of(96, 0, -0.38F, 0.45F, -3.20F, 0.76F, 0.60F, 0.35F, 0.01F));
        b.bone(CosmeticAnchor.ROOT, "fox_eye_left", "fox_head", -1.15F, -0.55F, -2.18F, 0, 0, 0,
                ProfessionalCosmeticModel.Box.of(112, 64, -0.34F, -0.34F, -0.22F, 0.68F, 0.68F, 0.26F, 0.01F));
        b.bone(CosmeticAnchor.ROOT, "fox_eye_right", "fox_head", 1.15F, -0.55F, -2.18F, 0, 0, 0,
                ProfessionalCosmeticModel.Box.of(112, 64, -0.34F, -0.34F, -0.22F, 0.68F, 0.68F, 0.26F, 0.01F));
        b.bone(CosmeticAnchor.ROOT, "fox_ear_left", "fox_head", -1.65F, -2.05F, 0, 0, 0, -8,
                ProfessionalCosmeticModel.Box.of(96, 0, -0.80F, -2.15F, -0.58F, 1.6F, 2.25F, 1.16F, 0.03F));
        b.bone(CosmeticAnchor.ROOT, "fox_ear_right", "fox_head", 1.65F, -2.05F, 0, 0, 0, 8,
                ProfessionalCosmeticModel.Box.of(96, 0, -0.80F, -2.15F, -0.58F, 1.6F, 2.25F, 1.16F, 0.03F));
        b.bone(CosmeticAnchor.ROOT, "fox_tail", "fox_body", 0, -0.75F, 2.9F, -18, 0, 0,
                ProfessionalCosmeticModel.Box.of(0, 32, -1.65F, -1.35F, -0.15F, 3.3F, 2.7F, 5.4F, 0.09F));
        b.bone(CosmeticAnchor.ROOT, "fox_tail_tip", "fox_tail", 0, 0, 4.65F, -6, 0, 0,
                ProfessionalCosmeticModel.Box.of(32, 32, -1.45F, -1.20F, -0.15F, 2.9F, 2.4F, 3.8F, 0.07F));
        for (int i = 0; i < 4; i++) {
            float x = i < 2 ? -1.35F : 1.35F;
            float z = (i % 2 == 0) ? -1.9F : 1.9F;
            b.bone(CosmeticAnchor.ROOT, "fox_leg_" + i, "fox_body", x, 1.1F, z, 0, 0, 0,
                    ProfessionalCosmeticModel.Box.of(64, 32, -0.52F, 0, -0.52F, 1.04F, 2.6F, 1.04F, 0.03F));
        }
        return b.bake(false);
    }


    private static ProfessionalCosmeticModel mobChibiFrog() {
        ProfessionalCosmeticModel.Builder b = new ProfessionalCosmeticModel.Builder("mobchibi_frog", 128, 128);
        b.bone(CosmeticAnchor.ROOT, "frog_root", null, 8.9F, 20.8F, 4.2F, 0, -16, 0);
        b.bone(CosmeticAnchor.ROOT, "frog_body", "frog_root", 0, -2.0F, 0, 0, 0, 0,
                ProfessionalCosmeticModel.Box.of(0, 0, -3.3F, -2.0F, -2.75F, 6.6F, 4.0F, 5.5F, 0.09F),
                ProfessionalCosmeticModel.Box.of(0, 32, -2.25F, 0.35F, -2.95F, 4.5F, 1.55F, 0.36F, 0.02F));
        b.bone(CosmeticAnchor.ROOT, "frog_eye_left", "frog_body", -1.75F, -1.9F, -1.55F, 0, 0, 0,
                ProfessionalCosmeticModel.Box.of(32, 0, -0.85F, -0.85F, -0.85F, 1.7F, 1.7F, 1.7F, 0.04F));
        b.bone(CosmeticAnchor.ROOT, "frog_eye_right", "frog_body", 1.75F, -1.9F, -1.55F, 0, 0, 0,
                ProfessionalCosmeticModel.Box.of(48, 0, -0.85F, -0.85F, -0.85F, 1.7F, 1.7F, 1.7F, 0.04F));
        b.bone(CosmeticAnchor.ROOT, "frog_pupil_left", "frog_eye_left", 0, 0, -0.86F, 0, 0, 0,
                ProfessionalCosmeticModel.Box.of(112, 64, -0.32F, -0.32F, -0.16F, 0.64F, 0.64F, 0.22F, 0.01F));
        b.bone(CosmeticAnchor.ROOT, "frog_pupil_right", "frog_eye_right", 0, 0, -0.86F, 0, 0, 0,
                ProfessionalCosmeticModel.Box.of(112, 64, -0.32F, -0.32F, -0.16F, 0.64F, 0.64F, 0.22F, 0.01F));
        b.bone(CosmeticAnchor.ROOT, "frog_arm_left", "frog_body", -2.2F, 1.0F, -1.65F, 12, 0, -18,
                ProfessionalCosmeticModel.Box.of(96, 0, -1.35F, -0.28F, -0.45F, 1.45F, 0.56F, 1.0F, 0.02F));
        b.bone(CosmeticAnchor.ROOT, "frog_arm_right", "frog_body", 2.2F, 1.0F, -1.65F, 12, 0, 18,
                ProfessionalCosmeticModel.Box.of(96, 0, -0.10F, -0.28F, -0.45F, 1.45F, 0.56F, 1.0F, 0.02F));
        b.bone(CosmeticAnchor.ROOT, "frog_leg_left", "frog_body", -2.55F, 1.0F, 1.25F, 0, 0, -10,
                ProfessionalCosmeticModel.Box.of(64, 0, -1.75F, -0.45F, -1.35F, 3.5F, 1.45F, 2.7F, 0.05F));
        b.bone(CosmeticAnchor.ROOT, "frog_leg_right", "frog_body", 2.55F, 1.0F, 1.25F, 0, 0, 10,
                ProfessionalCosmeticModel.Box.of(64, 0, -1.75F, -0.45F, -1.35F, 3.5F, 1.45F, 2.7F, 0.05F));
        return b.bake(false);
    }


    private static ProfessionalCosmeticModel mobChibiAllay() {
        ProfessionalCosmeticModel.Builder b = new ProfessionalCosmeticModel.Builder("mobchibi_allay", 128, 128);
        b.bone(CosmeticAnchor.ROOT, "allay_root", null, 8.8F, 14.9F, 4.7F, 0, -16, 0);
        b.bone(CosmeticAnchor.ROOT, "allay_body", "allay_root", 0, 0, 0, 0, 0, 0,
                ProfessionalCosmeticModel.Box.of(0, 0, -1.8F, -2.2F, -1.05F, 3.6F, 4.4F, 2.1F, 0.06F),
                ProfessionalCosmeticModel.Box.of(0, 32, -2.15F, 1.35F, -1.15F, 4.3F, 1.5F, 2.3F, 0.04F));
        b.bone(CosmeticAnchor.ROOT, "allay_head", "allay_body", 0, -2.4F, -0.05F, 0, 0, 0,
                ProfessionalCosmeticModel.Box.of(32, 0, -2.55F, -2.15F, -2.0F, 5.1F, 4.3F, 4.0F, 0.08F),
                ProfessionalCosmeticModel.Box.of(96, 0, -1.55F, -0.25F, -2.25F, 0.65F, 0.65F, 0.20F, 0.01F),
                ProfessionalCosmeticModel.Box.of(96, 0, 0.90F, -0.25F, -2.25F, 0.65F, 0.65F, 0.20F, 0.01F));
        b.bone(CosmeticAnchor.ROOT, "allay_wing_left", "allay_body", -1.55F, -0.7F, 0.4F, 0, 0, -15,
                ProfessionalCosmeticModel.Box.of(64, 0, -3.6F, -0.25F, -0.24F, 3.7F, 5.2F, 0.48F, 0.03F));
        b.bone(CosmeticAnchor.ROOT, "allay_wing_right", "allay_body", 1.55F, -0.7F, 0.4F, 0, 0, 15,
                ProfessionalCosmeticModel.Box.of(64, 0, -0.1F, -0.25F, -0.24F, 3.7F, 5.2F, 0.48F, 0.03F));
        b.bone(CosmeticAnchor.ROOT, "allay_arm_left", "allay_body", -1.95F, -0.8F, -0.2F, 0, 0, -12,
                ProfessionalCosmeticModel.Box.of(96, 0, -0.38F, 0, -0.38F, 0.76F, 3.5F, 0.76F, 0.025F));
        b.bone(CosmeticAnchor.ROOT, "allay_arm_right", "allay_body", 1.95F, -0.8F, -0.2F, 0, 0, 12,
                ProfessionalCosmeticModel.Box.of(96, 0, -0.38F, 0, -0.38F, 0.76F, 3.5F, 0.76F, 0.025F));
        return b.bake(true);
    }


    private static ProfessionalCosmeticModel mobChibiZombie() {
        ProfessionalCosmeticModel.Builder b = new ProfessionalCosmeticModel.Builder("mobchibi_zombie", 128, 128);
        b.bone(CosmeticAnchor.ROOT, "zombie_root", null, 9.0F, 20.5F, 4.3F, 0, -16, 0);
        b.bone(CosmeticAnchor.ROOT, "zombie_body", "zombie_root", 0, -3.25F, 0, 4, 0, 0,
                ProfessionalCosmeticModel.Box.of(0, 0, -2.05F, -2.65F, -1.25F, 4.1F, 5.3F, 2.5F, 0.06F),
                ProfessionalCosmeticModel.Box.of(0, 32, -1.75F, -2.1F, -1.43F, 3.5F, 4.2F, 0.36F, 0.02F));
        b.bone(CosmeticAnchor.ROOT, "zombie_head", "zombie_body", 0, -2.8F, -0.05F, -3, 0, 0,
                ProfessionalCosmeticModel.Box.of(32, 0, -2.85F, -2.65F, -2.65F, 5.7F, 5.3F, 5.3F, 0.08F),
                ProfessionalCosmeticModel.Box.of(96, 0, -1.85F, -0.35F, -2.92F, 0.78F, 0.78F, 0.20F, 0.01F),
                ProfessionalCosmeticModel.Box.of(96, 0, 1.07F, -0.35F, -2.92F, 0.78F, 0.78F, 0.20F, 0.01F));
        b.bone(CosmeticAnchor.ROOT, "zombie_hair", "zombie_head", 0, -2.45F, 0, 0, 0, 0,
                ProfessionalCosmeticModel.Box.of(0, 32, -2.9F, -0.65F, -2.9F, 5.8F, 0.85F, 5.8F, 0.03F));
        b.bone(CosmeticAnchor.ROOT, "zombie_arm_left", "zombie_body", -2.25F, -1.75F, -0.65F, -58, 0, -8,
                ProfessionalCosmeticModel.Box.of(64, 0, -0.65F, 0, -0.65F, 1.3F, 4.5F, 1.3F, 0.04F));
        b.bone(CosmeticAnchor.ROOT, "zombie_arm_right", "zombie_body", 2.25F, -1.75F, -0.65F, -58, 0, 8,
                ProfessionalCosmeticModel.Box.of(64, 0, -0.65F, 0, -0.65F, 1.3F, 4.5F, 1.3F, 0.04F));
        b.bone(CosmeticAnchor.ROOT, "zombie_leg_left", "zombie_body", -1.05F, 2.1F, 0, 0, 0, 0,
                ProfessionalCosmeticModel.Box.of(0, 32, -0.65F, 0, -0.65F, 1.3F, 3.6F, 1.3F, 0.04F));
        b.bone(CosmeticAnchor.ROOT, "zombie_leg_right", "zombie_body", 1.05F, 2.1F, 0, 0, 0, 0,
                ProfessionalCosmeticModel.Box.of(0, 32, -0.65F, 0, -0.65F, 1.3F, 3.6F, 1.3F, 0.04F));
        return b.bake(false);
    }


    private static ProfessionalCosmeticModel mobChibiSkeleton() {
        ProfessionalCosmeticModel.Builder b = new ProfessionalCosmeticModel.Builder("mobchibi_skeleton", 128, 128);
        b.bone(CosmeticAnchor.ROOT, "skeleton_root", null, 9.0F, 20.3F, 4.3F, 0, -16, 0);
        b.bone(CosmeticAnchor.ROOT, "skeleton_spine", "skeleton_root", 0, -3.3F, 0, 0, 0, 0,
                ProfessionalCosmeticModel.Box.of(0, 0, -0.65F, -2.8F, -0.65F, 1.3F, 5.6F, 1.3F, 0.04F));
        b.bone(CosmeticAnchor.ROOT, "skeleton_ribs", "skeleton_spine", 0, -0.75F, 0, 0, 0, 0,
                ProfessionalCosmeticModel.Box.of(32, 0, -2.25F, -1.55F, -0.55F, 4.5F, 3.1F, 1.1F, 0.04F),
                ProfessionalCosmeticModel.Box.of(32, 32, -1.75F, 1.05F, -0.50F, 3.5F, 0.75F, 1.0F, 0.02F));
        b.bone(CosmeticAnchor.ROOT, "skeleton_head", "skeleton_spine", 0, -3.0F, 0, 0, 0, 0,
                ProfessionalCosmeticModel.Box.of(64, 0, -2.75F, -2.60F, -2.60F, 5.5F, 5.2F, 5.2F, 0.08F),
                ProfessionalCosmeticModel.Box.of(96, 0, -1.75F, -0.45F, -2.88F, 1.0F, 1.0F, 0.20F, 0.01F),
                ProfessionalCosmeticModel.Box.of(96, 0, 0.75F, -0.45F, -2.88F, 1.0F, 1.0F, 0.20F, 0.01F));
        b.bone(CosmeticAnchor.ROOT, "skeleton_arm_left", "skeleton_spine", -2.2F, -1.85F, 0, 0, 0, -8,
                ProfessionalCosmeticModel.Box.of(0, 32, -0.48F, 0, -0.48F, 0.96F, 4.8F, 0.96F, 0.03F));
        b.bone(CosmeticAnchor.ROOT, "skeleton_arm_right", "skeleton_spine", 2.2F, -1.85F, 0, 0, 0, 8,
                ProfessionalCosmeticModel.Box.of(0, 32, -0.48F, 0, -0.48F, 0.96F, 4.8F, 0.96F, 0.03F));
        b.bone(CosmeticAnchor.ROOT, "skeleton_pelvis", "skeleton_spine", 0, 2.3F, 0, 0, 0, 0,
                ProfessionalCosmeticModel.Box.of(48, 32, -1.65F, -0.55F, -0.65F, 3.3F, 1.1F, 1.3F, 0.04F));
        b.bone(CosmeticAnchor.ROOT, "skeleton_leg_left", "skeleton_pelvis", -0.9F, 0.35F, 0, 0, 0, 0,
                ProfessionalCosmeticModel.Box.of(16, 32, -0.48F, 0, -0.48F, 0.96F, 3.9F, 0.96F, 0.03F));
        b.bone(CosmeticAnchor.ROOT, "skeleton_leg_right", "skeleton_pelvis", 0.9F, 0.35F, 0, 0, 0, 0,
                ProfessionalCosmeticModel.Box.of(16, 32, -0.48F, 0, -0.48F, 0.96F, 3.9F, 0.96F, 0.03F));
        return b.bake(false);
    }


}
