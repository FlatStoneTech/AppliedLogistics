package tech.flatstone.appliedlogistics.common.blocks.misc;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import tech.flatstone.appliedlogistics.AppliedLogisticsCreativeTabs;
import tech.flatstone.appliedlogistics.common.blocks.BlockTileBase;
import tech.flatstone.appliedlogistics.common.tileentities.misc.TileEntityCauldron;
import tech.flatstone.appliedlogistics.common.util.IProvideRecipe;
import tech.flatstone.appliedlogistics.common.util.LogHelper;
import tech.flatstone.appliedlogistics.common.util.TileHelper;

import javax.annotation.Nullable;

public class BlockCauldron extends BlockTileBase implements IProvideRecipe {
    protected static final AxisAlignedBB AABB_LEG_1_SEGMENT_1 = new AxisAlignedBB(0.125, 0, 0.125, 0.1875, 0.0625, 0.25);
    protected static final AxisAlignedBB AABB_LEG_1_SEGMENT_2 = new AxisAlignedBB(0.1875, 0, 0.125, 0.25, 0.0625, 0.1875);
    protected static final AxisAlignedBB AABB_LEG_2_SEGMENT_1 = new AxisAlignedBB(0.8125, 0, 0.125, 0.875, 0.0625, 0.25);
    protected static final AxisAlignedBB AABB_LEG_2_SEGMENT_2 = new AxisAlignedBB(0.75, 0, 0.125, 0.8125, 0.0625, 0.1875);
    protected static final AxisAlignedBB AABB_LEG_3_SEGMENT_1 = new AxisAlignedBB(0.125, 0, 0.75, 0.1875, 0.0625, 0.875);
    protected static final AxisAlignedBB AABB_LEG_3_SEGMENT_2 = new AxisAlignedBB(0.1875, 0, 0.8125, 0.25, 0.0625, 0.875);
    protected static final AxisAlignedBB AABB_LEG_4_SEGMENT_1 = new AxisAlignedBB(0.8125, 0, 0.75, 0.875, 0.0625, 0.875);
    protected static final AxisAlignedBB AABB_LEG_4_SEGMENT_2 = new AxisAlignedBB(0.75, 0, 0.8125, 0.8125, 0.0625, 0.875);
    protected static final AxisAlignedBB AABB_BASE = new AxisAlignedBB(0.125, 0.0625, 0.125, 0.875, 0.125, 0.875);
    protected static final AxisAlignedBB AABB_WALL_1 = new AxisAlignedBB(0.125, 0.125, 0.125, 0.875, 0.6875, 0.1875);
    protected static final AxisAlignedBB AABB_WALL_2 = new AxisAlignedBB(0.125, 0.125, 0.8125, 0.875, 0.6875, 0.875);
    protected static final AxisAlignedBB AABB_WALL_3 = new AxisAlignedBB(0.125, 0.125, 0.1875, 0.1875, 0.6875, 0.875);
    protected static final AxisAlignedBB AABB_WALL_4 = new AxisAlignedBB(0.8125, 0.125, 0.1875, 0.875, 0.6875, 0.875);
    protected static final AxisAlignedBB[] BOXES = new AxisAlignedBB[]{AABB_LEG_1_SEGMENT_1, AABB_LEG_1_SEGMENT_2, AABB_LEG_2_SEGMENT_1, AABB_LEG_2_SEGMENT_2,
            AABB_LEG_3_SEGMENT_1, AABB_LEG_3_SEGMENT_2, AABB_LEG_4_SEGMENT_1, AABB_LEG_4_SEGMENT_2, AABB_BASE, AABB_WALL_1, AABB_WALL_2, AABB_WALL_3, AABB_WALL_4};
    protected static final AxisAlignedBB AABB_WATER = new AxisAlignedBB(0.1875, 0.125, 0.1875, 0.8125, 0.625, 0.8125);
    protected static final AxisAlignedBB AABB_BOUNDING_BOX = new AxisAlignedBB(0.125, 0, 0.125, 0.875, 0.6875, 0.875);

    public BlockCauldron() {
        super(Material.ROCK, "misc/cauldron");
        setTileEntity(TileEntityCauldron.class);
        setCreativeTab(AppliedLogisticsCreativeTabs.GENERAL);
        this.setInternalName("cauldron");
        this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
    }

    @Override
    public void RegisterRecipes() {
        //GameRegistry.addShapelessRecipe(new ItemStack(this), new ItemStack(Blocks.CAULDRON));
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        return AABB_BOUNDING_BOX;
    }

    @Override
    public boolean isFullCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Override
    public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
        TileEntityCauldron tileEntity = TileHelper.getTileEntity(worldIn, pos, TileEntityCauldron.class);
        if (tileEntity != null) {
            return state.withProperty(FACING, tileEntity.getForward());
        }
        return state.withProperty(FACING, EnumFacing.NORTH);
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, FACING);
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState();
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return 0;
    }

    @Override
    @Deprecated
    @Nullable
    public RayTraceResult collisionRayTrace(IBlockState blockState, World worldIn, BlockPos pos, Vec3d start, Vec3d end)
    {
        RayTraceResult lookObject = null;
        double distance = Double.MAX_VALUE;
        for (int i = 0; i < BOXES.length; i++) {
            RayTraceResult rayTraceResult = BOXES[i].offset(pos.getX(), pos.getY(), pos.getZ()).calculateIntercept(start, end);
            if (rayTraceResult != null && start.distanceTo(rayTraceResult.hitVec) < distance) {
                lookObject = rayTraceResult;
            }
        }

        if (lookObject != null) {
            return new RayTraceResult(lookObject.hitVec.addVector(pos.getX(), pos.getY(), pos.getZ()), lookObject.sideHit, pos);
        }
        return null;
    }
}
