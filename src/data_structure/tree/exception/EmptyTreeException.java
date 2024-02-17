package data_structure.tree.exception;

public class EmptyTreeException extends RuntimeException {
    public EmptyTreeException() {
        super("Tree is empty");
    }
}
