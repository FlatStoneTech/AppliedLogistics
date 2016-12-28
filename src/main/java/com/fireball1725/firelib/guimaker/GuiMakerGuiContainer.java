package com.fireball1725.firelib.guimaker;

import com.fireball1725.firelib.guimaker.objects.GuiObject;
import com.fireball1725.firelib.guimaker.objects.GuiTab;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.Slot;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.config.GuiUtils;
import tech.flatstone.appliedlogistics.common.util.LanguageHelper;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GuiMakerGuiContainer extends GuiContainer {
    GuiMaker guiMaker;
    private TileEntity tileEntity;
    private InventoryPlayer player;
    private int mouseX = 0;
    private int mouseY = 0;
    protected String guiTitle = "";
    protected GuiMakerStatusIcon guiMakerStatusIcon = GuiMakerStatusIcon.EMPTY;


    private int selectedTab = 0;
    private List<GuiTab> guiTabs = new ArrayList<>();


    public GuiMakerGuiContainer(int id, EntityPlayer player, World world, BlockPos pos) {
        super(new GuiMakerContainer(id, player, world, pos));

        tileEntity = world.getTileEntity(pos);
        guiTitle = tileEntity.getBlockType().getLocalizedName();
        //guiTitle = tileEntity.hasCustomName() ? tileEntity.getCustomName() : LanguageHelper.NONE.translateMessage(tileEntity.getUnlocalizedName());
    }

    public void addGuiTab(GuiTab guiTab) {
        if (guiTab != null)
            guiTabs.add(guiTab);
    }

    public List<GuiObject> getGuiObjects() {
        if (guiTabs.size() == 0)
            return null;

        return guiTabs.get(selectedTab).getGuiObjectList();
    }

    @Override
    public void handleMouseInput() throws IOException {
        super.handleMouseInput();

        for (GuiObject guiObject : getGuiObjects())
            guiObject.handleMouseInput();
    }

    @Override
    public void initGui() {
        super.initGui();

        for (GuiObject guiObject : getGuiObjects()) {
            guiObject.updateGuiSize(this.guiLeft, this.guiTop, xSize, ySize);
            guiObject.initGui();
        }
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        super.drawScreen(mouseX, mouseY, partialTicks);
        this.mouseX = mouseX;
        this.mouseY = mouseY;

        this.zLevel = -1;

        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
        RenderHelper.enableGUIStandardItemLighting();

        for (GuiObject guiObject : getGuiObjects()) {
            guiObject.updateMouse(mouseX, mouseY);
            guiObject.drawScreen(this, mouseX, mouseY, partialTicks, this.zLevel);
        }

        this.zLevel = 0;

        // Draw Tab Tooltip
        if (this.guiTabs.size() > 1) {
            int tabNumber = 0;
            for (GuiTab guiTabs : this.guiTabs) {
                int x = this.guiLeft + this.xSize;
                int y = this.guiTop + 11 + (tabNumber * 25);
                int w = 21;
                int h = 24;

                Rectangle r = new Rectangle(w, h);
                if (r.contains(mouseX - x, mouseY - y)) {
                    this.drawHoveringText(Collections.singletonList(guiTabs.getTabName()), mouseX, mouseY);
                }

                tabNumber++;
            }
        }
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        super.drawGuiContainerForegroundLayer(mouseX, mouseY);

        if (!this.guiTitle.isEmpty()) {
            GlStateManager.rotate(90, 0, 0, 90);

            if (this.guiMakerStatusIcon != GuiMakerStatusIcon.EMPTY)
                this.fontRendererObj.drawString(this.guiTitle, 8 + 9, -1, 0xd5d5d5);
            else
                this.fontRendererObj.drawString(this.guiTitle, 8, -1, 0xd5d5d5);

            GlStateManager.rotate(-90, 0, 0, 90);
        }


        // Draw Tabs
        if (this.guiTabs.size() > 1) {
            GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);

            RenderHelper.enableGUIStandardItemLighting();

            int tabNumber = 0;
            for (GuiTab guiTabs : this.guiTabs) {
                if (guiTabs.getTabIconStack() != null) {
                    if (tabNumber == this.selectedTab) {
                        this.itemRender.renderItemAndEffectIntoGUI(guiTabs.getTabIconStack(), this.xSize, 14 + (tabNumber * 25));
                    } else {
                        this.itemRender.renderItemAndEffectIntoGUI(guiTabs.getTabIconStack(), this.xSize + 1, 14 + (tabNumber * 25));
                    }
                } else {
                    ResourceLocation resourceLocation = new ResourceLocation("firelib", "textures/gui/darkskin.png");
                    this.mc.getTextureManager().bindTexture(resourceLocation);

                    //todo: make work with icon Number, instead of hard coding for icon 1

                    if (tabNumber == this.selectedTab) {
                        drawTexturedModalRect(this.xSize, 14 + (tabNumber * 25), 128, 0, 16, 16);
                    } else {
                        drawTexturedModalRect(this.xSize + 1, 14 + (tabNumber * 25), 128, 0, 16, 16);
                    }
                }
                tabNumber++;
            }

            GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
        }

        for (GuiObject guiObject : this.getGuiObjects())
            guiObject.drawGuiContainerForegroundLayer(this, mouseX, mouseY);
    }

    @Override
    public void drawSlot(Slot slotIn) {
        super.drawSlot(slotIn);

        for (GuiObject guiObject : this.getGuiObjects())
            guiObject.drawSlot(slotIn);
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        super.mouseClicked(mouseX, mouseY, mouseButton);

        // Select Tab
        if (this.guiTabs.size() > 1) {
            if (mouseButton == 0) {
                int tabNumber = 0;
                for (GuiTab guiTabs : this.guiTabs) {
                    int x = this.guiLeft + this.xSize + 1;
                    int y = this.guiTop + 11 + (tabNumber * 25);
                    int w = 21;
                    int h = 24;

                    Rectangle r = new Rectangle(w, h);
                    if (r.contains(mouseX - x, mouseY - y)) {
                        this.selectedTab = tabNumber;
                        Minecraft.getMinecraft().getSoundHandler().playSound(PositionedSoundRecord.getMasterRecord(SoundEvents.UI_BUTTON_CLICK, 1.0F));
                        this.initGui();
                    }

                    tabNumber++;
                }
            }
        }

        for (GuiObject guiObject : this.getGuiObjects())
            guiObject.mouseClicked(this, mouseX, mouseY, mouseButton);
    }

    @Override
    protected void keyTyped(char typedChar, int keyCode) throws IOException {
        super.keyTyped(typedChar, keyCode);
        for (GuiObject guiObject : this.getGuiObjects())
            guiObject.keyTyped(typedChar, keyCode);
    }

    @Override
    public void onGuiClosed() {
        super.onGuiClosed();
        for (GuiObject guiObject : this.getGuiObjects())
            guiObject.onGuiClosed();
    }

    @Override
    public boolean doesGuiPauseGame() {
        return super.doesGuiPauseGame();
    }

    @Override
    public void updateScreen() {
        super.updateScreen();

        for (GuiObject guiObject : this.getGuiObjects()) {
            guiObject.updateGuiSize(this.guiLeft, this.guiTop, xSize, ySize);
        }

        // todo what is this??
        //this.guiMaker.getGuiMakerInstance().drawGui(this.tileEntity);

        for (GuiObject guiObject : this.getGuiObjects())
            guiObject.updateScreen();
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        GuiUtils.drawContinuousTexturedBox(GuiMaker.resourceLocation, this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize, 16, 16, 4, this.zLevel);

        if (!this.guiTitle.isEmpty()) {
            int textY = this.fontRendererObj.getStringWidth(this.guiTitle);

            if (this.guiMakerStatusIcon != GuiMakerStatusIcon.EMPTY) {
                GuiUtils.drawContinuousTexturedBox(GuiMaker.resourceLocation, this.guiLeft - 9, this.guiTop + 5, 0, 32, 12, textY + 5 + 9, 12, 16, 4, this.zLevel);

                this.mc.getTextureManager().bindTexture(GuiMaker.resourceLocation);
                drawTexturedModalRect(this.guiLeft - 9 + 3, this.guiTop + 5 + 3, 235 + ((this.guiMakerStatusIcon.ordinal() - 1) * 7), 0, 7, 7);
            } else {
                GuiUtils.drawContinuousTexturedBox(GuiMaker.resourceLocation, this.guiLeft - 9, this.guiTop + 5, 0, 32, 12, textY + 5, 12, 16, 4, this.zLevel);
            }
        }

        if (this.guiTabs.size() > 1) {
            int tabNumber = 0;
            for (GuiTab guiTab : this.guiTabs) {
                if (tabNumber == this.selectedTab) {
                    GuiUtils.drawContinuousTexturedBox(GuiMaker.resourceLocation, this.guiLeft + this.xSize - 1, this.guiTop + 10 + (tabNumber * 25), 4, 48, 21, 24, 12, 16, 4, this.zLevel);
                } else {
                    GuiUtils.drawContinuousTexturedBox(GuiMaker.resourceLocation, this.guiLeft + this.xSize, this.guiTop + 10 + (tabNumber * 25), 4, 48, 21, 24, 12, 16, 4, this.zLevel);
                }
                tabNumber++;
            }
        }

        for (GuiObject guiObject : this.getGuiObjects()) {
            //guiObject.setTextureSheet(GuiMaker.resourceLocation);
            guiObject.drawGuiContainerBackgroundLayer(this, partialTicks, mouseX, mouseY);
        }
    }

    public List<Rectangle> getExtaGuiAreas() {
        List<Rectangle> areas = new ArrayList<Rectangle>();

        if (this.guiTabs.size() > 1) {
            Rectangle tabArea = new Rectangle(this.guiLeft + this.xSize, this.guiTop + 9, 24, (25 * this.guiTabs.size()) + 1);
            areas.add(tabArea);
        }

        return areas;
    }
}
