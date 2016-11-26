package com.fireball1725.corelib.guimaker.network;

import com.fireball1725.corelib.guimaker.network.messages.PacketUpdateGuiContainer;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class PacketHandler {
    public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel("firelib");

    public static void init() {
        INSTANCE.registerMessage(PacketUpdateGuiContainer.class, PacketUpdateGuiContainer.class, 0, Side.SERVER);
    }
}
