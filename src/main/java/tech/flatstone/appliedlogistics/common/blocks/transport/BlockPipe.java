package tech.flatstone.appliedlogistics.common.blocks.transport;

import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import tech.flatstone.appliedlogistics.AppliedLogisticsCreativeTabs;
import tech.flatstone.appliedlogistics.api.features.TechLevel;
import tech.flatstone.appliedlogistics.common.blocks.BlockTechBase;
import tech.flatstone.appliedlogistics.common.tileentities.machines.TileEntityFurnace;
import tech.flatstone.appliedlogistics.common.tileentities.transport.TIleEntityPipe;
import tech.flatstone.appliedlogistics.common.util.TileHelper;

public class BlockPipe extends BlockTechBase {
    private static final PropertyBool WORKING = PropertyBool.create("working");

    public BlockPipe() {
        super(Material.ROCK, "transport/pipe", TechLevel.STONE_AGE,TechLevel.BRONZE_AGE,TechLevel.INDUSTRIAL_AGE, TechLevel.MECHANICAL_AGE, TechLevel.DIGITAL_AGE);
        this.setDefaultState(blockState.getBaseState().withProperty(TECHLEVEL, TechLevel.STONE_AGE).withProperty(FACING, EnumFacing.NORTH));
        this.setTileEntity(TIleEntityPipe.class);
        this.setCreativeTab(AppliedLogisticsCreativeTabs.TRANSPORT);
        this.setInternalName("transport_pipe");
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState().withProperty(TECHLEVEL, TechLevel.byMeta(meta));
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        TechLevel tier = (TechLevel) state.getValue(TECHLEVEL);
        return tier.getMeta();
    }

    @Override
    public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
        return state.withProperty(FACING, EnumFacing.NORTH).withProperty(WORKING, false);
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, TECHLEVEL, FACING, WORKING);
    }

    @Override
    public int damageDropped(IBlockState state) {
        return getMetaFromState(state);
    }
}
