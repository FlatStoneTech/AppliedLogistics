package tech.flatstone.appliedlogistics.common.blocks.machines;

import com.fireball1725.firelib.guimaker.GuiMaker;
import com.fireball1725.firelib.guimaker.IImplementsGuiMaker;
import com.fireball1725.firelib.guimaker.objects.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import tech.flatstone.appliedlogistics.AppliedLogistics;
import tech.flatstone.appliedlogistics.AppliedLogisticsCreativeTabs;
import tech.flatstone.appliedlogistics.ModInfo;
import tech.flatstone.appliedlogistics.api.features.TechLevel;
import tech.flatstone.appliedlogistics.common.blocks.BlockTechBase;
import tech.flatstone.appliedlogistics.common.blocks.Blocks;
import tech.flatstone.appliedlogistics.common.tileentities.machines.TileEntityFurnace;
import tech.flatstone.appliedlogistics.common.util.LanguageHelper;
import tech.flatstone.appliedlogistics.common.util.TileHelper;

import java.util.Random;

public class BlockFurnace extends BlockTechBase implements IImplementsGuiMaker {
    private static final PropertyBool WORKING = PropertyBool.create("working");
    private GuiMaker guiMaker;
    private GuiProgressArrow progressArrow1 = new GuiProgressArrow(52, 6, 0);
    private GuiProgressArrow progressArrow2 = new GuiProgressArrow(52, 24, 0);
    private GuiProgressArrow progressArrow3 = new GuiProgressArrow(52, 42, 0);
    private GuiProgressArrow progressArrow4 = new GuiProgressArrow(52, 60, 0);
    private GuiProgressFire progressFire1 = new GuiProgressFire(176, 127, 0);
    private GuiCenteredLabel labelIntTemp = new GuiCenteredLabel(178, 113, 20, 0xa1a1a1, 0.5f, "?°C");
    private GuiImage imageTempBar = new GuiImage(new ResourceLocation(ModInfo.MOD_ID, "textures/gui/tempbar.png"), 180, 6, 0, 0, 16, 104, 16, 104, 50, 240);

    public BlockFurnace() {
        super(Material.ROCK, "machines/furnace", TechLevel.STONE_AGE, TechLevel.BRONZE_AGE, TechLevel.INDUSTRIAL_AGE, TechLevel.MECHANICAL_AGE, TechLevel.DIGITAL_AGE);
        this.setDefaultState(blockState.getBaseState().withProperty(TECHLEVEL, TechLevel.STONE_AGE).withProperty(FACING, EnumFacing.NORTH));
        this.setTileEntity(TileEntityFurnace.class);
        this.setCreativeTab(AppliedLogisticsCreativeTabs.MACHINES);
        this.setInternalName("machine_furnace");

        this.guiMaker = new GuiMaker(this, 202, 174) {
            @Override
            public void guiObjectClicked(int controlID) {

            }

            @Override
            public void guiObjectUpdated(int controlID) {

            }
        };
    }

    @Override
    public int getLightValue(IBlockState state, IBlockAccess world, BlockPos pos) {
        IBlockState blockState = getActualState(getDefaultState(), world, pos);
        return blockState.getValue(WORKING) ? 15 : 0;
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState().withProperty(TECHLEVEL, TechLevel.byMeta(meta));
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        TechLevel tier = (TechLevel) state.getValue(TECHLEVEL);
        return tier.getMeta();
    }

    @Override
    public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
        TileEntityFurnace tileEntity = TileHelper.getTileEntity(worldIn, pos, TileEntityFurnace.class);
        if (tileEntity != null && tileEntity.canBeRotated()) {
            return state.withProperty(FACING, tileEntity.getForward()).withProperty(WORKING, tileEntity.getIntTemperature() > 0);
        }
        return state.withProperty(FACING, EnumFacing.NORTH).withProperty(WORKING, false);
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, TECHLEVEL, FACING, WORKING);
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
        TileEntityFurnace tileEntity = TileHelper.getTileEntity(worldIn, pos, TileEntityFurnace.class);

        if (worldIn.isRemote)
            return true;

        guiMaker.setGuiTitle(tileEntity.hasCustomName() ? tileEntity.getCustomName() : LanguageHelper.NONE.translateMessage(tileEntity.getUnlocalizedName()));
        guiMaker.show(AppliedLogistics.instance, worldIn, playerIn, pos);

        return true;
    }

    @Override
    public void randomDisplayTick(IBlockState state, World worldIn, BlockPos pos, Random rand) {
        TileEntityFurnace tileEntity = TileHelper.getTileEntity(worldIn, pos, TileEntityFurnace.class);
        if (tileEntity == null)
            return;

        if (tileEntity.getIntTemperature() == 0)
            return;

        EnumFacing enumfacing = tileEntity.getForward();
        double d0 = (double) pos.getX() + 0.5D;
        double d1 = (double) pos.getY() + rand.nextDouble() * 6.0D / 16.0D;
        double d2 = (double) pos.getZ() + 0.5D;
        double d3 = 0.52D;
        double d4 = rand.nextDouble() * 0.6D - 0.3D;

        EnumParticleTypes particleTypes = null;
        switch (tileEntity.getBlockMetadata()) {
            case 0: // Stone
            case 1: // Bronze
                particleTypes = EnumParticleTypes.SMOKE_LARGE;
                break;
            case 2: // Mechanical
            case 3: // Industrial
                particleTypes = EnumParticleTypes.SMOKE_NORMAL;
                break;
            case 4: // Digital
            default:
                break;
        }

        if (particleTypes != null) {
            switch (enumfacing) {
                case WEST:
                    worldIn.spawnParticle(particleTypes, d0 + d3, d1 + 0.7f, d2 + d4, 0.0D, 0.0D, 0.0D, new int[0]);
                    break;
                case EAST:
                    worldIn.spawnParticle(particleTypes, d0 - d3, d1 + 0.7f, d2 + d4, 0.0D, 0.0D, 0.0D, new int[0]);
                    break;
                case NORTH:
                    worldIn.spawnParticle(particleTypes, d0 + d4, d1 + 0.7f, d2 + d3, 0.0D, 0.0D, 0.0D, new int[0]);
                    break;
                case SOUTH:
                    worldIn.spawnParticle(particleTypes, d0 + d4, d1 + 0.7f, d2 - d3, 0.0D, 0.0D, 0.0D, new int[0]);
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public int damageDropped(IBlockState state) {
        return getMetaFromState(state);
    }

    @Override
    public void drawGui(TileEntity tileEntity) {
        TileEntityFurnace tileEntityFurnace = (TileEntityFurnace)tileEntity;

        progressArrow1.setProgress(tileEntityFurnace.getSmeltProgress(0));
        progressArrow2.setProgress(tileEntityFurnace.getSmeltProgress(1));
        progressArrow3.setProgress(tileEntityFurnace.getSmeltProgress(2));
        //progressArrow4.setProgress(tileEntityFurnace.getSmeltProgress(0));
        progressFire1.setProgress(tileEntityFurnace.getFuelOffset());

        labelIntTemp.setText(String.format("%d\u00B0C", tileEntityFurnace.getIntTemperature() + 20));

        float colorRangeFraction = tileEntityFurnace.getMaxTemp() / (float) TileEntityFurnace.HIGHEST_MAX_TEMP;
        int colorRange = (int) (240 * colorRangeFraction);
        float tempFraction = tileEntityFurnace.getIntTemperature() / (float) tileEntityFurnace.getMaxTemp();

        int height = (int) (104 * tempFraction);
        int textureHeight = (int) (colorRange * tempFraction);

        //new GuiImage(new ResourceLocation(ModInfo.MOD_ID, "textures/gui/tempbar.png"), 180, 6 + 104 - height, 0, 240 - textureHeight, 50, textureHeight, 16, height, 50, 240);
        imageTempBar.setY(6 + 104 - height);
        imageTempBar.setV(240 - textureHeight);
        imageTempBar.setvHeight(textureHeight);
        imageTempBar.setH(height);
    }

    @Override
    public void initGui(TileEntity tileEntity, InventoryPlayer inventoryPlayer) {
        guiMaker.clearGuiTabs();

        GuiTab tabGeneral = new GuiTab(this.guiMaker, "General", Blocks.BLOCK_MACHINE_FURNACE.getStack(1, tileEntity.getBlockMetadata()));
        //tabGeneral.addGuiObject(labelInputSlotStatus);
        // Slots...
        tabGeneral.addGuiObject(new GuiSlot(5, 5, 1));
        tabGeneral.addGuiObject(new GuiSlot(23, 5, 2));
        tabGeneral.addGuiObject(new GuiSlot(95, 5, 4));
        tabGeneral.addGuiObject(new GuiSlot(113, 5, 5));
        tabGeneral.addGuiObject(new GuiSlot(131, 5, 6));
        tabGeneral.addGuiObject(new GuiSlot(149, 5, 7));

        tabGeneral.addGuiObject(new GuiSlot(5, 23, 8));
        tabGeneral.addGuiObject(new GuiSlot(23, 23, 9));
        tabGeneral.addGuiObject(new GuiSlot(95, 23, 11));
        tabGeneral.addGuiObject(new GuiSlot(113, 23, 12));
        tabGeneral.addGuiObject(new GuiSlot(131, 23, 13));
        tabGeneral.addGuiObject(new GuiSlot(149, 23, 14));

        tabGeneral.addGuiObject(new GuiSlot(5, 41, 15));
        tabGeneral.addGuiObject(new GuiSlot(23, 41, 16));
        tabGeneral.addGuiObject(new GuiSlot(95, 41, 18));
        tabGeneral.addGuiObject(new GuiSlot(113, 41, 19));
        tabGeneral.addGuiObject(new GuiSlot(131, 41, 20));
        tabGeneral.addGuiObject(new GuiSlot(149, 41, 21));

        tabGeneral.addGuiObject(new GuiSlot(5, 59, 22));
        tabGeneral.addGuiObject(new GuiSlot(23, 59, 23));
        tabGeneral.addGuiObject(new GuiSlot(95, 59, 25));
        tabGeneral.addGuiObject(new GuiSlot(113, 59, 26));
        tabGeneral.addGuiObject(new GuiSlot(131, 59, 27));
        tabGeneral.addGuiObject(new GuiSlot(149, 59, 28));

        tabGeneral.addGuiObject(new GuiSlot(171, 143, 26, 26, 0));

        // Player's Inventory
        tabGeneral.addGuiObject(new GuiLabel(8, 83, 0xFFFFFF, "Inventory"));
        tabGeneral.addGuiObject(new GuiInventorySlots(5, 93));

        // Progress Bars
        tabGeneral.addGuiObject(progressArrow1);
        tabGeneral.addGuiObject(progressArrow2);
        tabGeneral.addGuiObject(progressArrow3);
        tabGeneral.addGuiObject(progressArrow4);
        tabGeneral.addGuiObject(progressFire1);

        // Temp Bar
        tabGeneral.addGuiObject(new GuiWindow(179, 5, 18, 106));
        tabGeneral.addGuiObject(labelIntTemp);
        tabGeneral.addGuiObject(new GuiLine(171, 22, 25, 1, 0xffa1a1a1));
        tabGeneral.addGuiObject(new GuiLabel(172, 17, 0xa1a1a1, 0.5f, "8x"));
        tabGeneral.addGuiObject(new GuiLine(171, 40, 25, 1, 0xffa1a1a1));
        tabGeneral.addGuiObject(new GuiLabel(172, 35, 0xa1a1a1, 0.5f, "4x"));
        tabGeneral.addGuiObject(new GuiLine(171, 58, 25, 1, 0xffa1a1a1));
        tabGeneral.addGuiObject(new GuiLabel(172, 53, 0xa1a1a1, 0.5f, "2x"));
        tabGeneral.addGuiObject(new GuiLine(171, 76, 25, 1, 0xffa1a1a1));
        tabGeneral.addGuiObject(new GuiLabel(172, 71, 0xa1a1a1, 0.5f, "1x"));
        tabGeneral.addGuiObject(imageTempBar);

        guiMaker.addGuiTab(tabGeneral);

        GuiTab tabAbout = new GuiTab(this.guiMaker, "About", 1);
        //labelInfoArray.setText(ABOUT_LABEL);
        //tabAbout.addGuiObject(labelInfoArray);
        guiMaker.addGuiTab(tabAbout);

    }
}
