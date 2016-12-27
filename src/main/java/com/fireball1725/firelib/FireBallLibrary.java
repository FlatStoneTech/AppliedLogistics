package com.fireball1725.firelib;

import com.fireball1725.firelib.proxy.IProxy;
import com.fireball1725.firelib.util.FontRendererExtended;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@Mod(modid = ModInfo.MOD_ID, name = ModInfo.MOD_NAME, certificateFingerprint = ModInfo.FINGERPRINT, dependencies = ModInfo.DEPENDENCIES, version = ModInfo.VERSION_BUILD, guiFactory = ModInfo.GUI_FACTORY)
public class FireBallLibrary {
    @Mod.Instance(ModInfo.MOD_ID)
    public static FireBallLibrary instance;
    @SidedProxy(clientSide = ModInfo.CLIENT_PROXY_CLASS, serverSide = ModInfo.SERVER_PROXY_CLASS)
    public static IProxy proxy;
    public static Configuration configuration;

    @SideOnly(Side.CLIENT)
    public FontRendererExtended fontRendererExtendedObj;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        proxy.registerEvents();
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.registerFontRendererExtended();
        proxy.registerLayers();
    }
}
