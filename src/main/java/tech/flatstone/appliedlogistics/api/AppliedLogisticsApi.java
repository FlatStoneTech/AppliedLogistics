package tech.flatstone.appliedlogistics.api;

import tech.flatstone.appliedlogistics.api.exceptions.CoreInaccessibleException;

import java.lang.reflect.Field;

public enum AppliedLogisticsApi {
    ;

    private static final String CORE_API_FQN = "tech.flatstone.appliedlogistics.common.core.Api";
    private static final String CORE_API_FIELD = "INSTANCE";
    private static final IAppliedLogisticsApi HELD_API;

    static {
        try {
            final Class<?> apiClass = Class.forName(CORE_API_FQN);
            final Field apiField = apiClass.getField(CORE_API_FIELD);

            HELD_API = (IAppliedLogisticsApi) apiField.get(apiClass);
        } catch (ClassNotFoundException ex) {
            throw new CoreInaccessibleException("Applied Logistics API tried to access the " + CORE_API_FQN + " class, without it being declared");
        } catch (NoSuchFieldException ex) {
            throw new CoreInaccessibleException("Applied Logistics API tried to access the " + CORE_API_FIELD + " field in " + CORE_API_FQN + " without it being declared");
        } catch (IllegalAccessException ex) {
            throw new CoreInaccessibleException("Applied Logistics API tried to access the " + CORE_API_FIELD + " field in " + CORE_API_FQN + " without enough access permissions");
        }
    }

    public static IAppliedLogisticsApi instance() {
        return HELD_API;
    }
}
