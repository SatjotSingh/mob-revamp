package net.daplow.mob_revamp;

import com.mojang.blaze3d.systems.RenderSystem;
import net.daplow.mob_revamp.blocks.ModBlocks;
import net.daplow.mob_revamp.effects.ModEffects;
import net.daplow.mob_revamp.items.ModItems;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.color.world.BiomeColors;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DaplowMainClient implements ClientModInitializer {
    public static final Identifier BAT_ELYTRA_TEXTURE = new Identifier("daplow-mob_revamp", "textures/entity/elytra/winged_elytra.png");

    private static final MinecraftClient client = MinecraftClient.getInstance();
    private final Random random = new Random();
    private final List<InkBlob> inkBlobs = new ArrayList<>();

    @Override
    public void onInitializeClient() {

        // Bat Mod
        ColorProviderRegistry.ITEM.register((stack, tintIndex) -> {
            if (tintIndex == 0) {
                if (stack.hasNbt()) {
                    NbtCompound display = stack.getSubNbt("display");
                    if (display != null && display.contains("color", NbtElement.INT_TYPE)) {
                        return display.getInt("color");
                    }
                }
                return -1;
            }
            return -1;
        }, ModItems.WING_SUIT);
        ModelPredicateProviderRegistry.register(
                Items.ELYTRA,
                new Identifier("batwing"),
                (stack, world, entity, seed) ->
                        stack.hasNbt() && stack.getNbt().getBoolean("BatWing") ? 1.0F : 0.0F
        );
        ColorProviderRegistry.ITEM.register((stack, tintIndex) -> {
            if (tintIndex == 0) {
                if (stack.hasNbt()) {
                    NbtCompound display = stack.getSubNbt("display");
                    if (display != null && display.contains("color", NbtElement.INT_TYPE)) {
                        return display.getInt("color");
                    }
                }
                return -1;
            }
            return -1;
        }, Items.ELYTRA);


        // Squid Mod
        HudRenderCallback.EVENT.register(this::renderInk);



        //      LEAF TINT  (1st one for world, 2nd one for inventory)


    }



    private void renderInk(MatrixStack matrices, float tickDelta) {
        PlayerEntity player = client.player;
        if (player == null) return;

        int screenWidth = client.getWindow().getScaledWidth();
        int screenHeight = client.getWindow().getScaledHeight();
        int centerX = screenWidth / 2;
        int centerY = screenHeight / 2;

        if (player.hasStatusEffect(ModEffects.SQUID_POTION)) {

            // Spawn more small blobs near crosshair
            if (inkBlobs.size() < 35) { // max 20 blobs at a time
                int offsetX = random.nextInt(300) - 150; // +/- 80 pixels
                int offsetY = random.nextInt(160) - 80;
                int size = 30 + random.nextInt(30); // small blobs: 10-25 px
                inkBlobs.add(new InkBlob(centerX + offsetX, centerY + offsetY, size));
            }

            // Render all blobs (static, fully black)
            for (InkBlob blob : inkBlobs) {
                int color = 0xFF000000; // solid black
                DrawableHelper.fill(
                        matrices,
                        blob.x - blob.size / 2,
                        blob.y - blob.size / 2,
                        blob.x + blob.size / 2,
                        blob.y + blob.size / 2,
                        color
                );
            }

        } else {
            // Clear all blobs when potion effect ends
            inkBlobs.clear();
        }
    }

    // Inner class to store blob info
    private static class InkBlob {
        int x, y;
        int size;

        InkBlob(int x, int y, int size) {
            this.x = x;
            this.y = y;
            this.size = size;
        }
    }

}
