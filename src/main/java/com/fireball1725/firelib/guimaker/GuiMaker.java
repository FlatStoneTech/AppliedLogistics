package com.fireball1725.firelib.guimaker;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import tech.flatstone.appliedlogistics.common.util.Platform;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public abstract class GuiMaker {
    public static ResourceLocation resourceLocation = new ResourceLocation("firelib", "textures/gui/darkskin.png");
    private static List<GuiMaker> guiInstances = new ArrayList<>();

    private final int guiId;

    private Class<? extends GuiMakerGuiContainer> classGuiContainer = null;
    private Class<? extends GuiMakerContainer> classContainer = null;

    private GuiMakerGuiContainer guiMakerGuiContainer;
    private GuiMakerContainer guiMakerContainer;

    public abstract Class<? extends GuiMakerGuiContainer> getGuiContainerClass();
    public abstract Class<? extends GuiMakerContainer> getContainerClass();

    public GuiMaker() {
        this.guiId = guiInstances.size();
        this.classContainer = getContainerClass();

        if (Platform.isClient())
            this.classGuiContainer = getGuiContainerClass();

        guiInstances.add(this);
    }

    public GuiMakerContainer getGuiMakerContainer() {
        return guiMakerContainer;
    }

    public GuiMakerGuiContainer getGuiMakerGuiContainer() {
        return guiMakerGuiContainer;
    }

    public static Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
        GuiMaker guiMaker = getGuiMakerInstance(id);
        if (guiMaker == null)
            return null;

        try {
            guiMaker.guiMakerContainer = guiMaker.classContainer.getConstructor(int.class, EntityPlayer.class, World.class, BlockPos.class).newInstance(id, player, world, new BlockPos(x, y, z));
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        return guiMaker.guiMakerContainer;
    }

    @SideOnly(Side.CLIENT)
    public static Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
        GuiMaker guiMaker = getGuiMakerInstance(id);
        if (guiMaker == null)
            return null;

        try {
            guiMaker.guiMakerGuiContainer = guiMaker.classGuiContainer.getConstructor(int.class, EntityPlayer.class, World.class, BlockPos.class).newInstance(id, player, world, new BlockPos(x, y, z));
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        return guiMaker.guiMakerGuiContainer;
    }

    public static GuiMaker getGuiMakerInstance(int guiID) {
        if (guiID < 0)
            return null;
        if (guiID >= guiInstances.size())
            return null;

        return guiInstances.get(guiID);
    }

    @SideOnly(Side.CLIENT)
    public void show(Object mod, World world, EntityPlayer player, BlockPos pos) {
        TileEntity tileEntity = world.getTileEntity(pos);

        player.openGui(mod, this.guiId, world, pos.getX(), pos.getY(), pos.getZ());
    }
}