package tech.flatstone.appliedlogistics.common.container.slot;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class SlotRestrictedInput extends SlotBase {
    List<ItemStack> lockedItems;
    ItemStack slotIcon;

    public SlotRestrictedInput(IInventory inventory, int idx, int x, int y, ArrayList<ItemStack> lockedItems, ItemStack slotIcon) {
        super(inventory, idx, x, y);
        this.lockedItems = lockedItems;
        this.slotIcon = slotIcon;
    }

    @Override
    public boolean isItemValid(ItemStack stack) {
        if (stack == null)
            return false;

        if (!this.inventory.isItemValidForSlot(getSlotIndex(), stack))
            return false;

        for(ItemStack item : lockedItems) {
            if (item.isItemEqual(stack))
                return true;
        }

        return false;
    }
}
