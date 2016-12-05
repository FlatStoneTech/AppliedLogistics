package com.fireball1725.firelib.guimaker;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.tileentity.TileEntity;

public interface IImplementsGuiMaker {
    void drawGui(TileEntity tileEntity);

    void initGui(TileEntity tileEntity, InventoryPlayer inventoryPlayer);
}
