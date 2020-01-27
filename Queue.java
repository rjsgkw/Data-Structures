public class Queue<T> {

    private T t;
    private Node<T> first;
    private Node<T> last;
    private int size;

    public boolean offer(T t) {
        Node<T> node = new Node<>(t);
        if(first == null) {
            first = node;
            last = node;
        } else {
            last.behind = node;
            node.inFront = last;
            last = node;
        }
        size++;
        return true;
    }

    public T poll() {
        Node<T> node = first;
        if(node != null) {
            if(first == last) {
                first = null;
                last = null;
            } else {
                first = node.behind;
                first.inFront = null;
            }
            size--;
            return node.t;
        }
        return null;
    }

    public T peek() {
        if(first != null)
            return first.t;
        return null;
    }

    public int size() {
        return size;
    }

    public String toString() {
        Node<T> current = first;
        if (current == null)
            return "queue is empty";
        StringBuilder sb = new StringBuilder();
        while (current != null) {
            sb.append(current.t + " ");
            current = current.behind;
        }
        return sb.toString();
    }

    private class Node<T> {

        private T t;
        private Node<T> behind;
        private Node<T> inFront;

        private Node(T t) {
            this.t = t;
        }
    }
}
