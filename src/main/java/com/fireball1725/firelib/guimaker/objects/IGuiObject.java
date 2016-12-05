package com.fireball1725.firelib.guimaker.objects;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;

import java.io.IOException;
import java.util.List;

public interface IGuiObject {
    /**
     * Adds the buttons (and other controls) to the screen in question. Called when the GUI is displayed and when the
     * window resizes, the buttonList is cleared beforehand.
     */
    void initGui();

    /**
     * Draws the screen and all the components in it.
     */
    void drawScreen(GuiContainer guiContainer, int mouseX, int mouseY, float partialTicks, float zLevel);

    /**
     * Draw the foreground layer for the GuiContainer (everything in front of the items)
     */
    void drawGuiContainerForegroundLayer(GuiContainer guiContainer, int mouseX, int mouseY);

    /**
     * Draws the background layer of this container (behind the items).
     */
    void drawGuiContainerBackgroundLayer(GuiContainer guiContainer, float partialTicks, int mouseX, int mouseY);

    /**
     * Draws the given slot: any item in it, the slot's background, the hovered highlight, etc.
     */
    void drawSlot(Slot slotIn);

    /**
     * Called when the mouse is clicked. Args : mouseX, mouseY, clickedButton
     */
    void mouseClicked(GuiContainer guiContainer, int mouseX, int mouseY, int mouseButton) throws IOException;

    /**
     * Fired when a key is typed (except F11 which toggles full screen). This is the equivalent of
     * KeyListener.keyTyped(KeyEvent e). Args : character (character on the key), keyCode (lwjgl Keyboard key code)
     */
    void keyTyped(char typedChar, int keyCode) throws IOException;

    /**
     * Called when the screen is unloaded. Used to disable keyboard repeat events
     */
    void onGuiClosed();

    /**
     * Called from the main game loop to update the screen.
     */
    void updateScreen();

    /**
     * Handle Mouse Input
     */
    void handleMouseInput();

    List<Slot> initContainer(InventoryPlayer player, IInventory inventory);
}
