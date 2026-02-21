package net.daplow.mob_revamp.worldgen.features;

import com.mojang.serialization.Codec;
import net.daplow.mob_revamp.blocks.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.passive.ParrotEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.structure.StructurePlacementData;
import net.minecraft.structure.StructureTemplate;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Heightmap;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;
import net.minecraft.util.math.random.Random;

import java.util.Optional;

public class BigFlowerTreeFeature extends Feature<DefaultFeatureConfig> {

    // Small trees to spawn around the big tree
    private static final String[] SURROUNDING_STRUCTURES = {
            "mid_tree",
            "small_tree",
            "shrub_tree",
            "one_flower",
            "tall_flower",
            "long_flower",
            "purple_flower",
            "blue_flower",
            "red_flower"

    };

    public static final Codec<BigFlowerTreeFeature> CODEC = Codec.unit(() -> new BigFlowerTreeFeature(DefaultFeatureConfig.CODEC));

    public BigFlowerTreeFeature(Codec<DefaultFeatureConfig> codec) {
        super(codec);
    }


    @Override
    public boolean generate(FeatureContext<DefaultFeatureConfig> context) {
        ServerWorldAccess world = context.getWorld();
        //System.out.println("Someting should happend");
        if (!(world instanceof ServerWorld serverWorld)) return false;
        System.out.println("Someting happend at " + context.getOrigin());

        BlockPos origin = context.getOrigin();
        Random random = context.getRandom();

        // ---------- Spawn Multiple Surrounding Structures Only ----------
        int surroundingCount = 20 + random.nextInt(5); // 5–8 surrounding trees/shrubs
        for (int i = 0; i < surroundingCount; i++) {
            int xOffset = random.nextInt(15) - 7;
            int zOffset = random.nextInt(15) - 7;
            BlockPos offsetPos = origin.add(xOffset, 0, zOffset);

            // Replace 50% of nearby leaves before spawning the structure
            replaceNearbyLeaves(serverWorld, offsetPos, 3, random); // radius 3 blocks

            // Pick a random surrounding structure
            String structureName = SURROUNDING_STRUCTURES[random.nextInt(SURROUNDING_STRUCTURES.length)];
            spawnStructureOnGrass(serverWorld, offsetPos, "small_jungle_world/" + structureName, random);
        }
        // ---------- Spawn 7–12 parrots ----------
        int parrotCount = 7 + random.nextInt(6); // 7–12
        for (int i = 0; i < parrotCount; i++) {
            int xOffset = random.nextInt(15) - 7;
            int zOffset = random.nextInt(15) - 7;
            BlockPos parrotPos = origin.add(xOffset, 1, zOffset);

            BlockPos topPos = serverWorld.getTopPosition(Heightmap.Type.WORLD_SURFACE, parrotPos);
            ParrotEntity parrot = new ParrotEntity(net.minecraft.entity.EntityType.PARROT, serverWorld);
            parrot.refreshPositionAndAngles(
                    topPos.getX() + 0.5,
                    topPos.getY(),
                    topPos.getZ() + 0.5,
                    random.nextFloat() * 360f,
                    0f
            );
            parrot.setVariant(random.nextInt(5)); // 0–4, random color

            serverWorld.spawnEntity(parrot);
        }
        return true;
    }


    private void replaceNearbyLeaves(ServerWorld serverWorld, BlockPos center, int radius, Random random) {
        int minY = 0; // start replacing from center height
        int maxY = radius; // replace up to radius above

        for (int x = -radius; x <= radius; x++) {
            for (int y = minY; y <= maxY; y++) {
                for (int z = -radius; z <= radius; z++) {
                    // optional: skip corners outside sphere
                    if (x*x + y*y + z*z > radius*radius) continue;

                    BlockPos checkPos = center.add(x, y, z);
                    // Only replace leaves, not logs or other blocks
                    if (serverWorld.getBlockState(checkPos).isIn(BlockTags.LEAVES)) {
                        if (random.nextInt(10) < 3) { // 50% chance
                            serverWorld.setBlockState(checkPos, ModBlocks.FLOWERED_JUNGLE_LEAF.getDefaultState(), 3);
                        }
                    }
                }
            }
        }
    }

    private void spawnStructureOnGrass(ServerWorld serverWorld, BlockPos pos, String templatePath, Random random) {
        Identifier templateId = new Identifier("daplow-mob_revamp", templatePath);
        Optional<StructureTemplate> templateOpt = serverWorld.getStructureTemplateManager().getTemplate(templateId);
        if (templateOpt.isEmpty()) return;

        StructureTemplate template = templateOpt.get();

        // Find actual grass block (ignore leaves, tall grass, fern)
        BlockPos surfacePos = serverWorld.getTopPosition(Heightmap.Type.WORLD_SURFACE, pos);
        for (int i = 0; i < 10; i++) { // check 10 blocks down max
            Block blockBelow = serverWorld.getBlockState(surfacePos.down()).getBlock();
            if (blockBelow == Blocks.GRASS_BLOCK) break;
            surfacePos = surfacePos.down();
        }
        // Abort if no grass block found
        if (serverWorld.getBlockState(surfacePos.down()).getBlock() != Blocks.GRASS_BLOCK) return;
        surfacePos = surfacePos.down();

        StructurePlacementData placementData = new StructurePlacementData()
                .setIgnoreEntities(true);

        BlockPos spawnPos = surfacePos.up(); // spawn structure above grass
        template.place(serverWorld, spawnPos, spawnPos, placementData, random, 2);
    }










/*


    public static final Feature<DefaultFeatureConfig> FEATURE_INSTANCE =
            new BigFlowerTreeFeature(DefaultFeatureConfig.CODEC);


    public static void register() {
        // Register the feature
        Registry.register(Registry.FEATURE, new Identifier("daplow-mob_revamp", "flower_jungle_feature"), FEATURE_INSTANCE);

        // Inject into the Plains biome
        BiomeModifications.addFeature(
                BiomeSelectors.includeByKey(BiomeKeys.PLAINS), // Plains biome key from BiomeKeys :contentReference[oaicite:1]{index=1}
                GenerationStep.Feature.SURFACE_STRUCTURES,
                RegistryKey.of(Registry.PLACED_FEATURE_KEY,
                        new Identifier("daplow-mob_revamp", "flower_jungle_feature_key"))
        );
    }
*/
}