package com.fireball1725.corelib.render;

import com.fireball1725.corelib.ModInfo;
import com.fireball1725.corelib.render.obj.AdvancedModelLoader;
import com.fireball1725.corelib.render.obj.IModelCustom;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.UUID;

public class Sword implements LayerRenderer<AbstractClientPlayer> {
    private final RenderPlayer renderPlayer;

    private static final ResourceLocation swordObj = new ResourceLocation(ModInfo.MOD_ID, "textures/sword/Benihime.obj");
    private static final ResourceLocation swordTex = new ResourceLocation(ModInfo.MOD_ID, "textures/sword/Benihime.png");

    @SideOnly(Side.CLIENT)
    public Sword(RenderPlayer renderPlayer) {
        this.renderPlayer = renderPlayer;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void doRenderLayer(AbstractClientPlayer entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        UUID playerUUID = entitylivingbaseIn.getGameProfile().getId();


        IModelCustom swordModel = AdvancedModelLoader.loadModel(swordObj);
        Minecraft.getMinecraft().getTextureManager().bindTexture(swordTex);
        swordModel.renderAll();
    }

    @Override
    public boolean shouldCombineTextures() {
        return false;
    }
}
