package com.arlight.cosmeticscurios;

/** Per-model block collision dimensions for companion entities. */
public record CompanionPhysicsProfile(float width, float height) {
    private static final CompanionPhysicsProfile DEFAULT = new CompanionPhysicsProfile(0.76F, 0.84F);

    public static CompanionPhysicsProfile forModel(String modelId) {
        return switch (modelId == null ? "" : modelId) {
            case "swansito_mini_trex" -> new CompanionPhysicsProfile(1.46F, 1.02F);
            case "mobchibi_creeper" -> new CompanionPhysicsProfile(0.82F, 0.84F);
            case "mobchibi_enderman" -> new CompanionPhysicsProfile(0.82F, 1.18F);
            case "mobchibi_bee" -> new CompanionPhysicsProfile(0.96F, 0.72F);
            case "mobchibi_axolotl" -> new CompanionPhysicsProfile(1.12F, 0.68F);
            case "mobchibi_slime" -> new CompanionPhysicsProfile(0.82F, 0.68F);
            case "mobchibi_warden" -> new CompanionPhysicsProfile(0.96F, 1.08F);
            case "mobchibi_chicken" -> new CompanionPhysicsProfile(0.74F, 0.68F);
            case "mobchibi_fox" -> new CompanionPhysicsProfile(1.14F, 0.78F);
            case "mobchibi_frog" -> new CompanionPhysicsProfile(0.88F, 0.68F);
            case "mobchibi_allay" -> new CompanionPhysicsProfile(0.94F, 0.80F);
            case "mobchibi_zombie" -> new CompanionPhysicsProfile(0.82F, 0.96F);
            case "mobchibi_skeleton" -> new CompanionPhysicsProfile(0.80F, 1.00F);
            default -> DEFAULT;
        };
    }
}
