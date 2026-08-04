package net.daplow.mob_revamp.items.custom;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class GuardianEyeItem extends Item {


    public GuardianEyeItem(Settings settings) {
        super(settings);
    }

    @Override
    public ItemStack getDefaultStack() {
        ItemStack stack = new ItemStack(this);

        NbtCompound nbt = stack.getOrCreateNbt();
        nbt.putString("Eye_Variant", "blue");


        return stack;
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        super.inventoryTick(stack, world, entity, slot, selected);

        if (!stack.hasNbt()) {
            stack.getOrCreateNbt().putString("Eye_Variant", "Deep Blue Guardian Eye");
        }
        super.inventoryTick(stack, world, entity, slot, selected);

    }

    @Override
    public void appendStacks(ItemGroup group, DefaultedList<ItemStack> stacks) {
        if (this.isIn(group)) {

            stacks.add(createStaff("blue", "Deep Blue Guardian Eye", Formatting.BLUE));
            stacks.add(createStaff("red", "Strong Red Guardian Eye", Formatting.RED));
            stacks.add(createStaff("green", "Healthy Green Guardian Eye", Formatting.GREEN));
            stacks.add(createStaff("purple", "Ranged Purple Guardian Eye", Formatting.DARK_PURPLE));
            stacks.add(createStaff("yellow", "Speedy Yellow Guardian Eye", Formatting.YELLOW));
            stacks.add(createStaff("orange", "Fast Orange Guardian Eye", Formatting.GOLD));
        }
    }


    private ItemStack createStaff(String variant, String name, Formatting color) {
        ItemStack stack = new ItemStack(this);
        NbtCompound nbt = stack.getOrCreateNbt();
        nbt.putString("Eye_Variant", variant);
        stack.setCustomName(Text.literal(name)
                .styled(style -> style.withItalic(false))
                .formatted(color)
        );

        return stack;
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        if (Screen.hasShiftDown()) {
            tooltip.add(Text.literal("§8- Can be used to craft"));
            tooltip.add(Text.literal("§8  the Guardian Staff"));

        } else {
            tooltip.add(Text.literal("§7Hold §eSHIFT§7 for more info"));
        }

        super.appendTooltip(stack, world, tooltip, context);
    }

}
