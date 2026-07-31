package com.arlight.cosmeticscurios.client;

import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Player;

/** Animaciones secundarias independientes de los 20 TPS del servidor. */
public final class ProfessionalCosmeticAnimations {
    private ProfessionalCosmeticAnimations() { }

    public static void apply(ProfessionalCosmeticModel model,
                             Player player,
                             float partialTick,
                             float limbSwing,
                             float limbSwingAmount,
                             float ageInTicks,
                             PetRenderController.Motion motion,
                             boolean simplified) {
        if (model == null || player == null) return;
        model.resetPose();

        float time = ageInTicks / 20.0F;
        float move = Mth.clamp(limbSwingAmount, 0.0F, 1.0F);
        if (isCompanion(model.id())) {
            move = switch (motion) {
                case RUN -> 1.0F;
                case WALK -> 0.48F;
                case SWIM, FLY -> 0.35F;
                default -> 0.0F;
            };
        }
        if (simplified) move = 0.0F;
        float walk = Mth.sin(limbSwing * 0.6662F) * move;
        float airborne = player.onGround() ? 0.0F : 1.0F;
        float activity = switch (motion) {
            case RUN -> 1.0F;
            case WALK -> 0.55F;
            case SWIM, FLY -> 0.7F;
            default -> 0.15F;
        };

        switch (model.id()) {
            case "pony_horn_crown" -> {
                addZ(model, "ear_left", Mth.sin(time * 3.1F) * 2.5F);
                addZ(model, "ear_right", -Mth.sin(time * 3.1F) * 2.5F);
                addX(model, "ribbon_left", Mth.sin(time * 4.3F) * 4.0F + walk * 5.0F);
                addX(model, "ribbon_right", -Mth.sin(time * 4.3F) * 4.0F - walk * 5.0F);
            }
            case "pony_pastel_outfit" -> {
                addX(model, "skirt_front_left", Mth.sin(time * 5.2F) * 1.2F + Math.abs(walk) * 3.0F);
                addX(model, "skirt_front_right", Mth.sin(time * 5.2F) * 1.2F + Math.abs(walk) * 3.0F);
                addX(model, "skirt_back_left", -Mth.sin(time * 5.2F) * 1.0F - Math.abs(walk) * 2.5F);
                addX(model, "skirt_back_right", -Mth.sin(time * 5.2F) * 1.0F - Math.abs(walk) * 2.5F);
                addX(model, "hood_left", Mth.sin(time * 3.0F) * 1.8F + walk * 2.0F);
                addX(model, "hood_right", -Mth.sin(time * 3.0F) * 1.8F - walk * 2.0F);
            }
            case "pony_axolotl_charm" -> {
                addZ(model, "gill_left", Mth.sin(time * 7.0F) * 5.0F);
                addZ(model, "gill_right", -Mth.sin(time * 7.0F) * 5.0F);
                moveY(model, "charm_root", Mth.sin(time * 3.2F) * 0.08F);
                float winkPhase = time % 3.8F;
                if (winkPhase > 3.45F) moveY(model, "wink_lid", 0.62F);
            }
            case "pony_orbit" -> {
                addY(model, "pony_orbit_root", time * 74.0F);
                moveY(model, "pony_body", Mth.sin(time * 5.0F) * 0.55F);
                addX(model, "pony_leg_left", Mth.sin(time * 10.0F) * 18.0F);
                addX(model, "pony_leg_right", -Mth.sin(time * 10.0F) * 18.0F);
                addY(model, "pony_tail", Mth.sin(time * 6.2F) * 12.0F);
                addZ(model, "pony_ear_left", Mth.sin(time * 5.0F) * 3.5F);
                addZ(model, "pony_ear_right", -Mth.sin(time * 5.0F) * 3.5F);
            }
            case "pony_axolotl_slippers" -> {
                float bounce = Math.abs(walk) * 0.18F;
                moveY(model, "left_slipper", -bounce);
                moveY(model, "right_slipper", bounce);
                addZ(model, "left_gill_left", Mth.sin(time * 7.5F) * 5.0F);
                addZ(model, "left_gill_right", -Mth.sin(time * 7.5F) * 5.0F);
                addZ(model, "right_gill_left", -Mth.sin(time * 7.5F) * 5.0F);
                addZ(model, "right_gill_right", Mth.sin(time * 7.5F) * 5.0F);
            }
            case "pony_whale_tail" -> {
                addX(model, "tail_base", Mth.sin(time * 3.5F) * 4.0F + move * 2.5F);
                addY(model, "tail_base", Mth.sin(time * 2.1F) * 4.5F);
                addX(model, "tail_mid", Mth.sin(time * 3.5F + 0.7F) * 4.0F);
                addX(model, "tail_tip", Mth.sin(time * 3.5F + 1.4F) * 5.0F);
                addY(model, "tail_fluke_connector", Mth.sin(time * 3.8F) * 2.0F);
                addZ(model, "tail_fin_left", Mth.sin(time * 5.0F) * 4.0F);
                addZ(model, "tail_fin_right", -Mth.sin(time * 5.0F) * 4.0F);
                addY(model, "tail_bow", Mth.sin(time * 2.7F) * 4.0F);
            }
            case "pony_axolotl_pal" -> {
                moveY(model, "axolotl_root", Mth.sin(time * 4.2F) * 0.35F - airborne * 0.25F);
                addY(model, "axolotl_root", Mth.sin(time * 1.8F) * 5.0F);
                addZ(model, "gill_left", Mth.sin(time * 7.5F) * 5.0F);
                addZ(model, "gill_right", -Mth.sin(time * 7.5F) * 5.0F);
                addX(model, "axolotl_tail", Mth.sin(time * 5.8F) * 8.0F);
                addZ(model, "leg_front_left", walk * 8.0F);
                addZ(model, "leg_front_right", -walk * 8.0F);
            }
            case "pony_whale_stars" -> {
                addY(model, "orbit_whale", time * 55.0F);
                moveY(model, "orbit_whale", Mth.sin(time * 4.0F) * 0.8F);
                addZ(model, "whale_tail_left", Mth.sin(time * 8.0F) * 8.0F);
                addZ(model, "whale_tail_right", -Mth.sin(time * 8.0F) * 8.0F);
                addY(model, "star_one", time * 90.0F);
                addY(model, "star_two", -time * 110.0F);
                addY(model, "star_three", time * 130.0F);
            }
            case "somita_bat_clip" -> {
                addZ(model, "clip_wing_left", Mth.sin(time * 10.0F) * 9.0F);
                addZ(model, "clip_wing_right", -Mth.sin(time * 10.0F) * 9.0F);
                moveY(model, "clip_body", Mth.sin(time * 3.0F) * 0.08F);
            }
            case "somita_night_outfit" -> {
                addX(model, "somita_skirt_front_left", Mth.sin(time * 5.0F) * 1.3F + Math.abs(walk) * 3.0F);
                addX(model, "somita_skirt_front_right", Mth.sin(time * 5.0F) * 1.3F + Math.abs(walk) * 3.0F);
                addX(model, "somita_skirt_back_left", -Mth.sin(time * 5.0F) * 1.1F - Math.abs(walk) * 2.5F);
                addX(model, "somita_skirt_back_right", -Mth.sin(time * 5.0F) * 1.1F - Math.abs(walk) * 2.5F);
            }
            case "somita_vampire_tail" -> {
                addY(model, "vampire_tail_base", Mth.sin(time * 3.1F) * 10.0F + walk * 6.0F);
                addY(model, "vampire_tail_mid", Mth.sin(time * 3.1F + 0.75F) * 13.0F);
                addY(model, "vampire_tail_tip", Mth.sin(time * 3.1F + 1.45F) * 16.0F);
                addZ(model, "vampire_heart_tip", Mth.sin(time * 4.6F) * 6.0F);
            }
            case "somita_numbers_orbit" -> {
                float orbit = -time * 22.0F;
                addY(model, "numbers_orbit_root", orbit);
                addY(model, "number_pair", -orbit);
                moveY(model, "number_pair", Mth.sin(time * 2.2F) * 0.45F);
                addZ(model, "number_pair", Mth.sin(time * 1.8F) * 3.5F);
                addY(model, "number_star", time * 95.0F);
            }
            case "somita_bat_slippers" -> {
                float flap = Mth.sin(time * 10.0F) * 14.0F;
                addZ(model, "left_bat_wing_left", flap);
                addZ(model, "left_bat_wing_right", -flap);
                addZ(model, "right_bat_wing_left", -flap);
                addZ(model, "right_bat_wing_right", flap);
                moveY(model, "left_bat_slipper", -Math.abs(walk) * 0.16F);
                moveY(model, "right_bat_slipper", Math.abs(walk) * 0.16F);
            }
            case "somita_bat_wings" -> {
                float flap = Mth.sin(time * (airborne > 0 ? 10.0F : 5.5F)) * (airborne > 0 ? 15.0F : 7.0F);
                addY(model, "wing_left_base", flap + move * 7.0F);
                addY(model, "wing_right_base", -flap - move * 7.0F);
                // Las membranas son hijas del marco: solo reciben una flexión secundaria.
                addZ(model, "wing_left_membrane_top", flap * 0.18F);
                addZ(model, "wing_right_membrane_top", -flap * 0.18F);
                addZ(model, "wing_left_membrane_low", flap * 0.12F);
                addZ(model, "wing_right_membrane_low", -flap * 0.12F);
            }
            case "somita_bat_pal" -> {
                moveY(model, "bat_pal_body", Mth.sin(time * 5.5F) * 0.45F - airborne * 0.2F);
                addY(model, "bat_pal_body", Mth.sin(time * 2.0F) * 5.0F);
                float flap = Mth.sin(time * 15.0F) * 20.0F;
                addZ(model, "bat_pal_wing_left", flap);
                addZ(model, "bat_pal_wing_right", -flap);
            }
            case "somita_crimson_hearts" -> {
                addY(model, "heart_orbit_one", time * 70.0F);
                addY(model, "heart_orbit_two", time * 70.0F);
                addY(model, "heart_orbit_three", time * 70.0F);
                moveY(model, "heart_orbit_one", Mth.sin(time * 4.0F) * 0.8F);
                moveY(model, "heart_orbit_two", Mth.sin(time * 4.0F + 2.1F) * 0.8F);
                moveY(model, "heart_orbit_three", Mth.sin(time * 4.0F + 4.2F) * 0.8F);
                addY(model, "mist_crystal", -time * 45.0F);
            }
            case "bingo_corrupted_crown" -> {
                moveY(model, "emerald_spike", Mth.sin(time * 4.0F) * 0.30F);
                moveY(model, "gold_spike", Mth.sin(time * 4.0F + 2.1F) * 0.35F);
                moveY(model, "amethyst_spike", Mth.sin(time * 4.0F + 4.2F) * 0.30F);
                addY(model, "emerald_spike", Mth.sin(time * 2.2F) * 2.0F);
                addY(model, "gold_spike", -Mth.sin(time * 2.2F) * 2.0F);
                addY(model, "amethyst_spike", Mth.sin(time * 2.2F) * 2.0F);
            }
            case "bingo_corrupted_outfit" -> {
                addX(model, "corrupt_tabard_front", Mth.sin(time * 5.5F) * 1.0F + Math.abs(walk) * 3.2F);
                addX(model, "corrupt_tabard_back", -Mth.sin(time * 5.5F) * 0.9F - Math.abs(walk) * 2.6F);
            }
            case "bingo_void_wings" -> {
                float flap = Mth.sin(time * (airborne > 0 ? 8.0F : 4.0F)) * (airborne > 0 ? 13.0F : 6.0F);
                addY(model, "void_wing_left_base", flap + move * 10.0F);
                addY(model, "void_wing_right_base", -flap - move * 10.0F);
                addZ(model, "void_wing_left_crystals", flap * 0.42F);
                addZ(model, "void_wing_right_crystals", -flap * 0.42F);
                addZ(model, "void_wing_left_spikes", flap * 0.25F);
                addZ(model, "void_wing_right_spikes", -flap * 0.25F);
            }
            case "bingo_amethyst_eye" -> {
                moveY(model, "eye_orb", Mth.sin(time * 4.5F) * 0.5F - airborne * 0.25F);
                addY(model, "eye_orb", Mth.sin(time * 1.8F) * 9.0F);
                addY(model, "eye_crystal_top", time * 60.0F);
                addX(model, "eye_tendril_one", Mth.sin(time * 5.0F) * 9.0F);
                addX(model, "eye_tendril_two", -Mth.sin(time * 5.0F) * 9.0F);
                addZ(model, "eye_tendril_one", Mth.sin(time * 3.4F) * 5.0F);
                addZ(model, "eye_tendril_two", -Mth.sin(time * 3.4F) * 5.0F);
            }
            case "bingo_emerald_orbit" -> animateOrbit(model, time, "gem_core");
            case "bingo_gold_orbit" -> {
                animateOrbit(model, time, "gold_core");
                addZ(model, "gold_flame_one", Mth.sin(time * 8.0F) * 9.0F);
                addZ(model, "gold_flame_two", -Mth.sin(time * 8.0F) * 9.0F);
            }
            case "bingo_amethyst_orbit" -> {
                animateOrbit(model, time, "amethyst_core");
                addZ(model, "shard_left", Mth.sin(time * 6.0F) * 6.0F);
                addZ(model, "shard_right", -Mth.sin(time * 6.0F) * 6.0F);
            }
            case "mobchibi_creeper" -> {
                moveY(model, "creeper_root", Mth.sin(time * (3.2F + activity * 3.0F)) * (0.10F + activity * 0.18F));
                addY(model, "creeper_head", Mth.sin(time * 2.0F) * 5.0F);
                for (int i = 0; i < 4; i++) addX(model, "creeper_leg_" + i,
                        Mth.sin(time * 8.0F + i * 1.57F) * (4.0F + move * 12.0F));
            }
            case "mobchibi_enderman" -> {
                moveY(model, "enderman_root", Mth.sin(time * (2.8F + activity * 2.5F)) * (0.10F + activity * 0.16F));
                addY(model, "enderman_head", Mth.sin(time * 1.6F) * 11.0F);
                addX(model, "enderman_head", Mth.sin(time * 1.25F) * 3.0F);
                addX(model, "enderman_arm_left", 7.0F + Mth.sin(time * 4.0F) * 7.0F + walk * 12.0F);
                addX(model, "enderman_arm_right", 7.0F - Mth.sin(time * 4.0F) * 7.0F - walk * 12.0F);
                addY(model, "enderman_hand_left", Mth.sin(time * 2.1F) * 5.0F);
                addY(model, "enderman_hand_right", -Mth.sin(time * 2.1F) * 5.0F);
                addX(model, "enderman_leg_left", walk * 18.0F);
                addX(model, "enderman_leg_right", -walk * 18.0F);
            }
            case "mobchibi_bee" -> {
                moveY(model, "bee_root", Mth.sin(time * (5.0F + activity * 4.0F)) * (0.48F + activity * 0.32F));
                float wing = Mth.sin(time * 25.0F) * 36.0F;
                addZ(model, "bee_wing_left", wing);
                addZ(model, "bee_wing_right", -wing);
                addY(model, "bee_body", Mth.sin(time * 2.0F) * 5.0F);
                addZ(model, "bee_antenna_left", Mth.sin(time * 4.4F) * 7.0F);
                addZ(model, "bee_antenna_right", -Mth.sin(time * 4.4F) * 7.0F);
                for (int i = 0; i < 3; i++) {
                    addZ(model, "bee_leg_left_" + i, Mth.sin(time * 6.0F + i) * 8.0F);
                    addZ(model, "bee_leg_right_" + i, -Mth.sin(time * 6.0F + i) * 8.0F);
                }
            }
            case "mobchibi_axolotl" -> {
                moveY(model, "pet_axolotl_root", Mth.sin(time * (3.0F + activity * 2.0F)) * (0.10F + activity * 0.18F));
                addZ(model, "pet_gill_left", Mth.sin(time * 7.2F) * 9.0F);
                addZ(model, "pet_gill_right", -Mth.sin(time * 7.2F) * 9.0F);
                addY(model, "pet_axolotl_tail", Mth.sin(time * 5.5F) * 15.0F);
                addY(model, "pet_axolotl_tail_fin", Mth.sin(time * 5.5F + 0.8F) * 20.0F);
                addX(model, "pet_axolotl_front_left", walk * 12.0F);
                addX(model, "pet_axolotl_front_right", -walk * 12.0F);
                addX(model, "pet_axolotl_back_left", -walk * 10.0F);
                addX(model, "pet_axolotl_back_right", walk * 10.0F);
            }
            case "mobchibi_slime" -> {
                boolean moving = motion == PetRenderController.Motion.WALK
                        || motion == PetRenderController.Motion.RUN;
                float frequency = motion == PetRenderController.Motion.RUN ? 12.0F
                        : moving ? 8.0F : 4.0F;
                float amplitude = motion == PetRenderController.Motion.RUN ? 0.82F
                        : moving ? 0.56F : 0.22F;
                float bounce = Math.abs(Mth.sin(time * frequency)) * amplitude;
                moveY(model, "slime_root", -bounce);
                addY(model, "slime_core", time * (moving ? 42.0F : 20.0F));
            }
            case "mobchibi_warden" -> {
                moveY(model, "warden_root", Mth.sin(time * 3.2F) * 0.15F);
                addZ(model, "warden_horn_left", Mth.sin(time * 5.2F) * 8.0F);
                addZ(model, "warden_horn_right", -Mth.sin(time * 5.2F) * 8.0F);
                addZ(model, "warden_horn_left_tip", Mth.sin(time * 5.2F + 0.5F) * 11.0F);
                addZ(model, "warden_horn_right_tip", -Mth.sin(time * 5.2F + 0.5F) * 11.0F);
                addX(model, "warden_arm_left", walk * 15.0F);
                addX(model, "warden_arm_right", -walk * 15.0F);
                addX(model, "warden_leg_left", -walk * 12.0F);
                addX(model, "warden_leg_right", walk * 12.0F);
            }
            case "dino_pajama_hat" -> {
                addZ(model, "dino_spike_one", Mth.sin(time * 3.8F) * 3.0F);
                addZ(model, "dino_spike_two", -Mth.sin(time * 3.8F + 0.6F) * 3.0F);
                addX(model, "dino_hat_jaw", (Mth.sin(time * 1.6F) + 1.0F) * 1.5F);
            }
            case "axolotl_pajama_hat" -> {
                addZ(model, "axo_gill_left", Mth.sin(time * 6.0F) * 7.0F);
                addZ(model, "axo_gill_right", -Mth.sin(time * 6.0F) * 7.0F);
            }
            case "dino_pajama_chest" -> {
                addZ(model, "pajama_back_spikes", Mth.sin(time * 4.0F) * 2.5F + move * 3.0F);
            }
            case "axolotl_pajama_chest" -> {
                addY(model, "pajama_bow", Mth.sin(time * 2.6F) * 5.0F);
                addZ(model, "pajama_bow", Mth.sin(time * 4.2F) * 2.0F);
            }
            case "dino_pajama_legs" -> {
                addY(model, "pajama_dino_tail", Mth.sin(time * 4.8F) * 11.0F + walk * 8.0F);
                addX(model, "pajama_dino_tail", Math.abs(walk) * 4.0F);
            }
            case "axolotl_pajama_legs" -> {
                addZ(model, "pajama_fin_left", Mth.sin(time * 5.5F) * 8.0F);
                addZ(model, "pajama_fin_right", -Mth.sin(time * 5.5F) * 8.0F);
            }
            case "dino_pajama_feet", "axolotl_pajama_feet" -> {
                moveY(model, "left_pajama_slipper", -Math.abs(walk) * 0.18F);
                moveY(model, "right_pajama_slipper", Math.abs(walk) * 0.18F);
                addZ(model, "left_gill_left", Mth.sin(time * 7.0F) * 5.0F);
                addZ(model, "left_gill_right", -Mth.sin(time * 7.0F) * 5.0F);
                addZ(model, "right_gill_left", -Mth.sin(time * 7.0F) * 5.0F);
                addZ(model, "right_gill_right", Mth.sin(time * 7.0F) * 5.0F);
            }
            case "pastel_heart_backpack" -> {
                float flap = Mth.sin(time * 7.5F) * 12.0F;
                addZ(model, "pack_wing_left", flap);
                addZ(model, "pack_wing_right", -flap);
                moveY(model, "heart_pack", Mth.sin(time * 2.8F) * 0.12F);
            }
            case "bunny_ear_hood" -> {
                addX(model, "bunny_ear_left", Mth.sin(time * 3.0F) * 5.0F + move * 4.0F);
                addX(model, "bunny_ear_right", Mth.sin(time * 3.0F + 0.8F) * 5.0F + move * 4.0F);
            }
            case "moon_star_cardigan" -> {
                moveY(model, "cardigan_body", Mth.sin(time * 2.5F) * 0.04F);
            }
            case "cloud_skirt" -> {
                addX(model, "cloud_skirt", Mth.sin(time * 4.0F) * 2.0F + Math.abs(walk) * 4.5F);
            }
            case "cloud_slippers" -> {
                float bounce = Math.abs(Mth.sin(time * 5.0F)) * 0.14F + Math.abs(walk) * 0.14F;
                moveY(model, "left_cloud", -bounce);
                moveY(model, "right_cloud", bounce);
            }
            case "floating_pastel_ribbon" -> {
                addY(model, "ribbon_orbit", time * 30.0F);
                addY(model, "ribbon_core", -time * 30.0F);
                moveY(model, "ribbon_core", Mth.sin(time * 3.4F) * 0.55F);
                addZ(model, "ribbon_star", time * 80.0F);
            }
            case "mobchibi_chicken" -> {
                moveY(model, "chicken_root", Mth.sin(time * 5.0F) * 0.18F);
                float wing = Mth.sin(time * 12.0F) * 21.0F;
                addZ(model, "chicken_wing_left", wing);
                addZ(model, "chicken_wing_right", -wing);
                addX(model, "chicken_leg_left", walk * 18.0F);
                addX(model, "chicken_leg_right", -walk * 18.0F);
                addX(model, "chicken_head", Mth.sin(time * 2.2F) * 5.0F);
                addX(model, "chicken_tail", Mth.sin(time * 5.0F) * 7.0F);
            }
            case "mobchibi_fox" -> {
                moveY(model, "fox_root", Mth.sin(time * 3.5F) * 0.14F);
                addY(model, "fox_tail", Mth.sin(time * 4.5F) * 16.0F);
                addY(model, "fox_tail_tip", Mth.sin(time * 4.5F + 0.75F) * 22.0F);
                addX(model, "fox_head", Mth.sin(time * 1.8F) * 4.0F);
                for (int i=0;i<4;i++) addX(model, "fox_leg_"+i,
                        Mth.sin(time * 7.0F + i * 1.57F) * (4.0F + move * 12.0F));
            }
            case "mobchibi_frog" -> {
                float hop = (Mth.sin(time * 4.0F) + 1.0F) * 0.32F;
                moveY(model, "frog_root", -hop);
                addZ(model, "frog_leg_left", Mth.sin(time * 4.0F) * 10.0F);
                addZ(model, "frog_leg_right", -Mth.sin(time * 4.0F) * 10.0F);
                addZ(model, "frog_arm_left", -Mth.sin(time * 4.0F) * 8.0F);
                addZ(model, "frog_arm_right", Mth.sin(time * 4.0F) * 8.0F);
            }
            case "mobchibi_allay" -> {
                moveY(model, "allay_root", Mth.sin(time * 5.5F) * 0.58F);
                float flap = Mth.sin(time * 20.0F) * 32.0F;
                addZ(model, "allay_wing_left", flap);
                addZ(model, "allay_wing_right", -flap);
                addX(model, "allay_arm_left", Mth.sin(time * 4.0F) * 8.0F);
                addX(model, "allay_arm_right", -Mth.sin(time * 4.0F) * 8.0F);
                addY(model, "allay_head", Mth.sin(time * 1.8F) * 6.0F);
            }
            case "mobchibi_zombie" -> {
                moveY(model, "zombie_root", Mth.sin(time * 3.2F) * 0.12F);
                addY(model, "zombie_head", Mth.sin(time * 1.5F) * 7.0F);
                addX(model, "zombie_leg_left", walk * 20.0F);
                addX(model, "zombie_leg_right", -walk * 20.0F);
                addY(model, "zombie_arm_left", Mth.sin(time * 3.0F) * 4.0F);
                addY(model, "zombie_arm_right", -Mth.sin(time * 3.0F) * 4.0F);
            }
            case "mobchibi_skeleton" -> {
                moveY(model, "skeleton_root", Mth.sin(time * 3.8F) * 0.10F);
                addY(model, "skeleton_head", Mth.sin(time * 2.0F) * 6.0F);
                addX(model, "skeleton_arm_left", walk * 18.0F);
                addX(model, "skeleton_arm_right", -walk * 18.0F);
                addX(model, "skeleton_leg_left", -walk * 16.0F);
                addX(model, "skeleton_leg_right", walk * 16.0F);
            }
            case "swansito_dino_tail" -> {
                float energy = 1.0F + move * 1.8F;
                addY(model, "dino_tail_base", Mth.sin(time * 3.2F) * 4.0F * energy);
                addY(model, "dino_tail_mid", Mth.sin(time * 3.2F + 0.65F) * 6.0F * energy);
                addY(model, "dino_tail_tip", Mth.sin(time * 3.2F + 1.25F) * 8.0F * energy);
                addY(model, "dino_tail_end", Mth.sin(time * 3.2F + 1.8F) * 10.0F * energy);
                addZ(model, "tail_spike_base", Mth.sin(time * 2.4F) * 1.8F);
                addZ(model, "tail_spike_mid", Mth.sin(time * 2.4F + 0.7F) * 2.2F);
            }
            case "swansito_dino_hands" -> {
                float clawPulse = Mth.sin(time * 4.0F) * 2.0F + Math.abs(walk) * 5.0F;
                addX(model, "dino_hand_left", -Math.abs(walk) * 3.0F);
                addX(model, "dino_hand_right", -Math.abs(walk) * 3.0F);
                addZ(model, "left_claw_outer", clawPulse);
                addZ(model, "left_claw_inner", -clawPulse * 0.65F);
                addZ(model, "right_claw_outer", -clawPulse);
                addZ(model, "right_claw_inner", clawPulse * 0.65F);
            }
            case "swansito_mini_trex" -> {
                boolean walkingState = motion == PetRenderController.Motion.WALK
                        || motion == PetRenderController.Motion.RUN;
                boolean runningState = motion == PetRenderController.Motion.RUN;
                float cadence = runningState ? time * 20.0F : walkingState ? time * 11.0F : time * 3.0F;
                float stride = runningState ? 38.0F : walkingState ? 24.0F : 2.5F;
                float bob = runningState ? 0.72F : walkingState ? 0.38F : 0.12F;
                moveY(model, "trex_root", Mth.sin(cadence * 2.0F) * bob);
                addX(model, "trex_body", Mth.sin(cadence * 2.0F) * (runningState ? 4.5F : 2.0F));
                addX(model, "trex_head", Mth.sin(cadence * 2.0F + 0.45F) * (runningState ? 6.0F : 3.0F));
                addX(model, "trex_leg_left", Mth.sin(cadence) * stride);
                addX(model, "trex_leg_right", -Mth.sin(cadence) * stride);
                addX(model, "trex_foot_left", -Mth.sin(cadence) * stride * 0.42F);
                addX(model, "trex_foot_right", Mth.sin(cadence) * stride * 0.42F);
                addX(model, "trex_arm_left", -12.0F + Mth.sin(cadence + 0.6F) * 8.0F);
                addX(model, "trex_arm_right", -12.0F - Mth.sin(cadence + 0.6F) * 8.0F);
                addY(model, "trex_tail_base", Mth.sin(cadence * 0.55F) * (runningState ? 12.0F : 7.0F));
                addY(model, "trex_tail_mid", Mth.sin(cadence * 0.55F + 0.75F) * (runningState ? 16.0F : 9.0F));
                addY(model, "trex_tail_tip", Mth.sin(cadence * 0.55F + 1.45F) * (runningState ? 20.0F : 11.0F));
                addX(model, "trex_jaw", runningState ? 16.0F + Mth.sin(time * 13.0F) * 3.0F
                        : walkingState ? 6.0F : 2.0F + (Mth.sin(time * 2.1F) + 1.0F) * 1.6F);
                if (!walkingState) {
                    addY(model, "trex_head", Mth.sin(time * 1.7F) * 5.0F);
                    moveY(model, "trex_body", Mth.sin(time * 2.0F) * 0.18F);
                }
                if (motion == PetRenderController.Motion.SWIM || motion == PetRenderController.Motion.FLY) {
                    addX(model, "trex_body", -12.0F);
                    addX(model, "trex_leg_left", 20.0F);
                    addX(model, "trex_leg_right", 20.0F);
                    addY(model, "trex_tail_base", Mth.sin(time * 7.0F) * 16.0F);
                }
            }
            case "swansito_meat_chase_aura" -> {
                float chaseSpeed = 72.0F + move * 65.0F;
                addY(model, "chase_orbit", time * chaseSpeed);
                moveY(model, "chase_dino_body", Mth.sin(time * 9.0F) * 0.38F);
                addX(model, "chase_leg_left", Mth.sin(time * 16.0F) * 24.0F);
                addX(model, "chase_leg_right", -Mth.sin(time * 16.0F) * 24.0F);
                addY(model, "chase_tail", Mth.sin(time * 8.0F) * 13.0F);
                moveY(model, "chase_meat", Mth.sin(time * 6.5F) * 0.65F);
                addZ(model, "chase_meat", time * 95.0F);
            }
            default -> { }
        }

        if (isCompanion(model.id())) {
            applyCompanionMotionPose(model, time, motion, simplified);
        }
    }

    private static void applyCompanionMotionPose(ProfessionalCosmeticModel model, float time,
                                                  PetRenderController.Motion motion,
                                                  boolean simplified) {
        String root = companionRoot(model.id());
        if (root.isBlank()) return;
        switch (motion) {
            case RUN -> {
                addX(model, root, 4.0F + Mth.sin(time * 16.0F) * 1.8F);
                if (!simplified) moveY(model, root, Math.abs(Mth.sin(time * 16.0F)) * -0.10F);
            }
            case WALK -> addX(model, root, 1.5F);
            case SWIM -> {
                addX(model, root, -13.0F);
                addY(model, root, Mth.sin(time * 4.0F) * 5.0F);
                if (!simplified) moveY(model, root, Mth.sin(time * 5.0F) * 0.12F);
            }
            case FLY -> {
                addX(model, root, -8.0F);
                addZ(model, root, Mth.sin(time * 3.2F) * 3.0F);
                if (!simplified) moveY(model, root, Mth.sin(time * 4.5F) * 0.16F);
            }
            case IDLE -> {
                if (!simplified) addY(model, root, Mth.sin(time * 1.2F) * 1.5F);
            }
        }
    }

    private static String companionRoot(String id) {
        return switch (id) {
            case "swansito_mini_trex" -> "trex_root";
            case "mobchibi_creeper" -> "creeper_root";
            case "mobchibi_enderman" -> "enderman_root";
            case "mobchibi_bee" -> "bee_root";
            case "mobchibi_axolotl" -> "pet_axolotl_root";
            case "mobchibi_slime" -> "slime_root";
            case "mobchibi_warden" -> "warden_root";
            case "mobchibi_chicken" -> "chicken_root";
            case "mobchibi_fox" -> "fox_root";
            case "mobchibi_frog" -> "frog_root";
            case "mobchibi_allay" -> "allay_root";
            case "mobchibi_zombie" -> "zombie_root";
            case "mobchibi_skeleton" -> "skeleton_root";
            default -> "";
        };
    }

    private static boolean isCompanion(String id) {
        return id != null && (id.equals("swansito_mini_trex") || id.startsWith("mobchibi_"));
    }

    private static void animateOrbit(ProfessionalCosmeticModel model, float time, String core) {
        addY(model, core, time * 95.0F);
        addX(model, core, time * 48.0F);
        addZ(model, core, time * 32.0F);
        moveY(model, core, Mth.sin(time * 4.2F) * 0.8F);
        addX(model, "corrupt_ring", time * 40.0F);
        addY(model, "corrupt_ring", time * 95.0F);
        addZ(model, "corrupt_ring", time * 60.0F);
    }

    private static void addX(ProfessionalCosmeticModel model, String bone, float degrees) {
        ModelPart part = model.bone(bone);
        if (part != null) part.xRot += degrees * Mth.DEG_TO_RAD;
    }

    private static void addY(ProfessionalCosmeticModel model, String bone, float degrees) {
        ModelPart part = model.bone(bone);
        if (part != null) part.yRot += degrees * Mth.DEG_TO_RAD;
    }

    private static void addZ(ProfessionalCosmeticModel model, String bone, float degrees) {
        ModelPart part = model.bone(bone);
        if (part != null) part.zRot += degrees * Mth.DEG_TO_RAD;
    }

    private static void moveY(ProfessionalCosmeticModel model, String bone, float pixels) {
        ModelPart part = model.bone(bone);
        if (part != null) part.y += pixels;
    }
}
