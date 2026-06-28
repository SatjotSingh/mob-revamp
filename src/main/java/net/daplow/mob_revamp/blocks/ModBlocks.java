package net.daplow.mob_revamp.blocks;

import net.daplow.mob_revamp.blocks.custom.SculkMineBlock;
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

import static net.daplow.mob_revamp.DaplowMain.MOD_ID;
import static net.daplow.mob_revamp.items.ModItems.DAPLOW_MOB_REVAMP;

public class ModBlocks {
// TEsting GIT
    public static final Block FLOWERED_JUNGLE_LEAF = new LeavesBlock(
            Settings.of(Material.LEAVES)
                    .strength(0.2f)
                    .ticksRandomly()
                    .sounds(BlockSoundGroup.GRASS)
                    .nonOpaque()
    );

    public static final Block WITHER_BONE_BLOCK = new WitherBoneBlock(
            AbstractBlock.Settings.copy(Blocks.BONE_BLOCK)
                    .requiresTool() // Cannot be broken by hand
    );

    public static final Block SCULK_MINE = new SculkMineBlock(AbstractBlock.Settings.copy(Blocks.SCULK).requiresTool());


    public static void registerBlocks() {

        AddBlock("flowered_jungle_leaf", FLOWERED_JUNGLE_LEAF, new BlockItem(FLOWERED_JUNGLE_LEAF, new Item.Settings().group(ItemGroup.DECORATIONS).group(DAPLOW_MOB_REVAMP)));

        AddBlock("wither_bone_block", WITHER_BONE_BLOCK, new BlockItem(WITHER_BONE_BLOCK, new Item.Settings().group(DAPLOW_MOB_REVAMP)));

        AddBlock("sculk_mine", SCULK_MINE, new BlockItem(SCULK_MINE, new Item.Settings().group(DAPLOW_MOB_REVAMP)));
        //Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "flowered_jungle_leaf"), FLOWERED_JUNGLE_LEAF);

        //Registry.register(Registry.ITEM, new Identifier(MOD_ID, "flowered_jungle_leaf"),
       //         new BlockItem(FLOWERED_JUNGLE_LEAF, new Item.Settings().group(ItemGroup.DECORATIONS).group(DAPLOW_MOB_REVAMP)));

        //Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "wither_bone_block"), WITHER_BONE_BLOCK);

        //Registry.register(Registry.ITEM, new Identifier(MOD_ID, "wither_bone_block"),
        //        new BlockItem(WITHER_BONE_BLOCK, new Item.Settings().group(DAPLOW_MOB_REVAMP)));

//        Registry.register((Registry.BLOCK, new Identifier(MOD_ID, "sculk_trap"), Block));

    }


    private static void AddBlock(String name, Block blockType, BlockItem blockItemType) {
        Registry.register(Registry.BLOCK, new Identifier(MOD_ID, name), blockType);
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, name), blockItemType);
    }
}
