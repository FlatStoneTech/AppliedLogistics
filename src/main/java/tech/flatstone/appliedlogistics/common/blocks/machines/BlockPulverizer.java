package tech.flatstone.appliedlogistics.common.blocks.machines;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import scala.reflect.internal.TreeInfo;
import tech.flatstone.appliedlogistics.AppliedLogistics;
import tech.flatstone.appliedlogistics.common.blocks.BlockBase;
import tech.flatstone.appliedlogistics.common.tileentities.builder.TileEntityBuilder;
import tech.flatstone.appliedlogistics.common.tileentities.machines.TileEntityPulverizer;

public class BlockPulverizer extends BlockBase {
    public BlockPulverizer() {
        super(Material.rock);
        this.setTileEntity(TileEntityPulverizer.class);
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumFacing side, float hitX, float hitY, float hitZ) {
        if (worldIn.isRemote)
            return true;

        playerIn.openGui(AppliedLogistics.instance, 1, worldIn, pos.getX(), pos.getY(), pos.getZ());
        return true;
    }
}
