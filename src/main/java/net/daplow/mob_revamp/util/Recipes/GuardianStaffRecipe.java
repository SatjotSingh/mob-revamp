package net.daplow.mob_revamp.util.Recipes;

import net.daplow.mob_revamp.items.ModItems;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.SpecialCraftingRecipe;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

public class GuardianStaffRecipe extends SpecialCraftingRecipe {

    public GuardianStaffRecipe(Identifier id) {
        super(id);
    }


    @Override
    public boolean matches(CraftingInventory inventory, World world) {
        if (inventory.getWidth() < 3 || inventory.getHeight() < 3) return false;

        return inventory.getStack(2).getItem() == ModItems.GUARDIAN_EYE &&
                inventory.getStack(4).getItem() == Items.STICK &&
                inventory.getStack(6).getItem() == Items.STICK &&
                inventory.getStack(0).isEmpty() &&
                inventory.getStack(1).getItem() == Items.DIAMOND &&
                inventory.getStack(3).isEmpty() &&
                inventory.getStack(5).getItem() == Items.DIAMOND &&
                inventory.getStack(7).isEmpty() &&
                inventory.getStack(8).isEmpty();
    }

    @Override
    public ItemStack craft(CraftingInventory inventory) {
        ItemStack result = this.getOutput().copy();

        for (int i = 0; i < inventory.size(); i++) {
            ItemStack stack = inventory.getStack(i);

            if (stack.getItem() == ModItems.GUARDIAN_EYE) {

                if (stack.hasNbt()) {
                    result.setNbt(stack.getNbt().copy());
                }

                break;
            }
        }

        return result;
    }

    @Override
    public boolean fits(int width, int height) {
        return width >= 3 && height >= 3;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return ModItems.GUARDIAN_STAFF_RECIPE;
    }


    @Override
    public ItemStack getOutput() {
        return new ItemStack(ModItems.GUARDIAN_STAFF);
    }

}


