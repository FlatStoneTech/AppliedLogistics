package tech.flatstone.appliedlogistics.common.util;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import tech.flatstone.appliedlogistics.api.registries.HammerRegistry;

import java.util.List;

public class OreDictHelper {
    public static boolean checkForDust(ItemStack itemStack) {
        int[] idList = OreDictionary.getOreIDs(itemStack);
        if (idList.length == 0)
            return false;

        for (int i = 0; i < idList.length; i++) {
            String oreDictName = OreDictionary.getOreName(idList[i]);

            if (oreDictName.startsWith("ore")) {
                oreDictName = oreDictName.substring(3);

                List<ItemStack> oreDusts = OreDictionary.getOres("dust" + oreDictName);
                if (oreDusts.size() > 0 && HammerRegistry.containsBlock(itemStack)) {
                    return true;
                }
            }
        }

        return false;
    }
}
