package tech.flatstone.appliedlogistics.common.items.misc;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;

public class ItemCrank extends ItemBlock {
    public ItemCrank(Block block) {
        super(block);
        this.setMaxStackSize(4);
    }
}
