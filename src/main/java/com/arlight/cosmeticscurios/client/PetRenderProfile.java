package com.arlight.cosmeticscurios.client;

/**
 * Ajustes visuales por mascota. Las distancias se expresan en unidades de
 * render de Minecraft (aproximadamente bloques) y se aplican además del pivote
 * interno del modelo. Así una abeja flotante no usa la misma altura ni la misma
 * distancia que un T-Rex terrestre.
 */
public record PetRenderProfile(
        float sideDistance,
        float backDistance,
        float baseYOffset,
        float previewSide,
        float previewBack,
        float previewYOffset,
        float previewScale,
        boolean floating,
        float bobAmplitude
) {
    public static PetRenderProfile forModel(String modelId) {
        return switch (modelId == null ? "" : modelId) {
            case "swansito_mini_trex" -> new PetRenderProfile(0.62F, 0.34F, 0.02F, 0.33F, 0.02F, 0.01F, 1.10F, false, 0.03F);
            case "mobchibi_creeper" -> new PetRenderProfile(0.58F, 0.28F, 0.01F, 0.31F, 0.01F, 0.01F, 1.08F, false, 0.025F);
            case "mobchibi_enderman" -> new PetRenderProfile(0.66F, 0.34F, -0.02F, 0.34F, 0.02F, -0.02F, 1.14F, false, 0.035F);
            case "mobchibi_bee" -> new PetRenderProfile(0.66F, 0.18F, -0.48F, 0.34F, 0.00F, -0.44F, 1.18F, true, 0.10F);
            case "mobchibi_axolotl" -> new PetRenderProfile(0.60F, 0.27F, 0.03F, 0.32F, 0.01F, 0.02F, 1.18F, false, 0.03F);
            case "mobchibi_slime" -> new PetRenderProfile(0.58F, 0.28F, 0.04F, 0.31F, 0.01F, 0.04F, 1.10F, false, 0.04F);
            case "mobchibi_warden" -> new PetRenderProfile(0.69F, 0.37F, -0.01F, 0.35F, 0.02F, -0.01F, 1.10F, false, 0.035F);
            case "mobchibi_chicken" -> new PetRenderProfile(0.58F, 0.25F, 0.05F, 0.31F, 0.01F, 0.05F, 1.16F, false, 0.03F);
            case "mobchibi_fox" -> new PetRenderProfile(0.66F, 0.36F, 0.03F, 0.34F, 0.02F, 0.03F, 1.14F, false, 0.03F);
            case "mobchibi_frog" -> new PetRenderProfile(0.58F, 0.27F, 0.07F, 0.31F, 0.01F, 0.06F, 1.20F, false, 0.04F);
            case "mobchibi_allay" -> new PetRenderProfile(0.67F, 0.18F, -0.50F, 0.34F, 0.00F, -0.46F, 1.18F, true, 0.11F);
            case "mobchibi_zombie" -> new PetRenderProfile(0.61F, 0.31F, 0.00F, 0.32F, 0.01F, 0.00F, 1.12F, false, 0.03F);
            case "mobchibi_skeleton" -> new PetRenderProfile(0.63F, 0.32F, 0.00F, 0.33F, 0.01F, 0.00F, 1.14F, false, 0.03F);
            default -> new PetRenderProfile(0.60F, 0.28F, 0.00F, 0.32F, 0.01F, 0.00F, 1.08F, false, 0.03F);
        };
    }
}
