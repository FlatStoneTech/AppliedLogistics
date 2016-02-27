package tech.flatstone.appliedlogistics.common.util;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemStackSrc {
    public final Item item;
    public final Block block;
    public final int damage;

    public ItemStackSrc(Item i, int dmg) {
        this.block = null;
        this.item = i;
        this.damage = dmg;
    }

    public ItemStackSrc(Block b, int dmg) {
        this.block = b;
        this.item = null;
        this.damage = dmg;
    }

    public ItemStack stack(int i) {
        if (this.block != null) {
            return new ItemStack(this.block, i, this.damage);
        }
        return new ItemStack(this.item, i, this.damage);
    }
}
