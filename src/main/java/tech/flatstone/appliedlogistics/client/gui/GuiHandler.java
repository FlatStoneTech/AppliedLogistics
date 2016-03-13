package tech.flatstone.appliedlogistics.client.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import tech.flatstone.appliedlogistics.client.gui.builder.GuiBuilder;
import tech.flatstone.appliedlogistics.client.gui.machines.GuiPulverizer;
import tech.flatstone.appliedlogistics.common.container.builder.ContainerBuilder;
import tech.flatstone.appliedlogistics.common.container.machines.ContainerPulverizer;
import tech.flatstone.appliedlogistics.common.tileentities.builder.TileEntityBuilder;
import tech.flatstone.appliedlogistics.common.tileentities.machines.TileEntityPulverizer;

public class GuiHandler implements IGuiHandler {

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        TileEntity tileEntity = world.getTileEntity(new BlockPos(x, y, z));

        if (tileEntity == null)
            return null;

        switch(ID) {
            case 0: // Builder GUI
                return new ContainerBuilder(player.inventory, tileEntity);
            case 1: // Pulverizer GUI
                return new ContainerPulverizer(player.inventory, tileEntity);
        }

        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        TileEntity tileEntity = world.getTileEntity(new BlockPos(x, y, z));

        if (tileEntity == null)
            return null;

        switch(ID) {
            case 0: // Builder GUI
                return new GuiBuilder(player.inventory, (TileEntityBuilder)tileEntity);
            case 1: // Pulverizer GUI
                return new GuiPulverizer(player.inventory, (TileEntityPulverizer)tileEntity);
        }

        return null;
    }
}
