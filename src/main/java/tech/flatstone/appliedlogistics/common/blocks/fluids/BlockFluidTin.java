package tech.flatstone.appliedlogistics.common.blocks.fluids;

import tech.flatstone.appliedlogistics.common.blocks.BlockFluidMetalBase;
import tech.flatstone.appliedlogistics.common.fluids.FluidTin;

public class BlockFluidTin extends BlockFluidMetalBase {
    public BlockFluidTin() {
        super(FluidTin.INSTANCE.getFluid());
    }
}
