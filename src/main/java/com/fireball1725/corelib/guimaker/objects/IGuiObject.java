package com.fireball1725.corelib.guimaker.objects;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ClickType;
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
    void drawScreen(int mouseX, int mouseY, float partialTicks);

    /**
     * Draw the foreground layer for the GuiContainer (everything in front of the items)
     */
    void drawGuiContainerForegroundLayer(int mouseX, int mouseY);

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
    void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException;

    /**
     * Called when a mouse button is pressed and the mouse is moved around. Parameters are : mouseX, mouseY,
     * lastButtonClicked & timeSinceMouseClick.
     */
    void mouseClickMove(int mouseX, int mouseY, int clickedMouseButton, long timeSinceLastClick);

    /**
     * Called when a mouse button is released.
     */
    void mouseReleased(int mouseX, int mouseY, int state);

    /**
     * Test if the 2D point is in a rectangle (relative to the GUI). Args : rectX, rectY, rectWidth, rectHeight, pointX,
     * pointY
     */
    boolean isPointInRegion(int rectX, int rectY, int rectWidth, int rectHeight, int pointX, int pointY);

    /**
     * Called when the mouse is clicked over a slot or outside the gui.
     */
    void handleMouseClick(Slot slotIn, int slotId, int mouseButton, ClickType type);

    /**
     * Fired when a key is typed (except F11 which toggles full screen). This is the equivalent of
     * KeyListener.keyTyped(KeyEvent e). Args : character (character on the key), keyCode (lwjgl Keyboard key code)
     */
    void keyTyped(char typedChar, int keyCode) throws IOException;

    /**
     * Checks whether a hotbar key (to swap the hovered item with an item in the hotbar) has been pressed. If so, it
     * swaps the given items.
     * Returns true if a hotbar key was pressed.
     */
    boolean checkHotbarKeys(int keyCode);

    /**
     * Called when the screen is unloaded. Used to disable keyboard repeat events
     */
    void onGuiClosed();

    /**
     * Called from the main game loop to update the screen.
     */
    void updateScreen();

    List<Slot> initContainer(InventoryPlayer player, IInventory inventory);
}
