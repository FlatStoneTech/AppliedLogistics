package tech.flatstone.appliedlogistics.common.events;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.oredict.OreDictionary;
import tech.flatstone.appliedlogistics.common.items.Items;
import tech.flatstone.appliedlogistics.common.items.tools.ItemHammer;
import tech.flatstone.appliedlogistics.common.util.LogHelper;

public class EventPlayer {

    @SubscribeEvent
    public void playerInteractEvent(PlayerInteractEvent event) {
        EntityPlayer player = event.entityPlayer;
        ItemStack itemStack = player.getHeldItem();

        if (itemStack == null || !itemStack.getItem().equals(Items.ITEM_TOOL_HAMMER.item))
            return;

        ItemHammer itemHammer = (ItemHammer)itemStack.getItem();
        itemHammer.canHarvest = false;

        if (event.action == PlayerInteractEvent.Action.LEFT_CLICK_BLOCK) {
            IBlockState blockState = event.world.getBlockState(event.pos);
            ItemStack blockToCheck = new ItemStack(blockState.getBlock(), 1, blockState.getBlock().getMetaFromState(blockState));

            int[] idList = OreDictionary.getOreIDs(blockToCheck);

            if (idList.length == 0)
                return;

            String oreDictName = OreDictionary.getOreName(idList[0]);

            if (!oreDictName.startsWith("ore"))
                return;

            // OreName
            oreDictName = oreDictName.substring(3);

            if (!OreDictionary.doesOreNameExist("dust" + oreDictName))
                return;

            itemHammer.canHarvest = true;
            LogHelper.info(">>> Added to effective list...");
        }
    }
}
