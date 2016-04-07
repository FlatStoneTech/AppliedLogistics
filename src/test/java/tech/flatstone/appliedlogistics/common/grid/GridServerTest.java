package tech.flatstone.appliedlogistics.common.grid;

import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

public class GridServerTest {
    GridServer gridServer;

    @Before
    public void setUp() throws Exception {
        gridServer = new GridServer();
    }


    @Test
    public void testCargo() throws Exception {
        String cargo = "hello";

        UUID start = UUID.randomUUID();
        UUID end = UUID.randomUUID();
        gridServer.addVertex(start);
        gridServer.addVertex(end);
        gridServer.addEdge(start, end);
        gridServer.markEdgeExit(start, end);
        gridServer.addCargo(new TransportContainer(start, "name", cargo));
        gridServer.gridTick();
        Object obj = gridServer.getCargo(end);

        assertNotNull(obj);
        assertSame(obj, cargo);

    }


}