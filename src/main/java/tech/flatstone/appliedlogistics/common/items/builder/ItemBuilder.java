package tech.flatstone.appliedlogistics.common.items.builder;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.ITickable;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import tech.flatstone.appliedlogistics.api.features.TechLevel;
import tech.flatstone.appliedlogistics.common.items.Items;
import tech.flatstone.appliedlogistics.common.util.IProvideRecipe;

import java.util.ArrayList;
import java.util.List;

public class ItemBuilder extends ItemBlock implements ITickable, IProvideRecipe {
    public ItemBuilder(Block block) {
        super(block);
        this.setHasSubtypes(true);
        this.setMaxDamage(0);
    }

    @Override
    public int getMetadata(int damage) {
        return damage;
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        String name = super.getUnlocalizedName();
        String techName = TechLevel.values()[stack.getItemDamage()].getName();
        return name + "." + techName;
    }

    @Override
    public void tick() {

    }

    @Override
    public List<ShapedOreRecipe> RecipesList() {
        List<ShapedOreRecipe> recipesList = new ArrayList();

        recipesList.add(new ShapedOreRecipe(new ItemStack(this, 1, 0),
                "cwc",
                "wgw",
                "cxc",
                'c', new ItemStack(Blocks.crafting_table),
                'w', "logWood",
                'g', "gearStone",
                'x', "chest"
        ));

        return recipesList;
    }
}
