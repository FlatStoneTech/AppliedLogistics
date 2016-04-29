package tech.flatstone.appliedlogistics.common.world;

import tech.flatstone.appliedlogistics.api.features.EnumOreType;
import tech.flatstone.appliedlogistics.common.blocks.Blocks;
import tech.flatstone.appliedlogistics.common.config.ConfigWorldGen;
import tech.flatstone.appliedlogistics.common.util.EnumOres;

public class WorldGenInit {
    public static void init() {
        for (EnumOres ore : EnumOres.byType(EnumOreType.ORE)) {
            // Always add ores, in case they get enabled at runtime
            ConfigWorldGen.OreConfig config = ConfigWorldGen.OreWorldGen.get(ore);
            WorldGen.addOreGen(ore.getName(), Blocks.BLOCK_ORE.getBlock().getStateFromMeta(ore.getMeta()), config);
        }
    }
}
