package net.daplow.mob_revamp.effects.type;


import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.AttributeContainer;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
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
        if (!entity.getWorld().isClient) {
            return;
        }
        entity.getWorld().playSound(
                null,                  // PlayerEntity: null = all nearby players
                entity.getBlockPos(),  // BlockPos where sound plays
                SoundEvents.ENTITY_SQUID_SQUIRT,
                SoundCategory.PLAYERS,
                1.0f,                  // volume
                0.9f + entity.getRandom().nextFloat() * 0.2f // pitch
        );

    }

    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        //System.out.println("POTION: "+entity.getEntityName());
        /*if (!entity.getWorld().isClient()) {
            System.out.println("POTION CLIENT: "+entity.getEntityName());

            return;
        }

         */

        //if (entity.getRandom().nextDouble() <0.5) {
        //    return;
        //}


        //System.out.println("POTION: "+entity.getEntityName());

        DefaultParticleType inkType = ParticleTypes.SQUID_INK;
        if (amplifier ==1) {
            inkType = ParticleTypes.GLOW_SQUID_INK;
        }

        for (int i =0; i <2; i++) {
            double offsetX = (entity.getRandom().nextDouble()-0.7) * entity.getWidth();
            double offsetY = (entity.getRandom().nextDouble()-0.3) * entity.getHeight();
            double offsetZ = (entity.getRandom().nextDouble()-0.7) * entity.getWidth();
            if (!entity.getWorld().isClient) {
                ((ServerWorld) entity.getWorld()).spawnParticles(
                        inkType,
                        entity.getX(),
                        entity.getY(),
                        entity.getZ(),
                        5, // count
                        offsetX,
                        offsetY,
                        offsetZ,
                        0.02
                );
            }
            /*
            entity.getWorld().addParticle(
                    ParticleTypes.SQUID_INK,
                    entity.getX() + offsetX,
                    entity.getY() + offsetY,
                    entity.getZ() +offsetZ,
                    0.0, 0.2, 0.0

            );

             */
        }

    }


    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {return true;}
}