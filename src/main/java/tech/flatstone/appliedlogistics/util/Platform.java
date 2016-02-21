package tech.flatstone.appliedlogistics.util;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.FMLCommonHandler;

import javax.annotation.Nullable;

public class Platform {
    /**
     * Check if the code is running on the server instance
     *
     * @return True if running on the server
     */
    public static boolean isServer() {
        return FMLCommonHandler.instance().getEffectiveSide().isServer();
    }

    /**
     * Check if the code is running on the client instance
     *
     * @return True if running on the client
     */
    public static boolean isClient() {
        return FMLCommonHandler.instance().getEffectiveSide().isClient();
    }

    /**
     * Compare two ItemStacks to see if they are the same
     *
     * @param itemStack1 ItemStack 1 to compare
     * @param itemStack2 ItemStack 2 to compare
     * @return True is they are the same
     */
    public static boolean isSameItem(@Nullable ItemStack itemStack1, @Nullable ItemStack itemStack2) {
        return itemStack1 != null && itemStack2 != null && itemStack1.isItemEqual(itemStack2);
    }
}
