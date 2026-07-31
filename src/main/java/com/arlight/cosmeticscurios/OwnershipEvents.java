package com.arlight.cosmeticscurios;

import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.common.util.TriState;
import net.neoforged.neoforge.event.ItemStackedOnOtherEvent;
import net.neoforged.neoforge.event.entity.player.ItemEntityPickupEvent;

public final class OwnershipEvents {
    @SubscribeEvent
    public static void onPickup(ItemEntityPickupEvent.Pre event) {
        ItemStack stack = event.getItemEntity().getItem();
        if (CosmeticOwnership.isBoundCosmetic(stack)
                && !CosmeticOwnership.canUse(stack, event.getPlayer())) {
            event.setCanPickup(TriState.FALSE);
        }
    }

    @SubscribeEvent
    public static void onStacked(ItemStackedOnOtherEvent event) {
        ItemStack carried = event.getCarriedItem();
        ItemStack target = event.getStackedOnItem();
        if ((CosmeticOwnership.isBoundCosmetic(carried) && !CosmeticOwnership.canUse(carried, event.getPlayer()))
                || (CosmeticOwnership.isBoundCosmetic(target) && !CosmeticOwnership.canUse(target, event.getPlayer()))) {
            event.setCanceled(true);
        }
    }

    private OwnershipEvents() { }
}
