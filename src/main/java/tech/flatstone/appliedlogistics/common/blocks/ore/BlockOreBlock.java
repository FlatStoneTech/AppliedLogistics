package tech.flatstone.appliedlogistics.common.blocks.ore;

import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import tech.flatstone.appliedlogistics.ModInfo;
import tech.flatstone.appliedlogistics.api.features.EnumOreType;
import tech.flatstone.appliedlogistics.common.blocks.BlockBase;
import tech.flatstone.appliedlogistics.common.blocks.Blocks;
import tech.flatstone.appliedlogistics.common.items.Items;
import tech.flatstone.appliedlogistics.common.util.EnumOres;
import tech.flatstone.appliedlogistics.common.util.IBlockRenderer;
import tech.flatstone.appliedlogistics.common.util.IProvideRecipe;

import java.util.ArrayList;
import java.util.List;

public class BlockOreBlock extends BlockBase implements IBlockRenderer, IProvideRecipe {
    public static final PropertyEnum<EnumOres> ORES = PropertyEnum.<EnumOres>create("name", EnumOres.class);

    public BlockOreBlock() {
        super(Material.rock);
        this.setDefaultState(this.blockState.getBaseState().withProperty(ORES, EnumOres.IRON));
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState().withProperty(ORES, EnumOres.byMeta(meta));
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return (state.getValue(ORES)).getMeta();
    }

    @Override
    protected BlockState createBlockState() {
        return new BlockState(this, new IProperty[]{ORES});
    }

    @Override
    public int damageDropped(IBlockState state) {
        return getMetaFromState(state);
    }

    @Override
    public void getSubBlocks(Item itemIn, CreativeTabs tab, List<ItemStack> list) {
        for (int i = 0; i < EnumOres.values().length; i++) {
            if (EnumOres.byMeta(i).isTypeSet(EnumOreType.BLOCK)) {
                list.add(new ItemStack(itemIn, 1, i));
            }
        }
    }

    @Override
    public void registerBlockRenderer() {
        for (int i = 0; i < EnumOres.values().length; i++) {
            if (EnumOres.byMeta(i).isTypeSet(EnumOreType.BLOCK)) {
                ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), i, new ModelResourceLocation(ModInfo.MOD_ID + ":materials/block/" + EnumOres.byMeta(i).getUnlocalizedName(), "inventory"));
            }
        }
    }

    @Override
    public void RegisterRecipes() {
        for (int i = 0; i < EnumOres.values().length; i++) {
            if (EnumOres.byMeta(i).isTypeSet(EnumOreType.INGOT) && EnumOres.byMeta(i).isTypeSet(EnumOreType.BLOCK)) {
                // Register 9x Ingot -> Block
                GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Blocks.BLOCK_ORE_BLOCK.block, 1, i),
                        "xxx",
                        "xxx",
                        "xxx",
                        'x', "ingot" + EnumOres.byMeta(i).getName())
                );
            }
        }
    }
}
