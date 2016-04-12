package tech.flatstone.appliedlogistics.common.tileentities.machines;

import mcp.mobius.waila.api.IWailaConfigHandler;
import mcp.mobius.waila.api.IWailaDataAccessor;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import tech.flatstone.appliedlogistics.common.integrations.waila.IWailaBodyMessage;
import tech.flatstone.appliedlogistics.common.tileentities.TileEntityMachineBase;
import tech.flatstone.appliedlogistics.common.tileentities.inventory.InternalInventory;
import tech.flatstone.appliedlogistics.common.tileentities.inventory.InventoryOperation;

import java.util.List;

public class TileEntityFurnace extends TileEntityMachineBase implements ITickable, IWailaBodyMessage {
    private InternalInventory inventory = new InternalInventory(this, 100);


    @Override
    public ItemStack removeStackFromSlot(int index) {
        return null;
    }

    @Override
    public void update() {

    }

    @Override
    public List<String> getWailaBodyToolTip(ItemStack itemStack, List<String> currentTip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
        return currentTip;
    }

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
     * Furnace Notes:
     *
     * Goals:
     * > multiple inputs
     * > multiple processing
     * > increased production
     * > Auto Eject (upgrade card)
     * > Auto Insert (upgrade card)
     * > Storage Block (Compressor? or piston)
     * > Additional output Storage (Chest)
     *
     * Stone Age:
     * > Multiple Inputs: min 1, max 2
     * > Multiple Processing: min 1, max 2
     * > Increased productivity: +20%
     * > Additional Output Slots: Chest (double the slots)
     */

    public int getSlotRows() {
        return 3;
    }
}
