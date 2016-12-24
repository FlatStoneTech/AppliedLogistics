package tech.flatstone.appliedlogistics.common.blocks.misc;

import com.fireball1725.firelib.guimaker.GuiMaker;
import com.fireball1725.firelib.guimaker.IImplementsGuiMaker;
import com.fireball1725.firelib.guimaker.objects.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import tech.flatstone.appliedlogistics.AppliedLogistics;
import tech.flatstone.appliedlogistics.AppliedLogisticsCreativeTabs;
import tech.flatstone.appliedlogistics.common.blocks.BlockTileBase;
import tech.flatstone.appliedlogistics.common.blocks.Blocks;
import tech.flatstone.appliedlogistics.common.slots.GuiSlotBlankPlanInput;
import tech.flatstone.appliedlogistics.common.tileentities.misc.TileEntityPatternStamper;
import tech.flatstone.appliedlogistics.common.util.IProvideRecipe;
import tech.flatstone.appliedlogistics.common.util.LanguageHelper;
import tech.flatstone.appliedlogistics.common.util.TileHelper;

public class BlockPatternStamper extends BlockTileBase implements IProvideRecipe, IImplementsGuiMaker {
    private GuiMaker guiMaker;

    private GuiLabel labelSlotDetails = new GuiLabel(36, 7, 0xd5d5d5, "Hello World...");
    private GuiButton buttonPrevious = new GuiButton(-999, 4, 34, 16, "<");
    private GuiButton buttonNext = new GuiButton(-999, 120, 34, 16, ">");
    private GuiButton buttonStamp = new GuiButton(-999, 138, 34, 52, "Stamp");

    private GuiCenteredLabel labelMachineName = new GuiCenteredLabel(22, 38, 96, 0xd5d5d5, "Hello World...");

    private GuiLabel labelStats = new GuiLabel(5, 134, 0xd5d5d5, 0.5f, "Total Building Time: 0:00:00 | Total Experence Cost: 0L");

    public BlockPatternStamper() {
        super(Material.ROCK, "misc/blockPatternStamper");
        this.setDefaultState(blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
        this.setTileEntity(TileEntityPatternStamper.class);
        this.setCreativeTab(AppliedLogisticsCreativeTabs.MACHINES);
        this.setInternalName("pattern_stamper");

        guiMaker = new GuiMaker(this, 194, 232) {
            @Override
            public void guiObjectClicked(int controlID) {

            }

            @Override
            public void guiObjectUpdated(int controlID) {

            }
        };
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

        String stats = String.format("Total Building Time: 0:00:00 | Total Experience Cost: %s0L%s", TextFormatting.GREEN, TextFormatting.RESET);

        if (tileEntityPatternStamper.isCreativeMode())
            stats += String.format(" | %sCreative Mode", TextFormatting.DARK_PURPLE);

        labelStats.setText(stats);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void initGui(TileEntity tileEntity, InventoryPlayer inventoryPlayer) {
        guiMaker.clearGuiTabs();

        GuiTab tabMachine = new GuiTab(this.guiMaker, "Pattern Stamper", Blocks.BLOCK_PATTERN_STAMPER.getStack());
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

        tabMachine.addGuiObject(new GuiScrollBox(this.guiMaker, 4, 52, 186, 80));

        tabMachine.addGuiObject(labelStats);

        tabMachine.addGuiObject(new GuiLabel(8, 142, 0xd5d5d5, "Inventory"));
        tabMachine.addGuiObject(new GuiInventorySlots(4, 152));

        guiMaker.addGuiTab(tabMachine);
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
}
