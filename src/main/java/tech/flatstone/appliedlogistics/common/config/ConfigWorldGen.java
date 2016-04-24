package tech.flatstone.appliedlogistics.common.config;

import net.minecraftforge.common.config.Configuration;
import tech.flatstone.appliedlogistics.common.util.ConfigHelper;

public class ConfigWorldGen {
    public static boolean worldGen_Generate_Copper;
    public static boolean worldGen_Generate_Tin;
    public static boolean worldGen_Generate_Silver;
    public static boolean worldGen_Generate_Lead;
    public static boolean worldGen_Generate_Nickel;
    public static boolean worldGen_Generate_Rutile;
    
    public static int worldGen_Generate_Copper_minY;
    public static int worldGen_Generate_Copper_maxY;
    public static int worldGen_Generate_Copper_veinSize;
    public static int worldGen_Generate_Copper_weight;
    public static int worldGen_Generate_Copper_chunkOccurrence;
    public static String worldGen_Generate_Copper_dimensionRestriction;
    public static int[] worldGen_Generate_Copper_dimensions;

    public static int worldGen_Generate_Tin_minY;
    public static int worldGen_Generate_Tin_maxY;
    public static int worldGen_Generate_Tin_veinSize;
    public static int worldGen_Generate_Tin_weight;
    public static int worldGen_Generate_Tin_chunkOccurrence;
    public static String worldGen_Generate_Tin_dimensionRestriction;
    public static int[] worldGen_Generate_Tin_dimensions;

    public static int worldGen_Generate_Silver_minY;
    public static int worldGen_Generate_Silver_maxY;
    public static int worldGen_Generate_Silver_veinSize;
    public static int worldGen_Generate_Silver_weight;
    public static int worldGen_Generate_Silver_chunkOccurrence;
    public static String worldGen_Generate_Silver_dimensionRestriction;
    public static int[] worldGen_Generate_Silver_dimensions;

    public static int worldGen_Generate_Lead_minY;
    public static int worldGen_Generate_Lead_maxY;
    public static int worldGen_Generate_Lead_veinSize;
    public static int worldGen_Generate_Lead_weight;
    public static int worldGen_Generate_Lead_chunkOccurrence;
    public static String worldGen_Generate_Lead_dimensionRestriction;
    public static int[] worldGen_Generate_Lead_dimensions;

    public static int worldGen_Generate_Nickel_minY;
    public static int worldGen_Generate_Nickel_maxY;
    public static int worldGen_Generate_Nickel_veinSize;
    public static int worldGen_Generate_Nickel_weight;
    public static int worldGen_Generate_Nickel_chunkOccurrence;
    public static String worldGen_Generate_Nickel_dimensionRestriction;
    public static int[] worldGen_Generate_Nickel_dimensions;

    public static int worldGen_Generate_Rutile_minY;
    public static int worldGen_Generate_Rutile_maxY;
    public static int worldGen_Generate_Rutile_veinSize;
    public static int worldGen_Generate_Rutile_weight;
    public static int worldGen_Generate_Rutile_chunkOccurrence;
    public static String worldGen_Generate_Rutile_dimensionRestriction;
    public static int[] worldGen_Generate_Rutile_dimensions;

    public static void init(Configuration configuration) {
        configuration.setCategoryLanguageKey(Config.CONFIG_WORLDGEN, "config.worldGen");
        configuration.setCategoryRequiresWorldRestart(Config.CONFIG_WORLDGEN, true);

        int[] dimBlacklist = {-1, 1};
        
        final String WORLDGEN_ORES = String.format("%s.%s", Config.CONFIG_WORLDGEN, "ores");
        final String WORLDGEN_ORES_COPPER = String.format("%s.%s", WORLDGEN_ORES, "Copper");
        final String WORLDGEN_ORES_TIN = String.format("%s.%s", WORLDGEN_ORES, "Tin");
        final String WORLDGEN_ORES_SILVER = String.format("%s.%s", WORLDGEN_ORES, "Silver");
        final String WORLDGEN_ORES_LEAD = String.format("%s.%s", WORLDGEN_ORES, "Lead");
        final String WORLDGEN_ORES_NICKEL = String.format("%s.%s", WORLDGEN_ORES, "Nickel");
        final String WORLDGEN_ORES_RUTILE = String.format("%s.%s", WORLDGEN_ORES, "Rutile");
        
        worldGen_Generate_Copper = ConfigHelper.getBoolean(configuration, "Copper", WORLDGEN_ORES, true, "Enable copper in world generation");
        worldGen_Generate_Tin = ConfigHelper.getBoolean(configuration, "Tin", WORLDGEN_ORES, true, "Enable tin in world generation");
        worldGen_Generate_Silver = ConfigHelper.getBoolean(configuration, "Silver", WORLDGEN_ORES, true, "Enable silver in world generation");
        worldGen_Generate_Lead = ConfigHelper.getBoolean(configuration, "Lead", WORLDGEN_ORES, true, "Enable lead in world generation");
        worldGen_Generate_Nickel = ConfigHelper.getBoolean(configuration, "Nickel", WORLDGEN_ORES, true, "Enable nickel in world generation");
        worldGen_Generate_Rutile = ConfigHelper.getBoolean(configuration, "Rutile", WORLDGEN_ORES, true, "Enable rutile in world generation");

        worldGen_Generate_Copper_minY = ConfigHelper.getInteger(configuration, "Min Height", WORLDGEN_ORES_COPPER, 10, "Min Y level copper will spawn at");
        worldGen_Generate_Copper_maxY = ConfigHelper.getInteger(configuration, "Max Height", WORLDGEN_ORES_COPPER, 75, "Max Y level copper will spawn at");
        worldGen_Generate_Copper_veinSize = ConfigHelper.getInteger(configuration, "Vein Size", WORLDGEN_ORES_COPPER, 16, "Size of vein");
        worldGen_Generate_Copper_weight = ConfigHelper.getInteger(configuration, "Weight", WORLDGEN_ORES_COPPER, 40, "Percent chance that copper will generate in chunk");
        worldGen_Generate_Copper_chunkOccurrence = ConfigHelper.getInteger(configuration, "Chunk Occurrence", WORLDGEN_ORES_COPPER, 20, "Number of times copper will attempt to generate in a chunk");
        worldGen_Generate_Copper_dimensionRestriction = ConfigHelper.getString(configuration, "Dimension Restriction", WORLDGEN_ORES_COPPER, "blacklist", "Either 'blacklist' or 'whitelist'");
        worldGen_Generate_Copper_dimensions = ConfigHelper.getIntegerList(configuration, "Dimensions", WORLDGEN_ORES_COPPER, dimBlacklist, "Dimension Numbers");

        worldGen_Generate_Tin_minY = ConfigHelper.getInteger(configuration, "Min Height", WORLDGEN_ORES_TIN, 20, "Min Y level tin will spawn at");
        worldGen_Generate_Tin_maxY = ConfigHelper.getInteger(configuration, "Max Height", WORLDGEN_ORES_TIN, 55, "Max Y level tin will spawn at");
        worldGen_Generate_Tin_veinSize = ConfigHelper.getInteger(configuration, "Vein Size", WORLDGEN_ORES_TIN, 10, "Size of vein");
        worldGen_Generate_Tin_weight = ConfigHelper.getInteger(configuration, "Weight", WORLDGEN_ORES_TIN, 40, "Percent chance that tin will generate in chunk");
        worldGen_Generate_Tin_chunkOccurrence = ConfigHelper.getInteger(configuration, "Chunk Occurrence", WORLDGEN_ORES_TIN, 20, "Number of times tin will attempt to generate in a chunk");
        worldGen_Generate_Tin_dimensionRestriction = ConfigHelper.getString(configuration, "Dimension Restriction", WORLDGEN_ORES_TIN, "blacklist", "Either 'blacklist' or 'whitelist'");
        worldGen_Generate_Tin_dimensions = ConfigHelper.getIntegerList(configuration, "Dimensions", WORLDGEN_ORES_TIN, dimBlacklist, "Dimension Numbers");

        worldGen_Generate_Silver_minY = ConfigHelper.getInteger(configuration, "Min Height", WORLDGEN_ORES_SILVER, 5, "Min Y level silver will spawn at");
        worldGen_Generate_Silver_maxY = ConfigHelper.getInteger(configuration, "Max Height", WORLDGEN_ORES_SILVER, 30, "Max Y level silver will spawn at");
        worldGen_Generate_Silver_veinSize = ConfigHelper.getInteger(configuration, "Vein Size", WORLDGEN_ORES_SILVER, 8, "Size of vein");
        worldGen_Generate_Silver_weight = ConfigHelper.getInteger(configuration, "Weight", WORLDGEN_ORES_SILVER, 20, "Percent chance that silver will generate in chunk");
        worldGen_Generate_Silver_chunkOccurrence = ConfigHelper.getInteger(configuration, "Chunk Occurrence", WORLDGEN_ORES_SILVER, 20, "Number of times silver will attempt to generate in a chunk");
        worldGen_Generate_Silver_dimensionRestriction = ConfigHelper.getString(configuration, "Dimension Restriction", WORLDGEN_ORES_SILVER, "blacklist", "Either 'blacklist' or 'whitelist'");
        worldGen_Generate_Silver_dimensions = ConfigHelper.getIntegerList(configuration, "Dimensions", WORLDGEN_ORES_SILVER, dimBlacklist, "Dimension Numbers");

        worldGen_Generate_Lead_minY = ConfigHelper.getInteger(configuration, "Min Height", WORLDGEN_ORES_LEAD, 10, "Min Y level lead will spawn at");
        worldGen_Generate_Lead_maxY = ConfigHelper.getInteger(configuration, "Max Height", WORLDGEN_ORES_LEAD, 35, "Max Y level lead will spawn at");
        worldGen_Generate_Lead_veinSize = ConfigHelper.getInteger(configuration, "Vein Size", WORLDGEN_ORES_LEAD, 8, "Size of vein");
        worldGen_Generate_Lead_weight = ConfigHelper.getInteger(configuration, "Weight", WORLDGEN_ORES_LEAD, 20, "Percent chance that lead will generate in chunk");
        worldGen_Generate_Lead_chunkOccurrence = ConfigHelper.getInteger(configuration, "Chunk Occurrence", WORLDGEN_ORES_LEAD, 20, "Number of times lead will attempt to generate in a chunk");
        worldGen_Generate_Lead_dimensionRestriction = ConfigHelper.getString(configuration, "Dimension Restriction", WORLDGEN_ORES_LEAD, "blacklist", "Either 'blacklist' or 'whitelist'");
        worldGen_Generate_Lead_dimensions = ConfigHelper.getIntegerList(configuration, "Dimensions", WORLDGEN_ORES_LEAD, dimBlacklist, "Dimension Numbers");

        worldGen_Generate_Nickel_minY = ConfigHelper.getInteger(configuration, "Min Height", WORLDGEN_ORES_NICKEL, 5, "Min Y level nickel will spawn at");
        worldGen_Generate_Nickel_maxY = ConfigHelper.getInteger(configuration, "Max Height", WORLDGEN_ORES_NICKEL, 60, "Max Y level nickel will spawn at");
        worldGen_Generate_Nickel_veinSize = ConfigHelper.getInteger(configuration, "Vein Size", WORLDGEN_ORES_NICKEL, 4, "Size of vein");
        worldGen_Generate_Nickel_weight = ConfigHelper.getInteger(configuration, "Weight", WORLDGEN_ORES_NICKEL, 20, "Percent chance that nickel will generate in chunk");
        worldGen_Generate_Nickel_chunkOccurrence = ConfigHelper.getInteger(configuration, "Chunk Occurrence", WORLDGEN_ORES_NICKEL, 20, "Number of times nickel will attempt to generate in a chunk");
        worldGen_Generate_Nickel_dimensionRestriction = ConfigHelper.getString(configuration, "Dimension Restriction", WORLDGEN_ORES_NICKEL, "blacklist", "Either 'blacklist' or 'whitelist'");
        worldGen_Generate_Nickel_dimensions = ConfigHelper.getIntegerList(configuration, "Dimensions", WORLDGEN_ORES_NICKEL, dimBlacklist, "Dimension Numbers");

        worldGen_Generate_Rutile_minY = ConfigHelper.getInteger(configuration, "Min Height", WORLDGEN_ORES_RUTILE, 1, "Min Y level rutile will spawn at");
        worldGen_Generate_Rutile_maxY = ConfigHelper.getInteger(configuration, "Max Height", WORLDGEN_ORES_RUTILE, 75, "Max Y level rutile will spawn at");
        worldGen_Generate_Rutile_veinSize = ConfigHelper.getInteger(configuration, "Vein Size", WORLDGEN_ORES_RUTILE, 20, "Size of vein");
        worldGen_Generate_Rutile_weight = ConfigHelper.getInteger(configuration, "Weight", WORLDGEN_ORES_RUTILE, 30, "Percent chance that rutile will generate in chunk");
        worldGen_Generate_Rutile_chunkOccurrence = ConfigHelper.getInteger(configuration, "Chunk Occurrence", WORLDGEN_ORES_RUTILE, 20, "Number of times rutile will attempt to generate in a chunk");
        worldGen_Generate_Rutile_dimensionRestriction = ConfigHelper.getString(configuration, "Dimension Restriction", WORLDGEN_ORES_RUTILE, "blacklist", "Either 'blacklist' or 'whitelist'");
        worldGen_Generate_Rutile_dimensions = ConfigHelper.getIntegerList(configuration, "Dimensions", WORLDGEN_ORES_RUTILE, dimBlacklist, "Dimension Numbers");
    }
}
