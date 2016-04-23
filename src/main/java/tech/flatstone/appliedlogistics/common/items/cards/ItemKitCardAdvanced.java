package tech.flatstone.appliedlogistics.common.items.cards;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.ShapedOreRecipe;
import tech.flatstone.appliedlogistics.common.items.ItemBase;
import tech.flatstone.appliedlogistics.common.items.Items;
import tech.flatstone.appliedlogistics.common.util.IItemRenderer;
import tech.flatstone.appliedlogistics.common.util.IProvideRecipe;

public class ItemKitCardAdvanced extends ItemBase implements IProvideRecipe {
    public ItemKitCardAdvanced() {
        super("cards/card_advanced_base");
    }

    @Override
    public void RegisterRecipes() {
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(this),
                "rrr",
                "gcg",
                "ddd",
                'r', "dustRedstone",
                'g', "gearElectrum",
                'd', "gemDiamond",
                'c', Items.ITEM_CARD_BLANK.getStack()
        ));
    }
}
