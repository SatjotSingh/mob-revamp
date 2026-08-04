package net.daplow.mob_revamp.effects.type;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;

public class RabbitGraceEffect extends StatusEffect {


    public RabbitGraceEffect() {super(StatusEffectCategory.BENEFICIAL, 0xFF0B6623); } // Light Green


    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return false;
    }


}
