package tech.flatstone.appliedlogistics.common.items.blueprints;

import tech.flatstone.appliedlogistics.api.features.IMachineBlueprint;
import tech.flatstone.appliedlogistics.api.features.MachineTier;

public class testblueprint implements IMachineBlueprint {

    @Override
    public MachineTier tier() {
        return MachineTier.Wood;
    }
}
