package com.arlight.cosmeticscurios.client;

import com.arlight.cosmeticscurios.CosmeticEntities;
import com.arlight.cosmeticscurios.CosmeticItems;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import top.theillusivec4.curios.api.client.CuriosRendererRegistry;

public final class ClientBootstrap {
    public static void init(IEventBus modBus) {
        modBus.addListener(ClientBootstrap::setup);
        modBus.addListener(ClientBootstrap::registerRenderers);
    }

    private static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(CosmeticEntities.COMPANION.get(), CompanionMarkerRenderer::new);
    }
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
