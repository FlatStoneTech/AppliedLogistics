package tech.flatstone.appliedlogistics.common.util;

import net.minecraft.util.StatCollector;
import tech.flatstone.appliedlogistics.ModInfo;

public class LanguageHelper {
    public static String getTranslated(String unlocalizedString) {
        return StatCollector.translateToLocal(unlocalizedString);
    }

    public static String getTranslatedMessage(String unlocalizedString) {
        return StatCollector.translateToLocal(String.format("messages.%s.%s", ModInfo.MOD_ID, unlocalizedString));
    }
}
