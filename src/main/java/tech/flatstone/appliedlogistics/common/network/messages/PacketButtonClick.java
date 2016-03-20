package tech.flatstone.appliedlogistics.common.network.messages;

import io.netty.buffer.ByteBuf;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import tech.flatstone.appliedlogistics.common.util.INetworkButton;

public class PacketButtonClick implements IMessage, IMessageHandler<PacketButtonClick, IMessage> {
    private int buttonID;
    private int blockX;
    private int blockY;
    private int blockZ;

    public PacketButtonClick() {

    }

    public PacketButtonClick(int buttonID, int blockX, int blockY, int blockZ) {
        this.buttonID = buttonID;
        this.blockX = blockX;
        this.blockY = blockY;
        this.blockZ = blockZ;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.buttonID = buf.readInt();
        this.blockX = buf.readInt();
        this.blockY = buf.readInt();
        this.blockZ = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(buttonID);
        buf.writeInt(blockX);
        buf.writeInt(blockY);
        buf.writeInt(blockZ);
    }

    @Override
    public IMessage onMessage(PacketButtonClick message, MessageContext ctx) {
        TileEntity tileEntity = ctx.getServerHandler().playerEntity.worldObj.getTileEntity(new BlockPos(message.blockX, message.blockY, message.blockZ));

        if (tileEntity == null)
            return null;

        if (tileEntity instanceof INetworkButton) {
            ((INetworkButton) tileEntity).actionPerformed(message.buttonID);
        }

        return null;
    }
}
