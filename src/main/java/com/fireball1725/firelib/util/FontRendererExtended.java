package com.fireball1725.firelib.util;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

import java.util.List;

public class FontRendererExtended extends FontRenderer {
    public FontRendererExtended(GameSettings gameSettingsIn, ResourceLocation location, TextureManager textureManagerIn, boolean unicode) {
        super(gameSettingsIn, location, textureManagerIn, unicode);
    }

    @SideOnly(Side.CLIENT)
    public void drawCenteredCroppedString(String str, int x, int y, int width, int color) {
        this.textColor = color;
        int strY = this.getStringWidth(str);
        if (strY > width) {
            int ellipsisWidth = this.getStringWidth(" ...");
            List<String> splitString = this.listFormattedStringToWidth(str, width - ellipsisWidth);
            str = splitString.get(0) + " ...";
        }
        this.renderCenteredStringAligned(str, x, y, width, this.textColor, 1.0f, false);
    }

    @SideOnly(Side.CLIENT)
    public void drawCenteredSplitString(String str, int x, int y, int wrapWidth, int color) {
        drawCenteredSplitString(str, x, y, wrapWidth, 1.0f, color);
    }

    @SideOnly(Side.CLIENT)
    public void drawCenteredSplitString(String str, int x, int y, int wrapWidth, float scale, int color) {
        enableAlpha();
        this.resetStyles();
        this.textColor = color;
        str = this.trimStringNewline(str);
        this.renderCenteredSplitString(str, x, y, wrapWidth, scale, false);
    }

    @SideOnly(Side.CLIENT)
    private void renderCenteredSplitString(String str, int x, int y, int wrapWidth, float scale, boolean addShadow) {
        wrapWidth = (int)Math.floor(wrapWidth / scale);
        x = (int)Math.floor(x / scale);
        y = (int)Math.floor(y / scale);

        GL11.glScalef(scale, scale, 1);
        for (String s : this.listFormattedStringToWidth(str, wrapWidth)) {
            this.renderCenteredStringAligned(s, x, y, wrapWidth, this.textColor, scale, addShadow);
            y += this.FONT_HEIGHT;
        }
        GL11.glScalef(1/scale, 1/scale, 1);
    }

    @SideOnly(Side.CLIENT)
    private int renderCenteredStringAligned(String text, int x, int y, int width, int color, float scale, boolean dropShadow) {
        if (this.getBidiFlag()) {
            int i = this.getStringWidth(this.bidiReorder(text));
            x = x + width - i;
        }

        int w = this.getStringWidth(text);
        x = x + ((width >> 1) - (w >> 1));


        int result = this.renderString(text, (float)x, (float)y, color, dropShadow);

        return result;
    }
}
