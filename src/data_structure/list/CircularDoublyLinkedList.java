package data_structure.list;

/*
* CircularDoublyLinkedList 구현 연습
* - next, prev 양방향 탐색 가능
* - head - tail 연결 -> 순환
* */

import data_structure.list.exception.DataNotFoundException;
import data_structure.list.exception.EmptyListException;
import data_structure.list.exception.InvalidIndexException;

import java.util.ArrayList;
import java.util.Comparator;

public class CircularDoublyLinkedList<T extends Comparable> {

    private Node<T> head;
    private int size;

    // 멤버 클래스로 Node 정의
    private class Node<T> {
        private T data;       // 값
        private Node<T> prev; // 이전 노드 포인터
        private Node<T> next; // 다음 노드 포인터

        // head에 사용할 더미 노드 생성자
        private Node() {
            this.data = null;
            prev = next = this;
        }

        // 노드 생성자
        private Node(T data, Node<T> prev, Node<T> next) {
            this.data = data;
            this.prev = prev;
            this.next = next;
        }
    }

    // public 생성자 : head 노드만 초기화, 최대 용량 제한 없음.
    public CircularDoublyLinkedList() {
        this.head = new Node<>();
        this.size = 0;
    }

    // 사이즈에 대한 public getter 제공
    public int getSize() {
        return size;
    }

    // 리스트가 비어있는지 확인
    public boolean isEmpty() {
        return size == 0 && head.next == head;
    }

    // 노드 검색 -> 일부 정보만 가진 오브젝트로 리스트에서 전체 정보를 가진 오브젝트 검색 가능
    public T get(T data) {
        return findByData(data).data;
    }

    // 인덱스로 노드 검색
    public T get(int index) {
        return findByIndex(index).data;
    }

    private Node<T> findByData(T data) {
        Node<T> node = head.next;
        for (int i = 0; i < size; i++) {
            if (data.compareTo(node.data) == 0) return node;
            node = node.next;
        }
        throw new DataNotFoundException();
    }

    private Node<T> findByIndex(int index) {
        if (0 > index || size <= index) throw new InvalidIndexException();
        Node<T> node = head.next;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node;
    }

    // 특정 값을 가진 노드 인덱스 확인
    public int indexOf(T data) {
        Node<T> node = head.next;
        int index = 0;
        while (index < size) {
            if (data.compareTo(node.data) == 0) return index;
            index++;
            node = node.next;
        }
        throw new DataNotFoundException();
    }

    // 모든 노드 데이터를 ArrayList로 리턴
    public ArrayList<T> toArrayList() {
        if (isEmpty()) throw new EmptyListException();
        Node<T> node = head.next;
        ArrayList<T> arrayList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            arrayList.add(node.data);
            node = node.next;
        }
        return arrayList;
    }

    // 리스트 맨 뒤에 요소 추가
    public void add(T data) {
        // if : 첫 요소 추가면 next, prev에 자기 자신 저장 / else : 기존의 맨 뒤, 맨 앞을 연결
        if (isEmpty()) insertNode(data);
        else insertNode(data, head.next.prev, head.next, false);
    }

    // 특정 위치에 요소 추가
    public void add(int index, T data) {
        // 마지막에 추가하는 것과 같다면 그냥 add(T data) 호출
        if (size == index) add(data);
        else {
            // 추가될 노드의 이전, 다음 노드 포인터
            Node<T> prevNode;
            Node<T> nextNode = findByIndex(index);

            if (index == 0) {
                prevNode = findByIndex(size - 1);
                insertNode(data, prevNode, nextNode, true);
            }
            else {
                prevNode = findByIndex(index - 1);
                insertNode(data, prevNode, nextNode, false);
            }
        }
    }

    // 첫 요소 추가 시 실행
    private void insertNode(T data) {
        // 추가될 노드의 이전, 다음 포인터에 자기 자신 설정
        Node<T> newNode = new Node<>(data, null, null);
        newNode.prev = newNode.next = newNode;
        head.next = newNode;

        size++;
    }

    // 노드 생성 및 관련 노드 간의 포인터 설정
    private void insertNode(T data, Node<T> prevNode, Node<T> nextNode, boolean isFirst) {
        // 추가될 노드에 이전, 다음 포인터 설정
        Node<T> newNode = new Node<>(data, prevNode, nextNode);

        // 이전 노드와 다음 노드에 새로운 노드를 각각 next, prev로 설정
        prevNode.next = nextNode.prev = newNode;

        // 첫 번째 자리에 들어오는 노드라면 헤드의 next와 연결
        if (isFirst) head.next = newNode;

        // 사이즈 1 증가
        size++;
    }

    // 데이터로 노드 삭제
    public void remove(T data) {
        Node<T> node = findByData(data);
        deleteNode(node);
    }

    // 인덱스로 노드 삭제
    public void remove(int index) {
        Node<T> node = findByIndex(index);
        deleteNode(node);
    }

    // 노드 삭제 메커니즘
    private void deleteNode(Node<T> node) {
        Node<T> prevNode = node.prev; // 재윤
        Node<T> nextNode = node.next; // 재우

        // 이전, 다음 노드의 포인터 수정
        prevNode.next = nextNode;
        nextNode.prev = prevNode;

        size--;
        if (head.next == node) head.next = nextNode;
        if (size == 0) head.next = head;
    }

    // 모든 노드 삭제
    public void clear() {
        if (isEmpty()) throw new EmptyListException();
        Node<T> node = head.next;
        while (size > 0) {
            deleteNode(node);
            node = node.next;
        }
        head.next = head;
    }

    @Override
    public String toString() {
        Node<T> node = head.next;
        StringBuffer stringBuffer = new StringBuffer("");
        for (int i = 0; i < size; i++) {
            stringBuffer.append("i: " + i + ", size: " + size + ", data : " + node.data.toString() + ", prev : " + node.prev.data.toString() + ", next : " + node.next.data.toString() + "\n");
            node = node.next;
        }
        return stringBuffer.toString();
    }
}
