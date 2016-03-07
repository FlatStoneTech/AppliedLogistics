package tech.flatstone.appliedlogistics.api.features;

import net.minecraft.util.IStringSerializable;

public enum TechLevel implements IStringSerializable {
    STONE_AGE("stone"),
    BRONZE_AGE("bronze"),
    STEEL_AGE("steel"),
    INDUSTRIAL_AGE("industrial"),
    DIGITAL_AGE("digital"),
    ;

    private static final TechLevel[] META_LOOKUP = new TechLevel[values().length];

    static {
        for (TechLevel tier : values()) {
            META_LOOKUP[tier.getMeta()] = tier;
        }
    }

    private final String name;

    TechLevel(String name) {
        this.name = name;
    }

    public int getMeta() {
        return ordinal();
    }

    public static TechLevel byMeta(int meta) {
        if (meta < 0 || meta >= META_LOOKUP.length) {
            meta = 0;
        }

        return META_LOOKUP[meta];
    }

    public String getName() {
        return this.name;
    }
}
