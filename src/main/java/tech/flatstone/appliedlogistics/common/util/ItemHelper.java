package tech.flatstone.appliedlogistics.common.util;

import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameData;
import tech.flatstone.appliedlogistics.ModInfo;

public class ItemHelper {
    public static ResourceLocation getItemLocation(Item item) {
        Object o = GameData.getItemRegistry().getNameForObject(item);

        if(o == null) {
            LogHelper.error("Item %s is not registered!" + item.getUnlocalizedName());
            return null;
        }

        return (ResourceLocation) o;
    }

    public static ResourceLocation getResource(String res) {
        return new ResourceLocation(ModInfo.MOD_ID, res);
    }
}
