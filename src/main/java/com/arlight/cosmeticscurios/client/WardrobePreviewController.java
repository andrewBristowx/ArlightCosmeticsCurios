package com.arlight.cosmeticscurios.client;

import com.arlight.cosmeticscurios.ArlightCosmeticsCurios;
import com.arlight.cosmeticscurios.CosmeticItemCatalog;
import com.arlight.cosmeticscurios.CosmeticOwnership;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.CuriosApi;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Vista previa local y aislada del ropero.
 *
 * <p>La versión anterior sólo sustituía la ranura seleccionada. Por eso el
 * maniquí conservaba mascotas, alas y prendas equipadas y la vista previa
 * terminaba mostrando varios cosméticos mezclados. Esta versión guarda todas
 * las ranuras cosméticas conocidas, las limpia durante el render y muestra:
 * <ul>
 *   <li>únicamente la mascota elegida, o</li>
 *   <li>el conjunto completo cuando se selecciona una pieza de pijama.</li>
 * </ul>
 * Nunca modifica el perfil persistente ni envía un cambio al servidor.</p>
 */
public final class WardrobePreviewController {
    private static final Map<String, ItemStack> PREVIOUS_BY_SLOT = new LinkedHashMap<>();
    private static final Set<String> ACTIVE_PREVIEW_IDS = new LinkedHashSet<>();
    private static boolean active;

    public static void begin(AbstractClientPlayer player, String cosmeticId) {
        end(player);
        CosmeticItemCatalog.Definition selected = CosmeticItemCatalog.byId(cosmeticId);
        if (selected == null) return;

        CuriosApi.getCuriosInventory(player).ifPresent(handler -> {
            PREVIOUS_BY_SLOT.clear();
            ACTIVE_PREVIEW_IDS.clear();

            // Guardamos y vaciamos todas las ranuras usadas por el catálogo. De este
            // modo la previsualización nunca arrastra una mascota o una pieza previa.
            for (String slot : knownSlots()) {
                handler.getStacksHandler(slot).ifPresent(stacksHandler -> {
                    var stacks = stacksHandler.getStacks();
                    if (stacks.getSlots() < 1) return;
                    PREVIOUS_BY_SLOT.put(slot, stacks.getStackInSlot(0).copy());
                    stacks.setStackInSlot(0, ItemStack.EMPTY);
                });
            }

            for (CosmeticItemCatalog.Definition definition : previewDefinitions(selected)) {
                handler.getStacksHandler(definition.slotId()).ifPresent(stacksHandler -> {
                    var stacks = stacksHandler.getStacks();
                    if (stacks.getSlots() < 1) return;
                    Item item = BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath(
                            ArlightCosmeticsCurios.MOD_ID, definition.itemId()));
                    if (item == null) return;
                    ItemStack preview = new ItemStack(item);
                    CosmeticOwnership.bind(preview, player.getUUID(),
                            player.getGameProfile().getName(), "wardrobe_preview");
                    stacks.setStackInSlot(0, preview);
                    ACTIVE_PREVIEW_IDS.add(definition.itemId());
                });
            }
            active = !ACTIVE_PREVIEW_IDS.isEmpty();
        });
    }

    public static void end(AbstractClientPlayer player) {
        if (!active && PREVIOUS_BY_SLOT.isEmpty()) return;
        Map<String, ItemStack> restore = new LinkedHashMap<>(PREVIOUS_BY_SLOT);
        active = false;
        PREVIOUS_BY_SLOT.clear();
        ACTIVE_PREVIEW_IDS.clear();

        CuriosApi.getCuriosInventory(player).ifPresent(handler -> restore.forEach((slot, stack) ->
                handler.getStacksHandler(slot).ifPresent(stacksHandler -> {
                    if (stacksHandler.getStacks().getSlots() > 0) {
                        stacksHandler.getStacks().setStackInSlot(0, stack.copy());
                    }
                })));
    }


    /** Indica si el cosmético seleccionado usa la ranura de mascota. */
    public static boolean isCompanion(String cosmeticId) {
        CosmeticItemCatalog.Definition definition = CosmeticItemCatalog.byId(cosmeticId);
        return definition != null && definition.companion();
    }

    /** Devuelve X/Y/Z adicionales usados en preview y en el mundo. */
    public static float[] position(String cosmeticId) {
        CosmeticItemCatalog.Definition definition = CosmeticItemCatalog.byId(cosmeticId);
        if (definition == null || !definition.companion()) return new float[]{0.0F, 0.0F, 0.0F};
        PetPositionSettings.Offset offset = PetPositionSettings.get(definition.modelId());
        return new float[]{offset.x(), offset.y(), offset.z()};
    }

    /** Ajuste incremental seguro de la mascota seleccionada. */
    public static float[] adjustPosition(String cosmeticId, float dx, float dy, float dz) {
        CosmeticItemCatalog.Definition definition = CosmeticItemCatalog.byId(cosmeticId);
        if (definition == null || !definition.companion()) return new float[]{0.0F, 0.0F, 0.0F};
        PetPositionSettings.Offset offset = PetPositionSettings.adjust(definition.modelId(), dx, dy, dz);
        PetRenderController.clear();
        return new float[]{offset.x(), offset.y(), offset.z()};
    }

    public static float[] resetPosition(String cosmeticId) {
        CosmeticItemCatalog.Definition definition = CosmeticItemCatalog.byId(cosmeticId);
        if (definition == null || !definition.companion()) return new float[]{0.0F, 0.0F, 0.0F};
        PetPositionSettings.Offset offset = PetPositionSettings.reset(definition.modelId());
        PetPositionSettings.save();
        PetRenderController.clear();
        return new float[]{offset.x(), offset.y(), offset.z()};
    }

    public static void savePositions() {
        PetPositionSettings.save();
    }

    public static boolean isActive() {
        return active;
    }

    public static boolean isPreviewing(String cosmeticId) {
        return active && ACTIVE_PREVIEW_IDS.contains(cosmeticId);
    }

    private static List<CosmeticItemCatalog.Definition> previewDefinitions(
            CosmeticItemCatalog.Definition selected) {
        if ("pajamas".equals(selected.category()) && !selected.setId().isBlank()) {
            List<CosmeticItemCatalog.Definition> set = new ArrayList<>();
            for (CosmeticItemCatalog.Definition definition : CosmeticItemCatalog.all()) {
                if (selected.setId().equals(definition.setId())) set.add(definition);
            }
            if (!set.isEmpty()) return set;
        }
        return List.of(selected);
    }

    private static Set<String> knownSlots() {
        Set<String> slots = new LinkedHashSet<>();
        for (CosmeticItemCatalog.Definition definition : CosmeticItemCatalog.all()) {
            slots.add(definition.slotId());
        }
        return slots;
    }

    private WardrobePreviewController() { }
}
