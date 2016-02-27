package tech.flatstone.appliedlogistics.proxy;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;
import tech.flatstone.appliedlogistics.common.blocks.Blocks;
import tech.flatstone.appliedlogistics.common.items.Items;
import tech.flatstone.appliedlogistics.common.util.EnumOres;

public abstract class CommonProxy implements IProxy {
    @Override
    public void registerBlocks() {
        Blocks.registerBlocks();
    }

    @Override
    public void registerItems() {
        Items.registerItems();
    }

    @Override
    public void registerOreDict() {
        for (int i = 0; i < EnumOres.values().length; i++) {
            String oreName = EnumOres.byMeta(i).getName();

            // Register Ore
            OreDictionary.registerOre("ore" + oreName, new ItemStack(Blocks.BLOCK_ORE.block, 1, i));

            // Register Ore Block
            OreDictionary.registerOre("block" + oreName, new ItemStack(Blocks.BLOCK_ORE_BLOCK.block, 1, i));

            // Register Ingot
            OreDictionary.registerOre("ingot" + oreName, new ItemStack(Items.ITEM_ORE_INGOT.item, 1, i));

            // Register Dusts
            OreDictionary.registerOre("dust" + oreName, new ItemStack(Items.ITEM_ORE_DUST.item, 1, i));
        }
    }

    @Override
    public void registerFurnaceRecipes() {
        for (int i = 0; i < EnumOres.values().length; i++) {
            // Register Ore -> Ingot
            GameRegistry.addSmelting(new ItemStack(Blocks.BLOCK_ORE.block, 1, i), new ItemStack(Items.ITEM_ORE_INGOT.item, 1, i), 0);

            // Register Dust -> Ingot
            GameRegistry.addSmelting(new ItemStack(Items.ITEM_ORE_DUST.item, 1, i), new ItemStack(Items.ITEM_ORE_INGOT.item, 1, i), 0);
        }
    }

    @Override
    public void registerRecipes() {
        for (int i = 0; i < EnumOres.values().length; i++) {
            if (!EnumOres.byMeta(i).isVanillaGen()) {
                // Register 9x Ingot -> Block
                GameRegistry.addRecipe(new ItemStack(Blocks.BLOCK_ORE_BLOCK.block, 1, i),
                        "xxx",
                        "xxx",
                        "xxx",
                        'x', new ItemStack(Items.ITEM_ORE_INGOT.item, 1, i)
                );

                // Register Block -> 9x Ingot
                GameRegistry.addShapelessRecipe(new ItemStack(Items.ITEM_ORE_INGOT.item, 9, i), new ItemStack(Blocks.BLOCK_ORE_BLOCK.block, 1, i));
            }
        }
    }
}
