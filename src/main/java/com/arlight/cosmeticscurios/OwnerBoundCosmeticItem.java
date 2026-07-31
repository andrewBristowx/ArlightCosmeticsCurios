package com.arlight.cosmeticscurios;

import net.minecraft.ChatFormatting;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

public final class OwnerBoundCosmeticItem extends Item implements ICurioItem {
    private final String slotId;
    private final String claimId;

    public OwnerBoundCosmeticItem(Properties properties, String slotId, String claimId) {
        super(properties);
        this.slotId = slotId;
        this.claimId = claimId;
    }

    public String slotId() { return slotId; }
    public String claimId() { return claimId; }

    @Override
    public boolean canEquip(SlotContext slotContext, ItemStack stack) {
        return slotContext != null && slotId.equals(slotContext.identifier())
                && CosmeticOwnership.canUse(stack, slotContext.entity());
    }

    @Override
    public boolean canUnequip(SlotContext slotContext, ItemStack stack) {
        // Los cosméticos se cambian desde /ropero. Evita extracción manual,
        // pérdida, duplicación o intercambio desde la pantalla de Curios.
        return false;
    }

    @Override
    public boolean isFoil(ItemStack stack) { return true; }

    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        LivingEntity entity = slotContext == null ? null : slotContext.entity();
        if (entity == null || !entity.level().isClientSide || !CosmeticOwnership.canUse(stack, entity)) return;
        if (!CosmeticRuntimeSettings.particlesEnabled()) return;
        boolean reducedParticles = CosmeticRuntimeSettings.reducedParticles();
        ResourceLocation key = BuiltInRegistries.ITEM.getKey(stack.getItem());
        String itemId = key == null ? "" : key.getPath();

        // Pony orbital: una estela pastel circular, limitada para no saturar el cliente.
        if ("pony_pastel_legs".equals(itemId) && entity.tickCount % (reducedParticles ? 8 : 4) == 0) {
            double angle = entity.tickCount * 0.24D;
            double radius = 0.82D;
            double x = entity.getX() + Math.cos(angle) * radius;
            double y = entity.getY() + 0.72D + Math.sin(angle * 1.7D) * 0.12D;
            double z = entity.getZ() + Math.sin(angle) * radius;
            entity.level().addParticle(ParticleTypes.END_ROD, x, y, z, 0.0D, 0.008D, 0.0D);
            if (entity.tickCount % 12 == 0) {
                entity.level().addParticle(ParticleTypes.HAPPY_VILLAGER, x, y + 0.08D, z, 0.0D, 0.0D, 0.0D);
            }
            return;
        }

        // El adorno de pecho emite un destello breve sincronizado con el guiño.
        if ("pony_pastel_chest".equals(itemId) && entity.tickCount % (reducedParticles ? 152 : 76) == 0) {
            entity.level().addParticle(ParticleTypes.END_ROD,
                    entity.getX(), entity.getY() + 1.35D, entity.getZ() - 0.30D,
                    0.0D, 0.012D, 0.0D);
            return;
        }

        // Los números 6 y 7 dejan una bruma violeta muy ligera sobre la cabeza.
        if ("somita_night_legs".equals(itemId) && entity.tickCount % (reducedParticles ? 14 : 7) == 0) {
            double angle = entity.tickCount * -0.19D;
            entity.level().addParticle(ParticleTypes.WITCH,
                    entity.getX() + Math.cos(angle) * 0.48D,
                    entity.getY() + 2.12D,
                    entity.getZ() + Math.sin(angle) * 0.48D,
                    0.0D, 0.004D, 0.0D);
            return;
        }

        // Las mascotas muestran un corazón ocasional; no se ejecuta cada tick.
        if ("arlight_companion".equals(slotId) && entity.tickCount % (reducedParticles ? 240 : 120) == 0) {
            entity.level().addParticle(ParticleTypes.HEART,
                    entity.getX() + 0.55D, entity.getY() + 0.65D, entity.getZ() + 0.35D,
                    0.0D, 0.015D, 0.0D);
        }

        if (!"arlight_trail".equals(slotId) || entity.tickCount % (reducedParticles ? 6 : 3) != 0) return;
        double horizontalSpeed = entity.getDeltaMovement().horizontalDistanceSqr();
        if (horizontalSpeed < 0.0004D || !entity.onGround()) return;
        var particle = claimId.equals("dino_footprints") ? ParticleTypes.COMPOSTER
                : claimId.equals("axolotl_bubbles") ? ParticleTypes.BUBBLE_POP
                : claimId.equals("flower_petals_steps") ? ParticleTypes.COMPOSTER
                : claimId.equals("pastel_star_steps") ? ParticleTypes.END_ROD
                : claimId.startsWith("somita_") ? ParticleTypes.WITCH
                : claimId.startsWith("bingo_") ? ParticleTypes.PORTAL
                : claimId.startsWith("swansito_") ? ParticleTypes.COMPOSTER
                : ParticleTypes.END_ROD;
        double x = entity.getX() + (entity.getRandom().nextDouble() - 0.5D) * 0.45D;
        double y = entity.getY() + 0.08D;
        double z = entity.getZ() + (entity.getRandom().nextDouble() - 0.5D) * 0.45D;
        entity.level().addParticle(particle, x, y, z, 0.0D, 0.012D, 0.0D);
        if (claimId.equals("axolotl_bubbles") && entity.tickCount % 6 == 0) {
            entity.level().addParticle(ParticleTypes.END_ROD, x, y + 0.05D, z, 0.0D, 0.006D, 0.0D);
        } else if (claimId.equals("pastel_star_steps") && entity.tickCount % 6 == 0) {
            entity.level().addParticle(ParticleTypes.HAPPY_VILLAGER, x, y + 0.04D, z, 0.0D, 0.004D, 0.0D);
        }
        if (claimId.startsWith("swansito_") && entity.tickCount % 6 == 0) {
            entity.level().addParticle(ParticleTypes.CRIMSON_SPORE,
                    x + (entity.getRandom().nextDouble() - 0.5D) * 0.18D,
                    y + 0.04D,
                    z + (entity.getRandom().nextDouble() - 0.5D) * 0.18D,
                    0.0D, 0.006D, 0.0D);
        }
    }

    @Override
    public Component getName(ItemStack stack) {
        String owner = CosmeticOwnership.ownerName(stack);
        Component base = super.getName(stack);
        return owner.isBlank() ? base : base.copy().append(
                Component.literal(" • " + owner).withStyle(ChatFormatting.DARK_GRAY));
    }
}
