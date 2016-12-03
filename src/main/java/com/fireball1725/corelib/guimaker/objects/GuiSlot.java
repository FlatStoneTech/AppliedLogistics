package com.fireball1725.corelib.guimaker.objects;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import tech.flatstone.appliedlogistics.common.container.slot.SlotNormal;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GuiSlot extends GuiObject {
    private int slotNumber;
    private boolean largeSlot = false;

    public GuiSlot(int x, int y, int slotNumber) {
        super(-999);
        this.x = x;
        this.y = y;
        this.w = 18;
        this.h = 18;
        this.slotNumber = slotNumber;
    }

    public GuiSlot(int x, int y, int w, int h, int slotNumber) {
        super(-999);

        if (w < 18)
            w = 18;

        if (h < 18)
            h = 18;

        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.slotNumber = slotNumber;
    }

    @Override
    public List<Slot> initContainer(InventoryPlayer inventoryPlayer, IInventory inventory) {
        List<Slot> slotList = new ArrayList<>();

        int slotX = (w >> 1) - (16 >> 1);
        int slotY = (h >> 1) - (16 >> 1);

        slotList.add(new Slot(inventory, this.slotNumber, slotX + this.x, slotY + this.y));
        return slotList;
    }

    @Override
    public void drawGuiContainerBackgroundLayer(GuiContainer guiContainer, float partialTicks, int mouseX, int mouseY) {
        drawSlot(this.x, this.y, this.w, this.h);
    }
}
