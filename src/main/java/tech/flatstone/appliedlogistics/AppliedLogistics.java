package tech.flatstone.appliedlogistics;

import com.google.common.base.Stopwatch;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import tech.flatstone.appliedlogistics.api.features.EnumOreType;
import tech.flatstone.appliedlogistics.api.registries.HammerRegistry;
import tech.flatstone.appliedlogistics.common.blocks.Blocks;
import tech.flatstone.appliedlogistics.common.integrations.IntegrationsManager;
import tech.flatstone.appliedlogistics.common.items.Items;
import tech.flatstone.appliedlogistics.common.util.EnumOres;
import tech.flatstone.appliedlogistics.common.world.WorldGen;
import tech.flatstone.appliedlogistics.proxy.IProxy;

@Mod(modid = ModInfo.MOD_ID, name = ModInfo.MOD_NAME, certificateFingerprint = ModInfo.FINGERPRINT, dependencies = ModInfo.DEPENDENCIES, version = ModInfo.VERSION_BUILD)
public class AppliedLogistics {
    @Mod.Instance(ModInfo.MOD_ID)
    public static AppliedLogistics instance;

    @SidedProxy(clientSide = ModInfo.CLIENT_PROXY_CLASS, serverSide = ModInfo.SERVER_PROXY_CLASS)
    public static IProxy proxy;

    public static void addConfiguredWorldGen(IBlockState state, String config) {
        WorldGen.addOreGen(config, state, 20, 0, 256, 100, 100);
    }

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        final Stopwatch stopwatch = Stopwatch.createStarted();
        // Register Blocks
        proxy.registerBlocks();
        proxy.registerItems();

        proxy.registerFurnaceRecipes();
        proxy.registerRecipes();

        proxy.registerOreDict();

        proxy.registerEvents();

        IntegrationsManager.instance().index();

        for (int i = 0; i < EnumOres.values().length; i++) {
            if (EnumOres.byMeta(i).isTypeSet(EnumOreType.ORE)) {
                addConfiguredWorldGen(Blocks.BLOCK_ORE.block.getStateFromMeta(i), EnumOres.byMeta(i).getUnlocalizedName());
            }
        }

        IntegrationsManager.instance().preInit();

        HammerRegistry.register(Blocks.BLOCK_ORE.block.getStateFromMeta(EnumOres.COPPER.getMeta()), new ItemStack(Items.ITEM_ORE_DUST.item, 1, EnumOres.COPPER.getMeta()), 30, 30);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {

        WorldGen worldGen = new WorldGen();
        GameRegistry.registerWorldGenerator(worldGen, 0);
        MinecraftForge.EVENT_BUS.register(worldGen);

        // Init Integrations
        IntegrationsManager.instance().init();
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        IntegrationsManager.instance().postInit();
    }
}
