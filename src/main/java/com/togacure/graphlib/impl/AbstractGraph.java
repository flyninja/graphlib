package com.togacure.graphlib.impl;

import com.togacure.graphlib.exceptions.EdgeExistException;
import com.togacure.graphlib.exceptions.VertexExistException;
import com.togacure.graphlib.exceptions.VertexNotFoundException;
import com.togacure.graphlib.interfaces.IEdge;
import com.togacure.graphlib.interfaces.IGraph;
import com.togacure.graphlib.interfaces.IGraphPathFinder;
import com.togacure.graphlib.interfaces.IVertex;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.val;

/**
 * @author Vitaly Alekseev
 * @since 9/24/2019
 */
@RequiredArgsConstructor
public abstract class AbstractGraph<T extends IVertex> implements IGraph<T> {

    protected final @NonNull IGraphPathFinder pathFinder;

    /**
     * all vertices of graph with their neighbors
     */
    protected final @NonNull Map<T, Set<T>> vertices;

    @Override
    public void addVertex(@NonNull T vertex) throws VertexExistException {
        val vertices = getVertices();
        if (vertices.containsKey(vertex)) {
            throw new VertexExistException(vertex);
        }
        vertices.put(vertex, new HashSet<>());
    }

    @Override
    public void addEdge(@NonNull IEdge<T> edge) throws VertexNotFoundException, EdgeExistException {
        val vertices = getVertices();
        val first = vertices.get(edge.getOne());
        val second = vertices.get(edge.getTwo());
        if (first == null) {
            throw new VertexNotFoundException(edge.getOne());
        }
        if (second == null) {
            throw new VertexNotFoundException(edge.getTwo());
        }
        if ((first.contains(edge.getOne()) && second.contains(edge.getTwo())) || (first.contains(edge.getTwo()) && second.contains(edge.getOne()))) {
            throw new EdgeExistException(edge);
        }
        linkNext(edge.getTwo(), first);
        linkPrev(edge.getOne(), second);
    }

    @Override
    public List<T> getPath(@NonNull T from, @NonNull T to) throws VertexNotFoundException {
        return pathFinder.findPath(from, to, getVertices());
    }

    protected Map<T, Set<T>> getVertices() {
        return vertices;
    }

    protected void linkNext(T next, Set<T> prevNeighbors) {
        prevNeighbors.add(next);
    }

    protected void linkPrev(T prev, Set<T> nextNeighbors) {
        nextNeighbors.add(prev);
    }
}
