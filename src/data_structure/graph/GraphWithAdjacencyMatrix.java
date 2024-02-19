package data_structure.graph;

import data_structure.graph.exception.OverflowGraphException;
import data_structure.graph.exception.VertexNotFoundException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GraphWithAdjacencyMatrix {
    private int[][] matrix;
    private int capacity;
    private int size = 0;
    private int[] emptyQueue;
    private int ptr = -1;

    public GraphWithAdjacencyMatrix(int capacity) {
        this.matrix = new int[capacity][this.capacity = capacity];
        emptyQueue = new int[capacity];
        fillMinusOne();
    }

    private void fillMinusOne() {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                matrix[i][j] = -1;
            }
        }
    }

    public void addVertex(int vertex) {
        if (size == capacity) throw new OverflowGraphException();

        size++;

        int idx;
        if (ptr >= 0) idx = emptyQueue[ptr--];
        else idx = size;

        matrix[0][idx] = matrix[idx][0] = vertex;

        for (int i = 1; i <= idx; i++) {
            for (int j = 1; j <= idx; j++) {
                if (i == idx) matrix[i][j] = 0;
                else if (j == idx) matrix[i][j] = 0;
            }
        }

        for (int i = idx + 1; i <= size; i++) {
            matrix[idx][i] = 0;
            matrix[i][idx] = 0;
        }

        matrix[idx][idx] = 0;
    }

    public void addEdge(int vertex1, int vertex2) {
        checkVertex(vertex1, vertex2);
        int v1Index = getIndex(vertex1);
        int v2Index = getIndex(vertex2);
        matrix[v1Index][v2Index] = 1;
        matrix[v2Index][v1Index] = 1;
    }

    private void checkVertex(int vertex1, int vertex2) {
        int check = 0;
        for (int i = 1; i <= size; i++) {
            if (matrix[i][0] == vertex1 || matrix[i][0] == vertex2) check++;
        }
        if (check != 2) throw new VertexNotFoundException();
    }

    private int getIndex(int vertex) {
        for (int i = 1; i <= size; i++) {
            if (matrix[i][0] == vertex) return i;
        }
        throw new VertexNotFoundException();
    }

    public void removeVertex(int vertex) {
        int vIndex = getIndex(vertex);
        matrix[0][vIndex] = matrix[vIndex][0] = -1;
        for (int i = 1; i <= size; i++) {
            matrix[i][vIndex] = -1;
            matrix[vIndex][i] = -1;
        }
        size--;
        emptyQueue[++ptr] = vIndex;
    }

    public void removeEdge(int vertex1, int vertex2) {
        checkVertex(vertex1, vertex2);
        int v1Index = getIndex(vertex1);
        int v2Index = getIndex(vertex2);
        matrix[v1Index][v2Index] = 0;
        matrix[v2Index][v1Index] = 0;
    }

    public int[] getLinkedVertex(int vertex) {
        int index = getIndex(vertex);
        int[] linked = new int[size];
        int lIndex = 0;
        for (int i = 1; i <= size; i++) {
            if (matrix[index][i] == 1) linked[lIndex++] = matrix[i][0];
        }
        return linked;
    }

    // 테스트용
    public ArrayList<ArrayList<Integer>> getMatrix() {
        ArrayList<ArrayList<Integer>> arrayList = new ArrayList<>();
        arrayList.add(new ArrayList<>());

        for (int i = 0; i < matrix.length; i++) {
            if (arrayList.get(i).isEmpty()) arrayList.add(new ArrayList<>());
            for (int j = 0; j < matrix.length; j++) {
                arrayList.get(i).add(matrix[i][j]);
            }
        }

        return arrayList;
    }

    // 테스트용
    public ArrayList<Integer> getEmptyQueue() {
        ArrayList<Integer> arrayList = new ArrayList<>();
        for (int i = 0; i < emptyQueue.length; i++) {
            arrayList.add(emptyQueue[i]);
        }
        return arrayList;
    }

    public void printMatrix() {

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                System.out.print(matrix[i][j] < 0 ? matrix[i][j] + " " : " " + matrix[i][j] + " ");
            }
            System.out.println();
        }
    }
}
