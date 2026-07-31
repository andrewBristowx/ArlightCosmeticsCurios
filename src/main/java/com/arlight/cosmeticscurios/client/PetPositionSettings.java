package com.arlight.cosmeticscurios.client;

import net.minecraft.util.Mth;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;

/**
 * Ajustes locales de posición por modelo de mascota.
 *
 * <p>Se guardan separados de la configuración general para que el administrador
 * pueda distribuir el archivo al modpack o modificarlo desde el ropero sin
 * recompilar el mod. Los valores son desplazamientos adicionales, no sustituyen
 * los perfiles seguros incluidos en {@link PetRenderProfile}.</p>
 */
public final class PetPositionSettings {
    public record Offset(float x, float y, float z) {
        static final Offset ZERO = new Offset(0.0F, 0.0F, 0.0F);
    }

    private static final Path FILE = Path.of("config", "arlightcosmeticscurios-pet-positions.properties");
    private static final Map<String, Offset> OFFSETS = new LinkedHashMap<>();
    private static boolean loaded;

    public static synchronized void load() {
        if (loaded) return;
        loaded = true;
        if (!Files.exists(FILE)) return;
        Properties values = new Properties();
        try (InputStream input = Files.newInputStream(FILE)) {
            values.load(input);
        } catch (IOException ignored) {
            return;
        }
        values.stringPropertyNames().stream()
                .filter(key -> key.endsWith(".x"))
                .map(key -> key.substring(0, key.length() - 2))
                .distinct()
                .forEach(model -> OFFSETS.put(model, new Offset(
                        read(values, model + ".x"),
                        read(values, model + ".y"),
                        read(values, model + ".z"))));
    }

    public static synchronized Offset get(String modelId) {
        load();
        return OFFSETS.getOrDefault(normalize(modelId), Offset.ZERO);
    }

    public static synchronized Offset adjust(String modelId, float dx, float dy, float dz) {
        load();
        String key = normalize(modelId);
        if (key.isBlank()) return Offset.ZERO;
        Offset current = OFFSETS.getOrDefault(key, Offset.ZERO);
        Offset updated = new Offset(
                snap(Mth.clamp(current.x() + dx, -1.40F, 1.40F)),
                snap(Mth.clamp(current.y() + dy, -1.20F, 1.20F)),
                snap(Mth.clamp(current.z() + dz, -1.40F, 1.40F)));
        OFFSETS.put(key, updated);
        return updated;
    }

    public static synchronized Offset reset(String modelId) {
        load();
        String key = normalize(modelId);
        if (!key.isBlank()) OFFSETS.remove(key);
        return Offset.ZERO;
    }

    public static synchronized void save() {
        load();
        Properties values = new Properties();
        OFFSETS.forEach((model, offset) -> {
            values.setProperty(model + ".x", format(offset.x()));
            values.setProperty(model + ".y", format(offset.y()));
            values.setProperty(model + ".z", format(offset.z()));
        });
        try {
            Files.createDirectories(FILE.getParent());
            try (OutputStream output = Files.newOutputStream(FILE)) {
                values.store(output,
                        "ArlightCosmeticsCurios pet offsets: X=lateral, Y=altura, Z=delante/atras");
            }
        } catch (IOException ignored) { }
    }

    private static float read(Properties values, String key) {
        try {
            return snap(Mth.clamp(Float.parseFloat(values.getProperty(key, "0").trim()), -1.40F, 1.40F));
        } catch (NumberFormatException ignored) {
            return 0.0F;
        }
    }

    private static float snap(float value) {
        return Math.round(value * 20.0F) / 20.0F;
    }

    private static String format(float value) {
        return String.format(Locale.ROOT, "%.2f", value);
    }

    private static String normalize(String value) {
        return value == null ? "" : value.trim().toLowerCase(Locale.ROOT);
    }

    private PetPositionSettings() { }
}
