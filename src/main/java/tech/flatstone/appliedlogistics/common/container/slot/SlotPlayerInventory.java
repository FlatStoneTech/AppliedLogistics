package tech.flatstone.appliedlogistics.common.container.slot;

import net.minecraft.inventory.IInventory;

public class SlotPlayerInventory extends SlotBase {

    public SlotPlayerInventory(IInventory inventory, int idx, int x, int y) {
        super(inventory, idx, x, y);
        this.isPlayerSide = true;
    }
}
