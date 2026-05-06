package net.daplow.mob_revamp;

import net.daplow.mob_revamp.blocks.ModBlocks;
import net.daplow.mob_revamp.commands.ModCommands;
import net.daplow.mob_revamp.effects.ModEffects;
import net.daplow.mob_revamp.effects.ModPotions;
import net.daplow.mob_revamp.items.ModItems;
import net.daplow.mob_revamp.util.ModLoot;
import net.daplow.mob_revamp.util.other.ParrotFeatherDrop;
import net.daplow.mob_revamp.worldgen.ModFeatures;
import net.daplow.mob_revamp.worldgen.ModStructures;
import net.fabricmc.api.ModInitializer;
import net.minecraft.item.Items;
import net.minecraft.potion.Potions;
import net.minecraft.recipe.BrewingRecipeRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DaplowMain implements ModInitializer {
    public static final String MOD_ID = "daplow-mob_revamp";

    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);


    // commit
    @Override
    public void onInitialize() {
        LOGGER.info("WAKE UP DAPLOW!!!");

        ModItems.registerItems();
        ModBlocks.registerBlocks();


        ParrotFeatherDrop.register();

        ModFeatures.register();

        ModStructures.register();

        ModCommands.register();

        ModLoot.register();
        ModEffects.register();
        ModPotions.register();



    }


}
