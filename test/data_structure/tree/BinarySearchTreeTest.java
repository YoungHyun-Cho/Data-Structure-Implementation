package data_structure.tree;

import data_structure.list.CircularDoublyLinkedListTest;
import data_structure.tree.exception.DataNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BinarySearchTreeTest {

    private static BinarySearchTree<Integer, Member> binarySearchTree;

    public static class Member implements Comparable<Member> {

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
                    ", age=" + age +
                    '}';
        }
    }

    @BeforeEach
    public void initiateTree() {
        binarySearchTree = new BinarySearchTree();
    }

    @Test
    public void addTest() {
        Member member1 = new Member(1, "yhCho",  30);
        Member member2 = new Member(2, "jwCho",  28);
        Member member3 = new Member(3, "jyCho",  26);
        Member member4 = new Member(4, "jkCho",  24);
        Member member5 = new Member(5, "shasha", 8 );
        Member member6 = new Member(6, "bami",   6 );

        binarySearchTree.add(member1.age, member1);
        binarySearchTree.add(member2.age, member2);
        binarySearchTree.add(member3.age, member3);
        binarySearchTree.add(member4.age, member4);
        binarySearchTree.add(member5.age, member5);
        binarySearchTree.add(member6.age, member6);

        assertEquals("yhCho" , binarySearchTree.get(member1.age).name);
        assertEquals("jwCho" , binarySearchTree.get(member2.age).name);
        assertEquals("jyCho" , binarySearchTree.get(member3.age).name);
        assertEquals("jkCho" , binarySearchTree.get(member4.age).name);
        assertEquals("shasha", binarySearchTree.get(member5.age).name);
        assertEquals("bami"  , binarySearchTree.get(member6.age).name);
    }

    @Test
    public void removeTest() {
        Member member1  = new Member(1 , "1111",  111);
        Member member2  = new Member(2 , "2222",  222);
        Member member3  = new Member(3 , "3333",  333);
        Member member4  = new Member(4 , "4444",  444);
        Member member5  = new Member(5 , "5555",  555);
        Member member6  = new Member(6 , "6666",  666);
        Member member7  = new Member(7 , "7777",  777);
        Member member8  = new Member(8 , "8888",  888);
        Member member9  = new Member(9 , "9999",  999);
        Member member10 = new Member(10, "1000",  100);
        Member member11 = new Member(11, "1001",  101);

        binarySearchTree.add(member6.id, member6);
        binarySearchTree.add(member1.id, member1);
        binarySearchTree.add(member2.id, member2);
        binarySearchTree.add(member3.id, member3);
        binarySearchTree.add(member4.id, member4);
        binarySearchTree.add(member5.id, member5);
        binarySearchTree.add(member7.id, member7);
        binarySearchTree.add(member8.id, member8);
        binarySearchTree.add(member9.id, member9);
        binarySearchTree.add(member10.id, member10);
        binarySearchTree.add(member11.id, member11);

        binarySearchTree.remove(member6.id);
        binarySearchTree.remove(member3.id);
        binarySearchTree.remove(member11.id);
        binarySearchTree.remove(member8.id);
        binarySearchTree.remove(member7.id);
        binarySearchTree.remove(member4.id);
        binarySearchTree.remove(member2.id);
        binarySearchTree.remove(member9.id);
        binarySearchTree.remove(member10.id);
        binarySearchTree.remove(member1.id);
        binarySearchTree.remove(member5.id);

        binarySearchTree.print();

        assertThrows(DataNotFoundException.class, () -> binarySearchTree.get(member1.id));
        assertThrows(DataNotFoundException.class, () -> binarySearchTree.get(member2.id));
        assertThrows(DataNotFoundException.class, () -> binarySearchTree.get(member3.id));
        assertThrows(DataNotFoundException.class, () -> binarySearchTree.get(member4.id));
        assertThrows(DataNotFoundException.class, () -> binarySearchTree.get(member5.id));
        assertThrows(DataNotFoundException.class, () -> binarySearchTree.get(member6.id));
        assertThrows(DataNotFoundException.class, () -> binarySearchTree.get(member7.id));
        assertThrows(DataNotFoundException.class, () -> binarySearchTree.get(member8.id));
        assertThrows(DataNotFoundException.class, () -> binarySearchTree.get(member9.id));
        assertThrows(DataNotFoundException.class, () -> binarySearchTree.get(member10.id));
        assertThrows(DataNotFoundException.class, () -> binarySearchTree.get(member11.id));
    }
}

















