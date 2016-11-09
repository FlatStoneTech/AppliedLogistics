package tech.flatstone.appliedlogistics.common.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.DefaultStateMapper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import tech.flatstone.appliedlogistics.AppliedLogisticsCreativeTabs;
import tech.flatstone.appliedlogistics.ModInfo;
import tech.flatstone.appliedlogistics.api.Rotation.Rotation.Face;
import tech.flatstone.appliedlogistics.api.Rotation.Rotation.IRotationState;
import tech.flatstone.appliedlogistics.api.Rotation.impl.RotationProperty;
import tech.flatstone.appliedlogistics.api.Rotation.impl.basicRotationState;
import tech.flatstone.appliedlogistics.common.tileentities.misc.TileEntityRT;
import tech.flatstone.appliedlogistics.common.util.TileHelper;

/**
 * Created by USER on 03/09/2016.
 */
public class RTBlock extends BlockTileBase {
    IRotationState RTState;

    public RTBlock() {
        super(Material.ANVIL, "misc/rt_block");
        internalName = "rt_block";
        this.setTileEntity(TileEntityRT.class);
        setDefaultState(this.blockState.getBaseState().withProperty(BlockTileBase.ROTATION, "north-up"));
        this.setCreativeTab(AppliedLogisticsCreativeTabs.MACHINES);
        RTState = new basicRotationState();
    }

    @Override
    public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
        IRotationState state = new basicRotationState();
        if (placer.getLookVec().yCoord < -0.85) {
            if (!placer.isSneaking()) {
                state.applyRT(Face.Front, EnumFacing.UP);
                state.applyRT(Face.Top, placer.getHorizontalFacing());
        } else {
                state.applyRT(Face.Front, EnumFacing.DOWN);
                state.applyRT(Face.Top, placer.getHorizontalFacing().getOpposite());
            }
        } else if (placer.getLookVec().yCoord > 0.85) {
            if (!placer.isSneaking()) {
                state.applyRT(Face.Front, EnumFacing.DOWN);
                state.applyRT(Face.Top, placer.getHorizontalFacing());
            } else {
                state.applyRT(Face.Front, EnumFacing.UP);
                state.applyRT(Face.Top, placer.getHorizontalFacing().getOpposite());
            }
        } else if (!placer.isSneaking())
            state.applyRT(Face.Front, placer.getHorizontalFacing().getOpposite());
        else
            state.applyRT(Face.Front, placer.getHorizontalFacing().getOpposite());
        if (!(RotationProperty.parseValue(state)).equals(getDefaultState().getProperties().get(ROTATION))) {
            RTState = state;
            return getDefaultState().withProperty(ROTATION, RotationProperty.parseValue(state));
        }
        RTState = state;
        return getDefaultState();
    }

    public IRotationState getRTState() {
        return RTState;
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, ROTATION);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerBlockRenderer() {
        final String resourcePath = String.format("%s:%s", ModInfo.MOD_ID, this.resourcePath);

        ModelLoader.setCustomStateMapper(this, new DefaultStateMapper() {
            @Override
            protected ModelResourceLocation getModelResourceLocation(IBlockState state) {
                return new ModelResourceLocation(resourcePath, "");//"rotation=" + state.getProperties().get(ROTATION));
            }
        });
    }

    @Override
    public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
        TileEntityRT rt = TileHelper.getTileEntity(worldIn, pos, TileEntityRT.class);
            if (rt != null && rt.getRTstate() != null
                && !state.getProperties().get(ROTATION).equals(RotationProperty.parseValue(rt.getRTstate())))
            return state.withProperty(ROTATION, RotationProperty.parseValue(rt.getRTstate()));
        return state;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerBlockItemRenderer() {
        final String resourcePath = String.format("%s:%s", ModInfo.MOD_ID, this.resourcePath);
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, new ModelResourceLocation(resourcePath, "inventory")); //Platform.getPropertyString(properties)));
    }
}
