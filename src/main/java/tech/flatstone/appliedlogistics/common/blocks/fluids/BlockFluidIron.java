package tech.flatstone.appliedlogistics.common.blocks.fluids;

import net.minecraft.block.material.Material;
import net.minecraftforge.fluids.Fluid;
import tech.flatstone.appliedlogistics.common.blocks.BlockFluidMetalBase;
import tech.flatstone.appliedlogistics.common.fluids.FluidIron;
import tech.flatstone.appliedlogistics.common.util.LogHelper;

public class BlockFluidIron extends BlockFluidMetalBase {
    public BlockFluidIron() {
        super(FluidIron.INSTANCE.getFluid());
    }
}
