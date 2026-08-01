package net.daplow.mob_revamp.mixin;

import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.render.entity.animation.Animation;
import net.minecraft.entity.AnimationState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(SinglePartEntityModel.class)
public interface WardenEntityModelInvoker {

    @Invoker("updateAnimation")
    void invokeUpdateAnimation(
            AnimationState state,
            Animation animation,
            float animationProgress
    );
}