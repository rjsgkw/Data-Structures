public class BinarySearchTree {

    private Node root;

    public boolean insert(Integer value) {
        Node node = new Node(value);
        Node current = root;
        Node parent = root;
        boolean left = false;
        if(current == null) {
            root = node;
            return true;
        }
        while (current != null) {
            if (node.value == current.value) {
                return false;
            } else {
                if (node.value > current.value) {
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

    public boolean lookUp(Integer value) {
        Node current = root;
        if(current == null)
            return false;
        while (current != null) {
            if(value == current.value)
                return true;
            if(value > current.value)
                current = current.right;
            else
                current = current.left;
        }
        return false;
    }

    private static class Node {

        private int value;
        private Node left;
        private Node right;

        private Node(int value) {
            this.value = value;
        }
    }
}
