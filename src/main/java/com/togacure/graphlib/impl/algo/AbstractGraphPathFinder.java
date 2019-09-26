package com.togacure.graphlib.impl.algo;

import com.togacure.graphlib.exceptions.PathNotFoundException;
import com.togacure.graphlib.exceptions.VertexNotFoundException;
import com.togacure.graphlib.interfaces.IGraphPathFinder;
import com.togacure.graphlib.interfaces.IVertex;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.val;
import org.springframework.beans.factory.annotation.Value;

/**
 * Implementation with separate find-path operation for two task:
 * <p>
 * - find all possible paths as back-linked edges set
 * - select single path
 *
 * @author Vitaly Alekseev
 * @since 9/24/2019
 */
public abstract class AbstractGraphPathFinder implements IGraphPathFinder {

    @Setter
    @Getter
    @Value("${frontier.queue.capacity:16}")
    protected int frontierQueueCapacity = 16;

    @Override
    public <T extends IVertex> List<T> findPath(@NonNull T from, @NonNull T to, @NonNull Map<T, Set<T>> graph) throws VertexNotFoundException, PathNotFoundException {
        return findSinglePath(from, to, findAllOutgoingEdges(from, to, graph));
    }


    /**
     * API for find all outgoing @{code from} edges as back-linked edges set
     *
     * @param from  - vertex start
     * @param to    - vertex goal
     * @param graph - all graph vertices with their neighbors
     * @return - back-linked edges set
     * @throws VertexNotFoundException
     */
    protected abstract <T extends IVertex> Map<T, T> findAllOutgoingEdges(T from, T to, Map<T, Set<T>> graph) throws VertexNotFoundException;

    /**
     * Override for optimize single path search
     *
     * @param from  - vertex start
     * @param to    - vertex goal
     * @param edges - all possible paths as back-linked edges set
     * @return - ordered vertices list as (non optimal) path
     * @throws PathNotFoundException
     */
    protected <T extends IVertex> List<T> findSinglePath(@NonNull T from, @NonNull T to, @NonNull Map<T, T> edges) throws PathNotFoundException {
        val result = new LinkedList<T>();
        T current = to;
        do {
            result.addFirst(current);
            current = edges.get(current);
            if (current == null) {
                throw new PathNotFoundException(from, to);
            }
        } while (!current.equals(from));
        result.addFirst(from);
        return result;
    }


    protected static int toPowOf2(int in) {
        int i = 1;
        while (in / (int) Math.pow(i, 2) >= 1) i++;
        return (int) Math.pow(i, 2);
    }

}
