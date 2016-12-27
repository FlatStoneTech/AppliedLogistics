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

package tech.flatstone.appliedlogistics.common.blocks.misc;

import com.fireball1725.firelib.guimaker.GuiMaker;
import com.fireball1725.firelib.guimaker.IImplementsGuiMaker;
import com.fireball1725.firelib.guimaker.objects.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.apache.commons.lang3.time.DurationFormatUtils;
import tech.flatstone.appliedlogistics.AppliedLogistics;
import tech.flatstone.appliedlogistics.AppliedLogisticsCreativeTabs;
import tech.flatstone.appliedlogistics.ModInfo;
import tech.flatstone.appliedlogistics.common.blocks.BlockTileBase;
import tech.flatstone.appliedlogistics.common.blocks.Blocks;
import tech.flatstone.appliedlogistics.common.slots.GuiSlotBlankBookInput;
import tech.flatstone.appliedlogistics.common.slots.GuiSlotBlankPlanInput;
import tech.flatstone.appliedlogistics.common.tileentities.misc.TileEntityPatternStamper;
import tech.flatstone.appliedlogistics.common.util.*;

import java.util.ArrayList;
import java.util.List;

public class BlockPatternStamper extends BlockTileBase implements IProvideRecipe, IImplementsGuiMaker {
    public static final ResourceLocation RESOURCE_BUILD_TIME = new ResourceLocation(ModInfo.MOD_ID, "textures/icons/stopwatch.png");
    public static final ResourceLocation RESOURCE_XP_COST = new ResourceLocation(ModInfo.MOD_ID, "textures/icons/xp_orb.png");

    private GuiMaker guiMaker;

    private GuiLabel labelSlotDetails = new GuiLabel(36, 7, 0xd5d5d5, "");
    private GuiButton buttonPrevious = new GuiButton(1, 4, 34, 16, "<");
    private GuiButton buttonNext = new GuiButton(2, 120, 34, 16, ">");
    private GuiButton buttonStamp = new GuiButton(3, 138, 34, 52, "Stamp");
    private GuiScrollBox scrollBoxOptions;
    private GuiScrollBox scrollBoxMaterials;
    private List<GuiObject> guiObjects = new ArrayList<>();
    private List<GuiObject> guiMaterialsList= new ArrayList<>();

    private GuiCenteredLabel labelMachineName = new GuiCenteredLabel(22, 38, 96, 0xd5d5d5, "");

    private GuiDrawSimpleImage imageBuildTime = new GuiDrawSimpleImage(RESOURCE_BUILD_TIME, 6, 130);
    private GuiDrawSimpleImage imageXPCost = new GuiDrawSimpleImage(RESOURCE_XP_COST, 90, 130);
    private GuiLabel labelCreativeMode = new GuiLabel(150, 132, 0xffffff, 0.5f, "");

    public BlockPatternStamper() {
        super(Material.ROCK, "misc/blockPatternStamper");
        this.setDefaultState(blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
        this.setTileEntity(TileEntityPatternStamper.class);
        this.setCreativeTab(AppliedLogisticsCreativeTabs.MACHINES);
        this.setInternalName("pattern_stamper");

        guiMaker = new GuiMaker(this, 194, 232) {
            @Override
            public void guiObjectClicked(int controlID, World world, BlockPos pos) {
                LogHelper.info(">>> Click: " + controlID);

                TileEntityPatternStamper tileEntity = TileHelper.getTileEntity(world, pos, TileEntityPatternStamper.class);
                if (tileEntity == null)
                    return;

                switch (controlID) {
                    case 0:
                    default:
                        if (controlID >= 100)
                            if (Platform.isClient())
                                handleCheckBoxClick(controlID, world, pos);

                        break;
                    case 1: // Previous
                        tileEntity.planPrev();
                        tileEntity.updateCheckBoxes();
                        break;
                    case 2: // Next
                        tileEntity.planNext();
                        tileEntity.updateCheckBoxes();
                        break;
                    case 3: // Stamp

                        break;
                    case 4: // Save Book
                        if (Platform.isClient())
                            tileEntity.saveBook();
                        break;
                }
            }

            @Override
            public void guiObjectUpdated(int controlID) {

            }
        };

        this.scrollBoxOptions = new GuiScrollBox(this.guiMaker, 4, 52, 186, 75);
        this.scrollBoxMaterials = new GuiScrollBox(this.guiMaker, 4, 24, 186, 103);
        this.imageXPCost.setScale(0.5f);
        this.imageBuildTime.setScale(0.5f);
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        TileEntityPatternStamper tileEntity = TileHelper.getTileEntity(worldIn, pos, TileEntityPatternStamper.class);

        if (worldIn.isRemote)
            return true;

        guiMaker.setGuiTitle(tileEntity.hasCustomName() ? tileEntity.getCustomName() : LanguageHelper.NONE.translateMessage(tileEntity.getUnlocalizedName()));
        guiMaker.show(AppliedLogistics.instance, worldIn, playerIn, pos);

        return true;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void drawGui(TileEntity tileEntity) {
        buttonNext.setDisabled(true);
        buttonPrevious.setDisabled(true);
        buttonStamp.setDisabled(true);

        TileEntityPatternStamper tileEntityPatternStamper = (TileEntityPatternStamper) tileEntity;

        if (tileEntityPatternStamper.getPlanTechLevel() != null)
            labelSlotDetails.setText(LanguageHelper.ITEM.translateMessage(String.format("plan_blank.%s.name", tileEntityPatternStamper.getPlanTechLevel().getName())));
        else
            labelSlotDetails.setText(LanguageHelper.MESSAGE.translateMessage("plan.insert"));

        if (tileEntityPatternStamper.isPlanValid()) {
            buttonNext.setDisabled(false);
            buttonPrevious.setDisabled(false);
            buttonStamp.setDisabled(false);
        }

        imageBuildTime.setLabelText(String.format("Total Build Time: %s",
                DurationFormatUtils.formatDuration(tileEntityPatternStamper.getRecipeTimeToBuild() * 1000, "HH:mm:ss")));
        imageXPCost.setLabelText(String.format("Total XP Cost: %s%dL%s",
                TextFormatting.GREEN, tileEntityPatternStamper.getRecipeXPRequired(), TextFormatting.RESET));

        labelCreativeMode.setText("");
        if (tileEntityPatternStamper.isCreativeMode())
            labelCreativeMode.setText(String.format("%sCreative Mode%s", TextFormatting.DARK_PURPLE, TextFormatting.RESET));

        labelMachineName.setText("");
        if (tileEntityPatternStamper.getSelectedPlanMachine() != null) {
            labelMachineName.setText(tileEntityPatternStamper.getSelectedPlanMachine().getName());
        }

    }

    @SideOnly(Side.CLIENT)
    @Override
    public void initGui(TileEntity tileEntity, InventoryPlayer inventoryPlayer) {
        guiMaker.clearGuiTabs();

        GuiTab tabMachine = new GuiTab(this.guiMaker, "Pattern Stamper", Blocks.BLOCK_PATTERN_STAMPER.getStack());
        GuiTab tabExport = new GuiTab(this.guiMaker, "Total Materials", new ItemStack(Items.BOOK));
        GuiTab tabAbout = new GuiTab(this.guiMaker, "About", 1);

        tabMachine.addGuiObject(new GuiSlot(4, 4, 28, 28, 0, GuiSlotBlankPlanInput.class));
        tabMachine.addGuiObject(labelSlotDetails);

        tabMachine.addGuiObject(new GuiWindow(34, 18, 156, 14)); // Progress Bar (for weight)...

        tabMachine.addGuiObject(buttonNext);
        tabMachine.addGuiObject(buttonPrevious);
        tabMachine.addGuiObject(buttonStamp);

        tabMachine.addGuiObject(labelMachineName);

        tabMachine.addGuiObject(new GuiLine(164, 14, 1, 18, 0xffa1a1a1));
        tabMachine.addGuiObject(new GuiLabel(158, 14, 0xa1a1a1, 0.5f, "Ok"));
        tabMachine.addGuiObject(new GuiLabel(166, 14, 0xa1a1a1, 0.5f, "Error"));

        tabMachine.addGuiObject(scrollBoxOptions);
        tabExport.addGuiObject(scrollBoxMaterials);

        // Bottom info bar
        tabMachine.addGuiObject(imageBuildTime);
        tabMachine.addGuiObject(imageXPCost);
        tabMachine.addGuiObject(labelCreativeMode);
        tabExport.addGuiObject(imageBuildTime);
        tabExport.addGuiObject(imageXPCost);
        tabExport.addGuiObject(labelCreativeMode);

        // Player's Inventory
        tabMachine.addGuiObject(new GuiLabel(8, 142, 0xd5d5d5, "Inventory"));
        tabMachine.addGuiObject(new GuiInventorySlots(4, 152));
        tabExport.addGuiObject(new GuiLabel(8, 142, 0xd5d5d5, "Inventory"));
        tabExport.addGuiObject(new GuiInventorySlots(4, 152));

        // Book and Quill Slot
        tabExport.addGuiObject(new GuiSlot(4, 4, 1, GuiSlotBlankBookInput.class));

        tabExport.addGuiObject(new GuiButton(4, 140, 4, 50, 18, "Save"));

        tabExport.addGuiObject(new GuiLabel(24, 10, 0xffffff, "Export Materials List"));

        guiMaker.addGuiTab(tabMachine);
        guiMaker.addGuiTab(tabExport);
        guiMaker.addGuiTab(tabAbout);
    }

    @Override
    public void RegisterRecipes() {

    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState();
    }

    @Override
    public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
        TileEntityPatternStamper tileEntity = TileHelper.getTileEntity(worldIn, pos, TileEntityPatternStamper.class);
        if (tileEntity != null)
            return state.withProperty(FACING, tileEntity.getForward());
        return state.withProperty(FACING, EnumFacing.NORTH);
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return 0;
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, FACING);
    }

    @Override
    public void registerBlockRenderer() {

    }

    @Override
    public void registerBlockItemRenderer() {

    }

    @SideOnly(Side.CLIENT)
    public void drawCheckBoxes(List<GuiObject> guiObjects, int maxScrollY) {
        this.guiObjects = guiObjects;
        this.scrollBoxOptions.clearObjects();

        for (GuiObject guiObject : this.guiObjects) {
            this.scrollBoxOptions.addGuiObject(guiObject);
        }

        this.scrollBoxOptions.setMaxScrollY(maxScrollY);
        this.scrollBoxOptions.initGui();
    }

    @SideOnly(Side.CLIENT)
    public void drawMaterialsList(List<GuiObject> guiObjects, int maxScrollY) {
        this.guiMaterialsList = guiObjects;
        this.scrollBoxMaterials.clearObjects();

        for (GuiObject guiObject : this.guiMaterialsList) {
            this.scrollBoxMaterials.addGuiObject(guiObject);
        }

        this.scrollBoxMaterials.setMaxScrollY(maxScrollY);
        this.scrollBoxMaterials.initGui();
    }

    @SideOnly(Side.CLIENT)
    public void handleCheckBoxClick(int checkBox, World world, BlockPos pos) {
        TileEntityPatternStamper tileEntity = TileHelper.getTileEntity(world, pos, TileEntityPatternStamper.class);
        if (tileEntity == null)
            return;

        int buttonID = checkBox - 100;

        GuiObject guiObject = this.guiObjects.get(buttonID);

        if (guiObject instanceof GuiCheckBox) {
            GuiCheckBox guiCheckBox = (GuiCheckBox)this.guiObjects.get(buttonID);
            guiCheckBox.setSelected(!guiCheckBox.isSelected());
            tileEntity.updateCheckBoxData(this.guiObjects);
        }
    }

}
