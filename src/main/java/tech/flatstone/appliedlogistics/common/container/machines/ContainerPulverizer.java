package tech.flatstone.appliedlogistics.common.container.machines;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.tileentity.TileEntity;
import tech.flatstone.appliedlogistics.common.container.ContainerBase;

public class ContainerPulverizer extends ContainerBase{
    IInventory inventory;

    public ContainerPulverizer(InventoryPlayer inventoryPlayer, TileEntity tileEntity) {
        super(inventoryPlayer, tileEntity);
        this.inventory = (IInventory) tileEntity;

        bindPlayerInventory(inventoryPlayer, 0, 138);
    }
}
