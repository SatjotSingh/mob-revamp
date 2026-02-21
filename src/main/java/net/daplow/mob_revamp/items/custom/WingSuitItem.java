package net.daplow.mob_revamp.items.custom;

import net.minecraft.item.DyeableItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class WingSuitItem extends Item implements DyeableItem {

    private static final int DEFAULT_BAT_BROWN = 0x3A2A1B;

    public WingSuitItem(Settings settings) {
        super(settings);
    }

    @Override
    public int getColor(ItemStack stack) {
        if (stack.hasNbt() && stack.getNbt().contains("display") && stack.getNbt().getCompound("display").contains("color")) {
            return stack.getNbt().getCompound("display").getInt("color");
        }

        return DEFAULT_BAT_BROWN;
    }
}
