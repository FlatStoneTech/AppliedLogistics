package tech.flatstone.appliedlogistics.common.fluids;

import tech.flatstone.appliedlogistics.common.blocks.fluids.BlockFluidGold;

public class FluidGold extends FluidBase {
    public static FluidGold INSTANCE;

    public FluidGold() {
        super("gold", BlockFluidGold.class, true);
        INSTANCE = this;
        this.getFluid().setLuminosity(15);
        this.getFluid().setViscosity(5000);
    }
}
