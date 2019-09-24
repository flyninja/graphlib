package com.togacure.graphlib.interfaces;

/**
 * Undirected edge interface
 *
 * @author Vitaly Alekseev
 * @since 9/23/2019
 */
public interface IEdge<T extends IVertex> {

    T getOne();

    T getTwo();
}
