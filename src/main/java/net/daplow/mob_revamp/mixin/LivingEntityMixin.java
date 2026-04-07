package net.daplow.mob_revamp.mixin;


import net.daplow.mob_revamp.items.armor.GuardianLeggingsItem;
import net.daplow.mob_revamp.items.armor.WitherChestplateItem;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {

    @Inject(method = "damage", at = @At("HEAD"), cancellable = true)
    private void wither_chestplate_damage(DamageSource source, float amount,
                                          CallbackInfoReturnable<Boolean> cir) {

        LivingEntity entity = (LivingEntity)(Object)this;

        if (!(entity instanceof PlayerEntity player)) return;

        ItemStack chestplate = player.getEquippedStack(EquipmentSlot.CHEST);

        if (chestplate.getItem() instanceof WitherChestplateItem) {
            if (source.isProjectile()) {
                cir.setReturnValue(false); // stops the code from going to damage code
            }
        }

    }

    @Inject(method = "damage", at = @At("TAIL"))
    private void prismarine_legging_throne(DamageSource source, float amount,
                                           CallbackInfoReturnable<Boolean> cir) {

        LivingEntity entity = (LivingEntity)(Object)this;

        if (!(entity instanceof PlayerEntity player)) return;

        ItemStack legs = player.getEquippedStack(EquipmentSlot.LEGS);

        if (legs.getItem() instanceof GuardianLeggingsItem) {

            if (source.getAttacker() instanceof LivingEntity attacker) {

                if (player.getRandom().nextFloat() < 0.9F) {

                    attacker.damage(DamageSource.thorns(player), 1.0F);
                    legs.damage(1, player, playerEntity -> playerEntity.sendEquipmentBreakStatus(EquipmentSlot.LEGS));

                }

            }

        }



    }

}
