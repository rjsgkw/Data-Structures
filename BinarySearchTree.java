public class BinarySearchTree<T extends Comparable<T>> {

    private Node<T> root;

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

    private static class Node<T> {

        private T value;
        private Node<T> left;
        private Node<T> right;

        private Node(T value) {
            this.value = value;
        }
    }
}
