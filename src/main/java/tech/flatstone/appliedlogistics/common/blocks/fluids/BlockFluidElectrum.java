package tech.flatstone.appliedlogistics.common.blocks.fluids;

import net.minecraft.block.material.Material;
import tech.flatstone.appliedlogistics.common.blocks.BlockFluidMetalBase;
import tech.flatstone.appliedlogistics.common.fluids.FluidElectrum;

public class BlockFluidElectrum extends BlockFluidMetalBase {
    public BlockFluidElectrum() {
        super(FluidElectrum.INSTANCE.getFluid());
    }
}
