package com.fireball1725.corelib.guimaker.objects;

import com.fireball1725.corelib.guimaker.GuiMaker;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraftforge.fml.client.config.GuiUtils;
import org.lwjgl.opengl.GL11;

import java.util.ArrayList;
import java.util.List;

public class GuiScrollBox extends GuiObject {
    private List<GuiObject> guiObjectList = new ArrayList<>();
    private int scrollY = 0;

    public GuiScrollBox(int x, int y, int w, int h) {
        super(-999);
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

    public void addGuiObject(GuiObject guiObject) {
        guiObject.updateGuiObject(this.guiMakerObj);
        guiObjectList.add(guiObject);
    }

    @Override
    public void initGui() {
        for (GuiObject guiObject : guiObjectList) {
            guiObject.updateGuiSize(this.guiX, this.guiY, this.guiW, this.guiH);
        }
    }

    @Override
    public void drawGuiContainerBackgroundLayer(GuiContainer guiContainer, float partialTicks, int mouseX, int mouseY) {
        GuiUtils.drawContinuousTexturedBox(GuiMaker.resourceLocation, this.x + this.guiX, this.y + this.guiY, 16, 128, this.w, this.h, 16, 16, 1, 0);
        GuiUtils.drawContinuousTexturedBox(GuiMaker.resourceLocation, this.x + this.guiX + this.w - 10, this.y + this.guiY, 32, 128, 10, this.h, 16, 16, 1, 0);

        GL11.glEnable(GL11.GL_STENCIL_TEST);
        GL11.glClear(GL11.GL_STENCIL_BUFFER_BIT);
        GL11.glStencilMask(0xFF);
        GL11.glStencilFunc(GL11.GL_ALWAYS, 1, 0xFF);
        GL11.glStencilOp(GL11.GL_KEEP, GL11.GL_KEEP, GL11.GL_REPLACE);
        GlStateManager.disableAlpha();

        Gui.drawRect(this.guiX + this.x + 1, this.guiY + this.y + 1, this.guiX + this.w - 2, this.guiY + this.h - 2, 0);

        GL11.glStencilMask(0x00);
        GL11.glStencilFunc(GL11.GL_NOTEQUAL, 0, 0xFF);
        //RenderHelper.enableGUIStandardItemLighting(); // todo needed?
        //GlStateManager.pushMatrix();

        for (GuiObject guiObject : guiObjectList)
            guiObject.drawGuiContainerBackgroundLayer(guiContainer, partialTicks, mouseX, mouseY);

        //GlStateManager.popMatrix();
        //RenderHelper.disableStandardItemLighting(); // todo needed?
        GL11.glDisable(GL11.GL_STENCIL_TEST);
    }
}
