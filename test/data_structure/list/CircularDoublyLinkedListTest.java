package data_structure.list;

import data_structure.list.exception.DataNotFoundException;
import data_structure.list.exception.EmptyListException;
import data_structure.list.exception.InvalidIndexException;
import org.junit.jupiter.api.*;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class CircularDoublyLinkedListTest {

    private static CircularDoublyLinkedList<Member> linkedList;

    private static class Member implements Comparable<Member> {

        private int id;
        private String name;
        private int age;

        public Member(int id, String name, int age) {
            this.id = id;
            this.name = name;
            this.age = age;
        }

        @Override
        public int compareTo(Member o) {
            return this.id - o.id;
        }

        @Override
        public String toString() {
            return "Member{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    '}';
        }
    }

    @BeforeEach
    public void initiateList() {
        linkedList = new CircularDoublyLinkedList<>();
    }

    @Test
    public void addToLastTest() {
        Member member1 = new Member(1, "yhCho", 30);
        Member member2 = new Member(2, "jwCho", 25);

        linkedList.add(member1);
        linkedList.add(member2);

        Member foundMember1 = linkedList.get(member1);
        Member foundMember2 = linkedList.get(member2);

        assertEquals(2, linkedList.getSize());
        assertEquals(1, foundMember1.id);
        assertEquals(2, foundMember2.id);
        assertEquals("yhCho", foundMember1.name);
        assertEquals("jwCho", foundMember2.name);
        assertEquals(30, foundMember1.age);
        assertEquals(25, foundMember2.age);
    }

    @Test
    public void addToIndexTest() {
        Member member1 = new Member(1, "yhCho", 30);
        Member member2 = new Member(2, "jwCho", 25);
        Member member3 = new Member(3, "jyCho", 27);
        Member member4 = new Member(4, "jkCho", 23);
        Member member5 = new Member(5, "shasha", 7);
        Member member6 = new Member(6, "bami", 5);

        linkedList.add(member1);
        linkedList.add(member2);
        linkedList.add(member3);

        linkedList.add(0, member4); // 맨 처음에 삽입
        linkedList.add(2, member5); // 중간에 삽입
        linkedList.add(5, member6); // 마지막에 삽입

        Member foundMember4 = linkedList.get(member4);
        Member foundMember5 = linkedList.get(member5);
        Member foundMember6 = linkedList.get(member6);

        assertEquals(6, linkedList.getSize());

        assertEquals(0, linkedList.indexOf(member4));
        assertEquals(2, linkedList.indexOf(member5));
        assertEquals(5, linkedList.indexOf(member6));

        assertEquals(4, foundMember4.id);
        assertEquals(5, foundMember5.id);
        assertEquals(6, foundMember6.id);

        assertEquals("jkCho", foundMember4.name);
        assertEquals("shasha", foundMember5.name);
        assertEquals("bami", foundMember6.name);

        assertEquals(23, foundMember4.age);
        assertEquals(7, foundMember5.age);
        assertEquals(5, foundMember6.age);
    }

    @Test
    public void getByDataTest() {
        Member member1 = new Member(1, "yhCho", 30);

        linkedList.add(member1);

        Member foundMember1 = linkedList.get(member1);

        assertEquals(1, foundMember1.id);
        assertEquals("yhCho", foundMember1.name);
        assertEquals(30, foundMember1.age);
    }

    @Test
    public void getByIndexTest() {

        Member member1 = new Member(1, "yhCho", 30);
        Member member2 = new Member(2, "jwCho", 25);

        linkedList.add(member1);
        linkedList.add(member2);

        Member foundMember1 = linkedList.get(0);
        Member foundMember2 = linkedList.get(1);

        assertEquals(1, foundMember1.id);
        assertEquals(2, foundMember2.id);
        assertEquals("yhCho", foundMember1.name);
        assertEquals("jwCho", foundMember2.name);
        assertEquals(30, foundMember1.age);
        assertEquals(25, foundMember2.age);
    }

    @Test
    public void indexOfTest() {
        Member member1 = new Member(1, "yhCho", 30);
        Member member2 = new Member(2, "jwCho", 25);
        Member member3 = new Member(3, "jyCho", 27);

        linkedList.add(member1);
        linkedList.add(member2);
        linkedList.add(member3);

        assertEquals(0, linkedList.indexOf(member1));
        assertEquals(1, linkedList.indexOf(member2));
        assertEquals(2, linkedList.indexOf(member3));
    }

    @Test
    public void toArrayListTest() {
        Member member1 = new Member(1, "yhCho", 30);
        Member member2 = new Member(2, "jwCho", 25);
        Member member3 = new Member(3, "jyCho", 27);

        linkedList.add(member1);
        linkedList.add(member2);
        linkedList.add(member3);

        ArrayList<Member> members = linkedList.toArrayList();

        assertEquals(1, members.get(0).id);
        assertEquals(2, members.get(1).id);
        assertEquals(3, members.get(2).id);
    }

    @Test
    public void removeByDataTest() {
        Member member1 = new Member(1, "yhCho", 30);
        Member member2 = new Member(2, "jwCho", 25);
        Member member3 = new Member(3, "jyCho", 27);

        linkedList.add(member1);
        linkedList.add(member2);
        linkedList.add(member3);

        linkedList.remove(member1);
        linkedList.remove(member2);
        linkedList.remove(member3);

        assertEquals(0, linkedList.getSize());
        assertEquals(true, linkedList.isEmpty());
        assertThrows(DataNotFoundException.class, () -> linkedList.get(member1));
        assertThrows(DataNotFoundException.class, () -> linkedList.get(member2));
        assertThrows(DataNotFoundException.class, () -> linkedList.get(member3));
    }

    @Test
    public void removeByIndexTest() {
        Member member1 = new Member(1, "yhCho", 30);
        Member member2 = new Member(2, "jwCho", 25);
        Member member3 = new Member(3, "jyCho", 27);

        linkedList.add(member1);
        linkedList.add(member2);
        linkedList.add(member3);

        linkedList.remove(1);
        linkedList.remove(0);
        linkedList.remove(0);

        assertEquals(0, linkedList.getSize());
        assertEquals(true, linkedList.isEmpty());
        assertThrows(DataNotFoundException.class, () -> linkedList.get(member1));
        assertThrows(DataNotFoundException.class, () -> linkedList.get(member2));
        assertThrows(DataNotFoundException.class, () -> linkedList.get(member3));
    }

    @Test
    public void clearTest() {
        Member member1 = new Member(1, "yhCho", 30);
        Member member2 = new Member(2, "jwCho", 25);
        Member member3 = new Member(3, "jyCho", 27);

        linkedList.add(member1);
        linkedList.add(member2);
        linkedList.add(member3);

        linkedList.clear();

        assertEquals(0, linkedList.getSize());
        assertEquals(true, linkedList.isEmpty());
        assertThrows(DataNotFoundException.class, () -> linkedList.get(member1));
        assertThrows(DataNotFoundException.class, () -> linkedList.get(member2));
        assertThrows(DataNotFoundException.class, () -> linkedList.get(member3));
    }

    @Test
    // public void isEmpty()
    public void isEmptyTest() {
        assertEquals(linkedList.isEmpty(), true);
    }

    @Test
    public void dataNotFoundExceptionThrowTest() {
        Member member1 = new Member(1, "yhCho", 30);
        assertThrows(DataNotFoundException.class, () -> linkedList.get(member1));
        assertThrows(DataNotFoundException.class, () -> linkedList.indexOf(member1));
    }

    @Test
    public void invalidIndexExceptionThrowTest() {
        assertThrows(InvalidIndexException.class, () -> linkedList.get(999));
    }

    @Test
    public void emptyListExceptionThrowTest() {
        assertThrows(EmptyListException.class, () -> linkedList.toArrayList());
        assertThrows(EmptyListException.class, () -> linkedList.clear());
    }
}





























