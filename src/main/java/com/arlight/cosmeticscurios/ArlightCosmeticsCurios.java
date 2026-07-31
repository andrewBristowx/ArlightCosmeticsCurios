package com.arlight.cosmeticscurios;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.NeoForge;

@Mod(ArlightCosmeticsCurios.MOD_ID)
public final class ArlightCosmeticsCurios {
    public static final String MOD_ID = "arlightcosmeticscurios";

    public ArlightCosmeticsCurios(IEventBus modBus) {
        CosmeticItems.ITEMS.register(modBus);
        CosmeticEntities.ENTITIES.register(modBus);
        modBus.addListener(CosmeticEntities::createAttributes);
        NeoForge.EVENT_BUS.register(OwnershipEvents.class);
        NeoForge.EVENT_BUS.register(CosmeticCommands.class);
        NeoForge.EVENT_BUS.register(CompanionManager.class);
    }
}
