package tech.flatstone.appliedlogistics.common.util;

import net.minecraft.util.IStringSerializable;
import tech.flatstone.appliedlogistics.api.features.EnumOreType;

import java.util.Arrays;

public enum EnumOres implements IStringSerializable {
    // Vanilla Ores
    IRON("Iron", 0, EnumOreType.NUGGET, EnumOreType.DUST, EnumOreType.VANILLA, EnumOreType.GEAR),
    GOLD("Gold", 1, EnumOreType.DUST, EnumOreType.VANILLA, EnumOreType.GEAR),
    DIAMOND("Diamond", 2, EnumOreType.NUGGET, EnumOreType.VANILLA),

    // Base Ores
    COPPER("Copper", 3, EnumOreType.BLOCK, EnumOreType.NUGGET, EnumOreType.INGOT, EnumOreType.DUST, EnumOreType.ORE, EnumOreType.GEAR),
    TIN("Tin", 4, EnumOreType.BLOCK, EnumOreType.NUGGET, EnumOreType.INGOT, EnumOreType.DUST, EnumOreType.ORE, EnumOreType.GEAR),
    SILVER("Silver", 5, EnumOreType.BLOCK, EnumOreType.NUGGET, EnumOreType.INGOT, EnumOreType.DUST, EnumOreType.ORE, EnumOreType.GEAR),
    LEAD("Lead", 6, EnumOreType.BLOCK, EnumOreType.NUGGET, EnumOreType.INGOT, EnumOreType.DUST, EnumOreType.ORE, EnumOreType.GEAR),
    NICKEL("Nickel", 7, EnumOreType.BLOCK, EnumOreType.NUGGET, EnumOreType.INGOT, EnumOreType.DUST, EnumOreType.ORE, EnumOreType.GEAR),
    RUTILE("Rutile", 8, EnumOreType.BLOCK, EnumOreType.NUGGET, EnumOreType.INGOT, EnumOreType.DUST, EnumOreType.ORE),

    // Alloys
    TITANIUM("Titanium", 9, EnumOreType.BLOCK, EnumOreType.NUGGET, EnumOreType.INGOT, EnumOreType.DUST, EnumOreType.GEAR),
    BRONZE("Bronze", 10, EnumOreType.BLOCK, EnumOreType.NUGGET, EnumOreType.INGOT, EnumOreType.DUST, EnumOreType.GEAR),
    ELECTRUM("Electrum", 11, EnumOreType.BLOCK, EnumOreType.NUGGET, EnumOreType.INGOT, EnumOreType.DUST, EnumOreType.GEAR),
    STEEL("Steel", 12, EnumOreType.BLOCK, EnumOreType.NUGGET, EnumOreType.INGOT, EnumOreType.DUST, EnumOreType.GEAR),

    // Misc Gears
    WOOD("Wood", 13, EnumOreType.GEAR, EnumOreType.VANILLA),
    COBBLESTONE("Stone", 14, EnumOreType.GEAR, EnumOreType.VANILLA),;

    private static final EnumOres[] META_LOOKUP = new EnumOres[values().length];

    static {
        for (EnumOres ore : values()) {
            META_LOOKUP[ore.getMeta()] = ore;
        }
    }

    private final String name;
    private final int meta;
    private final EnumOreType[] enumOresTypelist;

    EnumOres(String name, int meta, EnumOreType... oreTypes) {
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
