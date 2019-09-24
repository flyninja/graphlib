package com.togacure.graphlib.exceptions;

import com.togacure.graphlib.interfaces.IVertex;

/**
 * @author Vitaly Alekseev
 * @since 9/23/2019
 */
public class VertexNotFoundException extends Exception {

    public VertexNotFoundException(IVertex vertex) {
        super(String.format("[%s] not found in graph", vertex));
    }

}
