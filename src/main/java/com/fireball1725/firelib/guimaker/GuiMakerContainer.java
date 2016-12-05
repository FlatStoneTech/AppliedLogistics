package com.fireball1725.firelib.guimaker;


import com.fireball1725.firelib.guimaker.objects.GuiObject;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.tileentity.TileEntity;

import java.util.List;

public class GuiMakerContainer extends Container {
    private final InventoryPlayer inventoryPlayer;
    private final TileEntity tileEntity;
    private GuiMaker guiMaker;

    public GuiMakerContainer(InventoryPlayer inventoryPlayer, TileEntity tileEntity, int id) {
        this.inventoryPlayer = inventoryPlayer;
        this.tileEntity = tileEntity;
        this.guiMaker = GuiMaker.getGuiMaker(id);

        initContainer();
    }

    public void initContainer() {
        this.inventoryItemStacks.clear();
        this.inventorySlots.clear();

        for (GuiObject guiObject : guiMaker.getGuiObjects()) {
            List<Slot> slotList;
            slotList = guiObject.initContainer(inventoryPlayer, (IInventory) tileEntity);
            for (Slot slot : slotList) {
                this.addSlotToContainer(slot);
            }
        }
    }

    @Override
    public boolean canInteractWith(EntityPlayer playerIn) {
        return true;
    }
}
