package tech.flatstone.appliedlogistics.proxy;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.oredict.OreDictionary;
import tech.flatstone.appliedlogistics.AppliedLogistics;
import tech.flatstone.appliedlogistics.api.features.EnumOreType;
import tech.flatstone.appliedlogistics.api.registries.PlanRegistry;
import tech.flatstone.appliedlogistics.api.registries.HammerRegistry;
import tech.flatstone.appliedlogistics.client.gui.GuiHandler;
import tech.flatstone.appliedlogistics.common.blocks.Blocks;
import tech.flatstone.appliedlogistics.common.items.Items;
import tech.flatstone.appliedlogistics.common.items.plans.PlanPulverizer;
import tech.flatstone.appliedlogistics.common.util.EnumOres;
import tech.flatstone.appliedlogistics.common.util.IProvideEvent;
import tech.flatstone.appliedlogistics.common.util.IProvideRecipe;
import tech.flatstone.appliedlogistics.common.util.IProvideSmelting;

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
        for (int i = 0; i < Items.values().length; i++) {
            Item item = Items.values()[i].getItem();
            if (item instanceof IProvideSmelting) {
                ((IProvideSmelting) item).RegisterSmelting();
            }
        }

        for (int i = 0; i < Blocks.values().length; i++) {
            Block block = Blocks.values()[i].getBlock();
            if (block instanceof IProvideSmelting) {
                ((IProvideSmelting) block).RegisterSmelting();
            }
        }
    }

    @Override
    public void registerEvents() {
        for (int i = 0; i < Items.values().length; i++) {
            Item item = Items.values()[i].getItem();
            if (item instanceof IProvideEvent) {
                MinecraftForge.EVENT_BUS.register(item);
            }
        }

        for (int i = 0; i < Blocks.values().length; i++) {
            Block block = Blocks.values()[i].getBlock();
            if (block instanceof IProvideEvent) {
                MinecraftForge.EVENT_BUS.register(block);
            }
        }

    }

    @Override
    public void registerRecipes() {
        // Add Recipes provided by the items
        for (int i = 0; i < Items.values().length; i++) {
            Item item = Items.values()[i].getItem();
            if (item instanceof IProvideRecipe) {
                ((IProvideRecipe) item).RegisterRecipes();
            }
        }

        // Add Recipes provided by the blocks
        for (int i = 0; i < Blocks.values().length; i++) {
            Block block = Blocks.values()[i].getBlock();
            if (block instanceof IProvideRecipe) {
                ((IProvideRecipe) block).RegisterRecipes();
            }
        }
    }

    @Override
    public void registerBlueprints() {
        PlanRegistry.registerPlan(new PlanPulverizer());
    }

    @Override
    public void registerGUIs() {
        NetworkRegistry.INSTANCE.registerGuiHandler(AppliedLogistics.instance, new GuiHandler());
    }
}
