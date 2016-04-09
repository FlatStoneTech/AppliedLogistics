package tech.flatstone.appliedlogistics.common.grid;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.UUID;

import static org.junit.Assert.*;

public class TransportGridTest {
    TransportGrid transportGrid;

    @Before
    public void setUp() throws Exception {
        transportGrid = new TransportGrid();
    }

    @After
    public void tearDown() throws Exception {
        transportGrid.Shutdown();
    }

    @Test
    public void createTransportNode() throws Exception {
        transportGrid.createTransportNode();
    }

    @Test
    public void createEntryNode() throws Exception {
        transportGrid.createEntryNode(transportGrid.createTransportNode());
    }

    @Test
    public void createExitNode() throws Exception {
        transportGrid.createExitNode(transportGrid.createTransportNode());
    }

    @Test
    public void createDirectionalNodeConnection() throws Exception {
        UUID node1 = transportGrid.createTransportNode();
        UUID node2 = transportGrid.createTransportNode();
        transportGrid.createDirectionalNodeConnection(node1,node2);
    }

    @Test
    public void createNodeConnection() throws Exception {
        UUID node1 = transportGrid.createTransportNode();
        UUID node2 = transportGrid.createTransportNode();
        transportGrid.createNodeConnection(node1,node2);
    }

    @Test
    public void applyWhitelistToNode() throws Exception {
        UUID node1 = transportGrid.createTransportNode();
        UUID node2 = transportGrid.createExitNode(node1);
        ArrayList<String> list = new ArrayList<String>();
        list.add("whitelist");

        transportGrid.applyWhitelistToNode(node2,list);
    }

    @Test
    public void applyBlacklistToNode() throws Exception {
        UUID node1 = transportGrid.createTransportNode();
        UUID node2 = transportGrid.createExitNode(node1);
        ArrayList<String> list = new ArrayList<String>();
        list.add("blacklist");

        transportGrid.applyBlacklistToNode(node2,list);
    }

    @Test
    public void insertObjectToGrid() throws Exception {

    }

    @Test
    public void getObjectFromGrid() throws Exception {

    }

}