package data_structure.list.exception;

public class EmptyListException extends RuntimeException {
    public EmptyListException() {
        super("List is empty");
    }
}
