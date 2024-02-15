package data_structure.queue.exception;

public class OverflowPracticeQueueException extends RuntimeException {
    public OverflowPracticeQueueException() {
        super("Queue is already full");
    }
}
