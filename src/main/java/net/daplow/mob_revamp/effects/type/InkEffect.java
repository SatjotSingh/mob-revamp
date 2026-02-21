package net.daplow.mob_revamp.effects.type;


import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.world.World;
import net.minecraft.particle.ParticleTypes;

public class InkEffect extends StatusEffect {

    public InkEffect() {
        super(StatusEffectCategory.HARMFUL, 0x000080); // black color
    }

    /**
     * Called every tick on entities with this effect
     */


    /**
     * This ensures the effect ticks every tick
     */
    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }
}