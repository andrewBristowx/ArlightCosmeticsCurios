package com.arlight.weaponskins;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.EnumMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.Properties;
import java.util.Set;

public final class ToolCompatibilityConfig {
    private static final Path FILE = Paths.get("config", "arlightweaponskins-compat.properties");
    private static final MapSet INCLUDES = new MapSet();
    private static final Set<String> EXCLUDES = new HashSet<>();

    private ToolCompatibilityConfig() { }

    public static synchronized void load() {
        Properties properties = defaults();
        try {
            Files.createDirectories(FILE.getParent());
            if (Files.exists(FILE)) {
                try (InputStream input = Files.newInputStream(FILE)) { properties.load(input); }
            } else {
                try (OutputStream output = Files.newOutputStream(FILE)) {
                    properties.store(output, "Compatibilidad de Arlight Weapon Skins");
                }
            }
        } catch (IOException ignored) { }

        INCLUDES.clear();
        EXCLUDES.clear();
        for (ToolType type : ToolType.values()) {
            split(properties.getProperty("include." + type.assetId(), ""), INCLUDES.get(type));
        }
        split(properties.getProperty("exclude", ""), EXCLUDES);
    }

    public static ToolType explicitType(String itemId) {
        String normalized = normalize(itemId);
        if (EXCLUDES.contains(normalized)) return null;
        for (ToolType type : ToolType.values()) {
            if (INCLUDES.get(type).contains(normalized)) return type;
        }
        return null;
    }

    public static boolean excluded(String itemId) { return EXCLUDES.contains(normalize(itemId)); }

    private static Properties defaults() {
        Properties properties = new Properties();
        properties.setProperty("include.sword", "");
        properties.setProperty("include.pickaxe", "");
        properties.setProperty("include.axe", "");
        properties.setProperty("include.shovel", "");
        properties.setProperty("include.hoe", "");
        properties.setProperty("include.bow", "");
        properties.setProperty("exclude", "");
        return properties;
    }

    private static void split(String raw, Set<String> target) {
        if (raw == null || raw.isBlank()) return;
        for (String value : raw.split(",")) {
            String normalized = normalize(value);
            if (!normalized.isBlank()) target.add(normalized);
        }
    }

    private static String normalize(String value) {
        return value == null ? "" : value.trim().toLowerCase(Locale.ROOT);
    }

    private static final class MapSet extends EnumMap<ToolType, Set<String>> {
        private MapSet() {
            super(ToolType.class);
            for (ToolType type : ToolType.values()) put(type, new HashSet<>());
        }
        @Override public void clear() {
            for (Set<String> values : values()) values.clear();
        }
    }
}
