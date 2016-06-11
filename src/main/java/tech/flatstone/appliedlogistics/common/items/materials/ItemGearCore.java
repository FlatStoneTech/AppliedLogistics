package tech.flatstone.appliedlogistics.common.items.materials;

import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.ShapedOreRecipe;
import tech.flatstone.appliedlogistics.AppliedLogisticsCreativeTabs;
import tech.flatstone.appliedlogistics.common.items.ItemBase;
import tech.flatstone.appliedlogistics.common.items.Items;
import tech.flatstone.appliedlogistics.common.util.IProvideRecipe;

public class ItemGearCore extends ItemBase implements IProvideRecipe {
    public ItemGearCore() {
        super("materials/gear_core");
        this.setCreativeTab(AppliedLogisticsCreativeTabs.GENERAL);
        this.setInternalName("material_gear_core");
    }

    @Override
    public void RegisterRecipes() {
        GameRegistry.addRecipe(new ShapedOreRecipe(Items.ITEM_MATERIAL_GEARCORE.getStack(3),
                "yxy",
                "x x",
                "yxy",
                'x', "ingotBronze",
                'y', "ingotIron")
        );
    }
}
