package net.daplow.mob_revamp.effects.type;


import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

import java.util.List;


public class EcholocationEffect extends StatusEffect {

    public EcholocationEffect() {
        super(StatusEffectCategory.NEUTRAL, 0x523111);
    }

    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        World world = entity.getWorld();
        Random random = world.random;

        if (!world.isClient && random.nextFloat() < 0.4f) {
            SoundEvent[] sounds = {
                    SoundEvents.BLOCK_CAVE_VINES_PLACE,
                    SoundEvents.AMBIENT_CAVE
            };
            SoundEvent sound = sounds[random.nextInt(sounds.length)];
            world.playSound(null, entity.getBlockPos(), sound, entity.getSoundCategory(), 0.5f, 1f);
        }

        // Make nearby mobs glow
        List<LivingEntity> entities = world.getEntitiesByClass(LivingEntity.class, entity.getBoundingBox().expand(20), e -> !e.equals(entity));
        for (LivingEntity e : entities) {
            e.addStatusEffect(new net.minecraft.entity.effect.StatusEffectInstance(
                    net.minecraft.entity.effect.StatusEffects.GLOWING, 20, 0, false, false
            ));
        }
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }

}

