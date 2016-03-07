package tech.flatstone.appliedlogistics.common.items.tools;

import com.google.common.collect.Sets;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.oredict.ShapedOreRecipe;
import tech.flatstone.appliedlogistics.ModInfo;
import tech.flatstone.appliedlogistics.api.registries.HammerRegistry;
import tech.flatstone.appliedlogistics.api.registries.helpers.Hammerable;
import tech.flatstone.appliedlogistics.common.items.Items;
import tech.flatstone.appliedlogistics.common.util.IItemRenderer;
import tech.flatstone.appliedlogistics.common.util.IProvideRecipe;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class ItemHammer extends ItemTool implements IItemRenderer, IProvideRecipe {
    public static Set blocksEffectiveAgainst = Sets.newHashSet(new Block[]{});
    public static boolean canHarvest = false;
    private static IBlockState blockHarvest = null;
    public static ToolMaterial toolMaterialHammer = EnumHelper.addToolMaterial("APPLIEDLOGISTICSHAMMER", 3, 100, 15.0F, 4.0F, 30);

    public ItemHammer() {
        super(3.0F, toolMaterialHammer, blocksEffectiveAgainst);
    }

    @Override
    public boolean canHarvestBlock(Block block) {
        return canHarvest;
    }

    @Override
    public float getDigSpeed(ItemStack itemstack, IBlockState state) {
        if (state != blockHarvest)
            canHarvest = false;

        blockHarvest = state;

        return canHarvest ? efficiencyOnProperMaterial * 0.75f : 0.75f;
    }

    @Override
    public boolean onBlockStartBreak(ItemStack itemstack, BlockPos pos, EntityPlayer player) {
        World world = player.worldObj;
        IBlockState iBlockState = world.getBlockState(pos);
        Block block = iBlockState.getBlock();
        ItemStack blockItemStack = new ItemStack(block, 1, block.getMetaFromState(iBlockState));
        int fortune = EnchantmentHelper.getFortuneModifier(player);
        boolean valid = false;

        ArrayList<Hammerable> drops = HammerRegistry.getDrops(blockItemStack);

        if (drops.size() > 0) {
            Iterator<Hammerable> it = drops.iterator();

            while (it.hasNext()) {
                Hammerable drop = it.next();

                if (!world.isRemote && world.rand.nextFloat() <= drop.chance + (drop.luckMultiplier * fortune)) {
                    EntityItem entityItem = new EntityItem(world, (double)pos.getX() + 0.5D, (double)pos.getY() + 0.5D, (double)pos.getZ() + 0.5D, drop.outItemStack.copy());

                    double f3 = 0.05f;
                    entityItem.motionX = world.rand.nextGaussian() * f3;
                    entityItem.motionY = (0.2d);
                    entityItem.motionZ = world.rand.nextGaussian() * f3;

                    world.spawnEntityInWorld(entityItem);
                }

                valid = true;
            }
        } else {
            if (block.getMaterial().isToolNotRequired() || block.getHarvestLevel(iBlockState) == 0) {
                return false;
            }
        }

        itemstack.damageItem(1, player);

        if (itemstack.stackSize == 0)
            player.destroyCurrentEquippedItem();

        if (!world.isRemote) {
            world.setBlockToAir(pos);
            world.playAuxSFX(2001, pos, 1);
        }

        return valid;
    }

    @Override
    public void registerItemRenderer() {
        ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(ModInfo.MOD_ID + ":tools/" + Items.ITEM_TOOL_HAMMER.getInternalName(), "inventory"));
    }

    @Override
    public List<ShapedOreRecipe> RecipesList() {
        List<ShapedOreRecipe> recipesList = new ArrayList();

        recipesList.add(new ShapedOreRecipe(new ItemStack(Items.ITEM_TOOL_HAMMER.item),
                " yz",
                " xy",
                "x  ",
                'x', "stickWood",
                'y', "ingotIron",
                'z', "nuggetIron"
        ));

        recipesList.add(new ShapedOreRecipe(new ItemStack(Items.ITEM_TOOL_HAMMER.item),
                "zy ",
                "yx ",
                "  x",
                'x', "stickWood",
                'y', "ingotIron",
                'z', "nuggetIron"
        ));

        return recipesList;
    }
}
