package tech.flatstone.appliedlogistics.common.fluids;

import tech.flatstone.appliedlogistics.common.blocks.fluids.BlockFluidTitanium;

public class FluidTitanium extends FluidBase {
    public static FluidTitanium INSTANCE;

    public FluidTitanium() {
        super("titanium", BlockFluidTitanium.class, true);
        INSTANCE = this;
    }
}
