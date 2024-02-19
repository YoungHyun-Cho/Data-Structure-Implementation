package data_structure.graph.exception;

public class VertexNotFoundException extends RuntimeException {
    public VertexNotFoundException() {
        super("Vertex not found");
    }
}
