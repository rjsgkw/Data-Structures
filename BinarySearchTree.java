/*
Does not allow duplicates. Will return false for duplicate item added.
However, a Map is created to keep track of duplicates.
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class BinarySearchTree<T extends Comparable<T>> {

    public Node<T> root;
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

    public boolean remove(T value) {
        Node<T> current = root;
        Node<T> parent = null;
        if(current == null)
            return false;
        while(current != null && !value.equals(current.value)) {
            if(value.compareTo(current.value) > 0) {
                parent = current;
                current = current.right;
            } else {
                parent = current;
                current = current.left;
            }
        }
        if(current != null) {
            if(parent == null) {
               root = null;
               return true;
            }
            if(current.right == null) {
                parent.left = current.left;
            } else if(current.right.left == null) {
                current.right.left = current.left;
                parent.left = current.right;
            } else {
                Node<T> leftNode = current.right.left;
                Node<T> parentOfLeftNode = current.right;
                while(leftNode.left != null) {
                    parentOfLeftNode = leftNode;
                    leftNode = leftNode.left;
                }
                leftNode.left = current.left;
                Node<T> temp = current.right;
                parent.left = leftNode;
                parentOfLeftNode.left = leftNode.right;
                leftNode.right = temp;
            }
            return true;
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

    public List<T> print() {
        return breadthFirstSearchLoop();
    }

    public List<T> print(Queue<Node<T>> queue, List<T> list) {
        return breadthFirstSearchRecursive(queue, list);
    }

    private List<T> breadthFirstSearchLoop() {
        List<T> list = new ArrayList<>();
        Node<T> currentNode = root;
        Queue<Node<T>> queue = new Queue<>();
        queue.offer(currentNode);

        while(queue.size() > 0) {
            currentNode = queue.poll();
            list.add(currentNode.value);
            if(currentNode.left != null)
                queue.offer(currentNode.left);
            if(currentNode.right != null)
                queue.offer(currentNode.right);
        }

        return list;
    }

    private List<T> breadthFirstSearchRecursive(Queue<Node<T>> queue, List<T> list) {
        if(queue.size() == 0)
            return list;
        Node<T> node = queue.poll();
        list.add(node.value);
        if(node.left != null)
            queue.offer(node.left);
        if (node.right != null)
            queue.offer(node.right);

        return breadthFirstSearchRecursive(queue, list);
    }


}
