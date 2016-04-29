package tech.flatstone.appliedlogistics.common.items.machines;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import tech.flatstone.appliedlogistics.api.features.TechLevel;

public class ItemFurnace extends ItemBlock {

    public ItemFurnace(Block block) {
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
        String techName = TechLevel.values()[stack.getItemDamage()].getName();
        return name + "." + techName;
    }
}
