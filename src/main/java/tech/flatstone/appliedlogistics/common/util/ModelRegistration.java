package tech.flatstone.appliedlogistics.common.util;


import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.util.ResourceLocation;
import tech.flatstone.appliedlogistics.ModInfo;

public class ModelRegistration {
    public static final ModelResourceLocation CAULDRON_HANDLE = new ModelResourceLocation(new ResourceLocation(ModInfo.MOD_ID, "misc/cauldron_handle"), null);
    public static final ResourceLocation CAULDRON_PRECIPITATE = new ResourceLocation(ModInfo.MOD_ID, "textures/blocks/precipitate_kaolinite.png");
    public static final ResourceLocation CAULDRON_DISOLVE_DUST = new ResourceLocation(ModInfo.MOD_ID, "textures/blocks/disolve_dust.png");
}
