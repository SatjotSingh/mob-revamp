package net.daplow.mob_revamp.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.LeavesBlock;
import net.minecraft.block.Material;
import net.minecraft.block.AbstractBlock.Settings;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import static net.daplow.mob_revamp.items.ModItems.DAPLOW_MOB_REVAMP;

public class ModBlocks {

    public static final Block FLOWERED_JUNGLE_LEAF = new LeavesBlock(
            Settings.of(Material.LEAVES)
                    .strength(0.2f)
                    .ticksRandomly()
                    .sounds(BlockSoundGroup.GRASS)
                    .nonOpaque()
    );

    public static void registerBlocks() {
        Registry.register(Registry.BLOCK, new Identifier("daplow-mob_revamp", "flowered_jungle_leaf"), FLOWERED_JUNGLE_LEAF);

        Registry.register(Registry.ITEM, new Identifier("daplow-mob_revamp", "flowered_jungle_leaf"),
                new BlockItem(FLOWERED_JUNGLE_LEAF, new Item.Settings().group(ItemGroup.DECORATIONS).group(DAPLOW_MOB_REVAMP)));
    }

}
