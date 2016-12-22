package tech.flatstone.appliedlogistics.common.tileentities.transport;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ITickable;
import tech.flatstone.appliedlogistics.AppliedLogistics;
import tech.flatstone.appliedlogistics.common.tileentities.TileEntityBase;

import java.util.UUID;

public class TIleEntityPipe extends TileEntityBase implements ITickable {
    private UUID nodeUUID;
    private boolean loaded = false;

    @Override
    public void validate() {
        super.validate();
        if (!loaded)
            nodeUUID = AppliedLogistics.instance.transportGrid.createTransportNode();
    }

    @Override
    public void invalidate() {
        super.invalidate();
        AppliedLogistics.instance.transportGrid.removeNode(nodeUUID);
    }

    /**
     * Like the old updateEntity(), except more generic.
     */
    @Override
    public void update() {

    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbtTagCompound) {
        nbtTagCompound = super.writeToNBT(nbtTagCompound);
        nbtTagCompound.setUniqueId("nodeUUID", nodeUUID);
        return nbtTagCompound;
    }

    @Override
    public void readFromNBT(NBTTagCompound nbtTagCompound) {
        super.readFromNBT(nbtTagCompound);
        nodeUUID = nbtTagCompound.getUniqueId("nodeUUID");
        loaded = true;
    }
}
