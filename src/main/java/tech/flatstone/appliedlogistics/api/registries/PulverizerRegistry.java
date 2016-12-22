package tech.flatstone.appliedlogistics.api.registries;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import tech.flatstone.appliedlogistics.api.registries.helpers.Crushable;

import java.util.ArrayList;
import java.util.List;

public class PulverizerRegistry {
    private static ArrayList<Crushable> registry = new ArrayList<>();

    public static void register(ItemStack input, ItemStack output, float chance, boolean allowMachineFortune) {
        registry.add(new Crushable(input, output, chance, allowMachineFortune ? 1.0f : 0.0f));
    }

    public static List<ItemStack> getBlocks() {
        ArrayList<ItemStack> blocks = new ArrayList<>();

        for (Crushable entry : registry) {
            if (!blocks.contains(entry.getInItemStack()))
                blocks.add(entry.getInItemStack());
        }

        return blocks;
    }

    public static List<Crushable> getDrops(ItemStack itemStack) {
        ArrayList<Crushable> dropsList = new ArrayList<>();

        for (Crushable drop : registry) {
            if (drop.getInItemStack().isItemEqual(itemStack) && drop.getOutItemStack() != null)
                dropsList.add(drop);
        }

        return dropsList;
    }

    public static boolean containsBlock(ItemStack itemStack) {
        for (Crushable block : registry) {
            if (itemStack.isItemEqual(block.getInItemStack())) {
                return true;
            }
        }

        return false;
    }

    public static void registerOreDictOre(String oreName) {
        List<ItemStack> itemOres = OreDictionary.getOres("ore" + oreName);
        List<ItemStack> itemDusts = OreDictionary.getOres("dust" + oreName);

        if (itemOres.isEmpty() || itemDusts.isEmpty())
            return;

        for (ItemStack input : itemOres) {
            ItemStack output = itemDusts.get(0);
            register(input, output, 0.8f, true);
        }
    }

    public static void registerOreDictIngot(String ingotName) {
        List<ItemStack> itemOres = OreDictionary.getOres("ingot" + ingotName);
        List<ItemStack> itemDusts = OreDictionary.getOres("dust" + ingotName);

        if (itemOres.isEmpty() || itemDusts.isEmpty())
            return;

        for (ItemStack input : itemOres) {
            ItemStack output = itemDusts.get(0);
            register(input, output, 1.0f, false);
        }
    }
}
