package net.daplow.mob_revamp.blocks.custom;

import net.daplow.mob_revamp.blocks.ModBlockEntities;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.math.BlockPos;

public class WarpingStoneBlockEntity extends BlockEntity {

    public int teleportCooldown = 20;

    public WarpingStoneBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.WARPING_STONE, pos, state);
    }

}
