package tech.flatstone.appliedlogistics.common.blocks.fluids;

import tech.flatstone.appliedlogistics.common.blocks.BlockFluidMetalBase;
import tech.flatstone.appliedlogistics.common.fluids.FluidBronze;

public class BlockFluidBronze extends BlockFluidMetalBase {
    public BlockFluidBronze() {
        super(FluidBronze.INSTANCE.getFluid());
    }
}
