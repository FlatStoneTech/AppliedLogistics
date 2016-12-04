package com.fireball1725.corelib.render;

import com.fireball1725.corelib.ModInfo;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.client.model.Attributes;
import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.client.model.obj.OBJModel;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.UUID;
import java.util.function.Function;

public class Sword implements LayerRenderer<AbstractClientPlayer> {
    private final RenderPlayer renderPlayer;
    private OBJModel modelSword;
    private OBJModel.OBJBakedModel bakedModelObj;

    @SideOnly(Side.CLIENT)
    public Sword(RenderPlayer renderPlayer) {
        this.renderPlayer = renderPlayer;

        try {
            modelSword = (OBJModel) OBJLoader.INSTANCE.loadModel(new ResourceLocation(ModInfo.MOD_ID, "textures/sword/Benihime.obj"));
            Function<ResourceLocation, TextureAtlasSprite> textureGetter = location -> Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite(ModInfo.MOD_ID + ":textures/sword/Benihime.png");
            bakedModelObj = (OBJModel.OBJBakedModel) modelSword.bake(modelSword.getDefaultState(), Attributes.DEFAULT_BAKED_FORMAT, textureGetter::apply);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    @Override
    public void doRenderLayer(AbstractClientPlayer entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        UUID playerUUID = entitylivingbaseIn.getGameProfile().getId();


        //entitylivingbaseIn.isWearing(EnumPlayerModelParts.CAPE)


    }

    @Override
    public boolean shouldCombineTextures() {
        return false;
    }

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void TextureStitchEvent(TextureStitchEvent event) {
        event.getMap().registerSprite(new ResourceLocation(ModInfo.MOD_ID, "textures/sword/Benihime.png"));
    }
}
