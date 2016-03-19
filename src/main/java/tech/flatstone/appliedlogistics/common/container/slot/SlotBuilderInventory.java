package tech.flatstone.appliedlogistics.common.container.slot;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import tech.flatstone.appliedlogistics.common.tileentities.builder.TileEntityBuilder;
import tech.flatstone.appliedlogistics.common.util.PlanRequiredMaterials;

import java.util.List;

public class SlotBuilderInventory extends SlotBase {
    TileEntityBuilder tileEntity;
    int slotID;

    public SlotBuilderInventory(IInventory inventory, int idx, int x, int y, TileEntityBuilder tileEntity) {
        super(inventory, idx, x, y);
        this.tileEntity = tileEntity;
        this.slotID = idx;
    }

    @Override
    public boolean canBeHovered() {
        List<PlanRequiredMaterials> planRequiredMaterialsList = tileEntity.getPlanRequiredMaterialsList();
        if (planRequiredMaterialsList.size() < slotID)
            return false;

        return true;
    }

    @Override
    public boolean isItemValid(ItemStack stack) {
        if (!canBeHovered())
            return false;

        List<ItemStack> validItems = tileEntity.getPlanRequiredMaterialsList().get(slotID - 1).getRequiredMaterials();
        for (ItemStack validItem : validItems) {
            validItem.stackSize = 1;

            if (validItem.isItemEqual(stack))
                return true;

            if (validItem.getItemDamage() == Short.MAX_VALUE && validItem.getItem() == stack.getItem())
                return true;

        }

        return false;
    }

    @Override
    public int getSlotStackLimit() {
        return tileEntity.getPlanRequiredMaterialsList().get(slotID - 1).getMaxCount();
    }
}
