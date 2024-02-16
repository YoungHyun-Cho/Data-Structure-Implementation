package data_structure.list.exception;

public class DataNotFoundException extends RuntimeException {
    public DataNotFoundException() {
        super("Data not found");
    }
}
