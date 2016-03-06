package tech.flatstone.appliedlogistics.common.items.tools;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Sets;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import tech.flatstone.appliedlogistics.api.registries.HammerRegistry;
import tech.flatstone.appliedlogistics.common.items.ItemBaseTool;
import tech.flatstone.appliedlogistics.common.items.Items;
import tech.flatstone.appliedlogistics.common.util.LogHelper;

import java.util.Set;

public class ItemHammer extends ItemTool {
    public static Set blocksEffectiveAgainst = Sets.newHashSet(new Block[]{});
    public static boolean canHarvest = false;

    public ItemHammer() {
        super(3.0F, ToolMaterial.EMERALD, blocksEffectiveAgainst);
    }

    @Override
    public boolean canHarvestBlock(Block block) {
        return canHarvest;
    }

    @Override
    public float getDigSpeed(ItemStack itemstack, IBlockState state) {
        return canHarvest ? efficiencyOnProperMaterial * 0.75f : 0.75f;
    }

    @Override
    public boolean onBlockStartBreak(ItemStack itemstack, BlockPos pos, EntityPlayer player) {
        World world = player.worldObj;
        IBlockState iBlockState = world.getBlockState(pos);
        int fortune = EnchantmentHelper.getFortuneModifier(player);
        boolean valid = false;

        ItemStack reward = new ItemStack(Items.ITEM_ORE_DUST.item, 1, 3);

        if (!world.isRemote) {
            EntityItem entityItem = new EntityItem(world, (double)pos.getX() + 0.5D, (double)pos.getY() + 0.5D, (double)pos.getZ() + 0.5D, reward);

            double f3 = 0.05f;
            entityItem.motionX = world.rand.nextGaussian() * f3;
            entityItem.motionY = (0.2d);
            entityItem.motionZ = world.rand.nextGaussian() * f3;

            world.spawnEntityInWorld(entityItem);
        }

        valid = true;



        itemstack.damageItem(1, player);

        if (itemstack.stackSize == 0)
            player.destroyCurrentEquippedItem();

        if (!world.isRemote) {
            world.setBlockToAir(pos);
            world.playAuxSFX(2001, pos, 1);
        }

        return valid;
    }
}
