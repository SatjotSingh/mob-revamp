package net.daplow.mob_revamp.biome.custom;

import net.minecraft.structure.StructurePlacementData;
import net.minecraft.structure.StructureTemplate;
import net.minecraft.structure.StructureTemplateManager;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;

import java.util.Objects;
import java.util.Optional;

public class JungleStructureFeatures {

    public static final Feature<DefaultFeatureConfig> BIG_FLOWER_TREE = new Feature<DefaultFeatureConfig>(DefaultFeatureConfig.CODEC) {
        @Override
        public boolean generate(FeatureContext<DefaultFeatureConfig> context) {

            ServerWorldAccess world = context.getWorld();
            BlockPos pos = context.getOrigin();
            Random random = context.getRandom();

            // Get the structure manager from the world
            StructureTemplateManager manager = Objects.requireNonNull(world.getServer()).getStructureTemplateManager();

            // Load the NBT structure
            StructureTemplate template = manager.getTemplateOrBlank(new Identifier("daplow-mob_revamp", "small_jungle_world/big_flower_tree"));

            if (template != null) {
                // Create placement data
                StructurePlacementData placementData = new StructurePlacementData();

                // Place the structure (6 arguments)
                template.place(
                        world,          // world
                        pos,            // pivot
                        pos,  // placement settings
                        placementData,         // random
                        random,              // flags (update + notify neighbors)
                        0               // extra int (usually 0 for vanilla)
                );
                return true;
            }

            return false;
        }
    };
}