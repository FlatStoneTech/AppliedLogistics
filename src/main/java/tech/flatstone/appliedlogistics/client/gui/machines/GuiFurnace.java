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
        int slotRows = tileEntity.getFurnaceRows();
        this.xSize = 218;
        this.ySize = 126 + (18 * slotRows);
        this.tileEntity = tileEntity;
    }

    @Override
    public void drawBG(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
        bindTexture("gui/machines/furnace.png");
        int slotRows = tileEntity.getFurnaceRows();
        drawTexturedModalRect(paramInt1, paramInt2, 0, 0, this.xSize, 17);

        int windowY = 17;
        for (int i = 0; i < slotRows; i++) {
            drawTexturedModalRect(paramInt1, paramInt2 + windowY, 0, 17, this.xSize, 18);

            if (tileEntity.isUpgradeExtraSlots()) {
                drawTexturedModalRect(paramInt1 + 7, paramInt2 + windowY, 218, 0, 18, 18); // Draw Extra Input Slot #1
                drawTexturedModalRect(paramInt1 + 115, paramInt2 + windowY, 218, 0, 18, 18);
                drawTexturedModalRect(paramInt1 + 133, paramInt2 + windowY, 218, 0, 18, 18);
                drawTexturedModalRect(paramInt1 + 151, paramInt2 + windowY, 218, 0, 18, 18);
            }

            // Draw Smelt Progress
            // Dims = 24 x 17
            drawTexturedModalRect(paramInt1 + 58, paramInt2 + windowY + 1, 218, 32, tileEntity.getSmeltProgress(i) + 1, 17);

            windowY += 18;
        }

        drawTexturedModalRect(paramInt1, paramInt2 + windowY, 0, 35, this.xSize, this.ySize);

        // Draw Flames
        int fireOffset = tileEntity.getFuelOffset() + 1; // (x + 1) 1 and 11
        drawTexturedModalRect(paramInt1 + 191, paramInt2 + windowY + 60 + fireOffset, 218, 18 + fireOffset, 14, 14 - fireOffset);
    }

    @Override
    public void drawFG(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
        int slotRows = tileEntity.getFurnaceRows();

        int offsetY = 18 * slotRows;
        this.fontRendererObj.drawString(LanguageHelper.NONE.translateMessage(tileEntity.getUnlocalizedName()), 8, 6, 4210752);
        this.fontRendererObj.drawString(LanguageHelper.NONE.translateMessage("container.inventory"), 8, 33 + offsetY, 4210752);

        float tempPercent = ((((float) tileEntity.getIntTemperature()) / (float) (tileEntity.getMaxTemp()))) * 100;

        guiHelper.drawVerticalProgressBar(189, 10, 18, 52 + offsetY, Math.round(tempPercent), colorBackground, colorBorder, colorProgressBackground);
        guiHelper.drawLineOnVerticalProgressBar(189, 10, 18, 52 + offsetY, 200, tileEntity.getMaxTemp(), colorProgressBackgroundGood);
        if (tileEntity.getMaxTemp() >= 300)
            guiHelper.drawLineOnVerticalProgressBar(189, 10, 18, 52 + offsetY, 300, tileEntity.getMaxTemp(), colorProgressBackgroundGood);
        if (tileEntity.getMaxTemp() >= 400)
            guiHelper.drawLineOnVerticalProgressBar(189, 10, 18, 52 + offsetY, 400, tileEntity.getMaxTemp(), colorProgressBackgroundGood);
        if (tileEntity.getMaxTemp() >= 500)
            guiHelper.drawLineOnVerticalProgressBar(189, 10, 18, 52 + offsetY, 500, tileEntity.getMaxTemp(), colorProgressBackgroundGood);
        if (tileEntity.getMaxTemp() >= 600)
            guiHelper.drawLineOnVerticalProgressBar(189, 10, 18, 52 + offsetY, 600, tileEntity.getMaxTemp(), colorProgressBackgroundGood);

        guiHelper.drawCenteredString(189, 68 + offsetY, 18, String.format("%dx", tileEntity.getMultiplier()), 4210752);


    }
}
