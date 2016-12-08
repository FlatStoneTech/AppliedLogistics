package tech.flatstone.appliedlogistics.common.tileentities.misc;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import tech.flatstone.appliedlogistics.api.Rotation.Rotation.Face;
import tech.flatstone.appliedlogistics.api.Rotation.Rotation.IRotatable;
import tech.flatstone.appliedlogistics.api.Rotation.Rotation.IRotationState;
import tech.flatstone.appliedlogistics.api.Rotation.impl.RotationProperty;
import tech.flatstone.appliedlogistics.api.Rotation.impl.basicRotationState;
import tech.flatstone.appliedlogistics.common.blocks.BlockTileBase;
import tech.flatstone.appliedlogistics.common.blocks.RTBlock;
import tech.flatstone.appliedlogistics.common.tileentities.TileEntityBase;

import java.util.Arrays;

public class TileEntityRT extends TileEntityBase implements IRotatable {
    IRotationState state;

    @Override
    public EnumFacing getForward() {
        return getRTstate().getbyside(Face.Front);
    }

    public void writeNbt(NBTTagCompound nbt) {
        if (state != null)
            nbt.setIntArray("Sides", state.Save());
        else {
            RTBlock block = (RTBlock) worldObj.getBlockState(pos).getBlock();
            state = block.getRTState().copy();
        }

    }

//    @Override
//    public void setToptooltip(ProbeMode mode, IProbeInfo info, IProbeHitData data, EntityPlayer player) {
//        if (!getBlockState().getBlock().getDefaultState().equals(getBlockState()) && mode == ProbeMode.EXTENDED) {
//            info.text("Rotated.");
//            info.text(String.format("Top:%s", getRTstate().getbyside(Face.Top)));
//            info.text(String.format("Front:%s", getRTstate().getbyside(Face.Front)));
//        }
//    }

    public IRotationState loadFromNbt(NBTTagCompound nbt) {
        if (nbt.hasKey("Sides"))
            return new basicRotationState(nbt.getIntArray("Sides"));
        else if (state == null) {
            RTBlock block = (RTBlock) worldObj.getBlockState(pos).getBlock();
            state = block.getRTState().copy();
        }
        return state;
    }

    @Override
    public IRotationState getRTstate() {
        return state;
    }

    @Override
    public boolean handleRotation(Face side, EnumFacing face, BlockPos pos) {
        if (state.getbyside(side).equals(face)) return false;
        state.applyRT(side, face);
        if (!Arrays.equals(state.Save(), state.getDefaults()))
            worldObj.setBlockState(pos, getBlockState().getBlock().getDefaultState().
                    withProperty(BlockTileBase.getRotationProperty(), RotationProperty.parseValue(state)));
        else
            worldObj.setBlockState(pos, getBlockState().getBlock().getDefaultState());
        markDirty();
        return true;
    }

    @Override
    public boolean handleSetFront(EnumFacing side, BlockPos pos) {
        if (state.getbyside(Face.Front).equals(side)) return false;
        state.applyRT(Face.Front, side);
        if (!Arrays.equals(state.Save(), state.getDefaults()))
            worldObj.setBlockState(pos, getBlockState().getBlock().getDefaultState().
                    withProperty(BlockTileBase.getRotationProperty(), RotationProperty.parseValue(state)));
        else
            worldObj.setBlockState(pos, getBlockState().getBlock().getDefaultState());
        markDirty();
        return true;
    }

    @Override
    public Face getbyEF(EnumFacing facing) {
        return state.getbyEF(facing);
    }

    @Override
    public EnumFacing getbyside(Face side) {
        return state.getbyside(side);
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbtTagCompound) {
        writeNbt(nbtTagCompound);
        super.writeToNBT(nbtTagCompound);
        return nbtTagCompound;
    }

    @Override
    public void readFromNBT(NBTTagCompound nbtTagCompound) {
        super.readFromNBT(nbtTagCompound);
        state = loadFromNbt(nbtTagCompound);
    }

    @Override
    public NBTTagCompound getUpdateTag() {
        return writeToNBT(new NBTTagCompound());
    }
}