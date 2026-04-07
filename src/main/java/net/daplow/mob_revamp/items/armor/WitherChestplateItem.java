package net.daplow.mob_revamp.items.armor;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

public class WitherChestplateItem extends ArmorItem {
    public WitherChestplateItem(ArmorMaterial material, Settings settings) {
        super(material, EquipmentSlot.CHEST, settings);
    }

    @Override
    public Text getName(ItemStack stack) {
        return Text.translatable(this.getTranslationKey(stack)).formatted(Formatting.YELLOW);
    }
}
