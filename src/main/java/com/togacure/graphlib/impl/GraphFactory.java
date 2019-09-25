package com.togacure.graphlib.impl;

import com.togacure.graphlib.enums.GraphType;
import com.togacure.graphlib.exceptions.PathFinderNotFoundException;
import com.togacure.graphlib.exceptions.UnsupportedGraphTypeException;
import com.togacure.graphlib.interfaces.IGraph;
import com.togacure.graphlib.interfaces.IGraphFactory;
import com.togacure.graphlib.interfaces.IGraphPathFinder;
import com.togacure.graphlib.interfaces.IVertex;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import lombok.NonNull;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Vitaly Alekseev
 * @since 9/24/2019
 */
@Component
public class GraphFactory implements IGraphFactory {

    @Autowired(required = false)
    private final List<IGraphPathFinder> pathFinders = new ArrayList<>();

    @Override
    public void addPathFinder(@NonNull IGraphPathFinder pathFinder) {
        pathFinders.add(pathFinder);
    }

    @Override
    public <T extends IVertex> IGraph<T> getGraph(GraphType type, @NonNull Predicate<IGraphPathFinder> pathFinderPredicate) throws UnsupportedGraphTypeException, PathFinderNotFoundException {
        val finder = pathFinders.stream().filter(pathFinderPredicate).findFirst().orElseThrow(() -> new PathFinderNotFoundException());
        switch (type) {
            case UNDIRECTED:
                return new UndirectedGraph<T>(finder);
            case DIRECTED:
                return new DirectedGraph<T>(finder);
            default:
                throw new UnsupportedGraphTypeException(type);
        }
    }

}
