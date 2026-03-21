package net.daplow.mob_revamp.items;

import net.daplow.mob_revamp.items.armor.GuardianArmorMaterial;
import net.daplow.mob_revamp.items.armor.GuardianLeggingsItem;
import net.daplow.mob_revamp.items.armor.PiglinArmorMaterial;
import net.daplow.mob_revamp.items.armor.PiglinChestplateItem;
import net.daplow.mob_revamp.items.custom.*;
import net.daplow.mob_revamp.util.Recipes.GuardianStaffRecipe;
import net.daplow.mob_revamp.util.Recipes.WingSuitElytraRecipe;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.*;
import net.minecraft.recipe.SpecialRecipeSerializer;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import static net.daplow.mob_revamp.DaplowMain.MOD_ID;

public class ModItems {

    public static final ItemGroup DAPLOW_MOB_REVAMP = FabricItemGroupBuilder.create(
                    new Identifier("daplow-mob_revamp", "daplow_mob_revamp_tab"))
            .icon(() -> new ItemStack(ModItems.SQUID_PEARL)) // icon for the tab
            .build();

    // Bat Mod
    public static final Item BAT_WING = new BatWingItem(new FabricItemSettings().group(DAPLOW_MOB_REVAMP));
    public static final Item WING_SUIT = Registry.register(
            Registry.ITEM,
            new Identifier(MOD_ID, "wing_suit"),
            new WingSuitItem(new Item.Settings().maxCount(1).group(DAPLOW_MOB_REVAMP))
    );
    public static final SpecialRecipeSerializer<WingSuitElytraRecipe> WING_SUITE_ELYTRA_RECIPE =
            Registry.register(
                    Registry.RECIPE_SERIALIZER,
                    new Identifier(MOD_ID, "wing_suit_elytra"),
                    new SpecialRecipeSerializer<>(WingSuitElytraRecipe::new)
            );

    // Parrot Mod
    public static final Item PARROT_FEATHER = new Item(new FabricItemSettings().group(DAPLOW_MOB_REVAMP));
    public static final Item BAMBOO_FLUTE = new BambooFluteItem(new Item.Settings().maxCount(1).group(DAPLOW_MOB_REVAMP));

    // Squid Mod
    public static final Item SQUID_PEARL = new Item(new Item.Settings().group(DAPLOW_MOB_REVAMP));


    // Piglin Brute Mod
    public static final ArmorMaterial PIGLIN_ARMOR = new PiglinArmorMaterial();

    public static final Item PIGLIN_CHESTPLATE = Registry.register(
            Registry.ITEM,
            new Identifier(MOD_ID, "piglin_chestplate"),
            new PiglinChestplateItem(
                    PIGLIN_ARMOR,
                    new FabricItemSettings().maxCount(1).group(DAPLOW_MOB_REVAMP)
            )
    );


    // Guardian Mod
    public static final Item GUARDIAN_EYE = new GuardianEyeItem(new FabricItemSettings().group(DAPLOW_MOB_REVAMP).maxCount(1));
    public static final Item GUARDIAN_STAFF = new GuardianStaffItem(new FabricItemSettings().group(DAPLOW_MOB_REVAMP).maxCount(1).maxDamage(124));
    public static final SpecialRecipeSerializer<GuardianStaffRecipe> GUARDIAN_STAFF_RECIPE =
            Registry.register(
                    Registry.RECIPE_SERIALIZER,
                    new Identifier(MOD_ID, "guardian_staff_recipe"),
                    new SpecialRecipeSerializer<>(GuardianStaffRecipe::new)
            );


    // A Guardian Chestplate that gives thorns
    public static final ArmorMaterial GUARDIAN_ARMOR = new GuardianArmorMaterial();

    public static final Item GUARDIAN_LEGGINGS = Registry.register(
            Registry.ITEM,
            new Identifier(MOD_ID, "guardian_leggings"),
            new GuardianLeggingsItem(
                    GUARDIAN_ARMOR,
                    new FabricItemSettings().maxCount(1).group(DAPLOW_MOB_REVAMP)
            )
    );
    public static final Item PRISMARINE_INGOT = new Item(new Item.Settings().group(DAPLOW_MOB_REVAMP));



    public static void registerItems() {
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "bat_wing"), BAT_WING);

        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "parrot_feather"), PARROT_FEATHER);
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "bamboo_flute"), BAMBOO_FLUTE);

        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "squid_pearl"), SQUID_PEARL);

        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "guardian_eye"), GUARDIAN_EYE);
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "guardian_staff"), GUARDIAN_STAFF);
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "prismarine_ingot"), PRISMARINE_INGOT);




    }




}