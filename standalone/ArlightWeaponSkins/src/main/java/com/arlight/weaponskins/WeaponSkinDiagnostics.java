package com.arlight.weaponskins;

import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import net.minecraft.client.Minecraft;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

public final class WeaponSkinDiagnostics {
    private static final Set<String> PRINTED = ConcurrentHashMap.newKeySet();
    private static final String PREFIX = "[ArlightWeaponSkins/1.8.0] ";

    private WeaponSkinDiagnostics() { }

    public static void infoOnce(String key, String message) {
        if (PRINTED.add(key)) System.out.println(PREFIX + message);
    }

    public static void clientExtensionsRegistered(int count) {
        infoOnce("client-extensions", "Renderer GeckoLib registrado explícitamente para "
                + count + " objetos visuales.");
    }

    public static void renderHook(ToolType type) {
        infoOnce("hook:" + type.name(), "Hook de render detectado para " + type.name() + '.');
    }

    public static void missingSelection(UUID owner, ToolType type, String selected,
            String wardrobe, String legacy) {
        infoOnce("missing:" + owner + ':' + type.name(),
                "Sin skin cliente para " + type.name() + " (slot=" + selected
                        + ", ropero=" + wardrobe + ", legado=" + legacy
                        + ", uuid=" + owner + ").");
    }

    public static void wardrobeFallback(WeaponSkinTheme theme, ToolType type) {
        infoOnce("wardrobe:" + theme.name() + ':' + type.name(),
                "Selección recuperada desde el ropero: " + type.name() + " -> " + theme.name() + '.');
    }

    public static void curiosSelection(WeaponSkinTheme theme, ToolType type, String itemId) {
        infoOnce("curios:" + theme.name() + ':' + type.name(),
                "Curios activo: " + type.curiosSlot() + " -> " + itemId + '.');
    }

    public static void invalidCuriosItem(ToolType type, String itemId) {
        infoOnce("curios-invalid:" + type.name() + ':' + itemId,
                "El objeto de " + type.curiosSlot() + " no es una skin compatible: " + itemId + '.');
    }

    public static void curiosFailure(UUID owner, ToolType type, Throwable error) {
        infoOnce("curios-failure:" + type.name() + ':' + error.getClass().getName(),
                "No se pudo leer " + type.curiosSlot() + " para " + owner + ": "
                        + error.getClass().getSimpleName() + ": " + error.getMessage());
    }

    public static void bridgeFailure(UUID owner, ToolType type, Throwable error) {
        infoOnce("bridge:" + error.getClass().getName(),
                "Fallo del puente ChatClient para " + type.name() + " y " + owner + ": "
                        + error.getClass().getName() + ": " + error.getMessage());
    }

    public static void emptyReplacement(WeaponSkinTheme theme, ToolType type) {
        infoOnce("empty:" + theme.name() + ':' + type.name(),
                "El item visual no está registrado: " + theme.name() + '/' + type.name() + '.');
    }

    public static void replaced(WeaponSkinTheme theme, ToolType type, ItemStack stack) {
        var id = BuiltInRegistries.ITEM.getKey(stack.getItem());
        infoOnce("ok:" + theme.name() + ':' + type.name(),
                "Sustitución activa: " + type.name() + " -> " + theme.name()
                        + " usando " + id + '.');
        validateResources(theme, type, id);
    }

    private static void validateResources(WeaponSkinTheme theme, ToolType type,
            ResourceLocation itemId) {
        String visual = theme.assetId() + "_" + type.assetId();
        checkResource("model-alias:" + visual,
                ResourceLocation.fromNamespaceAndPath(itemId.getNamespace(),
                        "models/item/" + itemId.getPath() + ".json"));
        checkResource("geo:" + visual,
                ResourceLocation.fromNamespaceAndPath(ArlightWeaponSkins.MOD_ID,
                        "geo/" + visual + ".geo.json"));
        checkResource("texture:" + visual,
                ResourceLocation.fromNamespaceAndPath(ArlightWeaponSkins.MOD_ID,
                        "textures/item/" + visual + ".png"));
        checkResource("animation:" + visual,
                ResourceLocation.fromNamespaceAndPath(ArlightWeaponSkins.MOD_ID,
                        "animations/" + visual + ".animation.json"));
    }

    private static void checkResource(String key, ResourceLocation resource) {
        try {
            Minecraft minecraft = Minecraft.getInstance();
            if (minecraft == null || minecraft.getResourceManager() == null) return;
            if (minecraft.getResourceManager().getResource(resource).isPresent()) {
                infoOnce("resource-ok:" + key, "Recurso confirmado: " + resource + '.');
            } else {
                infoOnce("resource-missing:" + key, "RECURSO AUSENTE: " + resource + '.');
            }
        } catch (Throwable error) {
            infoOnce("resource-error:" + key,
                    "No se pudo comprobar " + resource + ": " + error.getClass().getSimpleName());
        }
    }
}
