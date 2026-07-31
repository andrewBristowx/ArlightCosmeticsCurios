package com.arlight.cosmeticscurios.client;

import net.minecraft.client.player.AbstractClientPlayer;

import java.lang.reflect.Method;

/** Detección tolerante entre mappings de modelo slim/clásico. */
public final class PlayerModelType {
    public static boolean isSlim(AbstractClientPlayer player) {
        if (player == null) return false;
        try {
            Method getSkin = player.getClass().getMethod("getSkin");
            Object skin = getSkin.invoke(player);
            if (skin != null) {
                Method model = skin.getClass().getMethod("model");
                Object result = model.invoke(skin);
                if (result != null && result.toString().toLowerCase(java.util.Locale.ROOT).contains("slim")) return true;
            }
        } catch (ReflectiveOperationException ignored) { }
        try {
            Method legacy = player.getClass().getMethod("getModelName");
            Object result = legacy.invoke(player);
            return result != null && result.toString().equalsIgnoreCase("slim");
        } catch (ReflectiveOperationException ignored) { }
        return false;
    }

    private PlayerModelType() { }
}
