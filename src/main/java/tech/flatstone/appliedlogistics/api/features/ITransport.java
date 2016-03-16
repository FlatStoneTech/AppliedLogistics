package tech.flatstone.appliedlogistics.api.features;

import java.util.UUID;

public interface ITransport {
    /**
     * Creates routing node
     *
     * @return
     */
    UUID createTransportNode();

    /**
     * Creates a node that accepts input into the routing network
     * no limit on how may nodes this node can connect to
     *
     * @return
     */
    UUID createEntryNode();

    /**
     * Creates a node that receives routed objects from the network
     * can only connect to one other node
     *
     * @return
     */
    UUID createExitNode();

    /**
     * Connects two nodes allowing objects to flow in one direction
     * can only connect to one other node
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
     * @return
     */
    boolean applyWhitelistToNode(UUID exitNode);

    /**
     * List of objects that the exit node will reject
     * overwrites an existing whitelist or blacklist
     * empty blacklist or null will cause node to accept all objects
     *
     * @param exitNode
     * @return
     */
    boolean applyBlacklistToNode(UUID exitNode);
}
