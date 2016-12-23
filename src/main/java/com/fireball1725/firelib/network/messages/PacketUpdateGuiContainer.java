package com.fireball1725.firelib.network.messages;

import com.fireball1725.firelib.guimaker.GuiMaker;
import io.netty.buffer.ByteBuf;
import net.minecraft.util.IThreadListener;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketUpdateGuiContainer implements IMessage, IMessageHandler<PacketUpdateGuiContainer, IMessage> {
    private int selectedTab;
    private int guiID;

    public PacketUpdateGuiContainer() {

    }

    public PacketUpdateGuiContainer(int guiID, int selectedTab) {
        this.guiID = guiID;
        this.selectedTab = selectedTab;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.guiID = buf.readInt();
        this.selectedTab = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(this.guiID);
        buf.writeInt(this.selectedTab);
    }

    @Override
    public IMessage onMessage(final PacketUpdateGuiContainer message, final MessageContext ctx) {
        IThreadListener mainThread = (WorldServer) ctx.getServerHandler().playerEntity.world;
        mainThread.addScheduledTask(new Runnable() {
            @Override
            public void run() {
                GuiMaker guiMaker = GuiMaker.getGuiMaker(message.guiID);
                guiMaker.setServerGuiTab(message.selectedTab, ctx.getServerHandler().playerEntity);
            }
        });

        return null;
    }
}
