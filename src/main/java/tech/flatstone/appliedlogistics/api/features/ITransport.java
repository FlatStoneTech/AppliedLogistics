package tech.flatstone.appliedlogistics.api.features;

import java.util.ArrayList;
import java.util.UUID;

public interface ITransport {
    /**
     * Creates routing node
     * no limit on how may nodes this node can connect to
     *
     * @return
     */
    UUID createTransportNode();

    /**
     * Creates a node that accepts input into the routing network
     * can only connect to one other node
     *
     * @param parentNode
     * @return
     */
    UUID createEntryNode(UUID parentNode);

    /**
     * Creates a node that receives routed objects from the network
     * can only connect to one other node
     *
     * @param parentNode
     * @return
     */
    UUID createExitNode(UUID parentNode);

    /**
     * Connects two nodes allowing objects to flow in one direction
     *
     * @param startNode
     * @param destNode
     * @return
     */
    boolean createDirectionalNodeConnection(UUID startNode, UUID destNode);

    /**
     * Connects two nodes allowing objects to flow in both directions
     *
     * @param node1
     * @param node2
     * @return
     */
    boolean createNodeConnection(UUID node1, UUID node2);

    /**
     * List of objects that the exit node will accept
     * overwrites an existing whitelist or blacklist
     * empty whitelist or null will cause node to accept no objects
     *
     * @param exitNode
     * @param unlocalizedNameList
     * @return
     */
    boolean applyWhitelistToNode(UUID exitNode, ArrayList<String> unlocalizedNameList);

    /**
     * List of objects that the exit node will reject
     * overwrites an existing whitelist or blacklist
     * empty blacklist or null will cause node to accept all objects
     *
     * @param exitNode
     * @param unlocalizedNameList
     * @return
     */
    boolean applyBlacklistToNode(UUID exitNode, ArrayList<String> unlocalizedNameList);
}
