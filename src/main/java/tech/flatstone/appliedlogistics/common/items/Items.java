package tech.flatstone.appliedlogistics.common.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import net.minecraftforge.fml.common.registry.GameRegistry;
import tech.flatstone.appliedlogistics.ModInfo;
import tech.flatstone.appliedlogistics.common.AppliedLogisticsCreativeTabs;
import tech.flatstone.appliedlogistics.common.items.ore.ItemOreDust;
import tech.flatstone.appliedlogistics.common.items.ore.ItemOreIngot;
import tech.flatstone.appliedlogistics.common.items.ore.ItemOreNugget;
import tech.flatstone.appliedlogistics.common.util.IItemRenderer;

public enum Items {
    ITEM_ORE_INGOT("oreIngot", new ItemOreIngot(), AppliedLogisticsCreativeTabs.tabGeneral),
    ITEM_ORE_DUST("oreDust", new ItemOreDust(), AppliedLogisticsCreativeTabs.tabGeneral),
    ITEM_ORE_NUGGET("oreNugget", new ItemOreNugget(), AppliedLogisticsCreativeTabs.tabGeneral),
    ;

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
        GameRegistry.registerItem(item, internalName);

        if (item instanceof IItemRenderer) {
            ((IItemRenderer) item).registerItemRenderer();
        }
    }

    public String getStatName() {
        return StatCollector.translateToLocal(item.getUnlocalizedName());
    }

    public ItemStack getStack(int damage, int size) {
        return new ItemStack(item, size, damage);
    }
}
