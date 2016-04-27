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
                debugChest.setItem(i, new ItemStack(Blocks.BLOCK_ORE.getBlock(), 1, i));

            if (EnumOres.byMeta(i).isTypeSet(EnumOreType.BLOCK))
                debugChest.setItem(i + 9, new ItemStack(Blocks.BLOCK_ORE_BLOCK.getBlock(), 1, i));

            if (EnumOres.byMeta(i).isTypeSet(EnumOreType.INGOT))
                debugChest.setItem(i + 18, new ItemStack(Items.ITEM_ORE_INGOT.getItem(), 1, i));

            if (EnumOres.byMeta(i).isTypeSet(EnumOreType.VANILLA)) {
                switch (i) {
                    case 0:
                        debugChest.setItem(i, new ItemStack(net.minecraft.init.Blocks.iron_ore));
                        debugChest.setItem(i + 9, new ItemStack(net.minecraft.init.Blocks.iron_block));
                        debugChest.setItem(i + 18, new ItemStack(net.minecraft.init.Items.iron_ingot));
                        break;
                    case 1:
                        debugChest.setItem(i, new ItemStack(net.minecraft.init.Blocks.gold_ore));
                        debugChest.setItem(i + 9, new ItemStack(net.minecraft.init.Blocks.gold_block));
                        debugChest.setItem(i + 18, new ItemStack(net.minecraft.init.Items.gold_ingot));
                        break;
                    case 2:
                        debugChest.setItem(i, new ItemStack(net.minecraft.init.Blocks.diamond_ore));
                        debugChest.setItem(i + 9, new ItemStack(net.minecraft.init.Blocks.diamond_block));
                        debugChest.setItem(i + 18, new ItemStack(net.minecraft.init.Items.diamond));
                        break;
                    default:
                        break;
                }
            }
        }

        return debugChest.registerItem();
    }
}
