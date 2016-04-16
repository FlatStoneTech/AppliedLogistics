package tech.flatstone.appliedlogistics.common.blocks.machines;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import tech.flatstone.appliedlogistics.AppliedLogistics;
import tech.flatstone.appliedlogistics.common.blocks.BlockTileBase;
import tech.flatstone.appliedlogistics.common.tileentities.machines.TileEntityFurnace;
import tech.flatstone.appliedlogistics.common.util.IBlockRenderer;
import tech.flatstone.appliedlogistics.common.util.TileHelper;

public class BlockFurnace extends BlockTileBase implements IBlockRenderer {
    public BlockFurnace() {
        super(Material.rock);
        this.setTileEntity(TileEntityFurnace.class);
    }

    @Override
    public void registerBlockRenderer() {

    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumFacing side, float hitX, float hitY, float hitZ) {
        TileEntityFurnace tileEntity = TileHelper.getTileEntity(worldIn, pos, TileEntityFurnace.class);

        if (worldIn.isRemote)
            return true;

        playerIn.openGui(AppliedLogistics.instance, 4, worldIn, pos.getX(), pos.getY(), pos.getZ());
        return true;
    }
}
