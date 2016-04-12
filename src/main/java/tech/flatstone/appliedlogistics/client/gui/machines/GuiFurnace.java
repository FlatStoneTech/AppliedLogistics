package tech.flatstone.appliedlogistics.client.gui.machines;

import net.minecraft.entity.player.InventoryPlayer;
import tech.flatstone.appliedlogistics.client.gui.GuiBase;
import tech.flatstone.appliedlogistics.common.container.machines.ContainerFurnace;
import tech.flatstone.appliedlogistics.common.tileentities.machines.TileEntityFurnace;
import tech.flatstone.appliedlogistics.common.util.GuiHelper;
import tech.flatstone.appliedlogistics.common.util.LanguageHelper;

public class GuiFurnace extends GuiBase {
    private TileEntityFurnace tileEntity;
    private GuiHelper guiHelper = new GuiHelper();

    public GuiFurnace(InventoryPlayer inventoryPlayer, TileEntityFurnace tileEntity) {
        super(new ContainerFurnace(inventoryPlayer, tileEntity));
        int slotRows = tileEntity.getSlotRows();
        this.xSize = 218;
        this.ySize = 126 + (18 * slotRows);
        this.tileEntity = tileEntity;
    }

    @Override
    public void drawBG(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
        bindTexture("gui/machines/furnace.png");
        int slotRows = tileEntity.getSlotRows();
        drawTexturedModalRect(paramInt1, paramInt2, 0, 0, this.xSize, 17);

        int windowY = 17;
        for (int i = 0; i < slotRows; i++) {
            drawTexturedModalRect(paramInt1, paramInt2 + windowY, 0, 17, this.xSize, 18);
            drawTexturedModalRect(paramInt1 + 7, paramInt2 + windowY, 218, 0, 18, 18); // Draw Extra Input Slot #1
            drawTexturedModalRect(paramInt1 + 115, paramInt2 + windowY, 218, 0, 18, 18);
            drawTexturedModalRect(paramInt1 + 133, paramInt2 + windowY, 218, 0, 18, 18);
            drawTexturedModalRect(paramInt1 + 151, paramInt2 + windowY, 218, 0, 18, 18);

            windowY += 18;
        }

        drawTexturedModalRect(paramInt1, paramInt2 + windowY, 0, 35, this.xSize, this.ySize);



    }

    @Override
    public void drawFG(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
        int slotRows = tileEntity.getSlotRows();

        int offsetY = 18 * slotRows;
        this.fontRendererObj.drawString(LanguageHelper.NONE.translateMessage(tileEntity.getUnlocalizedName()), 8, 6, 4210752);
        this.fontRendererObj.drawString(LanguageHelper.NONE.translateMessage("container.inventory"), 8, 33 + offsetY, 4210752);

        guiHelper.drawVerticalProgressBar(189, 10, 18, 52 + offsetY, 50, colorBackground, colorBorder, colorProgressBackground);
        guiHelper.drawCenteredString(189, 68 + offsetY, 18, "250°C", 4210752);
    }
}
