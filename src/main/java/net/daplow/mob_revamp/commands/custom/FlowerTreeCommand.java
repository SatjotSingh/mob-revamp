package net.daplow.mob_revamp.commands.custom;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.daplow.mob_revamp.worldgen.ModFeatures;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.util.FeatureContext;

import java.util.Optional;

public class FlowerTreeCommand {

    public static void register() {
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
            dispatcher.register(
                    LiteralArgumentBuilder.<ServerCommandSource>literal("daplow") // top-level command
                            .then(
                                    LiteralArgumentBuilder.<ServerCommandSource>literal("spawn") // subcommand
                                            .then(
                                                    LiteralArgumentBuilder.<ServerCommandSource>literal("flower_jungle") // final action
                                                            .executes(context -> {
                                                                ServerCommandSource source = context.getSource();
                                                                ServerWorld world = source.getWorld();
                                                                BlockPos pos = source.getPlayer().getBlockPos();
                                                                ChunkGenerator chunkGenerator = world.getChunkManager().getChunkGenerator();
                                                                DefaultFeatureConfig config = DefaultFeatureConfig.DEFAULT;

                                                                // Use Mojang Random
                                                                Random random = Random.create();

                                                                // Optional ConfiguredFeature (empty since we use raw Feature)
                                                                Optional<ConfiguredFeature<?, ?>> featureOpt = Optional.empty();

                                                                // FeatureContext
                                                                FeatureContext<DefaultFeatureConfig> featureContext =
                                                                        new FeatureContext<>(
                                                                                featureOpt,
                                                                                (StructureWorldAccess) world,
                                                                                chunkGenerator,
                                                                                random,
                                                                                pos,
                                                                                config
                                                                        );

                                                                // Generate the tree
                                                                ModFeatures.BIG_FLOWER_FEATURE.generate(featureContext);

                                                                // Feedback to player
                                                                source.sendFeedback(Text.literal("Spawned Flower Jungle Structure!"), false);
                                                                return 1;
                                                            })
                                            )
                            )
            );
        });
    }
}
