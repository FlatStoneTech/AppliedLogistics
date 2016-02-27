package tech.flatstone.appliedlogistics.common.integrations;

public interface IIntegration {
    void preInit();
    void init();
    void postInit();
}
