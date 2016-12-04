package com.fireball1725.corelib;

import com.fireball1725.corelib.proxy.IProxy;
import com.fireball1725.corelib.util.FontRendererExtended;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = ModInfo.MOD_ID, name = ModInfo.MOD_NAME, certificateFingerprint = ModInfo.FINGERPRINT, dependencies = ModInfo.DEPENDENCIES, version = ModInfo.VERSION_BUILD, guiFactory = ModInfo.GUI_FACTORY)
public class FireBallCoreLibrary {
    @Mod.Instance(ModInfo.MOD_ID)
    public static FireBallCoreLibrary instance;
    @SidedProxy(clientSide = ModInfo.CLIENT_PROXY_CLASS, serverSide = ModInfo.SERVER_PROXY_CLASS)
    public static IProxy proxy;
    public static Configuration configuration;

    public FontRendererExtended fontRendererExtendedObj;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        proxy.registerEvents();
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.registerFontRendererExtended();
    }
}
