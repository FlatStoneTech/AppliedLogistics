package com.fireball1725.firelib.guimaker.objects;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import scala.Int;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GuiSlot extends GuiObject {
    private int slotNumber;
    private boolean largeSlot = false;
    private Class<? extends Slot> guiSlot;

    public GuiSlot(int x, int y, int slotNumber, Class<? extends Slot> guiSlot) {
        super(-999);
        this.x = x;
        this.y = y;
        this.w = 18;
        this.h = 18;
        this.slotNumber = slotNumber;
        this.guiSlot = guiSlot;
    }

    public GuiSlot(int x, int y, int w, int h, int slotNumber, Class<? extends Slot> guiSlot) {
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
        this.guiSlot = guiSlot;
    }

    @Override
    public List<Slot> initContainer(InventoryPlayer inventoryPlayer, IInventory inventory) {
        List<Slot> slotList = new ArrayList<>();

        int slotX = (w >> 1) - (16 >> 1);
        int slotY = (h >> 1) - (16 >> 1);

        try {
            slotList.add(this.guiSlot.getConstructor(IInventory.class, int.class, int.class, int.class).newInstance(inventory, this.slotNumber, slotX + this.x, slotY + this.y));
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return slotList;
    }

    @Override
    public void drawGuiContainerBackgroundLayer(GuiContainer guiContainer, float partialTicks, int mouseX, int mouseY) {
        drawSlot(this.x, this.y, this.w, this.h);
    }
}
