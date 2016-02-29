package tech.flatstone.appliedlogistics.common.util;

import net.minecraft.util.IStringSerializable;
import tech.flatstone.appliedlogistics.api.features.EnumOreType;

import java.util.Arrays;

public enum EnumOres implements IStringSerializable {
    // Vanilla Ores
    IRON("Iron", EnumOreType.NUGGET, EnumOreType.DUST, EnumOreType.VANILLA),
    GOLD("Gold", EnumOreType.DUST, EnumOreType.VANILLA),

    // Base Ores
    COPPER("Copper", EnumOreType.BLOCK, EnumOreType.NUGGET, EnumOreType.INGOT, EnumOreType.DUST, EnumOreType.ORE),
    TIN("Tin", EnumOreType.BLOCK, EnumOreType.NUGGET, EnumOreType.INGOT, EnumOreType.DUST, EnumOreType.ORE),
    SILVER("Silver", EnumOreType.BLOCK, EnumOreType.NUGGET, EnumOreType.INGOT, EnumOreType.DUST, EnumOreType.ORE),
    LEAD("Lead", EnumOreType.BLOCK, EnumOreType.NUGGET, EnumOreType.INGOT, EnumOreType.DUST, EnumOreType.ORE),
    NICKEL("Nickel", EnumOreType.BLOCK, EnumOreType.NUGGET, EnumOreType.INGOT, EnumOreType.DUST, EnumOreType.ORE),
    RUTILE("Rutile", EnumOreType.BLOCK, EnumOreType.NUGGET, EnumOreType.INGOT, EnumOreType.DUST, EnumOreType.ORE),

    // Alloys
    TITANIUM("Titanium", EnumOreType.BLOCK, EnumOreType.NUGGET, EnumOreType.INGOT, EnumOreType.DUST),
    BRONZE("Bronze", EnumOreType.BLOCK, EnumOreType.NUGGET, EnumOreType.INGOT, EnumOreType.DUST),
    ELECTRUM("Electrum", EnumOreType.BLOCK, EnumOreType.NUGGET, EnumOreType.INGOT, EnumOreType.DUST),
    STEEL("Steel", EnumOreType.BLOCK, EnumOreType.NUGGET, EnumOreType.INGOT, EnumOreType.DUST),;

    private static final EnumOres[] META_LOOKUP = new EnumOres[values().length];

    static {
        for (EnumOres ore : values()) {
            META_LOOKUP[ore.getMeta()] = ore;
        }
    }

    private final String name;
    private final EnumOreType[] enumOresTypelist;

    EnumOres(String name, EnumOreType... oreTypes) {
        this.name = name;
        this.enumOresTypelist = oreTypes;
    }

    public static EnumOres byMeta(int meta) {
        if (meta < 0 || meta >= META_LOOKUP.length) {
            meta = 0;
        }

        return META_LOOKUP[meta];
    }

    public int getMeta() {
        return ordinal();
    }

    public String getUnlocalizedName() {
        return this.name.toLowerCase();
    }

    public String getName() {
        return this.name;
    }

    public String toString() {
        return getName();
    }

    public boolean isTypeSet(EnumOreType enumOreType) {
        return Arrays.asList(enumOresTypelist).contains(enumOreType);
    }
}
