public class Stack<T> {

    private T t;
    private Node<T> bottom;
    private Node<T> top;
    private int size;

    public boolean push(T t) {
        Node<T> node = new Node<>(t);
        if(bottom == null) {
            bottom = node;
            top = node;
        } else {
            top.next = node;
            node.previous = top;
            top = node;
        }
        size++;
        return true;
    }

    public T pop() {
        Node<T> node = top;
        if(node != null) {
            if(node != bottom) {
                node.previous.next = null;
            }
            top = node.previous;
            size--;
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

    public int size() {
        return size;
    }

    public String toString() {
        Node<T> current = bottom;
        if (current == null)
            return "stack is empty";
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
