package com.togacure.graphlib.impl.algo;

import com.togacure.graphlib.enums.PathFinderAlgorithm;
import com.togacure.graphlib.exceptions.VertexNotFoundException;
import com.togacure.graphlib.interfaces.IVertex;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import lombok.NonNull;
import lombok.val;
import org.springframework.stereotype.Component;

/**
 * Implements non-optimal graph path by "wide search" algorithm
 *
 * @author Vitaly Alekseev
 * @since 9/24/2019
 */
@Component
public class WideSearchPathFinder extends AbstractGraphPathFinder {

    @Override
    public PathFinderAlgorithm getAlgorithm() {
        return PathFinderAlgorithm.WIDE_SEARCH;
    }

    @Override
    protected <T extends IVertex> Map<T, T> findAllPaths(@NonNull T from, @NonNull T to, @NonNull Map<T, Set<T>> graph) throws VertexNotFoundException {
        val edges = new HashMap<T, T>();
        val frontier = new ArrayDeque<T>(toPowOf2(frontierQueueCapacity));
        frontier.offer(from);
        while (!frontier.isEmpty()) {
            val current = frontier.pollLast();
            if (current.equals(to)) {
                break;
            }
            val neighbors = graph.get(current);
            if (neighbors == null) {
                throw new VertexNotFoundException(current);
            }
            neighbors.stream().filter(next -> !edges.containsKey(next)).forEach(next -> {
                frontier.offer(current);
                edges.put(next, current);
            });
        }
        return edges;
    }
}
