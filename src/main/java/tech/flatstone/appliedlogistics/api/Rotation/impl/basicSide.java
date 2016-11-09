package tech.flatstone.appliedlogistics.api.Rotation.impl;

import net.minecraft.util.EnumFacing;
import tech.flatstone.appliedlogistics.api.Rotation.Rotation.Face;
import tech.flatstone.appliedlogistics.api.Rotation.Rotation.ISide;

public class basicSide implements ISide {
    Face relface;
    EnumFacing facing;

    public basicSide(Face relface, EnumFacing facing) {
        this.relface = relface;
        this.facing = facing;
    }

    @Override
    public EnumFacing getFacing() {
        return facing;
    }

    @Override
    public void setFacing(EnumFacing facing) {
        this.facing = facing;
    }

    public Face getSide() {
        return relface;
    }
}
