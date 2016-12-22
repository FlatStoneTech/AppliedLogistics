package tech.flatstone.appliedlogistics.common.items.misc;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import tech.flatstone.appliedlogistics.common.util.EnumCrankMaterials;

public class ItemCrank extends ItemBlock {
    public ItemCrank(Block block) {
        super(block);
        this.setMaxStackSize(4);
        this.setMaxDamage(0);
        this.setHasSubtypes(true);
    }

    @Override
    public int getMetadata(int damage) {
        return damage;
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        String name = super.getUnlocalizedName();
        String oreName = EnumCrankMaterials.values()[stack.getItemDamage()].getUnlocalizedName();
        return name + "." + oreName;
    }
}
