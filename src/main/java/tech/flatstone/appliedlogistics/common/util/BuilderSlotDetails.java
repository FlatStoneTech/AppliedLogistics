package tech.flatstone.appliedlogistics.common.util;

import net.minecraft.item.ItemStack;

import java.util.List;

public class BuilderSlotDetails {
    private List<ItemStack> slotMaterial;
    private String slotDescription;
    private int slotMaterialMinCount;
    private int slotMaterialMaxCount;
    private int slotMaterialWeight;
    private int slotMaterialTimeToAdd;

    private int slotMaterialCount = 0;
    private boolean slotIncorrectTechLevel;

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

    public int getSlotMaterialCount() {
        return slotMaterialCount;
    }

    public void setSlotMaterialCount(int slotMaterialCount) {
        this.slotMaterialCount = slotMaterialCount;
    }

    public boolean isSlotIncorrectTechLevel() {
        return slotIncorrectTechLevel;
    }

    public void setSlotIncorrectTechLevel(boolean slotIncorrectTechLevel) {
        this.slotIncorrectTechLevel = slotIncorrectTechLevel;
    }
}
