package tech.flatstone.appliedlogistics.tileentity.inventory;

import net.minecraft.nbt.NBTTagCompound;

public interface IInventoryCustom {
    void writeToNBT(NBTTagCompound nbtTagCompound);

    void readFromNBT(NBTTagCompound nbtTagCompound);
}
