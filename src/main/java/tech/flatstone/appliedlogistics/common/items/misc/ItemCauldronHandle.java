package tech.flatstone.appliedlogistics.common.items.misc;

import net.minecraftforge.client.model.ModelLoader;
import tech.flatstone.appliedlogistics.AppliedLogisticsCreativeTabs;
import tech.flatstone.appliedlogistics.common.items.ItemBase;
import tech.flatstone.appliedlogistics.common.util.ModelRegistration;

public class ItemCauldronHandle extends ItemBase {

    public ItemCauldronHandle() {
        super("misc/cauldron_handle");
        this.setCreativeTab(AppliedLogisticsCreativeTabs.GENERAL);
        this.setInternalName("cauldron_handle");
    }

    @Override
    public void registerItemRenderer() {
        super.registerItemRenderer();

        ModelLoader.registerItemVariants(this, ModelRegistration.CAULDRON_HANDLE);
    }
}
