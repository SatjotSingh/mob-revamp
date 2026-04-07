package net.daplow.mob_revamp.blocks.custom;

import net.daplow.mob_revamp.blocks.ModBlocks;
import net.daplow.mob_revamp.effects.ModEffects;
import net.daplow.mob_revamp.items.ModItems;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.PillarBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.context.LootContext;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Collections;
import java.util.List;

@SuppressWarnings("deprecation")
public class WitherBoneBlock extends PillarBlock {
    public WitherBoneBlock(Settings settings) {
        super(settings);
    }


    @Override
    public void onSteppedOn(World world, BlockPos pos, BlockState state, Entity entity) {
        super.onSteppedOn(world, pos, state, entity);
        if (!world.isClient) {
            PlayerEntity player = (PlayerEntity) entity;
            player.addStatusEffect(new StatusEffectInstance(
                    StatusEffects.WITHER,
                    40, //ticks (seconds*20)
                    0,
                    false,
                    false,
                    true

            ));
        }

    }

    @Override
    public List<ItemStack> getDroppedStacks(BlockState state, LootContext.Builder builder) {
        return Collections.singletonList(new ItemStack(ModBlocks.WITHER_BONE_BLOCK));
    }
}
