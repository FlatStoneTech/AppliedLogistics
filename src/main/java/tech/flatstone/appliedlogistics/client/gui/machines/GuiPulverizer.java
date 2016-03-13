package tech.flatstone.appliedlogistics.client.gui.machines;

import net.minecraft.entity.player.InventoryPlayer;
import tech.flatstone.appliedlogistics.client.gui.GuiBase;
import tech.flatstone.appliedlogistics.common.container.machines.ContainerPulverizer;
import tech.flatstone.appliedlogistics.common.tileentities.machines.TileEntityPulverizer;

public class GuiPulverizer extends GuiBase {
    TileEntityPulverizer tileEntity;
    public GuiPulverizer(InventoryPlayer inventoryPlayer, TileEntityPulverizer tileEntity) {
        super(new ContainerPulverizer(inventoryPlayer, tileEntity));
        this.xSize = 176;
        this.ySize = 221;
        this.tileEntity = tileEntity;
    }

    @Override
    public void drawBG(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
        bindTexture("gui/machines/pulverizer.png");
        drawTexturedModalRect(paramInt1, paramInt2, 0, 0, this.xSize, this.ySize);
    }

    @Override
    public void drawFG(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {

    }
}
