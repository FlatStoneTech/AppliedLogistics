package com.fireball1725.firelib.render;

import com.fireball1725.firelib.ModInfo;
import com.fireball1725.firelib.render.obj.AdvancedModelLoader;
import com.fireball1725.firelib.render.obj.IModelCustom;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.player.EnumPlayerModelParts;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;
import tech.flatstone.appliedlogistics.common.util.LogHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Sword implements LayerRenderer<AbstractClientPlayer> {
    private final RenderPlayer renderPlayer;

    private static final ResourceLocation swordObj = new ResourceLocation(ModInfo.MOD_ID, "textures/sword/Benihime.obj");
    private static final ResourceLocation swordTex = new ResourceLocation(ModInfo.MOD_ID, "textures/sword/Benihime.png");
    private final IModelCustom swordModel;
    private final List<UUID> uuidList = new ArrayList<>();;


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

        if (!uuidList.contains(playerUUID))
            return;

        Minecraft.getMinecraft().getTextureManager().bindTexture(swordTex);

        GlStateManager.rotate(90.0f, 1.0f, 0.0f, 0.0f);
        GlStateManager.translate(-0.25f, 0.14f, -0.05f);
        GlStateManager.rotate(-38.0f, 0.0f, 1.0f, 0.0f);
        swordModel.renderAllExcept("Benihime_Tassle");

        GL11.glDisable(GL11.GL_LIGHTING);
        GlStateManager.rotate(-51.0f, 0.0f, 1.0f, 0.0f);
        GlStateManager.translate(0.26f, -0.0f, -0.065f);
        swordModel.renderPart("Benihime_Tassle");
        GL11.glEnable(GL11.GL_LIGHTING);
    }

    @Override
    public boolean shouldCombineTextures() {
        return false;
    }
}
