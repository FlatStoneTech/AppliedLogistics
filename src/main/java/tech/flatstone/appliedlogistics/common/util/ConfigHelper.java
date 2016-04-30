package tech.flatstone.appliedlogistics.common.util;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

public class ConfigHelper {
    public static boolean getBoolean(Configuration configuration, String key, String category, boolean defaultValue, String description) {
        Property property = configuration.get(category, key, defaultValue, description);
        return property.getBoolean(defaultValue);
    }

    public static int getInteger(Configuration configuration, String key, String category, int defaultValue, String description) {
        Property property = configuration.get(category, key, defaultValue, description);
        return property.getInt(defaultValue);
    }

    public static int[] getIntegerList(Configuration configuration, String key, String category, int[] defaultValue, String description) {
        Property property = configuration.get(category, key, defaultValue, description);
        return property.getIntList();
    }

    public static String getString(Configuration configuration, String key, String category, String defaultValue, String description) {
        Property property = configuration.get(category, key, defaultValue, description);
        return property.getString();
    }
}
