package com.fireball1725.firelib.network.messages;

import com.fireball1725.firelib.guimaker.GuiMaker;
import io.netty.buffer.ByteBuf;
import net.minecraft.util.IThreadListener;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketGuiObjectClicked implements IMessage, IMessageHandler<PacketGuiObjectClicked, IMessage> {
    private int objectID;
    private int guiID;
    private BlockPos blockPos;

    public PacketGuiObjectClicked() {

    }

    public PacketGuiObjectClicked(int guiID, int objectID, BlockPos blockPos) {
        this.objectID = objectID;
        this.guiID = guiID;
        this.blockPos = blockPos;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.guiID = buf.readInt();
        this.objectID = buf.readInt();
        this.blockPos = new BlockPos(buf.readInt(), buf.readInt(), buf.readInt());
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(this.guiID);
        buf.writeInt(this.objectID);
        buf.writeInt(this.blockPos.getX());
        buf.writeInt(this.blockPos.getY());
        buf.writeInt(this.blockPos.getZ());
    }

    @Override
    public IMessage onMessage(final PacketGuiObjectClicked message, final MessageContext ctx) {
        IThreadListener mainThread = (WorldServer) ctx.getServerHandler().playerEntity.world;
        mainThread.addScheduledTask(new Runnable() {
            @Override
            public void run() {
                GuiMaker guiMaker = GuiMaker.getGuiMaker(message.guiID);
                guiMaker.guiObjectClickedClient(message.objectID, ctx.getServerHandler().playerEntity.world, message.blockPos);
            }
        });

        return null;
    }
}
