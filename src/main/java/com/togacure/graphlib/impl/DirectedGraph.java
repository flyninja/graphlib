package com.togacure.graphlib.impl;

import com.togacure.graphlib.exceptions.EdgeExistException;
import com.togacure.graphlib.exceptions.PathNotFoundException;
import com.togacure.graphlib.exceptions.VertexExistException;
import com.togacure.graphlib.exceptions.VertexNotFoundException;
import com.togacure.graphlib.interfaces.IEdge;
import com.togacure.graphlib.interfaces.IGraphPathFinder;
import com.togacure.graphlib.interfaces.IVertex;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import lombok.NonNull;

/**
 * Directed full (read/write) thread-safe graph implementation
 *
 * @author Vitaly Alekseev
 * @since 9/25/2019
 */
public class DirectedGraph<T extends IVertex> extends AbstractGraph<T> {

    public DirectedGraph(@NonNull IGraphPathFinder pathFinder) {
        super(pathFinder, new HashMap<>());
    }

    @Override
    public synchronized void addVertex(@NonNull T vertex) throws VertexExistException {
        super.addVertex(vertex);
    }

    @Override
    public synchronized void addEdge(@NonNull IEdge<T> edge) throws VertexNotFoundException, EdgeExistException {
        super.addEdge(edge);
    }

    @Override
    public synchronized List<T> getPath(@NonNull T from, @NonNull T to) throws VertexNotFoundException, PathNotFoundException {
        return super.getPath(from, to);
    }

    protected void linkPrev(T prev, Set<T> nextNeighbors) {

    }

    protected void checkEdgeExist(IEdge<T> edge, Set<T> firstNeighbors, Set<T> secondNeighbors) throws EdgeExistException {
        if (firstNeighbors.contains(edge.getTwo())) {
            throw new EdgeExistException(edge);
        }
    }
}
