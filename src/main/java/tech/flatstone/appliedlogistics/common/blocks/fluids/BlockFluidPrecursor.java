package tech.flatstone.appliedlogistics.common.blocks.fluids;

import net.minecraft.block.material.Material;
import tech.flatstone.appliedlogistics.common.blocks.BlockFluidBase;
import tech.flatstone.appliedlogistics.common.fluids.FluidPrecursor;

public class BlockFluidPrecursor extends BlockFluidBase {
    public BlockFluidPrecursor() {
        super(FluidPrecursor.INSTANCE.getFluid(), Material.WATER);
    }
}
