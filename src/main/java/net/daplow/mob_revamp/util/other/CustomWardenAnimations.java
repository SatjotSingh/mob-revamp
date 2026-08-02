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

            .addBoneAnimation("body",
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







    public static final Animation FLOP_SMASH = Animation.Builder.create(1.79167F)

            .addBoneAnimation(
                    "head",
                    new Transformation(
                            Targets.ROTATE,

                            new Keyframe(
                                    0.0F,
                                    AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F),
                                    Interpolations.LINEAR
                            ),

                            new Keyframe(
                                    0.20833F,
                                    AnimationHelper.createRotationalVector(-12.5F, 0.0F, 0.0F),
                                    Interpolations.LINEAR
                            ),

                            new Keyframe(
                                    0.5F,
                                    AnimationHelper.createRotationalVector(-11.56F, 0.0F, 0.0F),
                                    Interpolations.LINEAR
                            ),

                            new Keyframe(
                                    0.83333F,
                                    AnimationHelper.createRotationalVector(18.35F, 0.0F, 0.0F),
                                    Interpolations.LINEAR
                            ),

                            new Keyframe(
                                    1.04167F,
                                    AnimationHelper.createRotationalVector(17.23F, 0.0F, 0.0F),
                                    Interpolations.LINEAR
                            ),

                            new Keyframe(
                                    1.5F,
                                    AnimationHelper.createRotationalVector(-21.31F, 0.0F, 0.0F),
                                    Interpolations.LINEAR
                            ),

                            new Keyframe(
                                    1.79167F,
                                    AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F),
                                    Interpolations.LINEAR
                            )
                    )
            )    .addBoneAnimation(
                    "body",
                    new Transformation(
                            Targets.ROTATE,

                            new Keyframe(
                                    0.0F,
                                    AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F),
                                    Interpolations.LINEAR
                            ),

                            new Keyframe(
                                    0.29167F,
                                    AnimationHelper.createRotationalVector(-17.5F, 0.0F, 0.0F),
                                    Interpolations.LINEAR
                            ),

                            new Keyframe(
                                    1.04167F,
                                    AnimationHelper.createRotationalVector(35.0F, 0.0F, 0.0F),
                                    Interpolations.LINEAR
                            ),

                            new Keyframe(
                                    1.25F,
                                    AnimationHelper.createRotationalVector(-87.5F, 0.0F, 0.0F),
                                    Interpolations.LINEAR
                            ),

                            new Keyframe(
                                    1.45833F,
                                    AnimationHelper.createRotationalVector(-87.5F, 0.0F, 0.0F),
                                    Interpolations.LINEAR
                            ),

                            new Keyframe(
                                    1.79167F,
                                    AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F),
                                    Interpolations.LINEAR
                            )
                    )
            )    .addBoneAnimation(
                    "left_arm",
                    new Transformation(
                            Targets.ROTATE,

                            new Keyframe(
                                    0.0F,
                                    AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F),
                                    Interpolations.LINEAR
                            ),

                            new Keyframe(
                                    0.20833F,
                                    AnimationHelper.createRotationalVector(-15.3013F, -2.6568F, -8.7221F),
                                    Interpolations.LINEAR
                            ),

                            new Keyframe(
                                    0.5F,
                                    AnimationHelper.createRotationalVector(-1.6343F, -6.4883F, -23.2739F),
                                    Interpolations.LINEAR
                            ),

                            new Keyframe(
                                    1.0F,
                                    AnimationHelper.createRotationalVector(76.9684F, 48.821F, -95.5492F),
                                    Interpolations.LINEAR
                            ),

                            new Keyframe(
                                    1.25F,
                                    AnimationHelper.createRotationalVector(123.9333F, 7.3448F, -2.2777F),
                                    Interpolations.LINEAR
                            ),

                            new Keyframe(
                                    1.375F,
                                    AnimationHelper.createRotationalVector(133.2777F, 6.1207F, -1.8981F),
                                    Interpolations.LINEAR
                            ),

                            new Keyframe(
                                    1.79167F,
                                    AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F),
                                    Interpolations.LINEAR
                            )
                    )
            )     .addBoneAnimation(
                    "right_arm",
                    new Transformation(
                            Targets.ROTATE,

                            new Keyframe(
                                    0.0F,
                                    AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F),
                                    Interpolations.LINEAR
                            ),

                            new Keyframe(
                                    0.20833F,
                                    AnimationHelper.createRotationalVector(-15.3013F, 2.6568F, 8.7221F),
                                    Interpolations.LINEAR
                            ),

                            new Keyframe(
                                    0.5F,
                                    AnimationHelper.createRotationalVector(-1.6343F, 6.4883F, 23.2739F),
                                    Interpolations.LINEAR
                            ),

                            new Keyframe(
                                    1.0F,
                                    AnimationHelper.createRotationalVector(76.9684F, -48.821F, 95.5492F),
                                    Interpolations.LINEAR
                            ),

                            new Keyframe(
                                    1.25F,
                                    AnimationHelper.createRotationalVector(123.9333F, -7.3448F, 2.2777F),
                                    Interpolations.LINEAR
                            ),

                            new Keyframe(
                                    1.375F,
                                    AnimationHelper.createRotationalVector(133.2777F, -6.1207F, 1.8981F),
                                    Interpolations.LINEAR
                            ),

                            new Keyframe(
                                    1.79167F,
                                    AnimationHelper.createRotationalVector(0.0F, 0.0F, 0.0F),
                                    Interpolations.LINEAR
                            )
                    )
            ).build();


    public static final Animation NEW_SMASH = Animation.Builder.create(1.7917F)

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

            .addBoneAnimation("body",
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
                            }))        .addBoneAnimation("left_arm",
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
                                            AnimationHelper.createRotationalVector(-76.9684F, -48.821F, -95.5492F),
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
                                            AnimationHelper.createRotationalVector(-76.9684F, 48.821F, 95.5492F),
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
                            }))        .addBoneAnimation("right_leg",
                    new Transformation(
                            Transformation.Targets.SCALE,
                            new Keyframe[]{
                                    new Keyframe(0.2917F,
                                            AnimationHelper.createScalingVector(1F, 1F, 1F),
                                            Transformation.Interpolations.LINEAR)
                            }))

            .build();

}
