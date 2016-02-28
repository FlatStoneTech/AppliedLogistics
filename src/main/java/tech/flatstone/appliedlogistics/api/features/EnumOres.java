package tech.flatstone.appliedlogistics.api.features;

import net.minecraft.util.IStringSerializable;

import java.util.Arrays;

public enum EnumOres implements IStringSerializable {
    // Vanilla Ores
    IRON(0, "Iron", EnumOreType.NUGGET, EnumOreType.DUST, EnumOreType.VANILLA),
    GOLD(1, "Gold", EnumOreType.DUST, EnumOreType.VANILLA),

    // Base Ores
    COPPER(2, "Copper", EnumOreType.BLOCK, EnumOreType.NUGGET, EnumOreType.INGOT, EnumOreType.DUST, EnumOreType.ORE),
    TIN(3, "Tin", EnumOreType.BLOCK, EnumOreType.NUGGET, EnumOreType.INGOT, EnumOreType.DUST, EnumOreType.ORE),
    SILVER(4, "Silver", EnumOreType.BLOCK, EnumOreType.NUGGET, EnumOreType.INGOT, EnumOreType.DUST, EnumOreType.ORE),
    LEAD(5, "Lead", EnumOreType.BLOCK, EnumOreType.NUGGET, EnumOreType.INGOT, EnumOreType.DUST, EnumOreType.ORE),
    NICKEL(6, "Nickel", EnumOreType.BLOCK, EnumOreType.NUGGET, EnumOreType.INGOT, EnumOreType.DUST, EnumOreType.ORE),
    ALUMINIUM(7, "Aluminium", EnumOreType.BLOCK, EnumOreType.NUGGET, EnumOreType.INGOT, EnumOreType.DUST, EnumOreType.ORE),
    RUTILE(8, "Rutile", EnumOreType.BLOCK, EnumOreType.NUGGET, EnumOreType.INGOT, EnumOreType.DUST, EnumOreType.ORE),

    // Alloysyu
    TITANIUM(9, "Titanium", EnumOreType.BLOCK, EnumOreType.NUGGET, EnumOreType.INGOT, EnumOreType.DUST),
    BRONZE(10, "Bronze", EnumOreType.BLOCK, EnumOreType.NUGGET, EnumOreType.INGOT, EnumOreType.DUST),
    ELECTRUM(11, "Electrum", EnumOreType.BLOCK, EnumOreType.NUGGET, EnumOreType.INGOT, EnumOreType.DUST),
    STEEL(12, "Steel", EnumOreType.BLOCK, EnumOreType.NUGGET, EnumOreType.INGOT, EnumOreType.DUST),
    ;

    private static final EnumOres[] META_LOOKUP = new EnumOres[values().length];

    static {
        for (EnumOres ore : values()) {
            META_LOOKUP[ore.getMeta()] = ore;
        }
    }

    private final int meta;
    private final String name;
    private final EnumOreType[] enumOresTypelist;

    EnumOres(int meta, String name, EnumOreType... oreTypes) {
        this.name = name;
        this.meta = meta;
        this.enumOresTypelist = oreTypes;
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
