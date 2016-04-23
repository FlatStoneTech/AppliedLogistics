package tech.flatstone.appliedlogistics.api.registries;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import tech.flatstone.appliedlogistics.api.registries.helpers.Crushable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PulverizerRegistry {
    private static ArrayList<Crushable> registry = new ArrayList<Crushable>();

    public static void register(ItemStack input, ItemStack output, float chance, boolean allowMachineFortune) {
        registry.add(new Crushable(input, output, chance, allowMachineFortune ? 1.0f : 0.0f));
    }

    public static List<ItemStack> getBlocks() {
        ArrayList<ItemStack> blocks = new ArrayList();

        Iterator<Crushable> iterator = registry.iterator();
        while (iterator.hasNext()) {
            Crushable entry = iterator.next();

            if (!blocks.contains(entry.inItemStack))
                blocks.add(entry.inItemStack);
        }

        return blocks;
    }

    public static ArrayList<Crushable> getDrops(ItemStack itemStack) {
        ArrayList<Crushable> dropsList = new ArrayList();

        Iterator<Crushable> it = registry.iterator();
        while (it.hasNext()) {
            Crushable drop = it.next();

            if (drop.inItemStack.isItemEqual(itemStack) && drop.outItemStack != null)
                dropsList.add(drop);
        }

        return dropsList;
    }

    public static boolean containsBlock(ItemStack itemStack) {
        ArrayList<Crushable> blocks = new ArrayList();

        Iterator<Crushable> it = registry.iterator();
        while (it.hasNext()) {
            Crushable block = it.next();
            if (itemStack.isItemEqual(block.inItemStack)) {
                return true;
            }
        }

        return false;
    }

    public static void registerOreDictOre(String oreName) {
        List<ItemStack> itemOres = OreDictionary.getOres("ores" + oreName);
        List<ItemStack> itemDusts = OreDictionary.getOres("dust" + oreName);

        if (itemOres.size() == 0 || itemDusts.size() == 0)
            return;

        for (int i = 0; i < itemOres.size(); i++) {
            ItemStack input = itemOres.get(i);
            ItemStack output = itemDusts.get(0);
            register(input, output, 0.8f, true);
        }
    }

    public static void registerOreDictIngot(String ingotName) {
        List<ItemStack> itemOres = OreDictionary.getOres("ingot" + ingotName);
        List<ItemStack> itemDusts = OreDictionary.getOres("dust" + ingotName);

        if (itemOres.size() == 0 || itemDusts.size() == 0)
            return;

        for (int i = 0; i < itemOres.size(); i++) {
            ItemStack input = itemOres.get(i);
            ItemStack output = itemDusts.get(0);
            register(input, output, 1.0f, false);
        }
    }
}
