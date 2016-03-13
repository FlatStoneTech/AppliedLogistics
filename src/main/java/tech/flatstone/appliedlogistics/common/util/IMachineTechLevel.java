package tech.flatstone.appliedlogistics.common.util;

import java.util.HashMap;
import java.util.List;

public interface IMachineTechLevel {
    int getMaxWeight();
    HashMap<EnumMachineParts, List<IMachineParts>> getSlotGroups();
}
