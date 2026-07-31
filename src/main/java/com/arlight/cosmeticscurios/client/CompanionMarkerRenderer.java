package com.arlight.cosmeticscurios.client;

import com.arlight.cosmeticscurios.CompanionEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

/**
 * La entidad aporta física y sincronización; el modelo se dibuja una sola vez
 * desde el renderizador Curios para conservar todos los modelos ya aprobados.
 */
public final class CompanionMarkerRenderer extends EntityRenderer<CompanionEntity> {
    private static final ResourceLocation EMPTY = ResourceLocation.fromNamespaceAndPath(
            "arlightcosmeticscurios", "textures/cosmetics/swansito_mini_trex.png");

    public CompanionMarkerRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public void render(CompanionEntity entity, float yaw, float partialTick,
                       PoseStack poseStack, MultiBufferSource buffers, int light) {
        // Intencionadamente vacío: evita dibujar dos copias de la mascota.
    }

    @Override
    public ResourceLocation getTextureLocation(CompanionEntity entity) {
        return EMPTY;
    }
}
