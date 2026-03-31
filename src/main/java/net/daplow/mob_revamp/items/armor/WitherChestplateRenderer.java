package net.daplow.mob_revamp.items.armor;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.ArmorRenderer;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.model.Dilation;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec3f;

import static net.daplow.mob_revamp.DaplowMain.MOD_ID;

@Environment(EnvType.CLIENT)
public class WitherChestplateRenderer implements ArmorRenderer {

    private static final Identifier TEXTURE = new Identifier(MOD_ID, "textures/models/armor/wither_armor.png");



    @Override
    public void render(MatrixStack matrix, VertexConsumerProvider vertexConsumerProvider, ItemStack itemStack, LivingEntity livingEntity, EquipmentSlot equipmentSlot, int light, BipedEntityModel<LivingEntity> contextModel) {

        if (equipmentSlot != EquipmentSlot.CHEST) return;

        matrix.push();


        //contextModel = new BipedEntityModel<>(BipedEntityModel.getModelData(new Dilation(1.0F), 0.0F).createModel());

        // Make only the chest and arms visible
        contextModel.body.visible = true;
        contextModel.leftArm.visible = true;
        contextModel.rightArm.visible = true;

        // Hide other parts
        contextModel.head.visible = false;
        contextModel.leftLeg.visible = false;
        contextModel.rightLeg.visible = false;
        // Compute diagonal texture offset
        float tick = livingEntity.age + MinecraftClient.getInstance().getTickDelta();
        float speed = 0.01f; // adjust speed here
        float uOffset = tick * speed;
        float vOffset = tick * speed;

        VertexConsumer vertexConsumer = vertexConsumerProvider.getBuffer(RenderLayer.getArmorCutoutNoCull(TEXTURE));
        matrix.translate(uOffset, vOffset, 0);
        matrix.scale(1.1f, 1.1f, 1.1f);

        // Render chest and arms
        contextModel.body.render(matrix, vertexConsumer, light, OverlayTexture.DEFAULT_UV);
        matrix.scale(1.2f, 1.2f, 1.2f);

        matrix.translate(-0.07, 0, 0);
        contextModel.leftArm.render(matrix, vertexConsumer, light, OverlayTexture.DEFAULT_UV);
        matrix.translate(0.14, 0, 0);

        contextModel.rightArm.render(matrix, vertexConsumer, light, OverlayTexture.DEFAULT_UV);


        matrix.pop();


    }


}
