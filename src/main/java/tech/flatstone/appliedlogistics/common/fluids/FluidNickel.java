package tech.flatstone.appliedlogistics.common.fluids;

import tech.flatstone.appliedlogistics.common.blocks.fluids.BlockFluidNickel;

public class FluidNickel extends FluidBase {
    public static FluidNickel INSTANCE;

    public FluidNickel() {
        super("nickel", BlockFluidNickel.class, true);
        INSTANCE = this;
        this.getFluid().setLuminosity(15);
        this.getFluid().setViscosity(5000);
    }
}
