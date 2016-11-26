package com.fireball1725.corelib.guimaker.objects;

import com.fireball1725.corelib.util.FontRendererExtended;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GuiCenteredLabel extends GuiObject {
    protected int color;
    protected String labelText;

    public GuiCenteredLabel(int x, int y, int w, int color, String defaultLabel) {
        this.locX = x;
        this.locY = y;
        this.width = w;
        this.color = color;
        this.labelText = defaultLabel;
    }

    public GuiCenteredLabel(int x, int y, int w, int color) {
        this.locX = x;
        this.locY = y;
        this.width = w;
        this.color = color;
        this.labelText = "";
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        super.drawGuiContainerForegroundLayer(mouseX, mouseY);

        FontRendererExtended fontRendererExtended = new FontRendererExtended(Minecraft.getMinecraft().fontRendererObj);

        fontRendererExtended.drawCenteredSplitString(this.labelText, this.locX, this.locY, this.width, this.color);
    }

    public void setText(String text) {
        this.labelText = text;
    }
}
