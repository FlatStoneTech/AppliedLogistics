package tech.flatstone.appliedlogistics.core;

import tech.flatstone.appliedlogisticsapi.IAppliedLogisticsApi;
import tech.flatstone.appliedlogisticsapi.features.IRegistryContainer;

public final class Api implements IAppliedLogisticsApi{
    public static final Api INSTANCE = new Api();

    //private final IRegistryContainer registryContainer;

    private Api() {
        //this.registryContainer = new RegistryContainer();
    }

    @Override
    public IRegistryContainer registries() {
        //return this.registryContainer;
        return null;
    }
}
