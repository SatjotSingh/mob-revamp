package net.daplow.mob_revamp.util.Screen;

import net.daplow.mob_revamp.items.custom.PiggyBankItem;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.PotionItem;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.screen.slot.Slot;
import net.minecraft.util.Hand;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;

public class PiggyScreenHandler extends ScreenHandler {

    private final PlayerInventory playerInventory;
    private final SimpleInventory piggyInventory;

    private final ItemStack piggyStack;

    private final ItemStack openedPiggyBank;
    private final Hand hand;


    public PiggyScreenHandler(int syncId, PlayerInventory playerInventory, Hand hand) {
        super(ModScreenHandler.CUSTOM_PIGGY_SCREEN, syncId);
        this.playerInventory = playerInventory;
        this.hand = hand;
        this.piggyStack = playerInventory.player.getStackInHand(hand);
        this.openedPiggyBank = playerInventory.player.getStackInHand(hand);

        // Render Inventory
        this.piggyInventory = new SimpleInventory(7) {
            @Override
            public void markDirty() {
                super.markDirty();
                onContentChanged(this);
            }
        };

        loadInventory();

        this.addSlot(new PiggySlot(piggyInventory, 0, 8, 20));
        this.addSlot(new PiggySlot(piggyInventory, 1, 26, 20));
        this.addSlot(new PiggySlot(piggyInventory, 2, 44, 20));
        this.addSlot(new PiggySlot(piggyInventory, 3, 62, 20));
        this.addSlot(new PiggySlot(piggyInventory, 4, 80, 20));
        this.addSlot(new PiggySlot(piggyInventory, 5, 98, 20));
        this.addSlot(new PiggySlot(piggyInventory, 6, 116, 20));

        addPlayerInventory(playerInventory);




    }



    @Override
    public boolean canInsertIntoSlot(ItemStack stack, Slot slot) {
        if (stack.getItem() instanceof PiggyBankItem) {
            return false;
        }

        return super.canInsertIntoSlot(stack, slot);
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return true;
    }

    @Override
    public void close(PlayerEntity player) {
        super.close(player);
        // Give back items when closed
        /*
        for (int i = 0; i < 7; i++) { // input slots only
            ItemStack stack = piggyInventory.getStack(i);
            if (!stack.isEmpty()) {
                player.getInventory().offerOrDrop(stack);
                piggyInventory.setStack(i, ItemStack.EMPTY);
            }
        }
        //

         */
        if (!player.getWorld().isClient) {
            saveInventory();
        }    }

    @Override
    public ItemStack transferSlot(PlayerEntity player, int index) {
        ItemStack newStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);


        if (slot != null && slot.hasStack()) {
            ItemStack originalStack = slot.getStack();

            if (originalStack.getItem() instanceof PiggyBankItem) {
                return ItemStack.EMPTY;
            }

            newStack = originalStack.copy();

            // Piggy Bank slots: 0-6
            if (index < 7) {
                // Move from Piggy Bank -> Player
                if (!this.insertItem(originalStack, 7, this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else {
                // Move from Player -> Piggy Bank
                if (!this.insertItem(originalStack, 0, 7, false)) {
                    return ItemStack.EMPTY;
                }
            }

            if (originalStack.isEmpty()) {
                slot.setStack(ItemStack.EMPTY);
            } else {
                slot.markDirty();
            }
        }

        return newStack;
    }



    // --- CUSTOM FUNCTIONS --- //
    private void addPlayerInventory(PlayerInventory inventory) {
        int startX = 8;
        int startY = 51;

        int piggySlot = playerInventory.player.getInventory().selectedSlot + 36;

        // Main inventory
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 9; col++) {
                this.addSlot(new LockedSlot(inventory, col + row * 9 + 9, startX + col * 18, startY + row * 18,openedPiggyBank));
            }
        }

        // Hotbar
        for (int col = 0; col < 9; col++) {
            if (piggySlot == col) {
                this.addSlot(new LockedPiggySlot(inventory, col, startX + col * 18, startY + 58));
            } else{
            this.addSlot(new LockedSlot(inventory, col, startX + col * 18, startY + 58,openedPiggyBank));}
        }
    }


    private void loadInventory() {
        NbtCompound nbt = piggyStack.getOrCreateNbt();

        if (nbt.contains("Inventory")) {
            NbtCompound inventoryTag = nbt.getCompound("Inventory");

            DefaultedList<ItemStack> items = DefaultedList.ofSize(7, ItemStack.EMPTY);
            Inventories.readNbt(inventoryTag, items);

            for (int i = 0; i < items.size(); i++) {
                piggyInventory.setStack(i, items.get(i));
            }
        }
    }

    private void saveInventory() {
        NbtCompound nbt = piggyStack.getOrCreateNbt();

        DefaultedList<ItemStack> items = DefaultedList.ofSize(7, ItemStack.EMPTY);

        for (int i = 0; i < items.size(); i++) {
            items.set(i, piggyInventory.getStack(i));
        }

        NbtCompound inventoryTag = new NbtCompound();
        Inventories.writeNbt(inventoryTag, items);

        nbt.put("Inventory", inventoryTag);
    }

    public int getPiggySlot() {
        if (hand == Hand.MAIN_HAND) {
            return playerInventory.selectedSlot;
        }

        return -1; // offhand handled separately

    }
    public PlayerInventory getInventory() {
        return playerInventory;
    }

}
