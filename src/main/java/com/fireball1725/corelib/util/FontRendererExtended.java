package com.fireball1725.corelib.util;

import net.minecraft.client.gui.FontRenderer;

public class FontRendererExtended {
    FontRenderer fontRenderer;

    public FontRendererExtended(FontRenderer fontRenderer) {
        this.fontRenderer = fontRenderer;
    }

    public void drawCenteredSplitString(String str, int x, int y, int wrapWidth, int color) {
        fontRenderer.resetStyles();
        fontRenderer.textColor = color;
        str = fontRenderer.trimStringNewline(str);
        this.renderCenteredSplitString(str, x, y, wrapWidth, false);
    }

    private void renderCenteredSplitString(String str, int x, int y, int wrapWidth, boolean addShadow) {
        for (String s : fontRenderer.listFormattedStringToWidth(str, wrapWidth)) {
            this.renderCenteredStringAligned(s, x, y, wrapWidth, fontRenderer.textColor, addShadow);
            y += fontRenderer.FONT_HEIGHT;
        }
    }

    private int renderCenteredStringAligned(String text, int x, int y, int width, int color, boolean dropShadow) {
        if (fontRenderer.getBidiFlag()) {
            int i = fontRenderer.getStringWidth(fontRenderer.bidiReorder(text));
            x = x + width - i;
        }

        int w = fontRenderer.getStringWidth(text);
        x = x + ((width >> 1) - (w >> 1));

        return fontRenderer.renderString(text, (float)x, (float)y, color, dropShadow);
    }
}
