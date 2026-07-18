package net.daplow.mob_revamp.util.Screen;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.slot.Slot;

public class LockedSlot extends Slot {

    private final ItemStack lockedStack;
    public LockedSlot(Inventory inventory, int index, int x, int y, ItemStack lockedStack) {
        super(inventory, index, x, y);
        this.lockedStack = lockedStack;
    }

    @Override
    public boolean canTakeItems(PlayerEntity playerEntity) {
        return getStack() != lockedStack;
    }

    @Override
    public boolean canInsert(ItemStack stack) {
        return getStack() != lockedStack;
    }
}
