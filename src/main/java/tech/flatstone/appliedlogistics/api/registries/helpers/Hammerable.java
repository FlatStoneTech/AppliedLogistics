package tech.flatstone.appliedlogistics.api.registries.helpers;

import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;

public class Hammerable {
    public ItemStack inItemStack;
    public ItemStack outItemStack;
    public float chance;
    public float luckMultiplier;

    public Hammerable(ItemStack inItemStack, ItemStack outItemStack, float chance, float luckMultiplier) {
        this.inItemStack = inItemStack;
        this.outItemStack = outItemStack;
        this.chance = chance;
        this.luckMultiplier = luckMultiplier;
    }
}
