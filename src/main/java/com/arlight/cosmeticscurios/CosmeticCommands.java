package com.arlight.cosmeticscurios;

import com.mojang.brigadier.arguments.StringArgumentType;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.RegisterCommandsEvent;
import top.theillusivec4.curios.api.CuriosApi;

public final class CosmeticCommands {
    @SubscribeEvent
    public static void register(RegisterCommandsEvent event) {
        event.getDispatcher().register(Commands.literal("arlightcosmeticitem")
                .requires(source -> source.hasPermission(2))
                .then(Commands.literal("claim")
                        .then(Commands.argument("player", EntityArgument.player())
                                .then(Commands.argument("cosmetic", StringArgumentType.word())
                                        .executes(context -> claim(EntityArgument.getPlayer(context, "player"), StringArgumentType.getString(context, "cosmetic"))))))
                .then(Commands.literal("equip")
                        .then(Commands.argument("player", EntityArgument.player())
                                .then(Commands.argument("cosmetic", StringArgumentType.word())
                                        .executes(context -> equip(EntityArgument.getPlayer(context, "player"), StringArgumentType.getString(context, "cosmetic"))))))
                .then(Commands.literal("unequip")
                        .then(Commands.argument("player", EntityArgument.player())
                                .then(Commands.argument("slot", StringArgumentType.word())
                                        .executes(context -> unequip(EntityArgument.getPlayer(context, "player"), StringArgumentType.getString(context, "slot"))))))
                .then(Commands.literal("clearall")
                        .then(Commands.argument("player", EntityArgument.player())
                                .executes(context -> clearAll(EntityArgument.getPlayer(context, "player"))))));
    }

    private static int claim(ServerPlayer player, String claimId) {
        return equip(player, claimId);
    }

    private static int equip(ServerPlayer player, String claimId) {
        var itemIds = CosmeticItemCatalog.itemsForClaim(claimId);
        if (itemIds.isEmpty()) return 0;
        final int[] equipped = {0};
        CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
            for (String itemId : itemIds) {
                CosmeticItemCatalog.Definition def = CosmeticItemCatalog.byId(itemId);
                if (def == null) continue;
                Item item = BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(ArlightCosmeticsCurios.MOD_ID, itemId));
                if (!(item instanceof OwnerBoundCosmeticItem)) continue;
                handler.getStacksHandler(def.slotId()).ifPresent(stacksHandler -> {
                    var stacks = stacksHandler.getStacks();
                    ItemStack stack = new ItemStack(item);
                    CosmeticOwnership.bind(stack, player.getUUID(), player.getGameProfile().getName(), claimId);
                    stacks.setStackInSlot(0, stack);
                    equipped[0]++;
                });
            }
        });
        return equipped[0];
    }

    private static int unequip(ServerPlayer player, String pluginSlot) {
        String slotId = switch (pluginSlot.toUpperCase(java.util.Locale.ROOT)) {
            case "HEAD" -> "arlight_head";
            case "CHEST" -> "arlight_chest";
            case "LEGS" -> "arlight_legs";
            case "FEET" -> "arlight_feet";
            case "BACK" -> "arlight_back";
            case "TAIL" -> "arlight_tail";
            case "AURA" -> "arlight_aura";
            case "COMPANION" -> "arlight_companion";
            case "HANDS" -> "arlight_hands";
            case "SHOULDER" -> "arlight_shoulder";
            case "TRAIL" -> "arlight_trail";
            default -> "";
        };
        if (slotId.isEmpty()) return 0;
        final int[] removed = {0};
        CuriosApi.getCuriosInventory(player).ifPresent(handler -> handler.getStacksHandler(slotId).ifPresent(stacksHandler -> {
            var stacks = stacksHandler.getStacks();
            for (int i = 0; i < stacks.getSlots(); i++) {
                if (!stacks.getStackInSlot(i).isEmpty()) {
                    stacks.setStackInSlot(i, ItemStack.EMPTY);
                    removed[0]++;
                }
            }
        }));
        return removed[0];
    }

    private static int clearAll(ServerPlayer player) {
        String[] slots = {"arlight_head", "arlight_chest", "arlight_legs", "arlight_feet",
                "arlight_back", "arlight_tail", "arlight_aura", "arlight_companion",
                "arlight_hands", "arlight_shoulder", "arlight_trail"};
        final int[] removed = {0};
        CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
            for (String slotId : slots) {
                handler.getStacksHandler(slotId).ifPresent(stacksHandler -> {
                    var stacks = stacksHandler.getStacks();
                    for (int i = 0; i < stacks.getSlots(); i++) {
                        if (!stacks.getStackInSlot(i).isEmpty()) {
                            stacks.setStackInSlot(i, ItemStack.EMPTY);
                            removed[0]++;
                        }
                    }
                });
            }
        });
        return removed[0];
    }

    private CosmeticCommands() { }
}
