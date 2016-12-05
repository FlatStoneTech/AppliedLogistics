package com.fireball1725.firelib.guimaker.objects;

import com.fireball1725.firelib.guimaker.GuiMaker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraftforge.fml.client.config.GuiUtils;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GuiProgressFire extends GuiObject {
    private int progress;

    public GuiProgressFire(int x, int y, int progress) {
        super(-999);
        this.x = x;
        this.y = y;
        this.progress = progress;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void drawGuiContainerBackgroundLayer(GuiContainer guiContainer, float partialTicks, int mouseX, int mouseY) {
        if (!this.visible)
            return;

        Minecraft.getMinecraft().getTextureManager().bindTexture(GuiMaker.resourceLocation);
        GuiUtils.drawTexturedModalRect(this.x + this.guiX, this.y + this.guiY, 192, 240, 16, 16, 0);

        int progressY = (int) Math.floor((16.0d / 100) * progress);
        GuiUtils.drawTexturedModalRect(this.x + this.guiX, this.y + this.guiY + 16 - progressY, 208, 256 - progressY, 16, progressY, 0);
    }

    public int getProgress() {
        return this.progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }
}
