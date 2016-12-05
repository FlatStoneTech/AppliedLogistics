package com.fireball1725.firelib.guimaker.objects;

import com.fireball1725.firelib.guimaker.GuiMaker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.client.config.GuiUtils;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GuiScrollBox extends GuiObject {
    private final GuiMaker guiMakerObj;
    private List<GuiObject> guiObjectList = new ArrayList<>();
    private int maxScrollY = 0;
    private int offsetScrollY = 0;
    private boolean border;
    private int initialClickY = -1;
    private float scrollMultiplier;

    public GuiScrollBox(GuiMaker guiMakerObj, int x, int y, int w, int h) {
        super(-999);
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.maxScrollY = h;
        this.guiMakerObj = guiMakerObj;
        this.border = true;
    }

    public GuiScrollBox(GuiMaker guiMakerObj, int x, int y, int w, int h, boolean border) {
        super(-999);
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.maxScrollY = h;
        this.guiMakerObj = guiMakerObj;
        this.border = border;
    }

    public static void scissorCut(int x, int y, int w, int h) {
        Minecraft mc = Minecraft.getMinecraft();
        ScaledResolution sr = new ScaledResolution(mc);
        int scale = sr.getScaleFactor();
        GL11.glEnable(GL11.GL_SCISSOR_TEST);
        GL11.glScissor(x * scale, mc.displayHeight - (y + h) * scale, w * scale, h * scale);
    }

    public static void scissorEnd() {
        GL11.glDisable(GL11.GL_SCISSOR_TEST);
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
            guiObject.setParent(this.x, this.y);
            guiObject.updateGuiSize(this.guiX, this.guiY, this.guiW, this.guiH);
        }
    }

    @Override
    public void drawScreen(GuiContainer guiContainer, int mouseX, int mouseY, float partialTicks, float zLevel) {
        for (GuiObject guiObject : guiObjectList) {
            //guiObject.updateGuiSize(this.guiX + this.x, this.guiY + this.y - this.offsetScrollY, this.w, this.h);
            guiObject.updateMouse(this.mouseX, this.mouseY);
            guiObject.setOffset(1, 1 + -offsetScrollY);
        }

        for (GuiObject guiObject : guiObjectList)
            guiObject.drawScreen(guiContainer, mouseX, mouseY, partialTicks, zLevel);
    }

    private int getScrollBarHeight() {
        int scrollBarHeight = (int) ((float) ((this.h) * (this.h)) / (float) this.maxScrollY);
        scrollBarHeight = MathHelper.clamp_int(scrollBarHeight, 16, this.h - 2);

        return scrollBarHeight;
    }

    @Override
    public void drawGuiContainerBackgroundLayer(GuiContainer guiContainer, float partialTicks, int mouseX, int mouseY) {
        if (border) {
            GuiUtils.drawContinuousTexturedBox(GuiMaker.resourceLocation, this.x + this.guiX, this.y + this.guiY, 16, 128, this.w, this.h, 16, 16, 1, 0);
            GuiUtils.drawContinuousTexturedBox(GuiMaker.resourceLocation, this.x + this.guiX + this.w - 10, this.y + this.guiY, 32, 128, 10, this.h, 16, 16, 1, 0);
        }

        int top = this.y + this.guiY + 1;

        int scrollBarY = this.offsetScrollY * (this.h - getScrollBarHeight()) / this.getMaxScroll() + top;
        if (scrollBarY < top)
            scrollBarY = top;

        GuiUtils.drawContinuousTexturedBox(GuiMaker.resourceLocation, this.x + this.guiX + this.w - 9, scrollBarY, 0, 128, 8, getScrollBarHeight(), 8, 16, 1, 0);

        scissorCut(this.x + this.guiX + 1, this.y + this.guiY + 1, this.w - 10, this.h - 2);

        for (GuiObject guiObject : guiObjectList)
            guiObject.drawGuiContainerBackgroundLayer(guiContainer, partialTicks, mouseX, mouseY);

        scissorEnd();
    }

    @Override
    public void handleMouseInput() {
        if (Mouse.isButtonDown(0)) {
            if (initialClickY == -1) {
                boolean flag1 = true;
                if (isMouseXYWithinScrollBox()) {
                    if (isMouseXYWithinScrollBar()) {
                        this.scrollMultiplier = -1.0F;
                        int l1 = (int) ((float) ((this.h) * (this.h)) / (float) this.maxScrollY);
                        l1 = MathHelper.clamp_int(l1, 16, this.h - 2);
                        this.scrollMultiplier /= (float) (this.h - l1) / Math.max(1, this.getMaxScroll());
                    } else {
                        this.scrollMultiplier = 1.0F;
                    }
                    if (flag1)
                        this.initialClickY = this.mouseY;
                    else
                        this.initialClickY = -2;
                } else {
                    this.initialClickY = -2;
                }
            } else if (this.initialClickY >= 0) {
                this.offsetScrollY -= (this.mouseY - this.initialClickY) * this.scrollMultiplier;
                this.offsetScrollY = MathHelper.clamp_int(this.offsetScrollY, 0, this.maxScrollY - this.h);
                this.initialClickY = this.mouseY;
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

                this.offsetScrollY += (float) (i2 * 10);
                this.offsetScrollY = MathHelper.clamp_int(this.offsetScrollY, 0, this.maxScrollY - this.h);
            }
        }

        for (GuiObject guiObject : guiObjectList)
            guiObject.handleMouseInput();
    }

    private boolean isMouseXYWithinScrollBar() {
        int top = this.y + this.guiY + 1;
        int scrollBarY = this.offsetScrollY * (this.h - getScrollBarHeight()) / this.getMaxScroll() + top;
        if (scrollBarY < top)
            scrollBarY = top;

        Rectangle r = new Rectangle(this.x + this.guiX + this.w - 9, scrollBarY, 8, getScrollBarHeight());
        return r.contains(this.mouseX, this.mouseY);
    }

    private boolean isMouseXYWithinScrollBox() {
        Rectangle r = new Rectangle(this.x + this.guiX + 1, this.y + this.guiY + 1, this.w - 1, this.h - 1);
        return r.contains(this.mouseX, this.mouseY);
    }

    private int getMaxScroll() {
        return Math.max(0, this.maxScrollY - (this.h - 3));
    }

    @Override
    public void drawGuiContainerForegroundLayer(GuiContainer guiContainer, int mouseX, int mouseY) {
        scissorCut(this.x + 1 + guiX, this.y + 1 + guiY, this.w - 10, this.h - 2);

        for (GuiObject guiObject : guiObjectList)
            guiObject.drawGuiContainerForegroundLayer(guiContainer, mouseX, mouseY);

        scissorEnd();
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
        for (GuiObject guiObject : guiObjectList) {
            guiObject.updateGuiSize(this.guiX, this.guiY, this.guiW, this.guiH);
            guiObject.updateScreen();
        }
    }

    @Override
    public List<Slot> initContainer(InventoryPlayer player, IInventory inventory) {
        List<Slot> slotList = new ArrayList<>();
        for (GuiObject guiObject : guiObjectList)
            slotList.addAll(guiObject.initContainer(player, inventory));
        return slotList;
    }
}
