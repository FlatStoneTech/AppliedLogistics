package com.fireball1725.firelib.render;

import com.fireball1725.firelib.ModInfo;
import com.fireball1725.firelib.render.obj.AdvancedModelLoader;
import com.fireball1725.firelib.render.obj.IModelCustom;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Sword implements LayerRenderer<AbstractClientPlayer> {
    private final RenderPlayer renderPlayer;
    private static final ResourceLocation swordObj = new ResourceLocation(ModInfo.MOD_ID, "textures/sword/Benihime.obj");
    private static final ResourceLocation swordTex = new ResourceLocation(ModInfo.MOD_ID, "textures/sword/Benihime.png");
    private final IModelCustom swordModel;
    private final List<UUID> uuidList = new ArrayList<>();

    @SideOnly(Side.CLIENT)
    public Sword(RenderPlayer renderPlayer) {
        this.renderPlayer = renderPlayer;
        swordModel = AdvancedModelLoader.loadModel(swordObj);

        uuidList.add(UUID.fromString("e43e9766-f903-48e1-818f-d41bb48d80d5")); // FireBall1725
        uuidList.add(UUID.fromString("eb8ef7b9-3199-4a50-99e8-76a48baa6fdf")); // glasspelican
        uuidList.add(UUID.fromString("ed2d2e2c-654c-4e85-aa99-300f13561515"));

    }

    @SideOnly(Side.CLIENT)
    @Override
    public void doRenderLayer(AbstractClientPlayer entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        UUID playerUUID = entitylivingbaseIn.getGameProfile().getId();

        if (!uuidList.contains(playerUUID) || entitylivingbaseIn.isInvisible())
            return;

        if (entitylivingbaseIn.isSneaking()) {
            GlStateManager.translate(0.0f, 0.2f, 0.0f);
            GlStateManager.rotate(28.6479f, 1.0f, 0.0f, 0.0f);
        }

        Minecraft.getMinecraft().getTextureManager().bindTexture(swordTex);

        GlStateManager.rotate(90.0f, 1.0f, 0.0f, 0.0f);
        GlStateManager.translate(-0.25f, 0.14f, -0.05f);
        GlStateManager.rotate(-38.0f, 0.0f, 1.0f, 0.0f);
        swordModel.renderAllExcept("Benihime_Tassle");

        if (entitylivingbaseIn.isSneaking()) {
            GlStateManager.translate(0.25f, -0.14f, 0.05f);
            GlStateManager.rotate(38.0f, 0.0f, 1.0f, 0.0f);
            GlStateManager.rotate(-28.6479f, 1.0f, 0.0f, 0.0f);
            GlStateManager.translate(-0.25f, 0.14f, -0.05f);
            GlStateManager.rotate(-38.0f, 0.0f, 1.0f, 0.0f);
            GlStateManager.translate(0.026f, -0.021f, -0.101f);
        }

        GL11.glDisable(GL11.GL_LIGHTING);
        double d0 = entitylivingbaseIn.prevChasingPosX + (entitylivingbaseIn.chasingPosX - entitylivingbaseIn.prevChasingPosX) * partialTicks - (entitylivingbaseIn.prevPosX + (entitylivingbaseIn.posX - entitylivingbaseIn.prevPosX) * partialTicks);
        double d1 = entitylivingbaseIn.prevChasingPosY + (entitylivingbaseIn.chasingPosY - entitylivingbaseIn.prevChasingPosY) * partialTicks - (entitylivingbaseIn.prevPosY + (entitylivingbaseIn.posY - entitylivingbaseIn.prevPosY) * partialTicks);
        double d2 = entitylivingbaseIn.prevChasingPosZ + (entitylivingbaseIn.chasingPosZ - entitylivingbaseIn.prevChasingPosZ) * partialTicks - (entitylivingbaseIn.prevPosZ + (entitylivingbaseIn.posZ - entitylivingbaseIn.prevPosZ) * partialTicks);
        float f = entitylivingbaseIn.prevRenderYawOffset + (entitylivingbaseIn.renderYawOffset - entitylivingbaseIn.prevRenderYawOffset) * partialTicks;
        double d3 = MathHelper.sin(f * 0.017453292f);
        double d4 = -MathHelper.cos(f * 0.017453292f);
        float f1 = MathHelper.clamp_float((float)d1 * 50.0f, -6.0f, 180.0f);
        float f2 = Math.max(0, (float)(d0 * d3 + d2 * d4) * 100.0f);
        float f3 = (float)(d0 * d4 - d2 * d3) * 100.0f;
        float f4 = entitylivingbaseIn.prevCameraYaw + (entitylivingbaseIn.cameraYaw - entitylivingbaseIn.prevCameraYaw) * partialTicks;
        f1 += MathHelper.sin((entitylivingbaseIn.prevDistanceWalkedModified + (entitylivingbaseIn.distanceWalkedModified - entitylivingbaseIn.prevDistanceWalkedModified) * partialTicks) * 6.0f) * 32.0f * f4;
        GlStateManager.rotate(-51f, 0f, 1f, 0f);
        GlStateManager.translate(0.2017f, 0.0242f, 0.235f);
        GlStateManager.rotate(Math.max(-f2 / 2.0f - f1, -180), 0.0f, 0.0f, 1.0f);
        GlStateManager.rotate(f3 / 2.0f, 0.0f, 1.0f, 0.0f);
        GlStateManager.rotate(-f3 / 2.0f, 1.0f, 0.0f, 0.0f);
        GlStateManager.translate(0.0585f, -0.026f, -0.3f);
        swordModel.renderOnly("Benihime_Tassle");
        GL11.glEnable(GL11.GL_LIGHTING);
    }

    @Override
    public boolean shouldCombineTextures() {
        return false;
    }
}
