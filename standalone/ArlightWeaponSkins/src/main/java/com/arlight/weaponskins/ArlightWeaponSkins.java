package com.arlight.weaponskins;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

@Mod(ArlightWeaponSkins.MOD_ID)
public final class ArlightWeaponSkins {
    public static final String MOD_ID = "arlightweaponskins";

    public ArlightWeaponSkins(IEventBus modBus) {
        WeaponSkinDiagnostics.infoOnce("startup",
                "Arlight Weapon Skins 1.8.0 activo; hook low-level para herramientas custom habilitado.");
        WeaponSkinItems.register(modBus);
        ToolCompatibilityConfig.load();
    }
}
