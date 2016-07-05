package tech.flatstone.appliedlogistics.common.util;

import com.google.common.collect.Lists;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fluids.*;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import tech.flatstone.appliedlogistics.AppliedLogisticsCreativeTabs;
import tech.flatstone.appliedlogistics.ModInfo;

import java.util.List;

public class FluidHelper {

	static List<Fluid> fluidList = Lists.newArrayList();

	@SubscribeEvent
	public void onTextureStitch(TextureStitchEvent event)
	{
		for (Fluid fluid : fluidList)
		{
			if (fluid.getStill() != null)
				event.getMap().registerSprite(fluid.getStill());
			if (fluid.getFlowing() != null)
				event.getMap().registerSprite(fluid.getFlowing());
		}
	}

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

		Item fluidItem = Item.getItemFromBlock(fluidBlock);
		ModelBakery.registerItemVariants(fluidItem);

		final String resourcePath = String.format("%s:fluids", ModInfo.MOD_ID, fluidBlock.getFluid().getName());
		ModelLoader.setCustomStateMapper(fluidBlock, new StateMapperBase() {
			@Override
			protected ModelResourceLocation getModelResourceLocation(IBlockState state) {
				return new ModelResourceLocation(resourcePath, getPropertyString(state.getProperties()));
			}
		});

		fluidList.add(fluid);

        return fluid;
    }





}
