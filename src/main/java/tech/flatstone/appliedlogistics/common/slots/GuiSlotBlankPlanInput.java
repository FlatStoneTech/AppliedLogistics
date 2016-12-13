package tech.flatstone.appliedlogistics.common.slots;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import tech.flatstone.appliedlogistics.common.items.Items;

import javax.annotation.Nullable;

public class GuiSlotBlankPlanInput extends Slot {
    public GuiSlotBlankPlanInput(IInventory inventoryIn, int index, int xPosition, int yPosition) {
        super(inventoryIn, index, xPosition, yPosition);
    }

    @Override
    public boolean isItemValid(@Nullable ItemStack stack) {
        if (stack == null)
            return false;

        if (!this.inventory.isItemValidForSlot(getSlotIndex(), stack))
            return false;

        if (stack.getItem() != Items.ITEM_PLAN_BLANK.getItem())
            return false;

        return true;
    }

    @Override
    public int getSlotStackLimit() {
        return 1;
    }
}
