package data_structure.remind;

import data_structure.remind.exception.EmptyDataException;
import data_structure.remind.exception.FullDataException;

import java.util.Arrays;

public class RemindStack {
    int[] stack;
    int p;
    int capacity;

    public RemindStack(int capacity) {
        this.stack = new int[this.capacity = capacity];
        this.p = -1;
    }

    public void push(int el) {
        if (p >= capacity - 1) throw new FullDataException();
        stack[++p] = el;
    }

    public int pop() {
        if (p < 0) throw new EmptyDataException();
        return stack[p--];
    }

    public int peek() {
        if (p < 0) throw new EmptyDataException();
        return stack[p];
    }

    public int[] dump() {
        if (p < 0) throw new EmptyDataException();
        return Arrays.copyOf(stack, stack.length);
    }

    public void clear() {
        stack = new int[capacity];
        p = -1;
    }

    public boolean isEmpty() {
        return p == -1;
    }

    public int size() {
        return p + 1;
    }
}
