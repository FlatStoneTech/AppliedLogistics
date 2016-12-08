package tech.flatstone.appliedlogistics.common.blocks.fluids;

import net.minecraft.block.material.Material;
import tech.flatstone.appliedlogistics.common.blocks.BlockFluidMetalBase;
import tech.flatstone.appliedlogistics.common.fluids.FluidLead;

public class BlockFluidLead extends BlockFluidMetalBase {
    public BlockFluidLead() {
        super(FluidLead.INSTANCE.getFluid());
    }
}
