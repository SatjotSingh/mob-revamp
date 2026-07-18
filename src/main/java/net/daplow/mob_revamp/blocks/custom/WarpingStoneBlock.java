package net.daplow.mob_revamp.blocks.custom;

import net.daplow.mob_revamp.blocks.ModBlockEntities;
import net.daplow.mob_revamp.blocks.ModBlocks;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class WarpingStoneBlock extends BlockWithEntity {


    public WarpingStoneBlock(Settings settings) {
        super(settings);
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new WarpingStoneBlockEntity(pos, state);
    }

    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(
            World world,
            BlockState state,
            BlockEntityType<T> type) {

        return checkType(type, ModBlockEntities.WARPING_STONE,
                WarpingStoneBlock::tick);
    }

    @Override
    public void onSteppedOn(World world, BlockPos pos, BlockState state, Entity entity) {
        super.onSteppedOn(world, pos, state, entity);

        if(world.isClient()) return;

        if (Math.random() < 0.4) {
            ((ServerWorld) world).spawnParticles(
                    ParticleTypes.END_ROD,
                    pos.getX() + Math.random(),
                    pos.getY() + 1,
                    pos.getZ() + Math.random(),
                    1,
                    0,
                    0,
                    0,
                    0
            );
        }

        if (Math.random() < 0.05) {
            world.playSound(
                    null, // null = all nearby players hear it
                    pos,
                    SoundEvents.AMBIENT_WARPED_FOREST_ADDITIONS,
                    SoundCategory.BLOCKS,
                    0.1F, // volume
                    1.0F  // pitch
            );
        }

        if (!(world.getBlockEntity(pos) instanceof WarpingStoneBlockEntity source)) return;

        if (entity instanceof PlayerEntity player && player.isSneaking() &&  source.teleportCooldown < 1) {
            List<BlockPos> warpingStones = getNearbyWarpingStones(world, pos);

            if (!warpingStones.isEmpty()) {
                BlockPos target = warpingStones.get(world.random.nextInt(warpingStones.size()));

                if (!(world.getBlockEntity(target) instanceof WarpingStoneBlockEntity targetEntity)) return;

                source.teleportCooldown = 20;
                targetEntity.teleportCooldown = 20;

                player.requestTeleport(
                        target.getX() + 0.5,
                        target.getY() + 1,
                        target.getZ() + 0.5
                );
            }
        }
    }

    // --- CUSTOM CLASSES ---- //
    private static List<BlockPos> getNearbyWarpingStones(World world, BlockPos pos) {
        List<BlockPos> warpingStones = new ArrayList<>();

        BlockPos.Mutable mutable = new BlockPos.Mutable();

        for (int x = -8; x <= 7; x++) {
            for (int y = -8; y <= 7; y++) {
                for (int z = -8; z <= 7; z++) {

                    mutable.set(pos.getX() + x, pos.getY() + y, pos.getZ() + z);

                    // Skip the current Warping Stone
                    if (mutable.equals(pos))
                        continue;

                    if (!world.getBlockState(mutable).isOf(ModBlocks.WARPING_STONE))
                        continue;

                    // Check that there is room for the player
                    BlockPos feet = mutable.up();
                    BlockPos head = mutable.up(2);

                    if (!world.getBlockState(feet).getCollisionShape(world, feet).isEmpty())
                        continue;

                    if (!world.getBlockState(head).getCollisionShape(world, head).isEmpty())
                        continue;

                    warpingStones.add(mutable.toImmutable());
                }
            }
        }
        return warpingStones;
    }


    private static void tick(
            World world,
            BlockPos pos,
            BlockState state,
            WarpingStoneBlockEntity entity) {

        if (entity.teleportCooldown > 0)
            entity.teleportCooldown--;
    }

}
