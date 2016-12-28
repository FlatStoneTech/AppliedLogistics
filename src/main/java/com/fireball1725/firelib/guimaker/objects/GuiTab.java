package com.fireball1725.firelib.guimaker.objects;

import com.fireball1725.firelib.guimaker.GuiMaker;
import com.fireball1725.firelib.guimaker.GuiMakerGuiContainer;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class GuiTab {
    private final GuiMakerGuiContainer guiMakerGuiContainer;
    private final String tabName;
    private final ItemStack tabIconStack;
    private final int tabIconNumber;
    private List<GuiObject> guiObjectList = new ArrayList<>();

    public GuiTab(GuiMakerGuiContainer guiMakerGuiContainer, String tabName, int tabIconNumber) {
        this.guiMakerGuiContainer = guiMakerGuiContainer;
        this.tabName = tabName;
        this.tabIconNumber = tabIconNumber;
        this.tabIconStack = null;
    }

    public GuiTab(GuiMakerGuiContainer guiMakerGuiContainer, String tabName, ItemStack tabIconStack) {
        this.guiMakerGuiContainer = guiMakerGuiContainer;
        this.tabName = tabName;
        this.tabIconNumber = 0;
        this.tabIconStack = tabIconStack;
    }

    public void addGuiObject(GuiObject guiObject) {
        guiObject.updateGuiObject(this.guiMakerGuiContainer);
        guiObjectList.add(guiObject);
    }

    public List<GuiObject> getGuiObjectList() {
        return guiObjectList;
    }

    public String getTabName() {
        return tabName;
    }

    public ItemStack getTabIconStack() {
        return tabIconStack;
    }

    public int getTabIconNumber() {
        return tabIconNumber;
    }
}
