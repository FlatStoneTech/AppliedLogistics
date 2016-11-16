package tech.flatstone.appliedlogistics.client.gui.misc;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiListExtended;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.inventory.IInventory;
import tech.flatstone.appliedlogistics.common.container.slot.SlotBase;
import tech.flatstone.appliedlogistics.common.tileentities.inventory.InternalInventory;

public class GuiBuilderRecipeListEntry implements GuiListExtended.IGuiListEntry {
    private final Minecraft client;
    private final GuiBuilderRecipeList guiBuilderRecipeList;
    private long lastClickTime;

    public GuiBuilderRecipeListEntry(GuiBuilderRecipeList guiBuilderRecipeList) {
        this.client = Minecraft.getMinecraft();
        this.guiBuilderRecipeList = guiBuilderRecipeList;
    }

    @Override
    public void setSelected(int p_178011_1_, int p_178011_2_, int p_178011_3_) {
        // do nothing ...
    }

    @Override
    public void drawEntry(int slotIndex, int x, int y, int listWidth, int slotHeight, int mouseX, int mouseY, boolean isSelected) {
        // todo:
        /*
            Entry Name
            Shadow Icon (scrollable)
            Slot (for inventory)
            Text box for Min - Max / Weight / etc?
         */
        int guiTop = guiBuilderRecipeList.getGuiBuilder().guiTop;

        if (y > guiTop && y < guiTop + 125) {
            this.client.fontRendererObj.drawString("Hello World...", x, y + 10, 16777215);
            this.client.fontRendererObj.drawString("Hello World 1...", x, y + 20, 16777215);

            // todo: figure out why guiHelper isn't working
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            GlStateManager.enableBlend();
            guiBuilderRecipeList.getGuiBuilder().getGuiHelper().drawHorizontalProgressBar(x, y + 30, 40, 10, 50, 0x505050, 0xFFFFFF, 0x0000FF);
            GlStateManager.disableBlend();

            SlotBase testSlot = new SlotBase(guiBuilderRecipeList.getGuiBuilder().tileEntity.getInternalInventory(), 0, x, y + 10);
            guiBuilderRecipeList.getGuiBuilder().drawSlot(testSlot);

            // notes:
            /*
            so slots do work, but there is no highlight, or click on the slot, guessing because of the order the list runs.  Need to send
            mouse events to the slots that are added by the list.

            Also need to figure out an inventory to send to the slots, probably an internal inventory that isn't fixed on slot size.  Like
            what was used for the gravestone mod.

            Also, need to plan this out a little bit better...  Probably use a background image that alternates between A and B, and seperators
            will use vatiation C.  Then draw the items over the background image.
             */
        }
    }

    @Override
    public boolean mousePressed(int slotIndex, int mouseX, int mouseY, int mouseEvent, int relativeX, int relativeY) {
        this.guiBuilderRecipeList.selectItem(slotIndex);

        if (relativeX <= 32 && relativeX < 32) {
            return true;
        } else if (Minecraft.getSystemTime() - this.lastClickTime < 250L) {
            return true;
        } else {
            this.lastClickTime = Minecraft.getSystemTime();
            return false;
        }
    }

    @Override
    public void mouseReleased(int slotIndex, int x, int y, int mouseEvent, int relativeX, int relativeY) {
        // do nothing ...
    }
}
