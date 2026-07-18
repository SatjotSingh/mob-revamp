package net.daplow.mob_revamp.items.armor;

import net.daplow.mob_revamp.effects.ModEffects;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class EnderBootsItem extends ArmorItem {

    public EnderBootsItem(ArmorMaterial material, Settings settings) {
        super(material, EquipmentSlot.FEET, settings);
    }




    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {

        if (world.isClient) return;

        if (!(entity instanceof PlayerEntity player)) return;

        // Check if this item is actually equipped as boots
        if (player.getEquippedStack(EquipmentSlot.FEET) != stack) return;

        if (player.isOnGround()) {
            player.addStatusEffect(new StatusEffectInstance(
                    StatusEffects.SLOW_FALLING,
                    40, // 5 seconds
                    0,
                    false,
                    false,
                    true
            ));
        }
    }
}
