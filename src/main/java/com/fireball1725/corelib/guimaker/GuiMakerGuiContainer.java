package com.fireball1725.corelib.guimaker;

import com.fireball1725.corelib.guimaker.objects.GuiObject;
import com.fireball1725.corelib.guimaker.objects.GuiTab;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.ClickType;
import net.minecraft.inventory.Slot;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import tech.flatstone.appliedlogistics.common.util.LogHelper;

import java.awt.*;
import java.io.IOException;
import java.util.Collections;

public class GuiMakerGuiContainer extends GuiContainer {
    private TileEntity tileEntity;
    private InventoryPlayer player;
    GuiMaker guiMaker;

    public GuiMakerGuiContainer(InventoryPlayer inventoryPlayer, TileEntity tileEntity, int id) {
        super(new GuiMakerContainer(inventoryPlayer, tileEntity, id));
        this.guiMaker = GuiMaker.getGuiMaker(id);

        guiMaker.setServerGuiTab(0, inventoryPlayer.player);

        this.xSize = 256;
        this.ySize = 220;

        this.guiMaker.getGuiMakerInstance().DrawGui(tileEntity);

        this.tileEntity = tileEntity;

        for(GuiObject guiObject : guiMaker.getGuiObjects()) {
            guiObject.setGuiHeight(ySize);
            guiObject.setGuiWidth(xSize);
            guiObject.setGuiX(this.guiLeft);
            guiObject.setGuiY(this.guiTop);
        }
    }

    @Override
    public void initGui() {
        super.initGui();
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        super.drawScreen(mouseX, mouseY, partialTicks);

        // Draw Tab Tooltip
        if (guiMaker.getGuiTabs().size() > 1) {
            int tabNumber = 0;
            for (GuiTab guiTabs : guiMaker.getGuiTabs()) {
                int x = this.guiLeft + this.xSize + 3;
                int y = this.guiTop + 13 + (tabNumber * 27);
                int w = 20;
                int h = 20;

                Rectangle r = new Rectangle(w, h);
                if (r.contains(mouseX - x, mouseY - y)) {
                    this.drawHoveringText(Collections.singletonList(guiTabs.getTabName()), mouseX, mouseY);
                }

                tabNumber++;
            }
        }

        for(GuiObject guiObject : guiMaker.getGuiObjects())
            guiObject.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        super.drawGuiContainerForegroundLayer(mouseX, mouseY);

        if (!guiMaker.getGuiTitle().isEmpty()) {
            GlStateManager.rotate(90, 0, 0, 90);

            if (guiMaker.getGuiMakerStatusIcon() != GuiMakerStatusIcon.EMPTY)
                Minecraft.getMinecraft().fontRendererObj.drawString(guiMaker.getGuiTitle(), 8 + 9, -1, 0xd5d5d5);
            else
                Minecraft.getMinecraft().fontRendererObj.drawString(guiMaker.getGuiTitle(), 8, -1, 0xd5d5d5);

            GlStateManager.rotate(-90, 0, 0, 90);
        }


        // Draw Tabs
        if (guiMaker.getGuiTabs().size() > 1) {
            GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);

            RenderHelper.enableGUIStandardItemLighting();

            int tabNumber = 0;
            for (GuiTab guiTabs : guiMaker.getGuiTabs()) {
                if (guiTabs.getTabIcon() != null) {
                    if (tabNumber == guiMaker.getSelectedTab()) {
                        this.itemRender.renderItemAndEffectIntoGUI(guiTabs.getTabIcon(), this.xSize + 3, 14 + (tabNumber * 27));
                    } else {
                        this.itemRender.renderItemAndEffectIntoGUI(guiTabs.getTabIcon(), this.xSize + 4, 14 + (tabNumber * 27));
                    }
                } else {
                    ResourceLocation resourceLocation = new ResourceLocation("firelib", "textures/gui/Darkskin.png");
                    this.mc.getTextureManager().bindTexture(resourceLocation);

                    //todo: make work with icon Number, instead of hard coding for icon 1

                    if (tabNumber == guiMaker.getSelectedTab()) {
                        drawTexturedModalRect(this.xSize + 3, 14 + (tabNumber * 27), 128, 0, 16, 16);
                    } else {
                        drawTexturedModalRect(this.xSize + 4, 14 + (tabNumber * 27), 128, 0, 16, 16);
                    }
                }
                tabNumber++;
            }

            GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
        }




        for(GuiObject guiObject : guiMaker.getGuiObjects())
            guiObject.drawGuiContainerForegroundLayer(mouseX, mouseY);
    }

    @Override
    public void drawSlot(Slot slotIn) {
        super.drawSlot(slotIn);
        for(GuiObject guiObject : guiMaker.getGuiObjects())
            guiObject.drawSlot(slotIn);
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        super.mouseClicked(mouseX, mouseY, mouseButton);

        // Select Tab
        if (guiMaker.getGuiTabs().size() > 1) {
            if (mouseButton == 0) {
                int tabNumber = 0;
                for (GuiTab guiTabs : guiMaker.getGuiTabs()) {
                    int x = this.guiLeft + this.xSize + 3;
                    int y = this.guiTop + 13 + (tabNumber * 27);
                    int w = 20;
                    int h = 20;

                    Rectangle r = new Rectangle(w, h);
                    if (r.contains(mouseX - x, mouseY - y)) {
                        guiMaker.setServerGuiTab(tabNumber, Minecraft.getMinecraft().thePlayer);
                        Minecraft.getMinecraft().getSoundHandler().playSound(PositionedSoundRecord.getMasterRecord(SoundEvents.UI_BUTTON_CLICK, 1.0F));
                        this.initGui();
                    }

                    tabNumber++;
                }
            }
        }

        for(GuiObject guiObject : guiMaker.getGuiObjects())
            guiObject.mouseClicked(mouseX, mouseY, mouseButton);
    }

    @Override
    protected void mouseClickMove(int mouseX, int mouseY, int clickedMouseButton, long timeSinceLastClick) {
        super.mouseClickMove(mouseX, mouseY, clickedMouseButton, timeSinceLastClick);
        for(GuiObject guiObject : guiMaker.getGuiObjects())
            guiObject.mouseClickMove(mouseX, mouseY, clickedMouseButton, timeSinceLastClick);
    }

    @Override
    protected void mouseReleased(int mouseX, int mouseY, int state) {
        super.mouseReleased(mouseX, mouseY, state);
        for(GuiObject guiObject : guiMaker.getGuiObjects())
            guiObject.mouseReleased(mouseX, mouseY, state);
    }

    @Override
    protected boolean isPointInRegion(int rectX, int rectY, int rectWidth, int rectHeight, int pointX, int pointY) {
//        for(GuiObject guiObject : guiMaker.getGuiObjects())
//            guiObject.isPointInRegion(rectX, rectY, rectWidth, rectHeight, pointX, pointY);
        return super.isPointInRegion(rectX, rectY, rectWidth, rectHeight, pointX, pointY);
    }

    @Override
    protected void handleMouseClick(Slot slotIn, int slotId, int mouseButton, ClickType type) {
        super.handleMouseClick(slotIn, slotId, mouseButton, type);
        for(GuiObject guiObject : guiMaker.getGuiObjects())
            guiObject.handleMouseClick(slotIn, slotId, mouseButton, type);
    }

    @Override
    protected void keyTyped(char typedChar, int keyCode) throws IOException {
        super.keyTyped(typedChar, keyCode);
        for(GuiObject guiObject : guiMaker.getGuiObjects())
            guiObject.keyTyped(typedChar, keyCode);
    }

    @Override
    protected boolean checkHotbarKeys(int keyCode) {
//        for(GuiObject guiObject : guiMaker.getGuiObjects())
//            guiObject.checkHotbarKeys(keyCode);
        return super.checkHotbarKeys(keyCode);
    }

    @Override
    public void onGuiClosed() {
        super.onGuiClosed();
        for(GuiObject guiObject : guiMaker.getGuiObjects())
            guiObject.onGuiClosed();
    }

    @Override
    public boolean doesGuiPauseGame() {
        return super.doesGuiPauseGame();
    }

    @Override
    public void updateScreen() {
        super.updateScreen();

        for(GuiObject guiObject : guiMaker.getGuiObjects()) {
            guiObject.setGuiHeight(ySize);
            guiObject.setGuiWidth(xSize);
            guiObject.setGuiX(this.guiLeft);
            guiObject.setGuiY(this.guiTop);
        }

        this.guiMaker.getGuiMakerInstance().DrawGui(this.tileEntity);

        for(GuiObject guiObject : guiMaker.getGuiObjects())
            guiObject.updateScreen();
    }

    //todo: look at this one...
    @Override
    public Slot getSlotUnderMouse() {
        return super.getSlotUnderMouse();
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        ResourceLocation resourceLocation = new ResourceLocation("firelib", "textures/gui/Darkskin.png");
        this.mc.getTextureManager().bindTexture(resourceLocation);

        drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, 5, 5);
        drawTexturedModalRect(this.guiLeft + this.xSize - 5, this.guiTop, 10, 0, 5, 5);
        drawTexturedModalRect(this.guiLeft, this.guiTop + this.ySize - 5, 0, 10, 5, 5);
        drawTexturedModalRect(this.guiLeft + this.xSize - 5, this.guiTop + this.ySize - 5, 10, 10, 5, 5);

        for (int i = 0; i < this.xSize - 9 - 5; i+= 5 ) {
            drawTexturedModalRect(this.guiLeft + i + 5, this.guiTop, 5, 0, 5, 5);
            drawTexturedModalRect(this.guiLeft + i + 5, this.guiTop + this.ySize - 5, 5, 10, 5, 5);
        }
        drawTexturedModalRect(this.guiLeft + this.xSize - 5 - 5, this.guiTop, 5, 0, 5, 5);
        drawTexturedModalRect(this.guiLeft + this.xSize - 5 - 5, this.guiTop + this.ySize - 5, 5, 10, 5, 5);


        for (int i = 0; i < this.ySize - 9 - 5; i+= 5) {
            drawTexturedModalRect(this.guiLeft, this.guiTop + i + 5, 0, 5, 5, 5);
            drawTexturedModalRect(this.guiLeft + this.xSize - 5, this.guiTop + i + 5, 10, 5, 5, 5);
        }
        drawTexturedModalRect(this.guiLeft, this.guiTop + this.ySize - 5 - 5, 0, 5, 5, 5);
        drawTexturedModalRect(this.guiLeft + this.xSize - 5, this.guiTop + this.ySize -5 - 5, 10, 5, 5, 5);

        for (int i = 0; i < this.xSize - 9 - 5; i+=5) {
            for (int j = 0; j < this.ySize - 9 - 5; j+=5) {
                drawTexturedModalRect(this.guiLeft + i + 5, this.guiTop + j + 5, 5, 5, 5, 5);
            }
        }

        for (int i = 0; i < this.xSize - 9 - 5; i+=5) {
            drawTexturedModalRect(this.guiLeft + i + 5, this.guiTop + this.ySize -5 - 5, 5, 5, 5, 5);
        }

        for (int i = 0; i < this.ySize - 9 - 5; i+=5) {
            drawTexturedModalRect(this.guiLeft + this.xSize - 5 - 5, this.guiTop + i + 5, 5, 5, 5, 5);
        }

        if (!guiMaker.getGuiTitle().isEmpty()) {
            drawTexturedModalRect(this.guiLeft - 9, this.guiTop + 5, 0, 15, 5, 5);
            drawTexturedModalRect(this.guiLeft - 4, this.guiTop + 5, 5, 15, 5, 5);

            int textY = this.fontRendererObj.getStringWidth(guiMaker.getGuiTitle());

            if (guiMaker.getGuiMakerStatusIcon() != GuiMakerStatusIcon.EMPTY) {
                textY += 9;
            }

            for (int i = 0; i < textY - 8; i+=5) {
                drawTexturedModalRect(this.guiLeft - 9, this.guiTop + 10 + i, 0, 20, 5, 5);
                drawTexturedModalRect(this.guiLeft - 4, this.guiTop + 10 + i, 5, 20, 5, 5);
            }

            drawTexturedModalRect(this.guiLeft - 9, this.guiTop + textY + 5, 0, 25, 5, 5);
            drawTexturedModalRect(this.guiLeft - 4, this.guiTop + textY + 5, 5, 25, 5, 5);

            drawTexturedModalRect(this.guiLeft - 9, this.guiTop + textY, 0, 20, 5, 5);
            drawTexturedModalRect(this.guiLeft - 4, this.guiTop + textY, 5, 20, 5, 5);

            if (guiMaker.getGuiMakerStatusIcon() != GuiMakerStatusIcon.EMPTY) {
                drawTexturedModalRect(this.guiLeft - 9 + 3, this.guiTop + 5 + 3, 235 + ((guiMaker.getGuiMakerStatusIcon().ordinal() - 1) * 7), 0, 7, 7);
            }
        }

        // Draw Tabs
        if (guiMaker.getGuiTabs().size() > 1) {
            int tabNumber = 0;
            for (GuiTab guiTabs : guiMaker.getGuiTabs()) {
                if (tabNumber == guiMaker.getSelectedTab()) {
                    drawTab(this.guiLeft + this.xSize - 1, this.guiTop + 10 + (tabNumber * 27), true);
                } else {
                    drawTab(this.guiLeft + this.xSize, this.guiTop + 10 + (tabNumber * 27), false);
                }
                tabNumber++;
            }
        }

        for(GuiObject guiObject : guiMaker.getGuiObjects())
            guiObject.drawGuiContainerBackgroundLayer(partialTicks, mouseX, mouseY);

    }

    private void drawTab(int x, int y, boolean selected) {
        for (int i = 0; i < 24; i+=5) {
            if (selected)
                drawTexturedModalRect(x, y + i, 10, 20, 5, 5);

            if (i > 1 && i < 20) {
                for (int j = 0; j < 19; j+=5) {
                    drawTexturedModalRect(x + j, y + i, 15, 20, 5, 5);
                }
                drawTexturedModalRect(x + 20, y + i, 20, 20, 5, 5);
            } else if (i == 0) {
                for (int j = 0; j < 19; j+=5) {
                    drawTexturedModalRect(x + j, y + i, 15, 15, 5, 5);
                }
                drawTexturedModalRect(x + 20, y + i, 20, 15, 5, 5);
            } else if (i == 20) {
                for (int j = 0; j < 19; j+=5) {
                    drawTexturedModalRect(x + j, y + i, 15, 25, 5, 5);
                }
                drawTexturedModalRect(x + 20, y + i, 20, 25, 5, 5);
            }
        }
    }
}
