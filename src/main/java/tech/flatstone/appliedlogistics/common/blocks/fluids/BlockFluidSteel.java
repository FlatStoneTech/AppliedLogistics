package tech.flatstone.appliedlogistics.common.blocks.fluids;

import net.minecraft.block.material.Material;
import tech.flatstone.appliedlogistics.common.blocks.BlockFluidMetalBase;
import tech.flatstone.appliedlogistics.common.fluids.FluidSteel;

public class BlockFluidSteel extends BlockFluidMetalBase {
    public BlockFluidSteel() {
        super(FluidSteel.INSTANCE.getFluid());
    }
}
