package tech.flatstone.appliedlogistics.common;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import tech.flatstone.appliedlogistics.ModInfo;

public class AppliedLogisticsCreativeTabs {
    public static final CreativeTabs tabGeneral = new CreativeTabs(ModInfo.MOD_ID) {
        @Override
        public Item getTabIconItem() {
            return Items.apple;
        }
    };
}
