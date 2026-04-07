package net.daplow.mob_revamp.blocks;

import net.daplow.mob_revamp.blocks.custom.WitherBoneBlock;
import net.daplow.mob_revamp.items.ModItems;
import net.minecraft.block.*;
import net.minecraft.block.AbstractBlock.Settings;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.context.LootContext;
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

    public static final Block WITHER_BONE_BLOCK = new WitherBoneBlock(
            AbstractBlock.Settings.copy(Blocks.BONE_BLOCK)
                    .requiresTool()                                      // Cannot be broken by hand
    );

    public static void registerBlocks() {
        Registry.register(Registry.BLOCK, new Identifier("daplow-mob_revamp", "flowered_jungle_leaf"), FLOWERED_JUNGLE_LEAF);

        Registry.register(Registry.ITEM, new Identifier("daplow-mob_revamp", "flowered_jungle_leaf"),
                new BlockItem(FLOWERED_JUNGLE_LEAF, new Item.Settings().group(ItemGroup.DECORATIONS).group(DAPLOW_MOB_REVAMP)));

        Registry.register(Registry.BLOCK, new Identifier("daplow-mob_revamp", "wither_bone_block"), WITHER_BONE_BLOCK);

        Registry.register(Registry.ITEM, new Identifier("daplow-mob_revamp", "wither_bone_block"),
                new BlockItem(WITHER_BONE_BLOCK, new Item.Settings().group(DAPLOW_MOB_REVAMP)));

    }

}
