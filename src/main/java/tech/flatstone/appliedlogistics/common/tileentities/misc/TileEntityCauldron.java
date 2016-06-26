package tech.flatstone.appliedlogistics.common.tileentities.misc;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.SoundCategory;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidTank;
import tech.flatstone.appliedlogistics.common.sounds.Sounds;
import tech.flatstone.appliedlogistics.common.tileentities.TileEntityInventoryBase;
import tech.flatstone.appliedlogistics.common.tileentities.inventory.InternalInventory;
import tech.flatstone.appliedlogistics.common.tileentities.inventory.InventoryOperation;
import tech.flatstone.appliedlogistics.common.util.IRotatable;
import tech.flatstone.appliedlogistics.common.util.Platform;
import tech.flatstone.appliedlogistics.common.util.TileHelper;

import javax.annotation.Nullable;

public class TileEntityCauldron extends TileEntityInventoryBase implements IFluidTank, ITickable {
    private InternalInventory internalInventory = new InternalInventory(this, 2);
    private static final float MAX_HANDLE_ROTATION = -0.43F;
    private float handleRotation = MAX_HANDLE_ROTATION;
    private boolean handleRebounded, handleHasEnergy;
    private int tickCounter;

    @Override
    public IInventory getInternalInventory() {
        return internalInventory;
    }

    @Override
    public void onChangeInventory(IInventory inv, int slot, InventoryOperation operation, ItemStack removed, ItemStack added) {

    }

    @Override
    public boolean canBeRotated() {
        return true;
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

    @Nullable
    @Override
    public FluidStack getFluid() {
        return null;
    }

    @Override
    public int getFluidAmount() {
        return 0;
    }

    @Override
    public int getCapacity() {
        return 1000;
    }

    @Override
    public FluidTankInfo getInfo() {
        return null;
    }

    @Override
    public int fill(FluidStack resource, boolean doFill) {
        return 0;
    }

    @Nullable
    @Override
    public FluidStack drain(int maxDrain, boolean doDrain) {
        return null;
    }

    private void rotateHandle() {
        boolean wasLifted = handleRotation > MAX_HANDLE_ROTATION;
        handleRotation -= 0.03999999910593033;
        handleRotation *= 4;
        if (handleRotation <= MAX_HANDLE_ROTATION) {
            handleRotation = MAX_HANDLE_ROTATION;
            if (handleHasEnergy) {
                if (wasLifted && !handleRebounded) {
                    handleRebounded = true;
                    //Platform.playSound(Sounds.CAULDRON_HANDLE.getSound());
                    worldObj.playSound(pos.getX(), pos.getY(), pos.getZ(), Sounds.CAULDRON_HANDLE.getSound(), SoundCategory.MASTER, 0.6F, 1.0F, true);
                } else {
                    handleRotation += 0.1F;
                    handleHasEnergy = false;
                }
            }
        }
    }

    public float getHandleRotation() {
        return handleRotation;
    }

    public void liftHandle() {
        handleRotation = 0;
        handleHasEnergy = true;
        tickCounter = 0;
        handleRebounded = false;
    }

    @Override
    public void update() {
        tickCounter++;
        if (worldObj.isRemote) {
            if (tickCounter > 1 && (handleRotation > MAX_HANDLE_ROTATION || handleHasEnergy))
                rotateHandle();
        }
    }

    public float getRotation() {
        EnumFacing facing = getDirection();
        switch (facing) {
            case NORTH:
                return 90;
            case EAST:
                return 0;
            case SOUTH:
                return 270;
            case WEST:
                return 180;
        }
        return 0;
    }

    @Override
    public void onRotated() {
        liftHandle();
    }
}
