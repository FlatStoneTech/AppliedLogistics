package com.fireball1725.corelib.guimaker.objects;

import net.minecraft.inventory.ClickType;
import net.minecraft.inventory.Slot;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class GuiObject implements IGuiObject {
    protected int locX;
    protected int locY;
    protected int width;
    protected int height;
    protected int guiWidth;
    protected int guiHeight;
    protected int guiX;
    protected int guiY;

    public void setGuiWidth(int guiWidth) {
        this.guiWidth = guiWidth;
    }

    public void setGuiHeight(int guiHeight) {
        this.guiHeight = guiHeight;
    }

    public void setGuiX(int guiX) {
        this.guiX = guiX;
    }

    public void setGuiY(int guiY) {
        this.guiY = guiY;
    }

    public int getLocX() {
        return locX;
    }

    public void setLocX(int locX) {
        this.locX = locX;
    }

    public int getLocY() {
        return locY;
    }

    public void setLocY(int locY) {
        this.locY = locY;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public void initGui() {

    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {

    }

    @Override
    public void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {

    }

    @Override
    public void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {

    }

    @Override
    public void drawSlot(Slot slotIn) {

    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {

    }

    @Override
    public void mouseClickMove(int mouseX, int mouseY, int clickedMouseButton, long timeSinceLastClick) {

    }

    @Override
    public void mouseReleased(int mouseX, int mouseY, int state) {

    }

    @Override
    public boolean isPointInRegion(int rectX, int rectY, int rectWidth, int rectHeight, int pointX, int pointY) {
        return false;
    }

    @Override
    public void handleMouseClick(Slot slotIn, int slotId, int mouseButton, ClickType type) {

    }

    @Override
    public void keyTyped(char typedChar, int keyCode) throws IOException {

    }

    @Override
    public boolean checkHotbarKeys(int keyCode) {
        return false;
    }

    @Override
    public void onGuiClosed() {

    }

    @Override
    public void updateScreen() {

    }

    @Override
    public List<Slot> initContainer() {
        return new ArrayList<>();
    }


}
