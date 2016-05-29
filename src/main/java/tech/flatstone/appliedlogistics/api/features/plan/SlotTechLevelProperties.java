package tech.flatstone.appliedlogistics.api.features.plan;

public class SlotTechLevelProperties {
    private int itemMinCount;
    private int itemMaxCount;

    public SlotTechLevelProperties(int itemMinCount, int itemMaxCount) {
        this.itemMinCount = itemMinCount;
        this.itemMaxCount = itemMaxCount;
    }

    public int getItemMinCount() {
        return itemMinCount;
    }

    public int getItemMaxCount() {
        return itemMaxCount;
    }
}
