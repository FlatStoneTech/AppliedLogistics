package tech.flatstone.appliedlogistics.common.blocks.machines;

import com.fireball1725.corelib.guimaker.GuiMaker;
import com.fireball1725.corelib.guimaker.IImplementsGuiMaker;
import com.fireball1725.corelib.guimaker.objects.GuiInventorySlots;
import com.fireball1725.corelib.guimaker.objects.GuiLabel;
import com.fireball1725.corelib.guimaker.objects.GuiSlot;
import com.fireball1725.corelib.guimaker.objects.GuiTab;
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
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import tech.flatstone.appliedlogistics.AppliedLogistics;
import tech.flatstone.appliedlogistics.AppliedLogisticsCreativeTabs;
import tech.flatstone.appliedlogistics.api.features.TechLevel;
import tech.flatstone.appliedlogistics.common.blocks.BlockTechBase;
import tech.flatstone.appliedlogistics.common.blocks.Blocks;
import tech.flatstone.appliedlogistics.common.items.Items;
import tech.flatstone.appliedlogistics.common.tileentities.machines.TileEntityFurnace;
import tech.flatstone.appliedlogistics.common.util.LanguageHelper;
import tech.flatstone.appliedlogistics.common.util.TileHelper;

import java.util.Random;

public class BlockFurnace extends BlockTechBase implements IImplementsGuiMaker {
    private static final PropertyBool WORKING = PropertyBool.create("working");
    private GuiMaker guiMaker = new GuiMaker(this, 220, 174);

    public BlockFurnace() {
        super(Material.ROCK, "machines/furnace", TechLevel.STONE_AGE, TechLevel.BRONZE_AGE, TechLevel.INDUSTRIAL_AGE, TechLevel.MECHANICAL_AGE, TechLevel.DIGITAL_AGE);
        this.setDefaultState(blockState.getBaseState().withProperty(TECHLEVEL, TechLevel.STONE_AGE).withProperty(FACING, EnumFacing.NORTH));
        this.setTileEntity(TileEntityFurnace.class);
        this.setCreativeTab(AppliedLogisticsCreativeTabs.MACHINES);
        this.setInternalName("machine_furnace");
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

    }

    @Override
    public void initGui(TileEntity tileEntity, InventoryPlayer inventoryPlayer) {
        guiMaker.clearGuiTabs();

        GuiTab tabGeneral = new GuiTab("General", Blocks.BLOCK_MACHINE_FURNACE.getStack(1, tileEntity.getBlockMetadata()));
        //tabGeneral.addGuiObject(labelInputSlotStatus);
        // Slots...
        tabGeneral.addGuiObject(new GuiSlot(5, 5, 0));
        tabGeneral.addGuiObject(new GuiSlot(23, 5, 0));
        tabGeneral.addGuiObject(new GuiSlot(95, 5, 0));
        tabGeneral.addGuiObject(new GuiSlot(113, 5, 0));
        tabGeneral.addGuiObject(new GuiSlot(131, 5, 0));
        tabGeneral.addGuiObject(new GuiSlot(149, 5, 0));

        tabGeneral.addGuiObject(new GuiSlot(5, 23, 0));
        tabGeneral.addGuiObject(new GuiSlot(23, 23, 0));
        tabGeneral.addGuiObject(new GuiSlot(95, 23, 0));
        tabGeneral.addGuiObject(new GuiSlot(113, 23, 0));
        tabGeneral.addGuiObject(new GuiSlot(131, 23, 0));
        tabGeneral.addGuiObject(new GuiSlot(149, 23, 0));

        tabGeneral.addGuiObject(new GuiSlot(5, 41, 0));
        tabGeneral.addGuiObject(new GuiSlot(23, 41, 0));
        tabGeneral.addGuiObject(new GuiSlot(95, 41, 0));
        tabGeneral.addGuiObject(new GuiSlot(113, 41, 0));
        tabGeneral.addGuiObject(new GuiSlot(131, 41, 0));
        tabGeneral.addGuiObject(new GuiSlot(149, 41, 0));

        tabGeneral.addGuiObject(new GuiSlot(5, 59, 0));
        tabGeneral.addGuiObject(new GuiSlot(23, 59, 0));
        tabGeneral.addGuiObject(new GuiSlot(95, 59, 0));
        tabGeneral.addGuiObject(new GuiSlot(113, 59, 0));
        tabGeneral.addGuiObject(new GuiSlot(131, 59, 0));
        tabGeneral.addGuiObject(new GuiSlot(149, 59, 0));

        // Player's Inventory
        tabGeneral.addGuiObject(new GuiLabel(8, 83, 0xFFFFFF, "Inventory"));
        tabGeneral.addGuiObject(new GuiInventorySlots(5, 93));
        guiMaker.addGuiTab(tabGeneral);

        GuiTab tabAbout = new GuiTab("About", 1);
        //labelInfoArray.setText(ABOUT_LABEL);
        //tabAbout.addGuiObject(labelInfoArray);
        guiMaker.addGuiTab(tabAbout);

    }
}
