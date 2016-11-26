package com.fireball1725.corelib.guimaker.objects;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import tech.flatstone.appliedlogistics.common.container.slot.SlotNormal;

import java.util.ArrayList;
import java.util.List;

public class GuiSlot extends GuiObject {
    private int slotNumber;
    private IInventory inventory;

    public GuiSlot(int x, int y, int slotNumber, IInventory inventory) {
        this.locX = x;
        this.locY = y;
        this.slotNumber = slotNumber;
        this.inventory = inventory;
    }

    @Override
    public List<Slot> initContainer() {
        List<Slot> slotList = new ArrayList<>();
        slotList.add(new Slot(this.inventory, this.slotNumber, this.locX, this.locY));
        return slotList;
    }
}
