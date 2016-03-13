package tech.flatstone.appliedlogistics.common;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import tech.flatstone.appliedlogistics.ModInfo;
import tech.flatstone.appliedlogistics.common.blocks.Blocks;
import tech.flatstone.appliedlogistics.common.items.Items;

public class AppliedLogisticsCreativeTabs {
    public static final CreativeTabs tabGeneral = new CreativeTabs(ModInfo.MOD_ID) {
        @Override
        public Item getTabIconItem() {
            return Items.ITEM_TOOL_HAMMER.item;
        }
    };

    public static final CreativeTabs tabOres = new CreativeTabs(ModInfo.MOD_ID) {
        @Override
        public Item getTabIconItem() {
            return Items.ITEM_ORE_DUST.item;
        }
    };

    public static final CreativeTabs tabMachines = new CreativeTabs(ModInfo.MOD_ID) {
        @Override
        public Item getTabIconItem() {
            return Items.ITEM_MATERIAL_GEAR.item;
        }
    };
}
