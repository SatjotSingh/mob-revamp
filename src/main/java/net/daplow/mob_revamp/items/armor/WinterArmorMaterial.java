package net.daplow.mob_revamp.items.armor;

import net.daplow.mob_revamp.items.ModItems;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ArmorMaterials;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;

public class WinterArmorMaterial implements ArmorMaterial {

    @Override
    public int getDurability(EquipmentSlot slot) {
        return ArmorMaterials.LEATHER.getDurability(slot);
    }

    @Override
    public int getProtectionAmount(EquipmentSlot slot) {
        return ArmorMaterials.LEATHER.getProtectionAmount(slot);
    }


    @Override
    public int getEnchantability() {
        return ArmorMaterials.LEATHER.getEnchantability();
    }

    @Override
    public SoundEvent getEquipSound() {
        return SoundEvents.ITEM_ARMOR_EQUIP_LEATHER;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return Ingredient.ofItems(ModItems.WINTER_FUR);
    }

    @Override
    public String getName() {
        return "daplow-mob_revamp:winter";
    }

    @Override
    public float getToughness() {
        return 0.0F;
    }

    @Override
    public float getKnockbackResistance() {
        return 0.0F;
    }
}
