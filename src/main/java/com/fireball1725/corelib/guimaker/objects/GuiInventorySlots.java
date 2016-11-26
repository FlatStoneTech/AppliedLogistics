package com.fireball1725.corelib.guimaker.objects;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;

import java.util.ArrayList;
import java.util.List;

public class GuiInventorySlots extends GuiObject {

    public GuiInventorySlots(int x, int y) {
        this.locX = x;
        this.locY = y;
    }

    @Override
    public List<Slot> initContainer(InventoryPlayer inventoryPlayer, IInventory inventory) {
        List<Slot> slotList = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 9; j++) {
                slotList.add(new Slot(inventoryPlayer, j + i * 9 + 9, 1 + j * 18 + this.locX, 1 + i * 18 + this.locY));
            }
        }

        for (int i = 0; i < 9; i++) {
            slotList.add(new Slot(inventoryPlayer, i, 1 + i * 18 + this.locX, 1 + 58 + this.locY));
        }

        return slotList;
    }

    @Override
    public void drawGuiContainerBackgroundLayer(GuiContainer guiContainer, float partialTicks, int mouseX, int mouseY) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 9; j++) {
                drawSlot(guiContainer, j * 18 + this.locX, i * 18 + this.locY);
            }
        }

        for (int i = 0; i < 9; i++) {
            drawSlot(guiContainer, i * 18 + this.locX, this.locY + 58);
        }
    }
}
