package com.fireball1725.corelib.guimaker.objects;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.config.GuiUtils;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import tech.flatstone.appliedlogistics.ModInfo;

public class GuiImage extends GuiObject {
    private int u;
    private int v;
    private int uWidth;
    private int vHeight;
    private int tileWidth;
    private int tileHeight;
    private ResourceLocation resourceLocation;

    public GuiImage(ResourceLocation resourceLocation, int x, int y, int u, int v, int uWidth, int vHeight, int w, int h, int tileWidth, int tileHeight) {
        super(-999);
        this.x = x;
        this.y = y;
        this.u = u;
        this.v = v;
        this.uWidth = uWidth;
        this.vHeight = vHeight;
        this.w = w;
        this.h = h;
        this.tileWidth = tileWidth;
        this.tileHeight = tileHeight;
        this.resourceLocation = resourceLocation;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void drawGuiContainerBackgroundLayer(GuiContainer guiContainer, float partialTicks, int mouseX, int mouseY) {
        Minecraft.getMinecraft().getTextureManager().bindTexture(this.resourceLocation);
        GlStateManager.color(1, 1, 1, 1);
        Gui.drawScaledCustomSizeModalRect(this.x + this.guiX, this.y + this.guiY, this.u, this.v, this.uWidth, this.vHeight, this.w, this.h, this.tileWidth, this.tileHeight);
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setW(int w) {
        this.w = w;
    }

    public void setH(int h) {
        this.h = h;
    }

    public void setU(int u) {
        this.u = u;
    }

    public void setV(int v) {
        this.v = v;
    }

    public void setuWidth(int uWidth) {
        this.uWidth = uWidth;
    }

    public void setvHeight(int vHeight) {
        this.vHeight = vHeight;
    }

    public void setTileWidth(int tileWidth) {
        this.tileWidth = tileWidth;
    }

    public void setTileHeight(int tileHeight) {
        this.tileHeight = tileHeight;
    }
}
