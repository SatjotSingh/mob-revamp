package net.daplow.mob_revamp.items.custom;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.DyeableItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Objects;

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

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        if (Screen.hasShiftDown()) {
            if (!stack.hasNbt()) {
                tooltip.add(Text.literal("§7- Combine with dye to "));
                tooltip.add(Text.literal("§7  get colored Wing Suit!"));

                return;
            }

            NbtCompound nbt = stack.getNbt();
            if (nbt != null && nbt.contains("display", NbtElement.COMPOUND_TYPE)) {
                NbtCompound display = nbt.getCompound("display");
                if (display.contains("color", NbtElement.INT_TYPE)) {
                    tooltip.add(Text.literal("§7- Combine with the Elytra"));
                    tooltip.add(Text.literal("§7  to get Colored Elytra!"));
            } else {
                    tooltip.add(Text.literal("§7- Combine with dye to "));
                    tooltip.add(Text.literal("§7  get colored Wing Suit!"));
                }
            }
        } else {
            tooltip.add(Text.literal("§7Hold §eSHIFT§7 for more info"));
        }

        super.appendTooltip(stack, world, tooltip, context);
    }
}
