package com.fireball1725.firelib.network.messages;

import com.fireball1725.firelib.guimaker.GuiMaker;
import io.netty.buffer.ByteBuf;
import net.minecraft.util.IThreadListener;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketGuiObjectClicked implements IMessage, IMessageHandler<PacketGuiObjectClicked, IMessage> {
    private int objectID;
    private int guiID;

    public PacketGuiObjectClicked() {

    }

    public PacketGuiObjectClicked(int guiID, int objectID) {
        this.objectID = objectID;
        this.guiID = guiID;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.guiID = buf.readInt();
        this.objectID = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(this.guiID);
        buf.writeInt(this.objectID);
    }

    @Override
    public IMessage onMessage(final PacketGuiObjectClicked message, final MessageContext ctx) {
        IThreadListener mainThread = (WorldServer)ctx.getServerHandler().playerEntity.worldObj;
        mainThread.addScheduledTask(new Runnable() {
            @Override
            public void run() {
                GuiMaker guiMaker = GuiMaker.getGuiMaker(message.guiID);
                guiMaker.guiObjectClicked(message.objectID);
            }
        });

        return null;
    }
}
