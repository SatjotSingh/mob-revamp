package net.daplow.mob_revamp.effects.type;

import net.daplow.mob_revamp.effects.ModEffects;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FireBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.property.Properties;
import net.minecraft.tag.FluidTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class FreezingEffect extends StatusEffect {


    public FreezingEffect() {super(StatusEffectCategory.BENEFICIAL, 0x53B4FF);}


    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        if (!entity.canFreeze()) {
            //return;
        }

        // Makes the entity behave as though it's inside powder snow.
        entity.setInPowderSnow(true);

        // Increase frozen ticks.
        int max = entity.getMinFreezeDamageTicks();
        int increase = amplifier + 1;

        entity.setFrozenTicks(Math.min(
                entity.getFrozenTicks() + increase,
                max
        ));

        //BlockPos pos = entity.getBlockPos();

        World world = entity.getWorld();
        //BlockState state = entity.getWorld().getBlockState(pos);

        if (world.isClient()) return;

        //World world = entity.getWorld();
        BlockPos center = entity.getBlockPos();

        for (BlockPos pos : BlockPos.iterate(
                center.add(0, -1, 0),
                center.add(0, 1, 0))) {

            BlockState state = world.getBlockState(pos);

            if (state.getFluidState().isIn(FluidTags.LAVA)) {
                world.setBlockState(
                        pos,
                        state.getFluidState().isStill()
                                ? Blocks.OBSIDIAN.getDefaultState()
                                : Blocks.COBBLESTONE.getDefaultState()
                );

                removeFreezing(entity);
            }

            if (state.isOf(Blocks.FIRE) ||state.isOf(Blocks.SOUL_FIRE)) {
                world.setBlockState(pos, Blocks.AIR.getDefaultState());
                removeFreezing(entity);
            }
            if ( state.isOf(Blocks.CAMPFIRE) || state.isOf(Blocks.SOUL_CAMPFIRE)) {
                world.setBlockState(pos, state.with(Properties.LIT, false));
                removeFreezing(entity);
            }
        }

    }


    private void removeFreezing(LivingEntity entity){
        entity.removeStatusEffect(ModEffects.FREEZING_POTION);
        entity.setFrozenTicks(0);
        entity.getWorld().playSound(
                null, // null = all nearby players hear it
                entity.getBlockPos(),
                SoundEvents.BLOCK_FIRE_EXTINGUISH,
                SoundCategory.BLOCKS,
                1.0F,
                1.0F
        );
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {return true;
    }


}
