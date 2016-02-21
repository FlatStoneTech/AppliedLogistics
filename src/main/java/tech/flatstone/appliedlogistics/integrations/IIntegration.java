package tech.flatstone.appliedlogistics.integrations;

public interface IIntegration {
    void preInit();
    void init();
    void postInit();
}
