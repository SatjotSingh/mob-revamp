package net.daplow.mob_revamp.effects;

import net.daplow.mob_revamp.items.ModItems;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.Items;
import net.minecraft.potion.Potion;
import net.minecraft.potion.Potions;
import net.minecraft.recipe.BrewingRecipeRegistry;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import static net.daplow.mob_revamp.DaplowMain.MOD_ID;

public class ModPotions {

    public static final Potion BAT_POTION =
            Registry.register(
                    Registry.POTION,
                    new Identifier(MOD_ID, "bat_potion"),
                    new Potion(new StatusEffectInstance(
                            ModEffects.BAT_POTION,
                            20 * 30
                    ))
            );

    public static final Potion LONG_BAT_POTION =
            Registry.register(
                    Registry.POTION,
                    new Identifier(MOD_ID, "long_bat_potion"),
                    new Potion(new StatusEffectInstance(
                            ModEffects.BAT_POTION,
                            20 * 60
                    ))
            );


    public static final Potion SQUID_POTION =
            Registry.register(
                    Registry.POTION,
                    new Identifier(MOD_ID, "squid_potion"),
                    new Potion(new StatusEffectInstance(
                            ModEffects.SQUID_POTION,
                            20 * 10,
                            0,
                            false,
                            false,
                            true
                    ))
            );

    public static final Potion GLOW_SQUID_POTION =
            Registry.register(
                    Registry.POTION,
                    new Identifier(MOD_ID, "glow_squid_potion"),
                    new Potion(new StatusEffectInstance(
                            ModEffects.SQUID_POTION,
                            20 * 10,
                            1,
                            false,
                            false,
                            true
                    ))
            );

    public static final Potion RABBIT_POTION =
            Registry.register(
                    Registry.POTION,
                    new Identifier(MOD_ID, "rabbit_potion"),
                    new Potion(new StatusEffectInstance(
                            ModEffects.RABBIT_POTION,
                            20*180,
                            0,
                            false,
                            true,
                            true
                    ))
            );

    public static final Potion LONG_RABBIT_POTION =
            Registry.register(
                    Registry.POTION,
                    new Identifier(MOD_ID, "long_rabbit_potion"),
                    new Potion(new StatusEffectInstance(
                            ModEffects.RABBIT_POTION,
                            20*480,
                            0,
                            false,
                            true,
                            true
                    ))
            );


    public static void register() {

        // BAT_POTION
        BrewingRecipeRegistry.registerPotionRecipe(
                Potions.AWKWARD,
                ModItems.BAT_WING,
                ModPotions.BAT_POTION
        );

        BrewingRecipeRegistry.registerPotionRecipe(
                ModPotions.BAT_POTION,
                Items.REDSTONE,
                ModPotions.LONG_BAT_POTION
        );


        // SQUID_POTION
        BrewingRecipeRegistry.registerPotionRecipe(
                Potions.AWKWARD,
                ModItems.SQUID_PEARL,
                ModPotions.SQUID_POTION
        );

        BrewingRecipeRegistry.registerPotionRecipe(
                ModPotions.SQUID_POTION,
                Items.GLOW_INK_SAC,
                ModPotions.GLOW_SQUID_POTION
        );


        // RABBIT_POTION
        BrewingRecipeRegistry.registerPotionRecipe(
                Potions.AWKWARD,
                ModItems.LUCKY_CHARM,
                ModPotions.RABBIT_POTION
        );

        BrewingRecipeRegistry.registerPotionRecipe(
                ModPotions.RABBIT_POTION,
                Items.REDSTONE,
                ModPotions.LONG_RABBIT_POTION
        );

    }
}