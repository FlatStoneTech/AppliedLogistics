package com.fireball1725.firelib.network;

import com.fireball1725.firelib.network.messages.*;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class PacketHandler {
    public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel("firelib");

    public static int packetId = 0;

    public static void init() {
        register(PacketUpdateGuiContainer.class, PacketUpdateGuiContainer.class, Side.SERVER);
        register(PacketGuiObjectClicked.class, PacketGuiObjectClicked.class, Side.SERVER);
        register(PacketGuiSlotAdd.class, PacketGuiSlotAdd.class, Side.SERVER);
        register(PacketGuiSlotClear.class, PacketGuiSlotClear.class, Side.SERVER);
        register(PacketGuiSlotAddInventory.class, PacketGuiSlotAddInventory.class, Side.SERVER);
    }

    private static void register(Class handler, Class packet, Side side) {
        INSTANCE.registerMessage(handler, packet, packetId++, side);
    }
}
