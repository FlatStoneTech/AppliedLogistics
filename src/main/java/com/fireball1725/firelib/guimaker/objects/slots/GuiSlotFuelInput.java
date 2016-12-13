package com.fireball1725.firelib.guimaker.objects.slots;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityFurnace;

import javax.annotation.Nullable;

public class GuiSlotFuelInput extends Slot {
    public GuiSlotFuelInput(IInventory inventoryIn, int index, int xPosition, int yPosition) {
        super(inventoryIn, index, xPosition, yPosition);
    }

    @Override
    public boolean isItemValid(@Nullable ItemStack stack) {
        if (stack == null)
            return false;

        if (!this.inventory.isItemValidForSlot(getSlotIndex(), stack))
            return false;

        return TileEntityFurnace.getItemBurnTime(stack) > 0;
    }
}
