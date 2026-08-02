package net.daplow.mob_revamp.mixin;

import net.daplow.mob_revamp.util.other.CustomWardenAnimations;
import net.daplow.mob_revamp.util.other.SmashAnimationAccess;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.animation.Animation;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.render.entity.model.WardenEntityModel;
import net.minecraft.entity.AnimationState;
import net.minecraft.entity.mob.WardenEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.gen.Invoker;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(WardenEntityModel.class)
public abstract class WardenEntityModelMixin         extends SinglePartEntityModel<WardenEntity> {

    protected WardenEntityModelMixin() {
        super();
    }




    @Inject(method = "setAngles", at = @At("TAIL"))
    private void animateSmash(
            WardenEntity entity,
            float limbAngle,
            float limbDistance,
            float animationProgress,
            float headYaw,
            float headPitch,
            CallbackInfo ci) {

        SmashAnimationAccess access = (SmashAnimationAccess) entity;

        AnimationState state = access.getSmashAnimationState();

        if (!state.isRunning()) {
            return;
        }

        ((WardenEntityModelInvoker)(Object)this).invokeUpdateAnimation(
                state,
                CustomWardenAnimations.NEW_SMASH,
                animationProgress
        );
    }

}
