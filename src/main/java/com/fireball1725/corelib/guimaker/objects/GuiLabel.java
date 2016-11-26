package com.fireball1725.corelib.guimaker.objects;

import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GuiLabel extends GuiObject {
    protected int color;
    protected String labelText;

    public GuiLabel(int x, int y, int color, String defaultLabel) {
        this.locX = x;
        this.locY = y;
        this.color = color;
        this.labelText = defaultLabel;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        super.drawGuiContainerForegroundLayer(mouseX, mouseY);

        Minecraft.getMinecraft().fontRendererObj.drawString(this.labelText, this.locX, this.locY, this.color);
    }

    public void setText(String text) {
        this.labelText = text;
    }
}
