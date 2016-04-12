package tech.flatstone.appliedlogistics.common.container.machines;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.tileentity.TileEntity;
import tech.flatstone.appliedlogistics.common.container.ContainerBase;
import tech.flatstone.appliedlogistics.common.container.slot.SlotNormal;
import tech.flatstone.appliedlogistics.common.container.slot.SlotOutput;
import tech.flatstone.appliedlogistics.common.tileentities.machines.TileEntityFurnace;

public class ContainerFurnace extends ContainerBase {
    IInventory inventory;

    public ContainerFurnace(InventoryPlayer inventoryPlayer, TileEntity tileEntity) {
        super(inventoryPlayer, tileEntity);
        this.inventory = (IInventory) tileEntity;

        int slotRows = ((TileEntityFurnace)tileEntity).getSlotRows();
        int offsetY = slotRows * 18;

        bindPlayerInventory(inventoryPlayer, 0, 44 + offsetY);

        addSlotToContainer(new SlotNormal(inventory, 0, 190, 98 + offsetY));

        boolean extraSlots = false;
        int slotNumber = 1;

        for (int i = 0; i < slotRows; i++) {
            if (extraSlots)
                addSlotToContainer(new SlotNormal(inventory, slotNumber, 8, 18 + i * 18));
            slotNumber++;
            addSlotToContainer(new SlotNormal(inventory, slotNumber, 26, 18 + i * 18));
            slotNumber++;

            addSlotToContainer(new SlotOutput(inventory, slotNumber, 98, 18 + i * 18));
            slotNumber++;
            if (extraSlots) {
                addSlotToContainer(new SlotOutput(inventory, slotNumber, 116, 18 + i * 18));
                addSlotToContainer(new SlotOutput(inventory, slotNumber + 1, 134, 18 + i * 18));
                addSlotToContainer(new SlotOutput(inventory, slotNumber + 2, 152, 18 + i * 18));
            }
            slotNumber+=3;
        }
    }
}
