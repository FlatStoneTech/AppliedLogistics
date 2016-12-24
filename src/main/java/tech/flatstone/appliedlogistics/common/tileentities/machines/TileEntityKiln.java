package tech.flatstone.appliedlogistics.common.tileentities.machines;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import tech.flatstone.appliedlogistics.common.tileentities.TileEntityMachineBase;
import tech.flatstone.appliedlogistics.common.tileentities.inventory.InternalInventory;
import tech.flatstone.appliedlogistics.common.tileentities.inventory.InventoryOperation;

public class TileEntityKiln extends TileEntityMachineBase implements ITickable {
    private InternalInventory inventory = new InternalInventory(this, 100); //todo: lower slot count...
    private int fuelRemaining = 0;
    private int fuelTotal = 0;
    private double intTemperature = 0;
    private int fuelTempTick = 0;
    private int kilnRows = 1;
    private int[] smeltProgress = new int[9];
    private Item lastFuelType;
    private int lastFuelValue;

    @Override
    public void markForUpdate() {
        super.markForUpdate();

        this.markForLightUpdate();
    }

    @Override
    public boolean canBeRotated() {
        return true;
    }

    @Override
    public ItemStack removeStackFromSlot(int index) {
        return null;
    }

    @Override
    public void update() {

    }

//    @Override
//    public List<String> getWailaBodyToolTip(ItemStack itemStack, List<String> currentTip, IWailaDataAccessor accessor, IWailaConfigHandler config) {
//        return currentTip;
//    }

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

    public int getIntTemperature() {
        return (int) intTemperature;
    }
}
