package tech.flatstone.appliedlogistics.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.StatCollector;
import net.minecraftforge.fml.common.registry.GameRegistry;
import tech.flatstone.appliedlogistics.common.AppliedLogisticsCreativeTabs;
import tech.flatstone.appliedlogistics.common.blocks.builder.BlockBuilder;
import tech.flatstone.appliedlogistics.common.blocks.ore.BlockOre;
import tech.flatstone.appliedlogistics.common.blocks.ore.BlockOreBlock;
import tech.flatstone.appliedlogistics.common.items.builder.ItemBuilder;
import tech.flatstone.appliedlogistics.common.items.ore.ItemOre;
import tech.flatstone.appliedlogistics.common.items.ore.ItemOreBlock;
import tech.flatstone.appliedlogistics.common.util.IBlockRenderer;
import tech.flatstone.appliedlogistics.common.util.LogHelper;

public enum Blocks {
    // Ore
    BLOCK_ORE("ore", new BlockOre(), ItemOre.class, AppliedLogisticsCreativeTabs.tabOres),
    BLOCK_ORE_BLOCK("oreBlock", new BlockOreBlock(), ItemOreBlock.class, AppliedLogisticsCreativeTabs.tabOres),

    BLOCK_BUILDER("builder", new BlockBuilder(), ItemBuilder.class, AppliedLogisticsCreativeTabs.tabGeneral),;

    public final Block block;
    private final String internalName;
    private final Class<? extends ItemBlock> itemBlockClass;
    private final CreativeTabs creativeTabs;
    private final boolean defaultRenderer;

    Blocks(String internalName, Block block) {
        this(internalName, block, ItemBlock.class, null, true);
    }

    Blocks(String internalName, Block block, CreativeTabs creativeTabs) {
        this(internalName, block, ItemBlock.class, creativeTabs, true);
    }

    Blocks(String internalName, Block block, Class<? extends ItemBlock> itemBlockClass) {
        this(internalName, block, itemBlockClass, null, true);
    }

    Blocks(String internalName, Block block, Class<? extends ItemBlock> itemBlockClass, CreativeTabs creativeTabs) {
        this(internalName, block, itemBlockClass, creativeTabs, true);
    }

    Blocks(String internalName, Block block, Class<? extends ItemBlock> itemBlockClass, CreativeTabs creativeTabs, boolean useDefaultRenderer) {
        this.internalName = internalName;
        this.block = block;
        this.itemBlockClass = itemBlockClass;
        this.creativeTabs = creativeTabs;
        this.defaultRenderer = useDefaultRenderer;
    }

    public static void registerBlocks() {
        for (Blocks b : Blocks.values()) {
            b.registerBlock();
        }
    }


    public String getInternalName() {
        return internalName;
    }

    public String getStatName() {
        return StatCollector.translateToLocal(block.getUnlocalizedName().replace("tileentities.", "blocks."));
    }

    private void registerBlock() {
        // Register Block in Game Registry
        GameRegistry.registerBlock(block.setCreativeTab(creativeTabs).setUnlocalizedName(internalName), itemBlockClass, internalName);

        // If bock has Render Info, Register Renderer
        if (block instanceof IBlockRenderer) {
            ((IBlockRenderer) block).registerBlockRenderer();
        }

        LogHelper.info("Registered Block: " + internalName);
    }

    public Block getBlock() {
        return this.block;
    }
}