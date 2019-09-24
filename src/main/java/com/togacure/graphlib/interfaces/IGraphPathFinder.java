package com.togacure.graphlib.interfaces;

import com.togacure.graphlib.enums.PathFinderAlgorithm;
import com.togacure.graphlib.exceptions.VertexNotFoundException;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Vitaly Alekseev
 * @since 9/24/2019
 */
public interface IGraphPathFinder {

    PathFinderAlgorithm getAlgorithm();

    /**
     * API for search single path on graph
     *
     * @param from  - vertex start
     * @param to    - vertex goal
     * @param graph - all graph vertices with their neighbors
     * @return single path vertices @{code from} - @{code to}
     * @throws VertexNotFoundException
     */
    <T extends IVertex> List<T> findPath(T from, T to, Map<T, Set<T>> graph) throws VertexNotFoundException;
}
