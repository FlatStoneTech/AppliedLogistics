package tech.flatstone.appliedlogistics.common.network.messages;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.util.IThreadListener;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import tech.flatstone.appliedlogistics.common.tileentities.misc.TileEntityPatternStamper;
import tech.flatstone.appliedlogistics.common.util.TileHelper;

public class PacketPatternStamperUpdateSelectedPlan implements IMessage, IMessageHandler<PacketPatternStamperUpdateSelectedPlan, IMessage> {
    private BlockPos blockPos;
    private int selectedMachine;

    public PacketPatternStamperUpdateSelectedPlan() {

    }

    public PacketPatternStamperUpdateSelectedPlan(BlockPos blockPos, int selectedPacket) {
        this.blockPos = blockPos;
        this.selectedMachine = selectedPacket;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.blockPos = BlockPos.fromLong(buf.readLong());
        this.selectedMachine = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeLong(this.blockPos.toLong());
        buf.writeInt(this.selectedMachine);
    }

    @Override
    public IMessage onMessage(PacketPatternStamperUpdateSelectedPlan message, MessageContext ctx) {
        IThreadListener mainThread = (WorldServer) ctx.getServerHandler().playerEntity.world;
        mainThread.addScheduledTask(new Runnable() {
            @Override
            public void run() {
                TileEntityPatternStamper tileEntity = TileHelper.getTileEntity(ctx.getServerHandler().playerEntity.world, message.blockPos, TileEntityPatternStamper.class);
                if (tileEntity != null) {
                    tileEntity.setSelectedMachine(message.selectedMachine);
                }
            }
        });
        return null;
    }
}
