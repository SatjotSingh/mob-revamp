package net.daplow.mob_revamp.mixin;

import net.daplow.mob_revamp.items.ModItems;
import net.daplow.mob_revamp.items.custom.GuardianStaffItem;
import net.daplow.mob_revamp.items.custom.WardenStaffItem;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.item.HeldItemRenderer;
import net.minecraft.client.render.model.json.ModelTransformation;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Arm;
import net.minecraft.util.Hand;
import net.minecraft.util.math.Vec3f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Objects;

@Mixin(HeldItemRenderer.class)
public abstract class HeldItemRendererMixin {

    // FOR GUARDIAN STAFF
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

    // 0/0/0 is facing down left pointing towards me
    private static final float X_Degree = -95;  // was -40
    private static final float Y_Degree = 60;   // was 90 (front)
    private static final float Z_Degree = -15;  // was nothing (right)


    // FOR WARDEN STAFF
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
                                    // x+ moves right
                matrices.translate(0.13 - (time*0.025), 0.15 + (time*0.025), -0.85 + (time*0.025));
                matrices.multiply(Vec3f.POSITIVE_Y.getDegreesQuaternion(Y_Degree));
                matrices.multiply(Vec3f.POSITIVE_X.getDegreesQuaternion(X_Degree));
                matrices.multiply(Vec3f.POSITIVE_Z.getDegreesQuaternion(Z_Degree));

                /*
                matrices.translate(
                        Math.sin(time*SPEED) * 0.15,
                        Math.cos(time*SPEED) * 0.1,
                        -0.25
                );

                 */


            } else { // 2 second animation done

                matrices.translate(// up and down movement
                        0,
                        Math.cos(time * 4.5) * 0.005,
                        0
                );

                matrices.translate(0.13 -(0.1238), 0.15 +(0.1238), -0.85 +(0.1238));

                matrices.multiply(Vec3f.POSITIVE_Y.getDegreesQuaternion(Y_Degree));
                matrices.multiply(Vec3f.POSITIVE_X.getDegreesQuaternion(X_Degree));
                matrices.multiply(Vec3f.POSITIVE_Z.getDegreesQuaternion(Z_Degree));


            }
            // One color makes more knockback
            //matrices.multiply(Vec3f.POSITIVE_Z.getDegreesQuaternion(time * 30f));

        }else {  // If left hand :

            if (useTicks < USETIME) {

                matrices.translate(-0.13 + (time*0.025), 0.15 + (time*0.025), -0.85 + (time*0.025));
                matrices.multiply(Vec3f.POSITIVE_Y.getDegreesQuaternion(-Y_Degree));
                matrices.multiply(Vec3f.POSITIVE_X.getDegreesQuaternion(X_Degree));
                matrices.multiply(Vec3f.POSITIVE_Z.getDegreesQuaternion(-Z_Degree));


            } else { // 2 second animation done
                matrices.translate(// up and down movement
                        0,
                        Math.cos(time * 4.5) * 0.005,
                        0
                );

                matrices.translate(-0.13 +(0.1238), 0.15 +(0.1238), -0.85 +(0.1238));

                matrices.multiply(Vec3f.POSITIVE_Y.getDegreesQuaternion(-Y_Degree));
                matrices.multiply(Vec3f.POSITIVE_X.getDegreesQuaternion(X_Degree));
                matrices.multiply(Vec3f.POSITIVE_Z.getDegreesQuaternion(-Z_Degree));


            }


        }
    }



    @Inject(
            method = "renderItem",
            at = @At("HEAD")
    )
    private void renderItem(
            LivingEntity entity,
            ItemStack stack,
            ModelTransformation.Mode renderMode,
            boolean leftHanded,
            MatrixStack matrices,
            VertexConsumerProvider vertexConsumers,
            int light,
            CallbackInfo ci
    ) {

        if (stack.isOf(ModItems.WARDEN_STAFF)
                && entity.isUsingItem()) {
            //System.out.println(leftHanded);

            if (leftHanded) {
                matrices.translate(0.15/*left*/, 0/*forward*/, 0.1/*up*/);
                matrices.multiply(Vec3f.POSITIVE_X.getDegreesQuaternion(20)); // up
                matrices.multiply(Vec3f.POSITIVE_Y.getDegreesQuaternion(-20));
                matrices.multiply(Vec3f.POSITIVE_Z.getDegreesQuaternion(-40)); //GOOD

            } else {
                matrices.translate(-0.15/*left*/, 0/*forward*/, 0.1/*up*/);
                matrices.multiply(Vec3f.POSITIVE_X.getDegreesQuaternion(20)); // up
                matrices.multiply(Vec3f.POSITIVE_Y.getDegreesQuaternion(20));
                matrices.multiply(Vec3f.POSITIVE_Z.getDegreesQuaternion(40));

            }
        }
    }

 }
