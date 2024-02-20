package data_structure.tree;

/*
* 이진 탐색 트리 구현 연습
* - 트리 : 노드와 가지로 데이터를 계층구조로 나타내는 자료 구조
* - 이진 트리 : 각 노드가 최대 둘의 자식 노드를 갖는 트리
*   - 완전 이진 트리 : 아래 조건을 만족하는 이진 트리
*       - 리프를 제외한 모든 레벨에서 자식은 둘임.
*         && 리프에서는 왼쪽부터 모든 노드가 채워져야 함.
*   - 이진 탐색 트리
*       - 루트가 왼쪽 서브트리보다 큰 값을 가지며, 오른쪽 서브트리보다는 작은 값을 가지는 이진 트리
* */

import data_structure.tree.exception.DataNotFoundException;
import data_structure.tree.exception.ObjectRedundancyException;

import java.util.Optional;

public class BinarySearchTree<K extends Comparable, V> {

    private Node<K,V> root;

    private static class Node<K,V> {
        private K key;
        private V value;
        private Node<K,V> left;
        private Node<K,V> right;

        public Node(K key, V value, Node<K, V> left, Node<K, V> right) {
            this.key = key;
            this.value = value;
            this.left = left;
            this.right = right;
        }

        private K getKey() {
            return key;
        }

        private V getValue() {
            return value;
        }

        public void print() {
            System.out.println(value);
        }
    }

    public BinarySearchTree() {
        this.root = null;
    }

    public V get(K key) {
        Node<K,V> node = root;

        while (true) {
            if (node == null) throw new DataNotFoundException();

            int result = key.compareTo(node.key);
            if (result < 0) node = node.left;
            else if (result > 0) node = node.right;
            else return node.getValue();
        }
    }

    public void add(K key, V value) {
        if (root == null) root = new Node<>(key, value, null, null);
        else insertNode(root, key, value);
    }

    private void insertNode(Node<K,V> node, K key, V value) {
        int result = key.compareTo(node.key);
        if (result < 0) {
            if (node.left == null) node.left = new Node<>(key, value, null, null);
            else insertNode(node.left, key, value);
        }
        else if (result > 0) {
            if (node.right == null) node.right = new Node<>(key, value, null, null);
            else insertNode(node.right, key, value);
        }
        else throw new ObjectRedundancyException();
    }

    public void remove(K key) {
        Node<K,V> node = root;
        Node<K,V> parent = null;
        boolean isLeftChild = true;

        // 삭제 대상 노드를 트리에서 찾음
        while (true) {
            if (node == null) throw new DataNotFoundException();

            int result = key.compareTo(node.key);
            if (result == 0) break;
            else {
                parent = node;
                if (result < 0) {
                    isLeftChild = true;
                    node = node.left;
                }
                else {
                    isLeftChild = false;
                    node = node.right;
                }
            }
        }

        if (node.left == null && node.right == null) {      // 자식 노드가 없는 경우
            if (isLeftChild) parent.left = null;
            else parent.right = null;
        }
        else if (node.left != null && node.right == null) { // 왼쪽 자식만 있는 경우
            if (isLeftChild) parent.left = node.left;
            else parent.right = node.left;
        }
        else if (node.left == null && node.right != null) { // 오른쪽 자식만 있는 경우
            if (isLeftChild) parent.left = node.right;
            else parent.right = node.right;
        }
        else {                                              // 자식을 둘 가진 경우
            Node<K,V> left = node.left;
            isLeftChild = true;
            while (left.right != null) {
                parent = left;
                left = left.right;
                isLeftChild = false;
            }

            node.key = left.key;
            node.value = left.value;

            if (isLeftChild) parent.left = left.left;
            else parent.right = left.left;
        }
    }

    private void printSubTree(Node<K, V> node) {
        if (node != null) {
            printSubTree(node.left);
            System.out.println(node.key + " : " + node.value + ", left : " + (node.left == null ? "NULL" : node.left.value) + ", right : " + (node.right == null ? "NULL" : node.right.value));
            printSubTree(node.right);
        }
    }

    public void print() {
        printSubTree(root);
    }
}
