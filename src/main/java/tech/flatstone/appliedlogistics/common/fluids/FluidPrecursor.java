package tech.flatstone.appliedlogistics.common.fluids;

import tech.flatstone.appliedlogistics.common.blocks.fluids.BlockFluidPrecursor;

public class FluidPrecursor extends FluidBase {
    public static FluidPrecursor INSTANCE;

    public FluidPrecursor() {
        super("precursor", BlockFluidPrecursor.class, true);
        INSTANCE = this;
    }
}