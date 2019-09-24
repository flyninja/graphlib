package com.togacure.graphlib;

import com.togacure.graphlib.interfaces.IGraph;
import com.togacure.graphlib.interfaces.IVertex;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.ToString;
import lombok.val;

/**
 * @author Vitaly Alekseev
 * @since 9/24/2019
 */
@RequiredArgsConstructor
@ToString(onlyExplicitlyIncluded = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class TestVertex implements IVertex {

    @ToString.Include
    @EqualsAndHashCode.Include
    private final int id;

    @SneakyThrows
    public static List<TestVertex> buildTestCases(IGraph<TestVertex> graph, int size) {
        val result = new ArrayList<TestVertex>(size);
        for (int i = 1; i <= size; i++) {
            val v = new TestVertex(i);
            result.add(v);
            graph.addVertex(v);
        }
        return result;
    }

    public static void buildNeighbors(IGraph<TestVertex> graph, TestVertex from, Integer... neighbors) {
        Arrays.stream(neighbors).map(i -> new TestVertex(i)).forEach(v -> addEdge(graph, from, v));
    }

    @SneakyThrows
    private static void addEdge(IGraph<TestVertex> graph, TestVertex from, TestVertex to) {
        graph.addEdge(Edge.<TestVertex> builder().one(from).two(to).build());
    }
}
