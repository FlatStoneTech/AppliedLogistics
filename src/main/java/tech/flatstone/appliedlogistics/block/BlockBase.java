package tech.flatstone.appliedlogistics.block;

import tech.flatstone.appliedlogistics.reference.ModInfo;
import tech.flatstone.appliedlogistics.tileentity.TileEntityBase;
import tech.flatstone.appliedlogistics.util.TileTools;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.ReflectionHelper;

import java.util.Random;

public class BlockBase extends BlockContainer {
    protected boolean isInventory = false;
    protected boolean hasSubtypes = false;
    private Class<? extends TileEntity> tileEntityType = null;

    protected BlockBase(Material material) {
        super(material);

        setStepSound(Block.soundTypeStone);
        setHardness(2.2F);
        setResistance(5.0F);
        setHarvestLevel("pickaxe", 0);
    }

    @Override
    public TileEntity createNewTileEntity(World var1, int var2) {
        if (hasBlockTileEntity()) {
            try {
                return (TileEntity) this.tileEntityType.newInstance();
            } catch (Throwable e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }

    private boolean hasBlockTileEntity() {
        return this.tileEntityType != null;
    }

    protected void setTileEntity(Class<? extends TileEntity> c) {
        String tileName = "tileentity." + ModInfo.MOD_ID + "." + c.getSimpleName();

        GameRegistry.registerTileEntity(this.tileEntityType = c, tileName);
        this.isInventory = IInventory.class.isAssignableFrom(c);
        setTileProvider(hasBlockTileEntity());
    }

    private void setTileProvider(boolean b) {
        ReflectionHelper.setPrivateValue(Block.class, this, Boolean.valueOf(b), new String[]{"isTileProvider"});
    }

    public Class<? extends TileEntity> getTileEntityClass() {
        return this.tileEntityType;
    }

    @Override
    public void breakBlock(World world, BlockPos blockPos, IBlockState blockState) {
        dropInventory(world, blockPos);

        super.breakBlock(world, blockPos, blockState);
    }

    protected void dropInventory(World world, BlockPos blockPos) {
        TileEntity tileEntity = world.getTileEntity(blockPos);

        if (!(tileEntity instanceof IInventory)) {
            return;
        }

        IInventory inventory = (IInventory) tileEntity;

        for (int i = 0; i < inventory.getSizeInventory(); i++) {
            ItemStack itemStack = inventory.getStackInSlot(i);

            if (itemStack != null && itemStack.stackSize > 0) {
                Random rand = new Random();

                float dX = rand.nextFloat() * 0.8F + 0.1F;
                float dY = rand.nextFloat() * 0.8F + 0.1F;
                float dZ = rand.nextFloat() * 0.8F + 0.1F;

                EntityItem entityItem = new EntityItem(world, blockPos.getX() + dX, blockPos.getY() + dY, blockPos.getZ() + dZ, itemStack.copy());

                if (itemStack.hasTagCompound()) {
                    entityItem.getEntityItem().setTagCompound((NBTTagCompound) itemStack.getTagCompound().copy());
                }

                float factor = 0.05F;
                entityItem.motionX = rand.nextGaussian() * factor;
                entityItem.motionY = rand.nextGaussian() * factor + 0.2F;
                entityItem.motionZ = rand.nextGaussian() * factor;
                world.spawnEntityInWorld(entityItem);
                itemStack.stackSize = 0;
            }
        }
    }

    @Override
    public String getUnlocalizedName() {
        String blockName = getUnwrappedUnlocalizedName(super.getUnlocalizedName());

        String test = String.format("tile.%s", blockName);
        return test.toLowerCase();
    }

    protected String getUnwrappedUnlocalizedName(String unlocalizedName) {
        return unlocalizedName.substring(unlocalizedName.indexOf(".") + 1);
    }

    @Override
    public void onBlockPlacedBy(World world, BlockPos blockPos, IBlockState state, EntityLivingBase placer, ItemStack itemStack) {
        super.onBlockPlacedBy(world, blockPos, state, placer, itemStack);

        if (itemStack.hasDisplayName()) {
            TileEntityBase tileEntityBase = TileTools.getTileEntity(world, blockPos, TileEntityBase.class);
            tileEntityBase.setCustomName(itemStack.getDisplayName());
        }
    }

    @Override
    public int getRenderType() {
        return 3;
    }
}
