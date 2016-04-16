package tech.flatstone.appliedlogistics.common.util;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

public class InventoryHelper {
    public static ItemStack addItemStackToInventory(ItemStack itemIn, IInventory inventory, int slotStart, int slotEnd) {
        return addItemStackToInventory(itemIn, inventory, slotStart, slotEnd, false);
    }

    public static ItemStack addItemStackToInventory(ItemStack itemIn, IInventory inventory, int slotStart, int slotEnd, boolean simulate) {
        ItemStack itemOut = itemIn.copy();

        for (int i = slotStart; i <= slotEnd; i++) {
            ItemStack slotItemStack = inventory.getStackInSlot(i) == null ? null : inventory.getStackInSlot(i).copy();
            if (itemOut == null) return null;
            if (slotItemStack == null) {
                if (!simulate) inventory.setInventorySlotContents(i, itemOut);
                return null;
            }

            if (!(ItemStack.areItemsEqual(itemOut, slotItemStack))) continue;

            if (slotItemStack.stackSize == slotItemStack.getMaxStackSize()) continue;

            if (itemOut.stackSize + slotItemStack.stackSize >= slotItemStack.getMaxStackSize()) {
                int sizeRemaining = slotItemStack.getMaxStackSize() - slotItemStack.stackSize;
                itemOut.stackSize = itemOut.stackSize - sizeRemaining;
                if (!simulate) slotItemStack.stackSize = slotItemStack.stackSize + sizeRemaining;
                if (!simulate) inventory.setInventorySlotContents(i, slotItemStack);
                if (itemOut.stackSize == 0) itemOut = null;
                continue;
            }

            slotItemStack.stackSize = slotItemStack.stackSize + itemOut.stackSize;
            itemOut = null;
            if (!simulate) inventory.setInventorySlotContents(i, slotItemStack);
            break;
        }

        if (itemOut != null)
            return itemOut;

        return null;
    }
}
