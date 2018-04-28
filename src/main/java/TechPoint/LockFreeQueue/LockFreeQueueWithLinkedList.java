package TechPoint.LockFreeQueue;

import java.util.concurrent.atomic.AtomicReference;

public class LockFreeQueueWithLinkedList {
    private Node tail;


    public void EnQueue(Node node)    {
        Node end = this.tail;
        Node oldEnd = this.tail;
        AtomicReference<Node> atomciRefNode = new AtomicReference<Node>(this.tail.next);

        do {
            while (end.next != null) {
                end = end.next;
                atomciRefNode.set(end.next);
            }
        } while( atomciRefNode.compareAndSet(null, node) != true);

        atomciRefNode.set(this.tail);
        atomciRefNode.compareAndSet(oldEnd, node);
    }

}
