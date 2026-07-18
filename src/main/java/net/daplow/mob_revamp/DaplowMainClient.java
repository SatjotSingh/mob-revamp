package net.daplow.mob_revamp;


import net.daplow.mob_revamp.effects.ModEffects;
import net.daplow.mob_revamp.items.ModItems;
import net.daplow.mob_revamp.items.armor.WitherChestplateRenderer;
import net.daplow.mob_revamp.util.Screen.ModScreenHandler;
import net.daplow.mob_revamp.util.Screen.PiggyScreen;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.ArmorRenderer;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.item.ModelPredicateProviderRegistry;

import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.util.Identifier;

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



        // Guardian Mod
        ColorProviderRegistry.ITEM.register((stack, tintIndex) -> {
                if (tintIndex == 1) { // stem layer
                    if (!stack.hasNbt()) return 0xFFFFFF;
                    String stem = stack.getNbt().getString("Eye_Variant");
                    if ("gold".equals(stem)) return 0xFFD700;
                    if ("iron".equals(stem)) return 0xBEBEBE;
                    if ("red".equals(stem)) return 0xFF0000;
                    if ("blue".equals(stem)) return 0x0000FF;
                    if ("green".equals(stem)) return 0x00FF00;
                    if ("yellow".equals(stem)) return 0xFFFF00;
                    if ("orange".equals(stem)) return 0xFFA500;
                    if ("purple".equals(stem)) return 0x800080;
                    return 0x000000;
                }
                return 0xFFFFFF;
        }, ModItems.GUARDIAN_EYE);


        ColorProviderRegistry.ITEM.register((stack, tintIndex) -> {
            if (tintIndex == 1) { // stem layer
                if (!stack.hasNbt()) return 0xFFFFFF;
                Boolean golded = stack.getNbt().getBoolean("gold_variant");
                if (golded) return 0xFFD700;
                String stem = stack.getNbt().getString("Eye_Variant");
                if ("gold".equals(stem)) return 0xFFD700;
                if ("iron".equals(stem)) return 0xBEBEBE;
                if ("red".equals(stem)) return 0xFF0000;
                if ("blue".equals(stem)) return 0x0000FF;
                if ("green".equals(stem)) return 0x00FF00;
                if ("yellow".equals(stem)) return 0xFFFF00;
                if ("orange".equals(stem)) return 0xFFA500;
                if ("purple".equals(stem)) return 0x800080;
                return 0x000000;
            }
            return 0xFFFFFF;
        }, ModItems.GUARDIAN_STAFF);


        // Wither Mod
        ArmorRenderer.register(
                new WitherChestplateRenderer(),
                ModItems.WITHER_CHESTPLATE
        );


        // Warden Mod
        ModelPredicateProviderRegistry.register(
                ModItems.WARDEN_HEART,
                new Identifier("pulse"),
                (stack, world, entity, seed) -> {

                    if (entity == null)
                        return 0;

                    // Goes from 0 -> 1 repeatedly
                    float num =  (entity.age % 30) / 10f;
                    if (num >2) {
                        num = 0;
                    } else if (num >1) {
                        num = 2-num;
                    }
                    return num;
                }
        );

        // Piggy Bank Item Screen
        HandledScreens.register(ModScreenHandler.CUSTOM_PIGGY_SCREEN, PiggyScreen::new);

    }



    private void renderInk(MatrixStack matrices, float tickDelta) {
        PlayerEntity player = client.player;
        if (player == null) return;

        int screenWidth = client.getWindow().getScaledWidth();
        int screenHeight = client.getWindow().getScaledHeight();
        int centerX = screenWidth / 2;
        int centerY = screenHeight / 2;

        if (player.hasStatusEffect(ModEffects.SQUID_POTION)) {
            int color = 0xFF000000; // solid black
            StatusEffectInstance instance = player.getStatusEffect(ModEffects.SQUID_POTION);
            int amplifier = instance.getAmplifier();
            if (amplifier == 1) {
                color = 0xFF00FFCC;
            }
            // Spawn more small blobs near crosshair
            if (inkBlobs.size() < 35) { // max 20 blobs at a time
                int offsetX = random.nextInt(300) - 150; // +/- 80 pixels
                int offsetY = random.nextInt(160) - 80;
                int size = 30 + random.nextInt(30); // small blobs: 10-25 px
                inkBlobs.add(new InkBlob(centerX + offsetX, centerY + offsetY, size));
            }

            // Render all blobs
            for (InkBlob blob : inkBlobs) {
                if (amplifier == 1) {
                    DrawableHelper.fill(
                            matrices,
                            blob.x - blob.size/2 - 5,
                            blob.y - blob.size/2 - 5,
                            blob.x + blob.size/2 + 5,
                            blob.y + blob.size/2 + 5,
                            0x5500FFCC
                    );
                    float time = (System.currentTimeMillis() % 1000) / 1000f; // 0.0 → 1.0 over 1 second
                    int alpha = (int)(150 + Math.sin(time * Math.PI * 2 + blob.phase) * 70); // alpha oscillates 80 → 220
                    alpha += random.nextInt(10)-5;
                    color = (alpha << 24) | 0x00FFCC; // turquoise with variable alpha

                }
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
        float phase;

        InkBlob(int x, int y, int size) {
            this.x = x;
            this.y = y;
            this.size = size;
            this.phase = (float)Math.random() * (float)Math.PI * 2; // 0 → 2π

        }
    }

}
