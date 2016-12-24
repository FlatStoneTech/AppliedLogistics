package tech.flatstone.appliedlogistics.common.tileentities.misc;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.particle.IParticleFactory;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.IFluidTankProperties;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import tech.flatstone.appliedlogistics.client.particles.ParticleCauldronFlame;
import tech.flatstone.appliedlogistics.client.particles.ParticleCauldronSmokeNormal;
import tech.flatstone.appliedlogistics.common.blocks.misc.BlockCauldron;
import tech.flatstone.appliedlogistics.common.sounds.Sounds;
import tech.flatstone.appliedlogistics.common.tileentities.TileEntityInventoryBase;
import tech.flatstone.appliedlogistics.common.tileentities.inventory.InternalInventory;
import tech.flatstone.appliedlogistics.common.tileentities.inventory.InventoryOperation;

import javax.annotation.Nullable;
import java.util.EnumMap;
import java.util.Map;

public class TileEntityCauldron extends TileEntityInventoryBase implements IFluidHandler, IItemHandler, ITickable {
    private static final AxisAlignedBB AABB_FLUID = new AxisAlignedBB(0.1875, 0.3125, 0.1875, 0.8125, 0.8125, 0.8125);
    private static final float MAX_HANDLE_ROTATION = -0.43F;
    private static final float TICKS_PRECIPITATION_DELAY = 600;
    private static final float TICKS_PRECIPITATION_TOTAL = 1200;
    private static final double MAXIMUM_PRECIPITATE_LEVEL = 0.0625;
    private InternalInventory internalInventory = new InternalInventory(this, 2);
    private float handleRotation = MAX_HANDLE_ROTATION;
    private boolean handleRebounded, handleHasEnergy;
    private boolean fireLit = false;
    private int tickCounter, progressTicks;
    private double waterTemp = 22.0;
    private FluidTank tank = new FluidTank(Fluid.BUCKET_VOLUME);
    private Map<EnumItemType, Integer> itemCountMap = new EnumMap<>(EnumItemType.class);

    public boolean isFireLit() {
        return fireLit;
    }

    public void setFireLit(boolean fireLit) {
        this.fireLit = fireLit;
    }

    @Override
    public void readFromNBT(NBTTagCompound nbtTagCompound) {
        super.readFromNBT(nbtTagCompound);

        fireLit = nbtTagCompound.getBoolean("fireLit");
        for (EnumItemType itemType : EnumItemType.values()) {
            if (nbtTagCompound.hasKey(itemType.name())) {
                if (itemCountMap.containsKey(itemType))
                    itemCountMap.remove(itemType);

                itemCountMap.put(itemType, nbtTagCompound.getInteger(itemType.name()));
            }
        }
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbtTagCompound) {
        nbtTagCompound = super.writeToNBT(nbtTagCompound);

        nbtTagCompound.setBoolean("fireLit", this.fireLit);
        for (EnumItemType itemType : itemCountMap.keySet()) {
            nbtTagCompound.setInteger(itemType.name(), itemCountMap.get(itemType));
        }

        return nbtTagCompound;
    }

    @Override
    public boolean shouldRenderInPass(int pass) {
        return true;
    }

    public void setItemCount(EnumItemType itemType, int count) {
        if (itemCountMap.containsKey(itemType))
            itemCountMap.remove(itemType);

        itemCountMap.put(itemType, count);
    }

    public int getItemCount(EnumItemType itemType) {
        return itemCountMap.containsKey(itemType) ? itemCountMap.get(itemType) : 0;
    }

    public boolean isPrecursor() {
        return tank.getFluid() != null && tank.getFluid().getFluid() == FluidRegistry.getFluid("Precursor");
    }

    public boolean isPureWater() {
        return getItemCount(EnumItemType.ALUMINUM) == 0 && getItemCount(EnumItemType.SILICA) == 0 && (tank.getFluid() == null || tank.getFluid().getFluid() == FluidRegistry.WATER);

    }

    public void setPureWater() {
        itemCountMap.clear();
        world.notifyNeighborsOfStateChange(pos, blockType, false);
    }

    public double getWaterTemp() {
        return waterTemp;
    }

    public double getWaterLevel() {
        return 0.3125 + (tank.getFluidAmount() / (double) tank.getCapacity()) * 0.5;
    }

    public AxisAlignedBB getFluidCollisionBox() {
        return new AxisAlignedBB(AABB_FLUID.minX, AABB_FLUID.minY, AABB_FLUID.minZ, AABB_FLUID.maxX, AABB_FLUID.maxY, AABB_FLUID.maxZ);
    }

    public boolean hasSolidPrecipitate() {
        return getSolidPrecipitateLevel() > BlockCauldron.AABB_BASE.maxY;
    }

    public boolean hasMaximunSolidPrecipitate() {
        return getSolidPrecipitateLevel() == BlockCauldron.AABB_BASE.maxY + 0.001 + MAXIMUM_PRECIPITATE_LEVEL;
    }

    public double getSolidPrecipitateLevel() {
        return progressTicks <= TICKS_PRECIPITATION_DELAY ? -1 : BlockCauldron.AABB_BASE.maxY + 0.001 + (getPrecipitationProgressTicks() / TICKS_PRECIPITATION_TOTAL) * MAXIMUM_PRECIPITATE_LEVEL;
    }

    public int getPrecipitationProgressTicks() {
        return (int) (progressTicks - TICKS_PRECIPITATION_DELAY);
    }

    public int getProgressTicks() {
        return progressTicks;
    }

    public void setProgressTicks(int progressTicks) {
        this.progressTicks = progressTicks;
    }

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

    private void rotateHandle() {
        boolean wasLifted = handleRotation > MAX_HANDLE_ROTATION;
        handleRotation -= 0.03999999910593033;
        handleRotation *= 4;
        if (handleRotation <= MAX_HANDLE_ROTATION) {
            handleRotation = MAX_HANDLE_ROTATION;
            if (handleHasEnergy) {
                if (wasLifted && !handleRebounded) {
                    handleRebounded = true;
                    world.playSound(pos.getX(), pos.getY(), pos.getZ(), Sounds.CAULDRON_HANDLE.getSound(), SoundCategory.MASTER, 0.6F, 1.0F, true);
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

    @SideOnly(Side.CLIENT)
    @Override
    public void update() {
        tickCounter++;
        if (world.isRemote) {
            if (tickCounter > 1 && (handleRotation > MAX_HANDLE_ROTATION || handleHasEnergy))
                rotateHandle();
        }

        IBlockState state = world.getBlockState(getPos());
        if (!(state.getBlock() instanceof BlockCauldron))
            return;

        BlockCauldron blockCauldron = (BlockCauldron) state.getBlock();

        if (this.fireLit && world.isRemote && (tickCounter >= 400 || world.rand.nextInt() < tickCounter)) {
            IParticleFactory[] particles = new IParticleFactory[4];
            particles[0] = new ParticleCauldronFlame.Factory();
            for (int i = 1; i < 4; i++) {
                particles[i] = new ParticleCauldronSmokeNormal.Factory();
            }
            blockCauldron.spawnParticlesForLogs(world, pos, null, 5, particles);
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

    public boolean isEmpty() {
        return tank.getFluidAmount() == 0;
    }

    @Override
    public IFluidTankProperties[] getTankProperties() {
        return tank.getTankProperties();
    }

    @Override
    public int fill(FluidStack resource, boolean doFill) {
        return 0;
    }

    @Nullable
    @Override
    public FluidStack drain(FluidStack resource, boolean doDrain) {
        return null;
    }

    @Nullable
    @Override
    public FluidStack drain(int maxDrain, boolean doDrain) {
        return null;
    }

    @Override
    public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
        return capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY || capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY || super.hasCapability(capability, facing);
    }

    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
        if (capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY || capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
            return (T) this;

        return super.getCapability(capability, facing);
    }

    @Override
    public int getSlots() {
        return 0;
    }

    @Override
    public ItemStack insertItem(int slot, ItemStack stack, boolean simulate) {
        return null;
    }

    @Override
    public ItemStack extractItem(int slot, int amount, boolean simulate) {
        return null;
    }

    @Override
    public int getSlotLimit(int slot) {
        return 0;
    }

    public enum EnumItemType {
        ALUMINUM, SILICA;
    }
}
