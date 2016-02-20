package com.fireball1725.appliedlogistics;

import com.fireball1725.appliedlogistics.proxy.IProxy;
import com.fireball1725.appliedlogistics.reference.ModInfo;
import com.google.common.base.Stopwatch;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = ModInfo.MOD_ID, name = ModInfo.MOD_NAME, certificateFingerprint = ModInfo.FINGERPRINT, dependencies = ModInfo.DEPENDENCIES, version = ModInfo.VERSION_BUILD)
public class AppliedLogistics {
    @Mod.Instance(ModInfo.MOD_ID)
    public static AppliedLogistics instance;

    @SidedProxy(clientSide = ModInfo.CLIENT_PROXY_CLASS, serverSide = ModInfo.SERVER_PROXY_CLASS)
    public static IProxy proxy;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        final Stopwatch stopwatch = Stopwatch.createStarted();

    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {

    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {

    }
}
