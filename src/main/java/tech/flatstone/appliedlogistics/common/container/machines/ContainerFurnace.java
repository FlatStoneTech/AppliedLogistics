package tech.flatstone.appliedlogistics.common.container.machines;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.tileentity.TileEntity;
import tech.flatstone.appliedlogistics.common.container.ContainerBase;
import tech.flatstone.appliedlogistics.common.container.slot.SlotFuelInput;
import tech.flatstone.appliedlogistics.common.container.slot.SlotFurnaceInput;
import tech.flatstone.appliedlogistics.common.container.slot.SlotOutput;
import tech.flatstone.appliedlogistics.common.tileentities.machines.TileEntityFurnace;

public class ContainerFurnace extends ContainerBase {
    IInventory inventory;

    public ContainerFurnace(InventoryPlayer inventoryPlayer, TileEntity tileEntity) {
        super(inventoryPlayer, tileEntity);
        this.inventory = (IInventory) tileEntity;

        int slotRows = ((TileEntityFurnace) tileEntity).getFurnaceRows();
        int offsetY = slotRows * 18;

        bindPlayerInventory(inventoryPlayer, 0, 44 + offsetY);

        addSlotToContainer(new SlotFuelInput(inventory, 0, 190, 98 + offsetY, null));

        boolean extraSlots = ((TileEntityFurnace) tileEntity).isUpgradeExtraSlots();
        int slotNumber = 1;

        for (int i = 0; i < slotRows; i++) {
            if (extraSlots)
                addSlotToContainer(new SlotFurnaceInput(inventory, slotNumber, 8, 18 + i * 18, null));
            slotNumber++;
            addSlotToContainer(new SlotFurnaceInput(inventory, slotNumber, 26, 18 + i * 18, null));
            slotNumber += 2;

            addSlotToContainer(new SlotOutput(inventory, slotNumber, 98, 18 + i * 18));
            slotNumber++;
            if (extraSlots) {
                addSlotToContainer(new SlotOutput(inventory, slotNumber, 116, 18 + i * 18));
                addSlotToContainer(new SlotOutput(inventory, slotNumber + 1, 134, 18 + i * 18));
                addSlotToContainer(new SlotOutput(inventory, slotNumber + 2, 152, 18 + i * 18));
            }
            slotNumber += 3;
        }

        /**
         * Slot #       Purpose
         * 0            Fuel Input Slot
         *
         * Furnace Row #1
         * 1            Input Slot (1/1)
         * 2            Input Slot (1/2)
         * 3            Process Slot (1)
         * 4            Output Slot (1/1)
         * 5            Output Slot (1/2)
         * 6            Output Slot (1/3)
         * 7            Output Slot (1/4)
         *
         * Furnace Row #2
         * 8            Input Slot (2/1)
         * 9            Input Slot (2/2)
         * 10           Process Slot (2)
         * 11           Output Slot (2/1)
         * 12           Output Slot (2/2)
         * 13           Output Slot (2/3)
         * 14           Output Slot (2/4)
         *
         * Furnace Row #3
         * 15           Input Slot (3/1)
         * 16           Input Slot (3/2)
         * 17           Process Slot (3)
         * 18           Output Slot (3/1)
         * 19           Output Slot (3/2)
         * 20           Output Slot (3/3)
         * 21           Output Slot (3/4)
         */
    }
}
