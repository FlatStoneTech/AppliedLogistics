package tech.flatstone.appliedlogistics.common.util;

import net.minecraft.item.ItemStack;
import tech.flatstone.appliedlogistics.api.features.TechLevel;
import tech.flatstone.appliedlogistics.api.features.plan.SlotTechLevelProperties;

import java.util.List;
import java.util.Map;

public class BuilderSlotDetails {
    private List<ItemStack> slotMaterial;
    private String slotDescription;
    private int slotMaterialMinCount;
    private int slotMaterialMaxCount;
    private int slotMaterialWeight;
    private int slotMaterialTimeToAdd;

    public BuilderSlotDetails(List<ItemStack> slotMaterial, String slotDescription, int slotMaterialMinCount, int slotMaterialMaxCount, int slotMaterialWeight, int slotMaterialTimeToAdd) {
        this.slotMaterial = slotMaterial;
        this.slotDescription = slotDescription;
        this.slotMaterialMinCount = slotMaterialMinCount;
        this.slotMaterialMaxCount = slotMaterialMaxCount;
        this.slotMaterialWeight = slotMaterialWeight;
        this.slotMaterialTimeToAdd = slotMaterialTimeToAdd;
    }

    public List<ItemStack> getSlotMaterials() {
        return slotMaterial;
    }

    public String getSlotDescription() {
        return slotDescription;
    }

    public int getSlotMaterialMinCount() {
        return slotMaterialMinCount;
    }

    public int getSlotMaterialMaxCount() {
        return slotMaterialMaxCount;
    }

    public int getSlotMaterialWeight() {
        return slotMaterialWeight;
    }

    public int getSlotMaterialTimeToAdd() {
        return slotMaterialTimeToAdd;
    }
}
