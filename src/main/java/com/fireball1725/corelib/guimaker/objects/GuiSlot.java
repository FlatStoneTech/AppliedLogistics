package com.fireball1725.corelib.guimaker.objects;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import tech.flatstone.appliedlogistics.common.container.slot.SlotNormal;

import java.util.ArrayList;
import java.util.List;

public class GuiSlot extends GuiObject {
    private int slotNumber;

    public GuiSlot(int x, int y, int slotNumber) {
        this.locX = x;
        this.locY = y;
        this.slotNumber = slotNumber;
    }

    @Override
    public List<Slot> initContainer(InventoryPlayer inventoryPlayer, IInventory inventory) {
        List<Slot> slotList = new ArrayList<>();
        slotList.add(new Slot(inventory, this.slotNumber, 1 + this.locX, 1 + this.locY));
        return slotList;
    }

    @Override
    public void drawGuiContainerBackgroundLayer(GuiContainer guiContainer, float partialTicks, int mouseX, int mouseY) {
        guiContainer.mc.getTextureManager().bindTexture(this.textureSheet);

        drawSlot(guiContainer, this.locX, this.locY);
    }
}
