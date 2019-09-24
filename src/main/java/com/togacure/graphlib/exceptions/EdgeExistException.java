package com.togacure.graphlib.exceptions;

import com.togacure.graphlib.interfaces.IEdge;

/**
 * @author Vitaly Alekseev
 * @since 9/23/2019
 */
public class EdgeExistException extends Exception {

    public EdgeExistException(IEdge<?> edge) {
        super(String.format("edge [%s, %s] always exist in graph", edge.getOne(), edge.getTwo()));
    }

}
