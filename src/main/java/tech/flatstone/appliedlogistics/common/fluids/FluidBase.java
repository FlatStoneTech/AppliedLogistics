package tech.flatstone.appliedlogistics.common.fluids;

import net.minecraftforge.fluids.Fluid;
import tech.flatstone.appliedlogistics.common.blocks.BlockFluidBase;
import tech.flatstone.appliedlogistics.common.util.FluidHelper;

public class FluidBase {
    final protected Fluid fluid;

    public FluidBase(String fluidName, Class<? extends BlockFluidBase> tClass, boolean hasFlowIcon) {
        fluid = FluidHelper.createFluid(fluidName, hasFlowIcon);
        if (tClass != null)
            FluidHelper.registerFluidBlock(tClass);
    }

    public Fluid getFluid() {
        return fluid;
    }
}
