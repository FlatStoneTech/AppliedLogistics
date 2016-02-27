package tech.flatstone.appliedlogistics.proxy;

import tech.flatstone.appliedlogistics.common.blocks.Blocks;

public abstract class CommonProxy implements IProxy {
    @Override
    public void registerBlocks() {
        Blocks.registerBlocks();
    }
}
