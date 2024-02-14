package data_structure.stack;

import data_structure.stack.exception.EmptyPracticeStackException;
import data_structure.stack.exception.OverflowPracticeStackException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
* Stack 구현 연습
* 여러 타입의 데이터를 저장하기 위해 제네릭과 ArrayList를 사용
* ArrayList 선택 이유
*   - 물리적인 저장 구조 -> 메모리 상 각 요소가 물리적으로 인접하여 위치함.
*       - 맨 뒤에서만 순차적인 추가와 삭제만 발생하는 Stack의 특성 상, ArrayList로 구현하는 것이 효율적일 것으로 판단
*       - 저장 순서를 유지함.
*
* Stack 본체(멤버 변수 stack)에 직접적인 접근 및 수정을 막고자,
*   - private으로 접근 범위 제한
*   - Getter 및 Setter 제공 X
*   - 전체 요소를 리턴할 때에는 unmodifiableList로 바꿔서 리턴
*       - 예 : stack.dump().add(1); -> UnsupportedOperationException 발생
* */
public class PracticeStack<T> {

    private ArrayList<T> stack; // Stack 본체
    private int p; // 최상단 요소(Top)를 가리키는 포인터
    private int capacity; // 스택 용량

    // 생성자 : 용량을 입력 받아 스택을 생성하고, 포인터 및 용량을 초기화함.
    public PracticeStack(int capacity) {
        stack = new ArrayList<>(capacity);
        p = -1;
        this.capacity = capacity;
    }

    // 스택 용량에 대해서만 public getter 제공
    public int getCapacity() {
        return capacity;
    }

    // 최상단 요소를 리턴
    public T peek() {
        if (p < 0) throw new EmptyPracticeStackException();
        return stack.get(p);
    }

    // 요소 추가
    public T push(T el) {
        if (p < capacity - 1) stack.add(p++ + 1, el);
        else throw new OverflowPracticeStackException();
        return peek();
    }

    // 요소 삭제
    public T pop() {
        if (p < 0) throw new EmptyPracticeStackException();
        return stack.get(p--);
    }

    // 스택 전체 요소 리턴 (읽기만 가능)
    public List<T> dump() {
        if (isEmpty()) throw new EmptyPracticeStackException();
        return Collections.unmodifiableList(stack);
    }

    // 스택이 가진 요소의 개수
    public int size() {
        return p + 1;
    }

    // 스택 비우기
    public void clear() {
        p = -1;
        stack.clear();
    }

    // 스택이 비어있는지 확인
    public boolean isEmpty() {
        return stack.isEmpty();
    }
}
