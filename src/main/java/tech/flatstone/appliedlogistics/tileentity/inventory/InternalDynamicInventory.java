package tech.flatstone.appliedlogistics.tileentity.inventory;

import tech.flatstone.appliedlogistics.util.Platform;
import tech.flatstone.appliedlogistics.util.iterators.InventoryIterator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IChatComponent;

import java.util.ArrayList;
import java.util.Iterator;

public class InternalDynamicInventory implements IInventory, Iterable<ItemStack>, IInventoryCustom {
    protected final ArrayList<ItemStack> inventory;
    public boolean enableClientEvents = false;
    protected IInventoryHandler inventoryHandler;
    protected int maxSize;

    public InternalDynamicInventory(IInventoryHandler inventory) {
        this.inventory = new ArrayList<ItemStack>();
        this.inventoryHandler = inventory;
        this.maxSize = 64;
    }

    public boolean isEmpty() {
        for (int i = 0; i < this.getSizeInventory(); i++) {
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
        return inventory.size();
    }

    @Override
    public ItemStack getStackInSlot(int slot) {
        return this.inventory.get(slot);
    }

    @Override
    public ItemStack decrStackSize(int slot, int qty) {
        if (this.inventory.get(slot) != null) {
            ItemStack split = this.getStackInSlot(slot);
            ItemStack newStack;

            if (qty >= split.stackSize) {
                newStack = this.inventory.get(slot);
                this.inventory.set(slot, null);
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
        if (inventory.size() != 0 && inventory.size() >= slot) {
            this.inventory.set(slot, itemStack);
        } else {
            this.inventory.add(itemStack);
        }

        //this.inventoryHandler.onChangeInventory(this, slot, InventoryOperation.setInventorySlotContents, removed, added);

        this.markDirty();
    }

    public void addInventorySlotContents(ItemStack itemStack) {
        this.inventory.add(itemStack);

        //this.inventoryHandler.onChangeInventory(this, slot, InventoryOperation.setInventorySlotContents, removed, added);

        this.markDirty();
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

    public void writeToNBT(NBTTagCompound nbtTagCompound) {
        NBTTagCompound tagCompound = new NBTTagCompound();

        for (int i = 0; i < this.getSizeInventory(); i++) {
            NBTTagCompound item = new NBTTagCompound();
            ItemStack itemStack = this.getStackInSlot(i);
            if (itemStack != null)
                itemStack.writeToNBT(item);
            tagCompound.setTag("item" + i, item);
        }
        nbtTagCompound.setTag("Items", tagCompound);

        nbtTagCompound.setInteger("inventorySize", this.getSizeInventory());
    }

    public void readFromNBT(NBTTagCompound nbtTagCompound) {
        int invSize = 0;
        if (nbtTagCompound.hasKey("inventorySize"))
            invSize = nbtTagCompound.getInteger("inventorySize");

        NBTTagCompound tagCompound = nbtTagCompound.getCompoundTag("Items");
        for (int i = 0; i < invSize; i++) {
            NBTTagCompound item = tagCompound.getCompoundTag("item" + i);
            inventory.add(ItemStack.loadItemStackFromNBT(item));
        }
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