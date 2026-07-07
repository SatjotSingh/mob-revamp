package net.daplow.mob_revamp.effects.type;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;

public class FreezingEffect extends StatusEffect {


    public FreezingEffect() {super(StatusEffectCategory.BENEFICIAL, 0x53B4FF);}


    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        /*
        if (!(entity instanceof PlayerEntity player)) return;

        List<HostileEntity> nearbyMobs = player.getWorld().getEntitiesByClass(
                HostileEntity.class,
                player.getBoundingBox().expand(16),
                mob -> true
        );


        for (HostileEntity mob : nearbyMobs) {
            if (mob.getTarget() == player) {
                mob.setTarget(null);

                if (mob instanceof AbstractSkeletonEntity skeleton) {
                    skeleton.setAttacking(false);
                    skeleton.clearActiveItem();
                }
                if (mob instanceof CreeperEntity creeper) {
                    creeper.setFuseSpeed(-1);
                }

            }
        }

         */


    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }


}
