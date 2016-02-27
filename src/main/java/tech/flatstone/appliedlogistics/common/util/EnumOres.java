package tech.flatstone.appliedlogistics.common.util;

import net.minecraft.util.IStringSerializable;

public enum EnumOres implements IStringSerializable {
    IRON(0, "Iron", "iron", true),
    GOLD(1, "Gold", "gold", true),
    COPPER(2, "Copper", "copper", false),
    TIN(3, "Tin", "tin", false),
    SILVER(4, "Silver", "silver", false),
    LEAD(5, "Lead", "lead", false),
    ALUMINIUM(6, "Bauxite", "bauxite", false),;

    private static final EnumOres[] META_LOOKUP = new EnumOres[values().length];

    static {
        for (EnumOres ore : values()) {
            META_LOOKUP[ore.getMeta()] = ore;
        }
    }

    private final int meta;
    private final String name;
    private final String unlocalisedName;
    private final boolean vanillaGen;

    private EnumOres(int meta, String name, String unlocalisedName, boolean vanillaGen) {
        this.meta = meta;
        this.name = name;
        this.unlocalisedName = unlocalisedName;
        this.vanillaGen = vanillaGen;
    }

    public static EnumOres byMeta(int meta) {
        if (meta < 0 || meta >= META_LOOKUP.length) {
            meta = 0;
        }

        return META_LOOKUP[meta];
    }

    public int getMeta() {
        return this.meta;
    }

    public String getUnlocalisedName() {
        return this.unlocalisedName;
    }

    public String getName() {
        return this.name;
    }

    public String toString() {
        return getName();
    }

    public boolean isVanillaGen() {
        return this.vanillaGen;
    }
}
