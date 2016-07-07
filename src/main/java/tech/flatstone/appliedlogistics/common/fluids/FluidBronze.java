package tech.flatstone.appliedlogistics.common.fluids;

import tech.flatstone.appliedlogistics.common.blocks.fluids.BlockFluidBronze;

public class FluidBronze extends FluidBase {
    public static FluidBase INSTANCE;

    public FluidBronze() {
        super("bronze", BlockFluidBronze.class, true);
        INSTANCE = this;
    }
}
