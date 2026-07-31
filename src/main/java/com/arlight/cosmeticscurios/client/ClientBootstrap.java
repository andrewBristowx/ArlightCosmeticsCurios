package com.arlight.cosmeticscurios.client;

import com.arlight.cosmeticscurios.CosmeticItems;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import top.theillusivec4.curios.api.client.CuriosRendererRegistry;

public final class ClientBootstrap {
    public static void init(IEventBus modBus) { modBus.addListener(ClientBootstrap::setup); }
    private static void setup(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            ClientCosmeticSettings.load();
            PetPositionSettings.load();
            CosmeticItems.all().forEach(item ->
                    CuriosRendererRegistry.register(item, () -> new CosmeticCurioRenderer()));
        });
    }
    private ClientBootstrap() { }
}
