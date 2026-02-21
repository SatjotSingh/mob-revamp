package net.daplow.mob_revamp.mixin;

import net.daplow.mob_revamp.items.ModItems;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.brain.MemoryModuleType;
import net.minecraft.entity.mob.AbstractPiglinEntity;
import net.minecraft.entity.mob.PiglinBruteBrain;
import net.minecraft.entity.mob.PiglinBruteEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Optional;



@Mixin(PiglinBruteBrain.class)
public class PiglinBruteBrainMixin {


    @Inject(
            method = "getTarget",
            at = @At("RETURN"),
            cancellable = true
    )
    private static void preventTargetingWithArmor(
            AbstractPiglinEntity piglin,
            CallbackInfoReturnable<Optional<? extends LivingEntity>> cir
    ) {
        Optional<? extends LivingEntity> result = cir.getReturnValue();

        if (result.isPresent() && result.get() instanceof PlayerEntity player) {
            boolean alreadyAngry = piglin.getBrain().hasMemoryModule(MemoryModuleType.ANGRY_AT);

            if (!alreadyAngry && hasPacifyingArmor(player)) {
                cir.setReturnValue(Optional.empty());
            }
        }
    }

    @Inject(
            method = "tryRevenge",
            at = @At("HEAD"),
            cancellable = true
    )
    private static void onTryRevenge(PiglinBruteEntity brute, LivingEntity attacker, CallbackInfo ci) {
        brute.getBrain().forget(MemoryModuleType.CANT_REACH_WALK_TARGET_SINCE);
        brute.getBrain().remember(MemoryModuleType.ANGRY_AT, attacker.getUuid(), 600L);

        brute.world.getEntitiesByClass(
                AbstractPiglinEntity.class,
                brute.getBoundingBox().expand(16.0),
                piglin -> !(piglin instanceof PiglinBruteEntity)
        ).forEach(piglin -> {
            piglin.getBrain().forget(MemoryModuleType.CANT_REACH_WALK_TARGET_SINCE);
            piglin.getBrain().remember(MemoryModuleType.ANGRY_AT, attacker.getUuid(), 600L);
        });

    }

    // Function to check armor equiped
    private static boolean hasPacifyingArmor(PlayerEntity player) {
            ItemStack chest = player.getEquippedStack(EquipmentSlot.CHEST);
            return chest.isOf(ModItems.PIGLIN_CHESTPLATE);
    }


}
