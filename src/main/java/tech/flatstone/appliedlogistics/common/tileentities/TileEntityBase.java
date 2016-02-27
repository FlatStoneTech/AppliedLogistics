package tech.flatstone.appliedlogistics.common.tileentities;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import tech.flatstone.appliedlogistics.common.util.ItemStackSrc;

import java.util.ArrayList;
import java.util.HashMap;

public class TileEntityBase extends TileEntity {
    public static final HashMap<Class, ItemStackSrc> myItem = new HashMap();
    public String customName;
    public int renderedFragment = 0;

    public static void registerTileItem(Class c, ItemStackSrc wat) {
        myItem.put(c, wat);
    }

    @Override
    public Packet getDescriptionPacket() {
        NBTTagCompound data = new NBTTagCompound();
        writeToNBT(data);
        return new S35PacketUpdateTileEntity(this.pos, 1, data);
    }

    @Override
    public void onDataPacket(NetworkManager networkManager, S35PacketUpdateTileEntity s35PacketUpdateTileEntity) {
        readFromNBT(s35PacketUpdateTileEntity.getNbtCompound());
        worldObj.markBlockRangeForRenderUpdate(this.pos, this.pos);
        markForUpdate();
    }

    public void markForUpdate() {
        if (this.renderedFragment > 0) {
            this.renderedFragment |= 0x1;
        } else if (this.worldObj != null) {
            this.worldObj.markBlockForUpdate(this.pos);

            Block block = worldObj.getBlockState(this.pos).getBlock();

            int xCoord = this.pos.getX();
            int yCoord = this.pos.getY();
            int zCoord = this.pos.getZ();

            this.worldObj.notifyBlockOfStateChange(new BlockPos(xCoord, yCoord - 1, zCoord), block);
            this.worldObj.notifyBlockOfStateChange(new BlockPos(xCoord, yCoord + 1, zCoord), block);
            this.worldObj.notifyBlockOfStateChange(new BlockPos(xCoord - 1, yCoord, zCoord), block);
            this.worldObj.notifyBlockOfStateChange(new BlockPos(xCoord + 1, yCoord, zCoord), block);
            this.worldObj.notifyBlockOfStateChange(new BlockPos(xCoord, yCoord - 1, zCoord - 1), block);
            this.worldObj.notifyBlockOfStateChange(new BlockPos(xCoord, yCoord - 1, zCoord + 1), block);
        }
    }

    public void onChunkLoad() {
        if (this.isInvalid())
            this.validate();

        markForUpdate();
    }

    @Override
    public void onChunkUnload() {
        if (!this.isInvalid())
            this.invalidate();
    }

    public void getDrops(World w, int x, int y, int z, ArrayList<ItemStack> drops) {
        if (this instanceof IInventory) {
            IInventory inventory = (IInventory) this;
            for (int l = 0; l < inventory.getSizeInventory(); l++) {
                ItemStack itemStack = inventory.getStackInSlot(l);
                if (itemStack != null) {
                    drops.add(itemStack);
                }
            }
        }
    }

    protected ItemStack getItemFromTile(Object object) {
        ItemStackSrc itemStackSrc = myItem.get(object.getClass());
        if (itemStackSrc == null) {
            return null;
        }
        return itemStackSrc.stack(1);
    }

    public TileEntity getTile() {
        return this;
    }

    public String getCustomName() {
        return hasCustomName() ? this.customName : getUnlocalizedName();
    }

    public void setCustomName(String customName) {
        this.customName = customName;
    }

    public boolean hasCustomName() {
        return (this.customName != null) && (this.customName.length() > 0);
    }

    public String getUnlocalizedName() {
        Item item = Item.getItemFromBlock(worldObj.getBlockState(this.pos).getBlock());
        ItemStack itemStack = new ItemStack(item, 1, getBlockMetadata());

        return itemStack.getUnlocalizedName() + ".name";
    }

    public void setName(String name) {
        this.customName = name;
    }

    @Override
    public void writeToNBT(NBTTagCompound nbtTagCompound) {
        super.writeToNBT(nbtTagCompound);

        if (this.customName != null) {
            nbtTagCompound.setString("CustomName", this.customName);
        }
    }

    @Override
    public void readFromNBT(NBTTagCompound nbtTagCompound) {
        super.readFromNBT(nbtTagCompound);

        if (nbtTagCompound.hasKey("CustomName")) {
            this.customName = nbtTagCompound.getString("CustomName");
        } else {
            this.customName = null;
        }
    }

    public IBlockState getBlockState() {
        return worldObj.getBlockState(pos);
    }
}