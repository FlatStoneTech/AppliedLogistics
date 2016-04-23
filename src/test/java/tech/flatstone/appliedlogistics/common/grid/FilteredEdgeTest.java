package tech.flatstone.appliedlogistics.common.grid;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.UUID;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class FilteredEdgeTest {
    private FilteredEdge<UUID> filteredEdge;
    private ArrayList<String> list;

    @Before
    public void setUp() throws Exception {
        filteredEdge = new FilteredEdge<UUID>();

        list = new ArrayList<String>();
        list.add("This");
        list.add("is");
        list.add("a");
        list.add("whitelist");
    }

    @Test
    public void testWhitelist() throws Exception {
        filteredEdge.setWhitelist(list);
        assertTrue(filteredEdge.canRoute("whitelist"));
        assertFalse(filteredEdge.canRoute("blacklist"));
        assertFalse(filteredEdge.canRoute(""));
    }

    @Test
    public void testBlacklist() throws Exception {
        filteredEdge.setBlacklist(list);
        assertFalse(filteredEdge.canRoute("whitelist"));
        assertTrue(filteredEdge.canRoute("blacklist"));
        assertTrue(filteredEdge.canRoute(""));
    }

    @Test
    public void testDefaultList() throws Exception {
        assertTrue(filteredEdge.canRoute(""));
        assertTrue(filteredEdge.canRoute("anything"));
    }

    @After
    public void tearDown() throws Exception {

    }

}