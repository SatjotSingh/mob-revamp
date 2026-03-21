package net.daplow.mob_revamp.items.armor;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class GuardianLeggingsItem extends ArmorItem {
    public GuardianLeggingsItem(ArmorMaterial material, Settings settings) {
        super(material, EquipmentSlot.LEGS, settings);
    }

/*
    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        super.inventoryTick(stack, world, entity, slot, selected);

        if (!(entity instanceof PlayerEntity player)) return;


        ItemStack equipped = player.getEquippedStack(EquipmentSlot.LEGS);

        if (equipped == stack) {}

        System.out.println("wearing");

        // les go!


    }

 */

}
