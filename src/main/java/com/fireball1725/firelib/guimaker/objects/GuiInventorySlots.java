package com.fireball1725.firelib.guimaker.objects;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;

import java.util.ArrayList;
import java.util.List;

public class GuiInventorySlots extends GuiObject {

    public GuiInventorySlots(int x, int y) {
        super(-999);
        this.x = x;
        this.y = y;
    }

    @Override
    public void drawGuiContainerBackgroundLayer(GuiContainer guiContainer, float partialTicks, int mouseX, int mouseY) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 9; j++) {
                drawSlot(j * 18 + this.x, i * 18 + this.y, 18, 18);
            }
        }

        for (int i = 0; i < 9; i++) {
            drawSlot(i * 18 + this.x, this.y + 58, 18, 18);
        }
    }
}
