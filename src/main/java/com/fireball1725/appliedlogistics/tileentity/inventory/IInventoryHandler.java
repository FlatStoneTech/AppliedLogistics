package com.fireball1725.appliedlogistics.tileentity.inventory;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

public interface IInventoryHandler {
    void saveChanges();

    void onChangeInventory(IInventory inv, int slot, InventoryOperation inventoryOperation, ItemStack removedStack, ItemStack newStack);
}
