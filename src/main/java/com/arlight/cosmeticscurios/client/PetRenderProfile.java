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
        float bobAmplitude,
        float collisionRadius,
        float collisionHeight,
        float maxStepHeight,
        float maxSafeDrop
) {
    public static PetRenderProfile forModel(String modelId) {
        return switch (modelId == null ? "" : modelId) {
            case "swansito_mini_trex" -> land(0.62F, 0.34F, 0.02F, 0.33F, 0.02F, 0.01F, 1.10F, 0.03F, 0.31F, 0.86F);
            case "mobchibi_creeper" -> land(0.58F, 0.28F, 0.01F, 0.31F, 0.01F, 0.01F, 1.08F, 0.025F, 0.25F, 0.76F);
            case "mobchibi_enderman" -> land(0.66F, 0.34F, -0.02F, 0.34F, 0.02F, -0.02F, 1.14F, 0.035F, 0.27F, 1.08F);
            case "mobchibi_bee" -> flying(0.66F, 0.18F, -0.48F, 0.34F, 0.00F, -0.44F, 1.18F, 0.10F, 0.25F, 0.62F);
            case "mobchibi_axolotl" -> land(0.60F, 0.27F, 0.03F, 0.32F, 0.01F, 0.02F, 1.18F, 0.03F, 0.30F, 0.60F);
            case "mobchibi_slime" -> land(0.58F, 0.28F, 0.04F, 0.31F, 0.01F, 0.04F, 1.10F, 0.04F, 0.25F, 0.58F);
            case "mobchibi_warden" -> land(0.69F, 0.37F, -0.01F, 0.35F, 0.02F, -0.01F, 1.10F, 0.035F, 0.31F, 0.98F);
            case "mobchibi_chicken" -> land(0.58F, 0.25F, 0.05F, 0.31F, 0.01F, 0.05F, 1.16F, 0.03F, 0.24F, 0.58F);
            case "mobchibi_fox" -> land(0.66F, 0.36F, 0.03F, 0.34F, 0.02F, 0.03F, 1.14F, 0.03F, 0.31F, 0.68F);
            case "mobchibi_frog" -> land(0.58F, 0.27F, 0.07F, 0.31F, 0.01F, 0.06F, 1.20F, 0.04F, 0.27F, 0.58F);
            case "mobchibi_allay" -> flying(0.67F, 0.18F, -0.50F, 0.34F, 0.00F, -0.46F, 1.18F, 0.11F, 0.25F, 0.70F);
            case "mobchibi_zombie" -> land(0.61F, 0.31F, 0.00F, 0.32F, 0.01F, 0.00F, 1.12F, 0.03F, 0.27F, 0.86F);
            case "mobchibi_skeleton" -> land(0.63F, 0.32F, 0.00F, 0.33F, 0.01F, 0.00F, 1.14F, 0.03F, 0.26F, 0.90F);
            default -> land(0.60F, 0.28F, 0.00F, 0.32F, 0.01F, 0.00F, 1.08F, 0.03F, 0.27F, 0.78F);
        };
    }

    private static PetRenderProfile land(float side, float back, float y,
                                         float previewSide, float previewBack,
                                         float previewY, float previewScale,
                                         float bob, float radius, float height) {
        return new PetRenderProfile(side, back, y, previewSide, previewBack,
                previewY, previewScale, false, bob, radius, height, 1.08F, 1.28F);
    }

    private static PetRenderProfile flying(float side, float back, float y,
                                           float previewSide, float previewBack,
                                           float previewY, float previewScale,
                                           float bob, float radius, float height) {
        return new PetRenderProfile(side, back, y, previewSide, previewBack,
                previewY, previewScale, true, bob, radius, height, 1.35F, 1.80F);
    }
}
