package tech.flatstone.appliedlogistics.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import net.minecraftforge.fml.common.registry.GameRegistry;
import tech.flatstone.appliedlogistics.reference.ModInfo;

public enum Items {
    ;

    private static boolean registered = false;
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

    public static void registerAll() {
        if (registered)
            return;

        for (Items i : Items.values())
            i.register();

        registered = true;
    }

    public void register() {
        GameRegistry.registerItem(item, internalName);
    }

    public String getStatName() {
        return StatCollector.translateToLocal(item.getUnlocalizedName());
    }

    public ItemStack getStack(int damage, int size) {
        return new ItemStack(item, size, damage);
    }
}
