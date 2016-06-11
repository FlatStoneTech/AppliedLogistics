package tech.flatstone.appliedlogistics.common.debug;

import net.minecraft.item.ItemStack;
import tech.flatstone.appliedlogistics.api.features.EnumOreType;
import tech.flatstone.appliedlogistics.common.blocks.Blocks;
import tech.flatstone.appliedlogistics.common.items.Items;
import tech.flatstone.appliedlogistics.common.util.DebugItemHelper;
import tech.flatstone.appliedlogistics.common.util.EnumOres;

public class DebugOres1 implements IDebugChest {
    @Override
    public ItemStack getDebugChest() {
        DebugItemHelper debugChest = new DebugItemHelper("Ores 1/4");

        for (int i = 0; i < 9; i++) {

            if (EnumOres.byMeta(i).isTypeSet(EnumOreType.ORE))
                debugChest.setItem(i, Blocks.BLOCK_ORE.getStack(1, i));

            if (EnumOres.byMeta(i).isTypeSet(EnumOreType.STORAGE_BLOCK))
                debugChest.setItem(i + 9, Blocks.BLOCK_ORE_STORAGE_BLOCK.getStack(1, i));

            if (EnumOres.byMeta(i).isTypeSet(EnumOreType.INGOT))
                debugChest.setItem(i + 18, Items.ITEM_ORE_INGOT.getStack(1, i));

            if (EnumOres.byMeta(i).isTypeSet(EnumOreType.VANILLA)) {
                switch (i) {
                    case 0:
                        debugChest.setItem(i, new ItemStack(net.minecraft.init.Blocks.IRON_ORE));
                        debugChest.setItem(i + 9, new ItemStack(net.minecraft.init.Blocks.IRON_BLOCK));
                        debugChest.setItem(i + 18, new ItemStack(net.minecraft.init.Items.IRON_INGOT));
                        break;
                    case 1:
                        debugChest.setItem(i, new ItemStack(net.minecraft.init.Blocks.GOLD_ORE));
                        debugChest.setItem(i + 9, new ItemStack(net.minecraft.init.Blocks.GOLD_BLOCK));
                        debugChest.setItem(i + 18, new ItemStack(net.minecraft.init.Items.GOLD_INGOT));
                        break;
                    case 2:
                        debugChest.setItem(i, new ItemStack(net.minecraft.init.Blocks.DIAMOND_ORE));
                        debugChest.setItem(i + 9, new ItemStack(net.minecraft.init.Blocks.DIAMOND_BLOCK));
                        debugChest.setItem(i + 18, new ItemStack(net.minecraft.init.Items.DIAMOND));
                        break;
                    default:
                        break;
                }
            }
        }

        return debugChest.registerItem();
    }
}
