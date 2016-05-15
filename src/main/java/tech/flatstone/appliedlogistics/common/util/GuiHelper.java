/*
 *
 * LIMITED USE SOFTWARE LICENSE AGREEMENT
 * This Limited Use Software License Agreement (the "Agreement") is a legal agreement between you, the end-user, and the FlatstoneTech Team ("FlatstoneTech"). By downloading or purchasing the software material, which includes source code (the "Source Code"), artwork data, music and software tools (collectively, the "Software"), you are agreeing to be bound by the terms of this Agreement. If you do not agree to the terms of this Agreement, promptly destroy the Software you may have downloaded or copied.
 * FlatstoneTech SOFTWARE LICENSE
 * 1. Grant of License. FlatstoneTech grants to you the right to use the Software. You have no ownership or proprietary rights in or to the Software, or the Trademark. For purposes of this section, "use" means loading the Software into RAM, as well as installation on a hard disk or other storage device. The Software, together with any archive copy thereof, shall be destroyed when no longer used in accordance with this Agreement, or when the right to use the Software is terminated. You agree that the Software will not be shipped, transferred or exported into any country in violation of the U.S. Export Administration Act (or any other law governing such matters) and that you will not utilize, in any other manner, the Software in violation of any applicable law.
 * 2. Permitted Uses. For educational purposes only, you, the end-user, may use portions of the Source Code, such as particular routines, to develop your own software, but may not duplicate the Source Code, except as noted in paragraph 4. The limited right referenced in the preceding sentence is hereinafter referred to as "Educational Use." By so exercising the Educational Use right you shall not obtain any ownership, copyright, proprietary or other interest in or to the Source Code, or any portion of the Source Code. You may dispose of your own software in your sole discretion. With the exception of the Educational Use right, you may not otherwise use the Software, or an portion of the Software, which includes the Source Code, for commercial gain.
 * 3. Prohibited Uses: Under no circumstances shall you, the end-user, be permitted, allowed or authorized to commercially exploit the Software. Neither you nor anyone at your direction shall do any of the following acts with regard to the Software, or any portion thereof:
 * Rent;
 * Sell;
 * Lease;
 * Offer on a pay-per-play basis;
 * Distribute for money or any other consideration; or
 * In any other manner and through any medium whatsoever commercially exploit or use for any commercial purpose.
 * Notwithstanding the foregoing prohibitions, you may commercially exploit the software you develop by exercising the Educational Use right, referenced in paragraph 2. hereinabove.
 * 4. Copyright. The Software and all copyrights related thereto (including all characters and other images generated by the Software or depicted in the Software) are owned by FlatstoneTech and is protected by United States copyright laws and international treaty provisions. FlatstoneTech shall retain exclusive ownership and copyright in and to the Software and all portions of the Software and you shall have no ownership or other proprietary interest in such materials. You must treat the Software like any other copyrighted material. You may not otherwise reproduce, copy or disclose to others, in whole or in any part, the Software. You may not copy the written materials accompanying the Software. You agree to use your best efforts to see that any user of the Software licensed hereunder complies with this Agreement.
 * 5. NO WARRANTIES. FLATSTONETECH DISCLAIMS ALL WARRANTIES, BOTH EXPRESS IMPLIED, INCLUDING BUT NOT LIMITED TO, IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE WITH RESPECT TO THE SOFTWARE. THIS LIMITED WARRANTY GIVES YOU SPECIFIC LEGAL RIGHTS. YOU MAY HAVE OTHER RIGHTS WHICH VARY FROM JURISDICTION TO JURISDICTION. FlatstoneTech DOES NOT WARRANT THAT THE OPERATION OF THE SOFTWARE WILL BE UNINTERRUPTED, ERROR FREE OR MEET YOUR SPECIFIC REQUIREMENTS. THE WARRANTY SET FORTH ABOVE IS IN LIEU OF ALL OTHER EXPRESS WARRANTIES WHETHER ORAL OR WRITTEN. THE AGENTS, EMPLOYEES, DISTRIBUTORS, AND DEALERS OF FlatstoneTech ARE NOT AUTHORIZED TO MAKE MODIFICATIONS TO THIS WARRANTY, OR ADDITIONAL WARRANTIES ON BEHALF OF FlatstoneTech.
 * Exclusive Remedies. The Software is being offered to you free of any charge. You agree that you have no remedy against FlatstoneTech, its affiliates, contractors, suppliers, and agents for loss or damage caused by any defect or failure in the Software regardless of the form of action, whether in contract, tort, includinegligence, strict liability or otherwise, with regard to the Software. Copyright and other proprietary matters will be governed by United States laws and international treaties. IN ANY CASE, FlatstoneTech SHALL NOT BE LIABLE FOR LOSS OF DATA, LOSS OF PROFITS, LOST SAVINGS, SPECIAL, INCIDENTAL, CONSEQUENTIAL, INDIRECT OR OTHER SIMILAR DAMAGES ARISING FROM BREACH OF WARRANTY, BREACH OF CONTRACT, NEGLIGENCE, OR OTHER LEGAL THEORY EVEN IF FLATSTONETECH OR ITS AGENT HAS BEEN ADVISED OF THE POSSIBILITY OF SUCH DAMAGES, OR FOR ANY CLAIM BY ANY OTHER PARTY. Some jurisdictions do not allow the exclusion or limitation of incidental or consequential damages, so the above limitation or exclusion may not apply to you.
 */

package tech.flatstone.appliedlogistics.common.util;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;
import tech.flatstone.appliedlogistics.common.tileentities.TileEntityMachineBase;

import java.awt.*;
import java.util.List;

public class GuiHelper extends GuiScreen {
    private Minecraft mc = Minecraft.getMinecraft();
    private FontRenderer fontRenderer = mc.fontRendererObj;

    private void drawWindow(int x, int y, int w, int h, int bgColor) {
        drawRect(x - 3, y - 4, x + w + 3, y - 3, bgColor);
        drawRect(x - 3, y + h + 3, x + w + 3, y + h + 4, bgColor);
        drawRect(x - 3, y - 3, x + w + 3, y + h + 3, bgColor);
        drawRect(x - 4, y - 3, x - 3, y + h + 3, bgColor);
        drawRect(x + w + 3, y - 3, x + w + 4, y + h + 3, bgColor);
    }

    public void drawWindowWithBorder(int x, int y, int w, int h, int bgColor, int frameColor) {
        drawWindow(x, y, w, h, bgColor);
        int frameFade;
        frameFade = (frameColor & 0xFEFEFE) >> 1 | frameColor & 0xFF000000;

        drawGradientRect(x - 3, y - 3 + 1, x - 3 + 1, y + h + 3 - 1, frameColor, frameFade);
        drawGradientRect(x + w + 2, y - 3 + 1, x + w + 3, y + h + 3 - 1, frameColor, frameFade);
        drawGradientRect(x - 3, y - 3, x + w + 3, y - 3 + 1, frameColor, frameFade);
        drawGradientRect(x - 3, y + h + 2, x + w + 3, y + h + 3, frameColor, frameFade);
    }

    public void drawLineOnVerticalProgressBar(int x, int y, int w, int h, int p, int pMax, int lineColor) {
        x -= 2;
        y -= 2;
        w += 4;
        h += 4;

        //int lineY = Math.round(((float) h / 100) * p);

        //int lineY = Math.round(((((float)p) / ((float)(pMax - 20)))) * h);
        int lineY = Math.round((float) p / (float) pMax * h);

        drawRect(x, y + h - lineY, w + x, y + h - lineY + 1, lineColor);
    }

    public void drawVerticalProgressBar(int x, int y, int w, int h, int p, int bgColor, int frameColor, int progressColor) {
        drawProgressBar(x, y, w, h, p, bgColor, frameColor, progressColor, 1);
    }

    public void drawHorizontalProgressBar(int x, int y, int w, int h, int p, int bgColor, int frameColor, int progressColor) {
        drawProgressBar(x, y, w, h, p, bgColor, frameColor, progressColor, 0);
    }

    private void drawProgressBar(int x, int y, int w, int h, int p, int bgColor, int frameColor, int progressColor, int hv) {
        drawWindowWithBorder(x, y, w, h, bgColor, frameColor);

        // Adjust x, y, w, h to fit progress bar inside window...
        x -= 2;
        y -= 2;
        w += 4;
        h += 4;

        switch (hv) {
            case 0:
                float pWf = ((float) w / 100) * p;
                int pW = Math.round(pWf);
                drawRect(x, y, x + pW, y + h, progressColor);
                break;
            case 1:
                float pHf = ((float) h / 100) * p;
                int pH = Math.round(pHf);
                drawRect(x, y + h - pH, w + x, h + y, progressColor);
                break;
            default:
                break;
        }
    }

    public void drawItemStack(ItemStack itemStack, int x, int y) {
        int[][] savedGLState = OpenGLHelper.saveGLState(new int[]{GL11.GL_ALPHA_TEST, GL11.GL_LIGHTING});
        RenderItem renderItem = mc.getRenderItem();

        GL11.glPushMatrix();
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        RenderHelper.enableGUIStandardItemLighting();
        GL11.glEnable(GL12.GL_RESCALE_NORMAL);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        renderItem.renderItemIntoGUI(itemStack, x, y);
        GL11.glDisable(GL12.GL_RESCALE_NORMAL);
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glPopMatrix();

        OpenGLHelper.restoreGLState(savedGLState);
    }

    /**
     * Draws a transparent item in the slot
     *
     * @param itemStack  item to draw
     * @param x          slot x
     * @param y          slot y
     * @param renderItem Item Render
     */
    public void drawItemStack(ItemStack itemStack, int x, int y, RenderItem renderItem, boolean transparent) {
        this.zLevel = 50.0f;
        renderItem.zLevel = 50.0f;

        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        int colorOverlay = new Color(139, 139, 139, 160).hashCode();

        RenderHelper.enableGUIStandardItemLighting();
        renderItem.renderItemAndEffectIntoGUI(itemStack, x, y);

        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

        GlStateManager.disableDepth();
        GlStateManager.colorMask(true, true, true, false);
        if (transparent) {
            this.zLevel = 100.0f;
            renderItem.zLevel = 100.0f;
            this.drawGradientRect(x, y, x + 16, y + 16, colorOverlay, colorOverlay);
        }
        GlStateManager.colorMask(true, true, true, true);
        GlStateManager.enableDepth();

        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);

        this.zLevel = 0.0f;
        renderItem.zLevel = 0.0f;
    }

    public void drawMiniItemStack(ItemStack itemStack, int x, int y) {
        int[][] savedGLState = OpenGLHelper.saveGLState(new int[]{GL11.GL_ALPHA_TEST, GL11.GL_LIGHTING});
        GL11.glPushMatrix();
        GL11.glScalef(0.5f, 0.5f, 0.5f);
        drawItemStack(itemStack, x, y);
        GL11.glScalef(2.0f, 2.0f, 2.0f);
        GL11.glPopMatrix();
        OpenGLHelper.restoreGLState(savedGLState);
    }

    /**
     * Draws a slot that is disabled...
     *
     * @param x          slot x
     * @param y          slot y
     * @param renderItem Item Render
     */
    public void drawDisabledSlot(int x, int y, RenderItem renderItem) {
        this.zLevel = 100.f;
        renderItem.zLevel = 100.0f;

        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        int colorOverlay = new Color(139, 139, 139, 200).hashCode();
        GlStateManager.disableLighting();
        GlStateManager.disableDepth();
        GlStateManager.colorMask(true, true, true, false);
        renderItem.renderItemAndEffectIntoGUI(new ItemStack(Blocks.barrier), x, y);
        this.drawGradientRect(x, y, x + 16, y + 16, colorOverlay, colorOverlay);
        GlStateManager.colorMask(true, true, true, true);
        GlStateManager.enableLighting();
        GlStateManager.enableDepth();
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

        this.zLevel = 0.0f;
        renderItem.zLevel = 0.0f;
    }

    public void drawCenteredStringWithShadow(int x, int y, int w, String message, int color) {
        int messageWidth = fontRenderer.getStringWidth(message);
        int messageX = x + ((w >> 1) - (messageWidth >> 1));

        fontRenderer.drawStringWithShadow(message, messageX, y, color);
    }

    public void drawStringWithShadow(int x, int y, String message, int color) {
        fontRenderer.drawStringWithShadow(message, x, y, color);
    }

    public void drawCenteredString(int x, int y, int w, String message, int color) {
        int messageWidth = fontRenderer.getStringWidth(message);
        int messageX = x + ((w >> 1) - (messageWidth >> 1));

        fontRenderer.drawString(message, messageX, y, color);
    }

    public void renderSplitString(String str, int x, int y, int wrapWidth, int textColor) {
        int posY = y;
        for (String s : fontRenderer.listFormattedStringToWidth(str, wrapWidth)) {
            drawStringWithShadow(x, posY, s, textColor);
            posY += fontRenderer.FONT_HEIGHT;
        }
    }

    public List<String> getSplitString(String str, int wrapWidth) {
        return fontRenderer.listFormattedStringToWidth(str, wrapWidth);
    }

    public void drawPlayerHead(int x, int y) {
        ResourceLocation playerSkin = Minecraft.getMinecraft().thePlayer.getLocationSkin();
        mc.getTextureManager().bindTexture(playerSkin);

        int[][] savedGLState = OpenGLHelper.saveGLState(new int[]{GL11.GL_ALPHA_TEST, GL11.GL_LIGHTING});
        GL11.glPushMatrix();
        GL11.glScalef(1.0F, 0.5F, 1.0F);

        this.drawTexturedModalRect(x, y, 32, 64, 32, 64);
        this.drawTexturedModalRect(x, y, 160, 64, 32, 64);

        GL11.glPopMatrix();
        OpenGLHelper.restoreGLState(savedGLState);
    }

    private void drawPixels(int x, int y, int u, int v, int width, int height, int times) {
        for (int cu = u; cu < u + width; cu++) {
            for (int cv = v; cv < v + height; cv++) {
                for (int factor = 0; factor < times; factor++) {
                    drawTexturedModalRect(x + (cu * 3) + factor, y + (cv * 3) + factor, cu + u, cv + v, 1, 1);
                }
            }
        }
    }

    public void drawResource(ResourceLocation resource, int x, int y, int x1, int y1, int w, int h) {
        this.zLevel = 300;

        Minecraft.getMinecraft().getTextureManager().bindTexture(resource);

        GL11.glPushMatrix();
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

        drawTexturedModalRect(x, y, x1, y1, w, h);

        GL11.glDisable(GL11.GL_BLEND);
        GL11.glPopMatrix();

    }

    public void drawMachineUpgradeIcons(int x, int y, TileEntityMachineBase tileEntity) {
        int iconX = x;

        if (tileEntity.isComparatorEnabled()) {
            drawMiniItemStack(new ItemStack(Items.comparator), iconX, y);
            iconX = iconX - 18;
        }

        if (tileEntity.isRedstoneEnabled()) {
            drawMiniItemStack(new ItemStack(Items.redstone), iconX, y);
            iconX = iconX - 18;
        }

        if (tileEntity.isSidedEnabled()) {
            drawMiniItemStack(new ItemStack(Blocks.hopper), iconX, y);
        }
    }
}
