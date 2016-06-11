package tech.flatstone.appliedlogistics.common.blocks.fluids;

import net.minecraft.block.material.Material;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import tech.flatstone.appliedlogistics.AppliedLogisticsCreativeTabs;

public class BlockFluidBlock extends BlockFluidClassic {
    public BlockFluidBlock(Fluid fluid) {
        super(fluid, Material.LAVA);
        setCreativeTab(AppliedLogisticsCreativeTabs.FLUIDS);
    }

    @Override
    public String getUnlocalizedName() {
        Fluid fluid = FluidRegistry.getFluid(fluidName);
        if (fluid != null) {
            return fluid.getUnlocalizedName();
        }
        return super.getUnlocalizedName();
    }
}
