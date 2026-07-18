package net.daplow.mob_revamp.util.Screen;

import net.daplow.mob_revamp.items.custom.PiggyBankItem;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.slot.Slot;

public class PiggySlot extends Slot {
    public PiggySlot(Inventory inventory, int index, int x, int y) {
        super(inventory, index, x, y);
    }


    @Override
    public boolean canInsert(ItemStack stack) {
        return !(stack.getItem() instanceof PiggyBankItem);
    }
}
