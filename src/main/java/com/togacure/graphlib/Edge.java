package com.togacure.graphlib;

import com.togacure.graphlib.interfaces.IEdge;
import com.togacure.graphlib.interfaces.IVertex;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

/**
 * @author Vitaly Alekseev
 * @since 9/23/2019
 */
@Getter
@Builder
public class Edge<T extends IVertex> implements IEdge<T> {
    @NonNull
    private final T one;
    @NonNull
    private final T two;
}
