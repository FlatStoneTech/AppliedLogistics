package com.fireball1725.firelib.guimaker;

import com.fireball1725.firelib.network.PacketHandler;
import com.fireball1725.firelib.network.messages.PacketUpdateGuiContainer;
import com.fireball1725.firelib.guimaker.objects.GuiObject;
import com.fireball1725.firelib.guimaker.objects.GuiTab;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public abstract class GuiMaker {
    private static List<GuiMaker> guiInstances = new ArrayList<>();
    public static ResourceLocation resourceLocation = new ResourceLocation("firelib", "textures/gui/Darkskin.png");

    //region Things set in stone
    /**
     * Gui ID Number (Starts at 0 and increments)
     */
    private final int guiId;

    /**
     * GuiMaker Parent Instance
     */
    private final IImplementsGuiMaker guiMakerInstance;
    //endregion

    //region Things that can be adjusted or set during run-time
    /**
     * Height of the Gui Window
     */
    private int guiHeight;

    /**
     * Width of the Gui Window
     */
    private int guiWidth;

    /**
     * Currently selected tab (Default = 0)
     */
    private int selectedTab = 0;

    /**
     * Current Window Title
     */
    private String guiTitle = "";

    /**
     * Gui Status Icon
     */
    private GuiMakerStatusIcon guiMakerStatusIcon = GuiMakerStatusIcon.EMPTY;

    /**
     * Gui Tabs that make up Gui Window
     */
    private List<GuiTab> guiTabs = new ArrayList<>();
    //endregion

    //region Get Instance
    /**
     * Create new instance of GuiMaker
     *
     * @param instance
     */
    public GuiMaker(IImplementsGuiMaker instance, int windowWidth, int windowHeight) {
        this.guiMakerInstance = instance;
        this.guiId = guiInstances.size();
        this.guiWidth = windowWidth;
        this.guiHeight = windowHeight;
        guiInstances.add(this);
    }
    //endregion

    //region Constructor
    /**
     * Get Gui Maker Instance from Registry
     *
     * @param id
     * @return
     */
    public static GuiMaker getGuiMaker(int id) {
        return guiInstances.get(id);
    }
    //endregion

    //region Methods
    /**
     * Show Gui Instance
     *
     * @param mod
     * @param world
     * @param player
     * @param pos
     */
    public void show(Object mod, World world, EntityPlayer player, BlockPos pos) {
        TileEntity tileEntity = world.getTileEntity(pos);

        guiMakerInstance.initGui(tileEntity, player.inventory);
        if (guiTabs.size() == 0)
            return;

        selectedTab = 0;

        player.openGui(mod, this.guiId, world, pos.getX(), pos.getY(), pos.getZ());
    }

    /**
     * Set the window size
     * @param windowWidth
     * @param windowHeight
     */
    public void setGuiSize(int windowWidth, int windowHeight) {
        this.guiWidth = windowWidth;
        this.guiHeight = windowHeight;
    }

    /**
     * Mark the Gui Dirty, and force update on the server
     */
    public void setServerGuiTab(int tabID, EntityPlayer player) {
        this.selectedTab = tabID;
        World world = player.getEntityWorld();

        if (world.isRemote) {
            // Update sever with new TabID
            PacketHandler.INSTANCE.sendToServer(new PacketUpdateGuiContainer(this.guiId, this.selectedTab));
            if (player.openContainer instanceof GuiMakerContainer)
                ((GuiMakerContainer)player.openContainer).initContainer();
        } else {
            // Init server side
            if (player.openContainer instanceof GuiMakerContainer)
                ((GuiMakerContainer)player.openContainer).initContainer();
        }
    }
    //endregion

    //region Getters and Setters
    /**
     * Get GuiMaker Status Icon
     *
     * @return
     */
    public GuiMakerStatusIcon getGuiMakerStatusIcon() {
        return guiMakerStatusIcon;
    }

    /**
     * Set GuiMaker Status Icon
     *
     * @param guiMakerStatusIcon
     */
    public void setGuiMakerStatusIcon(GuiMakerStatusIcon guiMakerStatusIcon) {
        this.guiMakerStatusIcon = guiMakerStatusIcon;
    }

    /**
     * Get Selected Tab Index
     *
     * @return
     */
    public int getSelectedTab() {
        return selectedTab;
    }

    /**
     * Set Gui Title
     *
     * @return
     */
    public String getGuiTitle() {
        return guiTitle;
    }

    /**
     * Get Gui Title
     *
     * @param guiTitle
     */
    public void setGuiTitle(String guiTitle) {
        this.guiTitle = guiTitle;
    }

    /**
     * Get GuiMaker Parent Instance
     * @return
     */
    public IImplementsGuiMaker getGuiMakerInstance() {
        return guiMakerInstance;
    }

    /**
     * Get the window Height
     * @return
     */
    public int getGuiHeight() {
        return guiHeight;
    }

    /**
     * Get the window Width
     * @return
     */
    public int getGuiWidth() {
        return guiWidth;
    }

    /**
     * Get the Gui ID
     * @return
     */
    public int getGuiId() {
        return guiId;
    }
    //endregion

    //region Gui Tabs
    public List<GuiObject> getGuiObjects() {
        return guiTabs.get(selectedTab).getGuiObjectList();
    }

    public void addGuiTab(GuiTab guiTab) {
        this.guiTabs.add(guiTab);
    }

    public List<GuiTab> getGuiTabs() {
        return guiTabs;
    }

    public void clearGuiTabs() {
        guiTabs.clear();
    }
    //endregion

    //region Callbacks

    abstract public void guiObjectClicked(int controlID);

    abstract public void guiObjectUpdated(int controlID);

    //endregion
}