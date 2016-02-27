package tech.flatstone.appliedlogistics.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.StatCollector;
import net.minecraftforge.fml.common.registry.GameRegistry;
import tech.flatstone.appliedlogistics.common.AppliedLogisticsCreativeTabs;
import tech.flatstone.appliedlogistics.common.blocks.ore.BlockOre;
import tech.flatstone.appliedlogistics.common.items.ore.ItemOre;
import tech.flatstone.appliedlogistics.common.util.IBlockRenderer;
import tech.flatstone.appliedlogistics.common.util.LogHelper;

public enum Blocks {
    // Ore
    BLOCK_ORE("ore", new BlockOre(), ItemOre.class, AppliedLogisticsCreativeTabs.tabGeneral),
    //BLOCK_ORE_BLOCK("OreBlock", new BlockOreBlock(), AppliedLogisticsCreativeTabs.tabGeneral),

    ;

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

    public static void preInit() {
        for (Blocks b : Blocks.values()) {
            ((BlockBase) b.block).preInit();
        }
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
        GameRegistry.registerBlock(block.setCreativeTab(creativeTabs).setUnlocalizedName(internalName), itemBlockClass, internalName);

        if (block instanceof IBlockRenderer) {
            ((IBlockRenderer) block).registerBlockRenderer();
        }

        LogHelper.info("Registered Block: " + internalName);
    }
}