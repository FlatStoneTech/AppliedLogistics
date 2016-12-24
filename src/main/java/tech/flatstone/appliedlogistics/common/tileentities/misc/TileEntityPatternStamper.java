package tech.flatstone.appliedlogistics.common.tileentities.misc;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import tech.flatstone.appliedlogistics.api.features.TechLevel;
import tech.flatstone.appliedlogistics.common.items.Items;
import tech.flatstone.appliedlogistics.common.tileentities.TileEntityMachineBase;
import tech.flatstone.appliedlogistics.common.tileentities.inventory.InternalInventory;
import tech.flatstone.appliedlogistics.common.tileentities.inventory.InventoryOperation;
import tech.flatstone.appliedlogistics.common.util.LogHelper;

import javax.annotation.Nullable;

public class TileEntityPatternStamper extends TileEntityMachineBase {
    InternalInventory inventory = new InternalInventory(this, 2);

    private boolean creativeMode = false;
    private TechLevel planTechLevel = null;

    @Override
    public boolean canBeRotated() {
        return true;
    }

    @Override
    public void readFromNBT(NBTTagCompound nbtTagCompound) {
        super.readFromNBT(nbtTagCompound);

        creativeMode = nbtTagCompound.getBoolean("creativeMode");

        if (nbtTagCompound.getInteger("techLevel") == -1)
            planTechLevel = null;
        else
            planTechLevel = TechLevel.byMeta(nbtTagCompound.getInteger("techLevel"));

        LogHelper.info(">>> NBT Read");
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbtTagCompound) {
        super.writeToNBT(nbtTagCompound);

        nbtTagCompound.setBoolean("creativeMode", creativeMode);

        int techLevelMeta = planTechLevel == null ? -1 : planTechLevel.getMeta();
        nbtTagCompound.setInteger("techLevel", techLevelMeta);

        LogHelper.info(">>> NBT Write");

        return nbtTagCompound;
    }

    @Override
    public IInventory getInternalInventory() {
        return inventory;
    }

    @Override
    public void onChangeInventory(IInventory inv, int slot, InventoryOperation operation, ItemStack removed, ItemStack added) {
        LogHelper.info(String.format("Inventory: %s | slot: %d | Operation: %s | Removed ItemStack: %s | Added ItemStack: %s", inv.toString(), slot, operation, removed, added));

        if (slot == 0) {
            creativeMode = false;
            planTechLevel = null;

            this.markDirty();
            this.markForUpdate();

            ItemStack itemStack = inv.getStackInSlot(0);

            if (itemStack == null)
                return;

            if (!isPlanValid())
                return;

            if (itemStack.getItemDamage() == TechLevel.CREATIVE.getMeta()) {
                creativeMode = true;
                planTechLevel = TechLevel.byMeta(itemStack.getTagCompound().getInteger("planBaseMeta"));
            } else {
                planTechLevel = TechLevel.byMeta(itemStack.getItemDamage());
            }

            this.markDirty();
            this.markForUpdate();
        }
    }

    public boolean isPlanValid() {
        ItemStack itemStack = inventory.getStackInSlot(0);

        return itemStack != null && (itemStack.getItem() == Items.ITEM_PLAN_BLANK.getItem());
    }

    @Override
    public int[] getAccessibleSlotsBySide(EnumFacing side) {
        return new int[0];
    }

    @Nullable
    @Override
    public ItemStack removeStackFromSlot(int index) {
        return null;
    }

    @Override
    public void dropItems() {
        super.dropItems();
    }

    @Override
    public int getInventoryStackLimit() {
        return super.getInventoryStackLimit();
    }

    public boolean isCreativeMode() {
        return creativeMode;
    }

    public TechLevel getPlanTechLevel() {
        return planTechLevel;
    }
}
