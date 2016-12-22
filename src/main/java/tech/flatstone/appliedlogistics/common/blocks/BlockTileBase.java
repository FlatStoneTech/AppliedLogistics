package tech.flatstone.appliedlogistics.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.ReflectionHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import tech.flatstone.appliedlogistics.ModInfo;
import tech.flatstone.appliedlogistics.common.tileentities.TileEntityBase;
import tech.flatstone.appliedlogistics.common.util.IBlockRenderer;
import tech.flatstone.appliedlogistics.common.util.IOrientable;
import tech.flatstone.appliedlogistics.common.util.Platform;
import tech.flatstone.appliedlogistics.common.util.TileHelper;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class BlockTileBase extends BlockBase implements ITileEntityProvider, IBlockRenderer {
    protected static final PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);

    @Nonnull
    private Class<? extends TileEntity> tileEntityClass;

    public BlockTileBase(Material material, String resourcePath) {
        super(material, resourcePath);
    }

    protected void setTileEntity(final Class<? extends TileEntity> clazz) {
        this.tileEntityClass = clazz;
        this.setTileProvider(true);
        this.isBlockContainer = true;
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
            tileEntity.dropItems();
        }
    }

    @Override
    public EnumFacing[] getValidRotations(World world, BlockPos pos) {
        final TileEntityBase tileEntity = TileHelper.getTileEntity(world, pos, TileEntityBase.class);
        if (tileEntity != null && tileEntity.canBeRotated())
            return EnumFacing.HORIZONTALS;

        return super.getValidRotations(world, pos);
    }

    @Override
    public IOrientable getOrientable(IBlockAccess world, BlockPos pos) {
        return TileHelper.getTileEntity(world, pos, TileEntityBase.class);
    }

    @Override
    public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
        TileEntityBase tileEntity = TileHelper.getTileEntity(worldIn, pos, TileEntityBase.class);
        if (tileEntity != null && tileEntity.canBeRotated()) {
            return state.withProperty(FACING, tileEntity.getForward());
        }
        return state.withProperty(FACING, EnumFacing.NORTH);
    }

    @Override
    public void onBlockPlacedBy(World world, BlockPos blockPos, IBlockState state, EntityLivingBase placer, ItemStack itemStack) {
        TileEntityBase tileEntity = TileHelper.getTileEntity(world, blockPos, TileEntityBase.class);

        if (tileEntity == null)
            return;

        if (itemStack.hasTagCompound() && itemStack.getTagCompound().hasKey("MachineItemData")) {
            //todo: set other nbt data
            tileEntity.setMachineItemData(itemStack.getTagCompound().getCompoundTag("MachineItemData"));
            tileEntity.initMachineData();
        }

        if (itemStack.hasDisplayName()) {
            tileEntity.setCustomName(itemStack.getDisplayName());
        }

        if (tileEntity.canBeRotated()) {
            //todo: make client side configuration for this option...
            if (placer.isSneaking()) {
                tileEntity.setOrientation(placer.getHorizontalFacing());
            } else {
                tileEntity.setOrientation(placer.getHorizontalFacing().getOpposite());
            }
        }
    }

    @Override
    public boolean removedByPlayer(IBlockState state, World world, BlockPos pos, EntityPlayer player, boolean willHarvest) {
        return willHarvest || super.removedByPlayer(state, world, pos, player, false);
    }

    @Override
    public void harvestBlock(World worldIn, EntityPlayer player, BlockPos pos, IBlockState state, TileEntity te, ItemStack stack) {
        super.harvestBlock(worldIn, player, pos, state, te, stack);
        worldIn.setBlockToAir(pos);
    }

    /**
     * Add machineItemData to item that drops
     *
     * @param world
     * @param pos
     * @param state
     * @param fortune
     * @return
     */
    @Override
    public List<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
        TileEntityBase tileEntityBase = TileHelper.getTileEntity(world, pos, TileEntityBase.class);
        if (tileEntityBase != null) {
            final ItemStack itemStack = new ItemStack(this, 1, tileEntityBase.getBlockMetadata());

            NBTTagCompound machineItemData = tileEntityBase.getMachineItemData();
            if (machineItemData != null) {
                NBTTagCompound itemTag = new NBTTagCompound();
                itemTag.setTag("MachineItemData", machineItemData);
                itemStack.setTagCompound(itemTag);
            }

            if (tileEntityBase.hasCustomName()) {
                itemStack.setStackDisplayName(tileEntityBase.getCustomName());
            }

            ArrayList<ItemStack> drops = new ArrayList<ItemStack>();
            drops.add(itemStack);
            return drops;
        }
        return super.getDrops(world, pos, state, fortune);
    }

    @Override
    public boolean eventReceived(IBlockState state, World worldIn, BlockPos pos, int id, int param) {
        super.eventReceived(state, worldIn, pos, id, param);
        TileEntity tileentity = worldIn.getTileEntity(pos);
        return tileentity != null && tileentity.receiveClientEvent(id, param);
    }

    //todo: 1.11
//    @SideOnly(Side.CLIENT)
//    @Override
//    public void registerBlockItemRenderer() {
//        final String resourcePath = String.format("%s:%s", ModInfo.MOD_ID, this.resourcePath);
//
//        List<ItemStack> subBlocks = new ArrayList<ItemStack>();
//        getSubBlocks(Item.getItemFromBlock(this), null, subBlocks);
//
//        for (ItemStack itemStack : subBlocks) {
//            IBlockState blockState = this.getStateFromMeta(itemStack.getItemDamage());
//            Map<IProperty<?>, Comparable<?>> properties = new HashMap<>();
//            for (Map.Entry<IProperty<?>, Comparable<?>> entry : blockState.getProperties().entrySet()) {
//                if (entry.getKey() != FACING)
//                    properties.put(entry.getKey(), entry.getValue());
//            }
//
//            ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), itemStack.getItemDamage(), new ModelResourceLocation(resourcePath, Platform.getPropertyString(properties)));
//        }
//    }
}
