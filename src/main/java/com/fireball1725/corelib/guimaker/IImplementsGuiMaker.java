package com.fireball1725.corelib.guimaker;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.tileentity.TileEntity;

public interface IImplementsGuiMaker {
    void DrawGui(TileEntity tileEntity);
    void InitGui(TileEntity tileEntity, InventoryPlayer inventoryPlayer);
}
