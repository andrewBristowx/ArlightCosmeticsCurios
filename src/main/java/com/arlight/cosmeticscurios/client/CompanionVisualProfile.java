package com.arlight.cosmeticscurios.client;

/**
 * Corrige el pivote histórico de cada modelo. Los modelos nacieron como capas
 * Curios alrededor del jugador; al renderizarlos sobre una entidad real hay que
 * llevar su raíz al centro de la caja física.
 */
public record CompanionVisualProfile(float originX, float originZ, float yOffset) {
    public static CompanionVisualProfile forModel(String modelId) {
        return switch (modelId == null ? "" : modelId) {
            case "swansito_mini_trex" -> pixels(10.6F, 13.8F, 0.00F);
            case "mobchibi_bee" -> pixels(8.8F, 4.8F, 0.12F);
            case "mobchibi_allay" -> pixels(8.8F, 4.7F, 0.12F);
            case "mobchibi_creeper" -> pixels(9.0F, 4.4F, 0.00F);
            case "mobchibi_warden", "mobchibi_zombie", "mobchibi_skeleton",
                    "mobchibi_chicken" -> pixels(9.0F, 4.3F, 0.00F);
            case "mobchibi_enderman" -> pixels(9.0F, 4.2F, 0.00F);
            case "mobchibi_axolotl", "mobchibi_fox", "mobchibi_frog"
                    -> pixels(8.9F, 4.2F, 0.00F);
            case "mobchibi_slime" -> pixels(8.8F, 4.0F, 0.00F);
            default -> pixels(9.0F, 4.3F, 0.00F);
        };
    }

    private static CompanionVisualProfile pixels(float x, float z, float y) {
        return new CompanionVisualProfile(-x / 16.0F, -z / 16.0F, y);
    }
}
