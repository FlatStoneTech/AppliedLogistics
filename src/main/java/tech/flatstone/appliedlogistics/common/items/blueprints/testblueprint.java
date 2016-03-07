package tech.flatstone.appliedlogistics.common.items.blueprints;

import tech.flatstone.appliedlogistics.api.features.IMachineBlueprint;
import tech.flatstone.appliedlogistics.api.features.TechLevel;

public class testblueprint implements IMachineBlueprint {

    @Override
    public TechLevel tier() {
        return TechLevel.STONE_AGE;
    }
}
