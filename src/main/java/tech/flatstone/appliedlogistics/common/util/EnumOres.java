package tech.flatstone.appliedlogistics.common.util;

import net.minecraft.util.IStringSerializable;

public enum EnumOres implements IStringSerializable {
    IRON(0, "iron", true),
    GOLD(1, "gold", true),
    COPPER(2, "copper", false),
    TIN(3, "tin", false),
    SILVER(4, "silver", false),
    LEAD(5, "lead", false),
    ALUMINIUM(6, "bauxite", false),

    ;

    private final int meta;
    private final String name;
    private final boolean vanillaGen;

    private static final EnumOres[] META_LOOKUP = new EnumOres[values().length];

    private EnumOres(int meta, String name, boolean vanillaGen) {
        this.meta = meta;
        this.name = name;
        this.vanillaGen = vanillaGen;
    }

    public int getMeta() {
        return this.meta;
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

    public static EnumOres byMeta(int meta) {
        if (meta < 0 || meta >= META_LOOKUP.length) {
            meta = 0;
        }

        return META_LOOKUP[meta];
    }

    static {
        for (EnumOres ore : values()) {
            META_LOOKUP[ore.getMeta()] = ore;
        }
    }
}
