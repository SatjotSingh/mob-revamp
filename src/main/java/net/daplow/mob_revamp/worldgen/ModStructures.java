package net.daplow.mob_revamp.worldgen;

import com.mojang.serialization.Codec;
import net.daplow.mob_revamp.worldgen.structures.PiglinMinePiece;
import net.daplow.mob_revamp.worldgen.structures.PiglinMineProcessor;
import net.daplow.mob_revamp.worldgen.structures.PiglinMineStructure;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.structure.StructurePieceType;
import net.minecraft.structure.StructureSet;
import net.minecraft.structure.StructureSets;
import net.minecraft.structure.pool.SinglePoolElement;
import net.minecraft.structure.pool.StructurePool;
import net.minecraft.structure.pool.StructurePoolElement;
import net.minecraft.structure.processor.StructureProcessorType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.gen.chunk.placement.StructurePlacementType;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.structure.Structure;
import net.minecraft.world.gen.structure.StructureType;

import java.util.List;

import static net.daplow.mob_revamp.DaplowMain.MOD_ID;


public class ModStructures {


    public static final StructureProcessorType<PiglinMineProcessor> SIMPLE_STONE = StructureProcessorType.register("daplow-mob_revamp:piglin_mine_processor", PiglinMineProcessor.CODEC);


    public static StructurePieceType PIGLIN_MINE_PIECE;

    public static StructureType<PiglinMineStructure> PIGLIN_MINE;


    public static void register() {

        PIGLIN_MINE = Registry.register(Registry.STRUCTURE_TYPE, new Identifier(MOD_ID, "piglin_mine"), () -> PiglinMineStructure.CODEC);

        PIGLIN_MINE_PIECE = Registry.register(
                Registry.STRUCTURE_PIECE,
                new Identifier(MOD_ID, "piglin_mine_piece"),
                PiglinMinePiece::new
        );

    }
}