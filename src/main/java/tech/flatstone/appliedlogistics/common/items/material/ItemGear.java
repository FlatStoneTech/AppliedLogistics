package tech.flatstone.appliedlogistics.common.items.material;

import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.ShapedOreRecipe;
import tech.flatstone.appliedlogistics.ModInfo;
import tech.flatstone.appliedlogistics.api.features.EnumOreType;
import tech.flatstone.appliedlogistics.common.items.ItemBase;
import tech.flatstone.appliedlogistics.common.items.Items;
import tech.flatstone.appliedlogistics.common.util.EnumOres;
import tech.flatstone.appliedlogistics.common.util.IItemRenderer;
import tech.flatstone.appliedlogistics.common.util.IProvideRecipe;

import java.util.ArrayList;
import java.util.List;

public class ItemGear extends ItemBase implements IItemRenderer, IProvideRecipe {
    public ItemGear() {
        this.setHasSubtypes(true);
    }

    @Override
    public void getSubItems(Item itemIn, CreativeTabs tab, List<ItemStack> subItems) {
        for (int i = 0; i < EnumOres.values().length; i++) {
            if (EnumOres.byMeta(i).isTypeSet(EnumOreType.GEAR)) {
                subItems.add(new ItemStack(this, 1, i));
            }
        }
    }

    @Override
    public int getMetadata(int damage) {
        return damage;
    }

    @Override
    public String getUnlocalizedName(ItemStack itemStack) {
        String name = super.getUnlocalizedName();
        String oreName = EnumOres.byMeta(itemStack.getItemDamage()).getUnlocalizedName();
        return name + "." + oreName;
    }

    @Override
    public void registerItemRenderer() {
        for (int i = 0; i < EnumOres.values().length; i++) {
            if (EnumOres.byMeta(i).isTypeSet(EnumOreType.GEAR)) {
                ModelLoader.setCustomModelResourceLocation(this, i, new ModelResourceLocation(ModInfo.MOD_ID + ":materials/gear/" + EnumOres.byMeta(i).getUnlocalizedName(), "inventory"));
            }
        }
    }

    @Override
    public void RegisterRecipes() {
        for (int i = 0; i < EnumOres.values().length; i++) {
            // Normal Gear Recipe
            if (EnumOres.byMeta(i).isTypeSet(EnumOreType.GEAR) && EnumOres.byMeta(i).isTypeSet(EnumOreType.INGOT)) {
                GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Items.ITEM_MATERIAL_GEAR.item, 1, i),
                        " x ",
                        "xyx",
                        " x ",
                        'x', "ingot" + EnumOres.byMeta(i).getName(),
                        'y', "ingotIron")
                );
            }

            // Vanilla Material Gears
            if (EnumOres.byMeta(i).isTypeSet(EnumOreType.GEAR) && EnumOres.byMeta(i).isTypeSet(EnumOreType.VANILLA)) {
                String outerMaterial = "";
                String innerMaterial = "";

                switch (EnumOres.byMeta(i)) {
                    case WOOD:
                        outerMaterial = "stickWood";
                        innerMaterial = "plankWood";
                        break;
                    case COBBLESTONE:
                        outerMaterial = "cobblestone";
                        innerMaterial = "plankWood";
                        break;
                }

                GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Items.ITEM_MATERIAL_GEAR.item, 1, i),
                        " x ",
                        "xyx",
                        " x ",
                        'x', outerMaterial,
                        'y', innerMaterial)
                );
            }
        }
    }
}
