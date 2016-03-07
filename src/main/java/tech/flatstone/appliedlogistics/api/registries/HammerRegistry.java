package tech.flatstone.appliedlogistics.api.registries;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import tech.flatstone.appliedlogistics.api.registries.helpers.Hammerable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class HammerRegistry {
    public static ArrayList<Hammerable> registery = new ArrayList<Hammerable>();

    public static void register(ItemStack input, ItemStack output, float chance, float luckMultiplier) {
        registery.add(new Hammerable(input, output, chance, luckMultiplier));
    }

    public static List<ItemStack> getBlocks() {
        ArrayList<ItemStack> blocks = new ArrayList();

        Iterator<Hammerable> iterator = registery.iterator();
        while (iterator.hasNext()) {
            Hammerable entry = iterator.next();

            if (!blocks.contains(entry.inItemStack))
                blocks.add(entry.inItemStack);
        }

        return blocks;
    }

    public static ArrayList<Hammerable> getDrops(ItemStack itemStack) {
        ArrayList<Hammerable> dropsList = new ArrayList();

        Iterator<Hammerable> it = registery.iterator();
        while (it.hasNext()) {
            Hammerable drop = it.next();

            if (drop.inItemStack.isItemEqual(itemStack) && drop.outItemStack != null)
                dropsList.add(drop);
        }

        return dropsList;
    }

    public static boolean containsBlock(ItemStack itemStack) {
        ArrayList<Hammerable> blocks = new ArrayList();

        Iterator<Hammerable> it = registery.iterator();
        while (it.hasNext()) {
            Hammerable block = it.next();
            if (itemStack.isItemEqual(block.inItemStack)) {
                return true;
            }
        }

        return false;
    }

    public static void registerOreDictOre(String oreName) {
        List<ItemStack> itemOres = OreDictionary.getOres("ore" + oreName);
        List<ItemStack> itemDusts = OreDictionary.getOres("dust" + oreName);

        if (itemOres.size() == 0 || itemDusts.size() == 0)
            return;

        for (int i = 0; i < itemOres.size(); i++) {
            ItemStack input = itemOres.get(i);
            ItemStack output = itemDusts.get(0);
            register(input, output, 1.0f, 0.0f);
            register(input, output, 0.5f, 0.0f);
            register(input, output, 0.0f, 0.5f);
        }
    }
}
