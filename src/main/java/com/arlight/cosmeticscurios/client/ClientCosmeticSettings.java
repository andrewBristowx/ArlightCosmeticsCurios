package com.arlight.cosmeticscurios.client;

import com.arlight.cosmeticscurios.CosmeticRuntimeSettings;

/** Alias del lado cliente para mantener el renderizador legible. */
public final class ClientCosmeticSettings {
    public static void load() { CosmeticRuntimeSettings.load(); }
    public static int renderDistance() { return CosmeticRuntimeSettings.renderDistance(); }
    public static int fullAnimationDistance() { return CosmeticRuntimeSettings.fullAnimationDistance(); }
    public static float petSmoothing() { return CosmeticRuntimeSettings.petSmoothing(); }
    public static boolean hideUnderArmor() { return CosmeticRuntimeSettings.hideUnderArmor(); }
    public static String petSide() { return CosmeticRuntimeSettings.petSide(); }
    public static String particleMode() { return CosmeticRuntimeSettings.particleMode(); }
    private ClientCosmeticSettings() { }
}
