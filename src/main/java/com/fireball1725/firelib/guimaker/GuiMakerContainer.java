package com.fireball1725.firelib.guimaker;


import com.fireball1725.firelib.guimaker.objects.GuiObject;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.List;

public class GuiMakerContainer extends Container {
    private final InventoryPlayer inventoryPlayer;
    private final TileEntity tileEntity;
    private GuiMaker guiMaker;

    public GuiMakerContainer(int id, EntityPlayer player, World world, BlockPos pos) {
        this.inventoryPlayer = player.inventory;
        this.tileEntity = world.getTileEntity(pos);
        this.guiMaker = GuiMaker.getGuiMakerInstance(id);
    }



    @Override
    public boolean canInteractWith(EntityPlayer playerIn) {
        return true;
    }
}
