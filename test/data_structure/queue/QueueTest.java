package data_structure.queue;

import data_structure.queue.exception.EmptyPracticeQueueException;
import data_structure.queue.exception.OverflowPracticeQueueException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class QueueTest {

    private static PracticeQueue<Integer> queue; // 테스트에 사용할 큐

    // 큐 초기화
    @BeforeAll
    public static void initiateQueue() {
        queue = new PracticeQueue<>(5);
    }

    // 각 테스트 수행 후 뷰 비우기
    @AfterEach
    public void clearQueue() {
        queue.clear();
    }

    @Test
    public void enqueueTest() {
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);

        // 큐에 요소가 정확히 추가되었는지 확인
        assertEquals(queue.dump().toString(), "[1, 2, 3]");
    }

    @Test
    public void enqueueThrowTest() {
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(4);
        queue.enqueue(5);

        // 큐의 용량을 초과하여 요소를 추가하면 OverflowPracticeQueueException 발생
        assertThrows(OverflowPracticeQueueException.class, () -> queue.enqueue(6));
    }

    @Test
    public void dequeueTest() {
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);

        // 선입선출 -> 출력 순서와 입력 순서가 같아야 함.
        assertEquals(queue.dequeue(), 1);
        assertEquals(queue.dequeue(), 2);
        assertEquals(queue.dequeue(), 3);
    }

    @Test
    public void dequeueReuseTest() {
        queue.enqueue(1);
        queue.dequeue();

        // 큐에 요소가 모두 비워졌다가 다시 최대 용량만큼 사용될 수 있어야 함.
        assertEquals(queue.enqueue(1), 1);
        assertEquals(queue.enqueue(2), 2);
        assertEquals(queue.enqueue(3), 3);
        assertEquals(queue.enqueue(4), 4);
        assertEquals(queue.enqueue(5), 5);
    }

    @Test
    public void dequeueThrowTest() {
        // 큐가 비어있는 상황에서 디큐 호출 시, EmptyPracticeQueueException를 발생시켜야 함.
        assertThrows(EmptyPracticeQueueException.class, () -> queue.dequeue());
    }

    @Test
    public void peekTest() {
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);

        // 디큐될 요소를 단순히 읽어와 확인하여야 하며, peek()의 호출 이후에도 큐 사용량은 같아야 함.
        assertEquals(queue.peek(), 1);
        assertEquals(queue.size(), 3);
    }

    @Test
    public void peekThrowTest() {
        // 큐가 비어있을 때 peek()을 호출하면 EmptyPracticeQueueException 발생
        assertThrows(EmptyPracticeQueueException.class, () -> queue.peek());
    }

    @Test
    public void dumpTest() {
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);

        List<Integer> dump = queue.dump();

        // dump()를 호출하면 큐의 내용물을 List로 리턴함.
        assertEquals(dump.get(0), 1);
        assertEquals(dump.get(1), 2);
        assertEquals(dump.get(2), 3);
    }

    @Test
    public void dumpThrowTest() {
        // 큐가 비어있을 때 dump()를 호출하면 EmptyPracticeQueueException 발생
        assertThrows(EmptyPracticeQueueException.class, () -> queue.dump());
    }

    @Test
    public void dumpModifyThrowTest() {
        queue.enqueue(1);

        // dump()로 리턴받은 리스트는 수정할 수 없음.
        assertThrows(UnsupportedOperationException.class, () -> queue.dump().add(1));
    }

    @Test
    public void clearTest() {
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.clear();

        // 모든 요소를 제거함.
        assertEquals(queue.size(), 0);
    }

    @Test
    public void clearReuseTest() {
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.clear();

        // 큐에 요소가 모두 비워졌다가 다시 최대 용량만큼 사용될 수 있어야 함.
        assertEquals(queue.enqueue(1), 1);
        assertEquals(queue.enqueue(2), 2);
        assertEquals(queue.enqueue(3), 3);
        assertEquals(queue.enqueue(4), 4);
        assertEquals(queue.enqueue(5), 5);
    }

    @Test
    public void isEmptyTest() {
        // 요소가 없을 때에는 true
        assertEquals(queue.isEmpty(), true);

        // 요소가 있다면 false
        queue.enqueue(1);
        assertEquals(queue.isEmpty(), false);
    }
}

















