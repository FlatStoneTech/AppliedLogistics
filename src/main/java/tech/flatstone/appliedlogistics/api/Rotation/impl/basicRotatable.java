package tech.flatstone.appliedlogistics.api.Rotation.impl;

import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import tech.flatstone.appliedlogistics.api.Rotation.Rotation.Face;
import tech.flatstone.appliedlogistics.api.Rotation.Rotation.IRotatable;
import tech.flatstone.appliedlogistics.api.Rotation.Rotation.IRotationState;

/**
 * {@link basicRotatable}a class for a sample of how to write a Rotation handler
 */
public class basicRotatable implements IRotatable {
    IRotationState state;

    public basicRotatable() {
        state = new basicRotationState();
    }

    public basicRotatable(IRotationState state) {
        this.state = state;
    }

    @Override
    public boolean handleRotation(Face side, EnumFacing face, BlockPos pos) {
        state.applyRT(side, face);
        return true;
    }

    @Override
    public boolean handleSetFront(EnumFacing side,BlockPos pos) {
        if (state.validate()) {
            state.applyRT(Face.Front, side);
            return true;
        }
        return false;
    }

    @Override
    public IRotationState getRTstate() {
        return state;
    }

    @Override
    public Face getbyEF(EnumFacing facing) {
        return state.getbyEF(facing);
    }

    @Override
    public EnumFacing getbyside(Face side) {
        return state.getbyside(side);
    }
}
