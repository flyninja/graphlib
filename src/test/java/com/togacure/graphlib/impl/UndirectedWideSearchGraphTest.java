package com.togacure.graphlib.impl;

import com.togacure.graphlib.Edge;
import com.togacure.graphlib.TestVertex;
import com.togacure.graphlib.enums.GraphType;
import com.togacure.graphlib.enums.PathFinderAlgorithm;
import com.togacure.graphlib.exceptions.EdgeExistException;
import com.togacure.graphlib.interfaces.IGraph;
import lombok.SneakyThrows;
import lombok.val;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

/**
 * @author Vitaly Alekseev
 * @since 9/24/2019
 */
@RunWith(SpringRunner.class)
@ComponentScan(basePackageClasses = { Edge.class })
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = { UndirectedWideSearchGraphTest.class })
public class UndirectedWideSearchGraphTest extends AbstractGraphTests {

    @Override
    @SneakyThrows
    protected IGraph<TestVertex> getGraph() {
        final IGraph<TestVertex> graph = factory.getGraph(GraphType.UNDIRECTED, PathFinderAlgorithm.WIDE_SEARCH);
        Assert.assertTrue(graph instanceof UndirectedGraph);
        return graph;
    }

    @Test(expected = EdgeExistException.class)
    @SneakyThrows
    public void buildWithExistEdgeFaultTest() {
        final IGraph<TestVertex> graph = getGraph();
        val testCases = TestVertex.buildTestCases(graph, 3);
        TestVertex.buildNeighbors(graph, testCases.get(0/*1*/), 3);
        TestVertex.buildNeighbors(graph, testCases.get(2/*3*/), 1);
    }

}
