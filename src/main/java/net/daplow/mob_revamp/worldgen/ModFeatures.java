package net.daplow.mob_revamp.worldgen;


import net.daplow.mob_revamp.worldgen.features.BigFlowerTreeFeature;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.Heightmap;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.placementmodifier.CountPlacementModifier;
import net.minecraft.world.gen.placementmodifier.HeightmapPlacementModifier;
import net.minecraft.world.gen.placementmodifier.SquarePlacementModifier;

import java.util.List;

public class ModFeatures {

    // 1️⃣ Raw Feature
    public static final Feature<DefaultFeatureConfig> BIG_FLOWER_FEATURE =
            new BigFlowerTreeFeature(DefaultFeatureConfig.CODEC);

    // 2️⃣ Configured Feature
    public static final ConfiguredFeature<DefaultFeatureConfig, ?> BIG_FLOWER_CONFIGURED =
            new ConfiguredFeature<>(BIG_FLOWER_FEATURE, DefaultFeatureConfig.DEFAULT);

    // 3️⃣ Placed Feature
    public static final PlacedFeature BIG_FLOWER_PLACED = new PlacedFeature(
            RegistryEntry.of(BIG_FLOWER_CONFIGURED),
            List.of(
                    CountPlacementModifier.of(20), // High count for testing
                    SquarePlacementModifier.of(),
                    HeightmapPlacementModifier.of(Heightmap.Type.WORLD_SURFACE)
            )
    );

    // 4️⃣ RegistryKeys
    public static final RegistryKey<ConfiguredFeature<?, ?>> BIG_FLOWER_CONFIGURED_KEY =
            RegistryKey.of(Registry.CONFIGURED_FEATURE_KEY,
                    new Identifier("daplow-mob_revamp", "big_flower_configured"));

    public static final RegistryKey<PlacedFeature> BIG_FLOWER_PLACED_KEY =
            RegistryKey.of(Registry.PLACED_FEATURE_KEY,
                    new Identifier("daplow-mob_revamp", "big_flower_placed"));

    public static void register() {
        // ✅ Register the raw feature
        Registry.register(Registry.FEATURE,
                new Identifier("daplow-mob_revamp", "big_flower"),
                BIG_FLOWER_FEATURE);

        // ✅ Register ConfiguredFeature
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE,
                BIG_FLOWER_CONFIGURED_KEY.getValue(),
                BIG_FLOWER_CONFIGURED);

        // ✅ Register PlacedFeature
        Registry.register(BuiltinRegistries.PLACED_FEATURE,
                BIG_FLOWER_PLACED_KEY.getValue(),
                BIG_FLOWER_PLACED);

        // ✅ Inject into all overworld biomes for testing
        BiomeModifications.addFeature(
                BiomeSelectors.foundInOverworld(), // temporarily spawn everywhere
                GenerationStep.Feature.VEGETAL_DECORATION,
                BIG_FLOWER_PLACED_KEY
        );

        System.out.println("[ModStructures] BigFlowerTreeFeature registered!");
    }

}