package tech.flatstone.appliedlogistics.common.plans;

import tech.flatstone.appliedlogistics.common.items.ItemPlanBase;
import tech.flatstone.appliedlogistics.common.util.LogHelper;

public class PlanBase extends ItemPlanBase {

    public PlanBase(String planName) {
        String test = getUnlocalizedName();

        this.setUnlocalizedName(String.format("%s.%s", "plan", planName));
        LogHelper.internal(">>> " + test + " >>> " + this.getUnlocalizedName());
    }
}
