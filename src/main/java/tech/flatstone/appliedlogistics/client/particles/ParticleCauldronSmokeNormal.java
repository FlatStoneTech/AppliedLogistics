package tech.flatstone.appliedlogistics.client.particles;

import net.minecraft.client.particle.IParticleFactory;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleSmokeNormal;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import tech.flatstone.appliedlogistics.common.blocks.misc.BlockCauldron;

import javax.annotation.Nullable;

public class ParticleCauldronSmokeNormal extends ParticleSmokeNormal {
    private static final double MAX_HEIGHT = 0.15;

    protected ParticleCauldronSmokeNormal(World worldIn, double xCoordIn, double yCoordIn, double zCoordIn, double xSpeedIn, double ySpeedIn, double zSpeedIn, float scale) {
        super(worldIn, xCoordIn, yCoordIn, zCoordIn, xSpeedIn, ySpeedIn, zSpeedIn, scale);
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
        double x = MathHelper.floor(posX);
        double y = MathHelper.floor(posY);
        double z = MathHelper.floor(posZ);
        boolean isCauldron = world.getBlockState(new BlockPos(x, y, z)).getBlock() instanceof BlockCauldron;
        boolean isCollided = false;
        y = posY - y;
        if (isCauldron) {
            x = posX - x;
            z = posZ - z;
            if (y >= MAX_HEIGHT && x <= 0.9375 && x >= 0.0625 && z <= 0.9375 && z >= 0.0625) {
                isCollided = true;
                motionY = 0;
                motionX *= 1.1;
                motionZ *= 1.1;
            }
        }
        if (isCauldron && isCollided && y > MAX_HEIGHT)
            move(0, MAX_HEIGHT - y, 0);
    }

    @SideOnly(Side.CLIENT)
    public static class Factory implements IParticleFactory {
        @Nullable
        @Override
        public Particle createParticle(int particleID, World worldIn, double xCoordIn, double yCoordIn, double zCoordIn, double xSpeedIn, double ySpeedIn, double zSpeedIn, int... p_178902_15_) {
            return new ParticleCauldronSmokeNormal(worldIn, xCoordIn, yCoordIn, zCoordIn, xSpeedIn, ySpeedIn, zSpeedIn, 1.0F);
        }
    }
}
