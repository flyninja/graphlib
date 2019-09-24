package com.togacure.graphlib.exceptions;

import com.togacure.graphlib.interfaces.IVertex;

/**
 * @author Vitaly Alekseev
 * @since 9/23/2019
 */
public class VertexExistException extends Exception {

    public VertexExistException(IVertex vertex) {
        super(String.format("vertex [%s] always exist in graph", vertex));
    }

}
