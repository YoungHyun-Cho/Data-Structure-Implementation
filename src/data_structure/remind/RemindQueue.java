package data_structure.remind;

import data_structure.queue.exception.EmptyPracticeQueueException;
import data_structure.queue.exception.OverflowPracticeQueueException;

import java.util.Arrays;

public class RemindQueue {
    int[] queue;
    int capacity;
    int front;
    int rear;
    int size;

    public RemindQueue(int capacity) {
        this.queue = new int[this.capacity = capacity];
        front = rear =  0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size <= 0;
    }

    public int enqueue(int el) {
        if (size() >= capacity) throw new OverflowPracticeQueueException();
        queue[rear++] = el;
        size++;
        if (rear == capacity) rear = 0;
        return queue[rear == 0 ? capacity - 1 : rear - 1];
    }

    public int dequeue() {
        if (isEmpty()) throw new EmptyPracticeQueueException();
        size--;
        return queue[front + 1 == capacity ? front = 0 : front++];
    }

    public int peek() {
        if (isEmpty()) throw new EmptyPracticeQueueException();
        return queue[front];
    }

    public void clear() {
        queue = new int[capacity];
        front = rear = 0;
    }

    public int[] dump() {
        if (isEmpty()) throw new EmptyPracticeQueueException();
        return Arrays.copyOf(queue, queue.length);
    }


}
