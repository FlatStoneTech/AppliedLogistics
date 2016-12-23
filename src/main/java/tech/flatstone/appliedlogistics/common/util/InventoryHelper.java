package tech.flatstone.appliedlogistics.common.util;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

public class InventoryHelper {
    public static ItemStack addItemStackToInventory(ItemStack itemIn, IInventory inventory, int slotStart, int slotEnd) {
        return addItemStackToInventory(itemIn, inventory, slotStart, slotEnd, false);
    }

    public static ItemStack addItemStackToInventory(ItemStack itemIn, IInventory inventory, int slotStart, int slotEnd, boolean simulate) {
        if (itemIn == null) return null;
        ItemStack itemOut = itemIn.copy();

        for (int i = slotStart; i <= slotEnd; i++) {
            ItemStack slotItemStack = inventory.getStackInSlot(i).copy();
            if (itemOut == null) return null;

            if (!(ItemStack.areItemsEqual(itemOut, slotItemStack))) continue;

            if (slotItemStack.getCount() == slotItemStack.getMaxStackSize()) continue;

            if (itemOut.getCount() + slotItemStack.getCount() >= slotItemStack.getMaxStackSize()) {
                int sizeRemaining = slotItemStack.getMaxStackSize() - slotItemStack.getCount();
                itemOut.shrink(sizeRemaining);
                if (!simulate) slotItemStack.grow(sizeRemaining);
                if (!simulate) inventory.setInventorySlotContents(i, slotItemStack);
                if (itemOut.getCount() == 0) itemOut = null;
                continue;
            }

            slotItemStack.grow(itemOut.getCount());
            itemOut = null;
            if (!simulate) inventory.setInventorySlotContents(i, slotItemStack);
            break;
        }

        if (itemOut != null)
            return itemOut;

        return null;
    }
}
