package tech.flatstone.appliedlogistics.tileentity.inventory;

import tech.flatstone.appliedlogistics.util.Platform;
import tech.flatstone.appliedlogistics.util.iterators.InventoryIterator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IChatComponent;

import java.util.Iterator;

public class InternalInventory implements IInventory, Iterable<ItemStack> {
    protected final int size;
    protected final ItemStack[] inventory;
    public boolean enableClientEvents = false;
    protected IInventoryHandler inventoryHandler;
    protected int maxSize;

    public InternalInventory(IInventoryHandler inventory, int size) {
        this.size = size;
        this.inventory = new ItemStack[size];
        this.inventoryHandler = inventory;
        this.maxSize = 64;
    }

    public boolean isEmpty() {
        for (int i = 0; i < this.size; i++) {
            if (this.getStackInSlot(i) != null)
                return false;
        }

        return true;
    }

    protected boolean eventsEnabled() {
        return Platform.isServer() || this.enableClientEvents;
    }

    @Override
    public int getSizeInventory() {
        return this.size;
    }

    @Override
    public ItemStack getStackInSlot(int slot) {
        return this.inventory[slot];
    }

    @Override
    public ItemStack decrStackSize(int slot, int qty) {
        if (this.inventory[slot] != null) {
            ItemStack split = this.getStackInSlot(slot);
            ItemStack newStack = null;

            if (qty >= split.stackSize) {
                newStack = this.inventory[slot];
                this.inventory[slot] = null;
            } else {
                newStack = split.splitStack(qty);
            }

            if (inventoryHandler != null && this.eventsEnabled()) {
                this.inventoryHandler.onChangeInventory(this, slot, InventoryOperation.decreaseStackSize, newStack, null);
            }

            this.markDirty();
            return newStack;
        }

        return null;
    }

    @Override
    public ItemStack removeStackFromSlot(int index) {
        return null;
    }

    @Override
    public void setInventorySlotContents(int slot, ItemStack itemStack) {
        ItemStack oldStack = this.inventory[slot];
        this.inventory[slot] = itemStack;

        if (this.inventoryHandler != null && this.eventsEnabled()) {
            ItemStack removed = oldStack;
            ItemStack added = itemStack;

            if (oldStack != null && itemStack != null && Platform.isSameItem(oldStack, itemStack)) {
                if (oldStack.stackSize > itemStack.stackSize) {
                    removed = removed.copy();
                    removed.stackSize -= itemStack.stackSize;
                } else if (oldStack.stackSize < itemStack.stackSize) {
                    added = added.copy();
                    added.stackSize -= oldStack.stackSize;
                    removed = null;
                } else {
                    removed = added = null;
                }
            }

            this.inventoryHandler.onChangeInventory(this, slot, InventoryOperation.setInventorySlotContents, removed, added);

            this.markDirty();
        }
    }

    @Override
    public int getInventoryStackLimit() {
        return this.maxSize > 64 ? 64 : this.maxSize;
    }

    @Override
    public void markDirty() {
        if (this.inventoryHandler != null && this.eventsEnabled()) {
            this.inventoryHandler.onChangeInventory(this, -1, InventoryOperation.markDirty, null, null);
        }
    }

    public void markDirty(int slotIndex) {
        if (this.inventoryHandler != null && this.eventsEnabled()) {
            this.inventoryHandler.onChangeInventory(this, slotIndex, InventoryOperation.markDirty, null, null);
        }
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer p_70300_1_) {
        return true;
    }

    @Override
    public void openInventory(EntityPlayer player) {

    }

    @Override
    public void closeInventory(EntityPlayer player) {

    }

    @Override
    public boolean isItemValidForSlot(int slot, ItemStack itemStack) {
        return true;
    }

    @Override
    public int getField(int id) {
        return 0;
    }

    @Override
    public void setField(int id, int value) {

    }

    @Override
    public int getFieldCount() {
        return 0;
    }

    @Override
    public void clear() {

    }

    @Override
    public Iterator<ItemStack> iterator() {
        return new InventoryIterator(this);
    }

    public void setMaxStackSize(int s) {
        this.maxSize = s;
    }

    @Override
    public String getName() {
        return "internal";
    }

    @Override
    public boolean hasCustomName() {
        return false;
    }

    @Override
    public IChatComponent getDisplayName() {
        return null;
    }
}