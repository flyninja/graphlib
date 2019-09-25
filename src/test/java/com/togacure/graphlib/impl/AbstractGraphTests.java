package com.togacure.graphlib.impl;

import com.togacure.graphlib.TestVertex;
import com.togacure.graphlib.enums.GraphType;
import com.togacure.graphlib.enums.PathFinderAlgorithm;
import com.togacure.graphlib.exceptions.EdgeExistException;
import com.togacure.graphlib.exceptions.PathNotFoundException;
import com.togacure.graphlib.exceptions.VertexExistException;
import com.togacure.graphlib.interfaces.IGraph;
import com.togacure.graphlib.interfaces.IGraphFactory;
import lombok.SneakyThrows;
import lombok.val;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Vitaly Alekseev
 * @since 9/25/2019
 */
public abstract class AbstractGraphTests {

    @Autowired
    protected IGraphFactory factory;

    protected abstract IGraph<TestVertex> getGraph();

    @Test(expected = VertexExistException.class)
    @SneakyThrows
    public void buildWithExistVertexFaultTest() {
        final IGraph<TestVertex> graph = getGraph();
        val testCases = TestVertex.buildTestCases(graph, 3);
        graph.addVertex(new TestVertex(1));
    }

    @Test
    @SneakyThrows
    public void findPathWithSingleExistPathSuccessTest() {
        final IGraph<TestVertex> graph = getGraph();

        val testCases = TestVertex.buildTestCases(graph, 10);

        //          1
        //          /\
        //     6-4-2  3-5
        //        \
        //         7-8
        //         |
        //         9-10
        TestVertex.buildNeighbors(graph, testCases.get(0/*1*/), 2, 3);
        TestVertex.buildNeighbors(graph, testCases.get(1/*2*/), 4);
        TestVertex.buildNeighbors(graph, testCases.get(2/*3*/), 5);
        TestVertex.buildNeighbors(graph, testCases.get(3/*4*/), 6, 7);
        /*5*/
        /*6*/
        TestVertex.buildNeighbors(graph, testCases.get(6/*7*/), 8, 9);
        /*8*/
        TestVertex.buildNeighbors(graph, testCases.get(8/*9*/), 10);
        /*10*/

        val path = graph.getPath(testCases.get(1/*2*/), testCases.get(8/*9*/));

        Assert.assertEquals(4, path.size());

        Assert.assertTrue(path.contains(testCases.get(1/*2*/)));
        Assert.assertTrue(path.contains(testCases.get(3/*4*/)));
        Assert.assertTrue(path.contains(testCases.get(6/*7*/)));
        Assert.assertTrue(path.contains(testCases.get(8/*9*/)));

    }

    @Test(expected = PathNotFoundException.class)
    @SneakyThrows
    public void findPathWithNotExistPathSuccessTest() {
        final IGraph<TestVertex> graph = getGraph();

        val testCases = TestVertex.buildTestCases(graph, 10);

        //          1
        //          /\
        //     6-4-2  3-5
        //
        //         7-8
        //         |
        //         9-10
        TestVertex.buildNeighbors(graph, testCases.get(0/*1*/), 2, 3);
        TestVertex.buildNeighbors(graph, testCases.get(1/*2*/), 4);
        TestVertex.buildNeighbors(graph, testCases.get(2/*3*/), 5);
        TestVertex.buildNeighbors(graph, testCases.get(3/*4*/), 6);
        /*5*/
        /*6*/
        TestVertex.buildNeighbors(graph, testCases.get(6/*7*/), 8, 9);
        /*8*/
        TestVertex.buildNeighbors(graph, testCases.get(8/*9*/), 10);
        /*10*/

        graph.getPath(testCases.get(1/*2*/), testCases.get(8/*9*/));
    }

    @Test
    @SneakyThrows
    public void findPathWithDoubleExistPathSuccessTest() {
        final IGraph<TestVertex> graph = getGraph();

        val testCases = TestVertex.buildTestCases(graph, 10);

        //          1
        //          /\
        //     6-4-2  3-5
        //     |  \
        //     --- 7-8
        //         |
        //         9-10
        TestVertex.buildNeighbors(graph, testCases.get(0/*1*/), 2, 3);
        TestVertex.buildNeighbors(graph, testCases.get(1/*2*/), 4);
        TestVertex.buildNeighbors(graph, testCases.get(2/*3*/), 5);
        TestVertex.buildNeighbors(graph, testCases.get(3/*4*/), 6, 7);
        /*5*/
        TestVertex.buildNeighbors(graph, testCases.get(5/*6*/), 7);
        TestVertex.buildNeighbors(graph, testCases.get(6/*7*/), 8, 9);
        /*8*/
        TestVertex.buildNeighbors(graph, testCases.get(8/*9*/), 10);
        /*10*/

        val path = graph.getPath(testCases.get(1/*2*/), testCases.get(8/*9*/));

        Assert.assertEquals(4, path.size());

        Assert.assertTrue(path.contains(testCases.get(1/*2*/)));
        Assert.assertTrue(path.contains(testCases.get(3/*4*/)));
        Assert.assertTrue(path.contains(testCases.get(6/*7*/)));
        Assert.assertTrue(path.contains(testCases.get(8/*9*/)));

    }
}
