package TechPoint.LockFreeQueue;

public class Node<T> {
    Node<T> next;
    T value;

    public Node(T value, Node<T> next) {
        this.next = next;
        this.value = value;
    }
}

