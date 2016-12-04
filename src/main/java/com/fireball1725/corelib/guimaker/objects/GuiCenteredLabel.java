package com.fireball1725.corelib.guimaker.objects;

import com.fireball1725.corelib.FireBallCoreLibrary;
import com.fireball1725.corelib.util.FontRendererExtended;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.awt.*;

public class GuiCenteredLabel extends GuiObject {
    protected int color;
    protected String labelText;
    protected float scale;

    public GuiCenteredLabel(int x, int y, int w, int color, String defaultLabel) {
        super(-999);
        this.x = x;
        this.y = y;
        this.w = w;
        this.color = color;
        this.labelText = defaultLabel;
        this.scale = 1.0f;
    }

    public GuiCenteredLabel(int x, int y, int w, int color) {
        super(-999);
        this.x = x;
        this.y = y;
        this.w = w;
        this.color = color;
        this.labelText = "";
        this.scale = 1.0f;
    }

    public GuiCenteredLabel(int x, int y, int w, int color, float scale) {
        super(-999);
        this.x = x;
        this.y = y;
        this.w = w;
        this.color = color;
        this.labelText = "";
        this.scale = scale;
    }

    public GuiCenteredLabel(int x, int y, int w, int color, float scale, String defaultLabel) {
        super(-999);
        this.x = x;
        this.y = y;
        this.w = w;
        this.color = color;
        this.labelText = defaultLabel;
        this.scale = scale;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void drawGuiContainerForegroundLayer(GuiContainer guiContainer, int mouseX, int mouseY) {
        Point p = this.getWindowXY(false);

        FontRendererExtended fontRendererExtended = FireBallCoreLibrary.instance.fontRendererExtendedObj;

        fontRendererExtended.drawCenteredSplitString(this.labelText, p.x, p.y, this.w, this.scale, this.color);
    }

    public void setText(String text) {
        this.labelText = text;
    }
}
