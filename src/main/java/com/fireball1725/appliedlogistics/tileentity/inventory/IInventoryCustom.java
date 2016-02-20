package com.fireball1725.appliedlogistics.tileentity.inventory;

import net.minecraft.nbt.NBTTagCompound;

public interface IInventoryCustom {
    void writeToNBT(NBTTagCompound nbtTagCompound);

    void readFromNBT(NBTTagCompound nbtTagCompound);
}
