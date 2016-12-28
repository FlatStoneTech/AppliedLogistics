package com.fireball1725.firelib.guimaker.objects;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class GuiSlot extends GuiObject {
    public GuiSlot(int x, int y) {
        super(-999);
        this.x = x;
        this.y = y;
        this.w = 18;
        this.h = 18;
    }

    public GuiSlot(int x, int y, int w, int h) {
        super(-999);

        if (w < 18)
            w = 18;

        if (h < 18)
            h = 18;

        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

    @Override
    public void drawGuiContainerBackgroundLayer(GuiContainer guiContainer, float partialTicks, int mouseX, int mouseY) {
        drawSlot(this.x, this.y, this.w, this.h);
    }
}
