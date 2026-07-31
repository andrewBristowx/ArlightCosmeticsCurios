package com.arlight.cosmeticscurios.client;

import com.arlight.cosmeticscurios.CosmeticItemCatalog;
import com.arlight.cosmeticscurios.CosmeticOwnership;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraft.world.item.ItemStack;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RenderPlayerEvent;
import top.theillusivec4.curios.api.CuriosApi;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Oculta sólo durante el render las partes del jugador cubiertas por un pijama.
 * El cosmético continúa siendo Curios y no modifica skin, inventario, defensa ni
 * armadura del servidor. La cara se conserva visible dentro de la capucha.
 */
@EventBusSubscriber(
        modid = com.arlight.cosmeticscurios.ArlightCosmeticsCurios.MOD_ID,
        value = Dist.CLIENT
)
public final class PajamaRenderVisibility {
    private static final int HEAD = 1;
    private static final int CHEST = 1 << 1;
    private static final int LEGS = 1 << 2;
    private static final int FEET = 1 << 3;
    private static final ThreadLocal<Deque<Snapshot>> SNAPSHOTS =
            ThreadLocal.withInitial(ArrayDeque::new);

    private record Snapshot(PlayerModel<?> model,
                            boolean head, boolean hat,
                            boolean body, boolean jacket,
                            boolean leftArm, boolean rightArm,
                            boolean leftSleeve, boolean rightSleeve,
                            boolean leftLeg, boolean rightLeg,
                            boolean leftPants, boolean rightPants) {
        static Snapshot capture(PlayerModel<?> model) {
            return new Snapshot(model, model.head.visible, model.hat.visible,
                    model.body.visible, model.jacket.visible,
                    model.leftArm.visible, model.rightArm.visible,
                    model.leftSleeve.visible, model.rightSleeve.visible,
                    model.leftLeg.visible, model.rightLeg.visible,
                    model.leftPants.visible, model.rightPants.visible);
        }

        void restore() {
            model.head.visible = head;
            model.hat.visible = hat;
            model.body.visible = body;
            model.jacket.visible = jacket;
            model.leftArm.visible = leftArm;
            model.rightArm.visible = rightArm;
            model.leftSleeve.visible = leftSleeve;
            model.rightSleeve.visible = rightSleeve;
            model.leftLeg.visible = leftLeg;
            model.rightLeg.visible = rightLeg;
            model.leftPants.visible = leftPants;
            model.rightPants.visible = rightPants;
        }
    }

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void beforePlayer(RenderPlayerEvent.Pre event) {
        if (!(event.getEntity() instanceof AbstractClientPlayer player)) return;
        PlayerRenderer renderer = event.getRenderer();
        PlayerModel<?> model = renderer.getModel();
        int coverage = pajamaCoverage(player);
        SNAPSHOTS.get().push(Snapshot.capture(model));
        if (coverage == 0) return;
        if ((coverage & HEAD) != 0) {
            // La cara debe seguir visible; sólo se retira la segunda capa de la skin.
            model.hat.visible = false;
        }
        if ((coverage & CHEST) != 0) {
            model.body.visible = false;
            model.jacket.visible = false;
            model.leftArm.visible = false;
            model.rightArm.visible = false;
            model.leftSleeve.visible = false;
            model.rightSleeve.visible = false;
        }
        if ((coverage & (LEGS | FEET)) != 0) {
            model.leftLeg.visible = false;
            model.rightLeg.visible = false;
            model.leftPants.visible = false;
            model.rightPants.visible = false;
        }
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void afterPlayer(RenderPlayerEvent.Post event) {
        Deque<Snapshot> snapshots = SNAPSHOTS.get();
        if (snapshots.isEmpty()) return;
        snapshots.pop().restore();
        if (snapshots.isEmpty()) SNAPSHOTS.remove();
    }

    private static int pajamaCoverage(AbstractClientPlayer player) {
        int[] coverage = {0};
        CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
            java.util.Set<String> activeSets = new java.util.LinkedHashSet<>();
            for (String slotId : new String[]{
                    "arlight_head", "arlight_chest", "arlight_legs", "arlight_feet"
            }) {
                ItemStack stack = handler.getStacksHandler(slotId)
                        .map(slot -> slot.getStacks().getStackInSlot(0))
                        .orElse(ItemStack.EMPTY);
                CosmeticItemCatalog.Definition definition = pajama(stack, player);
                if (definition == null) continue;
                coverage[0] |= definitionCoverage(definition);
                if (!definition.setId().isBlank()) activeSets.add(definition.setId());
            }

            // El ropero y el servidor equipan los pijamas como conjuntos. Curios
            // sincroniza sus ranuras por separado, así que durante uno o varios
            // fotogramas puede llegar primero HEAD o FEET. Antes se ocultaba sólo
            // esa parte de la skin y torso/brazos atravesaban el modelo. Una pieza
            // de un set ahora declara la cobertura completa del conjunto desde el
            // primer fotograma, sin tocar inventario ni convertirlo en armadura.
            if (!activeSets.isEmpty()) {
                for (CosmeticItemCatalog.Definition definition : CosmeticItemCatalog.all()) {
                    if (activeSets.contains(definition.setId())
                            && "pajamas".equals(definition.category())) {
                        coverage[0] |= definitionCoverage(definition);
                    }
                }
            }
        });
        return coverage[0];
    }

    private static CosmeticItemCatalog.Definition pajama(ItemStack stack,
                                                          AbstractClientPlayer player) {
        if (stack.isEmpty() || !CosmeticOwnership.canUse(stack, player)) return null;
        CosmeticItemCatalog.Definition definition = CosmeticItemCatalog.byItem(stack.getItem());
        if (definition == null || !"pajamas".equals(definition.category())) return null;
        return definition;
    }

    private static int definitionCoverage(CosmeticItemCatalog.Definition definition) {
        return switch (definition.slotId()) {
            case "arlight_head" -> HEAD;
            case "arlight_chest" -> CHEST;
            case "arlight_legs" -> LEGS;
            case "arlight_feet" -> FEET;
            default -> 0;
        };
    }

    private PajamaRenderVisibility() { }
}
