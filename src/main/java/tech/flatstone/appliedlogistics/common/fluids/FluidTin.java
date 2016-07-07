package tech.flatstone.appliedlogistics.common.fluids;

import tech.flatstone.appliedlogistics.common.blocks.fluids.BlockFluidTin;

public class FluidTin extends FluidBase {
    public static FluidTin INSTANCE;

    public FluidTin() {
        super("tin", BlockFluidTin.class, true);
        INSTANCE = this;
    }
}
