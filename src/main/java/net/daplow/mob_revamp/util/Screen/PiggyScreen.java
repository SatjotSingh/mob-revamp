package net.daplow.mob_revamp.util.Screen;

import com.mojang.blaze3d.systems.RenderSystem;
import net.daplow.mob_revamp.items.custom.PiggyBankItem;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.slot.Slot;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import static net.daplow.mob_revamp.DaplowMain.MOD_ID;

public class PiggyScreen extends HandledScreen<PiggyScreenHandler> {


    private static final Identifier TEXTURE =
            new Identifier(MOD_ID, "textures/gui/piggy_bank_gui_with_texture.png");
    private static final Identifier LOCKED_ICON =
            new Identifier(MOD_ID, "textures/gui/locked_icon.png");


    public PiggyScreen(PiggyScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
        this.backgroundWidth = 176;
        this.backgroundHeight = 133;
        this.playerInventoryTitleY = 1000;
    }

    @Override
    protected void drawBackground(MatrixStack matrices, float delta, int mouseX, int mouseY) {
        this.client.getTextureManager().bindTexture(TEXTURE);

        RenderSystem.setShaderTexture(0, TEXTURE);
        DrawableHelper.drawTexture(matrices, this.x, this.y, 0, 0, backgroundWidth, backgroundHeight, 256, 256);
/*
        drawTexture(
                matrices,
                x + 81,
                y + 19 + (18),
                176,
                18,
                16,
                16
        );

 */
    }

    @Override
    protected void drawForeground(MatrixStack matrices, int mouseX, int mouseY) {
        super.drawForeground(matrices, mouseX, mouseY);

    }

    private void drawLockedIcon(MatrixStack matrices) {
        this.client.getTextureManager().bindTexture(LOCKED_ICON);

        RenderSystem.defaultBlendFunc();
        RenderSystem.enableBlend();
        RenderSystem.setShaderTexture(0, LOCKED_ICON);

        int guiX = ((width - backgroundWidth) / 2);
        int guiY = ((height - backgroundHeight) / 2);
        int slot = handler.getPiggySlot();
        if (slot == -1) return;

        DrawableHelper.drawTexture(
                matrices,
                this.x + 8 +(slot*18),
                this.y + 109,
                0,
                0,
                16,
                16,
                16,
                16
        );


        RenderSystem.disableBlend();
    }

    @Override
    protected void drawMouseoverTooltip(MatrixStack matrices, int x, int y) {
        int lockedSlot = handler.getPiggySlot();
        Slot slot = this.focusedSlot;
        if (slot != null && slot.id-34 == lockedSlot) {
            return;
        }

        super.drawMouseoverTooltip(matrices, x, y);
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        renderBackground(matrices);

        super.render(matrices, mouseX, mouseY, delta);

        drawLockedIcon(matrices);

        drawMouseoverTooltip(matrices, mouseX, mouseY);

    }


}
