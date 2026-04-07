package net.daplow.mob_revamp.items.armor;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.ArmorRenderer;
import net.minecraft.client.model.*;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.decoration.ArmorStandEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static net.daplow.mob_revamp.DaplowMain.MOD_ID;

@Environment(EnvType.CLIENT)
public class WitherChestplateRenderer implements ArmorRenderer {

    private static final Identifier FRONT_TEXTURE = new Identifier(MOD_ID, "textures/models/armor/wither_armor_front.png");
    private static final Identifier WITHER_TEXTURE = new Identifier(MOD_ID, "textures/models/armor/wither_armor_glow.png");
    private static final Identifier ARMOR_STAND_FRONT_TEXTURE = new Identifier(MOD_ID, "textures/models/armor/armor_stand_wither_front.png");

    private static final Identifier WHITE_TEXTURE = new Identifier(MOD_ID, "textures/models/armor/wither_white_image.png");


    private final ModelPart armorStandBody; //temp
    private final ModelPart armorStandLeftArm;
    private final ModelPart armorStandRightArm;

    public WitherChestplateRenderer() {

        float chestPivotY = -9.0F;

        // Armor Stand BODY
        List<ModelPart.Cuboid> bodyCuboids = new ArrayList<>();
        bodyCuboids.add(new ModelPart.Cuboid(
                0, 0,
                -4.0F, 0.0F, -2.0F,
                8.0F, 12.0F, 4.0F,
                0.9F, 0.5F, 0.9F,
                false,
                64.0F, 32.0F
        ));
        this.armorStandBody = new ModelPart(bodyCuboids, new HashMap<>());
        this.armorStandBody.setPivot(0.0F, chestPivotY, 0.0F); // pivot at chest height

        // Armor Stand LEFT ARM
        List<ModelPart.Cuboid> leftArmCuboids = new ArrayList<>();
        leftArmCuboids.add(new ModelPart.Cuboid(
                24, 0,
                -1.0F, -2.0F, -1.0F,
                3F, 12.0F, 3F,
                0.5F, 0.5F, 0.5F,
                false,
                64.0F, 32.0F
        ));

        this.armorStandLeftArm = new ModelPart(leftArmCuboids, new HashMap<>());
        this.armorStandLeftArm.setPivot(6.5F, chestPivotY+2, 0.0F);
        this.armorStandLeftArm.pitch = -0.17F; // tilt outward
        this.armorStandLeftArm.roll  = -0.17F; // tilt outward

        // Armor Stand RIGHT ARM
        List<ModelPart.Cuboid> rightArmCuboids = new ArrayList<>();
        rightArmCuboids.add(new ModelPart.Cuboid(
                32, 0,
                -1.0F, -2.0F, -1.0F,
                3F, 12.0F, 3F,
                0.5F, 0.5F, 0.5F,
                false,
                64.0F, 32.0F
        ));
        this.armorStandRightArm = new ModelPart(rightArmCuboids, new HashMap<>());
        this.armorStandRightArm.setPivot(-7F, chestPivotY+2, 0.0F);
        this.armorStandRightArm.pitch = -0.17F; // tilt outward
        this.armorStandRightArm.roll  = 0.17F; // tilt outward
    }



    @Override
    public void render(
            MatrixStack matrix, VertexConsumerProvider vertexConsumerProvider,
            ItemStack itemStack,
            LivingEntity livingEntity,
            EquipmentSlot equipmentSlot,
            int light,
            BipedEntityModel<LivingEntity> contextModel) {

        if (equipmentSlot != EquipmentSlot.CHEST) return;

        matrix.push();

        boolean isChild = livingEntity.isBaby();
        boolean isArmorStand = livingEntity instanceof ArmorStandEntity;

        float f = livingEntity.age;

        float scale = 0.5f;

        if (livingEntity instanceof ArmorStandEntity) {

            VertexConsumer glint_layer = ItemRenderer.getArmorGlintConsumer(
                    vertexConsumerProvider,
                    RenderLayer.getArmorCutoutNoCull(WITHER_TEXTURE),
                    false,
                    itemStack.hasGlint()
            );


            // Glow layer
            VertexConsumer glow_layer_1 = ItemRenderer.getDirectItemGlintConsumer(
                    vertexConsumerProvider,
                    RenderLayer.getEnergySwirl(
                            WITHER_TEXTURE,
                            (float)Math.cos(f * 0.02f) * 3.0f  % 1.0f,
                            f * 0.01f % 1.0f
                    ),
                    false,
                    itemStack.hasGlint()
            );
            VertexConsumer glow_layer_2 = ItemRenderer.getDirectItemGlintConsumer(
                    vertexConsumerProvider,
                    RenderLayer.getEnergySwirl(
                            WITHER_TEXTURE,
                            ((float)Math.cos(f * 0.02f) * 3.0f) + 0.5f % 1.0f,
                            (f * 0.01f) % 1.0f
                    ),
                    false,
                    itemStack.hasGlint()
            );

            matrix.push();
            matrix.translate(0, 0.5, 0);


            armorStandBody.render(matrix, glow_layer_1, light, OverlayTexture.DEFAULT_UV, 0.4f, 0.4f, 0.4f, 1f);
            armorStandLeftArm.render(matrix, glow_layer_1, light, OverlayTexture.DEFAULT_UV, 0.4f, 0.4f, 0.4f, 1f);
            armorStandRightArm.render(matrix, glow_layer_1, light, OverlayTexture.DEFAULT_UV, 0.4f, 0.4f, 0.4f, 1f);

            armorStandBody.render(matrix, glow_layer_2, light, OverlayTexture.DEFAULT_UV, 0.4f, 0.4f, 0.4f, 1f);
            armorStandLeftArm.render(matrix, glow_layer_2, light, OverlayTexture.DEFAULT_UV, 0.4f, 0.4f, 0.4f, 1f);
            armorStandRightArm.render(matrix, glow_layer_2, light, OverlayTexture.DEFAULT_UV, 0.4f, 0.4f, 0.4f, 1f);

            //armorStandBody.render(matrix, glint_layer, light, OverlayTexture.DEFAULT_UV);
            //armorStandLeftArm.render(matrix, glint_layer, light, OverlayTexture.DEFAULT_UV);
            //armorStandRightArm.render(matrix, glint_layer, light, OverlayTexture.DEFAULT_UV);

            matrix.pop();

            VertexConsumer front_layer = ItemRenderer.getDirectItemGlintConsumer(
                    vertexConsumerProvider,
                    RenderLayer.getEnergySwirl(ARMOR_STAND_FRONT_TEXTURE, 0, 0), true, false
            );
            matrix.push();

            matrix.translate(0.0F, 0.52F, 0.0F);
            float pulse = 1.15f + 0.025f * (float)Math.sin(f * 0.2f);
            matrix.scale(pulse, pulse, pulse);
            armorStandBody.render(
                    matrix,
                    front_layer,
                    light,
                    OverlayTexture.DEFAULT_UV,
                    0.8f, 0.8f, 0.8f, 0.8f // alpha controls intensity
            );
            matrix.pop();


            VertexConsumer white_layer = ItemRenderer.getDirectItemGlintConsumer(
                    vertexConsumerProvider,
                    RenderLayer.getEnergySwirl(
                            WHITE_TEXTURE,
                            0,
                            0
                    ),
                    false,
                    itemStack.hasGlint()
            );




            matrix.push();
            matrix.translate(0.0F, 0.52F, 0.0F);
            armorStandBody.render(matrix, white_layer, light, OverlayTexture.DEFAULT_UV, 0.05f, 0.05f, 0.05f, 1f);
            armorStandLeftArm.render(matrix, white_layer, light, OverlayTexture.DEFAULT_UV, 0.05f, 0.05f, 0.05f, 1f);
            armorStandRightArm.render(matrix, white_layer, light, OverlayTexture.DEFAULT_UV, 0.05f, 0.05f, 0.05f, 1f);

            matrix.pop();




            matrix.pop();
            return;
        }

        matrix.push();
        matrix.scale(1.2f, 1.2f, 1.2f);
        // Render chest and arms
        //contextModel.body.render(matrix, base, light, OverlayTexture.DEFAULT_UV);


        matrix.pop();



        VertexConsumer glint_layer = ItemRenderer.getArmorGlintConsumer(
                vertexConsumerProvider,
                RenderLayer.getArmorCutoutNoCull(WITHER_TEXTURE),
                false,
                itemStack.hasGlint()
        );


        VertexConsumer glow_layer_1 = ItemRenderer.getDirectItemGlintConsumer(
                vertexConsumerProvider,
                RenderLayer.getEnergySwirl(
                        WITHER_TEXTURE,
                        (float)Math.cos(f * 0.02f) * 3.0f % 1.0f,
                        f * 0.01f % 1.0f
                ),
                false,
                itemStack.hasGlint()
        );




        matrix.push();
        if (isChild) {
            matrix.scale(scale, scale, scale);
            matrix.translate(0, 1.5, 0);
        } else {
            matrix.scale(1.1f, 1.1f, 1.1f);
        }

        // Render again with darker tint (like Wither)



        matrix.scale(1.1f, 1f, 1.1f);
        matrix.translate(-0.05*scale, 0, 0);  // more negative mean more inward.
        contextModel.leftArm.render(matrix, glow_layer_1, light, OverlayTexture.DEFAULT_UV, 0.5f, 0.5f, 0.5f, 1f);

        matrix.translate(0.1*scale, 0, 0);
        contextModel.rightArm.render(matrix, glow_layer_1, light, OverlayTexture.DEFAULT_UV, 0.5f, 0.5f, 0.5f, 1f);

        matrix.pop();









        VertexConsumer glow_layer_2 = ItemRenderer.getDirectItemGlintConsumer(
                vertexConsumerProvider,
                RenderLayer.getEnergySwirl(
                        WITHER_TEXTURE,
                        ((float)Math.cos(f * 0.02f) * 3.0f) +0.5f % 1.0f,
                        (f * 0.01f) % 1.0f
                ),
                false,
                false
        );


        matrix.push();
        if (isChild) {
            matrix.scale(scale, scale, scale);
            matrix.translate(0, 1.5, 0);
        } else {
            matrix.scale(1.1f, 1.1f, 1.1f);
        }
        //matrix.translate(0, -1, 0);

        matrix.scale(1.2f, 0.9f, 1.2f);
        contextModel.body.render(matrix, glow_layer_1, light, OverlayTexture.DEFAULT_UV, 0.5f, 0.5f, 0.5f, 1f);

        contextModel.body.render(matrix, glow_layer_2, light, OverlayTexture.DEFAULT_UV, 0.5f, 0.5f, 0.5f, 1f);


        matrix.pop();

        matrix.push();
        if (isChild) {
            matrix.scale(scale, scale, scale);
            matrix.translate(0, 1.5, 0);
        } else {
            matrix.scale(1.1f, 1.1f, 1.1f);
        }

        // Render again with darker tint (like Wither)


        matrix.scale(1.1f, 1f, 1.1f);
        matrix.translate(-0.05*scale, 0, 0);  // more negative mean more inward.
        contextModel.leftArm.render(matrix, glow_layer_2, light, OverlayTexture.DEFAULT_UV, 0.5f, 0.5f, 0.5f, 1f);

        matrix.translate(0.1*scale, 0, 0);
        contextModel.rightArm.render(matrix, glow_layer_2, light, OverlayTexture.DEFAULT_UV, 0.5f, 0.5f, 0.5f, 1f);



        matrix.pop();

        VertexConsumer front_layer = ItemRenderer.getDirectItemGlintConsumer(
                vertexConsumerProvider,
                RenderLayer.getEnergySwirl(FRONT_TEXTURE, 0, 0), false, itemStack.hasGlint()
        );
        matrix.push();
        if (isChild) {
            matrix.scale(scale, scale, scale);
            matrix.translate(0, 1.5, 0);
        } else {
            matrix.scale(1.1f, 1.1f, 1.1f);
        }
        matrix.translate(0.0F, 0.0F, -0.02F);
        float pulse = 1.15f + 0.025f * (float)Math.sin(f * 0.2f);
        matrix.scale(pulse, pulse, pulse);
        contextModel.body.render(
                matrix,
                front_layer,
                light,
                OverlayTexture.DEFAULT_UV,
                1f, 1f, 1f, 0.8f // alpha controls intensity
        );
        matrix.pop();


        VertexConsumer white_layer = ItemRenderer.getDirectItemGlintConsumer(
                vertexConsumerProvider,
                RenderLayer.getEnergySwirl(
                        WHITE_TEXTURE,
                        0,
                        0
                ),
                false,
                itemStack.hasGlint()
        );




        matrix.push();
        if (isChild) {
            matrix.scale(scale, scale, scale);
            matrix.translate(0, 1.5, 0);
        } else {
            matrix.scale(1.1f, 1.1f, 1.1f);
        }

        matrix.scale(1.0f, 1f, 1.0f);
        matrix.translate(-0.05*scale, 0, 0);  // more negative mean more inward.
        contextModel.leftArm.render(matrix, white_layer, light, OverlayTexture.DEFAULT_UV, 0.2f, 0.2f, 0.2f, 1f);

        matrix.translate(0.1*scale, 0, 0);
        contextModel.rightArm.render(matrix, white_layer, light, OverlayTexture.DEFAULT_UV, 0.2f, 0.2f, 0.2f, 1f);
        matrix.pop();



        matrix.push();
        if (isChild) {
            matrix.scale(scale, scale, scale);
            matrix.translate(0, 1.5, 0);
        } else {
            matrix.scale(1.1f, 1.1f, 1.1f);
        }

        // Render again with darker tint (like Wither)

        matrix.scale(1.2f, 0.9f, 1.2f);
        contextModel.body.render(matrix, white_layer, light, OverlayTexture.DEFAULT_UV, 0.2f, 0.2f, 0.2f, 1f);
        matrix.pop();


        matrix.pop();


    }


}
