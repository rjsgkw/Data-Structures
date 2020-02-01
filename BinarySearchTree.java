/*
Does not allow duplicates. Will return false for duplicate item added.
However, a Map is created to keep track of duplicates.
 */

import java.util.HashMap;
import java.util.Map;

public class BinarySearchTree<T extends Comparable<T>> {

    private Node<T> root;
    private Map<T, Integer> duplicates = new HashMap<>();

    public boolean insert(T value) {
        Node<T> node = new Node<>(value);
        Node<T> current = root;
        Node<T> parent = root;
        boolean left = false;
        if(current == null) {
            root = node;
            return true;
        }
        while (current != null) {
            if (value.equals(current.value)) {
                duplicates.put(value, duplicates.get(value) != null ? duplicates.get(value) + 1 : 1);
                return false;
            } else {
                if (value.compareTo(current.value) > 0) {
                    parent = current;
                    current = current.right;
                    left = false;
                } else {
                    parent = current;
                    current = current.left;
                    left = true;
                }
            }
        }
        if(left)
            parent.left = node;
        else
            parent.right = node;
        return true;
    }

    public boolean lookUp(T value) {
        Node<T> current = root;
        if(current == null)
            return false;
        while (current != null) {
            if(value.equals(current.value))
                return true;
            if(value.compareTo(current.value) > 0)
                current = current.right;
            else
                current = current.left;
        }
        return false;
    }

    /*
    currently only removes leaf node.
     */
    public boolean remove(T value) {
        Node<T> current = root;
        Node<T> parent = root;
        boolean left = false;
        if(current == null)
            return false;
        while(current != null && !value.equals(current.value)) {
            if(value.compareTo(current.value) > 0) {
                parent = current;
                current = current.right;
                left = false;
            } else {
                parent = current;
                current = current.left;
                left = true;
            }
        }
        if(current != null) {
            if(current.left == null && current.right == null) {
                if(left) {
                    parent.left = null;
                    return true;
                } else {
                    parent.right = null;
                    return true;
                }
            }
        }
        return false;
    }

    public void printDuplicates() {
        System.out.println(duplicates);
    }

    private static class Node<T> {

        private T value;
        private Node<T> left;
        private Node<T> right;

        private Node(T value) {
            this.value = value;
        }
    }
}
