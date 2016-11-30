package com.fireball1725.corelib.guimaker.objects;

import com.fireball1725.corelib.guimaker.GuiMaker;
import com.fireball1725.corelib.util.FontRendererExtended;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.init.SoundEvents;
import net.minecraftforge.fml.client.config.GuiUtils;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.awt.*;
import java.io.IOException;

public class GuiButton extends GuiObject {
    private String buttonText;

    public GuiButton(int buttonID, int x, int y, int w, String buttonText) {
        super(buttonID);
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = 16;
        this.buttonText = buttonText;
    }

    public GuiButton(int buttonID, int x, int y, int w, int h, String buttonText) {
        super(buttonID);
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.buttonText = buttonText;
    }

    @Override
    public void drawGuiContainerBackgroundLayer(GuiContainer guiContainer, float partialTicks, int mouseX, int mouseY) {
        if (!this.visible)
            return;

        if (!this.disabled)
            GuiUtils.drawContinuousTexturedBox(GuiMaker.resourceLocation, this.x + this.guiX, this.y + this.guiY, 0, 96, this.w, this.h, 16, 16, 2, 0);
        else
            GuiUtils.drawContinuousTexturedBox(GuiMaker.resourceLocation, this.x + this.guiX, this.y + this.guiY, 48, 96, this.w, this.h, 16, 16, 2, 0);

        if (this.selected && !this.disabled)
            GuiUtils.drawContinuousTexturedBox(GuiMaker.resourceLocation, this.x + this.guiX, this.y + this.guiY, 32, 96, this.w, this.h, 16, 16, 2, 0);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void drawGuiContainerForegroundLayer(GuiContainer guiContainer, int mouseX, int mouseY) {
        if (!this.visible)
            return;

        if (!this.disabled) {
            Rectangle r = new Rectangle(this.x + this.guiX, this.y + this.guiY, this.w, this.h);

            if (r.contains(mouseX, mouseY)) {
                GuiUtils.drawContinuousTexturedBox(GuiMaker.resourceLocation, this.x, this.y, 16, 96, this.w, this.h, 16, 16, 2, 0);
            }
        }

        FontRenderer fontRendererObj = Minecraft.getMinecraft().fontRendererObj;
        FontRendererExtended fontRendererExtendedObj = new FontRendererExtended(Minecraft.getMinecraft().fontRendererObj);

        int textY = (this.h >> 1) - (fontRendererObj.FONT_HEIGHT >> 1);
        textY += this.y;

        fontRendererExtendedObj.drawCenteredCroppedString(this.buttonText, this.x + 4, textY, this.w - 6, 0xFFFFFF);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void mouseClicked(GuiContainer guiContainer, int mouseX, int mouseY, int mouseButton) throws IOException {
        if (!this.visible || this.disabled)
            return;

        Rectangle r = new Rectangle(this.x + this.guiX, this.y + this.guiY, this.w, this.h);

        if (!r.contains(mouseX, mouseY))
            return;

        Minecraft.getMinecraft().getSoundHandler().playSound(PositionedSoundRecord.getMasterRecord(SoundEvents.UI_BUTTON_CLICK, 1.0F));
        this.guiObjectClicked();
    }
}
