package tech.flatstone.appliedlogistics.common.items.misc;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import tech.flatstone.appliedlogistics.AppliedLogisticsCreativeTabs;
import tech.flatstone.appliedlogistics.common.items.ItemBase;
import tech.flatstone.appliedlogistics.common.util.IProvideRecipe;

public class ItemUnfiredCrucible extends ItemBase implements IProvideRecipe {

    public ItemUnfiredCrucible() {
        super("misc/unfired_crucible");
        this.setMaxStackSize(64);
        this.setCreativeTab(AppliedLogisticsCreativeTabs.tabGeneral);
        this.setInternalName("unfired_crucible");
    }

    @Override
    public void RegisterRecipes() {
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(this, 1),
                "???",
                "???",
                "???",
                '?', new ItemStack(Items.clay_ball)
        ));
    }
}
