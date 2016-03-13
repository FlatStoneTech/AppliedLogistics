package tech.flatstone.appliedlogistics.common.items;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import tech.flatstone.appliedlogistics.ModInfo;

public abstract class ItemBase extends Item {
    @Override
    public String getUnlocalizedName() {
        String itemName = getUnwrappedUnlocalizedName(super.getUnlocalizedName());

        String test = String.format("item.%s.%s", ModInfo.MOD_ID, itemName);
        return test;
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        String itemName = getUnwrappedUnlocalizedName(super.getUnlocalizedName(stack));

        String test = String.format("item.%s.%s", ModInfo.MOD_ID, itemName);
        return test;
    }

    protected String getUnwrappedUnlocalizedName(String unlocalizedName) {
        return unlocalizedName.substring(unlocalizedName.indexOf(":") + 1);
    }
}
