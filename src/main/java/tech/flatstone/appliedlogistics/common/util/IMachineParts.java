package tech.flatstone.appliedlogistics.common.util;

import net.minecraft.item.ItemStack;

import java.util.List;

public interface IMachineParts {
    List<ItemStack> getRequiredMaterial();

    int minCount();
    int maxCount();

    int itemWeight();

    float addTime();
    float removeTime();
}
