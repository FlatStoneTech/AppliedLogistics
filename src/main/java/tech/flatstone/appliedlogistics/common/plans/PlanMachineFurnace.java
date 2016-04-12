package tech.flatstone.appliedlogistics.common.plans;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import tech.flatstone.appliedlogistics.api.features.IMachinePlan;
import tech.flatstone.appliedlogistics.api.features.TechLevel;
import tech.flatstone.appliedlogistics.common.blocks.Blocks;
import tech.flatstone.appliedlogistics.common.items.ItemPlanBase;
import tech.flatstone.appliedlogistics.common.items.Items;
import tech.flatstone.appliedlogistics.common.util.LanguageHelper;
import tech.flatstone.appliedlogistics.common.util.PlanDetails;
import tech.flatstone.appliedlogistics.common.util.PlanRequiredMaterials;

import java.util.ArrayList;
import java.util.List;

public class PlanMachineFurnace extends ItemPlanBase implements IMachinePlan {
    @Override
    public String getLocalizedPlanDescription() {
        return LanguageHelper.DESCRIPTION.translateMessage("plan.furnace");
    }

    @Override
    public PlanDetails getTechLevels(TechLevel techLevel) {
        PlanDetails planDetails;
        List<PlanRequiredMaterials> requiredMaterialsList = new ArrayList<PlanRequiredMaterials>();

        switch (techLevel) {
            case STONE_AGE:
                requiredMaterialsList.add(new PlanRequiredMaterials(OreDictionary.getOres("cobblestone"), 8, 8, 0, 0, 0, "Cobble?"));
                requiredMaterialsList.add(new PlanRequiredMaterials(OreDictionary.getOres("gearIron"), 0, 1, 10, 60, 60, "Gear?"));
                requiredMaterialsList.add(new PlanRequiredMaterials(OreDictionary.getOres("gearStone"), 0, 1, 10, 60, 60, "Another Gear?"));
                requiredMaterialsList.add(new PlanRequiredMaterials(new ItemStack(Items.ITEM_KIT_REDSTONE_OUTPUT.getItem()), 0, 1, 10, 200, 200, "Adds comparator output"));
                requiredMaterialsList.add(new PlanRequiredMaterials(new ItemStack(Items.ITEM_KIT_REDSTONE_INPUT.getItem()), 0, 1, 10, 200, 200, "Adds redstone input to launch build"));
                requiredMaterialsList.add(new PlanRequiredMaterials(new ItemStack(Items.ITEM_KIT_AUTOMATION.getItem()), 0, 1, 10, 200, 200, "Adds support to adding / removing items with pipes and hoppers"));
                requiredMaterialsList.add(new PlanRequiredMaterials(new ItemStack(Items.ITEM_KIT_CRAFTING.getItem()), 0, 1, 10, 200, 200, "Adds crafting grid to machine"));

                planDetails = new PlanDetails(300, requiredMaterialsList, Blocks.BLOCK_MACHINE_FURNACE.getStack());
                break;

            default:
                planDetails = null;
        }

        return planDetails;
    }

    @Override
    public String getMachineDetails(TechLevel techLevel, List<ItemStack> inventory) {
        return "";
    }

    @Override
    public int getPlanRequiredXP() {
        return 0;
    }
}
