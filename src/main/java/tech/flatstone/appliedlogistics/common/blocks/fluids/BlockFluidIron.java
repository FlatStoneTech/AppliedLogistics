package tech.flatstone.appliedlogistics.common.blocks.fluids;

import tech.flatstone.appliedlogistics.common.blocks.BlockFluidMetalBase;
import tech.flatstone.appliedlogistics.common.fluids.FluidIron;

public class BlockFluidIron extends BlockFluidMetalBase {
    public BlockFluidIron() {
        super(FluidIron.INSTANCE.getFluid());
    }
}
