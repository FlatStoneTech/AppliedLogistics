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

package tech.flatstone.appliedlogistics.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import tech.flatstone.appliedlogistics.ModInfo;
import tech.flatstone.appliedlogistics.common.tileentities.TileEntityBase;
import tech.flatstone.appliedlogistics.common.util.*;

import java.util.ArrayList;
import java.util.List;

public abstract class BlockBase extends Block {
    public static final PropertyEnum AXIS_ORIENTATION = PropertyEnum.create("axis", EnumFacing.Axis.class);
    protected boolean isInventory = false;
    protected boolean hasSubtypes = false;

    protected BlockBase(Material material) {
        super(material);

        setStepSound(Block.soundTypeStone);
        setHardness(2.2F);
        setResistance(5.0F);
        setHarvestLevel("pickaxe", 0);
    }

    @Override
    public String getUnlocalizedName() {
        String blockName = getUnwrappedUnlocalizedName(super.getUnlocalizedName());

        String test = String.format("tile.%s.%s", ModInfo.MOD_ID, blockName);
        return test;
    }

    protected String getUnwrappedUnlocalizedName(String unlocalizedName) {
        return unlocalizedName.substring(unlocalizedName.indexOf(".") + 1);
    }

    @Override
    public int getRenderType() {
        return 3;
    }

    @Override
    public boolean removedByPlayer(World world, BlockPos pos, EntityPlayer player, boolean willHarvest) {
        return willHarvest || super.removedByPlayer(world, pos, player, false);
    }

    @Override
    public void harvestBlock(World world, EntityPlayer player, BlockPos pos, IBlockState state, TileEntity te) {
        super.harvestBlock(world, player, pos, state, te);
        world.setBlockToAir(pos);
    }

    @Override
    public List<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
        TileEntityBase tileEntity = TileHelper.getTileEntity(world, pos, TileEntityBase.class);
        if (tileEntity != null && tileEntity.hasCustomName()) {
            final ItemStack itemStack = new ItemStack(this, 1, tileEntity.getBlockMetadata());
            itemStack.setStackDisplayName(tileEntity.getCustomName());

            ArrayList<ItemStack> drops = new ArrayList<ItemStack>();
            drops.add(itemStack);

            return drops;
        }
        return super.getDrops(world, pos, state, fortune);
    }

    @Override
    public boolean rotateBlock(World world, BlockPos pos, EnumFacing axis) {
        final IOrientable rotatable = this.getOrientable(world, pos);
        if (rotatable != null && rotatable.canBeRotated()) {
            if (this.hasCustomRotation()) {
                this.customRotateBlock(rotatable, axis);
                return true;
            } else {
                EnumFacing forward = rotatable.getForward();
                EnumFacing up = rotatable.getUp();

                for (int rs = 0; rs < 4; rs++) {
                    forward = Platform.rotateAround(forward, axis);
                    up = Platform.rotateAround(up, axis);

                    LogHelper.info(">>> Up: " + up.getName());
                    LogHelper.info(">>> Forward: " + forward.getName());

                    if (this.isValidOrientation(world, pos, forward, up)) {
                        rotatable.setOrientation(forward, up);
                        return true;
                    }
                }
            }
        }

        return super.rotateBlock(world, pos, axis);
    }

    protected boolean hasCustomRotation() {
        return false;
    }

    protected void customRotateBlock(final IOrientable rotatable, final EnumFacing axis) {

    }

    public boolean isValidOrientation(final World world, final BlockPos pos, final EnumFacing forward, final EnumFacing up) {
        return true;
    }

    public IOrientable getOrientable(final IBlockAccess world, final BlockPos pos) {
        if (this instanceof IOrientableBlock)
            return ((IOrientableBlock) this).getOrientable(world, pos);
        return null;
    }

    @Override
    public EnumFacing[] getValidRotations(World world, BlockPos pos) {
        return new EnumFacing[0];
    }

    public EnumFacing mapRotation(final IOrientable orientable, final EnumFacing direction) {
        final EnumFacing forward = orientable.getForward();
        final EnumFacing up = orientable.getUp();

        if (forward == null || up == null)
            return direction;

        final int west_x = forward.getFrontOffsetY() * up.getFrontOffsetZ() - forward.getFrontOffsetZ() * up.getFrontOffsetY();
        final int west_y = forward.getFrontOffsetZ() * up.getFrontOffsetX() - forward.getFrontOffsetX() * up.getFrontOffsetZ();
        final int west_z = forward.getFrontOffsetX() * up.getFrontOffsetY() - forward.getFrontOffsetY() * up.getFrontOffsetX();

        EnumFacing west = null;
        for (final EnumFacing dir : EnumFacing.values()) {
            if (dir.getFrontOffsetX() == west_x && dir.getFrontOffsetY() == west_y && dir.getFrontOffsetZ() == west_z)
                west = dir;
        }

        if (west == null)
            return direction;

        if (direction == forward)
            return EnumFacing.SOUTH;

        if (direction == forward.getOpposite())
            return EnumFacing.NORTH;

        if (direction == up)
            return EnumFacing.UP;

        if (direction == up.getOpposite())
            return EnumFacing.DOWN;

        if (direction == west)
            return EnumFacing.WEST;

        if (direction == west.getOpposite())
            return EnumFacing.EAST;

        return null;
    }
}
