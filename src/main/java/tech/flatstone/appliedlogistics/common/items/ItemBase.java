package tech.flatstone.appliedlogistics.common.items;

import net.minecraft.item.Item;

public abstract class ItemBase extends Item {
    @Override
    public String getUnlocalizedName() {
        String blockName = getUnwrappedUnlocalizedName(super.getUnlocalizedName());

        String test = String.format("item.%s", blockName);
        return test;
    }

    protected String getUnwrappedUnlocalizedName(String unlocalizedName) {
        return unlocalizedName.substring(unlocalizedName.indexOf(":") + 1);
    }
}
