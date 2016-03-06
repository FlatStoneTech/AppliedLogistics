package tech.flatstone.appliedlogistics.common.util;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class OreDictHelper {
    public static boolean checkIfDustExists(ItemStack itemStack) {
        int[] idList = OreDictionary.getOreIDs(itemStack);

        if (idList.length == 0)
            return false;

        String oreDictName = OreDictionary.getOreName(idList[0]);

        if (!oreDictName.startsWith("ore"))
            return false;

        // OreName
        oreDictName = oreDictName.substring(3);

        if (!OreDictionary.doesOreNameExist("dust" + oreDictName))
            return false;

        return true;
    }
}
