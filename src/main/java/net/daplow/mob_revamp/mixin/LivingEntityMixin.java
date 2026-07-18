package net.daplow.mob_revamp.mixin;


import net.daplow.mob_revamp.effects.ModEffects;
import net.daplow.mob_revamp.items.ModItems;
import net.daplow.mob_revamp.items.armor.GuardianLeggingsItem;
import net.daplow.mob_revamp.items.armor.WitherChestplateItem;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {

    @Inject(method = "canTarget(Lnet/minecraft/entity/LivingEntity;)Z", at = @At("HEAD"), cancellable = true)
    private void rabbit_potion_effecter(LivingEntity target,
                                        CallbackInfoReturnable<Boolean> cir) {

        if (target instanceof PlayerEntity player) {
            if (player.hasStatusEffect(ModEffects.RABBIT_POTION)) {

                LivingEntity mob = (LivingEntity)(Object)this;
                if (mob.getAttacker() == player) {
                    return;
                }

                cir.setReturnValue(false);
            }
        }

    }

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

    /// --- DAMAGE --- ///
    @Inject(method = "damage", at = @At("TAIL"))
    private void prismarine_legging_throne(DamageSource source, float amount,
                                           CallbackInfoReturnable<Boolean> cir) {

        LivingEntity entity = (LivingEntity)(Object)this;


        if (source.isFire()) {
            entity.removeStatusEffect(ModEffects.FREEZING_POTION);
            entity.setFrozenTicks(0);
        }

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

    /// Allow Winter Boots to not Freeze player in Powdered Snow
    @Inject(method = "canFreeze", at = @At("HEAD"), cancellable = true)
    private void canFreeze(CallbackInfoReturnable<Boolean> cir) {
        LivingEntity living = (LivingEntity)(Object)this;

        if (living.getEquippedStack(EquipmentSlot.FEET).isOf(ModItems.WINTER_BOOTS)) {
            cir.setReturnValue(false);
        }
    }

    @Redirect(
            method = "travel",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/block/Block;getSlipperiness()F"
            )
    )
    private float noSlipOnIce(Block block) {
        LivingEntity self = (LivingEntity)(Object)this;
        if (!(self instanceof PlayerEntity player)) return block.getSlipperiness();
        if (!player.getEquippedStack(EquipmentSlot.FEET).isOf(ModItems.WINTER_BOOTS)) return block.getSlipperiness();

        ItemStack boots = player.getEquippedStack(EquipmentSlot.FEET);
        if (boots.isOf(ModItems.WINTER_BOOTS)
                    && (block == Blocks.ICE
                    || block == Blocks.PACKED_ICE
                    || block == Blocks.BLUE_ICE
                    || block == Blocks.FROSTED_ICE)) {return 0.6F;}
        return block.getSlipperiness();
    }

    @Redirect(
            method = "travel",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/entity/LivingEntity;updateVelocity(FLnet/minecraft/util/math/Vec3d;)V"
            )
    )
    private void redirectUpdateVelocity(LivingEntity entity, float speed, Vec3d movementInput) {
        if (entity instanceof PlayerEntity player
                && player.isTouchingWater()
                && player.getEquippedStack(EquipmentSlot.FEET).isOf(ModItems.WINTER_BOOTS)) {
            speed *= 1.35F;
        }
        entity.updateVelocity(speed, movementInput);
    }


    @ModifyVariable(
            method = "handleFallDamage",
            at = @At("STORE"),
            ordinal = 0
    )
    private int reduceFallDamage(int damage) {

        LivingEntity entity = (LivingEntity)(Object)this;

        if (entity instanceof PlayerEntity player) {
            if (player.getEquippedStack(EquipmentSlot.FEET)
                    .isOf(ModItems.ENDER_BOOTS)) {

                return 0;
            }
        }

        return damage;
    }



}
