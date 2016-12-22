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

package tech.flatstone.appliedlogistics.common.blocks.misc;

import com.fireball1725.firelib.guimaker.GuiMaker;
import com.fireball1725.firelib.guimaker.GuiMakerStatusIcon;
import com.fireball1725.firelib.guimaker.objects.*;
import com.fireball1725.firelib.guimaker.IImplementsGuiMaker;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import tech.flatstone.appliedlogistics.AppliedLogistics;
import tech.flatstone.appliedlogistics.AppliedLogisticsCreativeTabs;
import tech.flatstone.appliedlogistics.api.features.TechLevel;
import tech.flatstone.appliedlogistics.common.blocks.BlockTechBase;
import tech.flatstone.appliedlogistics.common.blocks.Blocks;
import tech.flatstone.appliedlogistics.common.items.Items;
import tech.flatstone.appliedlogistics.common.tileentities.misc.TileEntityBuilder;
import tech.flatstone.appliedlogistics.common.util.*;

public class BlockBuilder extends BlockTechBase implements IProvideRecipe, IImplementsGuiMaker {
    public static final PropertyEnum TECHLEVEL = PropertyEnum.create("tech", TechLevel.class);
    private GuiMaker guiMaker;
    private GuiLabel labelInputSlotStatus = new GuiLabel(26, 13, 0x00FF00, "");
    private GuiCenteredLabel labelInfoArray = new GuiCenteredLabel(0, 6, 242, 0xffffff);
    private GuiCheckBox checkBox1 = new GuiCheckBox(10, 70, 100, true);
    private GuiCheckBox checkBox2 = new GuiCheckBox(11, 10, 40, false);
    private GuiCheckBox checkBox3 = new GuiCheckBox(12, 30, 40, true);
    private GuiCheckBox checkBox4 = new GuiCheckBox(13, 50, 40, false);
    private GuiCheckBox checkBox5 = new GuiCheckBox(14, 50, 10, false);
    private GuiButton guiButton1 = new GuiButton(1, 10, 70, 140, "Hello World...");

    private static final String ABOUT_LABEL = "§l§nAbout the Builder§r\n\nThe builder is Applied Logistic's main tool for building all Applied Logistic items.\n\nThis is a pretty cool block that does a lot of things...";

    public BlockBuilder() {
        super(Material.ROCK, "misc/builder", TechLevel.all());
        this.setDefaultState(blockState.getBaseState().withProperty(TECHLEVEL, TechLevel.STONE_AGE).withProperty(FACING, EnumFacing.NORTH));
        this.setTileEntity(TileEntityBuilder.class);
        this.setCreativeTab(AppliedLogisticsCreativeTabs.MACHINES);
        this.setInternalName("builder");

        guiMaker = new GuiMaker(this, 256, 220) {
            @Override
            public void guiObjectClicked(int controlID) {
                switch(controlID) {
                    default:
                        LogHelper.info(">>> " + controlID);
                        break;
                    case 10:
                        checkBox1.setSelected(!checkBox1.isSelected());
                        break;
                    case 11:
                        checkBox2.setSelected(!checkBox2.isSelected());
                        checkBox1.setDisabled(checkBox2.isSelected());
                        guiButton1.setDisabled(checkBox2.isSelected());
                        break;
                    case 12:
                        checkBox3.setSelected(!checkBox3.isSelected());
                        guiButton1.setVisible(checkBox3.isSelected());
                        checkBox1.setVisible(checkBox3.isSelected());
                        break;
                    case 13:
                        checkBox4.setSelected(!checkBox4.isSelected());
                        guiButton1.setSelected(checkBox4.isSelected());
                        break;
                    case 14:
                        checkBox5.setSelected(!checkBox5.isSelected());
                }
            }

            @Override
            public void guiObjectUpdated(int controlID) {

            }
        };
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        TileEntityBuilder tileEntity = TileHelper.getTileEntity(worldIn, pos, TileEntityBuilder.class);
        if (hitY == 1 &&
                tileEntity != null &&
                tileEntity.canAttachCrank() &&
                playerIn.getHeldItemMainhand() != null &&
                playerIn.getHeldItemMainhand().getItem() == Blocks.BLOCK_MISC_CRANK.getStack().getItem() &&
                worldIn.isAirBlock(pos.up())
                )
            return false;

        if (!worldIn.isRemote) {
            worldIn.addBlockEvent(pos, this, EnumEventTypes.PLAN_SLOT_UPDATE.ordinal(), 0);
            playerIn.openGui(AppliedLogistics.instance, 0, worldIn, pos.getX(), pos.getY(), pos.getZ());
        }

        worldIn.addBlockEvent(pos, this, EnumEventTypes.PLAN_SLOT_UPDATE.ordinal(), 0);

        guiMaker.setGuiTitle(tileEntity.hasCustomName() ? tileEntity.getCustomName() : LanguageHelper.NONE.translateMessage(tileEntity.getUnlocalizedName()));
        guiMaker.show(AppliedLogistics.instance, worldIn, playerIn, pos);

        return true;
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState().withProperty(TECHLEVEL, TechLevel.byMeta(meta));
    }

    @Override
    public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
        TileEntityBuilder tileEntity = TileHelper.getTileEntity(worldIn, pos, TileEntityBuilder.class);
        if (tileEntity != null) {
            return state.withProperty(FACING, tileEntity.getForward());
        }
        return state.withProperty(FACING, EnumFacing.NORTH);
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        TechLevel tier = (TechLevel) state.getValue(TECHLEVEL);
        return (tier.getMeta());
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, TECHLEVEL, FACING);
    }

    @Override
    public int damageDropped(IBlockState state) {
        return getMetaFromState(state);
    }

    @Override
    public void RegisterRecipes() {
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(this, 1, 0),
                "cwc",
                "wgw",
                "cxc",
                'c', OreDictionary.getOres("craftingTableWood").isEmpty() ? new ItemStack(net.minecraft.init.Blocks.CRAFTING_TABLE) : "craftingTableWood",
                'w', "logWood",
                'g', "gearStone",
                'x', "chestWood"
        ));
    }

    @Override
    public boolean hasComparatorInputOverride(IBlockState state) {
        //todo: fix this later
        return true;
    }

    @Override
    public void drawGui(TileEntity tileEntity) {
        if (!(tileEntity instanceof TileEntityBuilder))
            return;
        TileEntityBuilder tileEntityBuilder = (TileEntityBuilder)tileEntity;

        ItemStack itemPlan = tileEntityBuilder.getPlanItem();

        if (tileEntityBuilder.getPlanItem() == null) {
            labelInputSlotStatus.setText(TextFormatting.RED + LanguageHelper.MESSAGE.translateMessage("plan.insert"));
        } else if (itemPlan != null && tileEntityBuilder.isUpgradeMode()) {
            labelInputSlotStatus.setText(LanguageHelper.LABEL.translateMessage("upgrade") + " " + LanguageHelper.NONE.translateMessage(itemPlan.getUnlocalizedName() + ".name"));
        } else if (itemPlan != null) {
            labelInputSlotStatus.setText(LanguageHelper.NONE.translateMessage(itemPlan.getUnlocalizedName() + ".name"));
        }


        if (guiMaker.getSelectedTab() == 0)
            guiMaker.setGuiMakerStatusIcon(GuiMakerStatusIcon.EMPTY);

        if (guiMaker.getSelectedTab() == 1)
            guiMaker.setGuiMakerStatusIcon(GuiMakerStatusIcon.GOOD);

        if (guiMaker.getSelectedTab() == 2)
            guiMaker.setGuiMakerStatusIcon(GuiMakerStatusIcon.WARN);

        if (guiMaker.getSelectedTab() == 3)
            guiMaker.setGuiMakerStatusIcon(GuiMakerStatusIcon.FAIL);

    }

    @Override
    public void initGui(TileEntity tileEntity, InventoryPlayer inventoryPlayer) {
        guiMaker.clearGuiTabs();

        GuiTab tabGeneral = new GuiTab(this.guiMaker, "General", Items.ITEM_MATERIAL_GEAR.getStack(1, 2));
        tabGeneral.addGuiObject(labelInputSlotStatus);
        tabGeneral.addGuiObject(new GuiSlot(5, 5, 0, Slot.class));
        tabGeneral.addGuiObject(new GuiInventorySlots(5, 139));
        tabGeneral.addGuiObject(checkBox2);
        tabGeneral.addGuiObject(checkBox3);
        tabGeneral.addGuiObject(checkBox4);
        tabGeneral.addGuiObject(guiButton1);
        tabGeneral.addGuiObject(new GuiButton(1, 10, 95, 40, "Hello World..."));
        tabGeneral.addGuiObject(checkBox1);
        tabGeneral.addGuiObject(new GuiProgressArrow(10, 115, 50));
        tabGeneral.addGuiObject(new GuiProgressFire(40, 115, 25));
        tabGeneral.addGuiObject(new GuiSlot(100, 100, 30, 30, 0, Slot.class));
        guiMaker.addGuiTab(tabGeneral);

        GuiTab tabAbout = new GuiTab(this.guiMaker, "About", 1);
        GuiScrollBox tabAboutScrollBox = new GuiScrollBox(this.guiMaker, 3, 3, 250, 214, false);
        labelInfoArray.setText(ABOUT_LABEL);
        tabAboutScrollBox.addGuiObject(labelInfoArray);
        tabAbout.addGuiObject(tabAboutScrollBox);
        guiMaker.addGuiTab(tabAbout);

        GuiTab tabTest = new GuiTab(this.guiMaker, "Test", 1);
        GuiScrollBox scrollBox = new GuiScrollBox(this.guiMaker, 20, 20, 150, 150);
        scrollBox.addGuiObject(new GuiWindow(0, 0, 18, 298));
        scrollBox.setMaxScrollY(300);
        scrollBox.addGuiObject(new GuiLabel(0, 100, 0xFFFFFF, "Hello world, this is a label..."));
        scrollBox.addGuiObject(checkBox5);
        scrollBox.addGuiObject(new GuiCenteredLabel(1, 1, 140, 0xFFFFFF, "Hello world, this is a test label to see if this works properly, who know if it will, this again is just a test to see what happens, I just needed a really long label to test all of this, so yay for really long labels, ok i think i am done here, good bye everyone :D"));

        tabTest.addGuiObject(scrollBox);
        guiMaker.addGuiTab(tabTest);
    }

//    @Override
//    public int getComparatorInputOverride(IBlockState blockState, World worldIn, BlockPos pos) {
//        TileEntityBuilder tileEntity = TileHelper.getTileEntity(worldIn, pos, TileEntityBuilder.class);
//
//        return tileEntity.getComparatorOutput();
//    }

//    @Override
//    public void onNeighborBlockChange(World worldIn, BlockPos pos, IBlockState state, Block neighborBlock) {
//        TileEntityBuilder tileEntity = TileHelper.getTileEntity(worldIn, pos, TileEntityBuilder.class);
//        if (tileEntity == null)
//            return;
//
//        tileEntity.setBadCrankCount(0);
//    }
}
