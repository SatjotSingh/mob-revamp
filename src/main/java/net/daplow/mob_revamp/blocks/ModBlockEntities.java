package net.daplow.mob_revamp.blocks;

import net.daplow.mob_revamp.blocks.custom.WarpingStoneBlockEntity;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import static net.daplow.mob_revamp.DaplowMain.MOD_ID;

public class ModBlockEntities {

    public static final BlockEntityType<WarpingStoneBlockEntity> WARPING_STONE =
            Registry.register(
                    Registry.BLOCK_ENTITY_TYPE,
                    new Identifier(MOD_ID, "warping_stone"),
                    FabricBlockEntityTypeBuilder.create(
                            WarpingStoneBlockEntity::new,
                            ModBlocks.WARPING_STONE
                    ).build()
            );

    public static void register() {}



}
