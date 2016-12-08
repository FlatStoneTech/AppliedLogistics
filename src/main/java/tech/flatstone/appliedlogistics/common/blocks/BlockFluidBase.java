package tech.flatstone.appliedlogistics.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import tech.flatstone.appliedlogistics.ModInfo;
import tech.flatstone.appliedlogistics.common.util.IBlockRenderer;

import java.util.Random;

public class BlockFluidBase extends BlockFluidClassic implements IBlockRenderer {
    public BlockFluidBase(Fluid fluid, Material material) {
        super(fluid, material);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerBlockRenderer() {
        String resourcePath = String.format("%s:fluids", ModInfo.MOD_ID);
        final ModelResourceLocation modelResourceLocation = new ModelResourceLocation(resourcePath, this.getFluid().getName());
        ModelLoader.setCustomStateMapper(this, new StateMapperBase() {
            @Override
            protected ModelResourceLocation getModelResourceLocation(IBlockState state) {
                return modelResourceLocation;
            }
        });
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerBlockItemRenderer() {
        Item fluidItem = Item.getItemFromBlock(this);
        String resourcePath = String.format("%s:fluids", ModInfo.MOD_ID);
        final ModelResourceLocation modelResourceLocation = new ModelResourceLocation(resourcePath, this.getFluid().getName());

        ModelLoader.setCustomModelResourceLocation(fluidItem, 0, modelResourceLocation);
    }

    @Override
    public void updateTick(World world, BlockPos pos, IBlockState state, Random rand) {
        super.updateTick(world, pos, state, rand);

        // Check to see if surrounding blocks are flammable...
        if (this.blockMaterial == Material.LAVA) {
            int i = rand.nextInt(10);

            if (i > 0) {
                BlockPos blockPos = pos;

                for (int j = 0; j < i; ++j) {
                    blockPos = blockPos.add(rand.nextInt(4) - 1, 1, rand.nextInt(4) - 1);

                    if (blockPos.getY() >= 0 && blockPos.getY() < world.getHeight() && !world.isBlockLoaded(blockPos))
                        return;

                    Block block = world.getBlockState(blockPos).getBlock();

                    if (block.isAir(world.getBlockState(blockPos), world, blockPos)) {
                        if (this.isSurroundingBlockFlammable(world, blockPos)) {
                            world.setBlockState(blockPos, Blocks.FIRE.getDefaultState());
                            return;
                        }
                    } else if (block.getMaterial(world.getBlockState(blockPos)).blocksMovement()) {
                        return;
                    }
                }
            } else {
                for (int j = 0; j < 10; ++j) {
                    BlockPos blockPos1 = pos.add(rand.nextInt(4) - 1, 0, rand.nextInt(4) - 1);

                    if (blockPos1.getY() >= 0 && blockPos1.getY() < 256 && !world.isBlockLoaded(blockPos1))
                        return;

                    if (world.isAirBlock(blockPos1.up()) && this.getCanBlockBurn(world, blockPos1))
                        world.setBlockState(blockPos1.up(), Blocks.FIRE.getDefaultState());
                }
            }
        }
    }

    @Override
    public void onBlockAdded(World world, BlockPos pos, IBlockState state) {
        super.onBlockAdded(world, pos, state);

        this.checkForMixing(world, pos, state);
    }

    @Override
    public void neighborChanged(IBlockState state, World world, BlockPos pos, Block neighborBlock) {
        super.neighborChanged(state, world, pos, neighborBlock);

        this.checkForMixing(world, pos, state);
    }

    protected boolean isSurroundingBlockFlammable(World worldIn, BlockPos pos) {
        return getCanBlockBurn(worldIn, pos);
    }

    private boolean getCanBlockBurn(World worldIn, BlockPos pos) {
        return true;
    }

    public boolean checkForMixing(World worldIn, BlockPos pos, IBlockState state) {
        if (this.blockMaterial == Material.LAVA) {
            boolean flag = false;

            for (EnumFacing enumfacing : EnumFacing.values()) {
                IBlockState blockState = worldIn.getBlockState(pos.offset(enumfacing));

                if (enumfacing != EnumFacing.DOWN && blockState.getMaterial() == Material.WATER) {
                    flag = true;
                    break;
                }

                if (enumfacing != EnumFacing.DOWN && blockState.getMaterial().isLiquid() && !blockState.getBlock().equals(this)) {
                    flag = true;
                    break;
                }
            }

            if (flag) {
                Integer integer = (Integer) state.getValue(LEVEL);

                if (integer.intValue() == 0) {
                    worldIn.setBlockState(pos, Blocks.OBSIDIAN.getDefaultState());
                    this.triggerMixEffects(worldIn, pos);
                    return true;
                }

                if (integer.intValue() <= 4) {
                    worldIn.setBlockState(pos, Blocks.COBBLESTONE.getDefaultState());
                    this.triggerMixEffects(worldIn, pos);
                    return true;
                }
            }
        }

        return false;
    }

    protected void triggerMixEffects(World worldIn, BlockPos pos) {
        double d0 = (double) pos.getX();
        double d1 = (double) pos.getY();
        double d2 = (double) pos.getZ();
        worldIn.playSound((EntityPlayer) null, pos, SoundEvents.BLOCK_LAVA_EXTINGUISH, SoundCategory.BLOCKS, 0.5F, 2.6F + (worldIn.rand.nextFloat() - worldIn.rand.nextFloat()) * 0.8F);

        for (int i = 0; i < 8; ++i) {
            worldIn.spawnParticle(EnumParticleTypes.SMOKE_LARGE, d0 + Math.random(), d1 + 1.2D, d2 + Math.random(), 0.0D, 0.0D, 0.0D, new int[0]);
        }
    }
}
