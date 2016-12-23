package tech.flatstone.appliedlogistics.client.gui.misc;

import com.google.common.collect.Lists;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiListExtended;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.math.MathHelper;
import tech.flatstone.appliedlogistics.common.util.LogHelper;

import javax.annotation.Nullable;
import java.util.List;

public class GuiBuilderRecipeList extends GuiListExtended {
    private final List<GuiBuilderRecipeListEntry> entries = Lists.newArrayList();
    private int selectedIndex = -1;
    private GuiBuilder guiBuilder;

    public GuiBuilderRecipeList(GuiBuilder guiBuilder, Minecraft mcIn, int widthIn, int heightIn, int topIn, int bottomIn, int slotHeightIn) {
        super(mcIn, widthIn, heightIn, topIn, bottomIn, slotHeightIn);
        this.hasListHeader = false;
        this.guiBuilder = guiBuilder;
        refreshList();
    }

    public GuiBuilder getGuiBuilder() {
        return guiBuilder;
    }

    private void refreshList() {
        // todo:

        this.entries.add(new GuiBuilderRecipeListEntry(this));
        this.entries.add(new GuiBuilderRecipeListEntry(this));
        this.entries.add(new GuiBuilderRecipeListEntry(this));
        this.entries.add(new GuiBuilderRecipeListEntry(this));
        this.entries.add(new GuiBuilderRecipeListEntry(this));
        this.entries.add(new GuiBuilderRecipeListEntry(this));
        this.entries.add(new GuiBuilderRecipeListEntry(this));
        this.entries.add(new GuiBuilderRecipeListEntry(this));
        this.entries.add(new GuiBuilderRecipeListEntry(this));
        this.entries.add(new GuiBuilderRecipeListEntry(this));
        this.entries.add(new GuiBuilderRecipeListEntry(this));
        this.entries.add(new GuiBuilderRecipeListEntry(this));
        this.entries.add(new GuiBuilderRecipeListEntry(this));
        this.entries.add(new GuiBuilderRecipeListEntry(this));
        this.entries.add(new GuiBuilderRecipeListEntry(this));
        this.entries.add(new GuiBuilderRecipeListEntry(this));
        this.entries.add(new GuiBuilderRecipeListEntry(this));
        this.entries.add(new GuiBuilderRecipeListEntry(this));
    }

    @Override
    protected void drawContainerBackground(Tessellator tessellator) {
        // Don't draw the container background...
    }

    @Override
    protected void overlayBackground(int startY, int endY, int startAlpha, int endAlpha) {
        //super.overlayBackground(startY, endY, startAlpha, endAlpha);
    }

    @Override
    protected void drawListHeader(int insideLeft, int insideTop, Tessellator tessellatorIn) {
        super.drawListHeader(insideLeft, insideTop, tessellatorIn);
    }

    @Override
    protected void renderDecorations(int mouseXIn, int mouseYIn) {
        //super.renderDecorations(mouseXIn, mouseYIn);
    }

    @Override
    protected void drawBackground() {
        // Don't draw the background...
    }

    @Override
    protected int getSize() {
        return this.entries.size();
    }

    @Override
    protected int getScrollBarX() {
        return super.getScrollBarX() + 20;
    }

    @Override
    public int getListWidth() {
        return super.getListWidth() + 50;
    }

    @Override
    protected boolean isSelected(int slotIndex) {
        return slotIndex == this.selectedIndex;
    }

    @Override
    public GuiBuilderRecipeListEntry getListEntry(int index) {
        return this.entries.get(index);
    }

    public void selectItem(int index) {
        //GuiBuilderRecipeListEntry entry = this.entries.get(index);
        //if (entry.getPlatform().isSeparator())
        //    return;

        //this.selectedIndex = index;
        //this.guiSettings.selectEntry(this.getSelectedEntry());
    }

    @Override
    public void drawScreen(int mouseXIn, int mouseYIn, float partialTicks)
    {
        if (visible)
        {
            mouseX = mouseXIn;
            mouseY = mouseYIn;
            drawBackground();
            int i = getScrollBarX();
            int j = i + 6;
            bindAmountScrolled();
            GlStateManager.disableLighting();
            GlStateManager.disableFog();
            Tessellator tessellator = Tessellator.getInstance();
            VertexBuffer vertexbuffer = tessellator.getBuffer();
            int k = left + width / 2 - getListWidth() / 2 + 2;
            int l = top + 4 - (int)amountScrolled;

            if (hasListHeader)
                drawListHeader(k, l, tessellator);

            drawSelectionBox(k, l, mouseXIn, mouseYIn);
            GlStateManager.disableDepth();
            drawOverlays();
            GlStateManager.enableBlend();
            GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ZERO, GlStateManager.DestFactor.ONE);
            GlStateManager.disableAlpha();
            GlStateManager.shadeModel(7425);
            GlStateManager.disableTexture2D();

            int j1 = getMaxScroll();

            if (j1 > 0)
            {
                int k1 = (bottom - top) * (bottom - top) / getContentHeight();
                k1 = MathHelper.clamp(k1, 32, bottom - top - 8);
                int l1 = (int)amountScrolled * (bottom - top - k1) / j1 + top;

                if (l1 < top)
                    l1 = top;

                vertexbuffer.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
                vertexbuffer.pos(i, bottom + 1, 0.0D).tex(0.0D, 1.0D).color(0, 0, 0, 255).endVertex();
                vertexbuffer.pos(j, bottom + 1, 0.0D).tex(1.0D, 1.0D).color(0, 0, 0, 255).endVertex();
                vertexbuffer.pos(j, top - 1, 0.0D).tex(1.0D, 0.0D).color(0, 0, 0, 255).endVertex();
                vertexbuffer.pos(i, top - 1, 0.0D).tex(0.0D, 0.0D).color(0, 0, 0, 255).endVertex();
                tessellator.draw();
                vertexbuffer.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
                vertexbuffer.pos(i + 1, (l1 + k1), 0.0D).tex(0.0D, 1.0D).color(139, 139, 139, 255).endVertex();
                vertexbuffer.pos(j - 1, (l1 + k1), 0.0D).tex(1.0D, 1.0D).color(139, 139, 139, 255).endVertex();
                vertexbuffer.pos(j - 1, l1, 0.0D).tex(1.0D, 0.0D).color(139, 139, 139, 255).endVertex();
                vertexbuffer.pos(i + 1, l1, 0.0D).tex(0.0D, 0.0D).color(139, 139, 139, 255).endVertex();
                tessellator.draw();
            }

            renderDecorations(mouseXIn, mouseYIn);
            GlStateManager.enableTexture2D();
            GlStateManager.shadeModel(7424);
            GlStateManager.enableAlpha();
            GlStateManager.disableBlend();
        }
    }

    protected void drawOverlays()
    {
//        mc.getTextureManager().bindTexture(guiModelMaker.GUI_TEXTURE);
//        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
//        int left = guiModelMaker.getGuiLeft() - 24;
//        int top = guiModelMaker.getGuiTop();
//        guiModelMaker.drawTexturedModalRect(left, top, 0, 0, 254, 21);
//        int offsetY = 121;
//        guiModelMaker.drawTexturedModalRect(left, top + offsetY, 0, offsetY, 254, 219 - offsetY);
    }


}
