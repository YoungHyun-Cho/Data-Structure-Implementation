package data_structure.graph.exception;

public class OverflowGraphException extends RuntimeException {
    public OverflowGraphException() {
        super("Graph is full");
    }
}
