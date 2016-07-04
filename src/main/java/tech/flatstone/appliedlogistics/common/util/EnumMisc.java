package tech.flatstone.appliedlogistics.common.util;

import net.minecraft.util.IStringSerializable;
import tech.flatstone.appliedlogistics.api.features.EnumOreType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum EnumMisc implements IStringSerializable {
    SILICA("Silica", 0, EnumOreType.GRAVITY, EnumOreType.BLOCK, EnumOreType.DUST),
    PRECURSOR("Precursor", 1, EnumOreType.COLD_FLUID),
    ;

    public static final EnumMisc[] META_LOOKUP = new EnumMisc[values().length];

    static {
        for (EnumMisc misc : values()) {
            META_LOOKUP[misc.getMeta()] = misc;
        }
    }

    private final String name;
    private final int meta;
    private final EnumOreType[] enumOreTypeList;

    EnumMisc(String name, int meta, EnumOreType... oreTypes) {
        this.name = name;
        this.meta = meta;
        this.enumOreTypeList = oreTypes;
    }

    public static EnumMisc byMeta(int meta) {
        if (meta < 0 || meta >= META_LOOKUP.length) {
            meta = 0;
        }

        return META_LOOKUP[meta];
    }

    public static List<EnumMisc> byType(EnumOreType type) {
        List<EnumMisc> result = new ArrayList<>();

        for (EnumMisc misc : values()) {
            if (misc.isTypeSet(type)) {
                result.add(misc);
            }
        }

        return result;
    }

    public int getMeta() {
        return this.meta;
    }

    public String getUnlocalizedName() {
        return this.name.toLowerCase();
    }

    public String getName() {
        return this.name.toLowerCase();
    }

    public String getMiscName() {
        return this.name;
    }

    public String toString() {
        return getName();
    }

    public boolean isTypeSet(EnumOreType enumOreType) {
        return Arrays.asList(enumOreTypeList).contains(enumOreType);
    }
}
