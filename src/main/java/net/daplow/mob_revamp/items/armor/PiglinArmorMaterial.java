package net.daplow.mob_revamp.items.armor;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ArmorMaterials;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;

public class PiglinArmorMaterial implements ArmorMaterial {

    @Override
    public int getDurability(EquipmentSlot slot) {
        return ArmorMaterials.GOLD.getDurability(slot);
    }

    @Override
    public int getProtectionAmount(EquipmentSlot slot) {
        return ArmorMaterials.GOLD.getProtectionAmount(slot);
    }


    @Override
    public int getEnchantability() {
        return ArmorMaterials.GOLD.getEnchantability();
    }

    @Override
    public SoundEvent getEquipSound() {
        return SoundEvents.ITEM_ARMOR_EQUIP_GOLD;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return (Ingredient.ofItems(Items.GOLD_BLOCK) );
    }

    @Override
    public String getName() {
        return "daplow-mob_revamp:piglin";
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
