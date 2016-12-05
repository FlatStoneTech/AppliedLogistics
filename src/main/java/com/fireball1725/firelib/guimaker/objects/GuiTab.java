package com.fireball1725.firelib.guimaker.objects;

import com.fireball1725.firelib.guimaker.GuiMaker;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class GuiTab {
    private final GuiMaker guiMakerObj;
    private List<GuiObject> guiObjectList = new ArrayList<>();

    private final String tabName;
    private final ItemStack tabIconStack;
    private final int tabIconNumber;

    public GuiTab(GuiMaker guiMakerObj, String tabName, int tabIconNumber) {
        this.guiMakerObj = guiMakerObj;
        this.tabName = tabName;
        this.tabIconNumber = tabIconNumber;
        this.tabIconStack = null;
    }

    public GuiTab(GuiMaker guiMakerObj, String tabName, ItemStack tabIconStack) {
        this.guiMakerObj = guiMakerObj;
        this.tabName = tabName;
        this.tabIconNumber = 0;
        this.tabIconStack = tabIconStack;
    }

    public void addGuiObject(GuiObject guiObject) {
        guiObject.updateGuiObject(this.guiMakerObj);
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
