package com.fireball1725.firelib.guimaker.objects;

import com.fireball1725.firelib.guimaker.GuiMaker;
import com.fireball1725.firelib.guimaker.GuiMakerContainer;
import com.fireball1725.firelib.network.PacketHandler;
import com.fireball1725.firelib.network.messages.PacketGuiSlotAdd;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Slot;

public class GuiSlot extends GuiObject {
    private Class<? extends Slot> slot;
    private int slotID;

    public GuiSlot(Class<? extends Slot> slot, int slotID, int x, int y) {
        this(slot, slotID, x, y, 18, 18);
    }

    public GuiSlot(Class<? extends Slot> slot, int slotID, int x, int y, int w, int h) {
        super(-999);

        if (w < 18)
            w = 18;

        if (h < 18)
            h = 18;

        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;

        this.slotID = slotID;
        this.slot = slot;
    }

    @Override
    public void initGui() {
        PacketHandler.INSTANCE.sendToServer(new PacketGuiSlotAdd(slot, slotID, this.x, this.y, this.guiMakerGuiContainerObj.getGuiID()));
        GuiMaker.getGuiMakerInstance(this.guiMakerGuiContainerObj.getGuiID()).getGuiMakerContainer().addSlot(slot, slotID, this.x, this.y);
        ((GuiMakerContainer)GuiMaker.getGuiMakerInstance(this.guiMakerGuiContainerObj.getGuiID()).getGuiMakerGuiContainer().inventorySlots).addSlot(slot, slotID, this.x, this.y);
    }

    @Override
    public void drawGuiContainerBackgroundLayer(GuiContainer guiContainer, float partialTicks, int mouseX, int mouseY) {
        drawSlot(this.x, this.y, this.w, this.h);
    }
}
