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
import net.minecraftforge.client.model.ModelLoader;
import tech.flatstone.appliedlogistics.ModInfo;
import tech.flatstone.appliedlogistics.common.blocks.BlockBase;
import tech.flatstone.appliedlogistics.common.util.EnumOres;
import tech.flatstone.appliedlogistics.common.util.IBlockRenderer;

import java.util.List;

public class BlockOreBlock extends BlockBase implements IBlockRenderer {
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
            if (!EnumOres.byMeta(i).isVanillaGen()) {
                list.add(new ItemStack(itemIn, 1, i));
            }
        }
    }

    @Override
    public void registerBlockRenderer() {
        for (int i = 0; i < EnumOres.values().length; i++) {
            if (!EnumOres.byMeta(i).isVanillaGen()) {
                ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), i, new ModelResourceLocation(ModInfo.MOD_ID + ":" + "block_" + EnumOres.byMeta(i).getUnlocalisedName(), "inventory"));
            } else {
                ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), i, new ModelResourceLocation(EnumOres.byMeta(i).getUnlocalisedName() + "_block", "inventory"));
            }
        }
    }
}
