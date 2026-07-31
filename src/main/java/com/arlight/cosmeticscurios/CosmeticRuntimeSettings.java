package com.arlight.cosmeticscurios;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

/** Preferencias visuales locales, seguras también al cargar el servidor dedicado. */
public final class CosmeticRuntimeSettings {
    private static final Path FILE = Path.of("config", "arlightcosmeticscurios-client.properties");
    private static final Properties VALUES = new Properties();
    private static boolean loaded;

    public static synchronized void load() {
        if (loaded) return;
        loaded = true;
        VALUES.setProperty("render-distance", "40");
        VALUES.setProperty("full-animation-distance", "18");
        VALUES.setProperty("pet-follow-smoothing", "0.16");
        VALUES.setProperty("pet-side", "auto");
        VALUES.setProperty("hide-cosmetics-under-armor", "true");
        VALUES.setProperty("particles", "full");
        try {
            Files.createDirectories(FILE.getParent());
            if (Files.exists(FILE)) {
                try (InputStream in = Files.newInputStream(FILE)) { VALUES.load(in); }
            } else {
                try (OutputStream out = Files.newOutputStream(FILE)) {
                    VALUES.store(out, "ArlightCosmeticsCurios local settings");
                }
            }
        } catch (IOException ignored) { }
    }

    public static int renderDistance() { load(); return clamp(intValue("render-distance", 40), 12, 96); }
    public static int fullAnimationDistance() { load(); return clamp(intValue("full-animation-distance", 18), 6, renderDistance()); }
    public static float petSmoothing() { load(); return clamp(floatValue("pet-follow-smoothing", 0.16F), 0.05F, 0.45F); }
    public static boolean hideUnderArmor() { load(); return boolValue("hide-cosmetics-under-armor", true); }
    public static String petSide() { load(); return VALUES.getProperty("pet-side", "auto").trim().toLowerCase(java.util.Locale.ROOT); }
    public static String particleMode() { load(); return VALUES.getProperty("particles", "full").trim().toLowerCase(java.util.Locale.ROOT); }
    public static boolean particlesEnabled() { return !particleMode().equals("off"); }
    public static boolean reducedParticles() { return particleMode().equals("reduced"); }

    private static int intValue(String key, int fallback) {
        try { return Integer.parseInt(VALUES.getProperty(key, Integer.toString(fallback)).trim()); }
        catch (NumberFormatException ignored) { return fallback; }
    }
    private static float floatValue(String key, float fallback) {
        try { return Float.parseFloat(VALUES.getProperty(key, Float.toString(fallback)).trim()); }
        catch (NumberFormatException ignored) { return fallback; }
    }
    private static boolean boolValue(String key, boolean fallback) {
        String value = VALUES.getProperty(key);
        return value == null ? fallback : Boolean.parseBoolean(value.trim());
    }
    private static int clamp(int value, int min, int max) { return Math.max(min, Math.min(max, value)); }
    private static float clamp(float value, float min, float max) { return Math.max(min, Math.min(max, value)); }
    private CosmeticRuntimeSettings() { }
}
