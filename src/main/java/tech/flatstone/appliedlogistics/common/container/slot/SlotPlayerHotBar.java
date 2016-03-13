package tech.flatstone.appliedlogistics.common.container.slot;

import net.minecraft.inventory.IInventory;

/**
 * Created by FireBall1725 on 3/12/2016.
 */
public class SlotPlayerHotBar extends SlotBase {
    public SlotPlayerHotBar(IInventory inventory, int idx, int x, int y) {
        super(inventory, idx, x, y);
        isPlayerSide = true;
    }
}
