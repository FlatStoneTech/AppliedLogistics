package tech.flatstone.appliedlogistics.common.events;

import net.minecraft.block.state.IBlockState;
import net.minecraftforge.event.world.BlockEvent;

public class EventWorld {
    public static void blockBreakEvent(BlockEvent.BreakEvent event) {
        IBlockState state = event.state;


    }
}
