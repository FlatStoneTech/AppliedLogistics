/*
 * LIMITED USE SOFTWARE LICENSE AGREEMENT
 * This Limited Use Software License Agreement (the "Agreement") is a legal agreement between you, the end-user, and the FlatstoneTech Team ("FlatstoneTech"). By downloading or purchasing the software material, which includes source code (the "Source Code"), artwork data, music and software tools (collectively, the "Software"), you are agreeing to be bound by the terms of this Agreement. If you do not agree to the terms of this Agreement, promptly destroy the Software you may have downloaded or copied.
 * FlatstoneTech SOFTWARE LICENSE
 * 1. Grant of License. FlatstoneTech grants to you the right to use the Software. You have no ownership or proprietary rights in or to the Software, or the Trademark. For purposes of this section, "use" means loading the Software into RAM, as well as installation on a hard disk or other storage device. The Software, together with any archive copy thereof, shall be destroyed when no longer used in accordance with this Agreement, or when the right to use the Software is terminated. You agree that the Software will not be shipped, transferred or exported into any country in violation of the U.S. Export Administration Act (or any other law governing such matters) and that you will not utilize, in any other manner, the Software in violation of any applicable law.
 * 2. Permitted Uses. For educational purposes only, you, the end-user, may use portions of the Source Code, such as particular routines, to develop your own software, but may not duplicate the Source Code, except as noted in paragraph 4. The limited right referenced in the preceding sentence is hereinafter referred to as "Educational Use." By so exercising the Educational Use right you shall not obtain any ownership, copyright, proprietary or other interest in or to the Source Code, or any portion of the Source Code. You may dispose of your own software in your sole discretion. With the exception of the Educational Use right, you may not otherwise use the Software, or an portion of the Software, which includes the Source Code, for commercial gain.
 * 3. Prohibited Uses: Under no circumstances shall you, the end-user, be permitted, allowed or authorized to commercially exploit the Software. Neither you nor anyone at your direction shall do any of the following acts with regard to the Software, or any portion thereof:
 *  * Rent;
 *  * Sell;
 *  * Lease;
 *  * Offer on a pay-per-play basis;
 *  * Distribute for money or any other consideration; or
 *  * In any other manner and through any medium whatsoever commercially exploit or use for any commercial purpose.
 *  * Notwithstanding the foregoing prohibitions, you may commercially exploit the software you develop by exercising the Educational Use right, referenced in paragraph 2. hereinabove.
 *  4. Copyright. The Software and all copyrights related thereto (including all characters and other images generated by the Software or depicted in the Software) are owned by FlatstoneTech and is protected by United States copyright laws and international treaty provisions. FlatstoneTech shall retain exclusive ownership and copyright in and to the Software and all portions of the Software and you shall have no ownership or other proprietary interest in such materials. You must treat the Software like any other copyrighted material. You may not otherwise reproduce, copy or disclose to others, in whole or in any part, the Software. You may not copy the written materials accompanying the Software. You agree to use your best efforts to see that any user of the Software licensed hereunder complies with this Agreement.
 *  5. NO WARRANTIES. FLATSTONETECH DISCLAIMS ALL WARRANTIES, BOTH EXPRESS IMPLIED, INCLUDING BUT NOT LIMITED TO, IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE WITH RESPECT TO THE SOFTWARE. THIS LIMITED WARRANTY GIVES YOU SPECIFIC LEGAL RIGHTS. YOU MAY HAVE OTHER RIGHTS WHICH VARY FROM JURISDICTION TO JURISDICTION. FlatstoneTech DOES NOT WARRANT THAT THE OPERATION OF THE SOFTWARE WILL BE UNINTERRUPTED, ERROR FREE OR MEET YOUR SPECIFIC REQUIREMENTS. THE WARRANTY SET FORTH ABOVE IS IN LIEU OF ALL OTHER EXPRESS WARRANTIES WHETHER ORAL OR WRITTEN. THE AGENTS, EMPLOYEES, DISTRIBUTORS, AND DEALERS OF FlatstoneTech ARE NOT AUTHORIZED TO MAKE MODIFICATIONS TO THIS WARRANTY, OR ADDITIONAL WARRANTIES ON BEHALF OF FlatstoneTech.
 *  Exclusive Remedies. The Software is being offered to you free of any charge. You agree that you have no remedy against FlatstoneTech, its affiliates, contractors, suppliers, and agents for loss or damage caused by any defect or failure in the Software regardless of the form of action, whether in contract, tort, includinegligence, strict liability or otherwise, with regard to the Software. Copyright and other proprietary matters will be governed by United States laws and international treaties. IN ANY CASE, FlatstoneTech SHALL NOT BE LIABLE FOR LOSS OF DATA, LOSS OF PROFITS, LOST SAVINGS, SPECIAL, INCIDENTAL, CONSEQUENTIAL, INDIRECT OR OTHER SIMILAR DAMAGES ARISING FROM BREACH OF WARRANTY, BREACH OF CONTRACT, NEGLIGENCE, OR OTHER LEGAL THEORY EVEN IF FLATSTONETECH OR ITS AGENT HAS BEEN ADVISED OF THE POSSIBILITY OF SUCH DAMAGES, OR FOR ANY CLAIM BY ANY OTHER PARTY. Some jurisdictions do not allow the exclusion or limitation of incidental or consequential damages, so the above limitation or exclusion may not apply to you.
 */

package tech.flatstone.appliedlogistics.client.gui.machines;

import com.fireball1725.firelib.guimaker.GuiMaker;
import com.fireball1725.firelib.guimaker.objects.*;
import com.fireball1725.firelib.guimaker.objects.slots.GuiSlotFuelInput;
import com.fireball1725.firelib.guimaker.objects.slots.GuiSlotFurnaceInput;
import com.fireball1725.firelib.guimaker.objects.slots.GuiSlotOutput;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import tech.flatstone.appliedlogistics.ModInfo;
import tech.flatstone.appliedlogistics.common.blocks.Blocks;
import tech.flatstone.appliedlogistics.common.tileentities.machines.TileEntityFurnace;

public class GuiFurnace {
//    private GuiProgressArrow progressArrow1;
//    private GuiProgressArrow progressArrow2;
//    private GuiProgressArrow progressArrow3;
//    private GuiProgressArrow progressArrow4;
//    private GuiProgressFire progressFire1;
//    private GuiCenteredLabel labelIntTemp;
//    private GuiImage imageTempBar;
//
//    public GuiFurnace() {
//        super(202, 174);
//    }
//
//    @Override
//    public void guiObjectClickedClient(int controlID, World world, BlockPos pos) {
//
//    }
//
//    @Override
//    public void guiObjectClickedServer(int controlID, World world, BlockPos pos) {
//
//    }
//
//    @Override
//    public void guiObjectUpdated(int controlID) {
//
//    }
//
//    @SideOnly(Side.CLIENT)
//    @Override
//    public void initGui(TileEntity tileEntity, InventoryPlayer inventoryPlayer) {
//        this.clearGuiTabs();
//
//        GuiTab tabGeneral = new GuiTab(this, "General", Blocks.BLOCK_MACHINE_FURNACE.getStack(1, tileEntity.getBlockMetadata()));
//        //tabGeneral.addGuiObject(labelInputSlotStatus);
//        // Slots...
//        tabGeneral.addGuiObject(new GuiSlot(5, 5, 1, GuiSlotFurnaceInput.class));
//        tabGeneral.addGuiObject(new GuiSlot(23, 5, 2, GuiSlotFurnaceInput.class));
//        tabGeneral.addGuiObject(new GuiSlot(95, 5, 4, GuiSlotOutput.class));
//        tabGeneral.addGuiObject(new GuiSlot(113, 5, 5, GuiSlotOutput.class));
//        tabGeneral.addGuiObject(new GuiSlot(131, 5, 6, GuiSlotOutput.class));
//        tabGeneral.addGuiObject(new GuiSlot(149, 5, 7, GuiSlotOutput.class));
//
//        tabGeneral.addGuiObject(new GuiSlot(5, 23, 8, GuiSlotFurnaceInput.class));
//        tabGeneral.addGuiObject(new GuiSlot(23, 23, 9, GuiSlotFurnaceInput.class));
//        tabGeneral.addGuiObject(new GuiSlot(95, 23, 11, GuiSlotOutput.class));
//        tabGeneral.addGuiObject(new GuiSlot(113, 23, 12, GuiSlotOutput.class));
//        tabGeneral.addGuiObject(new GuiSlot(131, 23, 13, GuiSlotOutput.class));
//        tabGeneral.addGuiObject(new GuiSlot(149, 23, 14, GuiSlotOutput.class));
//
//        tabGeneral.addGuiObject(new GuiSlot(5, 41, 15, GuiSlotFurnaceInput.class));
//        tabGeneral.addGuiObject(new GuiSlot(23, 41, 16, GuiSlotFurnaceInput.class));
//        tabGeneral.addGuiObject(new GuiSlot(95, 41, 18, GuiSlotOutput.class));
//        tabGeneral.addGuiObject(new GuiSlot(113, 41, 19, GuiSlotOutput.class));
//        tabGeneral.addGuiObject(new GuiSlot(131, 41, 20, GuiSlotOutput.class));
//        tabGeneral.addGuiObject(new GuiSlot(149, 41, 21, GuiSlotOutput.class));
//
//        tabGeneral.addGuiObject(new GuiSlot(5, 59, 22, GuiSlotFurnaceInput.class));
//        tabGeneral.addGuiObject(new GuiSlot(23, 59, 23, GuiSlotFurnaceInput.class));
//        tabGeneral.addGuiObject(new GuiSlot(95, 59, 25, GuiSlotOutput.class));
//        tabGeneral.addGuiObject(new GuiSlot(113, 59, 26, GuiSlotOutput.class));
//        tabGeneral.addGuiObject(new GuiSlot(131, 59, 27, GuiSlotOutput.class));
//        tabGeneral.addGuiObject(new GuiSlot(149, 59, 28, GuiSlotOutput.class));
//
//        tabGeneral.addGuiObject(new GuiSlot(171, 143, 26, 26, 0, GuiSlotFuelInput.class));
//
//        // Player's Inventory
//        tabGeneral.addGuiObject(new GuiLabel(8, 83, 0xFFFFFF, "Inventory"));
//        tabGeneral.addGuiObject(new GuiInventorySlots(5, 93));
//
//        // Progress Bars
//        tabGeneral.addGuiObject(progressArrow1);
//        tabGeneral.addGuiObject(progressArrow2);
//        tabGeneral.addGuiObject(progressArrow3);
//        tabGeneral.addGuiObject(progressArrow4);
//        tabGeneral.addGuiObject(progressFire1);
//
//        // Temp Bar
//        tabGeneral.addGuiObject(new GuiWindow(179, 5, 18, 106));
//        tabGeneral.addGuiObject(labelIntTemp);
//        tabGeneral.addGuiObject(new GuiLine(171, 22, 25, 1, 0xffa1a1a1));
//        tabGeneral.addGuiObject(new GuiLabel(172, 17, 0xa1a1a1, 0.5f, "8x"));
//        tabGeneral.addGuiObject(new GuiLine(171, 40, 25, 1, 0xffa1a1a1));
//        tabGeneral.addGuiObject(new GuiLabel(172, 35, 0xa1a1a1, 0.5f, "4x"));
//        tabGeneral.addGuiObject(new GuiLine(171, 58, 25, 1, 0xffa1a1a1));
//        tabGeneral.addGuiObject(new GuiLabel(172, 53, 0xa1a1a1, 0.5f, "2x"));
//        tabGeneral.addGuiObject(new GuiLine(171, 76, 25, 1, 0xffa1a1a1));
//        tabGeneral.addGuiObject(new GuiLabel(172, 71, 0xa1a1a1, 0.5f, "1x"));
//        tabGeneral.addGuiObject(imageTempBar);
//
//        this.addGuiTab(tabGeneral);
//
//        GuiTab tabAbout = new GuiTab(this, "About", 1);
//        //labelInfoArray.setText(ABOUT_LABEL);
//        //tabAbout.addGuiObject(labelInfoArray);
//        this.addGuiTab(tabAbout);
//    }
//
//    @SideOnly(Side.CLIENT)
//    @Override
//    public void drawGui(TileEntity tileEntity) {
//        TileEntityFurnace tileEntityFurnace = (TileEntityFurnace) tileEntity;
//
//        progressArrow1.setProgress(tileEntityFurnace.getSmeltProgress(0));
//        progressArrow2.setProgress(tileEntityFurnace.getSmeltProgress(1));
//        progressArrow3.setProgress(tileEntityFurnace.getSmeltProgress(2));
//        //progressArrow4.setProgress(tileEntityFurnace.getSmeltProgress(0));
//        progressFire1.setProgress(tileEntityFurnace.getFuelOffset());
//
//        labelIntTemp.setText(String.format("%d\u00B0C", tileEntityFurnace.getIntTemperature() + 20));
//
//        float colorRangeFraction = tileEntityFurnace.getMaxTemp() / (float) TileEntityFurnace.HIGHEST_MAX_TEMP;
//        int colorRange = (int) (240 * colorRangeFraction);
//        float tempFraction = tileEntityFurnace.getIntTemperature() / (float) tileEntityFurnace.getMaxTemp();
//
//        int height = (int) (104 * tempFraction);
//        int textureHeight = (int) (colorRange * tempFraction);
//
//        //new GuiImage(new ResourceLocation(ModInfo.MOD_ID, "textures/gui/tempbar.png"), 180, 6 + 104 - height, 0, 240 - textureHeight, 50, textureHeight, 16, height, 50, 240);
//        imageTempBar.setY(6 + 104 - height);
//        imageTempBar.setV(240 - textureHeight);
//        imageTempBar.setvHeight(textureHeight);
//        imageTempBar.setH(height);
//    }
//
//    @SideOnly(Side.CLIENT)
//    @Override
//    public void initControls() {
//        progressArrow1 = new GuiProgressArrow(52, 6, 0);
//        progressArrow2 = new GuiProgressArrow(52, 24, 0);
//        progressArrow3 = new GuiProgressArrow(52, 42, 0);
//        progressArrow4 = new GuiProgressArrow(52, 60, 0);
//        progressFire1 = new GuiProgressFire(176, 127, 0);
//        labelIntTemp = new GuiCenteredLabel(178, 113, 20, 0xa1a1a1, 0.5f, "?°C");
//        imageTempBar = new GuiImage(new ResourceLocation(ModInfo.MOD_ID, "textures/gui/tempbar.png"), 180, 6, 0, 0, 16, 104, 16, 104, 50, 240);
//    }
}