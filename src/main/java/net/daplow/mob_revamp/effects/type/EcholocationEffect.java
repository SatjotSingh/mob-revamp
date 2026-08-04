package net.daplow.mob_revamp.effects.type;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

public class EcholocationEffect extends StatusEffect {

    public EcholocationEffect() {
        super(StatusEffectCategory.NEUTRAL, 0x523111); // Brown Color
    }

    private static final SoundEvent[] AMBIENT_SOUNDS = {
            SoundEvents.BLOCK_CAVE_VINES_PLACE,
            SoundEvents.AMBIENT_CAVE
    };

    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        World world = entity.getWorld();
        if (world.isClient) {return;}
        Random random = world.random;

        // Random Ambience Sound
        if ( random.nextFloat() < 0.4f) {
            SoundEvent sound = AMBIENT_SOUNDS[random.nextInt(AMBIENT_SOUNDS.length)];
            world.playSound(null, entity.getBlockPos(), sound, entity.getSoundCategory(), 0.5f, 1f);
        }

        // Make nearby mobs glow
        for (LivingEntity target : world.getEntitiesByClass(
                LivingEntity.class,
                entity.getBoundingBox().expand(20),
                e -> e != entity
        )) {
            target.addStatusEffect(new StatusEffectInstance(
                    StatusEffects.GLOWING,
                    25,
                    0,
                    false,
                    false
            ));
        }
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return duration % 20 == 0;
    }

}

