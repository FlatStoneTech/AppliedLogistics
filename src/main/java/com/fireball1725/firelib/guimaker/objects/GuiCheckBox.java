package com.fireball1725.corelib.guimaker.objects;

import com.fireball1725.corelib.guimaker.GuiMaker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.init.SoundEvents;
import net.minecraftforge.fml.client.config.GuiUtils;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.awt.*;
import java.io.IOException;

public class GuiCheckBox extends GuiObject {
    public GuiCheckBox(int buttonID, int x, int y, boolean checked) {
        super(buttonID);
        this.x = x;
        this.y = y;
        this.selected = checked;
    }

    @Override
    public void drawGuiContainerBackgroundLayer(GuiContainer guiContainer, float partialTicks, int mouseX, int mouseY) {
        if (!this.visible)
            return;
        
        Point p = this.getWindowXY(true);

        GuiUtils.drawContinuousTexturedBox(GuiMaker.resourceLocation, p.x, p.y, 0, 64, 11, 11, 11, 11, 1, 0);

        if (disabled && selected)
            GuiUtils.drawContinuousTexturedBox(GuiMaker.resourceLocation, p.x, p.y, 48, 64, 11, 11, 11, 11, 1, 0);

        if (!disabled && selected)
            GuiUtils.drawContinuousTexturedBox(GuiMaker.resourceLocation, p.x, p.y, 32, 64, 11, 11, 11, 11, 1, 0);
    }

    @Override
    public void drawGuiContainerForegroundLayer(GuiContainer guiContainer, int mouseX, int mouseY) {
        if (!this.visible || disabled)
            return;
        
        Point p = this.getWindowXY(false);

        Rectangle r = new Rectangle(p.x + this.guiX, p.y + this.guiY, 11, 11);

        if (r.contains(mouseX, mouseY)) {
            GuiUtils.drawContinuousTexturedBox(GuiMaker.resourceLocation, p.x, p.y, 16, 64, 11, 11, 11, 11, 1, 0);
        }

        if (selected) {
            GuiUtils.drawContinuousTexturedBox(GuiMaker.resourceLocation, p.x, p.y, 32, 64, 11, 11, 11, 11, 1, 0);
        }
    }

    @Override
    public void drawScreen(GuiContainer guiContainer, int mouseX, int mouseY, float partialTicks, float zLevel) {

    }

    @SideOnly(Side.CLIENT)
    @Override
    public void mouseClicked(GuiContainer guiContainer, int mouseX, int mouseY, int mouseButton) throws IOException {
        if (!this.visible || disabled)
            return;

        Point p = this.getWindowXY(true);

        Rectangle r = new Rectangle(p.x, p.y, 11, 11);

        if (!r.contains(mouseX, mouseY))
            return;

        Minecraft.getMinecraft().getSoundHandler().playSound(PositionedSoundRecord.getMasterRecord(SoundEvents.UI_BUTTON_CLICK, 1.0F));
        this.guiObjectClicked();
    }
}
