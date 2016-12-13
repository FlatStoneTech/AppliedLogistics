package com.fireball1725.firelib.guimaker.objects.slots;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;

import javax.annotation.Nullable;

public class GuiSlotFurnaceInput extends Slot {
    public GuiSlotFurnaceInput(IInventory inventoryIn, int index, int xPosition, int yPosition) {
        super(inventoryIn, index, xPosition, yPosition);
    }

    @Override
    public boolean isItemValid(@Nullable ItemStack stack) {
        if (stack == null)
            return false;

        if (!this.inventory.isItemValidForSlot(getSlotIndex(), stack))
            return false;

        return canItemSmelt(stack);
    }

    private boolean canItemSmelt(ItemStack stack) {
        return FurnaceRecipes.instance().getSmeltingResult(stack) != null;
    }
}
