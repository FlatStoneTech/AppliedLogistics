package com.fireball1725.firelib.guimaker.integrations.jei;

import com.fireball1725.firelib.guimaker.GuiMakerGuiContainer;
import mezz.jei.api.*;
import mezz.jei.api.gui.IAdvancedGuiHandler;
import mezz.jei.api.ingredients.IModIngredientRegistration;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.awt.*;
import java.util.List;

@JEIPlugin
public class JeiPlugin implements IModPlugin {

    @Override
    public void registerItemSubtypes(ISubtypeRegistry subtypeRegistry) {

    }

    @Override
    public void registerIngredients(IModIngredientRegistration registry) {

    }

    @Override
    public void register(@Nonnull IModRegistry registry) {
        registry.addAdvancedGuiHandlers(new IAdvancedGuiHandler<GuiMakerGuiContainer>() {
            @Nonnull
            @Override
            public Class<GuiMakerGuiContainer> getGuiContainerClass() {
                return GuiMakerGuiContainer.class;
            }

            @Nullable
            @Override
            @SideOnly(Side.CLIENT)
            public List<Rectangle> getGuiExtraAreas(@Nonnull GuiMakerGuiContainer guiContainer) {
                return guiContainer.getExtaGuiAreas();
            }

            @Nullable
            @Override
            public Object getIngredientUnderMouse(GuiMakerGuiContainer guiContainer, int mouseX, int mouseY) {
                return null;
            }
        });
    }

    @Override
    public void onRuntimeAvailable(@Nonnull IJeiRuntime jeiRuntime) {

    }
}
