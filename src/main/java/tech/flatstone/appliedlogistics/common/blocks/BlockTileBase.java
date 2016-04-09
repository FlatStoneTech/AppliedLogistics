package tech.flatstone.appliedlogistics.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.ReflectionHelper;
import tech.flatstone.appliedlogistics.ModInfo;
import tech.flatstone.appliedlogistics.common.tileentities.TileEntityBase;
import tech.flatstone.appliedlogistics.common.util.IOrientable;
import tech.flatstone.appliedlogistics.common.util.TileHelper;

import javax.annotation.Nonnull;

public abstract class BlockTileBase extends BlockBase implements ITileEntityProvider {

    @Nonnull
    private Class<? extends TileEntity> tileEntityClass;

    public BlockTileBase(Material material) {
        super(material);
    }

    protected void setTileEntity(final Class<? extends TileEntity> clazz) {
        this.tileEntityClass = clazz;
        this.setTileProvider(true);
        this.isInventory = IInventory.class.isAssignableFrom(clazz);

        String tileName = "tileentity." + ModInfo.MOD_ID + "." + clazz.getSimpleName();
        GameRegistry.registerTileEntity(this.tileEntityClass, tileName);
    }

    private void setTileProvider(final boolean b) {
        ReflectionHelper.setPrivateValue(Block.class, this, b, "isTileProvider");
    }

    public Class<? extends TileEntity> getTileEntityClass() {
        return this.tileEntityClass;
    }

    @Override
    public final TileEntity createNewTileEntity(World worldIn, int meta) {
        try {
            return this.tileEntityClass.newInstance();
        } catch (final InstantiationException ex) {
            throw new IllegalStateException("Failed to create a new instance of an illegal class " + this.tileEntityClass, ex);
        } catch (final IllegalAccessException ex) {
            throw new IllegalStateException("Failed to create a new instance of " + this.tileEntityClass + " because of a lack of permissions", ex);
        }
    }

    @Override
    public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
        final TileEntityBase tileEntity = TileHelper.getTileEntity(worldIn, pos, TileEntityBase.class);
        if (tileEntity != null) {
            //todo: ask tile entity for drops?
        }
    }

    @Override
    public EnumFacing[] getValidRotations(World world, BlockPos pos) {
        final TileEntityBase tileEntity = TileHelper.getTileEntity(world, pos, TileEntityBase.class);
        if (tileEntity != null && tileEntity.canBeRotated())
            return EnumFacing.values();

        return super.getValidRotations(world, pos);
    }

    @Override
    public IOrientable getOrientable(IBlockAccess world, BlockPos pos) {
        return TileHelper.getTileEntity(world, pos, TileEntityBase.class);
    }


    @Override
    public void onBlockPlacedBy(World world, BlockPos blockPos, IBlockState state, EntityLivingBase placer, ItemStack itemStack) {
        TileEntityBase tileEntity = TileHelper.getTileEntity(world, blockPos, TileEntityBase.class);

        if (tileEntity == null)
            return;

        if (itemStack.hasTagCompound() && itemStack.getTagCompound().hasKey("MachineItemData")) {
            tileEntity.setMachineItemData(itemStack.getTagCompound().getCompoundTag("MachineItemData"));
        }

        if (itemStack.hasDisplayName()) {
            tileEntity.setCustomName(itemStack.getDisplayName());
        }
    }
}
