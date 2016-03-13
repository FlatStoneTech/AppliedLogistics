package tech.flatstone.appliedlogistics.client.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import tech.flatstone.appliedlogistics.ModInfo;
import tech.flatstone.appliedlogistics.common.container.slot.SlotBase;
import tech.flatstone.appliedlogistics.common.util.LogHelper;

public abstract class GuiBase extends GuiContainer {
    public GuiBase(Container container) {
        super(container);
    }

    @Override
    public void initGui() {
        super.initGui();
    }

    @Override
    public void drawScreen(int mouse_x, int mouse_y, float btn) {
        super.drawScreen(mouse_x, mouse_y, btn);
    }

    public void drawTooltip(int par2, int par3, int forceWidth, String Msg) {
        GL11.glPushAttrib(1048575);
        GL11.glDisable(32826);
        RenderHelper.disableStandardItemLighting();
        GL11.glDisable(2896);
        GL11.glDisable(2929);
        String[] var4 = Msg.split("\n");
        if (var4.length > 0) {
            int var5 = 0;
            for (int var6 = 0; var6 < var4.length; var6++) {
                int var7 = this.fontRendererObj.getStringWidth(var4[var6]);
                if (var7 > var5) {
                    var5 = var7;
                }
            }
            int var6 = par2 + 12;
            int var7 = par3 - 12;
            int var9 = 8;
            if (var4.length > 1) {
                var9 += 2 + (var4.length - 1) * 10;
            }
            if (this.guiTop + var7 + var9 + 6 > this.height) {
                var7 = this.height - var9 - this.guiTop - 6;
            }
            if (forceWidth > 0) {
                var5 = forceWidth;
            }
            this.zLevel = 300.0F;
            itemRender.zLevel = 300.0F;
            int var10 = -267386864;
            drawGradientRect(var6 - 3, var7 - 4, var6 + var5 + 3, var7 - 3, var10, var10);
            drawGradientRect(var6 - 3, var7 + var9 + 3, var6 + var5 + 3, var7 + var9 + 4, var10, var10);
            drawGradientRect(var6 - 3, var7 - 3, var6 + var5 + 3, var7 + var9 + 3, var10, var10);
            drawGradientRect(var6 - 4, var7 - 3, var6 - 3, var7 + var9 + 3, var10, var10);
            drawGradientRect(var6 + var5 + 3, var7 - 3, var6 + var5 + 4, var7 + var9 + 3, var10, var10);
            int var11 = 1347420415;
            int var12 = (var11 & 0xFEFEFE) >> 1 | var11 & 0xFF000000;
            drawGradientRect(var6 - 3, var7 - 3 + 1, var6 - 3 + 1, var7 + var9 + 3 - 1, var11, var12);
            drawGradientRect(var6 + var5 + 2, var7 - 3 + 1, var6 + var5 + 3, var7 + var9 + 3 - 1, var11, var12);
            drawGradientRect(var6 - 3, var7 - 3, var6 + var5 + 3, var7 - 3 + 1, var11, var11);
            drawGradientRect(var6 - 3, var7 + var9 + 2, var6 + var5 + 3, var7 + var9 + 3, var12, var12);
            for (int var13 = 0; var13 < var4.length; var13++) {
                String var14 = var4[var13];
                if (var13 == 0) {
                    var14 = "§" + Integer.toHexString(15) + var14;
                } else {
                    var14 = "§7" + var14;
                }
                this.fontRendererObj.drawStringWithShadow(var14, var6, var7, -1);
                if (var13 == 0) {
                    var7 += 2;
                }
                var7 += 10;
            }
            this.zLevel = 0.0F;
            itemRender.zLevel = 0.0F;
        }
        GL11.glPopAttrib();
    }

    public abstract void drawBG(int paramInt1, int paramInt2, int paramInt3, int paramInt4);

    public abstract void drawFG(int paramInt1, int paramInt2, int paramInt3, int paramInt4);

    public void bindTexture(String base, String file) {
        ResourceLocation resourceLocation = new ResourceLocation(base, "textures/" + file);
        this.mc.getTextureManager().bindTexture(resourceLocation);
    }

    public void bindTexture(String file) {
        ResourceLocation resourceLocation = new ResourceLocation(ModInfo.MOD_ID, "textures/" + file);
        this.mc.getTextureManager().bindTexture(resourceLocation);
    }

    protected void drawItem(int x, int y, ItemStack itemStack) {
        this.zLevel = 100.0F;
        itemRender.zLevel = 100.0F;

        GL11.glEnable(2896);
        GL11.glEnable(32826);
        RenderHelper.enableGUIStandardItemLighting();
        itemRender.renderItemAndEffectIntoGUI(itemStack, x, y);
        GL11.glDisable(2896);

        itemRender.zLevel = 0.0F;
        this.zLevel = 0.0F;
    }

    protected final void drawGuiContainerBackgroundLayer(float f, int x, int y) {
        int ox = this.guiLeft;
        int oy = this.guiTop;
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        drawBG(ox, oy, x, y);
    }

    protected final void drawGuiContainerForegroundLayer(int x, int y) {
        int ox = this.guiLeft;
        int oy = this.guiTop;
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        drawFG(ox, oy, x, y);
    }

    protected Slot getSlot(int mouseX, int mouseY) {
        for (int j1 = 0; j1 < this.inventorySlots.inventorySlots.size(); j1++) {
            Slot slot = (Slot) this.inventorySlots.inventorySlots.get(j1);
            if (isPointInRegion(slot.xDisplayPosition, slot.yDisplayPosition, 16, 16, mouseX, mouseY)) {
                return slot;
            }
        }
        return null;
    }

    @Override
    public void drawSlot(Slot s) {
        try {
            ItemStack is = s.getStack();
            if (((s instanceof SlotBase)) && ((((SlotBase) s).renderIconWithItem()) || (is == null)) && (((SlotBase) s).isEnabled)) {
                SlotBase aes = (SlotBase) s;
                //todo render itemstack in slot but with transparency...
            }
            if ((s instanceof SlotBase)) {
                ((SlotBase) s).isDisplay = true;
                safeDrawSlot(s);
            } else {
                safeDrawSlot(s);
            }
            return;
        } catch (Exception err) {
            safeDrawSlot(s);
        }
    }

    private void safeDrawSlot(Slot s) {
        try {
            super.drawSlot(s);
        } catch (Exception err) {
            LogHelper.error(err.getMessage());
        }
    }
}
