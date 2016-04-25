package tech.flatstone.appliedlogistics.common.util;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import tech.flatstone.appliedlogistics.common.debug.*;

import java.util.ArrayList;
import java.util.List;

public class DebugItemHelper {
    private String inventoryName;
    ItemStack[] inventory;

    public DebugItemHelper(String inventoryName) {
        this.inventoryName = inventoryName;
        this.inventory = new ItemStack[27];

        for (int i = 0; i < inventory.length; i++) {
            inventory[i] = new ItemStack(Blocks.barrier);
        }
    }

    public void setItem(int slot, ItemStack itemStack) {
        this.inventory[slot] = itemStack;
    }

    public ItemStack registerItem() {
        ItemStack itemStack = new ItemStack(Blocks.chest);

        NBTTagCompound chestNBT = new NBTTagCompound();

        NBTTagCompound display = new NBTTagCompound();
        display.setString("Name", this.inventoryName);

        NBTTagCompound blockEntityTag = new NBTTagCompound();
        blockEntityTag.setString("id", "Chest");
        blockEntityTag.setString("Lock", "");

        NBTTagList chestContents = new NBTTagList();
        for (int i = 0; i < this.inventory.length; i++) {
            NBTTagCompound chestItem = new NBTTagCompound();
            chestItem.setByte("Slot", (byte)i);
            this.inventory[i].writeToNBT(chestItem);
            chestContents.appendTag(chestItem);
        }
        blockEntityTag.setTag("Items", chestContents);

        chestNBT.setTag("BlockEntityTag", blockEntityTag);
        chestNBT.setTag("display", display);

        itemStack.setTagCompound(chestNBT);

        return itemStack;
    }

    public static List<ItemStack> init() {
        List<ItemStack> debugItems = new ArrayList<>();

        List<IDebugChest> debugChests = new ArrayList<>();
        debugChests.add(new DebugOres1());
        debugChests.add(new DebugOres2());
        debugChests.add(new DebugOres3());
        debugChests.add(new DebugOres4());

        for (IDebugChest debugChest : debugChests) {
            debugItems.add(debugChest.getDebugChest());
        }

        return debugItems;
    }
}
