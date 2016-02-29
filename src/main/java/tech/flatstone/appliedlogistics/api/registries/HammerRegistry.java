package tech.flatstone.appliedlogistics.api.registries;

import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import tech.flatstone.appliedlogistics.api.registries.helpers.Smashable;

import java.util.ArrayList;
import java.util.Iterator;

public class HammerRegistry {
    public static ArrayList<Smashable> registery = new ArrayList<Smashable>();

    public static void register(IBlockState input, ItemStack output, float chance, float luckMultiplier) {
        registery.add(new Smashable(input, output, chance, luckMultiplier));
    }

    public static IBlockState[] getBlocks() {
        ArrayList<IBlockState> blockStates = new ArrayList();

        Iterator<Smashable> iterator = registery.iterator();
        while (iterator.hasNext()) {
            Smashable entry = iterator.next();

            if (!blockStates.contains(entry.inBlock))
                blockStates.add(entry.inBlock);
        }

        return blockStates.toArray(new IBlockState[blockStates.size()]);
    }
}
