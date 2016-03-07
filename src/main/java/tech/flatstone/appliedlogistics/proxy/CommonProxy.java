package tech.flatstone.appliedlogistics.proxy;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import tech.flatstone.appliedlogistics.api.features.EnumOreType;
import tech.flatstone.appliedlogistics.api.registries.HammerRegistry;
import tech.flatstone.appliedlogistics.common.blocks.Blocks;
import tech.flatstone.appliedlogistics.common.events.EventPlayer;
import tech.flatstone.appliedlogistics.common.items.Items;
import tech.flatstone.appliedlogistics.common.util.EnumOres;
import tech.flatstone.appliedlogistics.common.util.IProvideRecipe;
import tech.flatstone.appliedlogistics.common.util.LogHelper;

import java.util.ArrayList;
import java.util.List;

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
            if (EnumOres.byMeta(i).isTypeSet(EnumOreType.ORE))
                OreDictionary.registerOre("ore" + oreName, new ItemStack(Blocks.BLOCK_ORE.block, 1, i));

            // Register Ore Block
            if (EnumOres.byMeta(i).isTypeSet(EnumOreType.BLOCK))
                OreDictionary.registerOre("block" + oreName, new ItemStack(Blocks.BLOCK_ORE_BLOCK.block, 1, i));

            // Register Ingot
            if (EnumOres.byMeta(i).isTypeSet(EnumOreType.INGOT))
                OreDictionary.registerOre("ingot" + oreName, new ItemStack(Items.ITEM_ORE_INGOT.item, 1, i));

            // Register Dusts
            if (EnumOres.byMeta(i).isTypeSet(EnumOreType.DUST))
                OreDictionary.registerOre("dust" + oreName, new ItemStack(Items.ITEM_ORE_DUST.item, 1, i));

            // Register Nuggets
            if (EnumOres.byMeta(i).isTypeSet(EnumOreType.NUGGET))
                OreDictionary.registerOre("nugget" + oreName, new ItemStack(Items.ITEM_ORE_NUGGET.item, 1, i));

            // Register Gears
            if (EnumOres.byMeta(i).isTypeSet(EnumOreType.GEAR))
                OreDictionary.registerOre("gear" + oreName, new ItemStack(Items.ITEM_MATERIAL_GEAR.item, 1, i));
        }
    }

    @Override
    public void registerHammerRecipes() {
        // Ores -> Dust
        for (int i = 0; i < EnumOres.values().length; i++) {
            String oreName = EnumOres.byMeta(i).getName();

            // Register Hammer
            if (EnumOres.byMeta(i).isTypeSet(EnumOreType.DUST) && EnumOres.byMeta(i).isTypeSet(EnumOreType.ORE))
                HammerRegistry.registerOreDictOre(oreName);
        }

        // Coal
        HammerRegistry.register(new ItemStack(net.minecraft.init.Blocks.coal_ore), new ItemStack(Items.ITEM_ORE_NUGGET.item, 1, EnumOres.DIAMOND.getMeta()), 0.005f, 0.01f);
        HammerRegistry.register(new ItemStack(net.minecraft.init.Blocks.coal_ore), new ItemStack(net.minecraft.init.Items.coal, 2), 1.0f, 0.5f);
        HammerRegistry.register(new ItemStack(net.minecraft.init.Blocks.coal_ore), new ItemStack(net.minecraft.init.Items.coal, 1), 0.5f, 0.5f);
        HammerRegistry.register(new ItemStack(net.minecraft.init.Blocks.coal_ore), new ItemStack(net.minecraft.init.Items.coal, 1), 0.5f, 0.5f);

        // Redstone
        HammerRegistry.register(new ItemStack(net.minecraft.init.Blocks.redstone_ore), new ItemStack(net.minecraft.init.Items.redstone, 6), 1.0f, 0.5f);
        HammerRegistry.register(new ItemStack(net.minecraft.init.Blocks.redstone_ore), new ItemStack(net.minecraft.init.Items.redstone, 4), 0.1f, 0.1f);
        HammerRegistry.register(new ItemStack(net.minecraft.init.Blocks.redstone_ore), new ItemStack(net.minecraft.init.Items.redstone, 2), 0.0f, 0.5f);
        HammerRegistry.register(new ItemStack(net.minecraft.init.Blocks.lit_redstone_ore), new ItemStack(net.minecraft.init.Items.redstone, 6), 1.0f, 0.5f);
        HammerRegistry.register(new ItemStack(net.minecraft.init.Blocks.lit_redstone_ore), new ItemStack(net.minecraft.init.Items.redstone, 4), 0.1f, 0.1f);
        HammerRegistry.register(new ItemStack(net.minecraft.init.Blocks.lit_redstone_ore), new ItemStack(net.minecraft.init.Items.redstone, 2), 0.0f, 0.5f);
    }

    @Override
    public void registerFurnaceRecipes() {
        for (int i = 0; i < EnumOres.values().length; i++) {
            // Register Ore -> Ingot
            if (EnumOres.byMeta(i).isTypeSet(EnumOreType.ORE) && EnumOres.byMeta(i).isTypeSet(EnumOreType.INGOT))
                GameRegistry.addSmelting(new ItemStack(Blocks.BLOCK_ORE.block, 1, i), new ItemStack(Items.ITEM_ORE_INGOT.item, 1, i), 0);

            // Register Dust -> Ingot
            if (EnumOres.byMeta(i).isTypeSet(EnumOreType.DUST) && EnumOres.byMeta(i).isTypeSet(EnumOreType.INGOT))
                GameRegistry.addSmelting(new ItemStack(Items.ITEM_ORE_DUST.item, 1, i), new ItemStack(Items.ITEM_ORE_INGOT.item, 1, i), 0);
        }
    }

    @Override
    public void registerEvents() {
        MinecraftForge.EVENT_BUS.register(new EventPlayer());
    }

    @Override
    public void registerRecipes() {
        for (int i = 0; i < EnumOres.values().length; i++) {
            if (EnumOres.byMeta(i).isTypeSet(EnumOreType.INGOT) && EnumOres.byMeta(i).isTypeSet(EnumOreType.BLOCK)) {
                // Register 9x Ingot -> Block
                GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Blocks.BLOCK_ORE_BLOCK.block, 1, i),
                        "xxx",
                        "xxx",
                        "xxx",
                        'x', "ingot" + EnumOres.byMeta(i).getName())
                );

                // Register Block -> 9x Ingot
                GameRegistry.addShapelessRecipe(new ItemStack(Items.ITEM_ORE_INGOT.item, 9, i), new ItemStack(Blocks.BLOCK_ORE_BLOCK.block, 1, i));
            }

            if (EnumOres.byMeta(i).isTypeSet(EnumOreType.NUGGET) && EnumOres.byMeta(i).isTypeSet(EnumOreType.INGOT)) {
                // Register 9x Nugget -> Ingot
                GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Items.ITEM_ORE_INGOT.item, 1, i),
                        "xxx",
                        "xxx",
                        "xxx",
                        'x', "nugget" + EnumOres.byMeta(i).getName())
                );

                // Register Ingot -> 9x Nugget
                GameRegistry.addShapelessRecipe(new ItemStack(Items.ITEM_ORE_NUGGET.item, 9, i), new ItemStack(Items.ITEM_ORE_INGOT.item, 1, i));
            }

            if (EnumOres.byMeta(i) == EnumOres.IRON) {
                // Register 9x Nugget -> Ingot
                GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(net.minecraft.init.Items.iron_ingot, 1),
                        "xxx",
                        "xxx",
                        "xxx",
                        'x', "nugget" + EnumOres.byMeta(i).getName())
                );

                // Register Ingot -> 9x Nugget
                GameRegistry.addShapelessRecipe(new ItemStack(Items.ITEM_ORE_NUGGET.item, 9, i), new ItemStack(net.minecraft.init.Items.iron_ingot, 1));
            }

            if (EnumOres.byMeta(i) == EnumOres.DIAMOND) {
                // Register 9x Nugget -> Diamond
                GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(net.minecraft.init.Items.diamond, 1),
                        "xxx",
                        "xxx",
                        "xxx",
                        'x', "nugget" + EnumOres.byMeta(i).getName())
                );

                // Register Diamond -> 9x Nugget
                GameRegistry.addShapelessRecipe(new ItemStack(Items.ITEM_ORE_NUGGET.item, 9, i), new ItemStack(net.minecraft.init.Items.diamond, 1));
            }

            // Register Gears that use ingots
            if (EnumOres.byMeta(i).isTypeSet(EnumOreType.GEAR) && EnumOres.byMeta(i).isTypeSet(EnumOreType.INGOT)) {
                GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(Items.ITEM_MATERIAL_GEAR.item, 1, i),
                        " x ",
                        "xyx",
                        " x ",
                        'x', "ingot" + EnumOres.byMeta(i).getName(),
                        'y', "ingotIron")
                );
            }

            // Register Vanilla Material Gears
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

        // Add Recipes provided by the blocks and items
        List<ShapedOreRecipe> recipesList = new ArrayList();

        for (int i = 0; i < Items.values().length; i++) {
            Item item = Items.values()[i].getItem();
            if (item instanceof IProvideRecipe) {
                recipesList.addAll(((IProvideRecipe) item).RecipesList());
            }
        }

        for (int i = 0; i < Blocks.values().length; i++) {
            Block block = Blocks.values()[i].getBlock();
            if (block instanceof IProvideRecipe) {
                recipesList.addAll(((IProvideRecipe) block).RecipesList());
            }
        }

        for (ShapedOreRecipe recipe : recipesList) {
            GameRegistry.addRecipe(recipe);
        }
    }
}
