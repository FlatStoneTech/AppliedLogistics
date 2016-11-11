package tech.flatstone.appliedlogistics.common.tileentities.transport;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import tech.flatstone.appliedlogistics.common.tileentities.TileEntityMachineBase;
import tech.flatstone.appliedlogistics.common.tileentities.inventory.InternalInventory;
import tech.flatstone.appliedlogistics.common.tileentities.inventory.InventoryOperation;

import javax.annotation.Nullable;
import java.util.UUID;

public class TileEntityExit extends TileEntityMachineBase implements ITickable {
    private InternalInventory inventory = new InternalInventory(this, 1);
    private UUID nodeUUID;
    private boolean loaded = false;

    @Override
    public IInventory getInternalInventory() {
        return inventory;
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
}
