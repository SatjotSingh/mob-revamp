package net.daplow.mob_revamp.mixin;

import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.feature.ElytraFeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.ElytraEntityModel;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;

import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ElytraFeatureRenderer.class)
public abstract class ElytraFeatureRendererMixin<T extends LivingEntity, M extends EntityModel<T>>
        extends FeatureRenderer<T, M> {

    @Shadow @Final
    private ElytraEntityModel<T> elytra;

    private ItemStack capturedElytra;

    public ElytraFeatureRendererMixin(FeatureRendererContext<T, M> context) {
        super(context);
    }

    @ModifyVariable(
            method = "render(Lnet/minecraft/client/util/math/MatrixStack;" +
                    "Lnet/minecraft/client/render/VertexConsumerProvider;I" +
                    "Lnet/minecraft/entity/LivingEntity;FFFFFF)V",
            at = @At("STORE"),
            ordinal = 0
    )
    private ItemStack captureElytra(ItemStack stack) {
        this.capturedElytra = stack;
        return stack;
    }

    @Redirect(
            method = "render(Lnet/minecraft/client/util/math/MatrixStack;" +
                    "Lnet/minecraft/client/render/VertexConsumerProvider;I" +
                    "Lnet/minecraft/entity/LivingEntity;FFFFFF)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/render/entity/model/ElytraEntityModel;" +
                            "render(Lnet/minecraft/client/util/math/MatrixStack;" +
                            "Lnet/minecraft/client/render/VertexConsumer;IIFFFF)V"
            )
    )
    private void renderColoredElytra(
            ElytraEntityModel<T> model,
            MatrixStack matrices,
            VertexConsumer vertices,
            int light,
            int overlay,
            float red,
            float green,
            float blue,
            float alpha
    ) {
        Integer color = getElytraColorOrNull(this.capturedElytra);

        if (color != null) {
            float r = ((color >> 16) & 0xFF) / 255f;
            float g = ((color >> 8) & 0xFF) / 255f;
            float b = (color & 0xFF) / 255f;

            model.render(matrices, vertices, light, overlay, r, g, b, alpha);
        } else {
            model.render(matrices, vertices, light, overlay, red, green, blue, alpha);
        }
    }
    @Nullable
    private static Integer getElytraColorOrNull(ItemStack stack) {
        if (stack == null) return null;

        NbtCompound display = stack.getSubNbt("display");
        if (display != null && display.contains("color", NbtElement.INT_TYPE)) {
            return display.getInt("color");
        }
        return null;
    }
    private static int getElytraColor(ItemStack stack) {
        if (stack == null) return 0xFFFFFF;

        NbtCompound display = stack.getSubNbt("display");
        if (display != null && display.contains("color", NbtElement.INT_TYPE)) {
            return display.getInt("color");
        }
        return 0xFFFFFF;
    }
}
