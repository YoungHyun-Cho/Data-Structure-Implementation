package data_structure.queue;

/*
* Queue 구현 연습
* - 방법 2가지
*   1. ArrayList 사용, front, rear 포인터 사용
*       - 구현 방법이 2번보다 간단하지 않고, 내부 저장 공간 사용량이 다소 비효율적이지만,
*       - 포인터를 사용한 큐 원리 복습을 위해 1번 방법 선택
*   2. LinkedList 사용
*       - 구현 방법 매우 간단함.
*       - 내부 동작 효율적임
*       - 컬렉션 프레임워크에 이미 Queue는 LinkedList로 구현되어 있음.
*
* - 배열로 구현하지 않고 컬렉션으로 구현한 이유
*   - 다양한 타입의 요소를 저장하기 위해 제네릭을 사용하여야 함.
*   - 배열의 경우, T[] 타입의 배열 변수 선언 불가
*   - 따라서, 제네릭 타입을 사용할 수 있는 컬렉션 프레임워크를 사용하여 구현
* */

import data_structure.queue.exception.EmptyPracticeQueueException;
import data_structure.queue.exception.OverflowPracticeQueueException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PracticeQueue<T> {

    private ArrayList<T> queue; // 큐 본체
    private int capacity;       // 큐 용량
    private int front;          // front 포인터
    private int rear;           // rear 포인터

    // 생성자 : 큐 생성 및 관련 멤버 변수 초기화
    public PracticeQueue(int capacity) {
        this.queue = new ArrayList<>(capacity);
        this.capacity = capacity;
        this.front = this.rear = 0;
    }

    // 큐 용량에 대해서만 Public Getter 제공
    public int getCapacity() {
        return capacity;
    }

    // 큐 맨 뒤에 요소 추가
    public T enqueue(T el) {
        if (isFull()) throw new OverflowPracticeQueueException();
        queue.add(el);
        rear++;
        return el;
    }

    // 큐 맨 앞의 요소 제거
    public T dequeue() {
        if (isEmpty()) throw new EmptyPracticeQueueException();

        T el = queue.get(front);

        if (queue.size() == 1) clear();
        else front++;

        return el;
    }

    // 디큐 대상 요소를 읽어서 확인
    public T peek() {
        if (isEmpty()) throw new EmptyPracticeQueueException();
        return queue.get(front);
    }

    // 저장된 요소 갯수
    public int size() {
        return queue.size();
    }

    // 큐가 비어있는지 확인
    public boolean isEmpty() {
        return front == rear;
    }

    // 큐가 가득 찼는지 확인
    public boolean isFull() {
        return rear == capacity;
    }

    // 큐의 모든 요소 리턴
    public List<T> dump() {
        if (isEmpty()) throw new EmptyPracticeQueueException();
        return Collections.unmodifiableList(queue);
    }

    // 큐 모든 요소 삭제
    public void clear() {
        queue.clear();
        front = rear = 0;
    }
}
