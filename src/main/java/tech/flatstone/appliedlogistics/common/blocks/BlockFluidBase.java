package tech.flatstone.appliedlogistics.common.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import tech.flatstone.appliedlogistics.ModInfo;
import tech.flatstone.appliedlogistics.common.util.IBlockRenderer;

public class BlockFluidBase extends BlockFluidClassic implements IBlockRenderer {
    public BlockFluidBase(Fluid fluid, Material material) {
        super(fluid, material);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerBlockRenderer() {
        String resourcePath = String.format("%s:fluids", ModInfo.MOD_ID);
        final ModelResourceLocation modelResourceLocation = new ModelResourceLocation(resourcePath, this.getFluid().getName());
        ModelLoader.setCustomStateMapper(this, new StateMapperBase() {
            @Override
            protected ModelResourceLocation getModelResourceLocation(IBlockState state) {
                return modelResourceLocation;
            }
        });
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerBlockItemRenderer() {
        Item fluidItem = Item.getItemFromBlock(this);
        String resourcePath = String.format("%s:fluids", ModInfo.MOD_ID);
        final ModelResourceLocation modelResourceLocation = new ModelResourceLocation(resourcePath, this.getFluid().getName());

        ModelLoader.setCustomModelResourceLocation(fluidItem, 0, modelResourceLocation);
    }
}
