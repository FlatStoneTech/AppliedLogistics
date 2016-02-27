package tech.flatstone.appliedlogistics.common.tileentities.inventory;

import net.minecraft.nbt.NBTTagCompound;

public interface IInventoryCustom {
    void writeToNBT(NBTTagCompound nbtTagCompound);

    void readFromNBT(NBTTagCompound nbtTagCompound);
}
