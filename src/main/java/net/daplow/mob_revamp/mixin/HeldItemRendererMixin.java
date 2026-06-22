package net.daplow.mob_revamp.mixin;

import net.daplow.mob_revamp.items.custom.GuardianStaffItem;
import net.daplow.mob_revamp.items.custom.WardenStaffItem;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.item.HeldItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Hand;
import net.minecraft.util.math.Vec3f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Objects;

@Mixin(HeldItemRenderer.class)
public abstract class HeldItemRendererMixin {


    @Inject(method= "renderFirstPersonItem", at = @At("HEAD"))
    private void guardianStaffAnimation (
            AbstractClientPlayerEntity player,
            float tickDelta,
            float pitch,
            Hand hand,
            float swingProgress,
            ItemStack stack,
            float equipProgress,
            MatrixStack matrices,
            VertexConsumerProvider vertexConsumer,
            int light,
            CallbackInfo ci
    ) {

        // Remove all non guardian staff
        if (!(stack.getItem() instanceof GuardianStaffItem)) return;

        if (!player.isUsingItem()) return;


        int useTicks = Math.min(player.getItemUseTime(), 40);
        useTicks = player.getItemUseTime();

        // check if stack.getItem is  purple and make 40 20...

        float time = (useTicks + tickDelta) * 0.20f - 1f;

        int USETIME = 30;
        double SPEED = 1;
        NbtCompound nbt = stack.getNbt();
        if (Objects.equals(nbt.getString("Eye_Variant"), "yellow")) {
            USETIME = 20;
            SPEED = 1.5;
        }
        if (hand == Hand.MAIN_HAND) {
            if (useTicks < USETIME) {

                matrices.translate(0.65, 0.40, 0);

                matrices.translate(
                        Math.sin(time*SPEED) * 0.15,
                        Math.cos(time*SPEED) * 0.1,
                        0
                );

                // rotate upward
                matrices.multiply(Vec3f.POSITIVE_Y.getDegreesQuaternion(90f));
                matrices.multiply(Vec3f.POSITIVE_X.getDegreesQuaternion(-40f));

            } else { // 2 second animation done

                matrices.translate(
                        Math.sin((USETIME * 0.20f - 1f)*SPEED) * 0.15,
                        Math.cos((USETIME * 0.20f - 1f)*SPEED) * 0.1,
                        0
                );

                matrices.translate(
                        0,
                        Math.cos(time * 4.5) * 0.005,
                        0
                );

                matrices.translate(0.65, 0.40, 0);
                matrices.multiply(Vec3f.POSITIVE_Y.getDegreesQuaternion(90f));
                matrices.multiply(Vec3f.POSITIVE_X.getDegreesQuaternion(-40f));

            }
            // One color makes more knockback
            //matrices.multiply(Vec3f.POSITIVE_Z.getDegreesQuaternion(time * 30f));

        }else {  // If left hand :

            if (useTicks < USETIME) {

                matrices.translate(-0.65, 0.40, 0);

                matrices.translate(
                        -Math.sin(time*SPEED) * 0.15,
                        Math.cos(time*SPEED) * 0.1,
                        0
                );

                matrices.multiply(Vec3f.POSITIVE_Y.getDegreesQuaternion(-90f));
                matrices.multiply(Vec3f.POSITIVE_X.getDegreesQuaternion(-40f));


            } else { // 2 second animation done

                matrices.translate(
                        -Math.sin((USETIME * 0.20f - 1f)*SPEED) * 0.15,
                        Math.cos((USETIME * 0.20f - 1f)*SPEED) * 0.1,
                        0
                );

                matrices.translate(
                        0,
                        Math.cos(time * 4.5) * 0.005,
                        0
                );

                matrices.translate(-0.65, 0.40, 0);

                matrices.multiply(Vec3f.POSITIVE_Y.getDegreesQuaternion(-90f));
                matrices.multiply(Vec3f.POSITIVE_X.getDegreesQuaternion(-40f));

            }




        }
    }



    @Inject(method= "renderFirstPersonItem", at = @At("HEAD"))
    private void wardenStaffAnimation (
            AbstractClientPlayerEntity player,
            float tickDelta,
            float pitch,
            Hand hand,
            float swingProgress,
            ItemStack stack,
            float equipProgress,
            MatrixStack matrices,
            VertexConsumerProvider vertexConsumer,
            int light,
            CallbackInfo ci
    ) {

        // Remove all non guardian staff
        if (!(stack.getItem() instanceof WardenStaffItem)) return;

        if (!player.isUsingItem()) return;


        int useTicks = Math.min(player.getItemUseTime(), 40);
        useTicks = player.getItemUseTime();

        // check if stack.getItem is  purple and make 40 20...

        float time = (useTicks + tickDelta) * 0.20f - 1f;

        int USETIME = 30;
        double SPEED = 1;

        if (hand == Hand.MAIN_HAND) {
            if (useTicks < USETIME) {

                matrices.translate(0.65, 0.40, 0);

                matrices.translate(
                        Math.sin(time*SPEED) * 0.15,
                        Math.cos(time*SPEED) * 0.1,
                        0
                );

                // rotate upward
                matrices.multiply(Vec3f.POSITIVE_Y.getDegreesQuaternion(90f));
                matrices.multiply(Vec3f.POSITIVE_X.getDegreesQuaternion(-40f));

            } else { // 2 second animation done

                matrices.translate(
                        Math.sin((USETIME * 0.20f - 1f)*SPEED) * 0.15,
                        Math.cos((USETIME * 0.20f - 1f)*SPEED) * 0.1,
                        0
                );

                matrices.translate(
                        0,
                        Math.cos(time * 4.5) * 0.005,
                        0
                );

                matrices.translate(0.65, 0.40, 0);
                matrices.multiply(Vec3f.POSITIVE_Y.getDegreesQuaternion(90f));
                matrices.multiply(Vec3f.POSITIVE_X.getDegreesQuaternion(-40f));

            }
            // One color makes more knockback
            //matrices.multiply(Vec3f.POSITIVE_Z.getDegreesQuaternion(time * 30f));

        }else {  // If left hand :

            if (useTicks < USETIME) {

                matrices.translate(-0.65, 0.40, 0);

                matrices.translate(
                        -Math.sin(time*SPEED) * 0.15,
                        Math.cos(time*SPEED) * 0.1,
                        0
                );

                matrices.multiply(Vec3f.POSITIVE_Y.getDegreesQuaternion(-90f));
                matrices.multiply(Vec3f.POSITIVE_X.getDegreesQuaternion(-40f));


            } else { // 2 second animation done

                matrices.translate(
                        -Math.sin((USETIME * 0.20f - 1f)*SPEED) * 0.15,
                        Math.cos((USETIME * 0.20f - 1f)*SPEED) * 0.1,
                        0
                );

                matrices.translate(
                        0,
                        Math.cos(time * 4.5) * 0.005,
                        0
                );

                matrices.translate(-0.65, 0.40, 0);

                matrices.multiply(Vec3f.POSITIVE_Y.getDegreesQuaternion(-90f));
                matrices.multiply(Vec3f.POSITIVE_X.getDegreesQuaternion(-40f));

            }




        }
    }
}
