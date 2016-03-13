package tech.flatstone.appliedlogistics.api.features;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import tech.flatstone.appliedlogistics.common.util.IMachineTechLevel;

import java.util.HashMap;

public interface IMachinePlan {
    /**
     * Gets the unlocalized name for the description
     * @return
     */
    String getUnlocalizedPlanDescription();

    /**
     * Gets the tech levels for the plan
     * @return
     */
    HashMap<TechLevel, IMachineTechLevel> getTechLevels();

    ItemStack getMachineItemStack();
}
