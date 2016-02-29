package tech.flatstone.appliedlogistics.api.registries.helpers;

import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;

public class Smashable {
    public IBlockState inBlock;
    public ItemStack outItemStack;
    public float chance;
    public float luckMultiplier;

    public Smashable(IBlockState inBlock, ItemStack outItemStack, float chance, float luckMultiplier) {
        this.inBlock = inBlock;
        this.outItemStack = outItemStack;
        this.chance = chance;
        this.luckMultiplier = luckMultiplier;
    }
}
