package data_structure.stack;

import data_structure.remind.RemindStack;
import data_structure.remind.exception.EmptyDataException;
import data_structure.remind.exception.FullDataException;
import data_structure.stack.exception.EmptyPracticeStackException;
import data_structure.stack.exception.OverflowPracticeStackException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class StackTest {

    private static RemindStack stack; // Test에 사용할 Stack

    // 테스트 실행 전, 한 번만 스택 생성 및 초기화
    @BeforeAll
    public static void initiateStack() {
        stack = new RemindStack(5);
    }

    // 각 테스트 후 스택 비우기
    @AfterEach
    public void clearStack() {
        stack.clear();
    }

    @Test
    public void pushTest() {
        stack.push(1);
        assertEquals(stack.peek(), 1);
        stack.push(2);
        assertEquals(stack.peek(), 2);
        stack.push(3);
        assertEquals(stack.peek(), 3);
        stack.push(4);
        assertEquals(stack.peek(), 4);
        stack.push(5);
        assertEquals(stack.peek(), 5);
    }

    @Test
    public void pushThrowTest() {
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);

        // 스택이 꽉차면 OverflowPracticeStackException을 뱉어야 한다.
        assertThrows(FullDataException.class, () -> stack.push(6));
    }

    @Test
    public void popTest() {
        stack.push(1);
        stack.push(2);
        stack.push(3);

        // pop()을 호출했을 때, 추가 순서의 역순으로 값이 꺼내져야 한다.
        assertEquals(stack.pop(), 3);
        assertEquals(stack.pop(), 2);
        assertEquals(stack.pop(), 1);
    }

    @Test
    public void popThrowTest() {
        // 스택이 비어 있는 상태에서 pop()을 호출하면 EmptyPracticeStackException를 뱉어야 한다.
//        assertThrows(EmptyPracticeStackException.class, () -> stack.pop());
        assertThrows(EmptyDataException.class, () -> stack.pop());
    }

    @Test
    public void peekTest() {
        stack.push(1);
        stack.push(2);
        stack.push(3);

        // 최상단 요소는 3이어야 하며, 값을 읽어오기만 할 뿐, 제거하지는 말아야 한다.
        assertEquals(stack.peek(), 3);
        assertEquals(stack.size(), 3);
    }

    @Test
    public void peekThrowTest() {
        // 요소가 없을 때에는 EmptyPracticeStackException을 뱉어야 한다.
        assertThrows(EmptyDataException.class, () -> stack.peek());
    }

    @Test
    public void dumpTest() {
        stack.push(1);
        stack.push(2);
        stack.push(3);

//        List<Integer> dump = stack.dump();
        int[] dump = stack.dump();

        // 결과 리스트의 각 요소가 제대로 리턴되는지 확인한다.
//        assertEquals(dump.get(0), 1);
//        assertEquals(dump.get(1), 2);
//        assertEquals(dump.get(2), 3);
        assertEquals(1, dump[0]);
        assertEquals(2, dump[1]);
        assertEquals(3, dump[2]);
    }

    @Test
    public void dumpThrowTest() {
        // 스택이 비어있는 상태에서 dump()를 호출하면 EmptyPracticeStackException를 뱉어야 한다.
        assertThrows(EmptyDataException.class, () -> stack.dump());
    }

//    @Test
////    public void dumpModifyThrowTest() {
////        stack.push(1);
////
////        // dump()를 통해 전달받은 리스트를 직접적으로 수정하고자 할 때, UnsupportedOperationException를 뱉어야 한다.
////        assertThrows(UnsupportedOperationException.class, () -> stack.dump().add(1));
////    }

    @Test
    public void sizeTest() {
        // 요소를 추가할 때마다 size가 정확히 리턴되어야 한다.
        assertEquals(stack.size(), 0);
        stack.push(1);
        assertEquals(stack.size(), 1);
        stack.push(2);
        assertEquals(stack.size(), 2);
        stack.push(3);
        assertEquals(stack.size(), 3);
    }

    @Test
    public void clearTest() {
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.clear();

        // clear() 호출 시, 요소의 갯수가 0이 되어야 한다.
        assertEquals(0, stack.size());
    }

    @Test
    public void isEmptyTest() {
        // 스택이 비어있으면 true가 리턴되어야 한다.
        assertEquals(stack.isEmpty(), true);

        // 스택이 비어있지 않다면 false가 리턴되어야 한다.
        stack.push(1);
        assertEquals(stack.isEmpty(), false);
    }
}
