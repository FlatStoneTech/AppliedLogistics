package tech.flatstone.appliedlogistics.common.util;

import net.minecraft.util.EnumFacing;

/**
 * Inspired from AlgorithmX2 from RotatableBlocks
 */

public interface IOrientable {
    boolean canBeRotated();

    EnumFacing getForward();

    void setOrientation(EnumFacing forward);
}
