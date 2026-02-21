package net.daplow.mob_revamp.biome.custom;

import com.mojang.serialization.Codec;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;

import java.util.Random;

public class JungleLeafFeature extends Feature<DefaultFeatureConfig> {

    private static final Block FLOWERED_JUNGLE_LEAF =
            Registry.BLOCK.get(new Identifier("daplow-mob_revamp", "flowered_jungle_leaf"));

    public JungleLeafFeature(Codec<DefaultFeatureConfig> configCodec) {
        super(configCodec);
    }

    @Override
    public boolean generate(FeatureContext<DefaultFeatureConfig> context) {

        // Extract world, position, and random from context
        ServerWorldAccess world = context.getWorld();
        BlockPos pos = context.getOrigin();
        Random random = (Random) context.getRandom();

        for (int i = 0; i < 20; i++) { // spawn 20 leaves randomly
            BlockPos targetPos = pos.add(random.nextInt(10) - 5, random.nextInt(3), random.nextInt(10) - 5);
            if (world.isAir(targetPos)) {
                world.setBlockState(targetPos, FLOWERED_JUNGLE_LEAF.getDefaultState(), 3);
            }
        }

        return true;
    }
}