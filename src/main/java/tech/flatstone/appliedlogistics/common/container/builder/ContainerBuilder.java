package tech.flatstone.appliedlogistics.common.container.builder;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import tech.flatstone.appliedlogistics.common.container.ContainerBase;
import tech.flatstone.appliedlogistics.common.container.slot.SlotNormal;
import tech.flatstone.appliedlogistics.common.container.slot.SlotRestrictedInput;
import tech.flatstone.appliedlogistics.common.items.Items;

import java.util.ArrayList;

public class ContainerBuilder extends ContainerBase {
    IInventory inventory;

    public ContainerBuilder(InventoryPlayer inventoryPlayer, TileEntity tileEntity) {
        super(inventoryPlayer, tileEntity);
        this.inventory = (IInventory) tileEntity;

        addSlotToContainer(new SlotRestrictedInput(inventory, 0, 8, 18, new ArrayList<ItemStack>() {{add(new ItemStack(Items.ITEM_PLAN.item));}}, new ItemStack(Items.ITEM_PLAN.item)));
        //addSlotToContainer(new SlotNormal(inventory, 0, 1, 18));

        bindPlayerInventory(inventoryPlayer, 0, 138);
    }
}
