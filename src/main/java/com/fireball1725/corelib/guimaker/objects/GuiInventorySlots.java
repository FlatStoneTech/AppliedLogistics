package com.fireball1725.corelib.guimaker.objects;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;

import java.util.ArrayList;
import java.util.List;

public class GuiInventorySlots extends GuiObject {
    private InventoryPlayer inventoryPlayer;

    public GuiInventorySlots(int x, int y, InventoryPlayer inventoryPlayer) {
        this.locX = x;
        this.locY = y;
        this.inventoryPlayer = inventoryPlayer;
    }

    @Override
    public List<Slot> initContainer() {
        List<Slot> slotList = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 9; j++) {
                slotList.add(new Slot(inventoryPlayer, j + i * 9 + 9, 8 + j * 18 + this.locX, this.locY + i * 18));
            }
        }

        for (int i = 0; i < 9; i++) {
            slotList.add(new Slot(inventoryPlayer, i, 8 + i * 18 + this.locX, this.locY + 58));
        }

        return slotList;
    }
}
