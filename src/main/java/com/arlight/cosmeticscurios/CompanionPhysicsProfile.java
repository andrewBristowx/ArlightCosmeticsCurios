package com.arlight.cosmeticscurios;

import net.minecraft.world.entity.EntityDimensions;

/** Per-model block collision dimensions for companion entities. */
public record CompanionPhysicsProfile(float width, float height) {
    private static final CompanionPhysicsProfile DEFAULT = new CompanionPhysicsProfile(0.66F, 0.80F);

    public static CompanionPhysicsProfile forModel(String modelId) {
        return switch (modelId == null ? "" : modelId) {
            case "swansito_mini_trex" -> new CompanionPhysicsProfile(1.08F, 0.90F);
            case "mobchibi_creeper" -> new CompanionPhysicsProfile(0.72F, 0.80F);
            case "mobchibi_enderman" -> new CompanionPhysicsProfile(0.70F, 1.12F);
            case "mobchibi_bee" -> new CompanionPhysicsProfile(0.82F, 0.66F);
            case "mobchibi_axolotl" -> new CompanionPhysicsProfile(0.92F, 0.62F);
            case "mobchibi_slime" -> new CompanionPhysicsProfile(0.70F, 0.62F);
            case "mobchibi_warden" -> new CompanionPhysicsProfile(0.82F, 1.02F);
            case "mobchibi_chicken" -> new CompanionPhysicsProfile(0.64F, 0.62F);
            case "mobchibi_fox" -> new CompanionPhysicsProfile(0.94F, 0.72F);
            case "mobchibi_frog" -> new CompanionPhysicsProfile(0.72F, 0.62F);
            case "mobchibi_allay" -> new CompanionPhysicsProfile(0.80F, 0.74F);
            case "mobchibi_zombie" -> new CompanionPhysicsProfile(0.70F, 0.90F);
            case "mobchibi_skeleton" -> new CompanionPhysicsProfile(0.68F, 0.94F);
            default -> DEFAULT;
        };
    }

    public EntityDimensions dimensions() {
        return EntityDimensions.scalable(width, height);
    }
}
