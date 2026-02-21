package net.daplow.mob_revamp.blocks.custom;

import net.daplow.mob_revamp.blocks.ModBlocks;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.minecraft.client.color.block.BlockColors;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.color.world.BiomeColors;

@Environment(EnvType.CLIENT)
public class ModBlockColors {

    public static void register() {
        ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> {
            if (world != null && pos != null) {
                // Use the biome foliage color at this position
                return BiomeColors.getFoliageColor(world, pos);
            }
            return 0x00FF00; // fallback bright green
        }, ModBlocks.FLOWERED_JUNGLE_LEAF);
    }
}
