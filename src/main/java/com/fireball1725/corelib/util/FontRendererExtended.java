package com.fireball1725.corelib.util;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

import java.util.List;

public class FontRendererExtended {
    FontRenderer fontRenderer;

    public FontRendererExtended(FontRenderer fontRenderer) {
        this.fontRenderer = fontRenderer;
    }

    @SideOnly(Side.CLIENT)
    public void drawCenteredCroppedString(String str, int x, int y, int width, int color) {
        fontRenderer.textColor = color;
        int strY = fontRenderer.getStringWidth(str);
        if (strY > width) {
            int ellipsisWidth = fontRenderer.getStringWidth(" ...");
            List<String> splitString = fontRenderer.listFormattedStringToWidth(str, width - ellipsisWidth);
            str = splitString.get(0) + " ...";
        }
        this.renderCenteredStringAligned(str, x, y, width, fontRenderer.textColor, 1.0f, false);
    }

    @SideOnly(Side.CLIENT)
    public void drawCenteredSplitString(String str, int x, int y, int wrapWidth, int color) {
        drawCenteredSplitString(str, x, y, wrapWidth, 1.0f, color);
    }

    @SideOnly(Side.CLIENT)
    public void drawCenteredSplitString(String str, int x, int y, int wrapWidth, float scale, int color) {
        fontRenderer.resetStyles();
        fontRenderer.textColor = color;
        str = fontRenderer.trimStringNewline(str);
        this.renderCenteredSplitString(str, x, y, wrapWidth, scale, false);
    }

    @SideOnly(Side.CLIENT)
    private void renderCenteredSplitString(String str, int x, int y, int wrapWidth, float scale, boolean addShadow) {
        wrapWidth = (int)Math.floor(wrapWidth / scale);
        x = (int)Math.floor(x / scale);
        y = (int)Math.floor(y / scale);

        GL11.glScalef(scale, scale, 1);
        for (String s : fontRenderer.listFormattedStringToWidth(str, wrapWidth)) {
            this.renderCenteredStringAligned(s, x, y, wrapWidth, fontRenderer.textColor, scale, addShadow);
            y += fontRenderer.FONT_HEIGHT;
        }
        GL11.glScalef(1/scale, 1/scale, 1);
    }

    @SideOnly(Side.CLIENT)
    private int renderCenteredStringAligned(String text, int x, int y, int width, int color, float scale, boolean dropShadow) {
        if (fontRenderer.getBidiFlag()) {
            int i = fontRenderer.getStringWidth(fontRenderer.bidiReorder(text));
            x = x + width - i;
        }

        int w = fontRenderer.getStringWidth(text);
        x = x + ((width >> 1) - (w >> 1));


        int result = fontRenderer.renderString(text, (float)x, (float)y, color, dropShadow);

        return result;
    }
}
