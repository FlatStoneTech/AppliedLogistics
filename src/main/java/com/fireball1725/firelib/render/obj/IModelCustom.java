package com.fireball1725.firelib.render.obj;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public interface IModelCustom {
    String getType();

    @SideOnly(Side.CLIENT)
    void renderAll();

    @SideOnly(Side.CLIENT)
    void renderOnly(String... groupNames);

    @SideOnly(Side.CLIENT)
    void renderPart(String partName);

    @SideOnly(Side.CLIENT)
    void renderAllExcept(String... excludedGroupNames);
}