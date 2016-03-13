package tech.flatstone.appliedlogistics.common.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import net.minecraftforge.fml.common.registry.GameRegistry;
import tech.flatstone.appliedlogistics.ModInfo;
import tech.flatstone.appliedlogistics.common.AppliedLogisticsCreativeTabs;
import tech.flatstone.appliedlogistics.common.items.material.ItemGear;
import tech.flatstone.appliedlogistics.common.items.ore.ItemOreDust;
import tech.flatstone.appliedlogistics.common.items.ore.ItemOreIngot;
import tech.flatstone.appliedlogistics.common.items.ore.ItemOreNugget;
import tech.flatstone.appliedlogistics.common.items.tools.ItemHammer;
import tech.flatstone.appliedlogistics.common.util.IItemRenderer;
import tech.flatstone.appliedlogistics.common.util.Platform;

import java.util.Locale;

public enum Items {
    ITEM_ORE_INGOT("ore_ingot", new ItemOreIngot(), AppliedLogisticsCreativeTabs.tabOres),
    ITEM_ORE_DUST("ore_dust", new ItemOreDust(), AppliedLogisticsCreativeTabs.tabOres),
    ITEM_ORE_NUGGET("ore_nugget", new ItemOreNugget(), AppliedLogisticsCreativeTabs.tabOres),

    ITEM_MATERIAL_GEAR("material_gear", new ItemGear(), AppliedLogisticsCreativeTabs.tabGeneral),

    ITEM_TOOL_HAMMER("tool_hammer", new ItemHammer(), AppliedLogisticsCreativeTabs.tabGeneral),

    ITEM_PLAN("plan", new ItemPlanBase(), AppliedLogisticsCreativeTabs.tabGeneral),;

    public final Item item;
    private final String internalName;

    Items(String internalName, Item item) {
        this(internalName, item, null);
    }

    Items(String internalName, Item item, CreativeTabs creativeTabs) {
        this.internalName = internalName;
        this.item = item;
        item.setUnlocalizedName(ModInfo.MOD_ID + ":" + internalName);
        item.setCreativeTab(creativeTabs);
    }

    public static void registerItems() {
        for (Items i : Items.values()) {
            i.register();
        }
    }

    public void register() {
        if(!internalName.equals(internalName.toLowerCase(Locale.US))) {
            throw new IllegalArgumentException(String.format("Unlocalized names need to be all lowercase! Item: %s", internalName));
        }

        GameRegistry.registerItem(item, internalName);

        if (item instanceof IItemRenderer && Platform.isClient()) {
            ((IItemRenderer) item).registerItemRenderer();
        }
    }

    public String getStatName() {
        return StatCollector.translateToLocal(item.getUnlocalizedName());
    }

    public ItemStack getStack(int damage, int size) {
        return new ItemStack(item, size, damage);
    }

    public String getInternalName() {
        return this.internalName;
    }

    public Item getItem() {
        return this.item;
    }
}
