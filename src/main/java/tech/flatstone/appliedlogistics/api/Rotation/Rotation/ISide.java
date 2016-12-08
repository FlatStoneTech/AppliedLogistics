package tech.flatstone.appliedlogistics.api.Rotation.Rotation;

import net.minecraft.util.EnumFacing;

public interface ISide {
    Face getSide();
    EnumFacing getFacing();
    void setFacing(EnumFacing facing);

}
