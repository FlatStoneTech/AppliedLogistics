package tech.flatstone.appliedlogistics.client.gui.builder;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import tech.flatstone.appliedlogistics.ModMessages;
import tech.flatstone.appliedlogistics.client.gui.GuiBase;
import tech.flatstone.appliedlogistics.common.container.builder.ContainerBuilder;
import tech.flatstone.appliedlogistics.common.tileentities.builder.TileEntityBuilder;
import tech.flatstone.appliedlogistics.common.util.LanguageHelper;

public class GuiBuilder extends GuiBase {
    TileEntityBuilder tileEntity;
    public GuiBuilder(InventoryPlayer inventoryPlayer, TileEntityBuilder tileEntity) {
        super(new ContainerBuilder(inventoryPlayer, tileEntity));
        this.xSize = 176;
        this.ySize = 221;
        this.tileEntity = tileEntity;
    }

    @Override
    public void drawBG(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
        bindTexture("gui/machines/builder.png");
        drawTexturedModalRect(paramInt1, paramInt2, 0, 0, this.xSize, this.ySize);
    }

    @Override
    public void drawFG(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
        this.fontRendererObj.drawString(LanguageHelper.getTranslated(tileEntity.getUnlocalizedName()), 6, 6, 4210752);

        ItemStack itemPlan = tileEntity.getInternalInventory().getStackInSlot(0);

        if (itemPlan == null) {
            this.fontRendererObj.drawString(EnumChatFormatting.RED + LanguageHelper.getTranslatedMessage(ModMessages.MESSAGE_PLAN_INSERT), 32, 22, 4210752);
        } else if (!itemPlan.hasTagCompound()) {
            this.fontRendererObj.drawString(EnumChatFormatting.RED + LanguageHelper.getTranslatedMessage(ModMessages.MESSAGE_PLAN_INVALID), 32, 22, 4210752);
        } else {
            this.fontRendererObj.drawString(LanguageHelper.getTranslated(itemPlan.getUnlocalizedName() + ".name"), 32, 22, 4210752);
        }
    }
}
