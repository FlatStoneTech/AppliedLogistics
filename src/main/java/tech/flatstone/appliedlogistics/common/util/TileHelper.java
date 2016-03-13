package tech.flatstone.appliedlogistics.common.util;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.IBlockAccess;

public class TileHelper {
    public static <T> T getTileEntity(IBlockAccess world, BlockPos blockPos, Class<T> tClass) {
        TileEntity tileEntity = world.getTileEntity(blockPos);
        return !tClass.isInstance(tileEntity) ? null : (T) tileEntity;
    }
}