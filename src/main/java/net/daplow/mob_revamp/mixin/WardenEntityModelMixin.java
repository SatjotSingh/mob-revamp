package net.daplow.mob_revamp.mixin;

import net.daplow.mob_revamp.util.other.CustomWardenAnimations;
import net.daplow.mob_revamp.util.other.SmashAnimationAccess;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.animation.Animation;
import net.minecraft.client.render.entity.model.WardenEntityModel;
import net.minecraft.entity.AnimationState;
import net.minecraft.entity.mob.WardenEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(WardenEntityModel.class)
public abstract class WardenEntityModelMixin {


    @Inject(
            method = "setAngles",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/render/entity/model/WardenEntityModel;updateAnimation(Lnet/minecraft/entity/AnimationState;Lnet/minecraft/client/render/entity/animation/Animation;F)V"
            ),
            cancellable = true
    )    private void animateSmash(
            WardenEntity entity,
            float limbAngle,
            float limbDistance,
            float animationProgress,
            float headYaw,
            float headPitch,
            CallbackInfo ci) {


        SmashAnimationAccess access =
                (SmashAnimationAccess) entity;


      //  if(access.getSmashAnimationState().isRunning()) {

            // Reset pose first
//            this.getPart().traverse().forEach(ModelPart::resetTransform);

            // Run ONLY custom animation
            ((WardenEntityModelInvoker)(Object)this)
                    .invokeUpdateAnimation(
                            access.getSmashAnimationState(),
                            CustomWardenAnimations.SMASH,
                            animationProgress
                    );

            //ci.cancel();
       // }
    }

}
