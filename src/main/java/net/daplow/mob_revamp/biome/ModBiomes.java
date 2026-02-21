package net.daplow.mob_revamp.biome;


import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.Builder;
import net.minecraft.world.biome.BiomeEffects;
import net.minecraft.world.biome.GenerationSettings;
import net.minecraft.world.biome.SpawnSettings;

public class ModBiomes {

    public static final RegistryKey<Biome> SMALL_FLOWER_JUNGLE_KEY =
            RegistryKey.of(Registry.BIOME_KEY, new Identifier("daplow-mob_revamp", "small_flower_jungle"));

    public static void register() {
        Biome biome = new Biome.Builder()
                .precipitation(Biome.Precipitation.RAIN)
                .temperature(0.95f)
                .downfall(0.9f)
                .effects(new BiomeEffects.Builder()
                        .skyColor(7842047)
                        .fogColor(12638463)
                        .waterColor(4159204)
                        .waterFogColor(329011)
                        .build())
                .spawnSettings(new SpawnSettings.Builder().build())
                .generationSettings(new GenerationSettings.Builder().build())
                .build();

        BuiltinRegistries.add(BuiltinRegistries.BIOME, SMALL_FLOWER_JUNGLE_KEY.getValue(), biome);
    }


}