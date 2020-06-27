// Doubly linked List class for implementing it in the SSLStackClass.
public class DoublyLinkedList<E> {
    //---------------- nested Node class ----------------//
    public static class Node<E> {
        private E element;
        private Node<E> next;
        private Node<E> previous;
        public Node(E e, Node<E> p, Node<E> n) {
            element = e;
            previous = p;
            next = n;
        }
        public E getElement() {
            return element;
        }
        /*        public void setElement(E e){
                    element = e;
                }*/
        public Node<E> getNext() {
            return next;
        }
        public Node<E> getPrev() {
            return previous;
        }
        public void setNext(Node<E> n) {
            next = n;
        }
        public void setPrev(Node<E> p) {
            previous = p;
        }
    }
    // ------------------------------------- End of the Nested Class -----------------------------------------//
    private Node<E> header;
    private Node<E>  trailer;
    private int size = 0;
    public DoublyLinkedList() {
        header = new Node<>(null, null, null);
        trailer = new Node<>(null, header, null);
        header.setNext(trailer);
    }
    public int size() {
        return size;
    }
    public boolean isEmpty() {
        return size == 0;
    }
    public E first() {
        if (isEmpty()) return null;
        return header.getNext().getElement();
    }
    public E last() {
        if (isEmpty()) return null;
        return trailer.getPrev().getElement();
    }
    public void addFirst(E e) {
        addBetween(e, header, header.getNext());
    }
    public void addLast(E e) {
        addBetween(e, trailer.getPrev(), trailer);
    }
    public E removeFirst() {
        if (isEmpty())
            return null;
        return remove(header.getNext());
    }
    public E removeLast() {
        if (isEmpty()) {
            return null;
        }
        return remove(trailer.getPrev());
    }
    public void addBetween(E e, Node<E> predecessor, Node<E> successor) {
        Node<E> newest = new Node<>(e, predecessor, successor);
        predecessor.setNext(newest);
        successor.setPrev(newest);
        size++;
    }
    public E remove(Node<E> node) {
        Node<E> predecessor = node.getPrev();
        Node<E> successor = node.getNext();
        predecessor.setNext(successor);
        successor.setPrev(predecessor);
        size--;
        return node.getElement();
    }
    public Node<E> getHeader() {
        return header;
    }
    public Node<E> getTrailer() {
        return trailer;
    }
}