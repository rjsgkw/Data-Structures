public class LinkedList<T> {

    private Node<T> head;
    private Node<T> tail;
    private int size;

    public void append(T t) {
        Node<T> node = new Node<>(t);
        if(head == null) {
            head = node;
            tail = node;
            head.index = 0;
        } else {
            int index = tail.index;
            tail.next = node;
            node.previous = tail;
            tail = node;
            node.next = null;
            node.index = ++index;
        }
        size++;
    }

    public void prepend(T t) {
        Node<T> node = new Node<>(t);
        node.next = head;
        head = node;
        size++;
        Node<T> current = this.head;
        int i = 0;
        while(current != null) {
            current.index = i;
            i++;
            current = current.next;
        }
    }

    public String toString() {
        if(head == null) {
            return "list is empty";
        }
        System.out.println("[previous t next] ");
        StringBuilder list = new StringBuilder();
        Node<T> current = this.head;
        while(current != null) {
            if(current.previous == null) {
                list.append("[" + current.previous + " " + current.t + " " + current.next.index);
                current = current.next;
            } else if (current.next == null) {
                list.append("[" + current.previous.index + " " + current.t + " " + current.next + "]");
                current = current.next;
            } else {
                list.append("[" + current.previous.index + " " + current.t +" " + current.next.index + "]");
                current = current.next;
            }
        }
        return list.toString();
    }

    public int size() {
        return size;
    }

    public boolean insert(int index, T t) {
        if(index > size) {
            return false;
        }
        if(head == null || size == index) {
            append(t);
            return true;
        }
        Node<T> current = head;
        Node<T> previous = head;
        while(current.index != index) {
            previous = current;
            current = current.next;
            current.previous = previous;
        }
        Node<T> newNode = new Node<>(t);
        if(index == 0) {
            newNode.next = current;
            newNode.index = 0;
            head = newNode;
        } else {
            previous.next = newNode;
            newNode.next = current;
            newNode.index = index;
        }
        while(current != null) {
            current.index = ++index;
            current.previous = previous;
            current = current.next;
        }
        size++;
        return true;
    }

    public boolean remove(int index) {
        if(index >= size) {
            return false;
        }
        if(head == null) {
            return false;
        }
        Node<T> current = head;
        Node<T> previous = head;
        while(current.index != index) {
            previous = current;
            current = current.next;
        }
        if(index == 0) {
            current = current.next;
            head = current;
        } else {
            previous.next = current.next;
            current = current.next;
        }
        while(current != null) {
            current.index = current.index - 1;
            current = current.next;
        }
        size--;
        return true;
    }

    public LinkedList<T> reverse() {
        LinkedList<T> reversedList = new LinkedList<>();
        Node<T> current = tail;
        while(current != null) {
            reversedList.append(current.t);
            current = current.previous;
        }
        return reversedList;
    }

    private class Node<T> {

        private Node<T> next;
        private Node<T> previous;
        private T t;
        private int index;

        private Node(T t) {
            this.t = t;
        }
    }
}
