package com.arlight.cosmeticscurios.client;

import com.arlight.cosmeticscurios.ArlightCosmeticsCurios;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

/**
 * Punto de entrada exclusivo del cliente físico.
 * Mantiene las clases de renderizado fuera del arranque del servidor dedicado.
 */
@Mod(value = ArlightCosmeticsCurios.MOD_ID, dist = Dist.CLIENT)
public final class ArlightCosmeticsCuriosClient {
    public ArlightCosmeticsCuriosClient(IEventBus modBus) {
        ClientBootstrap.init(modBus);
    }
}
