package tech.flatstone.appliedlogistics.item.blueprints;

import tech.flatstone.appliedlogisticsapi.features.IMachineBlueprint;
import tech.flatstone.appliedlogisticsapi.features.MachineTier;

public class testblueprint implements IMachineBlueprint {

    @Override
    public MachineTier tier() {
        return MachineTier.Wood;
    }
}
