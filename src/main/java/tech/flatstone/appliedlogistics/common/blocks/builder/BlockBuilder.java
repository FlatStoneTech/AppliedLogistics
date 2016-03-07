package tech.flatstone.appliedlogistics.common.blocks.builder;

import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import tech.flatstone.appliedlogistics.api.features.TechLevel;
import tech.flatstone.appliedlogistics.common.blocks.BlockBase;
import tech.flatstone.appliedlogistics.common.tileentities.builder.TileEntityBuilder;

import java.util.List;

public class BlockBuilder extends BlockBase {
    public static final PropertyEnum TECHLEVEL = PropertyEnum.create("tech", TechLevel.class);

    public BlockBuilder() {
        super(Material.rock);
        this.setDefaultState(this.blockState.getBaseState().withProperty(TECHLEVEL, TechLevel.STONE_AGE));
        this.setTileEntity(TileEntityBuilder.class);
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState().withProperty(TECHLEVEL, TechLevel.byMeta(meta));
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        TechLevel tier = (TechLevel) state.getValue(TECHLEVEL);
        return (tier.getMeta());
    }

    @Override
    protected BlockState createBlockState() {
        return new BlockState(this, new IProperty[]{TECHLEVEL});
    }

    @Override
    public int damageDropped(IBlockState state) {
        return getMetaFromState(state);
    }

    @Override
    public void getSubBlocks(Item itemIn, CreativeTabs tab, List<ItemStack> list) {
        for (int i = 0; i < TechLevel.values().length; i++) {
            list.add(new ItemStack(itemIn, 1, i));
        }
    }
}
