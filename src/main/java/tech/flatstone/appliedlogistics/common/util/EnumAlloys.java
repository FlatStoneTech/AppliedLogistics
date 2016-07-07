package tech.flatstone.appliedlogistics.common.util;

import net.minecraft.util.IStringSerializable;
import tech.flatstone.appliedlogistics.api.features.EnumOreType;
import tech.flatstone.appliedlogistics.common.fluids.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum EnumAlloys implements IStringSerializable {
    TITANIUM("Titanium", 0, FluidTitanium.class, EnumOreType.STORAGE_BLOCK, EnumOreType.NUGGET, EnumOreType.INGOT, EnumOreType.DUST),
    BRONZE("Bronze", 1, FluidBronze.class, EnumOreType.STORAGE_BLOCK, EnumOreType.NUGGET, EnumOreType.INGOT, EnumOreType.DUST),
    ELECTRUM("Electrum", 2, FluidElectrum.class, EnumOreType.STORAGE_BLOCK, EnumOreType.NUGGET, EnumOreType.INGOT, EnumOreType.DUST),
    STEEL("Steel", 3, FluidSteel.class, EnumOreType.STORAGE_BLOCK, EnumOreType.NUGGET, EnumOreType.INGOT, EnumOreType.DUST),
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
    private final Class<? extends FluidBase> fluidClass;

    EnumAlloys(String name, int meta, EnumOreType... oreTypes) {
        this.name = name;
        this.meta = meta;
        this.enumOreTypeList = oreTypes;
        this.fluidClass = null;
    }

    EnumAlloys(String name, int meta, Class<? extends FluidBase> fluidClass, EnumOreType... oreTypes) {
        this.name = name;
        this.meta = meta;
        this.enumOreTypeList = oreTypes;
        this.fluidClass = fluidClass;
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

    public Class<? extends FluidBase> getFluidClass() {
        return fluidClass;
    }
}
