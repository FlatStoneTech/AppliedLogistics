package tech.flatstone.appliedlogistics.common.items.ore;

import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import tech.flatstone.appliedlogistics.ModInfo;
import tech.flatstone.appliedlogistics.api.features.EnumOreType;
import tech.flatstone.appliedlogistics.common.items.ItemBase;
import tech.flatstone.appliedlogistics.common.items.Items;
import tech.flatstone.appliedlogistics.common.util.EnumOres;
import tech.flatstone.appliedlogistics.common.util.IItemRenderer;
import tech.flatstone.appliedlogistics.common.util.IProvideRecipe;

import java.util.List;

public class ItemOreNugget extends ItemBase implements IItemRenderer, IProvideRecipe {
    public ItemOreNugget() {
        this.setHasSubtypes(true);
    }

    @Override
    public void getSubItems(Item itemIn, CreativeTabs tab, List<ItemStack> subItems) {
        for (int i = 0; i < EnumOres.values().length; i++) {
            if (EnumOres.byMeta(i).isTypeSet(EnumOreType.NUGGET)) {
                subItems.add(new ItemStack(this, 1, i));
            }
        }
    }

    @Override
    public int getMetadata(int damage) {
        return damage;
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        String name = super.getUnlocalizedName();
        String oreName = EnumOres.byMeta(stack.getItemDamage()).getUnlocalizedName();
        return name + "." + oreName;
    }

    @Override
    public void registerItemRenderer() {
        for (int i = 0; i < EnumOres.values().length; i++) {
            if (EnumOres.byMeta(i).isTypeSet(EnumOreType.NUGGET)) {
                ModelLoader.setCustomModelResourceLocation(this, i, new ModelResourceLocation(ModInfo.MOD_ID + ":materials/nugget/" + EnumOres.byMeta(i).getUnlocalizedName(), "inventory"));
            }
        }
    }

    @Override
    public void RegisterRecipes() {
        for (int i = 0; i < EnumOres.values().length; i++) {
            // Ingot -> 9x Nugget
            if (EnumOres.byMeta(i).isTypeSet(EnumOreType.INGOT) && EnumOres.byMeta(i).isTypeSet(EnumOreType.NUGGET)) {
                GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(Items.ITEM_ORE_NUGGET.item, 9, i), "ingot" + EnumOres.byMeta(i).getName()));
            }

            // Iron Ingot -> 9x Iron Nuggets
            if (EnumOres.byMeta(i) == EnumOres.IRON) {
                GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(Items.ITEM_ORE_NUGGET.item, 9, i), "ingotIron"));
            }

            // Diamond -> 9x Diamond Nuggets
            if (EnumOres.byMeta(i) == EnumOres.DIAMOND) {
                GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(Items.ITEM_ORE_NUGGET.item, 9, i), "gemDiamond"));
            }
        }
    }
}