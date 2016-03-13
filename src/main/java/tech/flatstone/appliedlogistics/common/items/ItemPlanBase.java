package tech.flatstone.appliedlogistics.common.items;

import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.ShapedOreRecipe;
import tech.flatstone.appliedlogistics.ModInfo;
import tech.flatstone.appliedlogistics.api.features.IMachinePlan;
import tech.flatstone.appliedlogistics.api.registries.PlanRegistry;
import tech.flatstone.appliedlogistics.common.util.IItemRenderer;
import tech.flatstone.appliedlogistics.common.util.IProvideRecipe;

import java.util.List;

public class ItemPlanBase extends ItemBase implements IProvideRecipe, IItemRenderer {
    public static final String TAG_PLANTYPE = "PlanType";

    public ItemPlanBase() {
        this.setHasSubtypes(true);
    }

    @Override
    public void getSubItems(Item itemIn, CreativeTabs tab, List<ItemStack> subItems) {
        // Add blank plan
        subItems.add(new ItemStack(this));

        // Add registered plans
        for (Item plan : PlanRegistry.getPlanItems()) {
            ItemStack stack = new ItemStack(this);
            setTagForPlan(stack, plan);

            subItems.add(stack);
        }
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        Item plan = PlanRegistry.getPlanAsItem(getTagForPlan(stack));

        if (plan != null) {
            String planName = plan.getUnlocalizedName();
            return planName;
        }

        return super.getUnlocalizedName() + ".blank";
    }

    @Override
    public void registerItemRenderer() {
        ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(ModInfo.MOD_ID + ":plans/itemPlan", "inventory"));
    }

    @Override
    public void RegisterRecipes() {
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(this, 4),
                "xyx",
                "yzy",
                "xyx",
                'x', "stickWood",
                'y', "plankWood",
                'z', new ItemStack(Items.paper)
        ));
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
        Item plan = PlanRegistry.getPlanAsItem(getTagForPlan(stack));

        if (plan != null && plan instanceof IMachinePlan) {
            String techLevel = ((IMachinePlan)plan).getUnlocalizedPlanDescription(); //todo: statscollector
            tooltip.add(techLevel);
        }
    }

    public static void setTagForPlan(ItemStack stack, Item plan) {
        NBTTagCompound tagCompound = new NBTTagCompound();
        tagCompound.setString(TAG_PLANTYPE, plan.getUnlocalizedName());
        stack.setTagCompound(tagCompound);
    }

    public static String getTagForPlan(ItemStack stack) {
        NBTTagCompound tagCompound;

        if (stack.hasTagCompound()) {
            tagCompound = stack.getTagCompound();
        } else {
            tagCompound = new NBTTagCompound();
        }
        String plan = tagCompound.getString(TAG_PLANTYPE);

        return plan;
    }

}
