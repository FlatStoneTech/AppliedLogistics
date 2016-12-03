package com.fireball1725.corelib.guimaker.objects;

import com.fireball1725.corelib.guimaker.GuiMaker;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.client.config.GuiUtils;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;
import sun.java2d.loops.DrawRect;
import tech.flatstone.appliedlogistics.common.util.LogHelper;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GuiScrollBox extends GuiObject {
    private List<GuiObject> guiObjectList = new ArrayList<>();
    private int maxScrollY = 0;
    private int offsetScrollY = 0;

    public GuiScrollBox(int x, int y, int w, int h) {
        super(-999);
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.maxScrollY = h;
    }

    public void setMaxScrollY(int maxScrollY) {
        if (maxScrollY < h)
            maxScrollY = h;

        this.maxScrollY = maxScrollY;
    }

    public void addGuiObject(GuiObject guiObject) {
        guiObject.updateGuiObject(this.guiMakerObj);
        guiObjectList.add(guiObject);
    }

    @Override
    public void initGui() {
        for (GuiObject guiObject : guiObjectList) {
            guiObject.updateGuiSize(this.guiX + this.x, this.guiY + this.y - this.offsetScrollY, this.w, this.h);
        }
    }

    @Override
    public void drawScreen(GuiContainer guiContainer, int mouseX, int mouseY, float partialTicks, float zLevel) {
        for (GuiObject guiObject : guiObjectList) {
            guiObject.updateGuiSize(this.guiX + this.x, this.guiY + this.y - this.offsetScrollY, this.w, this.h);
            guiObject.updateMouse(this.mouseX, this.mouseY);
        }

        for (GuiObject guiObject : guiObjectList)
            guiObject.drawScreen(guiContainer, mouseX, mouseY, partialTicks, zLevel);
    }

    private int getScrollBarHeight() {
        int k1 = (this.h) * (this.h) / this.maxScrollY;
        k1 = MathHelper.clamp_int(k1, 16, this.h - 2);

        return k1;
    }

    @Override
    public void drawGuiContainerBackgroundLayer(GuiContainer guiContainer, float partialTicks, int mouseX, int mouseY) {
        GuiUtils.drawContinuousTexturedBox(GuiMaker.resourceLocation, this.x + this.guiX, this.y + this.guiY, 16, 128, this.w, this.h, 16, 16, 1, 0);
        GuiUtils.drawContinuousTexturedBox(GuiMaker.resourceLocation, this.x + this.guiX + this.w - 10, this.y + this.guiY, 32, 128, 10, this.h, 16, 16, 1, 0);

        GuiUtils.drawContinuousTexturedBox(GuiMaker.resourceLocation, this.x + this.guiX + this.w - 9, this.y + this.guiY + 1 + offsetScrollY, 0, 128, 8, getScrollBarHeight(), 8, 16, 1, 0);

        GL11.glEnable(GL11.GL_STENCIL_TEST);
        GL11.glClear(GL11.GL_STENCIL_BUFFER_BIT);
        GL11.glStencilMask(0xFF);
        GL11.glStencilFunc(GL11.GL_ALWAYS, 1, 0xFF);
        GL11.glStencilOp(GL11.GL_KEEP, GL11.GL_KEEP, GL11.GL_REPLACE);
        GlStateManager.disableAlpha();

        Gui.drawRect(this.x + this.guiX + 1, this.y + this.guiY + 1, this.w + this.guiX + this.x - 10, this.h + this.guiY + this.y - 1, 0);

        GL11.glStencilMask(0x00);
        GL11.glStencilFunc(GL11.GL_NOTEQUAL, 0, 0xFF);

        for (GuiObject guiObject : guiObjectList)
            guiObject.drawGuiContainerBackgroundLayer(guiContainer, partialTicks, mouseX, mouseY);

        GL11.glDisable(GL11.GL_STENCIL_TEST);
    }

    private int initialClickY = -1;
    private float scrollMultiplier;

    @Override
    public void handleMouseInput() {
        if (Mouse.isButtonDown(0)) {
            if (initialClickY == -1) {
                if (!isMouseXYWithinScrollBar())
                    return;

                initialClickY = mouseY;
                scrollMultiplier = -1.0F;

                int k1 = this.getMaxScroll();
                if (k1 < 1)
                    k1 = 1;

                int l1 = (int)((float)((this.h) * (this.h)) / (float)this.maxScrollY);
                l1 = MathHelper.clamp_int(l1, 16, this.h - 2);
                //this.scrollMultiplier /= (float)(this.h - l1) / (float)k1;

            } else if (this.initialClickY >= 0) {
                this.offsetScrollY -= (float)(this.mouseY - this.initialClickY) * this.scrollMultiplier;
                this.initialClickY = this.mouseY;

                if (offsetScrollY < 0)
                    offsetScrollY = 0;

                if (offsetScrollY > this.h - 2 - getScrollBarHeight())
                    offsetScrollY = this.h - 2 - getScrollBarHeight();
            }
        } else {
            this.initialClickY = -1;
        }

        if (isMouseXYWithinScrollBox()) {
            int i2 = Mouse.getEventDWheel();

            if (i2 != 0) {
                if (i2 > 0)
                    i2 = -1;
                else if (i2 < 0)
                    i2 = 1;

                this.offsetScrollY += (float)(i2 * 10);

                if (offsetScrollY < 0)
                    offsetScrollY = 0;

                if (offsetScrollY > this.h - 2 - getScrollBarHeight())
                    offsetScrollY = this.h - 2 - getScrollBarHeight();
            }
        }

        for (GuiObject guiObject : guiObjectList)
            guiObject.handleMouseInput();
    }

    private boolean isMouseXYWithinScrollBar() {
        Rectangle r = new Rectangle(this.x + this.guiX + this.w - 9, this.y + this.guiY + 1 + offsetScrollY, 8, getScrollBarHeight());
        return r.contains(this.mouseX, this.mouseY);
    }

    private boolean isMouseXYWithinScrollBox() {
        Rectangle r = new Rectangle(this.x + this.guiX + 1, this.y + this.guiY + 1, this.w - 1, this.h - 1);
        return r.contains(this.mouseX, this.mouseY);
    }

    private int getMaxScroll() {
        return Math.max(0, this.maxScrollY - (this.h - 2));
    }

    @Override
    public void drawGuiContainerForegroundLayer(GuiContainer guiContainer, int mouseX, int mouseY) {
        for (GuiObject guiObject : guiObjectList) {
            guiObject.updateForegroundSize(this.x, this.y - offsetScrollY);
            guiObject.drawGuiContainerForegroundLayer(guiContainer, mouseX, mouseY);
        }
    }

    @Override
    public void drawSlot(Slot slotIn) {
        for (GuiObject guiObject : guiObjectList)
            guiObject.drawSlot(slotIn);
    }

    @Override
    public void mouseClicked(GuiContainer guiContainer, int mouseX, int mouseY, int mouseButton) throws IOException {
        for (GuiObject guiObject : guiObjectList)
            guiObject.mouseClicked(guiContainer, mouseX, mouseY, mouseButton);
    }

    @Override
    public void keyTyped(char typedChar, int keyCode) throws IOException {
        for (GuiObject guiObject : guiObjectList)
            guiObject.keyTyped(typedChar, keyCode);
    }

    @Override
    public void onGuiClosed() {
        for (GuiObject guiObject : guiObjectList)
            guiObject.onGuiClosed();
    }

    @Override
    public void updateScreen() {
        for (GuiObject guiObject : guiObjectList)
            guiObject.updateScreen();
    }

    @Override
    public List<Slot> initContainer(InventoryPlayer player, IInventory inventory) {
        List<Slot> slotList = new ArrayList<>();
        for (GuiObject guiObject : guiObjectList)
            slotList.addAll(guiObject.initContainer(player, inventory));
        return slotList;
    }
}
