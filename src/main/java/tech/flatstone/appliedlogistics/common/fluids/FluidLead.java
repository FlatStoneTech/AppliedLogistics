package tech.flatstone.appliedlogistics.common.fluids;

import tech.flatstone.appliedlogistics.common.blocks.fluids.BlockFluidLead;

public class FluidLead extends FluidBase {
    public static FluidLead INSTANCE;

    public FluidLead() {
        super("lead", BlockFluidLead.class, true);
        INSTANCE = this;
        this.getFluid().setLuminosity(15);
        this.getFluid().setViscosity(5000);
    }
}
