package tech.flatstone.appliedlogistics.common.tileentities.transport;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import tech.flatstone.appliedlogistics.AppliedLogistics;
import tech.flatstone.appliedlogistics.common.tileentities.TileEntityMachineBase;
import tech.flatstone.appliedlogistics.common.tileentities.inventory.InventoryOperation;

import javax.annotation.Nullable;
import java.util.UUID;

public class TIleEntityPipe extends TileEntityMachineBase implements ITickable {
    private UUID nodeUUID;

    @Override
    public void validate(){
        super.validate();
        nodeUUID = AppliedLogistics.instance.transportGrid.createTransportNode();
    }

    @Override
    public void invalidate() {
        super.invalidate();
        AppliedLogistics.instance.transportGrid.removeNode(nodeUUID);
    }

    @Override
    public IInventory getInternalInventory() {
        return null;
    }

    @Override
    public void onChangeInventory(IInventory inv, int slot, InventoryOperation operation, ItemStack removed, ItemStack added) {

    }

    @Override
    public int[] getAccessibleSlotsBySide(EnumFacing side) {
        return new int[0];
    }

    /**
     * Removes a stack from the given slot and returns it.
     *
     * @param index
     */
    @Nullable
    @Override
    public ItemStack removeStackFromSlot(int index) {
        return null;
    }

    /**
     * Like the old updateEntity(), except more generic.
     */
    @Override
    public void update() {

    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbtTagCompound) {
        super.writeToNBT(nbtTagCompound);
        nbtTagCompound.setUniqueId("nodeUUID", nodeUUID);
        return nbtTagCompound;
    }

    @Override
    public void readFromNBT(NBTTagCompound nbtTagCompound) {
        super.readFromNBT(nbtTagCompound);
        nodeUUID = nbtTagCompound.getUniqueId("nodeUUID");
    }
}
