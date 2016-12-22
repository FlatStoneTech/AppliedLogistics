package tech.flatstone.appliedlogistics.common.blocks.fluids;

import tech.flatstone.appliedlogistics.common.blocks.BlockFluidMetalBase;
import tech.flatstone.appliedlogistics.common.fluids.FluidSilver;

public class BlockFluidSilver extends BlockFluidMetalBase {
    public BlockFluidSilver() {
        super(FluidSilver.INSTANCE.getFluid());
    }
}
