package tech.flatstone.appliedlogistics.common.grid;

import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import static org.junit.Assert.*;

public class GridServerTest {
    private GridServer gridServer;

    @Before
    public void setUp() throws Exception {
        gridServer = new GridServer();
    }


    @Test
    public void testCargo() throws Exception {
        String cargo = "hello";
        Object obj;

        UUID start = UUID.randomUUID();
        UUID end = UUID.randomUUID();
        UUID pivet = UUID.randomUUID();

        gridServer.addVertex(start);
        gridServer.addVertex(end);
        gridServer.addVertex(pivet);

        gridServer.addEdge(start, pivet);
        gridServer.addEdge(pivet, end);
        gridServer.markEdgeExit(pivet, end);

        gridServer.addCargo(new TransportContainer(start, "name", cargo));

        assertNull(gridServer.getCargo(end));
        gridServer.gridTick();

        assertNull(gridServer.getCargo(end));
        gridServer.gridTick();

        obj = gridServer.getCargo(end);
        assertNotNull(obj);
        TransportContainer container = (TransportContainer) obj;
        assertSame(container.getCargo(), cargo);

    }


}