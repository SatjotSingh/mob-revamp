package net.daplow.mob_revamp.mixin;

import net.daplow.mob_revamp.items.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.PowderSnowBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PowderSnowBlock.class)
public class PowderSnowBlockMixin {

    @Inject(method = "canWalkOnPowderSnow", at = @At("HEAD"), cancellable = true)
    private static void WinterBootsPowderSnow (Entity entity, CallbackInfoReturnable<Boolean> cir) {

        if (entity instanceof PlayerEntity player && player.getEquippedStack(EquipmentSlot.FEET).isOf(ModItems.WINTER_BOOTS)) {
            cir.setReturnValue(true);

        }
    }
}
