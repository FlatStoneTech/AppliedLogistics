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

    @Test
    public void testEdgeLinking() throws Exception {
        UUID start = UUID.randomUUID();
        UUID midNode1 = UUID.randomUUID();
        UUID modNode2 = UUID.randomUUID();
        UUID end = UUID.randomUUID();

        gridServer.addVertex(start);
        gridServer.addVertex(midNode1);
        gridServer.addVertex(modNode2);
        gridServer.addVertex(end);

        gridServer.addEdge(start, midNode1);
        gridServer.addEdge(midNode1, modNode2);
        gridServer.addEdge(modNode2, midNode1);
        gridServer.addEdge(modNode2, end);

        gridServer.gridTick();

    }

    @Test
    public void testRemoveVertex() throws Exception {
        UUID vertex = UUID.randomUUID();

        gridServer.removeVertex(vertex);
        gridServer.gridTick();

        gridServer.addVertex(vertex);
        assertTrue(gridServer.removeVertex(vertex));
        gridServer.gridTick();

        gridServer.addVertex(vertex);
        gridServer.addVertex(vertex);
        gridServer.gridTick();

    }

    @Test
    public void testRemoveEdge() throws Exception {
        UUID vertex1 = UUID.randomUUID();
        UUID vertex2 = UUID.randomUUID();

        gridServer.addVertex(vertex1);
        gridServer.addVertex(vertex2);
        assertTrue(gridServer.addEdge(vertex1, vertex2));

        gridServer.gridTick();

        assertTrue(gridServer.removeEdge(vertex1, vertex2));
        assertTrue(gridServer.removeEdge(vertex2, vertex1));

        gridServer.gridTick();

        assertFalse(gridServer.removeEdge(null,vertex1));
    }

}