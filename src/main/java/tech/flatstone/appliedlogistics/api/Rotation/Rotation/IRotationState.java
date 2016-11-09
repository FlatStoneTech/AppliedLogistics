package tech.flatstone.appliedlogistics.api.Rotation.Rotation;

import net.minecraft.util.EnumFacing;

public interface IRotationState {
    int[] getDefaults();
    EnumFacing getbyside(Face side);
    Face getbyEF(EnumFacing facing);
    int[] Save();

    IRotationState Load(int[] sides);
    IRotationState copy();
    IRotationState applyRT(Face side, EnumFacing face);

    boolean validate();
}
