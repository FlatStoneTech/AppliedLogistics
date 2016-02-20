package com.fireball1725.appliedlogistics.tileentity.inventory;

import com.fireball1725.appliedlogistics.tileentity.TileEntityBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;

public abstract class TileEntityInventoryBase extends TileEntityBase implements ISidedInventory, IInventoryHandler {
    public abstract IInventory getInternalInventory();

    @Override
    public void readFromNBT(NBTTagCompound nbtTagCompound) {
        super.readFromNBT(nbtTagCompound);

        if (getInternalInventory() instanceof IInventoryCustom) {
            IInventoryCustom inventoryCustom = (IInventoryCustom)getInternalInventory();
            inventoryCustom.readFromNBT(nbtTagCompound);
        } else {
            IInventory inventory = this.getInternalInventory();
            NBTTagCompound tagCompound = nbtTagCompound.getCompoundTag("Items");
            for (int i = 0; i < inventory.getSizeInventory(); i++) {
                NBTTagCompound item = tagCompound.getCompoundTag("item" + i);
                inventory.setInventorySlotContents(i, ItemStack.loadItemStackFromNBT(item));
            }
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound nbtTagCompound) {
        super.writeToNBT(nbtTagCompound);

        if (getInternalInventory() instanceof IInventoryCustom) {
            IInventoryCustom inventoryCustom = (IInventoryCustom)getInternalInventory();
            inventoryCustom.writeToNBT(nbtTagCompound);
        } else {
            IInventory inventory = this.getInternalInventory();
            NBTTagCompound tagCompound = new NBTTagCompound();
            for (int i = 0; i < inventory.getSizeInventory(); i++) {
                NBTTagCompound item = new NBTTagCompound();
                ItemStack itemStack = this.getStackInSlot(i);
                if (itemStack != null)
                    itemStack.writeToNBT(item);
                tagCompound.setTag("item" + i, item);
            }
            nbtTagCompound.setTag("Items", tagCompound);
        }
    }

    @Override
    public int getSizeInventory() {
        return this.getInternalInventory().getSizeInventory();
    }

    @Override
    public ItemStack getStackInSlot(int slot) {
        return this.getInternalInventory().getStackInSlot(slot);
    }

    @Override
    public ItemStack decrStackSize(int i, int j) {
        return this.getInternalInventory().decrStackSize(i, j);
    }

    @Override
    public void setInventorySlotContents(int slot, ItemStack itemStack) {
        this.getInternalInventory().setInventorySlotContents(slot, itemStack);
    }

    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer player) {
        final double squaredMCReach = 64.0D;
        return this.worldObj.getTileEntity(this.pos) == this && player.getDistanceSq(this.pos.getX() + 0.5D, this.pos.getY() + 0.5D, this.getPos().getZ() + 0.5D) <= squaredMCReach;
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
    public abstract void onChangeInventory(IInventory inv, int slot, InventoryOperation operation, ItemStack removed, ItemStack added);

    @Override
    public int[] getSlotsForFace(EnumFacing side) {
        return this.getAccessibleSlotsBySide(side);
    }

    @Override
    public boolean canInsertItem(int index, ItemStack itemStackIn, EnumFacing direction) {
        return this.isItemValidForSlot(index, itemStackIn);
    }

    @Override
    public boolean canExtractItem(int index, ItemStack stack, EnumFacing direction) {
        return true;
    }

    public abstract int[] getAccessibleSlotsBySide(EnumFacing side);

    @Override
    public String getName() {
        return getCustomName();
    }
}
