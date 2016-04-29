package tech.flatstone.appliedlogistics.common.world;

import net.minecraft.block.state.IBlockState;
import net.minecraftforge.fml.relauncher.ReflectionHelper;
import org.apache.commons.lang3.text.WordUtils;
import tech.flatstone.appliedlogistics.api.features.EnumOreType;
import tech.flatstone.appliedlogistics.common.blocks.Blocks;
import tech.flatstone.appliedlogistics.common.config.ConfigWorldGen;
import tech.flatstone.appliedlogistics.common.util.EnumOres;
import tech.flatstone.appliedlogistics.common.util.LogHelper;

public class WorldGenInit {
    private static void addConfiguredWorldGen(IBlockState state, String blockName) {
        String blockConfigName = WordUtils.capitalize(blockName);

        try {
            boolean enabled = ReflectionHelper.findField(ConfigWorldGen.class, String.format("worldGen_Generate_%s", blockConfigName)).getBoolean(ConfigWorldGen.class);
            int minY = ReflectionHelper.findField(ConfigWorldGen.class, String.format("worldGen_Generate_%s_minY", blockConfigName)).getInt(ConfigWorldGen.class);
            int maxY = ReflectionHelper.findField(ConfigWorldGen.class, String.format("worldGen_Generate_%s_maxY", blockConfigName)).getInt(ConfigWorldGen.class);
            ;
            int veinSize = ReflectionHelper.findField(ConfigWorldGen.class, String.format("worldGen_Generate_%s_veinSize", blockConfigName)).getInt(ConfigWorldGen.class);
            ;
            int weight = ReflectionHelper.findField(ConfigWorldGen.class, String.format("worldGen_Generate_%s_weight", blockConfigName)).getInt(ConfigWorldGen.class);
            ;
            int chunkOccurrence = ReflectionHelper.findField(ConfigWorldGen.class, String.format("worldGen_Generate_%s_chunkOccurrence", blockConfigName)).getInt(ConfigWorldGen.class);
            ;
            String dimensionRestriction = (String) ReflectionHelper.findField(ConfigWorldGen.class, String.format("worldGen_Generate_%s_dimensionRestriction", blockConfigName)).get(ConfigWorldGen.class);
            ;
            int[] dimensions = (int[]) ReflectionHelper.findField(ConfigWorldGen.class, String.format("worldGen_Generate_%s_dimensions", blockConfigName)).get(ConfigWorldGen.class);
            ;

            if (enabled)
                WorldGen.addOreGen(blockName, state, veinSize, minY, maxY, chunkOccurrence, weight);

        } catch (IllegalAccessException ex) {
            LogHelper.fatal("There was a fatal exception with setting up worldGen");
            ex.printStackTrace();
        } catch (ClassCastException ex) {
            LogHelper.fatal("Failed to get proper config type, erase your config and relaunch minecraft");
            ex.printStackTrace();
        }
    }

    public static void init() {
        for (int i = 0; i < EnumOres.values().length; i++) {
            if (EnumOres.byMeta(i).isTypeSet(EnumOreType.ORE)) {
                addConfiguredWorldGen(Blocks.BLOCK_ORE.getBlock().getStateFromMeta(i), EnumOres.byMeta(i).getUnlocalizedName());
            }
        }
    }
}
