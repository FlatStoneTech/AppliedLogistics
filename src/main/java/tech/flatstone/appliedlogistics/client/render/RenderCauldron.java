package tech.flatstone.appliedlogistics.client.render;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.renderer.vertex.VertexFormatElement;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.client.model.animation.FastTESR;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import org.lwjgl.opengl.GL11;
import javax.vecmath.Matrix4f;
import javax.vecmath.Vector4f;
import tech.flatstone.appliedlogistics.ModInfo;
import tech.flatstone.appliedlogistics.client.util.ModelTransformer;
import tech.flatstone.appliedlogistics.common.blocks.misc.BlockCauldron;
import tech.flatstone.appliedlogistics.common.tileentities.misc.TileEntityCauldron;
import tech.flatstone.appliedlogistics.common.util.ModelRegistration;

public class RenderCauldron extends FastTESR<TileEntityCauldron> {
    @Override
    public void renderTileEntityFast(final TileEntityCauldron cauldronTE, double x, double y, double z, final float partialTicks, int destroyStage, VertexBuffer VertexBuffer) {
        IBlockState state = cauldronTE.getWorld().getBlockState(cauldronTE.getPos());
        if (!(state.getBlock() instanceof BlockCauldron))
            return;

        VertexBuffer vb = Tessellator.getInstance().getBuffer();
        double x2 = x + 0.1875;
        double z2 = z + 0.1875;

        if (MinecraftForgeClient.getRenderPass() == 1) {
            BlockCauldron cauldronBlock = (BlockCauldron) state.getBlock();
            int level = 0;//cauldronBlock.getWaterLevel(state);
            if (level > 0) {
                vb.finishDrawing();
                double y2 = y + 0.4375;
                if (level > 1)
                    y2 += 0.1875 * (level - 1);

                float alpha = 0;//cauldronTE.getAlpha();
                Vec3d pos = new Vec3d(x2, y2, z2);
                renderTexturedSide(pos, alpha, "minecraft:blocks/water_still");
                renderTexturedSide(pos, 1 - alpha, ModInfo.MOD_ID + ":fluids/kaolinite_precursor_still");

                vb.begin(GL11.GL_QUADS, DefaultVertexFormats.BLOCK);
            }
        } else {
            int threshold = 80;
            int progress = 0;//cauldronTE.getPrecipitationProgressTicks();
            if (progress > threshold) {
                vb.finishDrawing();
                bindTexture(ModelRegistration.CAULDRON_PRECIPITATE);
                //renderTexturedSide(new Vec3d(x2, y + cauldronTE.getSolidPrecipitateLevel(), z2), 0, 1, 0, 1, (progress - threshold) / 600.0F);
                vb.begin(GL11.GL_QUADS, DefaultVertexFormats.BLOCK);
                bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
            }

            IBakedModel origModel = Minecraft.getMinecraft().getBlockRendererDispatcher().getBlockModelShapes().getModelManager().getModel(ModelRegistration.CAULDRON_HANDLE);
            VertexBuffer.setTranslation(x - cauldronTE.getPos().getX(), y - cauldronTE.getPos().getY(), z - cauldronTE.getPos().getZ());

            IBakedModel model = ModelTransformer.transform(origModel, new ModelTransformer.IVertexTransformer() {
                @Override
                public float[] transform(BakedQuad quad, VertexFormatElement.EnumType type, VertexFormatElement.EnumUsage usage, float... data) {
                    if (usage == VertexFormatElement.EnumUsage.POSITION) {
                        Vector4f vec = new Vector4f(data[0] - 0.5F, data[1] - 0.8F, data[2] - 0.5F, 0);
                        Matrix4f mat = new Matrix4f();
                        mat.setIdentity();
                        mat.rotZ(cauldronTE.getHandleRotation());
                        mat.transform(vec);
                        mat.rotY((float) Math.toRadians(cauldronTE.getRotation()));
                        mat.transform(vec);
                        data[0] = vec.x + 0.5F;
                        data[1] = vec.y + 0.8F;
                        data[2] = vec.z + 0.5F;
                    }
                    return data;
                }
            }, null, 0);

            BlockModelRenderer modelRenderer = Minecraft.getMinecraft().getBlockRendererDispatcher().getBlockModelRenderer();
            modelRenderer.renderModel(cauldronTE.getWorld(), model, state, cauldronTE.getPos(), VertexBuffer, false);
        }
    }

    private void renderTexturedSide(Vec3d pos, float alpha, String path) {
        TextureAtlasSprite texture = Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite(path);
        renderTexturedSide(pos, texture.getMinU(), texture.getMaxU(), texture.getMinV(), texture.getMaxV(), alpha);
    }

    private void renderTexturedSide(Vec3d pos, double minU, double maxU, double minV, double maxV, float alpha) {
        Tessellator tessellator = Tessellator.getInstance();
        VertexBuffer vb = tessellator.getBuffer();
        double f = 0.625;
        GlStateManager.color(1, 1, 1, alpha);
        vb.begin(7, DefaultVertexFormats.POSITION_TEX);
        vb.pos(pos.xCoord, pos.yCoord, pos.zCoord + f).tex(maxU, minV).endVertex();
        vb.pos(pos.xCoord + f, pos.yCoord, pos.zCoord + f).tex(minU, minV).endVertex();
        vb.pos(pos.xCoord + f, pos.yCoord, pos.zCoord).tex(minU, maxV).endVertex();
        vb.pos(pos.xCoord, pos.yCoord, pos.zCoord).tex(maxU, maxV).endVertex();
        tessellator.draw();
    }
}
