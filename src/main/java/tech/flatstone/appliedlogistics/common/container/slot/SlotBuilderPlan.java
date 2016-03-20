package tech.flatstone.appliedlogistics.common.container.slot;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import tech.flatstone.appliedlogistics.common.tileentities.builder.TileEntityBuilder;

public class SlotBuilderPlan extends SlotBase {
    TileEntityBuilder tileEntity;

    public SlotBuilderPlan(IInventory inventory, int idx, int x, int y, TileEntityBuilder tileEntity) {
        super(inventory, idx, x, y);
        this.tileEntity = tileEntity;
    }

    @Override
    public boolean canBeHovered() {
        if (tileEntity.getTicksRemaining() > 0)
            return false;

        return true;
    }

    @Override
    public boolean isItemValid(ItemStack stack) {
        if (tileEntity.getTicksRemaining() > 0)
            return false;

        return true;
    }

    @Override
    public boolean canTakeStack(EntityPlayer playerIn) {
        if (tileEntity.getTicksRemaining() > 0)
            return false;

        return true;
    }
}
