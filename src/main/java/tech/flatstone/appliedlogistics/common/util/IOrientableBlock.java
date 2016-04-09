package tech.flatstone.appliedlogistics.common.util;

import net.minecraft.util.BlockPos;
import net.minecraft.world.IBlockAccess;

public interface IOrientableBlock {
    boolean usesMetaData();

    IOrientable getOrientable(IBlockAccess world, BlockPos pos);
}
