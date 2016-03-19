package tech.flatstone.appliedlogistics.common.util;

import net.minecraft.util.StatCollector;
import tech.flatstone.appliedlogistics.ModInfo;

public enum LanguageHelper {
    MESSAGE("message"),
    LABEL("label"),
    BLOCK("tile"),
    ITEM("item"),
    DESCRIPTION("description"),
    NONE(""),;

    private String name;

    LanguageHelper(String name) {
        this.name = name;
    }

    public String translateMessage(String message) {
        if (this.name == "")
            return StatCollector.translateToLocal(message);

        return StatCollector.translateToLocal(String.format("%s.%s.%s", this.name, ModInfo.MOD_ID, message));
    }
}
