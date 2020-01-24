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
        top = node;


        return false;
    }

    public T pop() {
        return t;
    }

    public T peek() {
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
        private T t;

        private Node(T t) {
            this.t = t;
        }

    }
}
