package com.arlight.cosmeticscurios;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;
import top.theillusivec4.curios.api.CuriosApi;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/** Mantiene como máximo una entidad de mascota por jugador y mundo cargado. */
public final class CompanionManager {
    private static final Map<UUID, UUID> ACTIVE = new HashMap<>();

    @SubscribeEvent
    public static void onPlayerTick(PlayerTickEvent.Post event) {
        if (!(event.getEntity() instanceof ServerPlayer player)) return;
        if (player.tickCount % 10 != 0) return;
        synchronize(player);
    }

    private static void synchronize(ServerPlayer player) {
        String modelId = equippedModel(player);
        CompanionEntity current = activeEntity(player);
        if (modelId.isBlank() || player.isSpectator() || !player.isAlive()) {
            if (current != null) current.discard();
            ACTIVE.remove(player.getUUID());
            discardDuplicates(player, null);
            return;
        }

        if (current == null) current = findLoaded(player);
        if (current == null) {
            current = CosmeticEntities.COMPANION.get().create(player.level());
            if (current == null) return;
            current.configure(player.getUUID(), modelId);
            current.setPos(player.getX(), player.getY(), player.getZ());
            if (!current.placeSafelyNear(player, "spawn")) return;
            player.level().addFreshEntity(current);
        } else if (!modelId.equals(current.modelId())) {
            current.configure(player.getUUID(), modelId);
        }
        ACTIVE.put(player.getUUID(), current.getUUID());
        discardDuplicates(player, current);
    }

    private static CompanionEntity activeEntity(ServerPlayer player) {
        UUID entityId = ACTIVE.get(player.getUUID());
        if (entityId == null) return null;
        if (((ServerLevel) player.level()).getEntity(entityId) instanceof CompanionEntity companion
                && companion.isAlive() && player.getUUID().equals(companion.ownerId())) {
            return companion;
        }
        ACTIVE.remove(player.getUUID());
        return null;
    }

    private static CompanionEntity findLoaded(ServerPlayer player) {
        return player.level().getEntitiesOfClass(CompanionEntity.class,
                        player.getBoundingBox().inflate(48.0D),
                        companion -> player.getUUID().equals(companion.ownerId()))
                .stream().findFirst().orElse(null);
    }

    private static void discardDuplicates(ServerPlayer player, CompanionEntity keep) {
        player.level().getEntitiesOfClass(CompanionEntity.class,
                        player.getBoundingBox().inflate(48.0D),
                        companion -> player.getUUID().equals(companion.ownerId()))
                .forEach(companion -> {
                    if (companion != keep) companion.discard();
                });
    }

    public static boolean isEquipped(ServerPlayer player, String expectedModel) {
        return expectedModel != null && expectedModel.equals(equippedModel(player));
    }

    private static String equippedModel(ServerPlayer player) {
        final String[] model = {""};
        CuriosApi.getCuriosInventory(player).ifPresent(handler ->
                handler.getStacksHandler("arlight_companion").ifPresent(stacksHandler -> {
                    var stacks = stacksHandler.getStacks();
                    if (stacks.getSlots() <= 0) return;
                    ItemStack stack = stacks.getStackInSlot(0);
                    if (stack.isEmpty() || !CosmeticOwnership.canUse(stack, player)) return;
                    CosmeticItemCatalog.Definition definition = CosmeticItemCatalog.byItem(stack.getItem());
                    if (definition != null && definition.companion()) model[0] = definition.modelId();
                }));
        return model[0];
    }

    private CompanionManager() { }
}
