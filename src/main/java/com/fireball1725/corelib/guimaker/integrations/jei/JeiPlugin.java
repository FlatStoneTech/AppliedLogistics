package com.fireball1725.corelib.guimaker.integrations.jei;

import com.fireball1725.corelib.guimaker.GuiMakerGuiContainer;
import mezz.jei.api.IJeiRuntime;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.JEIPlugin;
import mezz.jei.api.gui.IAdvancedGuiHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.awt.Rectangle;
import java.util.List;

@JEIPlugin
public class JeiPlugin implements IModPlugin {

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
            public List<Rectangle> getGuiExtraAreas(@Nonnull GuiMakerGuiContainer guiContainer) {
                return guiContainer.getExtaGuiAreas();
            }
        });
    }

    @Override
    public void onRuntimeAvailable(@Nonnull IJeiRuntime jeiRuntime) {

    }
}
