package tech.flatstone.appliedlogistics.api.reference;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import tech.flatstone.appliedlogistics.api.exceptions.IncompatibleApiVersionException;

import static org.junit.Assert.*;

public class ApiInfoTest {
    @Rule
    public ExpectedException versionMismatch = ExpectedException.none();

    @Test
    public void checkAPI() throws Exception {
        versionMismatch.expect(IncompatibleApiVersionException.class);
        versionMismatch.expectMessage(String.format("%s expects %s API %d:%d version %d:%d provided",
                "Test Mod","Applied Logistics", Integer.MAX_VALUE, Integer.MAX_VALUE,
                ApiInfo.getApiVersion(), ApiInfo.getFeatureLevel()));
        ApiInfo.CheckAPI("Test Mod",Integer.MAX_VALUE,Integer.MAX_VALUE);
    }

}