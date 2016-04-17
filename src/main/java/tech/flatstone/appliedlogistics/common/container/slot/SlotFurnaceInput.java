package tech.flatstone.appliedlogistics.common.container.slot;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;

public class SlotFurnaceInput extends SlotBase {
    public SlotFurnaceInput(IInventory inventory, int idx, int x, int y, ItemStack slotIcon) {
        super(inventory, idx, x, y);
        this.overlayIcon = slotIcon;
    }

    @Override
    public boolean isItemValid(ItemStack stack) {
        if (stack == null)
            return false;

        if (!this.inventory.isItemValidForSlot(getSlotIndex(), stack))
            return false;

        return canSmelt(stack);
    }

    private boolean canSmelt(ItemStack stack) {
        ItemStack itemstack = FurnaceRecipes.instance().getSmeltingResult(stack);
        return itemstack != null;
    }
}
