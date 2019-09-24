package com.togacure.graphlib.exceptions;

import com.togacure.graphlib.enums.GraphType;

/**
 * @author Vitaly Alekseev
 * @since 9/24/2019
 */
public class UnsupportedGraphTypeException extends Exception {

    public UnsupportedGraphTypeException(GraphType type) {
        super(String.format("graph type [%s] is not supported", type));
    }

}
