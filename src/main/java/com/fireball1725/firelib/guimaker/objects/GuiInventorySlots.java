package com.fireball1725.firelib.guimaker.objects;

import com.fireball1725.firelib.guimaker.GuiMaker;
import com.fireball1725.firelib.guimaker.GuiMakerContainer;
import com.fireball1725.firelib.network.PacketHandler;
import com.fireball1725.firelib.network.messages.PacketGuiSlotAddInventory;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Slot;

public class GuiInventorySlots extends GuiObject {

    public GuiInventorySlots(int x, int y) {
        super(-999);
        this.x = x;
        this.y = y;
    }

    @Override
    public void initGui() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 9; j++) {
                drawSlot(j * 18 + this.x, i * 18 + this.y, 18, 18);
                PacketHandler.INSTANCE.sendToServer(new PacketGuiSlotAddInventory(Slot.class, j + i * 9 + 9, j * 18 + this.x + 1, i * 18 + this.y + 1, this.guiMakerGuiContainerObj.getGuiID()));
                ((GuiMakerContainer)GuiMaker.getGuiMakerInstance(this.guiMakerGuiContainerObj.getGuiID()).getGuiMakerGuiContainer().inventorySlots).addInventorySlot(Slot.class, j + i * 9 + 9, j * 18 + this.x + 1, i * 18 + this.y + 1);
            }
        }

        for (int i = 0; i < 9; i++) {
            drawSlot(i * 18 + this.x, this.y + 58, 18, 18);
            PacketHandler.INSTANCE.sendToServer(new PacketGuiSlotAddInventory(Slot.class, i, i * 18 + this.x + 1, 58 + this.y + 1, this.guiMakerGuiContainerObj.getGuiID()));
            ((GuiMakerContainer)GuiMaker.getGuiMakerInstance(this.guiMakerGuiContainerObj.getGuiID()).getGuiMakerGuiContainer().inventorySlots).addInventorySlot(Slot.class, i, i * 18 + this.x + 1, 58 + this.y + 1);
        }
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
