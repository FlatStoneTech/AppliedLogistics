package tech.flatstone.appliedlogistics.common.blocks.fluids;

import net.minecraft.block.material.Material;
import tech.flatstone.appliedlogistics.common.blocks.BlockFluidMetalBase;
import tech.flatstone.appliedlogistics.common.fluids.FluidGold;

public class BlockFluidGold extends BlockFluidMetalBase {
    public BlockFluidGold() {
        super(FluidGold.INSTANCE.getFluid());
    }
}
