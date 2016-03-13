package tech.flatstone.appliedlogistics.common.world;

import com.google.common.collect.ArrayListMultimap;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.state.pattern.BlockHelper;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
import net.minecraft.world.ChunkCoordIntPair;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.event.world.ChunkDataEvent;
import net.minecraftforge.fml.common.IWorldGenerator;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;
import tech.flatstone.appliedlogistics.common.util.LogHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WorldGen implements IWorldGenerator {
    protected static ArrayList<OreGen> oreSpawnList = new ArrayList();
    protected static ArrayList<Integer> oreDimBlackList = new ArrayList();
    protected static ArrayListMultimap<Integer, ChunkCoordIntPair> retrogenChunks = ArrayListMultimap.create();

    public static OreGen addOreGen(String name, IBlockState block, int maxVeinSize, int minY, int maxY, int chunkOccurrence, int weight) {
        OreGen oreGen = new OreGen(name, block, maxVeinSize, Blocks.stone, minY, maxY, chunkOccurrence, weight);
        oreSpawnList.add(oreGen);
        return oreGen;
    }

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
        if (!oreDimBlackList.contains(world.provider.getDimensionId()))
            for (OreGen oreGen : oreSpawnList)
                oreGen.generate(world, random, chunkX * 16, chunkZ * 16);
    }

    public void generateOres(Random random, int chunkX, int chunkZ, World world, boolean newGeneration) {
        if (!oreDimBlackList.contains(world.provider.getDimensionId())) {
            for (OreGen gen : oreSpawnList) {
                if ((newGeneration) || (retroGenEnabled(gen.name))) {
                    gen.generate(world, random, chunkX * 16, chunkZ * 16);
                }
            }
        }
    }

    private boolean retrogenEnabled() {
        /*for (OreGen gen : oreSpawnList) {

        }*/

        return true;
    }

    private boolean retroGenEnabled(String oreName) {
        return true;
    }

    @SubscribeEvent
    public void chunkSave(ChunkDataEvent.Save event) {
        NBTTagCompound nbtTagCompound = new NBTTagCompound();
        event.getData().setTag("AppliedLogistics", nbtTagCompound);
        nbtTagCompound.setBoolean("DEFAULT", true);
    }

    @SubscribeEvent
    public void chunkLoad(ChunkDataEvent.Load event) {
        int dimID = event.world.provider.getDimensionId();
        if ((!event.getData().getCompoundTag("AppliedLogistics").hasKey("DEFAULT")) && retrogenEnabled()) {
            LogHelper.info("Chunk " + event.getChunk().getChunkCoordIntPair() + " has been flagged for Ore RetroGen by Applied Logistics");
            retrogenChunks.put(dimID, event.getChunk().getChunkCoordIntPair());
        }
    }

    @SubscribeEvent
    public void serverWorldTick(TickEvent.WorldTickEvent event) {
        if ((event.side == Side.CLIENT) || (event.phase == TickEvent.Phase.START))
            return;

        int dimID = event.world.provider.getDimensionId();
        int counter = 0;

        List<ChunkCoordIntPair> chunks = retrogenChunks.get(dimID);

        if ((chunks != null) && (!chunks.isEmpty())) {
            //todo: regen more chunks per tick if tick-rate is good
            for (int i = 1; i <= 2; i++) {
                int index = chunks.size() - i;
                if (index < 0)
                    return;

                counter++;

                ChunkCoordIntPair chunkCoordIntPair = chunks.get(index);
                long worldSeed = event.world.getSeed();
                Random fmlRandom = new Random(worldSeed);
                long xSeed = fmlRandom.nextLong() >> 3;
                long zSeed = fmlRandom.nextLong() >> 3;
                fmlRandom.setSeed(xSeed * chunkCoordIntPair.chunkXPos + zSeed * chunkCoordIntPair.chunkZPos ^ worldSeed);
                generateOres(fmlRandom, chunkCoordIntPair.chunkXPos, chunkCoordIntPair.chunkZPos, event.world, false);
                chunks.remove(index);
            }
        }

        if (counter > 0)
            LogHelper.info("Retrogen was performed on " + counter + " Chunks, " + Math.max(0, chunks.size()) + " chunks remaining");
    }

    public static class OreGen {
        WorldGenMinable worldGenMinable;
        int minY;
        int maxY;
        int chunkOccurrence;
        int weight;
        String name;

        public OreGen(String name, IBlockState block, int maxVeinSize, Block replaceTarget, int minY, int maxY, int chunkOccurrence, int weight) {
            this.name = name;
            this.worldGenMinable = new WorldGenMinable(block, maxVeinSize, BlockHelper.forBlock(replaceTarget));
            this.minY = minY;
            this.maxY = maxY;
            this.chunkOccurrence = chunkOccurrence;
            this.weight = weight;
        }

        public void generate(World world, Random random, int x, int z) {
            for (int i = 0; i < chunkOccurrence; i++) {
                if (random.nextInt(100) < this.weight) {
                    BlockPos blockPos = new BlockPos(x + random.nextInt(16), this.minY + random.nextInt(this.maxY - this.minY), z + random.nextInt(16));
                    this.worldGenMinable.generate(world, random, blockPos);
                }
            }
        }
    }
}
