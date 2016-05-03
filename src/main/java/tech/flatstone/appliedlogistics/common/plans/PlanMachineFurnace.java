package tech.flatstone.appliedlogistics.common.plans;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import tech.flatstone.appliedlogistics.api.features.IMachinePlan;
import tech.flatstone.appliedlogistics.api.features.TechLevel;
import tech.flatstone.appliedlogistics.common.blocks.Blocks;
import tech.flatstone.appliedlogistics.common.items.Items;
import tech.flatstone.appliedlogistics.common.util.LanguageHelper;
import tech.flatstone.appliedlogistics.common.util.PlanDetails;
import tech.flatstone.appliedlogistics.common.util.PlanRequiredMaterials;

import java.util.ArrayList;
import java.util.List;

public class PlanMachineFurnace extends PlanBase implements IMachinePlan {
    public PlanMachineFurnace() {
        super("furnace");
    }

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
                requiredMaterialsList.add(new PlanRequiredMaterials(OreDictionary.getOres("cobblestone"), 12, 12, 0, 0, 0, ""));

                requiredMaterialsList.add(new PlanRequiredMaterials(new ItemStack(net.minecraft.init.Blocks.furnace), 1, 2, 10, 60, 60));
                requiredMaterialsList.add(new PlanRequiredMaterials(OreDictionary.getOres("gearIron"), 0, 1, 10, 60, 60));

                requiredMaterialsList.add(new PlanRequiredMaterials(new ItemStack(Items.ITEM_KIT_CRAFTING.getItem()), 0, 1, 10, 200, 200, "Adds crafting grid to machine"));

                planDetails = new PlanDetails(300, requiredMaterialsList, Blocks.BLOCK_MACHINE_FURNACE.getStack());
                break;

            case BRONZE_AGE:
                requiredMaterialsList.add(new PlanRequiredMaterials(OreDictionary.getOres("stone"), 10, 10, 0, 0, 0, ""));
                requiredMaterialsList.add(new PlanRequiredMaterials(OreDictionary.getOres("ingotBronze"), 6, 6, 0, 0, 0, ""));

                requiredMaterialsList.add(new PlanRequiredMaterials(OreDictionary.getOres("chestWood"), 0, 1, 10, 60, 60, "More Slots"));

                requiredMaterialsList.add(new PlanRequiredMaterials(new ItemStack(net.minecraft.init.Blocks.furnace), 1, 3, 10, 60, 60));
                requiredMaterialsList.add(new PlanRequiredMaterials(OreDictionary.getOres("gearIron"), 0, 2, 10, 60, 60));

                requiredMaterialsList.add(new PlanRequiredMaterials(new ItemStack(Items.ITEM_KIT_REDSTONE_OUTPUT.getItem()), 0, 1, 10, 200, 200, "Adds comparator output"));
                requiredMaterialsList.add(new PlanRequiredMaterials(new ItemStack(Items.ITEM_KIT_REDSTONE_INPUT.getItem()), 0, 1, 10, 200, 200, "Adds redstone input to launch build"));
                requiredMaterialsList.add(new PlanRequiredMaterials(new ItemStack(Items.ITEM_KIT_AUTOMATION.getItem()), 0, 1, 10, 200, 200, "Adds support to adding / removing items with pipes and hoppers"));
                requiredMaterialsList.add(new PlanRequiredMaterials(new ItemStack(Items.ITEM_KIT_CRAFTING.getItem()), 0, 1, 10, 200, 200, "Adds crafting grid to machine"));

                planDetails = new PlanDetails(300, requiredMaterialsList, Blocks.BLOCK_MACHINE_FURNACE.getStack());
                break;

            case INDUSTRIAL_AGE:
                requiredMaterialsList.add(new PlanRequiredMaterials(OreDictionary.getOres("stone"), 10, 10, 0, 0, 0, ""));
                requiredMaterialsList.add(new PlanRequiredMaterials(OreDictionary.getOres("ingotSteel"), 8, 8, 0, 0, 0, ""));

                requiredMaterialsList.add(new PlanRequiredMaterials(OreDictionary.getOres("chestWood"), 0, 1, 10, 60, 60, "More Slots"));

                requiredMaterialsList.add(new PlanRequiredMaterials(new ItemStack(net.minecraft.init.Blocks.furnace), 1, 3, 10, 60, 60));
                requiredMaterialsList.add(new PlanRequiredMaterials(OreDictionary.getOres("gearIron"), 0, 2, 10, 60, 60));

                requiredMaterialsList.add(new PlanRequiredMaterials(new ItemStack(Items.ITEM_KIT_REDSTONE_OUTPUT.getItem()), 0, 1, 10, 200, 200, "Adds comparator output"));
                requiredMaterialsList.add(new PlanRequiredMaterials(new ItemStack(Items.ITEM_KIT_REDSTONE_INPUT.getItem()), 0, 1, 10, 200, 200, "Adds redstone input to launch build"));
                requiredMaterialsList.add(new PlanRequiredMaterials(new ItemStack(Items.ITEM_KIT_AUTOMATION.getItem()), 0, 1, 10, 200, 200, "Adds support to adding / removing items with pipes and hoppers"));
                requiredMaterialsList.add(new PlanRequiredMaterials(new ItemStack(Items.ITEM_KIT_CRAFTING.getItem()), 0, 1, 10, 200, 200, "Adds crafting grid to machine"));

                planDetails = new PlanDetails(300, requiredMaterialsList, Blocks.BLOCK_MACHINE_FURNACE.getStack());
                break;

            case MECHANICAL_AGE:
                requiredMaterialsList.add(new PlanRequiredMaterials(OreDictionary.getOres("stone"), 20, 20, 0, 0, 0, ""));
                requiredMaterialsList.add(new PlanRequiredMaterials(OreDictionary.getOres("ingotTitanium"), 8, 8, 0, 0, 0, ""));

                requiredMaterialsList.add(new PlanRequiredMaterials(OreDictionary.getOres("chestWood"), 0, 1, 10, 60, 60, "More Slots"));

                requiredMaterialsList.add(new PlanRequiredMaterials(new ItemStack(net.minecraft.init.Blocks.furnace), 1, 3, 10, 60, 60));
                requiredMaterialsList.add(new PlanRequiredMaterials(OreDictionary.getOres("gearIron"), 0, 2, 10, 60, 60));

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
        return "Hello World";
    }

    @Override
    public int getPlanRequiredXP() {
        return 0;
    }
}
