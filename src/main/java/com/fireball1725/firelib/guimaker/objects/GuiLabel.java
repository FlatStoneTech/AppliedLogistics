package com.fireball1725.firelib.guimaker.objects;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

import java.awt.*;

public class GuiLabel extends GuiObject {
    protected int color;
    protected String labelText;
    protected float scale;

    public GuiLabel(int x, int y, int color, String defaultLabel) {
        super(-999);
        this.x = x;
        this.y = y;
        this.color = color;
        this.labelText = defaultLabel;
        this.scale = 1.0f;
    }

    public GuiLabel(int x, int y, int color, float scale, String defaultLabel) {
        super(-999);
        this.x = x;
        this.y = y;
        this.color = color;
        this.labelText = defaultLabel;
        this.scale = scale;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void drawGuiContainerForegroundLayer(GuiContainer guiContainer, int mouseX, int mouseY) {
        Point p = this.getWindowXY(false);

        int scaledX = (int)Math.floor(p.getX() / this.scale);
        int scaledY = (int)Math.floor(p.getY() / this.scale);

        GL11.glScalef(this.scale, this.scale, 1);
        Minecraft.getMinecraft().fontRendererObj.drawString(this.labelText, scaledX, scaledY, this.color);
        GL11.glScalef(1/this.scale, 1/this.scale, 1);
    }

    public void setText(String text) {
        this.labelText = text;
    }
}
