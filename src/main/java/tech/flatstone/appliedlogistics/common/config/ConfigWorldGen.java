package tech.flatstone.appliedlogistics.common.config;

import net.minecraftforge.common.config.Configuration;
import tech.flatstone.appliedlogistics.api.features.EnumOreType;
import tech.flatstone.appliedlogistics.common.util.ConfigHelper;
import tech.flatstone.appliedlogistics.common.util.EnumOres;

import java.util.HashMap;
import java.util.Map;

public class ConfigWorldGen {
    public enum RestrictionType {
        Blacklist,
        Whitelist
        ;

        public static RestrictionType fromString(String str, RestrictionType def) {
            for (RestrictionType rt : values()) {
                if (rt.name().equalsIgnoreCase(str)) {
                    return rt;
                }
            }

            return def;
        }
    }

    public static class OreConfig {
        public boolean Enabled;
        public int MinY;
        public int MaxY;
        public int VeinSize;
        public int Weight;
        public int ChunkOccurrence;
        public RestrictionType DimensionRestriction;
        public int[] Dimensions;

        public boolean isEnabledForDim(int dim) {
            boolean inList = false;
            for (int listDim : Dimensions)
                inList = inList || listDim == dim;
            return (inList && DimensionRestriction == RestrictionType.Whitelist) ||
                   (!inList && DimensionRestriction == RestrictionType.Blacklist);
        }
    }

    public static final Map<EnumOres, OreConfig> OreWorldGen = new HashMap<>(EnumOres.values().length);

    private static final Map<EnumOres, OreConfig> OreWorldGenDefaults = new HashMap<>(EnumOres.values().length);

    private static final int[] DEFAULT_DIMENSION_BLACKLIST = {-1, 1};

    static {
        // Initialize defaults
        for (EnumOres ore : EnumOres.byType(EnumOreType.ORE)) {
            OreConfig defaultConfig = new OreConfig();
            defaultConfig.Enabled = true;
            defaultConfig.DimensionRestriction = RestrictionType.Blacklist;
            defaultConfig.Dimensions = DEFAULT_DIMENSION_BLACKLIST;

            // Might want to vary this per-ore at some point, but so far it's constant
            defaultConfig.ChunkOccurrence = 20;

            switch (ore) {
                case COPPER:
                    defaultConfig.MinY = 10;
                    defaultConfig.MaxY = 75;
                    defaultConfig.VeinSize = 16;
                    defaultConfig.Weight = 40;
                    break;

                case TIN:
                    defaultConfig.MinY = 20;
                    defaultConfig.MaxY = 55;
                    defaultConfig.VeinSize = 10;
                    defaultConfig.Weight = 40;
                    break;

                case SILVER:
                    defaultConfig.MinY = 5;
                    defaultConfig.MaxY = 30;
                    defaultConfig.VeinSize = 8;
                    defaultConfig.Weight = 20;
                    break;

                case LEAD:
                    defaultConfig.MinY = 10;
                    defaultConfig.MaxY = 35;
                    defaultConfig.VeinSize = 8;
                    defaultConfig.Weight = 20;
                    break;

                case NICKEL:
                    defaultConfig.MinY = 5;
                    defaultConfig.MaxY = 60;
                    defaultConfig.VeinSize = 4;
                    defaultConfig.Weight = 20;
                    break;

                case RUTILE:
                    defaultConfig.MinY = 1;
                    defaultConfig.MaxY = 75;
                    defaultConfig.VeinSize = 20;
                    defaultConfig.Weight = 30;
                    break;
            }

            OreWorldGenDefaults.put(ore, defaultConfig);
        }
    }

    public static void init(Configuration configuration) {
        configuration.setCategoryLanguageKey(Config.CONFIG_WORLDGEN, "config.worldGen");
        configuration.setCategoryRequiresWorldRestart(Config.CONFIG_WORLDGEN, true); //TODO: This could theoretically be false now, if desired

        final String WORLDGEN_ORES = String.format("%s.%s", Config.CONFIG_WORLDGEN, "ores");

        final String DESC_ENABLED = "Enable %s in world generation";
        final String DESC_MIN_Y = "Minimum Y-level that %s will spawn at";
        final String DESC_MAX_Y = "Maximum Y-level that %s will spawn at";
        final String DESC_VEIN_SIZE = "Size of %s veins";
        final String DESC_WEIGHT = "Percent chance that %s will generate for each chunk occurrence";
        final String DESC_CHUNK_OCCURRENCE = "Number of times that %s will attempt to generate in each chunk";
        final String DESC_DIM_RESTRICTION = "Either 'blacklist' or 'whitelist'";
        final String DESC_DIM_LIST = "Dimension numbers for restriction";

        for (EnumOres ore : EnumOres.byType(EnumOreType.ORE)) {
            String oreName = ore.getName();
            String oreLower = oreName.toLowerCase();
            String category = String.format("%s.%s", WORLDGEN_ORES, oreName);
            OreConfig defaultConfig = OreWorldGenDefaults.get(ore);

            OreConfig oreConfig;
            if (OreWorldGen.containsKey(ore)) {
                // Manipulating the existing config will automatically update WorldGen
                oreConfig = OreWorldGen.get(ore);
            }
            else {
                oreConfig = new OreConfig();
            }

            oreConfig.Enabled = ConfigHelper.getBoolean(configuration, oreName, WORLDGEN_ORES, defaultConfig.Enabled, String.format(DESC_ENABLED, oreLower));

            oreConfig.MinY = ConfigHelper.getInteger(configuration, "Min Height", category, defaultConfig.MinY, String.format(DESC_MIN_Y, oreLower));
            oreConfig.MaxY = ConfigHelper.getInteger(configuration, "Max Height", category, defaultConfig.MaxY, String.format(DESC_MAX_Y, oreLower));
            oreConfig.VeinSize = ConfigHelper.getInteger(configuration, "Vein Size", category, defaultConfig.VeinSize, String.format(DESC_VEIN_SIZE, oreLower));
            oreConfig.Weight = ConfigHelper.getInteger(configuration, "Weight", category, defaultConfig.Weight, String.format(DESC_WEIGHT, oreLower));
            oreConfig.ChunkOccurrence = ConfigHelper.getInteger(configuration, "Chunk Occurrence", category, defaultConfig.ChunkOccurrence, String.format(DESC_CHUNK_OCCURRENCE, oreLower));

            String defaultDimRestriction = defaultConfig.DimensionRestriction.name().toLowerCase();
            String dimRestriction = ConfigHelper.getString(configuration, "Dimension Restriction", category, defaultDimRestriction, DESC_DIM_RESTRICTION);
            oreConfig.DimensionRestriction = RestrictionType.fromString(dimRestriction, defaultConfig.DimensionRestriction);

            oreConfig.Dimensions = ConfigHelper.getIntegerList(configuration, "Dimensions", category, defaultConfig.Dimensions, DESC_DIM_LIST);

            OreWorldGen.put(ore, oreConfig);
        }
    }
}
