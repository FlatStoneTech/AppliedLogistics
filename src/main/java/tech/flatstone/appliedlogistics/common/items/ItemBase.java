package tech.flatstone.appliedlogistics.common.items;

import net.minecraft.item.Item;
import tech.flatstone.appliedlogistics.ModInfo;

public abstract class ItemBase extends Item {
    @Override
    public String getUnlocalizedName() {
        String blockName = getUnwrappedUnlocalizedName(super.getUnlocalizedName());

        String test = String.format("item.%s.%s", ModInfo.MOD_ID, blockName);
        return test;
    }

    protected String getUnwrappedUnlocalizedName(String unlocalizedName) {
        return unlocalizedName.substring(unlocalizedName.indexOf(":") + 1);
    }
}
