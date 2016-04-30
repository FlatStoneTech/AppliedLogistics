package tech.flatstone.appliedlogistics.common.util;

import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

public class FluidHelper {
    public static Fluid createFluid(String name, String textureName, boolean hasFlowIcon) {
        ResourceLocation still = new ResourceLocation(textureName + "-still");
        ResourceLocation flowing = hasFlowIcon ? new ResourceLocation(textureName + "-flow") : still;

        Fluid fluid = new Fluid(name, still, flowing);
        if (!FluidRegistry.registerFluid(fluid)) {
            throw new IllegalStateException("Unable to register fluid");
        }

        return fluid;
    }

    public static void registerBucket(Fluid fluid) {

    }
}
