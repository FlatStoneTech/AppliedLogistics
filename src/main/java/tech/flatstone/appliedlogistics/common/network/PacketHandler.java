package tech.flatstone.appliedlogistics.common.network;

import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;
import tech.flatstone.appliedlogistics.ModInfo;
import tech.flatstone.appliedlogistics.common.network.messages.PacketButtonClick;

public class PacketHandler {
    public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(ModInfo.MOD_ID.toLowerCase());

    public static void init() {
        INSTANCE.registerMessage(PacketButtonClick.class, PacketButtonClick.class, 0, Side.SERVER);
    }
}
