package com.togacure.graphlib.interfaces;

import com.togacure.graphlib.enums.GraphType;
import com.togacure.graphlib.enums.PathFinderAlgorithm;
import com.togacure.graphlib.exceptions.PathFinderNotFoundException;
import com.togacure.graphlib.exceptions.UnsupportedGraphTypeException;
import java.util.function.Predicate;

/**
 * @author Vitaly Alekseev
 * @since 9/24/2019
 */
public interface IGraphFactory {

    void addPathFinder(IGraphPathFinder pathFinder);

    <T extends IVertex> IGraph<T> getGraph(GraphType type, Predicate<IGraphPathFinder> pathFinderPredicate) throws UnsupportedGraphTypeException, PathFinderNotFoundException;

    default <T extends IVertex> IGraph<T> getGraph(GraphType type, PathFinderAlgorithm algorithm) throws UnsupportedGraphTypeException, PathFinderNotFoundException {
        return getGraph(type, pf -> pf.getAlgorithm() == algorithm);
    }
}
