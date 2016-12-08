package tech.flatstone.appliedlogistics.common.blocks.fluids;

import net.minecraft.block.material.Material;
import tech.flatstone.appliedlogistics.common.blocks.BlockFluidMetalBase;
import tech.flatstone.appliedlogistics.common.fluids.FluidCopper;

public class BlockFluidCopper extends BlockFluidMetalBase {
    public BlockFluidCopper() {
        super(FluidCopper.INSTANCE.getFluid());
    }
}
