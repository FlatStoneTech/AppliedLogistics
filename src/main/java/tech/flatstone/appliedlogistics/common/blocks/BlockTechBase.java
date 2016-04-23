package tech.flatstone.appliedlogistics.common.blocks;

import com.google.common.collect.Maps;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.statemap.DefaultStateMapper;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import tech.flatstone.appliedlogistics.ModInfo;
import tech.flatstone.appliedlogistics.api.features.TechLevel;
import tech.flatstone.appliedlogistics.common.util.IBlockRenderer;

import java.util.Arrays;
import java.util.Map;

public abstract class BlockTechBase extends BlockTileBase implements IBlockRenderer {
    protected static final PropertyEnum TECHLEVEL = PropertyEnum.create("tech", TechLevel.class);
    private TechLevel[] techLevels;

    public BlockTechBase(Material material, String resourcePath, TechLevel... techLevels) {
        super(material, resourcePath);
        this.techLevels = techLevels;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerBlockRenderer() {
        final String resourcePath = String.format("%s:%s-", ModInfo.MOD_ID, this.resourcePath);
        final String badPath = String.format("%s:badblock", ModInfo.MOD_ID);

        ModelLoader.setCustomStateMapper(this, new DefaultStateMapper() {
            @Override
            protected ModelResourceLocation getModelResourceLocation(IBlockState state) {
                Map<IProperty, Comparable> blockStates = Maps.newLinkedHashMap(state.getProperties());

                if (!Arrays.asList(techLevels).contains(blockStates.get(TECHLEVEL)))
                    return new ModelResourceLocation(badPath, "");

                if (blockStates.containsKey(TECHLEVEL))
                    blockStates.remove(TECHLEVEL);

                return new ModelResourceLocation(resourcePath + ((TechLevel) state.getValue(TECHLEVEL)).getName(), getPropertyString(blockStates));
            }
        });
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerBlockItemRenderer() {
        final String resourcePath = String.format("%s:%s-", ModInfo.MOD_ID, this.resourcePath);
        final String badPath = String.format("%s:badblock", ModInfo.MOD_ID);

        for (TechLevel techLevel : TechLevel.values()) {
            if (!Arrays.asList(techLevels).contains(techLevel)) {
                ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), techLevel.getMeta(), new ModelResourceLocation(badPath, "inventory"));
            } else {
                ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), techLevel.getMeta(), new ModelResourceLocation(resourcePath + techLevel.getName(), "inventory"));
            }
        }
    }
}
