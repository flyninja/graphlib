package com.togacure.graphlib.exceptions;

import com.togacure.graphlib.interfaces.IVertex;

/**
 * @author Vitaly Alekseev
 * @since 9/25/2019
 */
public class PathNotFoundException extends Exception {

    public PathNotFoundException(IVertex from, IVertex to) {
        super(String.format("path from:[%s] to:[%s] does not exist", from, to));
    }
}
