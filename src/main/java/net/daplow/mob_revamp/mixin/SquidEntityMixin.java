package net.daplow.mob_revamp.mixin;

import net.daplow.mob_revamp.effects.ModEffects;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.passive.GlowSquidEntity;
import net.minecraft.entity.passive.SquidEntity;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(SquidEntity.class)
public class SquidEntityMixin {

    @Inject(method = "squirt", at = @At("tail"))
    private void GiveInkEffect(CallbackInfo ci) {

        SquidEntity squid = (SquidEntity)(Object)this;

        if (squid.getWorld().isClient) {
            return;
        }

        int squidType = 0;
        if (squid instanceof GlowSquidEntity) {
            squidType = 1;
        }

        // Get Nearby Players //
        double radius = 1.5;
        List<PlayerEntity> players = squid.getWorld().getEntitiesByClass(
                PlayerEntity.class,
                squid.getBoundingBox().expand(radius),
                player -> true
        );

        // Give Players Ink Effect //
        for (PlayerEntity player : players) {
            player.addStatusEffect(new StatusEffectInstance(
                    ModEffects.SQUID_POTION,
                    100, //ticks (seconds*20)
                    squidType,
                    false,
                    false,
                    true

            ));
        }



    }
}
