package tech.flatstone.appliedlogistics.common.util;

import net.minecraft.util.IStringSerializable;

public enum EnumMachineParts implements IStringSerializable {
    MACHINE_FRAME("machine_frame"),
    MACHINE_INTERNAL("machine_internal"),
    ;

    private static final EnumMachineParts[] META_LOOKUP = new EnumMachineParts[values().length];

    static {
        for (EnumMachineParts parts : values()) {
            META_LOOKUP[parts.getMeta()] = parts;
        }
    }

    private final String name;

    EnumMachineParts(String name) {
        this.name = name;
    }

    public static EnumMachineParts byMeta(int meta) {
        if (meta < 0 || meta >= META_LOOKUP.length) {
            meta = 0;
        }

        return META_LOOKUP[meta];
    }

    public int getMeta() {
        return ordinal();
    }

    public String getName() {
        return this.name;
    }
}
