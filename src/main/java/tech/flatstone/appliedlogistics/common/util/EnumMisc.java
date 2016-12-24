package tech.flatstone.appliedlogistics.common.util;

import net.minecraft.util.IStringSerializable;
import tech.flatstone.appliedlogistics.api.features.EnumOreType;
import tech.flatstone.appliedlogistics.common.fluids.FluidBase;
import tech.flatstone.appliedlogistics.common.fluids.FluidPrecursor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum EnumMisc implements IStringSerializable {
    SILICA("Silica", 0, EnumOreType.GRAVITY, EnumOreType.BLOCK, EnumOreType.DUST),
    PRECURSOR("Precursor", 1, FluidPrecursor.class),;

    public static final EnumMisc[] META_LOOKUP = new EnumMisc[values().length];

    static {
        for (EnumMisc misc : values()) {
            META_LOOKUP[misc.getMeta()] = misc;
        }
    }

    private final String name;
    private final int meta;
    private final EnumOreType[] enumOreTypeList;
    private final Class<? extends FluidBase> fluidClass;

    EnumMisc(String name, int meta, EnumOreType... oreTypes) {
        this.name = name;
        this.meta = meta;
        this.enumOreTypeList = oreTypes;
        this.fluidClass = null;
    }

    EnumMisc(String name, int meta, Class<? extends FluidBase> fluidClass, EnumOreType... oreTypes) {
        this.name = name;
        this.meta = meta;
        this.enumOreTypeList = oreTypes;
        this.fluidClass = fluidClass;
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

    public Class<? extends FluidBase> getFluidClass() {
        return fluidClass;
    }
}
