package com.fireball1725.corelib.guimaker.objects;

import com.fireball1725.corelib.guimaker.objects.GuiObject;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class GuiTab {
    private String tabName;
    private List<GuiObject> guiObjects = new ArrayList<>();
    private ItemStack tabIcon;
    private int tabIconNumber = 0;

    public int getTabIconNumber() {
        return tabIconNumber;
    }

    public void setTabIconNumber(int tabIconNumber) {
        this.tabIconNumber = tabIconNumber;
    }

    public GuiTab(String tabName, ItemStack tabIcon) {
        this.tabName = tabName;
        this.tabIcon = tabIcon;
    }

    public GuiTab(String tabName, int tabIconNumber) {
        this.tabName = tabName;
        this.tabIcon = null;
        this.tabIconNumber = tabIconNumber;
    }

    public void addGuiObject(GuiObject guiObject) {
        guiObjects.add(guiObject);
    }

    public ItemStack getTabIcon() {
        return tabIcon;
    }

    public void setTabIcon(ItemStack tabIcon) {
        this.tabIcon = tabIcon;
    }

    public String getTabName() {
        return tabName;
    }

    public void setTabName(String tabName) {
        this.tabName = tabName;
    }

    public List<GuiObject> getGuiObjects() {
        return guiObjects;
    }

    public void setGuiObjects(List<GuiObject> guiObjects) {
        this.guiObjects = guiObjects;
    }
}
