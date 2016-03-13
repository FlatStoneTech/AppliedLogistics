package tech.flatstone.appliedlogistics.api.registries;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Sets;
import net.minecraft.item.Item;
import tech.flatstone.appliedlogistics.api.features.IMachinePlan;

import java.util.Collection;
import java.util.Set;

public class PlanRegistry {
    private static final Set<Item> planItems = Sets.newHashSet();

    public static void registerPlan(Item plan) {
        if (plan instanceof IMachinePlan)
            planItems.add(plan);
    }

    public static Collection<Item> getPlanItems() {
        return ImmutableList.copyOf(planItems);
    }

    public static Item getPlanAsItem(String planName) {
        for (Item item : planItems) {
            if (planName.equalsIgnoreCase(item.getUnlocalizedName())) {
                return item;
            }
        }

        return null;
    }
}
