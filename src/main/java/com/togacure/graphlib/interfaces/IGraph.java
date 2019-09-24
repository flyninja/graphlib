package com.togacure.graphlib.interfaces;

import com.togacure.graphlib.exceptions.EdgeExistException;
import com.togacure.graphlib.exceptions.PathNotFoundException;
import com.togacure.graphlib.exceptions.VertexExistException;
import com.togacure.graphlib.exceptions.VertexNotFoundException;
import java.util.List;

/**
 * @author Vitaly Alekseev
 * @since 9/23/2019
 */
public interface IGraph<T extends IVertex> {

    void addVertex(T vertex) throws VertexExistException;

    void addEdge(IEdge<T> edge) throws VertexNotFoundException, EdgeExistException;

    List<T> getPath(T from, T to) throws VertexNotFoundException, PathNotFoundException;
}
