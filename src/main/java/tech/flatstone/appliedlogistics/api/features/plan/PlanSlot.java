package tech.flatstone.appliedlogistics.api.features.plan;

import net.minecraft.item.ItemStack;
import tech.flatstone.appliedlogistics.api.features.TechLevel;

import java.util.List;
import java.util.Map;

public class PlanSlot {
    private List<ItemStack> slotMaterial;
    private String slotDescription;
    private Map<TechLevel, SlotTechLevelProperties> slotProperties;
    private int slotMaterialWeight;
    private int slotMaterialTimeToAdd;

    public PlanSlot(List<ItemStack> slotMaterial, String slotDescription, Map<TechLevel, SlotTechLevelProperties> slotProperties, int slotMaterialWeight, int slotMaterialTimeToAdd) {
        this.slotMaterial = slotMaterial;
        this.slotDescription = slotDescription;
        this.slotProperties = slotProperties;
        this.slotMaterialWeight = slotMaterialWeight;
        this.slotMaterialTimeToAdd = slotMaterialTimeToAdd;
    }

    public List<ItemStack> getSlotMaterial() {
        return slotMaterial;
    }

    public String getSlotDescription() {
        return slotDescription;
    }

    public Map<TechLevel, SlotTechLevelProperties> getSlotProperties() {
        return slotProperties;
    }

    public int getSlotMaterialWeight() {
        return slotMaterialWeight;
    }

    public int getSlotMaterialTimeToAdd() {
        return slotMaterialTimeToAdd;
    }
}
