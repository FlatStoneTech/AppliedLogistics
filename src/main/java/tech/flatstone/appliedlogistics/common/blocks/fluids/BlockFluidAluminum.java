package tech.flatstone.appliedlogistics.common.blocks.fluids;

import net.minecraft.block.material.Material;
import tech.flatstone.appliedlogistics.common.blocks.BlockFluidMetalBase;
import tech.flatstone.appliedlogistics.common.fluids.FluidAluminum;

public class BlockFluidAluminum extends BlockFluidMetalBase {
    public BlockFluidAluminum() {
        super(FluidAluminum.INSTANCE.getFluid());
    }
}
