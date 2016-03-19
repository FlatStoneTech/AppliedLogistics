package tech.flatstone.appliedlogistics.common.container.slot;

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
    public ItemStack decrStackSize(int amount) {
        // todo: check if tile is processing or not...

        return super.decrStackSize(amount);
    }
}
