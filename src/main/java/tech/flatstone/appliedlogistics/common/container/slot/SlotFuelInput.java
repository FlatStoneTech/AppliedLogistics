package tech.flatstone.appliedlogistics.common.container.slot;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityFurnace;

public class SlotFuelInput extends SlotBase {
    public SlotFuelInput(IInventory inventory, int idx, int x, int y, ItemStack slotIcon) {
        super(inventory, idx, x, y);
        this.overlayIcon = slotIcon;
    }

    @Override
    public boolean isItemValid(ItemStack stack) {
        if (stack == null)
            return false;

        if (!this.inventory.isItemValidForSlot(getSlotIndex(), stack))
            return false;

        return TileEntityFurnace.getItemBurnTime(stack) > 0;
    }
}
