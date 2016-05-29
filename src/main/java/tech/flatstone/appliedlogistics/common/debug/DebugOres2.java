package tech.flatstone.appliedlogistics.common.debug;

import net.minecraft.item.ItemStack;
import tech.flatstone.appliedlogistics.api.features.EnumOreType;
import tech.flatstone.appliedlogistics.common.items.Items;
import tech.flatstone.appliedlogistics.common.util.DebugItemHelper;
import tech.flatstone.appliedlogistics.common.util.EnumOres;

public class DebugOres2 implements IDebugChest {
    @Override
    public ItemStack getDebugChest() {
        DebugItemHelper debugChest = new DebugItemHelper("Ores 2/4");

        for (int i = 0; i < 9; i++) {

            if (EnumOres.byMeta(i).isTypeSet(EnumOreType.NUGGET))
                debugChest.setItem(i, new ItemStack(Items.ITEM_ORE_NUGGET.getItem(), 1, i));

            if (EnumOres.byMeta(i).isTypeSet(EnumOreType.DUST))
                debugChest.setItem(i + 9, new ItemStack(Items.ITEM_ORE_DUST.getItem(), 1, i));
            
            //todo: // FIXME: 5/28/16
            //if (EnumOres.byMeta(i).isTypeSet(EnumOreType.GEAR))
            //    debugChest.setItem(i + 18, new ItemStack(Items.ITEM_MATERIAL_GEAR.getItem(), 1, i));

            if (EnumOres.byMeta(i).isTypeSet(EnumOreType.VANILLA)) {
                switch (i) {
                    case 1:
                        debugChest.setItem(i, new ItemStack(net.minecraft.init.Items.gold_nugget));
                        break;
                    default:
                        break;
                }
            }
        }

        return debugChest.registerItem();
    }
}
