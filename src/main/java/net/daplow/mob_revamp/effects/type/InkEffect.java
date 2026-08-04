package net.daplow.mob_revamp.effects.type;


import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.AttributeContainer;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.particle.ParticleTypes;

public class InkEffect extends StatusEffect {

    public InkEffect() {
        super(StatusEffectCategory.HARMFUL, 0x000080); // black color
    }

/*
If Ink Effect value = 0, then Black Ink
IF Ink Effect value = 1, then Glow Ink
 */
    @Override
    public void onApplied(LivingEntity entity, AttributeContainer attributes, int amplifier) {
        World world = entity.getWorld();
        if (world.isClient) {
            return;
        }
        world.playSound(
                null,    // PlayerEntity: null = all nearby players
                entity.getBlockPos(),  // BlockPos where sound plays
                SoundEvents.ENTITY_SQUID_SQUIRT,
                SoundCategory.PLAYERS,
                1.0f,
                0.9f + entity.getRandom().nextFloat() * 0.2f
        );

    }

    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {

        DefaultParticleType inkType =
                amplifier > 0
                        ? ParticleTypes.GLOW_SQUID_INK
                        : ParticleTypes.SQUID_INK;

        for (int i =0; i <2; i++) {
            if (!entity.getWorld().isClient) {
                Vec3d eyes = entity.getEyePos();
                ((ServerWorld) entity.getWorld()).spawnParticles(
                        inkType,
                        eyes.x,
                        eyes.y-0.2,
                        eyes.z,
                        4, // count
                        entity.getWidth() * 0.3,
                        entity.getHeight() * 0.2,
                        entity.getWidth() * 0.3,
                        0.02
                );
            }
        }

    }


    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {return duration % 2 == 0;}
}