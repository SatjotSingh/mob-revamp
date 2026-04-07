package net.daplow.mob_revamp.items.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.Waterloggable;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.Items;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.tag.BlockTags;
import net.minecraft.tag.FluidTags;
import net.minecraft.util.ActionResult;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

public class WitherBoneMealItem extends Item {


    public WitherBoneMealItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {

        if (context.getWorld().isClient) return super.useOnBlock(context);

        World world = context.getWorld();
        BlockPos pos = context.getBlockPos();
        ItemStack stack = context.getStack();

        PlayerEntity player = context.getPlayer();

        Block block = world.getBlockState(pos).getBlock();


        if (block.asItem() == Items.GRASS_BLOCK || isGrass(world.getBlockState(pos))) {
            int xzRadius = 2;
            int yRadius = 2;
            for (int x = -xzRadius; x <= xzRadius; x++) {
                for (int y = -yRadius; y <= yRadius; y++) {
                    for (int z = -xzRadius; z <= xzRadius; z++) {

                        BlockPos currentPos = pos.add(x, y, z);
                        BlockPos abovePos = currentPos.add(0,1,0);
                        if (world.getBlockState(currentPos).getBlock().asItem() == Items.GRASS_BLOCK) {
                            if (isGrass(world.getBlockState(abovePos))) {
                                world.setBlockState(abovePos, Blocks.AIR.getDefaultState());


                            }
                            ((ServerWorld)world).spawnParticles(
                                    ParticleTypes.DAMAGE_INDICATOR,
                                    abovePos.getX() + world.getRandom().nextDouble() -0.5f,
                                    abovePos.getY()+ 0.0f,
                                    abovePos.getZ() + world.getRandom().nextDouble() -0.5f,
                                    1,
                                    0,
                                    0,
                                    0,
                                    0
                            );
                        }

                    }
                }
            }
            
            // Sound
            world.playSound(
                    null,
                    pos,
                    SoundEvents.BLOCK_GRASS_BREAK,
                    SoundCategory.BLOCKS,
                    1.0f,
                    1.0f
            );

            if (!player.isCreative()) {
                stack.decrement(1);
            }
            return TypedActionResult.success(stack).getResult();

        } else if (isTouchingWater(world, pos)) {  // Check water

            int xzRadius = 2;
            int yRadius = 2;
            for (int x = -xzRadius; x <= xzRadius; x++) {
                for (int y = -yRadius; y <= yRadius; y++) {
                    for (int z = -xzRadius; z <= xzRadius; z++) {

                        BlockPos currentPos = pos.add(x, y, z);
                        BlockPos abovePos = currentPos.add(0,1,0);

                        if (isWaterGrass(world.getBlockState(abovePos))) {
                            world.setBlockState(abovePos, Blocks.WATER.getDefaultState());

                            ((ServerWorld)world).spawnParticles(
                                    ParticleTypes.DAMAGE_INDICATOR,
                                    abovePos.getX() + world.getRandom().nextDouble() -0.5f,
                                    abovePos.getY()+ 0.0f,
                                    abovePos.getZ() + world.getRandom().nextDouble() -0.5f,
                                    1,
                                    0,
                                    0.1,
                                    0,
                                    0.1
                            );
                        }

                    }
                }
            }
            ((ServerWorld)world).spawnParticles(
                    ParticleTypes.DAMAGE_INDICATOR,
                    pos.getX() + world.getRandom().nextDouble() -0.5f,
                    pos.getY()+ 1.0f,
                    pos.getZ() + world.getRandom().nextDouble() -0.5f,
                    1,
                    0,
                    0.05,
                    0,
                    0
            );
            // Sound
            world.playSound(
                    null,
                    pos,
                    SoundEvents.BLOCK_GRASS_BREAK,
                    SoundCategory.BLOCKS,
                    1.0f,
                    1.0f
            );
            if (!player.isCreative()) {
                stack.decrement(1);
            }
            return TypedActionResult.success(stack).getResult();
        }
        return super.useOnBlock(context);
    }


    private boolean isGrass(BlockState blockState) {

        return blockState.isIn(BlockTags.FLOWERS) ||
                blockState.isOf(Blocks.GRASS) ||
                blockState.isOf(Blocks.TALL_GRASS) ||
                blockState.isOf(Blocks.FERN) ||
                blockState.isOf(Blocks.LARGE_FERN);
    }

    private boolean isWaterGrass(BlockState blockState) {

        return blockState.isOf(Blocks.SEAGRASS) ||
                blockState.isOf(Blocks.TALL_SEAGRASS) ||
                blockState.isOf(Blocks.KELP) ||
                blockState.isOf(Blocks.KELP_PLANT);
    }

    private boolean isTouchingWater(World world, BlockPos pos) {
        for (Direction dir : Direction.values()) {
            BlockPos offset = pos.offset(dir);

            if (world.getFluidState(offset).isIn(FluidTags.WATER)) {
                return true;
            }
        }

        return false;
    }


}