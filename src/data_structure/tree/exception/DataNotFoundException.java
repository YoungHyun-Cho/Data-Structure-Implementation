package data_structure.tree.exception;

public class DataNotFoundException extends RuntimeException {
    public DataNotFoundException() {
        super("Data not found");
    }
}
