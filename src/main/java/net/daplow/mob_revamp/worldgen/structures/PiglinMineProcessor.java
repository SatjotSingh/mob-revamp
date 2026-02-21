package net.daplow.mob_revamp.worldgen.structures;


import net.daplow.mob_revamp.worldgen.ModStructures;
import net.minecraft.block.*;
import net.minecraft.block.enums.StructureBlockMode;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.mob.PiglinBruteEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.structure.processor.StructureProcessor;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.block.enums.BlockHalf;
import net.minecraft.structure.StructurePlacementData;
import net.minecraft.structure.StructureTemplate;
import net.minecraft.structure.processor.StructureProcessor;
import net.minecraft.structure.processor.StructureProcessorType;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.WorldView;
import org.jetbrains.annotations.Nullable;

public class PiglinMineProcessor extends StructureProcessor {
    // empty class; we just need an instance to satisfy the constructor


    private boolean entitiesSpawned = false; // ensures we spawn only once per structure

    public static final Codec<PiglinMineProcessor> CODEC = RecordCodecBuilder.create((proc) -> proc.group(
            Codec.FLOAT.fieldOf("mossiness").forGetter(processor -> processor.mossiness)
    ).apply(proc, PiglinMineProcessor::new));
    private final float mossiness;

    public PiglinMineProcessor(float mossiness) {
        this.mossiness = mossiness;
    }

    private static BlockState randomStairProperties(Random random, Block stairs) {
        return stairs.getDefaultState().with(StairsBlock.FACING, Direction.Type.HORIZONTAL.random(random)).with(StairsBlock.HALF, BlockHalf.values()[random.nextInt(BlockHalf.values().length)]);
    }

    private static BlockState randomState(Random random, BlockState[] states) {
        return states[random.nextInt(states.length)];
    }

    @Nullable
    @Override
    public StructureTemplate.StructureBlockInfo process(
            WorldView worldView,
            BlockPos pos,
            BlockPos blockPos,
            StructureTemplate.StructureBlockInfo structureBlockInfo,
            StructureTemplate.StructureBlockInfo structureBlockInfo2,
            StructurePlacementData structurePlacementData
    ) {
        Random random = structurePlacementData.getRandom(structureBlockInfo2.pos);
        BlockState blockState = structureBlockInfo2.state;
        BlockPos blockPos2 = structureBlockInfo2.pos;
        BlockState blockState2 = null;

        if (structureBlockInfo.state.isOf(Blocks.DIAMOND_ORE)) {
            System.out.println("Structure got Orange Wool");
            // Create a "marker block" that will hold entity data

            BlockState markerBlock = Blocks.STRUCTURE_BLOCK.getDefaultState()
                    .with(StructureBlock.MODE, StructureBlockMode.DATA);

            NbtCompound nbt = new NbtCompound();

            // Entity spawn data
            NbtCompound entityTag = new NbtCompound();
            entityTag.putString("id", "minecraft:piglin");
            entityTag.putBoolean("PersistenceRequired", true); // Prevent despawn

            entityTag.putInt("Health", 16); // Optional: set NBT data

            nbt.put("Entity", entityTag);
            // Set the NBT in the block state (you might need to use BlockEntity)
            // Vanilla often uses a structure_block in DATA mode with the "EntityTag" NBT

            return new StructureTemplate.StructureBlockInfo(
                    structureBlockInfo2.pos,   // same relative position
                    markerBlock,   // new block state
                    nbt            // NBT data to hold entity info
            ); // Replace orange wool with marker
        }

        // Replace the marker block with air


        // Modify GOLD_BLOCKS randomly
        if (blockState.isOf(Blocks.GOLD_BLOCK)) {
            //System.out.println("Structure got Gold Block");

            blockState2 = processBlocks(random);
        }

        return blockState2 != null
                ? new StructureTemplate.StructureBlockInfo(blockPos2, blockState2, structureBlockInfo2.nbt)
                : structureBlockInfo2;
    }

    @Nullable
    private BlockState processBlocks(Random random) {
        if (random.nextFloat() >= 0.5F) {
            return null;
        } else {
            BlockState[] blockStates = new BlockState[]{Blocks.GOLD_BLOCK.getDefaultState(), Blocks.RAW_GOLD_BLOCK.getDefaultState()};
            BlockState[] blockStates2 = new BlockState[]{Blocks.GOLD_BLOCK.getDefaultState(), Blocks.RAW_GOLD_BLOCK.getDefaultState()};
            return this.process(random, blockStates, blockStates2);
        }
    }

    private BlockState process(Random random, BlockState[] regularStates, BlockState[] mossyStates) {
        return random.nextFloat() < this.mossiness ? randomState(random, mossyStates) : randomState(random, regularStates);
    }

    protected StructureProcessorType<?> getType() {
        return ModStructures.SIMPLE_STONE;
    }

}