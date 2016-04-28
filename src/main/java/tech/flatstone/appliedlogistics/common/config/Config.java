package tech.flatstone.appliedlogistics.common.config;

import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.config.GuiConfig;
import net.minecraftforge.fml.client.config.IConfigElement;
import tech.flatstone.appliedlogistics.AppliedLogistics;
import tech.flatstone.appliedlogistics.ModInfo;

import java.io.File;
import java.util.Arrays;

public class Config extends GuiConfig {
    public static final String CONFIG_WORLDGEN = "worldgen";

    public Config(GuiScreen parentScreen) {
        super(
            parentScreen,
            Arrays.asList(new IConfigElement[]{
                new ConfigElement(AppliedLogistics.configuration.getCategory(CONFIG_WORLDGEN)),
            }),
            ModInfo.MOD_ID, false, false, "Applied Logistics Configuration");
        titleLine2 = AppliedLogistics.configuration.getConfigFile().getAbsolutePath();
    }

    public static Configuration configuration;

    public static Configuration initConfig(File configFile) {
        if (configuration == null) {
            configuration = new Configuration(configFile);
            loadConfiguration();
        }
        return configuration;
    }

    public static void loadConfiguration() {
        ConfigWorldGen.init(configuration);

        configuration.save();
    }
}
