package net.daplow.mob_revamp.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.daplow.mob_revamp.items.ModItems;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.brain.MemoryModuleType;
import net.minecraft.entity.mob.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PiglinBrain.class)
public abstract class PiglinBrainMixin {


    @ModifyReturnValue(
            method = "wearsGoldArmor(Lnet/minecraft/entity/LivingEntity;)Z",
            at = @At("RETURN")
    )
    private static boolean mob_revamp$allowPiglinChestplate(
            boolean original,
            LivingEntity entity
    ) {
        if (original) return true;

        if (entity instanceof PlayerEntity player) {
            ItemStack chest = player.getEquippedStack(EquipmentSlot.CHEST);
            return chest.isOf(ModItems.PIGLIN_CHESTPLATE);
        }

        return false;
    }


}