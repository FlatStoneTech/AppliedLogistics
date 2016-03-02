package tech.flatstone.appliedlogistics.common.items.tools;

import com.google.common.collect.Sets;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;
import tech.flatstone.appliedlogistics.common.blocks.Blocks;
import tech.flatstone.appliedlogistics.common.items.ItemBaseTool;
import tech.flatstone.appliedlogistics.common.util.LogHelper;

import java.util.List;
import java.util.Set;

public class ItemHammer extends ItemBaseTool {
    public static Set effectiveAgainst = Sets.newHashSet(new Block[]{});

    public ItemHammer() {
        super(3.0F, ToolMaterial.STONE, effectiveAgainst);

        effectiveAgainst.add(Blocks.BLOCK_ORE.block);
    }

    @Override
    public boolean canHarvestBlock(Block blockIn) {
        return true;
    }

    @Override
    public float getDigSpeed(ItemStack stack, IBlockState state) {
        return 0.8F;
    }

    @Override
    public boolean onBlockStartBreak(ItemStack itemstack, BlockPos pos, EntityPlayer player) {
        IBlockState blockState = player.worldObj.getBlockState(pos);
        LogHelper.info(">>> Debug: " + blockState);
        if (OreDictionary.getOreIDs(new ItemStack(blockState.getBlock(), 1, blockState.getBlock().getMetaFromState(blockState))).length > 0) {
            String ore = OreDictionary.getOreName(OreDictionary.getOreIDs(new ItemStack(blockState.getBlock(), 1, blockState.getBlock().getMetaFromState(blockState)))[0]);
            if (ore.startsWith("ore")) {
                String oreName = ore.substring(3);
                List<ItemStack> ores = OreDictionary.getOres("dust" + oreName);
                if (ores.size() > 0) {
                    ItemStack oreToDrop = ores.get(0);
                    LogHelper.info(oreToDrop.toString());
                }
            }

        }
        return false;
        //return super.onBlockStartBreak(itemstack, pos, player);
    }


}
