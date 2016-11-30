package com.fireball1725.corelib.guimaker.objects;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraftforge.fml.client.config.GuiUtils;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import tech.flatstone.appliedlogistics.common.util.GuiHelper;

public class GuiLine extends GuiObject {
    int color;

    public GuiLine(int x, int y, int w, int h, int color) {
        super(-999);

        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.color = color;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void drawGuiContainerBackgroundLayer(GuiContainer guiContainer, float partialTicks, int mouseX, int mouseY) {
        GuiUtils.drawGradientRect(1, this.x + this.guiX, this.y + this.guiY, this.x + this.w + this.guiX, this.y + this.h + this.guiY, this.color, this.color);
    }
}
