package data_structure.stack.exception;

public class OverflowPracticeStackException extends RuntimeException {
    public OverflowPracticeStackException() {
        super("Stack is already full");
    }
}
