package tech.flatstone.appliedlogistics.common.items.misc;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import tech.flatstone.appliedlogistics.common.util.EnumMisc;

public class ItemMiscBlock extends ItemBlock {
    public ItemMiscBlock(Block block) {
        super(block);
        this.setHasSubtypes(true);
        this.setMaxDamage(0);
    }

    @Override
    public int getMetadata(int damage) {
        return damage;
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        String name = super.getUnlocalizedName();
        String materialName = EnumMisc.byMeta(stack.getItemDamage()).getUnlocalizedName();
        return name + "." + materialName;
    }
}
