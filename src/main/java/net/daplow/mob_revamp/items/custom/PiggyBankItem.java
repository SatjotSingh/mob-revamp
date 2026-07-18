package net.daplow.mob_revamp.items.custom;

import net.daplow.mob_revamp.util.Screen.PiggyScreenHandler;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerFactory;
import net.minecraft.screen.SimpleNamedScreenHandlerFactory;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.World;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class PiggyBankItem extends Item {
    public PiggyBankItem(Settings settings) {
        super(settings);
    }


    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);

        if (!world.isClient) {

            ServerPlayerEntity serverPlayer = (ServerPlayerEntity) user;

            serverPlayer.openHandledScreen(new ExtendedScreenHandlerFactory() {


                @Override
                public void writeScreenOpeningData(ServerPlayerEntity player, PacketByteBuf buf) {
                    buf.writeEnumConstant(hand);
                }

                @Override
                public Text getDisplayName() {
                    return stack.getName();
                }

                @Override
                public PiggyScreenHandler createMenu(int syncId, PlayerInventory inv, PlayerEntity playerEntity) {

                    PiggyScreenHandler handler = new PiggyScreenHandler(syncId, inv, hand);

                    return handler;

                }
            });


        }

        return TypedActionResult.consume(stack);
    }




    @Override
    public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext context) {
        super.appendTooltip(stack, world, tooltip, context);

        NbtCompound nbt = stack.getNbt();
        tooltip.add(
                Text.literal("Items:")
                        .formatted(Formatting.WHITE, Formatting.BOLD, Formatting.UNDERLINE)
        );
        if (nbt != null && nbt.contains("Inventory")) {

            DefaultedList<ItemStack> items = DefaultedList.ofSize(7, ItemStack.EMPTY);

            Inventories.readNbt(nbt.getCompound("Inventory"), items);

            Map<Item, Integer> itemCounts = new LinkedHashMap<>();
            Map<Item, ItemStack> itemStacks = new LinkedHashMap<>();

            // Combine duplicate items
            for (ItemStack itemStack : items) {
                if (!itemStack.isEmpty()) {

                    Item item = itemStack.getItem();

                    itemCounts.merge(
                            item,
                            itemStack.getCount(),
                            Integer::sum
                    );

                    // Keep one ItemStack reference for rarity/name
                    itemStacks.putIfAbsent(item, itemStack);
                }
            }

            if (!itemCounts.isEmpty()) {


                for (Map.Entry<Item, Integer> entry : itemCounts.entrySet()) {

                    ItemStack itemStack = itemStacks.get(entry.getKey());

                    int count = entry.getValue();

                    // Align the counts (1x, 64x, etc.)
                    String countText = String.format("%2dx ", count);

                    MutableText line = Text.literal(countText)
                            .formatted(Formatting.WHITE);

                    MutableText name = itemStack.getName().copy();

                    // If the name has no color, use the item's rarity color
                    if (name.getStyle().getColor() == null) {
                        name.setStyle(
                                name.getStyle().withFormatting(itemStack.getRarity().formatting)
                        );
                    }

                    // Renamed in an anvil -> italic like vanilla
                    if (itemStack.hasCustomName()) {
                        name.setStyle(
                                name.getStyle().withItalic(true)
                        );
                    }

                    line.append(name);

                    tooltip.add(line);
                }
            } else {
                tooltip.add(Text.literal("Empty").formatted(Formatting.DARK_GRAY));

            }

        } else {
            tooltip.add(Text.literal("Empty").formatted(Formatting.DARK_GRAY));
        }

    }

}
