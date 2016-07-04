package tech.flatstone.appliedlogistics.common.util;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.IFluidBlock;
import net.minecraftforge.fml.common.registry.GameRegistry;
import tech.flatstone.appliedlogistics.AppliedLogisticsCreativeTabs;
import tech.flatstone.appliedlogistics.ModInfo;

public class FluidHelper {
    public static Fluid createFluid(String name, Material material, boolean hasFlowIcon) {
        String texturePrefix = ModInfo.MOD_ID + ":fluids/";

        ResourceLocation still = new ResourceLocation(String.format("%s%s_still", texturePrefix, name));
        ResourceLocation flowing = hasFlowIcon ? new ResourceLocation(String.format("%s%s_flowing", texturePrefix, name)) : still;

        Fluid fluid = new Fluid(name, still, flowing);
        fluid.setUnlocalizedName(ModInfo.MOD_ID + "." + name);
        FluidRegistry.addBucketForFluid(fluid);

        BlockFluidClassic fluidBlock = new BlockFluidClassic(fluid, material);
        fluidBlock.setRegistryName(String.format("fluid.%s", name));
        fluidBlock.setUnlocalizedName(fluidBlock.getRegistryName().toString());
        fluidBlock.setCreativeTab(AppliedLogisticsCreativeTabs.FLUIDS);
        GameRegistry.register(fluidBlock);
        GameRegistry.register((new ItemBlock(fluidBlock)).setRegistryName(fluidBlock.getRegistryName()));

        return fluid;
    }
}
