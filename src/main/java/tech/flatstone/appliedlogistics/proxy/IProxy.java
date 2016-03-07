package tech.flatstone.appliedlogistics.proxy;

public interface IProxy {
    /**
     * Register Blocks
     */
    void registerBlocks();

    /**
     * Register Items
     */
    void registerItems();

    /**
     * Register Ore Dictionary
     */
    void registerOreDict();

    /**
     * Register Furnace Recipes
     */
    void registerFurnaceRecipes();

    /**
     * Register Hammer Recipes
     */
    void registerHammerRecipes();

    /**
     * Register Recipes
     */
    void registerRecipes();

    /**
     * Register Events
     */
    void registerEvents();
}
