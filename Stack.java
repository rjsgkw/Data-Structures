public class Stack<T> {

    private T t;
    private Node<T> bottom;
    private Node<T> top;

    public boolean push(T t) {
        Node<T> node = new Node<>(t);
        if(bottom == null) {
            bottom = node;
            top = node;
            return true;
        }

        top.next = node;
        node.previous = top;
        top = node;

        return true;
    }

    public T pop() {
        Node<T> node = top;
        if(node != null) {
            node.previous.next = null;
            top = node.previous;
            return node.t;
        }
        return t;
    }

    public T peek() {
        if(top != null) {
            return top.t;
        }
        return t;
    }

    public String toString() {
        Node<T> current = bottom;
        StringBuilder sb = new StringBuilder();
        while (current != null) {
            sb.append(current.t + " ");
            current = current.next;
        }
        return sb.toString();
    }

    private class Node<T> {

        private Node<T> next;
        private Node<T> previous;
        private T t;

        private Node(T t) {
            this.t = t;
        }

    }
}
