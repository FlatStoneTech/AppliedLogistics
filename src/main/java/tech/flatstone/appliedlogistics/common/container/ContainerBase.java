package tech.flatstone.appliedlogistics.common.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import tech.flatstone.appliedlogistics.common.container.slot.SlotDisabled;
import tech.flatstone.appliedlogistics.common.container.slot.SlotPlayerHotBar;
import tech.flatstone.appliedlogistics.common.container.slot.SlotPlayerInventory;

import java.util.HashSet;

public abstract class ContainerBase extends Container {
    final InventoryPlayer inventoryPlayer;
    final TileEntity tileEntity;
    public boolean isContainerValid = true;
    protected HashSet<Integer> locked = new HashSet<Integer>();

    public ContainerBase(InventoryPlayer inventoryPlayer, TileEntity tileEntity) {
        this.inventoryPlayer = inventoryPlayer;
        this.tileEntity = tileEntity;
    }

    protected void bindPlayerInventory(InventoryPlayer inventoryPlayer, int offsetX, int offsetY) {
        // Get player inventory slots...
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 9; j++) {
                if (this.locked.contains(Integer.valueOf(j + i * 9 + 9))) {
                    addSlotToContainer(new SlotDisabled(inventoryPlayer, j + i * 9 + 9, 8 + j * 18 + offsetX, offsetY + i * 18));
                } else {
                    addSlotToContainer(new SlotPlayerInventory(inventoryPlayer, j + i * 9 + 9, 8 + j * 18 + offsetX, offsetY + i * 18));
                }
            }
        }

        // Get hotbar slots...
        for (int i = 0; i < 9; i++) {
            if (this.locked.contains(Integer.valueOf(i))) {
                addSlotToContainer(new SlotDisabled(inventoryPlayer, i, 8 + i * 18 + offsetX, 58 + offsetY));
            } else {
                addSlotToContainer(new SlotPlayerHotBar(inventoryPlayer, i, 8 + i * 18 + offsetX, 58 + offsetY));
            }
        }
    }

    @Override
    public boolean canInteractWith(EntityPlayer player) {
        if (this.isContainerValid) {
            if ((this.tileEntity instanceof IInventory))
                return ((IInventory) this.tileEntity).isUseableByPlayer(player);

            return true;
        }
        return false;
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int slotID) {
        ItemStack itemStack = null;
        //todo: ugh, so much checking
        //todo for now, returning null..

        return null;
    }

    private void updateSlot(Slot clickSlot) {
        detectAndSendChanges();
    }

    @Override
    public void detectAndSendChanges() {
        super.detectAndSendChanges();
    }
}
