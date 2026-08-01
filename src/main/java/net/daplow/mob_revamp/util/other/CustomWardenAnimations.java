package net.daplow.mob_revamp.util.other;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.animation.Animation;
import net.minecraft.client.render.entity.animation.AnimationHelper;
import net.minecraft.client.render.entity.animation.Keyframe;
import net.minecraft.client.render.entity.animation.Transformation;
import net.minecraft.client.render.entity.animation.Transformation.Interpolations;
import net.minecraft.client.render.entity.animation.Transformation.Targets;

@Environment(EnvType.CLIENT)
public class CustomWardenAnimations {
     public static final Animation TEST;
     public static final Animation SECOND_TEST;
    public static final Animation SMASH = Animation.Builder.create(1.7917F)

            .addBoneAnimation("head",
                    new Transformation(
                            Transformation.Targets.ROTATE,
                            new Keyframe[]{
                                    new Keyframe(0.0F,
                                            AnimationHelper.createRotationalVector(0F, 0F, 0F),
                                            Transformation.Interpolations.CUBIC),
                                    new Keyframe(0.2083F,
                                            AnimationHelper.createRotationalVector(12.5F, 0F, 0F),
                                            Transformation.Interpolations.CUBIC),
                                    new Keyframe(0.5F,
                                            AnimationHelper.createRotationalVector(11.56F, 0F, 0F),
                                            Transformation.Interpolations.CUBIC),
                                    new Keyframe(0.8333F,
                                            AnimationHelper.createRotationalVector(-18.35F, 0F, 0F),
                                            Transformation.Interpolations.CUBIC),
                                    new Keyframe(1.0417F,
                                            AnimationHelper.createRotationalVector(-17.23F, 0F, 0F),
                                            Transformation.Interpolations.CUBIC),
                                    new Keyframe(1.5F,
                                            AnimationHelper.createRotationalVector(21.31F, 0F, 0F),
                                            Transformation.Interpolations.CUBIC),
                                    new Keyframe(1.7917F,
                                            AnimationHelper.createRotationalVector(0F, 0F, 0F),
                                            Transformation.Interpolations.CUBIC)
                            }))

            .addBoneAnimation("torso",
                    new Transformation(
                            Transformation.Targets.ROTATE,
                            new Keyframe[]{
                                    new Keyframe(0.0F,
                                            AnimationHelper.createRotationalVector(0F, 0F, 0F),
                                            Transformation.Interpolations.CUBIC),
                                    new Keyframe(0.2917F,
                                            AnimationHelper.createRotationalVector(17.5F, 0F, 0F),
                                            Transformation.Interpolations.CUBIC),
                                    new Keyframe(1.0417F,
                                            AnimationHelper.createRotationalVector(-35F, 0F, 0F),
                                            Transformation.Interpolations.CUBIC),
                                    new Keyframe(1.25F,
                                            AnimationHelper.createRotationalVector(87.5F, 0F, 0F),
                                            Transformation.Interpolations.CUBIC),
                                    new Keyframe(1.4583F,
                                            AnimationHelper.createRotationalVector(87.5F, 0F, 0F),
                                            Transformation.Interpolations.CUBIC),
                                    new Keyframe(1.7917F,
                                            AnimationHelper.createRotationalVector(0F, 0F, 0F),
                                            Transformation.Interpolations.CUBIC)
                            }))

            .addBoneAnimation("left_arm",
                    new Transformation(
                            Transformation.Targets.ROTATE,
                            new Keyframe[]{
                                    new Keyframe(0.0F,
                                            AnimationHelper.createRotationalVector(0F, 0F, 0F),
                                            Transformation.Interpolations.LINEAR),
                                    new Keyframe(0.2083F,
                                            AnimationHelper.createRotationalVector(15.3013F, 2.6568F, -8.7221F),
                                            Transformation.Interpolations.LINEAR),
                                    new Keyframe(0.5F,
                                            AnimationHelper.createRotationalVector(1.6343F, 6.4883F, -23.2739F),
                                            Transformation.Interpolations.LINEAR),
                                    new Keyframe(1.0F,
                                            AnimationHelper.createRotationalVector(-68.1736F, -15.1347F, -75.4431F),
                                            Transformation.Interpolations.LINEAR),
                                    new Keyframe(1.25F,
                                            AnimationHelper.createRotationalVector(-123.9333F, -7.3448F, -2.2777F),
                                            Transformation.Interpolations.LINEAR),
                                    new Keyframe(1.375F,
                                            AnimationHelper.createRotationalVector(-133.2777F, -6.1207F, -1.8981F),
                                            Transformation.Interpolations.LINEAR),
                                    new Keyframe(1.7917F,
                                            AnimationHelper.createRotationalVector(0F, 0F, 0F),
                                            Transformation.Interpolations.LINEAR)
                            }))

            .addBoneAnimation("right_arm",
                    new Transformation(
                            Transformation.Targets.ROTATE,
                            new Keyframe[]{
                                    new Keyframe(0.0F,
                                            AnimationHelper.createRotationalVector(0F, 0F, 0F),
                                            Transformation.Interpolations.LINEAR),
                                    new Keyframe(0.2083F,
                                            AnimationHelper.createRotationalVector(15.3013F, -2.6568F, 8.7221F),
                                            Transformation.Interpolations.LINEAR),
                                    new Keyframe(0.5F,
                                            AnimationHelper.createRotationalVector(1.6343F, -6.4883F, 23.2739F),
                                            Transformation.Interpolations.LINEAR),
                                    new Keyframe(1.0F,
                                            AnimationHelper.createRotationalVector(-68.1736F, 15.1347F, 75.4431F),
                                            Transformation.Interpolations.LINEAR),
                                    new Keyframe(1.25F,
                                            AnimationHelper.createRotationalVector(-123.9333F, 7.3448F, 2.2777F),
                                            Transformation.Interpolations.LINEAR),
                                    new Keyframe(1.375F,
                                            AnimationHelper.createRotationalVector(-133.2777F, 6.1207F, 1.8981F),
                                            Transformation.Interpolations.LINEAR),
                                    new Keyframe(1.7917F,
                                            AnimationHelper.createRotationalVector(0F, 0F, 0F),
                                            Transformation.Interpolations.LINEAR)
                            }))

            .addBoneAnimation("right_leg",
                    new Transformation(
                            Transformation.Targets.SCALE,
                            new Keyframe[]{
                                    new Keyframe(0.2917F,
                                            AnimationHelper.createScalingVector(1F, 1F, 1F),
                                            Transformation.Interpolations.LINEAR)
                            }))

            .build();
     static {
         TEST = Animation.Builder.create(1.0F)

                 .addBoneAnimation("head",
                         new Transformation(
                                 Transformation.Targets.ROTATE,
                                 new Keyframe[]{
                                         new Keyframe(0.0F,
                                                 AnimationHelper.createRotationalVector(0F, 0F, 0F),
                                                 Transformation.Interpolations.LINEAR),
                                         new Keyframe(0.20833F,
                                                 AnimationHelper.createRotationalVector(55F, 0F, 0F),
                                                 Transformation.Interpolations.LINEAR),
                                         new Keyframe(1.0F,
                                                 AnimationHelper.createRotationalVector(0F, 0F, 0F),
                                                 Transformation.Interpolations.LINEAR)
                                 }))

                 .addBoneAnimation("left_arm",
                         new Transformation(
                                 Transformation.Targets.ROTATE,
                                 new Keyframe[]{
                                         new Keyframe(0.0F,
                                                 AnimationHelper.createRotationalVector(0F,0F,0F),
                                                 Transformation.Interpolations.LINEAR),
                                         new Keyframe(0.20833F,
                                                 AnimationHelper.createRotationalVector(-1.5224F,-1.983F,-52.4736F),
                                                 Transformation.Interpolations.LINEAR),
                                         new Keyframe(1.0F,
                                                 AnimationHelper.createRotationalVector(0F,0F,0F),
                                                 Transformation.Interpolations.LINEAR)
                                 }))

                 .addBoneAnimation("right_arm",
                         new Transformation(
                                 Targets.ROTATE,
                                 new Keyframe[]{
                                         new Keyframe(0.0F,
                                                 AnimationHelper.createRotationalVector(0F,0F,0F),
                                                 Interpolations.LINEAR),
                                         new Keyframe(0.20833F,
                                                 AnimationHelper.createRotationalVector(0F,0F,62.5F),
                                                 Interpolations.LINEAR),
                                         new Keyframe(1.0F,
                                                 AnimationHelper.createRotationalVector(0F,0F,0F),
                                                 Interpolations.LINEAR)
                                 }))

                 .addBoneAnimation("left_leg",
                         new Transformation(
                                 Targets.ROTATE,
                                 new Keyframe[]{
                                         new Keyframe(0.0F,
                                                 AnimationHelper.createRotationalVector(0F,0F,0F),
                                                 Interpolations.LINEAR),
                                         new Keyframe(0.20833F,
                                                 AnimationHelper.createRotationalVector(-17.5F,0F,0F),
                                                 Interpolations.LINEAR),
                                         new Keyframe(1.0F,
                                                 AnimationHelper.createRotationalVector(0F,0F,0F),
                                                 Interpolations.LINEAR)
                                 }))

                 .addBoneAnimation("right_leg",
                         new Transformation(
                                 Targets.ROTATE,
                                 new Keyframe[]{
                                         new Keyframe(0.0F,
                                                 AnimationHelper.createRotationalVector(0F,0F,0F),
                                                 Interpolations.LINEAR),
                                         new Keyframe(0.20833F,
                                                 AnimationHelper.createRotationalVector(-12.5F,0F,0F),
                                                 Interpolations.LINEAR),
                                         new Keyframe(1.0F,
                                                 AnimationHelper.createRotationalVector(0F,0F,0F),
                                                 Interpolations.LINEAR)
                                 }))

                 .build();


         SECOND_TEST = Animation.Builder.create(1.0F)

                 .addBoneAnimation("head",
                         new Transformation(
                                 Transformation.Targets.ROTATE,
                                 new Keyframe[]{
                                         new Keyframe(
                                                 0.0F,
                                                 AnimationHelper.createRotationalVector(0F, 0F, 0F),
                                                 Transformation.Interpolations.LINEAR
                                         ),
                                         new Keyframe(
                                                 0.2083F,
                                                 AnimationHelper.createRotationalVector(15F, 0F, 0F),
                                                 Transformation.Interpolations.LINEAR
                                         ),
                                         new Keyframe(
                                                 1.0F,
                                                 AnimationHelper.createRotationalVector(0F, 0F, 0F),
                                                 Transformation.Interpolations.LINEAR
                                         )
                                 }))

                 .addBoneAnimation("left_arm",
                         new Transformation(
                                 Transformation.Targets.ROTATE,
                                 new Keyframe[]{
                                         new Keyframe(
                                                 0.0F,
                                                 AnimationHelper.createRotationalVector(0F,0F,0F),
                                                 Transformation.Interpolations.LINEAR
                                         ),
                                         new Keyframe(
                                                 0.2083F,
                                                 AnimationHelper.createRotationalVector(1.5224F,1.983F,-52.4736F),
                                                 Transformation.Interpolations.LINEAR
                                         ),
                                         new Keyframe(
                                                 1.0F,
                                                 AnimationHelper.createRotationalVector(0F,0F,0F),
                                                 Transformation.Interpolations.LINEAR
                                         )
                                 }))

                 .addBoneAnimation("right_arm",
                         new Transformation(
                                 Transformation.Targets.ROTATE,
                                 new Keyframe[]{
                                         new Keyframe(
                                                 0.0F,
                                                 AnimationHelper.createRotationalVector(0F,0F,0F),
                                                 Transformation.Interpolations.LINEAR
                                         ),
                                         new Keyframe(
                                                 0.2083F,
                                                 AnimationHelper.createRotationalVector(0F,0F,62.5F),
                                                 Transformation.Interpolations.LINEAR
                                         ),
                                         new Keyframe(
                                                 1.0F,
                                                 AnimationHelper.createRotationalVector(0F,0F,0F),
                                                 Transformation.Interpolations.LINEAR
                                         )
                                 }))

                 .addBoneAnimation("left_leg",
                         new Transformation(
                                 Transformation.Targets.ROTATE,
                                 new Keyframe[]{
                                         new Keyframe(
                                                 0.0F,
                                                 AnimationHelper.createRotationalVector(0F,0F,0F),
                                                 Transformation.Interpolations.LINEAR
                                         ),
                                         new Keyframe(
                                                 0.2083F,
                                                 AnimationHelper.createRotationalVector(17.5F,0F,0F),
                                                 Transformation.Interpolations.LINEAR
                                         ),
                                         new Keyframe(
                                                 1.0F,
                                                 AnimationHelper.createRotationalVector(0F,0F,0F),
                                                 Transformation.Interpolations.LINEAR
                                         )
                                 }))

                 .addBoneAnimation("right_leg",
                         new Transformation(
                                 Transformation.Targets.ROTATE,
                                 new Keyframe[]{
                                         new Keyframe(
                                                 0.0F,
                                                 AnimationHelper.createRotationalVector(0F,0F,0F),
                                                 Transformation.Interpolations.LINEAR
                                         ),
                                         new Keyframe(
                                                 0.2083F,
                                                 AnimationHelper.createRotationalVector(12.5F,0F,0F),
                                                 Transformation.Interpolations.LINEAR
                                         ),
                                         new Keyframe(
                                                 1.0F,
                                                 AnimationHelper.createRotationalVector(0F,0F,0F),
                                                 Transformation.Interpolations.LINEAR
                                         )
                                 }))

                 .build();



     }
    public static final Animation DA_DANCE = Animation.Builder.create(5.5417F)
            .addBoneAnimation("head",
                    new Transformation(
                            Transformation.Targets.ROTATE,
                            new Keyframe[]{
                                    new Keyframe(0.0F,
                                            AnimationHelper.createRotationalVector(17.5F, 0.0F, 0.0F),
                                            Transformation.Interpolations.CUBIC),
                                    new Keyframe(0.25F,
                                            AnimationHelper.createRotationalVector(2.5F, 0.0F, 0.0F),
                                            Transformation.Interpolations.CUBIC),
                                    new Keyframe(0.5F,
                                            AnimationHelper.createRotationalVector(17.5F, 0.0F, 0.0F),
                                            Transformation.Interpolations.CUBIC),
                                    new Keyframe(0.75F,
                                            AnimationHelper.createRotationalVector(2.5F, 0.0F, 0.0F),
                                            Transformation.Interpolations.CUBIC),
                                    new Keyframe(1.0F,
                                            AnimationHelper.createRotationalVector(17.5F, 0.0F, 0.0F),
                                            Transformation.Interpolations.CUBIC),
                                    new Keyframe(1.25F,
                                            AnimationHelper.createRotationalVector(2.5F, 0.0F, 0.0F),
                                            Transformation.Interpolations.CUBIC),
                                    new Keyframe(1.5F,
                                            AnimationHelper.createRotationalVector(17.5F, 0.0F, 0.0F),
                                            Transformation.Interpolations.CUBIC),
                                    new Keyframe(1.75F,
                                            AnimationHelper.createRotationalVector(2.5F, 0.0F, 0.0F),
                                            Transformation.Interpolations.CUBIC),
                                    new Keyframe(2.0F,
                                            AnimationHelper.createRotationalVector(17.5F, 0.0F, 0.0F),
                                            Transformation.Interpolations.CUBIC),
                                    new Keyframe(2.25F,
                                            AnimationHelper.createRotationalVector(2.5F, 0.0F, 0.0F),
                                            Transformation.Interpolations.CUBIC),
                                    new Keyframe(2.5F,
                                            AnimationHelper.createRotationalVector(17.5F, 0.0F, 0.0F),
                                            Transformation.Interpolations.CUBIC),
                                    new Keyframe(2.75F,
                                            AnimationHelper.createRotationalVector(2.5F, 0.0F, 0.0F),
                                            Transformation.Interpolations.CUBIC),
                                    new Keyframe(3.0F,
                                            AnimationHelper.createRotationalVector(17.5F, 0.0F, 0.0F),
                                            Transformation.Interpolations.CUBIC),
                                    new Keyframe(3.25F,
                                            AnimationHelper.createRotationalVector(2.5F, 0.0F, 0.0F),
                                            Transformation.Interpolations.CUBIC),
                                    new Keyframe(3.5F,
                                            AnimationHelper.createRotationalVector(17.5F, 0.0F, 0.0F),
                                            Transformation.Interpolations.CUBIC),
                                    new Keyframe(3.75F,
                                            AnimationHelper.createRotationalVector(2.5F, 0.0F, 0.0F),
                                            Transformation.Interpolations.CUBIC),
                                    new Keyframe(4.0F,
                                            AnimationHelper.createRotationalVector(17.5F, 0.0F, 0.0F),
                                            Transformation.Interpolations.CUBIC),
                                    new Keyframe(4.25F,
                                            AnimationHelper.createRotationalVector(2.5F, 0.0F, 0.0F),
                                            Transformation.Interpolations.CUBIC),
                                    new Keyframe(4.5F,
                                            AnimationHelper.createRotationalVector(17.5F, 0.0F, 0.0F),
                                            Transformation.Interpolations.CUBIC),
                                    new Keyframe(4.75F,
                                            AnimationHelper.createRotationalVector(2.5F, 0.0F, 0.0F),
                                            Transformation.Interpolations.CUBIC),
                                    new Keyframe(5.0F,
                                            AnimationHelper.createRotationalVector(17.5F, 0.0F, 0.0F),
                                            Transformation.Interpolations.CUBIC),
                                    new Keyframe(5.25F,
                                            AnimationHelper.createRotationalVector(2.5F, 0.0F, 0.0F),
                                            Transformation.Interpolations.CUBIC)
                            }))

            .addBoneAnimation("torso",
                    new Transformation(
                            Transformation.Targets.ROTATE,
                            new Keyframe[]{
                                    new Keyframe(0.0F,
                                            AnimationHelper.createRotationalVector(7.5F, 0.0F, 0.0F),
                                            Transformation.Interpolations.CUBIC),
                                    new Keyframe(0.2083F,
                                            AnimationHelper.createRotationalVector(7.5628F, 7.3383F, 0.9715F),
                                            Transformation.Interpolations.CUBIC),
                                    new Keyframe(0.4583F,
                                            AnimationHelper.createRotationalVector(8.0099F, -11.1145F, -1.5985F),
                                            Transformation.Interpolations.CUBIC),
                                    new Keyframe(0.6667F,
                                            AnimationHelper.createRotationalVector(0.5885F, 13.6468F, 1.8757F),
                                            Transformation.Interpolations.CUBIC),
                                    new Keyframe(0.9167F,
                                            AnimationHelper.createRotationalVector(-9.4269F, 3.6472F, 1.7733F),
                                            Transformation.Interpolations.CUBIC),
                                    new Keyframe(1.0833F,
                                            AnimationHelper.createRotationalVector(-37.0146F, 8.5789F, 0.9464F),
                                            Transformation.Interpolations.CUBIC),
                                    new Keyframe(1.5F,
                                            AnimationHelper.createRotationalVector(-37.0146F, 8.5789F, 0.9464F),
                                            Transformation.Interpolations.CUBIC),
                                    new Keyframe(2.0F,
                                            AnimationHelper.createRotationalVector(1.0035F, 62.4359F, -14.135F),
                                            Transformation.Interpolations.CUBIC),
                                    new Keyframe(3.0F,
                                            AnimationHelper.createRotationalVector(1.0035F, 62.4359F, -14.135F),
                                            Transformation.Interpolations.CUBIC),
                                    new Keyframe(3.5F,
                                            AnimationHelper.createRotationalVector(-43.4859F, -45.5027F, 12.0856F),
                                            Transformation.Interpolations.CUBIC)
                            }))        .addBoneAnimation("left_arm",
                    new Transformation(
                            Transformation.Targets.ROTATE,
                            new Keyframe[]{
                                    new Keyframe(0.0F,
                                            AnimationHelper.createRotationalVector(-65.9286F, 20.9878F, 8.2738F),
                                            Transformation.Interpolations.CUBIC),
                                    new Keyframe(0.25F,
                                            AnimationHelper.createRotationalVector(-90.9753F, 20.6743F, 8.1283F),
                                            Transformation.Interpolations.CUBIC),
                                    new Keyframe(0.5F,
                                            AnimationHelper.createRotationalVector(-88.8984F, 0.3021F, 7.552F),
                                            Transformation.Interpolations.CUBIC),
                                    new Keyframe(0.75F,
                                            AnimationHelper.createRotationalVector(-67.1009F, 1.6011F, 7.4389F),
                                            Transformation.Interpolations.CUBIC),
                                    new Keyframe(1.0F,
                                            AnimationHelper.createRotationalVector(-65.9286F, 20.9878F, 8.2738F),
                                            Transformation.Interpolations.CUBIC),
                                    new Keyframe(1.25F,
                                            AnimationHelper.createRotationalVector(-90.9753F, 20.6743F, 8.1283F),
                                            Transformation.Interpolations.CUBIC),
                                    new Keyframe(1.5F,
                                            AnimationHelper.createRotationalVector(-88.8984F, 0.3021F, 7.552F),
                                            Transformation.Interpolations.CUBIC),
                                    new Keyframe(1.75F,
                                            AnimationHelper.createRotationalVector(-67.1009F, 1.6011F, 7.4389F),
                                            Transformation.Interpolations.CUBIC),
                                    new Keyframe(2.0F,
                                            AnimationHelper.createRotationalVector(-65.9286F, 20.9878F, 8.2738F),
                                            Transformation.Interpolations.CUBIC),
                                    new Keyframe(2.25F,
                                            AnimationHelper.createRotationalVector(-90.9753F, 20.6743F, 8.1283F),
                                            Transformation.Interpolations.CUBIC),
                                    new Keyframe(2.5F,
                                            AnimationHelper.createRotationalVector(-88.8984F, 0.3021F, 7.552F),
                                            Transformation.Interpolations.CUBIC),
                                    new Keyframe(2.75F,
                                            AnimationHelper.createRotationalVector(-67.1009F, 1.6011F, 7.4389F),
                                            Transformation.Interpolations.CUBIC),
                                    new Keyframe(3.0F,
                                            AnimationHelper.createRotationalVector(-65.9286F, 20.9878F, 8.2738F),
                                            Transformation.Interpolations.CUBIC),
                                    new Keyframe(3.25F,
                                            AnimationHelper.createRotationalVector(-90.9753F, 20.6743F, 8.1283F),
                                            Transformation.Interpolations.CUBIC),
                                    new Keyframe(3.5F,
                                            AnimationHelper.createRotationalVector(-88.8984F, 0.3021F, 7.552F),
                                            Transformation.Interpolations.CUBIC),
                                    new Keyframe(3.75F,
                                            AnimationHelper.createRotationalVector(-67.1009F, 1.6011F, 7.4389F),
                                            Transformation.Interpolations.CUBIC),
                                    new Keyframe(4.0F,
                                            AnimationHelper.createRotationalVector(-65.9286F, 20.9878F, 8.2738F),
                                            Transformation.Interpolations.CUBIC)
                            }))

            .addBoneAnimation("right_arm",
                    new Transformation(
                            Transformation.Targets.ROTATE,
                            new Keyframe[]{
                                    new Keyframe(0.0F,
                                            AnimationHelper.createRotationalVector(-89.8531F, -22.5227F, -1.5905F),
                                            Transformation.Interpolations.CUBIC),
                                    new Keyframe(0.25F,
                                            AnimationHelper.createRotationalVector(-70.9004F, -22.1407F, -1.8755F),
                                            Transformation.Interpolations.CUBIC),
                                    new Keyframe(0.5F,
                                            AnimationHelper.createRotationalVector(-66.1698F, -40.4594F, -10.6735F),
                                            Transformation.Interpolations.CUBIC),
                                    new Keyframe(0.75F,
                                            AnimationHelper.createRotationalVector(-83.7061F, -38.4789F, -10.301F),
                                            Transformation.Interpolations.CUBIC),
                                    new Keyframe(1.0F,
                                            AnimationHelper.createRotationalVector(-83.8278F, -9.4703F, -6.4279F),
                                            Transformation.Interpolations.CUBIC),
                                    new Keyframe(1.25F,
                                            AnimationHelper.createRotationalVector(-70.9004F, -22.1407F, -1.8755F),
                                            Transformation.Interpolations.CUBIC),
                                    new Keyframe(1.5F,
                                            AnimationHelper.createRotationalVector(-66.1698F, -40.4594F, -10.6735F),
                                            Transformation.Interpolations.CUBIC),
                                    new Keyframe(1.75F,
                                            AnimationHelper.createRotationalVector(-83.7061F, -38.4789F, -10.301F),
                                            Transformation.Interpolations.CUBIC),
                                    new Keyframe(2.0F,
                                            AnimationHelper.createRotationalVector(-83.8278F, -9.4703F, -6.4279F),
                                            Transformation.Interpolations.CUBIC),
                                    new Keyframe(2.25F,
                                            AnimationHelper.createRotationalVector(-70.9004F, -22.1407F, -1.8755F),
                                            Transformation.Interpolations.CUBIC),
                                    new Keyframe(2.5F,
                                            AnimationHelper.createRotationalVector(-66.1698F, -40.4594F, -10.6735F),
                                            Transformation.Interpolations.CUBIC),
                                    new Keyframe(2.75F,
                                            AnimationHelper.createRotationalVector(-83.7061F, -38.4789F, -10.301F),
                                            Transformation.Interpolations.CUBIC),
                                    new Keyframe(3.0F,
                                            AnimationHelper.createRotationalVector(-83.8278F, -9.4703F, -6.4279F),
                                            Transformation.Interpolations.CUBIC),
                                    new Keyframe(3.25F,
                                            AnimationHelper.createRotationalVector(-83.8278F, -9.4703F, -6.4279F),
                                            Transformation.Interpolations.CUBIC),
                                    new Keyframe(3.5F,
                                            AnimationHelper.createRotationalVector(-66.1698F, -40.4594F, -10.6735F),
                                            Transformation.Interpolations.CUBIC),
                                    new Keyframe(3.75F,
                                            AnimationHelper.createRotationalVector(-83.7061F, -38.4789F, -10.301F),
                                            Transformation.Interpolations.CUBIC),
                                    new Keyframe(4.0F,
                                            AnimationHelper.createRotationalVector(-83.8278F, -9.4703F, -6.4279F),
                                            Transformation.Interpolations.CUBIC)
                            }))        .addBoneAnimation("left_leg",
                    new Transformation(
                            Transformation.Targets.ROTATE,
                            new Keyframe[]{
                                    new Keyframe(0.2917F,
                                            AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F),
                                            Transformation.Interpolations.CUBIC),
                                    new Keyframe(0.5417F,
                                            AnimationHelper.createRotationalVector(-52.5F, 0.0F, 0.0F),
                                            Transformation.Interpolations.CUBIC),
                                    new Keyframe(0.7917F,
                                            AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F),
                                            Transformation.Interpolations.CUBIC),
                                    new Keyframe(1.0833F,
                                            AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F),
                                            Transformation.Interpolations.CUBIC),
                                    new Keyframe(1.3333F,
                                            AnimationHelper.createRotationalVector(-52.5F, 0.0F, 0.0F),
                                            Transformation.Interpolations.CUBIC),
                                    new Keyframe(1.5833F,
                                            AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F),
                                            Transformation.Interpolations.CUBIC),
                                    new Keyframe(1.875F,
                                            AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F),
                                            Transformation.Interpolations.CUBIC),
                                    new Keyframe(2.125F,
                                            AnimationHelper.createRotationalVector(-52.5F, 0.0F, 0.0F),
                                            Transformation.Interpolations.CUBIC),
                                    new Keyframe(2.375F,
                                            AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F),
                                            Transformation.Interpolations.CUBIC),
                                    new Keyframe(2.6667F,
                                            AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F),
                                            Transformation.Interpolations.CUBIC),
                                    new Keyframe(2.9167F,
                                            AnimationHelper.createRotationalVector(-52.5F, 0.0F, 0.0F),
                                            Transformation.Interpolations.CUBIC),
                                    new Keyframe(3.1667F,
                                            AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F),
                                            Transformation.Interpolations.CUBIC),
                                    new Keyframe(3.4583F,
                                            AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F),
                                            Transformation.Interpolations.CUBIC),
                                    new Keyframe(3.7083F,
                                            AnimationHelper.createRotationalVector(-52.5F, 0.0F, 0.0F),
                                            Transformation.Interpolations.CUBIC),
                                    new Keyframe(3.9583F,
                                            AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F),
                                            Transformation.Interpolations.CUBIC),
                                    new Keyframe(4.25F,
                                            AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F),
                                            Transformation.Interpolations.CUBIC),
                                    new Keyframe(4.5F,
                                            AnimationHelper.createRotationalVector(-52.5F, 0.0F, 0.0F),
                                            Transformation.Interpolations.CUBIC),
                                    new Keyframe(4.75F,
                                            AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F),
                                            Transformation.Interpolations.CUBIC),
                                    new Keyframe(5.0417F,
                                            AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F),
                                            Transformation.Interpolations.CUBIC),
                                    new Keyframe(5.2917F,
                                            AnimationHelper.createRotationalVector(-52.5F, 0.0F, 0.0F),
                                            Transformation.Interpolations.CUBIC),
                                    new Keyframe(5.5417F,
                                            AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F),
                                            Transformation.Interpolations.CUBIC)
                            }))

            .addBoneAnimation("right_leg",
                    new Transformation(
                            Transformation.Targets.ROTATE,
                            new Keyframe[]{
                                    new Keyframe(0.0F,
                                            AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F),
                                            Transformation.Interpolations.CUBIC),
                                    new Keyframe(0.25F,
                                            AnimationHelper.createRotationalVector(-52.5F, 0.0F, 0.0F),
                                            Transformation.Interpolations.CUBIC),
                                    new Keyframe(0.5F,
                                            AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F),
                                            Transformation.Interpolations.CUBIC),
                                    new Keyframe(0.7917F,
                                            AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F),
                                            Transformation.Interpolations.CUBIC),
                                    new Keyframe(1.0417F,
                                            AnimationHelper.createRotationalVector(-52.5F, 0.0F, 0.0F),
                                            Transformation.Interpolations.CUBIC),
                                    new Keyframe(1.2917F,
                                            AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F),
                                            Transformation.Interpolations.CUBIC),
                                    new Keyframe(1.5833F,
                                            AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F),
                                            Transformation.Interpolations.CUBIC),
                                    new Keyframe(1.8333F,
                                            AnimationHelper.createRotationalVector(-52.5F, 0.0F, 0.0F),
                                            Transformation.Interpolations.CUBIC),
                                    new Keyframe(2.0833F,
                                            AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F),
                                            Transformation.Interpolations.CUBIC),
                                    new Keyframe(2.375F,
                                            AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F),
                                            Transformation.Interpolations.CUBIC),
                                    new Keyframe(2.625F,
                                            AnimationHelper.createRotationalVector(-52.5F, 0.0F, 0.0F),
                                            Transformation.Interpolations.CUBIC),
                                    new Keyframe(2.875F,
                                            AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F),
                                            Transformation.Interpolations.CUBIC),
                                    new Keyframe(3.1667F,
                                            AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F),
                                            Transformation.Interpolations.CUBIC),
                                    new Keyframe(3.4167F,
                                            AnimationHelper.createRotationalVector(-52.5F, 0.0F, 0.0F),
                                            Transformation.Interpolations.CUBIC),
                                    new Keyframe(3.6667F,
                                            AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F),
                                            Transformation.Interpolations.CUBIC),
                                    new Keyframe(3.9583F,
                                            AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F),
                                            Transformation.Interpolations.CUBIC),
                                    new Keyframe(4.2083F,
                                            AnimationHelper.createRotationalVector(-52.5F, 0.0F, 0.0F),
                                            Transformation.Interpolations.CUBIC),
                                    new Keyframe(4.4583F,
                                            AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F),
                                            Transformation.Interpolations.CUBIC),
                                    new Keyframe(4.75F,
                                            AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F),
                                            Transformation.Interpolations.CUBIC),
                                    new Keyframe(5.0F,
                                            AnimationHelper.createRotationalVector(-52.5F, 0.0F, 0.0F),
                                            Transformation.Interpolations.CUBIC),
                                    new Keyframe(5.25F,
                                            AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F),
                                            Transformation.Interpolations.CUBIC)
                            }))

            .build();

}
