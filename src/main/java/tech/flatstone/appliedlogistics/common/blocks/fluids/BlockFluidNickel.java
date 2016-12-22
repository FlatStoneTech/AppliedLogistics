package tech.flatstone.appliedlogistics.common.blocks.fluids;

import tech.flatstone.appliedlogistics.common.blocks.BlockFluidMetalBase;
import tech.flatstone.appliedlogistics.common.fluids.FluidNickel;

public class BlockFluidNickel extends BlockFluidMetalBase {
    public BlockFluidNickel() {
        super(FluidNickel.INSTANCE.getFluid());
    }
}
