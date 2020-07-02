package Question4;

// this question 4 and 5
// it is an implementation of the positional list using arrays.
// the logic is as follows:
// i store the nodes inside the array indexes and for each node i have the exact index stored in it plus the element.
import java.util.NoSuchElementException;

public class ArrayPositionalList<Integer> implements PositionalList<Integer>, Iterable<Position<Integer>> {
    //−−−−−−−−−−−−−−−− nested Node class −−−−−−−−−−−−−−−−
    private static class Node<E> implements Position<E> {
        private E element;
        private int index;

        private Node(E e, int index) {
            element = e; // reference to the element stored at this node
            this.index = index;
        }

        public E getElement() throws IllegalStateException {
            return element;
        }
        // for storing the index of the array inside the node
        private int getIndex() {
            return index;
        }

        private void setElement(E e) {
            element = e;
        }
        // in order to able to update the index after the insertion of a new node since they are stored inside an array.
        private void setIndex(int i) {
            index = i;
        }
    } //−−−−−−−−−−− end of nested Node class −−−−−−−−−−−
    // question number 5's implementation of the iterator however there is a error as the after method gives an exception.
    private class ArrayReverseIterator implements Iterator<Position<Integer>> {
        ArrayPositionalList<Integer> copiedList = new ArrayPositionalList<>(15);
        Position<Integer> cursor = first();
        ArrayReverseIterator() {
            while(cursor != after(last())) {
                copiedList.addLast(cursor.getElement());
                cursor = after(cursor);
            }
        }
        Position<Integer> copiedCursor = copiedList.first();
        Position<Integer> copiedRecent = null;
        public boolean hasNext() {
            return (copiedCursor != copiedList.first());
        }
        public Position<Integer> next() throws NoSuchElementException {
            if (copiedCursor == after(copiedList.first())) throw new NoSuchElementException("nothing left in the collection");
            copiedRecent = copiedCursor;
            copiedCursor = after(copiedCursor);
            return copiedRecent;
        }
        public void remove() throws IllegalStateException {
            if (copiedRecent == null) throw new IllegalStateException("Nothing to remove");
            ArrayPositionalList.this.remove(copiedRecent);
            copiedRecent = null;
        }
    }

    private final int CAPACITY = 15; //initial capacity
    private Node[] data;
    private int size = 0;
    public ArrayPositionalList(int capacity){
        data = new Node[capacity];
    }
    private Node<Integer> validate(Position<Integer> p) throws IllegalArgumentException {
        if (!(p instanceof Node))
            throw   new IllegalArgumentException("Invalid p");
        Node<Integer> node = (Node<Integer>) p; // safe cast
        return node;
    }
    // the implementation is similar to linkedPositionalList
    public int size() {
        return size;
    }
    public boolean isEmpty() {
        return (size == 0);
    }
    public Position<Integer> first() {
        return data[0];
    } // returns the first position.
    public Position<Integer> last() {
        return data[size-1]; // returns the last position.
    }
    // turns the position into a node
    public Position<Integer> before(Position<Integer> p) throws IndexOutOfBoundsException {
        Node<Integer> node = validate(p);
        return data[node.getIndex() - 1];
    }
    /** Returns the Position immediately after Position p (or null, if p is last). */
    public Position<Integer> after(Position<Integer> p) throws IllegalArgumentException {
        Node<Integer> node = validate(p);
        return data[node.getIndex() + 1];
    }
    /** Adds element e to the linked list between the given nodes. */
    private Position<Integer> addBetween(Integer e, Node[] data, int prevIndex, int succIndex) {
        Node<Integer> newest = new Node<>(e, prevIndex + 1);
        // the for loop shits the position to the position one to the right
        for(int i = size - 1; i > newest.getIndex(); i--) {
            data[i + 1] = data[i]; // the shifiting
            data[i + 1].setIndex(data[i+1].getIndex() + 1); // this is update for the index inside the node each time i shift one to the right
        }
        data[prevIndex + 1] = newest;
        size++;
        return newest;
    }

    // public update methods
    /** Inserts element e at the front of the linked list and returns its new Position.*/
    public Position<Integer> addFirst(Integer e) {
        return addBetween(e,data, -1,1); // just after the header
    }
    /** Inserts element e at the back of the linked list and returns its new Position.*/
    public Position<Integer> addLast(Integer e) {
        return addBetween(e, data, size - 1, size); // just before the trailer
    }
    /** Inserts element e immediately before Position p, and returns its new Position.*/
    public Position<Integer> addBefore(Position<Integer> p, Integer e) throws IllegalArgumentException {
        Node<Integer> node = validate(p);
        return addBetween(e, data,node.getIndex() - 1, node.getIndex());
    }
    /** Inserts element e immediately after Position p, and returns its new Position.*/
    public Position<Integer> addAfter(Position<Integer> p, Integer e) throws IllegalArgumentException {
        Node<Integer> node = validate(p);
        return addBetween(e,data,node.getIndex(),node.getIndex()+1);
    }
    /** Replaces the element stored at Position p and returns the replaced element.*/
    public Integer set(Position<Integer> p, Integer e) throws IllegalArgumentException {
        Node<Integer> node = validate(p);
        Integer answer = node.getElement();
        node.setElement(e);
        return answer;
    }
    public Integer remove(Position<Integer> p) throws IllegalArgumentException {
        Node<Integer> node = validate(p);
        Integer answer = node.getElement();
        int index = node.getIndex();
        for(int i = node.getIndex(); i < size - 1 ; i++){
            data[i] = data[i + 1];
        }
        data[size - 1] = null;
        size--;
        return answer;
    }
    // show method just to print the elements.
    public void show (){
        for (int i = 0; i < size; i++) {
            System.out.println(data[i].getElement());
        }
    }
    public Iterator<Position<Integer>> iterator(){
        return new ArrayReverseIterator();
    }
}
class test{
    public static void main(String[] args){
        ArrayPositionalList<Integer> data = new ArrayPositionalList<>(15);
        data.addLast(1);
        data.addLast(2);
        data.addLast(3);
        data.addLast(4);
        data.addLast(5);
        data.show();
        System.out.println(data.remove(data.first()));
        Iterator<Position<Integer>> itr = data.iterator();
        System.out.println(itr.next().getElement());
    }
}