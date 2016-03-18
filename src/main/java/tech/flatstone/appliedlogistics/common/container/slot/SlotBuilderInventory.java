package tech.flatstone.appliedlogistics.common.container.slot;

import net.minecraft.entity.player.EntityPlayer;
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
        return canBeHovered();
    }
}
