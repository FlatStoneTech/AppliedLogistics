package tech.flatstone.appliedlogistics.common.util;

import net.minecraft.util.IStringSerializable;
import tech.flatstone.appliedlogistics.api.features.EnumOreType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum EnumAlloys implements IStringSerializable {
    TITANIUM("Titanium", 0, EnumOreType.BLOCK, EnumOreType.NUGGET, EnumOreType.INGOT, EnumOreType.DUST, EnumOreType.FLUID),
    BRONZE("Bronze", 1, EnumOreType.BLOCK, EnumOreType.NUGGET, EnumOreType.INGOT, EnumOreType.DUST, EnumOreType.FLUID),
    ELECTRUM("Electrum", 2, EnumOreType.BLOCK, EnumOreType.NUGGET, EnumOreType.INGOT, EnumOreType.DUST, EnumOreType.FLUID),
    STEEL("Steel", 3, EnumOreType.BLOCK, EnumOreType.NUGGET, EnumOreType.INGOT, EnumOreType.DUST, EnumOreType.FLUID),
    ;

    public static final EnumAlloys[] META_LOOKUP = new EnumAlloys[values().length];

    static {
        for (EnumAlloys alloy : values()) {
            META_LOOKUP[alloy.getMeta()] = alloy;
        }
    }

    private final String name;
    private final int meta;
    private final EnumOreType[] enumOreTypeList;

    EnumAlloys(String name, int meta, EnumOreType... oreTypes) {
        this.name = name;
        this.meta = meta;
        this.enumOreTypeList = oreTypes;
    }

    public static EnumAlloys byMeta(int meta) {
        if (meta < 0 || meta >= META_LOOKUP.length) {
            meta = 0;
        }

        return META_LOOKUP[meta];
    }

    public static List<EnumAlloys> byType(EnumOreType type) {
        List<EnumAlloys> result = new ArrayList<>();

        for (EnumAlloys alloy : values()) {
            if (alloy.isTypeSet(type)) {
                result.add(alloy);
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

    public String getAlloyName() {
        return this.name;
    }

    public String toString() {
        return getName();
    }

    public boolean isTypeSet(EnumOreType enumOreType) {
        return Arrays.asList(enumOreTypeList).contains(enumOreType);
    }
}
