package tech.flatstone.appliedlogistics.api.Rotation.Rotation;

import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;

public interface IRotatable {
    boolean handleRotation(Face side, EnumFacing face, BlockPos pos);

    boolean handleSetFront(EnumFacing side, BlockPos pos);

    Face getbyEF(EnumFacing facing);

    EnumFacing getbyside(Face side);

    IRotationState getRTstate();
}
