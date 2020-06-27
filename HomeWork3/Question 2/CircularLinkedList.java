// The class is the Circular Linked List class in our text book.
// a small feature is add and that is it removes the last element.
// The name of the function is  removeLast() on line 76 ends at 96.
public class CircularLinkedList<E> {
    //---------------- nested Node class ----------------
    public static class Node<E> {
        private E element;
        private Node<E> next;
        public Node(E e, Node<E> n) {
            element = e;
            next = n;
        }
        public E getElement() {
            return element;
        }
        public Node<E> getNext() {
            return next;
        }
        public void setNext(Node<E> n) {
            next = n;
        }
    } //----------- end of nested Node class -----------
    private Node<E> tail = null;
    private int size = 0;
    public CircularLinkedList() {
    }
    public int size() {
        return size;
    }
    public boolean isEmpty() {
        return size == 0;
    }
    public E first() {
        if (isEmpty()) return null;
        return tail.getNext().getElement();
    }
    public E last() {
        if (isEmpty()) return null;
        return tail.getElement();
    }
    public void addFirst(E e) {
        if (size == 0) {
            tail = new Node<>(e, null);
            tail.setNext(tail);
        } else {
            Node<E> newest = new Node<E>(e, tail.getNext());
            tail.setNext(newest);
        }
        size++;
    }
    public void addLast(E e) {
        addFirst(e);
        tail = tail.getNext();
    }

    public E removeFirst() {
        if (isEmpty()) return null;
        Node<E> head = tail.getNext();
        if (head == tail)
            tail = null;
        else
            tail.setNext(head.getNext());
        size--;
        return head.getElement();
    }
    public E removeLast() {
        if (isEmpty())
            return null;
        else {
            Node<E> current = tail;  // to keep track of the node previous of the tail.
            Node<E> current2 = tail; // to reach tail during loop.
            Node<E> preLastNode; // to store the reference of the node before the tail.
            while (true) {
                current = current.getNext(); // traverses till the node before the tail.
                current2 = current2.getNext().getNext(); // traverses till it reaches the tail.
                if(current2 == tail)
                    break;
            }
                preLastNode = current;
                preLastNode.setNext(tail.getNext()); // changing the reference of the pretailnode to reference to the tail.getNext
                E element = tail.getElement();
                tail = tail.getNext(); // changing the reference of the tail to the next
                size--;
            return element;
        }
    }
    public  Node<E> getTail(){
        return tail;
    }
}
// a class for testing the removeLast function.
class Test {
    public static void main (String[] args) {
    CircularLinkedList<Integer> list = new CircularLinkedList<>();
    list.addLast(1);
    list.addLast(2);
    list.addLast(3);
    list.addLast(4);
    list.addLast(5);
    list.addLast(6);
    CircularLinkedList.Node<Integer> b = list.getTail();
/*    while(true) {
        System.out.print(b.getElement() + " ");
        b = b.getNext();
        if(b == list.getTail())
            break;
    }*/
    Integer a = list.removeLast();
    System.out.println(a);
    }
}