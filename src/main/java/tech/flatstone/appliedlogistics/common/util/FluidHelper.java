package tech.flatstone.appliedlogistics.common.util;

import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import tech.flatstone.appliedlogistics.AppliedLogisticsCreativeTabs;
import tech.flatstone.appliedlogistics.ModInfo;
import tech.flatstone.appliedlogistics.common.blocks.BlockFluidBase;
import tech.flatstone.appliedlogistics.common.fluids.FluidBase;

import java.util.ArrayList;
import java.util.List;

@SideOnly(Side.CLIENT)
public class FluidHelper {
    private static List<Class<? extends FluidBase>> fluidClasses = new ArrayList<>();
    private static List<Class<? extends BlockFluidBase>> fluidBlockClasses = new ArrayList<>();

    public static void registerFluidBlock(Class<? extends BlockFluidBase> tClass) {
        fluidBlockClasses.add(tClass);
    }

    public static void registerFluid(Class<? extends FluidBase> tClass) {
        fluidClasses.add(tClass);
    }

    public static void initFluids() {
        for (Class<? extends FluidBase> tClass : fluidClasses) {
            try {
                tClass.newInstance();
            } catch (Exception e) {
                LogHelper.fatal("Exception registering fluid");
                e.printStackTrace();
            }
        }

        initBlockFluids();
    }

    private static void initBlockFluids() {
        for (Class<? extends BlockFluidBase> tClass : fluidBlockClasses) {

            BlockFluidClassic fluidBlock = null;

            try {
                fluidBlock = tClass.newInstance();
            } catch (Exception e) {
                LogHelper.fatal("Exception registering fluid");
                e.printStackTrace();
            }

            if (fluidBlock == null)
                return;

            fluidBlock.setRegistryName(String.format("fluid.%s", fluidBlock.getFluid().getName()));
            fluidBlock.setUnlocalizedName(fluidBlock.getRegistryName().toString());
            fluidBlock.setCreativeTab(AppliedLogisticsCreativeTabs.FLUIDS);

            GameRegistry.register(fluidBlock);
            GameRegistry.register((new ItemBlock(fluidBlock)).setRegistryName(fluidBlock.getRegistryName()));

            Item fluidItem = Item.getItemFromBlock(fluidBlock);
            ModelBakery.registerItemVariants(fluidItem);

            if (fluidBlock instanceof IBlockRenderer && Platform.isClient()) {
                ((IBlockRenderer) fluidBlock).registerBlockRenderer();
                ((IBlockRenderer) fluidBlock).registerBlockItemRenderer();
            }
        }
    }

    public static Fluid createFluid(String name, boolean hasFlowIcon) {
        String texturePrefix = ModInfo.MOD_ID + ":fluids/";

        ResourceLocation still = new ResourceLocation(String.format("%s%s_still", texturePrefix, name));
        ResourceLocation flowing = hasFlowIcon ? new ResourceLocation(String.format("%s%s_flowing", texturePrefix, name)) : still;

        Fluid fluid = new Fluid(name, still, flowing);
        fluid.setUnlocalizedName(ModInfo.MOD_ID + "." + name);
        FluidRegistry.addBucketForFluid(fluid);

        return fluid;
    }
}
