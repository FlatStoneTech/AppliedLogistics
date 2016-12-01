package tech.flatstone.appliedlogistics.common.tileentities.machines;

import mcp.mobius.waila.api.IWailaConfigHandler;
import mcp.mobius.waila.api.IWailaDataAccessor;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import tech.flatstone.appliedlogistics.api.features.TechLevel;
import tech.flatstone.appliedlogistics.common.integrations.waila.IWailaBodyMessage;
import tech.flatstone.appliedlogistics.common.items.Items;
import tech.flatstone.appliedlogistics.common.tileentities.TileEntityMachineBase;
import tech.flatstone.appliedlogistics.common.tileentities.inventory.InternalInventory;
import tech.flatstone.appliedlogistics.common.tileentities.inventory.InventoryOperation;
import tech.flatstone.appliedlogistics.common.util.EnumOres;
import tech.flatstone.appliedlogistics.common.util.InventoryHelper;

import java.util.List;

public class TileEntityFurnace extends TileEntityMachineBase implements ITickable, IWailaBodyMessage {
    public static final float HIGHEST_MAX_TEMP = 380;
    public static final float LOWEST_MAX_TEMP = 23;

    private InternalInventory inventory = new InternalInventory(this, 100);


















    private int fuelRemaining = 0;
    private int fuelTotal = 0;
    private double intTemperature = 0;
    private int fuelTempTick = 0;
    private int furnaceRows = 1;
    private boolean upgradeExtraSlots = false;
    private int[] smeltProgress = new int[9];
    private int maxProcessItems = 1;
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
    public void initMachineData() {
        super.initMachineData();

        int furnaceCount = 1;
        int gearCount = 0;

        NBTTagCompound machineItemData = this.getMachineItemData();
        if (machineItemData != null) {
            for (int i = 0; i < 27; i++) {
                if (machineItemData.hasKey("item_" + i)) {
                    ItemStack item = ItemStack.loadItemStackFromNBT(machineItemData.getCompoundTag("item_" + i));

                    if (ItemStack.areItemsEqual(item, Items.ITEM_MATERIAL_GEAR.getStack(1, EnumOres.IRON.getMeta())))
                        gearCount = item.stackSize;

                    if (ItemStack.areItemsEqual(item, new ItemStack(Blocks.FURNACE)))
                        furnaceCount = item.stackSize;

                    if (ItemStack.areItemsEqual(item, new ItemStack(Blocks.CHEST)))
                        upgradeExtraSlots = true;
                }
            }
        }

        furnaceCount--;
        if (furnaceCount == 1 && gearCount == 1)
            furnaceRows = 2;
    }

    @Override
    public void update() {
        if (fuelRemaining == 0 && inventory.getStackInSlot(0) != null && net.minecraft.tileentity.TileEntityFurnace.getItemBurnTime(inventory.getStackInSlot(0)) > 0) {
            if (inventory.getStackInSlot(0).getItem() == lastFuelType) {
                fuelRemaining = lastFuelValue;
            } else {
                fuelRemaining = net.minecraft.tileentity.TileEntityFurnace.getItemBurnTime(inventory.getStackInSlot(0));
                lastFuelType = inventory.getStackInSlot(0).getItem();
                lastFuelValue = fuelRemaining;
            }
            fuelTotal = fuelRemaining;
            inventory.decrStackSize(0, 1);
            this.markDirty();
            this.markForUpdate();
        }

        if (fuelRemaining > 0) {
            --fuelRemaining;
        }

        if (intTemperature < this.getMaxTemp() && fuelRemaining > 0) {
            fuelTempTick++;
        }

        if (fuelRemaining == 0 && fuelTempTick > 0) {
            fuelTempTick -= 0.2;
            fuelTotal = 0;

            if (fuelTempTick <= 0) {
                fuelTempTick = 0;

                this.markDirty();
                this.markForUpdate();
            }
        }

        intTemperature = (Math.sqrt(fuelTempTick) * 10);

        for (int i = 0; i < furnaceRows; i++) {
            // Check inputs for item, then move to process slot
            ItemStack processItem = inventory.getStackInSlot(i * 7 + 3);
            if (processItem == null) {
                smeltProgress[i] = 0;

                ItemStack input1 = inventory.getStackInSlot(i * 7 + 1);
                ItemStack input2 = inventory.getStackInSlot(i * 7 + 2);

                if (input1 == null && input2 == null)
                    continue;

                if (ItemStack.areItemsEqual(input1, input2)) {
                    // todo: have it pull multiple items from both slots if not enough in slot 1
                }

                if (input1 == null) {
                    inventory.setInventorySlotContents(i * 7 + 3, inventory.decrStackSize(i * 7 + 2, maxProcessItems));
                    this.markForUpdate();
                    this.markDirty();
                } else {
                    inventory.setInventorySlotContents(i * 7 + 3, inventory.decrStackSize(i * 7 + 1, maxProcessItems));
                    this.markForUpdate();
                    this.markDirty();
                }
            }

            // Checking to process items
            if (processItem != null && getMultiplier() > 0) {
                smeltProgress[i] += getMultiplier();

                if (smeltProgress[i] > 300) {
                    smeltProgress[i] = 300;
                    if (FurnaceRecipes.instance().getSmeltingResult(processItem.copy()) == null)
                        continue;
                    ItemStack outputStack = FurnaceRecipes.instance().getSmeltingResult(processItem.copy()).copy();
                    outputStack.stackSize = processItem.stackSize * outputStack.stackSize;
                    int slotsToCheck = upgradeExtraSlots ? 4 : 1;
                    int slotMin = i * 7 + 3 + 1;
                    int slotMax = i * 7 + 3 + slotsToCheck;
                    if (InventoryHelper.addItemStackToInventory(outputStack, inventory, slotMin, slotMax, true) == null) {
                        InventoryHelper.addItemStackToInventory(outputStack, inventory, slotMin, slotMax);
                        inventory.setInventorySlotContents(i * 7 + 3, null);
                        this.markForUpdate();
                        this.markDirty();
                    }
                }
            } else {
                if (smeltProgress[i] > 0)
                    smeltProgress[i]--;
            }
        }
    }

    public int getIntTemperature() {
        return (int) intTemperature;
    }

    public int getMultiplier() {
        int speed = (int) Math.floor((intTemperature - 100) / 100);
        if (speed < 0) speed = 0;
        return speed;
    }

    public int getMaxTemp() {
        return 200;
    }

    public int getFurnaceRows() {
        return furnaceRows;
    }

    public boolean isUpgradeExtraSlots() {
        return upgradeExtraSlots;
    }

    public int getFuelOffset() {
        return Math.round(((((float) fuelRemaining) / (float) fuelTotal)) * 100);
    }

    public int getSmeltProgress(int row) {
        return Math.round(((float) smeltProgress[row] / (float) 300) * 100);
    }

    private boolean canSmelt(ItemStack stack) {
        ItemStack itemstack = FurnaceRecipes.instance().getSmeltingResult(stack);
        return itemstack != null;
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

    @Override
    public void readFromNBT(NBTTagCompound nbtTagCompound) {
        super.readFromNBT(nbtTagCompound);

        fuelRemaining = nbtTagCompound.getInteger("fuelRemaining");
        intTemperature = nbtTagCompound.getDouble("intTemperature");
        fuelTempTick = nbtTagCompound.getInteger("fuelTickTemp");
        fuelTotal = nbtTagCompound.getInteger("fuelTotal");
        furnaceRows = nbtTagCompound.getInteger("furnaceRows");
        upgradeExtraSlots = nbtTagCompound.getBoolean("upgradeExtraSlots");
        smeltProgress = nbtTagCompound.getIntArray("smeltProgress");
        maxProcessItems = nbtTagCompound.getInteger("maxProcessItems");
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbtTagCompound) {
        super.writeToNBT(nbtTagCompound);

        nbtTagCompound.setInteger("fuelRemaining", fuelRemaining);
        nbtTagCompound.setDouble("intTemperature", intTemperature);
        nbtTagCompound.setInteger("fuelTickTemp", fuelTempTick);
        nbtTagCompound.setInteger("fuelTotal", fuelTotal);
        nbtTagCompound.setInteger("furnaceRows", furnaceRows);
        nbtTagCompound.setBoolean("upgradeExtraSlots", upgradeExtraSlots);
        nbtTagCompound.setIntArray("smeltProgress", smeltProgress);
        nbtTagCompound.setInteger("maxProcessItems", maxProcessItems);

        return nbtTagCompound;
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

    /**
     * Additional Furnace Notes:
     *
     * Stone Furnace:
     * Base Max Temp: 120C
     * Max Temp: 120C
     * Base Input Slots: 1
     * Base Output Slots: 2
     * Max Input Slots: 1
     * Max Output Slots: 4
     * Base Furnaces: 1
     * Max Furnaces: 2
     * Max Speed: 1x
     *
     * Bronze Furnace:
     * Base Max Temp: 160C
     * Max Temp: 200C
     * Base Input Slots: 2
     * Base Output Slots: 2
     * Max Input Slots: 2
     * Max Output Slots: 4
     * Base Furnaces: 1
     * Max Furnaces: 2
     * Max Speed: 2x
     *
     * Mechanical Furnace:
     * Base Max Temp: 240C
     * Max Temp: 300C
     * Base Input Slots: 2
     * Base Output Slots: 2
     * Max Input Slots: 2
     * Max Output Slots: 4
     * Base Furnaces: 1
     * Max Furnaces: 3
     * Max Speed: 4x
     *
     * Industrial / Digital Furnace:
     * Base Max Temp: 300C
     * Max Temp: 380C
     * Base Input Slots: 2
     * Base Output Slots: 2
     * Max Input Slots: 2
     * Max Output Slots: 4
     * Base Furnaces: 1
     * Max Furnaces: 4
     * Max Speed: 8x
     */
}
