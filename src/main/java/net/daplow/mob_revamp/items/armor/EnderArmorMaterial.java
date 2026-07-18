package net.daplow.mob_revamp.items.armor;

import net.daplow.mob_revamp.items.ModItems;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ArmorMaterials;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;

public class EnderArmorMaterial implements ArmorMaterial {

    @Override
    public int getDurability(EquipmentSlot slot) {
        return ArmorMaterials.IRON.getDurability(slot);
    }

    @Override
    public int getProtectionAmount(EquipmentSlot slot) {
        return ArmorMaterials.IRON.getProtectionAmount(slot);
    }


    @Override
    public int getEnchantability() {
        return ArmorMaterials.IRON.getEnchantability();
    }

    @Override
    public SoundEvent getEquipSound() {
        return SoundEvents.ITEM_ARMOR_EQUIP_LEATHER;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return Ingredient.ofItems(ModItems.ENDERMITE_SCALE);
    }

    @Override
    public String getName() {
        return "daplow-mob_revamp:ender";
    }

    @Override
    public float getToughness() {
        return 1.0F;
    }

    @Override
    public float getKnockbackResistance() {
        return 0.0F;
    }
}
