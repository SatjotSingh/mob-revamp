
package net.daplow.mob_revamp.util.Recipes;

import net.daplow.mob_revamp.items.ModItems;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.SpecialCraftingRecipe;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

public class WingSuitElytraRecipe extends SpecialCraftingRecipe {

    public WingSuitElytraRecipe(Identifier id) {
        super(id);
    }

    @Override
    public ItemStack craft(CraftingInventory inv) {
        ItemStack wingSuitStack = ItemStack.EMPTY;

        for (int i = 0; i < inv.size(); i++) {
            ItemStack stack = inv.getStack(i);
            if (stack.getItem() == ModItems.WING_SUIT) {
                wingSuitStack = stack;
                break;
            }
        }

        ItemStack result = new ItemStack(Items.ELYTRA);

        if (wingSuitStack.hasNbt() && wingSuitStack.getNbt().contains("display")) {
            result.getOrCreateNbt().put("display", wingSuitStack.getNbt().getCompound("display").copy());
        }
        result.getOrCreateNbt().putBoolean("BatWing", true);

        return result;
    }


    @Override
    public boolean matches(CraftingInventory inventory, World world) {
        boolean hasWingSuit = false;
        boolean hasElytra = false;
        for (int i = 0; i < inventory.size(); i++) {
            ItemStack stack = inventory.getStack(i);
            if (stack.isEmpty()) continue;
            if (stack.getItem() == ModItems.WING_SUIT) hasWingSuit = true;
            if (stack.getItem() == Items.ELYTRA) hasElytra = true;
        }
        return hasWingSuit && hasElytra;
    }

    @Override
    public boolean fits(int width, int height) {
        return width * height >= 2;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return ModItems.WING_SUITE_ELYTRA_RECIPE;
    }

}
