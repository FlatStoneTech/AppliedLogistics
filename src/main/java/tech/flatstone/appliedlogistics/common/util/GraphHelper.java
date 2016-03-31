package tech.flatstone.appliedlogistics.common.util;

import org.jgrapht.Graphs;
import org.jgrapht.traverse.ClosestFirstIterator;

import java.util.LinkedList;

public class GraphHelper {

    public static <V, E> LinkedList findPathBetween(ClosestFirstIterator<V, E> iterator, V startVertex, V endVertex) {
        LinkedList<E> path = new LinkedList<E>();

        V currentVertex = endVertex;

        while (true) {
            E edge = iterator.getSpanningTreeEdge(currentVertex);

            if (edge == null)
                break;

            path.addFirst(edge);
            currentVertex = Graphs.getOppositeVertex(iterator.getGraph(), edge, currentVertex);
        }

        return path;
    }
}
