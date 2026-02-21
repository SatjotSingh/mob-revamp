package net.daplow.mob_revamp;

import net.daplow.mob_revamp.blocks.ModBlocks;
import net.daplow.mob_revamp.commands.ModCommands;
import net.daplow.mob_revamp.effects.ModEffects;
import net.daplow.mob_revamp.effects.ModPotions;
import net.daplow.mob_revamp.items.ModItems;
import net.daplow.mob_revamp.util.ModLoot;
import net.daplow.mob_revamp.other.ParrotFeatherDrop;
import net.daplow.mob_revamp.worldgen.ModFeatures;
import net.daplow.mob_revamp.worldgen.ModStructures;
import net.fabricmc.api.ModInitializer;
import net.minecraft.potion.Potions;
import net.minecraft.recipe.BrewingRecipeRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.ref.Reference;

public class DaplowMain implements ModInitializer {
    public static final String MOD_ID = "daplow-mob_revamp";

    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);


    // commit
    @Override
    public void onInitialize() {
        LOGGER.info("WAKE UP DAPLOW!!!");

        ModItems.registerItems();
        ModBlocks.registerBlocks();

        //ModStructures.register();
        //ModConfiguredFeatures.registerConfiguredFeatures();

        //ModStructures.injectIntoBiomes();
        //ModBiomes.register();

        ParrotFeatherDrop.register();

        ModFeatures.register();
        //ModPlacedFeatures.register();


        ModStructures.register();
        //ModStructurePieces.register();

        ModCommands.register();

        ModLoot.registerLoot();
        ModEffects.register();
        ModPotions.register();

        BrewingRecipeRegistry.registerPotionRecipe(
                Potions.AWKWARD,
                ModItems.BAT_WING,
                ModPotions.BAT_POTION
        );

        BrewingRecipeRegistry.registerPotionRecipe(
                Potions.AWKWARD,
                ModItems.SQUID_PEARL,
                ModPotions.SQUID_POTION
        );


    }


}
