package data_structure.graph;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class GraphWithAdjacencyMatrixTest {

    private GraphWithAdjacencyMatrix graph;

    @BeforeEach
    public void createGraph() {
        graph = new GraphWithAdjacencyMatrix(5);
    }

    @Test
    public void addVertexTest() {
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);
        graph.printMatrix();

        ArrayList<ArrayList<Integer>> matrix = graph.getMatrix();

        assertEquals(1, matrix.get(1).get(0));
        assertEquals(1, matrix.get(0).get(1));
        assertEquals(2, matrix.get(2).get(0));
        assertEquals(2, matrix.get(0).get(2));
        assertEquals(3, matrix.get(3).get(0));
        assertEquals(3, matrix.get(0).get(3));
    }

    @Test
    public void removeVertexTest() {
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);

        graph.removeVertex(2);
        graph.printMatrix();

        ArrayList<ArrayList<Integer>> matrix = graph.getMatrix();

        assertEquals(-1, matrix.get(2).get(0));
        assertEquals(-1, matrix.get(0).get(2));
        assertEquals(-1, matrix.get(2).get(2));
        assertEquals(-1, matrix.get(1).get(2));
        assertEquals(-1, matrix.get(2).get(1));

        ArrayList<Integer> emptyQueue = graph.getEmptyQueue();

        assertEquals(2, emptyQueue.get(0));
    }

    @Test
    public void removeAndAddVertexTest() {
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);

        graph.removeVertex(2);
        graph.addVertex(2);
        graph.printMatrix();

        ArrayList<ArrayList<Integer>> matrix = graph.getMatrix();

        assertEquals(2, matrix.get(2).get(0));
        assertEquals(2, matrix.get(0).get(2));
        assertEquals(0, matrix.get(2).get(2));
        assertEquals(0, matrix.get(1).get(2));
        assertEquals(0, matrix.get(2).get(1));
    }

    @Test
    public void addEdgeTest() {
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);

        graph.addEdge(1, 2);
        graph.addEdge(1, 3);
        graph.printMatrix();

        ArrayList<ArrayList<Integer>> matrix = graph.getMatrix();

        assertEquals(1, matrix.get(1).get(2));
        assertEquals(1, matrix.get(2).get(1));
        assertEquals(1, matrix.get(1).get(3));
        assertEquals(1, matrix.get(3).get(1));
    }

    @Test
    public void removeEdgeTest() {
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);

        graph.addEdge(1, 2);
        graph.addEdge(1, 3);
        graph.removeEdge(1, 2);

        ArrayList<ArrayList<Integer>> matrix = graph.getMatrix();

        assertEquals(0, matrix.get(1).get(2));
        assertEquals(0, matrix.get(2).get(1));
        assertEquals(1, matrix.get(1).get(3));
        assertEquals(1, matrix.get(3).get(1));
    }

    @Test
    public void removeAndAddEdgeTest() {
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);

        graph.addEdge(1, 2);
        graph.addEdge(1, 3);

        graph.removeEdge(1, 2);
        graph.addEdge(1, 2);
        graph.printMatrix();

        ArrayList<ArrayList<Integer>> matrix = graph.getMatrix();

        assertEquals(1, matrix.get(1).get(2));
        assertEquals(1, matrix.get(2).get(1));
    }

    @Test
    public void getLinkedVertexTest() {
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);

        graph.addEdge(1, 2);
        graph.addEdge(1, 3);

        graph.printMatrix();

        int[] linkedVertex = graph.getLinkedVertex(1);

        assertEquals(2, linkedVertex[0]);
        assertEquals(3, linkedVertex[1]);
    }
}
