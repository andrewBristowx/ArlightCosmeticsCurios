package com.arlight.cosmeticscurios;

import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.List;

public final class CosmeticItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(ArlightCosmeticsCurios.MOD_ID);

    public static final DeferredItem<Item> PONY_HORN_CROWN = item("pony_horn_crown", "arlight_head", "pony_horn_crown");
    // Se conservan los ids históricos para no invalidar objetos ya reclamados.
    public static final DeferredItem<Item> PONY_PASTEL_CHEST = item("pony_pastel_chest", "arlight_chest", "pony_pastel_chest");
    public static final DeferredItem<Item> PONY_PASTEL_LEGS = item("pony_pastel_legs", "arlight_aura", "pony_pastel_legs");
    public static final DeferredItem<Item> PONY_PASTEL_FEET = item("pony_pastel_feet", "arlight_feet", "pony_pastel_feet");
    public static final DeferredItem<Item> PONY_WHALE_TAIL = item("pony_whale_tail", "arlight_tail", "pony_whale_tail");
    public static final DeferredItem<Item> PONY_AXOLOTL_PAL = item("pony_axolotl_pal", "arlight_shoulder", "pony_axolotl_pal");
    public static final DeferredItem<Item> PONY_WHALE_STARS = item("pony_whale_stars", "arlight_aura", "pony_whale_stars");
    public static final DeferredItem<Item> PONY_PASTEL_STEPS = item("pony_pastel_steps", "arlight_trail", "pony_pastel_steps");

    public static final DeferredItem<Item> SOMITA_BAT_CLIP = item("somita_bat_clip", "arlight_head", "somita_bat_clip");
    public static final DeferredItem<Item> SOMITA_NIGHT_CHEST = item("somita_night_chest", "arlight_tail", "somita_night_chest");
    public static final DeferredItem<Item> SOMITA_NIGHT_LEGS = item("somita_night_legs", "arlight_aura", "somita_night_legs");
    public static final DeferredItem<Item> SOMITA_NIGHT_FEET = item("somita_night_feet", "arlight_feet", "somita_night_feet");
    public static final DeferredItem<Item> SOMITA_BAT_WINGS = item("somita_bat_wings", "arlight_back", "somita_bat_wings");
    public static final DeferredItem<Item> SOMITA_BAT_PAL = item("somita_bat_pal", "arlight_shoulder", "somita_bat_pal");
    public static final DeferredItem<Item> SOMITA_CRIMSON_HEARTS = item("somita_crimson_hearts", "arlight_aura", "somita_crimson_hearts");
    public static final DeferredItem<Item> SOMITA_NIGHT_STEPS = item("somita_night_steps", "arlight_trail", "somita_night_steps");

    public static final DeferredItem<Item> BINGO_CORRUPTED_CROWN = item("bingo_corrupted_crown", "arlight_head", "bingo_corrupted_crown");
    public static final DeferredItem<Item> BINGO_CORRUPTED_CHEST = item("bingo_corrupted_chest", "arlight_chest", "bingo_corrupted_outfit");
    public static final DeferredItem<Item> BINGO_CORRUPTED_LEGS = item("bingo_corrupted_legs", "arlight_legs", "bingo_corrupted_outfit");
    public static final DeferredItem<Item> BINGO_CORRUPTED_FEET = item("bingo_corrupted_feet", "arlight_feet", "bingo_corrupted_outfit");
    public static final DeferredItem<Item> BINGO_VOID_WINGS = item("bingo_void_wings", "arlight_back", "bingo_void_wings");
    public static final DeferredItem<Item> BINGO_AMETHYST_EYE = item("bingo_amethyst_eye", "arlight_shoulder", "bingo_amethyst_eye");
    public static final DeferredItem<Item> BINGO_EMERALD_ORBIT = item("bingo_emerald_orbit", "arlight_aura", "bingo_emerald_orbit");
    public static final DeferredItem<Item> BINGO_GOLD_ORBIT = item("bingo_gold_orbit", "arlight_aura", "bingo_gold_orbit");
    public static final DeferredItem<Item> BINGO_CORRUPTION_STEPS = item("bingo_corruption_steps", "arlight_trail", "bingo_corruption_steps");
    public static final DeferredItem<Item> BINGO_AMETHYST_ORBIT = item("bingo_amethyst_orbit", "arlight_aura", "bingo_amethyst_orbit");

    public static final DeferredItem<Item> SWANSITO_DINO_TAIL = item("swansito_dino_tail", "arlight_tail", "swansito_dino_tail");
    public static final DeferredItem<Item> SWANSITO_DINO_HANDS = item("swansito_dino_hands", "arlight_hands", "swansito_dino_hands");
    public static final DeferredItem<Item> SWANSITO_MINI_TREX = item("swansito_mini_trex", "arlight_companion", "swansito_mini_trex");
    public static final DeferredItem<Item> SWANSITO_MEAT_CHASE_AURA = item("swansito_meat_chase_aura", "arlight_aura", "swansito_meat_chase_aura");
    public static final DeferredItem<Item> SWANSITO_DINO_STEPS = item("swansito_dino_steps", "arlight_trail", "swansito_dino_steps");

    public static final DeferredItem<Item> MOBCHIBI_CREEPER = item("mobchibi_creeper", "arlight_companion", "mobchibi_creeper");
    public static final DeferredItem<Item> MOBCHIBI_ENDERMAN = item("mobchibi_enderman", "arlight_companion", "mobchibi_enderman");
    public static final DeferredItem<Item> MOBCHIBI_BEE = item("mobchibi_bee", "arlight_companion", "mobchibi_bee");
    public static final DeferredItem<Item> MOBCHIBI_AXOLOTL = item("mobchibi_axolotl", "arlight_companion", "mobchibi_axolotl");
    public static final DeferredItem<Item> MOBCHIBI_SLIME = item("mobchibi_slime", "arlight_companion", "mobchibi_slime");
    public static final DeferredItem<Item> MOBCHIBI_WARDEN = item("mobchibi_warden", "arlight_companion", "mobchibi_warden");

    public static final DeferredItem<Item> DINO_PAJAMA_HAT = item("dino_pajama_hat", "arlight_head", "dino_pajama_hat");
    public static final DeferredItem<Item> DINO_PAJAMA_CHEST = item("dino_pajama_chest", "arlight_chest", "dino_pajama_chest");
    public static final DeferredItem<Item> DINO_PAJAMA_LEGS = item("dino_pajama_legs", "arlight_legs", "dino_pajama_legs");
    public static final DeferredItem<Item> DINO_PAJAMA_FEET = item("dino_pajama_feet", "arlight_feet", "dino_pajama_feet");
    public static final DeferredItem<Item> AXOLOTL_PAJAMA_HAT = item("axolotl_pajama_hat", "arlight_head", "axolotl_pajama_hat");
    public static final DeferredItem<Item> AXOLOTL_PAJAMA_CHEST = item("axolotl_pajama_chest", "arlight_chest", "axolotl_pajama_chest");
    public static final DeferredItem<Item> AXOLOTL_PAJAMA_LEGS = item("axolotl_pajama_legs", "arlight_legs", "axolotl_pajama_legs");
    public static final DeferredItem<Item> AXOLOTL_PAJAMA_FEET = item("axolotl_pajama_feet", "arlight_feet", "axolotl_pajama_feet");
    public static final DeferredItem<Item> PASTEL_HEART_BACKPACK = item("pastel_heart_backpack", "arlight_back", "pastel_heart_backpack");
    public static final DeferredItem<Item> BUNNY_EAR_HOOD = item("bunny_ear_hood", "arlight_head", "bunny_ear_hood");
    public static final DeferredItem<Item> MOON_STAR_CARDIGAN = item("moon_star_cardigan", "arlight_chest", "moon_star_cardigan");
    public static final DeferredItem<Item> CLOUD_SKIRT = item("cloud_skirt", "arlight_legs", "cloud_skirt");
    public static final DeferredItem<Item> CLOUD_SLIPPERS = item("cloud_slippers", "arlight_feet", "cloud_slippers");
    public static final DeferredItem<Item> FLOATING_PASTEL_RIBBON = item("floating_pastel_ribbon", "arlight_aura", "floating_pastel_ribbon");
    public static final DeferredItem<Item> DINO_FOOTPRINTS = item("dino_footprints", "arlight_trail", "dino_footprints");
    public static final DeferredItem<Item> AXOLOTL_BUBBLES = item("axolotl_bubbles", "arlight_trail", "axolotl_bubbles");
    public static final DeferredItem<Item> PASTEL_STAR_STEPS = item("pastel_star_steps", "arlight_trail", "pastel_star_steps");
    public static final DeferredItem<Item> FLOWER_PETALS_STEPS = item("flower_petals_steps", "arlight_trail", "flower_petals_steps");
    public static final DeferredItem<Item> MOBCHIBI_CHICKEN = item("mobchibi_chicken", "arlight_companion", "mobchibi_chicken");
    public static final DeferredItem<Item> MOBCHIBI_FOX = item("mobchibi_fox", "arlight_companion", "mobchibi_fox");
    public static final DeferredItem<Item> MOBCHIBI_FROG = item("mobchibi_frog", "arlight_companion", "mobchibi_frog");
    public static final DeferredItem<Item> MOBCHIBI_ALLAY = item("mobchibi_allay", "arlight_companion", "mobchibi_allay");
    public static final DeferredItem<Item> MOBCHIBI_ZOMBIE = item("mobchibi_zombie", "arlight_companion", "mobchibi_zombie");
    public static final DeferredItem<Item> MOBCHIBI_SKELETON = item("mobchibi_skeleton", "arlight_companion", "mobchibi_skeleton");

    private static DeferredItem<Item> item(String id, String slot, String claim) {
        return ITEMS.register(id, () -> new OwnerBoundCosmeticItem(new Item.Properties().stacksTo(1), slot, claim));
    }

    public static List<Item> all() {
        return List.of(
                PONY_HORN_CROWN.get(), PONY_PASTEL_CHEST.get(), PONY_PASTEL_LEGS.get(), PONY_PASTEL_FEET.get(),
                PONY_WHALE_TAIL.get(), PONY_AXOLOTL_PAL.get(), PONY_WHALE_STARS.get(), PONY_PASTEL_STEPS.get(),
                SOMITA_BAT_CLIP.get(), SOMITA_NIGHT_CHEST.get(), SOMITA_NIGHT_LEGS.get(), SOMITA_NIGHT_FEET.get(),
                SOMITA_BAT_WINGS.get(), SOMITA_BAT_PAL.get(), SOMITA_CRIMSON_HEARTS.get(), SOMITA_NIGHT_STEPS.get(),
                BINGO_CORRUPTED_CROWN.get(), BINGO_CORRUPTED_CHEST.get(), BINGO_CORRUPTED_LEGS.get(), BINGO_CORRUPTED_FEET.get(),
                BINGO_VOID_WINGS.get(), BINGO_AMETHYST_EYE.get(), BINGO_EMERALD_ORBIT.get(), BINGO_GOLD_ORBIT.get(),
                BINGO_CORRUPTION_STEPS.get(), BINGO_AMETHYST_ORBIT.get(), SWANSITO_DINO_TAIL.get(), SWANSITO_DINO_HANDS.get(),
                SWANSITO_MINI_TREX.get(), SWANSITO_MEAT_CHASE_AURA.get(), SWANSITO_DINO_STEPS.get(),
                MOBCHIBI_CREEPER.get(), MOBCHIBI_ENDERMAN.get(), MOBCHIBI_BEE.get(), MOBCHIBI_AXOLOTL.get(),
                MOBCHIBI_SLIME.get(), MOBCHIBI_WARDEN.get(),
                DINO_PAJAMA_HAT.get(), DINO_PAJAMA_CHEST.get(), DINO_PAJAMA_LEGS.get(), DINO_PAJAMA_FEET.get(), AXOLOTL_PAJAMA_HAT.get(), AXOLOTL_PAJAMA_CHEST.get(), AXOLOTL_PAJAMA_LEGS.get(), AXOLOTL_PAJAMA_FEET.get(), PASTEL_HEART_BACKPACK.get(), BUNNY_EAR_HOOD.get(), MOON_STAR_CARDIGAN.get(), CLOUD_SKIRT.get(), CLOUD_SLIPPERS.get(), FLOATING_PASTEL_RIBBON.get(), DINO_FOOTPRINTS.get(), AXOLOTL_BUBBLES.get(), PASTEL_STAR_STEPS.get(), FLOWER_PETALS_STEPS.get(), MOBCHIBI_CHICKEN.get(), MOBCHIBI_FOX.get(), MOBCHIBI_FROG.get(), MOBCHIBI_ALLAY.get(), MOBCHIBI_ZOMBIE.get(), MOBCHIBI_SKELETON.get());
    }

    private CosmeticItems() { }
}
