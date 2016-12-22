package tech.flatstone.appliedlogistics.common.blocks.fluids;

import tech.flatstone.appliedlogistics.common.blocks.BlockFluidMetalBase;
import tech.flatstone.appliedlogistics.common.fluids.FluidTitanium;

public class BlockFluidTitanium extends BlockFluidMetalBase {
    public BlockFluidTitanium() {
        super(FluidTitanium.INSTANCE.getFluid());
    }
}
