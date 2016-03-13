package tech.flatstone.appliedlogistics.common.container.slot;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotBase extends Slot {
    public ItemStack overlayIcon = null;
    public int defX;
    public int defY;
    public boolean isDraggable = true;
    public boolean isPlayerSide = false;
    public boolean isDisplay = false;
    public boolean isEnabled = true;

    public SlotBase(IInventory inventory, int idx, int x, int y) {
        super(inventory, idx, x, y);
        this.defX = x;
        this.defY = y;
    }

    public Slot setNotDraggable() {
        this.isDraggable = false;
        return this;
    }

    public Slot setPlayerSide() {
        this.isPlayerSide = true;
        return this;
    }

    @Override
    public boolean canBeHovered() {
        return isEnabled;
    }

    public String getToolTip() {
        return null;
    }

    @Override
    public ItemStack getStack() {
        if (!isEnabled)
            return null;

        if (this.inventory.getSizeInventory() <= getSlotIndex())
            return null;

        if (this.isDisplay) {
            this.isDisplay = false;
            return getDisplayStack();
        }

        return super.getStack();
    }

    @Override
    public void putStack(ItemStack stack) {
        if (!isEnabled)
            return;

        super.putStack(stack);
    }

    public void clearStack() {
        super.putStack(null);
    }

    @Override
    public boolean canTakeStack(EntityPlayer playerIn) {
        if (!isEnabled)
            return false;

        return super.canTakeStack(playerIn);
    }

    @Override
    public boolean isItemValid(ItemStack stack) {
        if (!isEnabled)
            return false;

        return super.isItemValid(stack);
    }

    public ItemStack getDisplayStack() {
        return super.getStack();
    }

    public float getOpacityOfIcon() {
        return 0.4f;
    }

    public ItemStack getIcon() {
        return this.overlayIcon;
    }

    public boolean isPlayerSide() {
        return this.isPlayerSide;
    }

    public boolean renderIconWithItem() {
        return false;
    }
}
