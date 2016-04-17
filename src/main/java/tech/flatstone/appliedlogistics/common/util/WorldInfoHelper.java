package tech.flatstone.appliedlogistics.common.util;

import net.minecraft.world.WorldServer;
import net.minecraftforge.common.DimensionManager;

public class WorldInfoHelper {
    public static double getTps(WorldServer worldServer){
        long totalTick = 0L;
        int dimensions = 0;

        for (long tick : worldServer.getMinecraftServer().tickTimeArray) {
            totalTick +=tick;
            dimensions++;
        }

        double meanTick = (totalTick / dimensions) * 1.0E-6D;

        return Math.min(1000.0 / meanTick, 20);

    }

    public static double getTps() {
        long totalTick = 0L;
        int worlds = 0;

        for (WorldServer world : DimensionManager.getWorlds()){
            totalTick += getTps(world);
            worlds ++;
        }

        return totalTick/worlds;
    }
}
