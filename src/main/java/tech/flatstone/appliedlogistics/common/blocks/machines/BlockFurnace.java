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

package tech.flatstone.appliedlogistics.common.blocks.machines;

import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import tech.flatstone.appliedlogistics.AppliedLogistics;
import tech.flatstone.appliedlogistics.AppliedLogisticsCreativeTabs;
import tech.flatstone.appliedlogistics.api.features.TechLevel;
import tech.flatstone.appliedlogistics.client.gui.machines.GuiFurnace;
import tech.flatstone.appliedlogistics.common.blocks.BlockTechBase;
import tech.flatstone.appliedlogistics.common.blocks.Blocks;
import tech.flatstone.appliedlogistics.common.items.Items;
import tech.flatstone.appliedlogistics.common.plans.PlanComponent;
import tech.flatstone.appliedlogistics.common.plans.PlanMachine;
import tech.flatstone.appliedlogistics.common.plans.PlanRegistry;
import tech.flatstone.appliedlogistics.common.tileentities.machines.TileEntityFurnace;
import tech.flatstone.appliedlogistics.common.util.EnumOres;
import tech.flatstone.appliedlogistics.common.util.IProvideRecipe;
import tech.flatstone.appliedlogistics.common.util.LanguageHelper;
import tech.flatstone.appliedlogistics.common.util.TileHelper;

import java.util.Arrays;
import java.util.Random;

public class BlockFurnace extends BlockTechBase implements IProvideRecipe {
    private static final PropertyBool WORKING = PropertyBool.create("working");
    private GuiFurnace guiFurnace = new GuiFurnace();

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

    @SideOnly(Side.CLIENT)
    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        TileEntityFurnace tileEntity = TileHelper.getTileEntity(worldIn, pos, TileEntityFurnace.class);

        if (worldIn.isRemote)
            return true;

        guiFurnace.setGuiTitle(tileEntity.hasCustomName() ? tileEntity.getCustomName() : LanguageHelper.NONE.translateMessage(tileEntity.getUnlocalizedName()));
        guiFurnace.show(AppliedLogistics.instance, worldIn, playerIn, pos);

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
                    worldIn.spawnParticle(particleTypes, d0 + d3, d1 + 0.7f, d2 + d4, 0.0D, 0.0D, 0.0D);
                    break;
                case EAST:
                    worldIn.spawnParticle(particleTypes, d0 - d3, d1 + 0.7f, d2 + d4, 0.0D, 0.0D, 0.0D);
                    break;
                case NORTH:
                    worldIn.spawnParticle(particleTypes, d0 + d4, d1 + 0.7f, d2 + d3, 0.0D, 0.0D, 0.0D);
                    break;
                case SOUTH:
                    worldIn.spawnParticle(particleTypes, d0 + d4, d1 + 0.7f, d2 - d3, 0.0D, 0.0D, 0.0D);
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
    public void RegisterRecipes() {
        // Register Components
        PlanComponent techStoneBase = new PlanComponent("Base Materials",
                Arrays.asList(
                        PlanRegistry.getItemStack(new ItemStack(net.minecraft.init.Blocks.COBBLESTONE, 10)),
                        PlanRegistry.getItemStack(Items.ITEM_PLAN.getStack(10)),
                        PlanRegistry.getItemStack(Blocks.BLOCK_MISC_CRANK.getStack(1)),
                        PlanRegistry.getItemStack(Items.ITEM_ORE_INGOT.getStack(10, EnumOres.COPPER.getMeta())),
                        PlanRegistry.getItemStack("ingotIron", 10)
                ),
                null,
                null,
                0, 0, 0, true);

        PlanComponent techStoneSpeed1 = new PlanComponent("50% Speed Increase",
                Arrays.asList(
                        PlanRegistry.getItemStack(new ItemStack(net.minecraft.init.Blocks.COBBLESTONE, 10)),
                        PlanRegistry.getItemStack("ingotIron", 10)
                ),
                null,
                null,
                40, 2, 50, false);

        PlanComponent techStoneSpeed2 = new PlanComponent("100% Speed Increase",
                Arrays.asList(
                        PlanRegistry.getItemStack(new ItemStack(net.minecraft.init.Blocks.COBBLESTONE, 10)),
                        PlanRegistry.getItemStack("ingotIron", 10),
                        PlanRegistry.getItemStack("logWood", 10),
                        PlanRegistry.getItemStack("plankWood", 30)
                ),
                null,
                null,
                40, 2, 50, false);


        // Register Machines
        PlanRegistry.RegisterMachine(new PlanMachine("Stone Furnace",
                Arrays.asList(
                        techStoneBase, techStoneSpeed1
                ), Blocks.BLOCK_MACHINE_FURNACE.getStack(), 100, TechLevel.STONE_AGE));

        PlanRegistry.RegisterMachine(new PlanMachine("Furnace 1",
                Arrays.asList(
                        techStoneBase, techStoneSpeed1, techStoneSpeed2
                ), Blocks.BLOCK_MACHINE_FURNACE.getStack(), 100, TechLevel.STONE_AGE));

        PlanRegistry.RegisterMachine(new PlanMachine("Furnace 2",
                Arrays.asList(
                        techStoneSpeed1
                ), Blocks.BLOCK_MACHINE_FURNACE.getStack(), 100, TechLevel.STONE_AGE));

        PlanRegistry.RegisterMachine(new PlanMachine("Furnace 3",
                Arrays.asList(
                        techStoneSpeed1, techStoneSpeed2
                ), Blocks.BLOCK_MACHINE_FURNACE.getStack(), 100, TechLevel.STONE_AGE));

        PlanRegistry.RegisterMachine(new PlanMachine("Furnace 4",
                Arrays.asList(
                        techStoneSpeed1, techStoneSpeed2, techStoneSpeed1, techStoneSpeed2, techStoneBase, techStoneBase, techStoneSpeed1, techStoneSpeed2
                ), Blocks.BLOCK_MACHINE_FURNACE.getStack(), 100, TechLevel.STONE_AGE));

    }
}
