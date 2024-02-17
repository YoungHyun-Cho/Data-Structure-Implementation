package data_structure.tree.exception;

public class ObjectRedundancyException extends RuntimeException {
    public ObjectRedundancyException() {
        super("Same object already exists");
    }
}
