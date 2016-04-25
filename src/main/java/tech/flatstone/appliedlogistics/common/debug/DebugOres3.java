package tech.flatstone.appliedlogistics.common.debug;

import net.minecraft.item.ItemStack;
import tech.flatstone.appliedlogistics.api.features.EnumOreType;
import tech.flatstone.appliedlogistics.common.blocks.Blocks;
import tech.flatstone.appliedlogistics.common.items.Items;
import tech.flatstone.appliedlogistics.common.util.DebugItemHelper;
import tech.flatstone.appliedlogistics.common.util.EnumOres;

public class DebugOres3 implements IDebugChest {
    @Override
    public ItemStack getDebugChest() {
        DebugItemHelper debugChest = new DebugItemHelper("Ores 3/4");

        for (int i = 0; i < 6; i++) {

            if (EnumOres.byMeta(i + 9).isTypeSet(EnumOreType.ORE))
                debugChest.setItem(i, new ItemStack(Blocks.BLOCK_ORE.getBlock(), 1, i + 9));

            if (EnumOres.byMeta(i + 9).isTypeSet(EnumOreType.BLOCK))
                debugChest.setItem(i + 9, new ItemStack(Blocks.BLOCK_ORE_BLOCK.getBlock(), 1, i + 9));

            if (EnumOres.byMeta(i + 9).isTypeSet(EnumOreType.INGOT))
                debugChest.setItem(i + 18, new ItemStack(Items.ITEM_ORE_INGOT.getItem(), 1, i + 9));
        }

        return debugChest.registerItem();
    }
}
