package com.fireball1725.corelib.guimaker.objects;

import com.fireball1725.corelib.truetyper.Test;
import mcp.mobius.waila.overlay.DisplayUtil;
import mcp.mobius.waila.overlay.OverlayConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

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
        int scaledX = (int)Math.floor(this.x / this.scale);
        int scaledY = (int)Math.floor(this.y / this.scale);

        GL11.glScalef(this.scale, this.scale, 1);
        Minecraft.getMinecraft().fontRendererObj.drawString(this.labelText, scaledX, scaledY, this.color);
        GL11.glScalef(1/this.scale, 1/this.scale, 1);
    }

    public void setText(String text) {
        this.labelText = text;
    }
}
