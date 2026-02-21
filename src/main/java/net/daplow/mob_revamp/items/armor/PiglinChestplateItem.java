package net.daplow.mob_revamp.items.armor;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;

public class PiglinChestplateItem extends ArmorItem {
    public PiglinChestplateItem(ArmorMaterial material, Settings settings) {
        super(material, EquipmentSlot.CHEST, settings);
    }

}
