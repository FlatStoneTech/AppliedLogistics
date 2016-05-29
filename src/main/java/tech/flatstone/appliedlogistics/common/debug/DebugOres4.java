package tech.flatstone.appliedlogistics.common.debug;

import net.minecraft.item.ItemStack;
import tech.flatstone.appliedlogistics.api.features.EnumOreType;
import tech.flatstone.appliedlogistics.common.items.Items;
import tech.flatstone.appliedlogistics.common.util.DebugItemHelper;
import tech.flatstone.appliedlogistics.common.util.EnumOres;

public class DebugOres4 implements IDebugChest {
    @Override
    public ItemStack getDebugChest() {
        DebugItemHelper debugChest = new DebugItemHelper("Ores 4/4");

        for (int i = 0; i < 6; i++) {

            if (EnumOres.byMeta(i + 9).isTypeSet(EnumOreType.NUGGET))
                debugChest.setItem(i, new ItemStack(Items.ITEM_ORE_NUGGET.getItem(), 1, i + 9));

            if (EnumOres.byMeta(i + 9).isTypeSet(EnumOreType.DUST))
                debugChest.setItem(i + 9, new ItemStack(Items.ITEM_ORE_DUST.getItem(), 1, i + 9));

            //todo: // FIXME: 5/28/16 
            //if (EnumOres.byMeta(i + 9).isTypeSet(EnumOreType.GEAR))
            //    debugChest.setItem(i + 18, new ItemStack(Items.ITEM_MATERIAL_GEAR.getItem(), 1, i + 9));
        }

        return debugChest.registerItem();
    }
}
