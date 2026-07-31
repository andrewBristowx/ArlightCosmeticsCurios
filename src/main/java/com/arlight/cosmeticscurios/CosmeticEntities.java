package com.arlight.cosmeticscurios;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

/** Registro único de las entidades cosméticas controladas por el servidor. */
public final class CosmeticEntities {
    public static final DeferredRegister<EntityType<?>> ENTITIES =
            DeferredRegister.create(BuiltInRegistries.ENTITY_TYPE, ArlightCosmeticsCurios.MOD_ID);

    public static final DeferredHolder<EntityType<?>, EntityType<CompanionEntity>> COMPANION =
            ENTITIES.register("companion", () -> EntityType.Builder
                    .of(CompanionEntity::new, MobCategory.MISC)
                    // La navegación usa las dimensiones registradas del tipo. Se registra
                    // el máximo real y cada modelo reduce después su AABB; así ningún pet
                    // calcula rutas por huecos donde su cuerpo visual no cabe.
                    .sized(1.46F, 1.18F)
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
