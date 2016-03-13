package tech.flatstone.appliedlogistics.common.items.plans;

import com.google.common.collect.Lists;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.oredict.OreDictionary;
import tech.flatstone.appliedlogistics.ModInfo;
import tech.flatstone.appliedlogistics.api.features.IMachinePlan;
import tech.flatstone.appliedlogistics.api.features.TechLevel;
import tech.flatstone.appliedlogistics.common.blocks.Blocks;
import tech.flatstone.appliedlogistics.common.items.ItemPlanBase;
import tech.flatstone.appliedlogistics.common.util.EnumMachineParts;
import tech.flatstone.appliedlogistics.common.util.IMachineTechLevel;
import tech.flatstone.appliedlogistics.common.util.IMachineParts;
import tech.flatstone.appliedlogistics.common.util.LanguageHelper;

import java.util.HashMap;
import java.util.List;

public class PlanPulverizer extends ItemPlanBase implements IMachinePlan {
    public PlanPulverizer() {
        this.setUnlocalizedName(String.format("%s:%s", ModInfo.MOD_ID, "plan.pulverizer"));
    }

    @Override
    public String getUnlocalizedPlanDescription() {
        return EnumChatFormatting.YELLOW + "" + EnumChatFormatting.ITALIC + LanguageHelper.getTranslated(String.format("description.%s.plan.pulverizer", ModInfo.MOD_ID));
    }

    @Override
    public HashMap<TechLevel, IMachineTechLevel> getTechLevels() {
        HashMap<TechLevel, IMachineTechLevel> techLevels = new HashMap<TechLevel, IMachineTechLevel>();

        techLevels.put(TechLevel.STONE_AGE, new IMachineTechLevel() {
            @Override
            public int getMaxWeight() {
                return 38;
            }

            @Override
            public HashMap<EnumMachineParts, List<IMachineParts>> getSlotGroups() {
                HashMap<EnumMachineParts, List<IMachineParts>> machineParts = new HashMap<EnumMachineParts, List<IMachineParts>>();

                /**
                 * Machine Frame (Weight 28)
                 */
                List<IMachineParts> MachineFrame = Lists.newArrayList();
                MachineFrame.add(new IMachineParts() {
                    @Override
                    public List<ItemStack> getRequiredMaterial() {
                        return OreDictionary.getOres("cobblestone");
                    }

                    @Override
                    public int minCount() {
                        return 12;
                    }

                    @Override
                    public int maxCount() {
                        return 12;
                    }

                    @Override
                    public int itemWeight() {
                        return 2;
                    }

                    @Override
                    public float addTime() {
                        return 120;
                    }

                    @Override
                    public float removeTime() {
                        return 200;
                    }
                });

                MachineFrame.add(new IMachineParts() {
                    @Override
                    public List<ItemStack> getRequiredMaterial() {
                        return OreDictionary.getOres("plankWood");
                    }

                    @Override
                    public int minCount() {
                        return 4;
                    }

                    @Override
                    public int maxCount() {
                        return 4;
                    }

                    @Override
                    public int itemWeight() {
                        return 1;
                    }

                    @Override
                    public float addTime() {
                        return 40;
                    }

                    @Override
                    public float removeTime() {
                        return 80;
                    }
                });
                machineParts.put(EnumMachineParts.MACHINE_FRAME, MachineFrame);

                /**
                 * Machine Internal
                 */
                List<IMachineParts> MachineInternal = Lists.newArrayList();
                MachineInternal.add(new IMachineParts() {
                    @Override
                    public List<ItemStack> getRequiredMaterial() {
                        return OreDictionary.getOres("gearWood");
                    }

                    @Override
                    public int minCount() {
                        return 0;
                    }

                    @Override
                    public int maxCount() {
                        return 1;
                    }

                    @Override
                    public int itemWeight() {
                        return 3;
                    }

                    @Override
                    public float addTime() {
                        return 60;
                    }

                    @Override
                    public float removeTime() {
                        return 60;
                    }
                });

                MachineInternal.add(new IMachineParts() {
                    @Override
                    public List<ItemStack> getRequiredMaterial() {
                        return OreDictionary.getOres("gearStone");
                    }

                    @Override
                    public int minCount() {
                        return 1;
                    }

                    @Override
                    public int maxCount() {
                        return 2;
                    }

                    @Override
                    public int itemWeight() {
                        return 5;
                    }

                    @Override
                    public float addTime() {
                        return 80;
                    }

                    @Override
                    public float removeTime() {
                        return 80;
                    }
                });
                machineParts.put(EnumMachineParts.MACHINE_INTERNAL, MachineInternal);

                return machineParts;
            }
        });

        return techLevels;
    }

    @Override
    public ItemStack getMachineItemStack() {
        return new ItemStack(Blocks.BLOCK_MACHINE_PULVERIZER.block);
    }
}
