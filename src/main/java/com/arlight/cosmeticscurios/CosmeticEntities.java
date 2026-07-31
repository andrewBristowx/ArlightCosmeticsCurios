package com.arlight.cosmeticscurios;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

/** Registro único de las entidades cosméticas controladas por el servidor. */
public final class CosmeticEntities {
    public static final DeferredRegister.Entities ENTITIES =
            DeferredRegister.createEntities(ArlightCosmeticsCurios.MOD_ID);

    public static final DeferredHolder<EntityType<?>, EntityType<CompanionEntity>> COMPANION =
            ENTITIES.register("companion", () -> EntityType.Builder
                    .of(CompanionEntity::new, MobCategory.MISC)
                    .sized(0.54F, 0.78F)
                    .noSummon()
                    .noSave()
                    .clientTrackingRange(8)
                    .updateInterval(2)
                    .build(ArlightCosmeticsCurios.MOD_ID + ":companion"));

    public static void createAttributes(EntityAttributeCreationEvent event) {
        event.put(COMPANION.get(), CompanionEntity.createAttributes().build());
    }

    private CosmeticEntities() { }
}
