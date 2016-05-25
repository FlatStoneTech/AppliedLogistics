package tech.flatstone.appliedlogistics.common.blocks.materials;

import net.minecraft.block.material.Material;
import tech.flatstone.appliedlogistics.AppliedLogisticsCreativeTabs;
import tech.flatstone.appliedlogistics.common.blocks.BlockBaseFalling;

public class BlockSilica extends BlockBaseFalling {
    public BlockSilica() {
        super(Material.sand, "materials/silica");
        this.setCreativeTab(AppliedLogisticsCreativeTabs.tabGeneral);
        this.setInternalName("silica");
    }
}
