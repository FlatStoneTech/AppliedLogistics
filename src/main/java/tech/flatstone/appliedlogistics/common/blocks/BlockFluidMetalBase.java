package tech.flatstone.appliedlogistics.common.blocks;

import net.minecraft.block.material.Material;
import net.minecraftforge.fluids.Fluid;

public class BlockFluidMetalBase extends BlockFluidBase {
    public BlockFluidMetalBase(Fluid fluid) {
        super(fluid, Material.LAVA);
    }
}
