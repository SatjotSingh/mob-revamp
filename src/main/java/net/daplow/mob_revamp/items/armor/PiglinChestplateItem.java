package net.daplow.mob_revamp.items.armor;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class PiglinChestplateItem extends ArmorItem {
    public PiglinChestplateItem(ArmorMaterial material, Settings settings) {
        super(material, EquipmentSlot.CHEST, settings);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        if (Screen.hasShiftDown()) {
            tooltip.add(Text.literal("§7- Equip to be peaceful"));
            tooltip.add(Text.literal("§7  with Piglin Brutes"));

        } else {
            tooltip.add(Text.literal("§7Hold §eSHIFT§7 for more info"));
        }

        super.appendTooltip(stack, world, tooltip, context);
    }
}
