package tech.flatstone.appliedlogistics.common.util.iterators;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

import java.util.Iterator;

public class InventoryIterator implements Iterator<ItemStack> {
    final IInventory inventory;
    final int size;

    int i = 0;

    public InventoryIterator(IInventory inventory) {
        this.inventory = inventory;
        this.size = this.inventory.getSizeInventory();
    }

    @Override
    public boolean hasNext() {
        return this.i < this.size;
    }

    @Override
    public ItemStack next() {
        ItemStack result = this.inventory.getStackInSlot(this.i);
        this.i++;
        return result;
    }

    @Override
    public void remove() {
        throw new RuntimeException("I'm sorry Dave, I can't let you do that...");
        // haha...
    }

}
