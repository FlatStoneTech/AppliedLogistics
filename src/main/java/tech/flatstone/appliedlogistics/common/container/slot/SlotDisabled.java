package tech.flatstone.appliedlogistics.common.container.slot;

import net.minecraft.inventory.IInventory;

public class SlotDisabled extends SlotBase {
    public SlotDisabled(IInventory inventory, int idx, int x, int y) {
        super(inventory, idx, x, y);
        this.isEnabled = false;
    }
}
