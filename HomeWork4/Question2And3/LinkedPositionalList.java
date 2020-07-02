// this class implements LinkedIntPositional<Integer>
// it answer the question number four
// also it extends the iterable interface which has an iterator method which returns iterator
// inside the Position Iterator class i implement insertion sort which sorts the element of the list
// however it sorts the copy of the original list and not the list it self
// small bug it prints null at the begging.
package Question2And3;

import java.util.NoSuchElementException;

/** Implementation of a positional list stored as a doubly linked list. */
class LinkedIntPositionalList<Integer> implements PositionalList<Integer>, Iterable<Position<Integer>> {
    //−−−−−−−−−−−−−−−− nested Node class −−−−−−−−−−−−−−−−
    private static class Node<Integer> implements Position<Integer> {
    private Integer element;
    private Node<Integer> prev;
    private Node<Integer> next;
    public Node(Integer e, Node<Integer> p, Node<Integer> n) {
        element = e; // reference to the element stored at this node
        prev = p; // reference to the previous node in the list
        next = n; // reference to the subsequent node in the list
    }
    public Integer getElement() throws IllegalStateException {
        if (next == null) // convention for defunct node
            throw new IllegalStateException("Position no longer valid");
        return element;
    }
    public Position<Integer> getPrev() { return prev; }
    public Position<Integer> getNext() { return next; }
    private Node<Integer> getPrevNode() { return prev; }
    private Node<Integer> getNextNode() { return next; }
    public void setElement(Integer e) { element = e; }
    public void setPrev(Node<Integer> p) { prev = p; }
    public void setNext(Node<Integer> n) { next = n; }
    } //−−−−−−−−−−− end of nested Node class −−−−−−−−−−−

    // --------------nested PositionIterator class------------------------//
    private class PositionIterator implements Iterator<Position<Integer>> {
        LinkedIntPositionalList<Integer> listCopy = new LinkedIntPositionalList<>();
        Position<Integer> cursor = first();
        // creating copy of the list
        public PositionIterator(){
            while(cursor != after(last())){ // the check is for after last since we are dealing with positions thus the trailer node is a position itself
                listCopy.addLast(cursor.getElement());
                cursor =  after(cursor);
            }
            // sorts the copy and returns it
            insertionSort(listCopy);
        }
        // iterate through the copy
        Position<Integer> copiedCursor = listCopy.last(); // this is the cursor for the copied list.
        Position<Integer> copiedRecent = null; // this is the recent of the copied list.
        public boolean hasNext() {
            return (copiedCursor != after(listCopy.last()));
        }
        public Position<Integer> next() throws NoSuchElementException {
            if(copiedCursor == after(listCopy.last())) throw new NoSuchElementException("nothing left in the collection");
            copiedRecent = copiedCursor;
            copiedCursor = after(copiedCursor);
            return copiedRecent;
        }
        public void remove() throws IllegalStateException{
            if(copiedRecent == null) throw new IllegalStateException("Nothing to remove");
            LinkedIntPositionalList.this.remove(copiedRecent);
            copiedRecent = null;
        }
        // the insertion sort
        public void insertionSort(LinkedIntPositionalList<Integer> listCopy){
            Position<Integer> marker = listCopy.first();
            while(marker!= listCopy.last()){
                Position<Integer> pivot = listCopy.after(marker);
                Integer value = pivot.getElement();
                if((int)value> (int)marker.getElement())
                    marker = pivot;
                else{
                    Position<Integer> walk = marker;
                    while(walk != listCopy.first() && (int)listCopy.before(walk).getElement() > (int)value)
                        walk = listCopy.before(walk);
                    listCopy.remove(pivot);
                    listCopy.addBefore(walk, value);
                }
            }
        }
    }
    //----------------- end of the nested PositionIterator Class ------------------//
    public Iterator<Position<Integer>> iterator( ) {
        return  new PositionIterator();
    }
    // instance variables of the LinkedPositionalList
    private Node<Integer> header; // header sentinel
    private Node<Integer> trailer; // trailer sentinel
    private int size = 0; // number of elements in the list
    /** Constructs a new empty list. */
    public LinkedIntPositionalList() {
        header = new Node<>(null, null, null); // create header
        trailer = new Node<>(null, header, null); // trailer is preceded by header
        header.setNext(trailer); // header is followed by trailer
    }
    /** Validates the position and returns it as a node. */
    private Node<Integer> validate(Position<Integer> p) throws IllegalArgumentException {
        if (!(p instanceof Node))
            throw new IllegalArgumentException("Invalid p");
        Node<Integer> node = (Node<Integer>) p; // safe cast
        if (node.getNext() == null) // convention for defunct node
            throw new IllegalArgumentException("p is no longer in the list");
        return node;
    }
    /** Returns the given node as a Position (or null, if it is a sentinel). */
    private Position<Integer> position(Node<Integer> node) {
        if (node == header || node == trailer)
            return null;
        return node;
    }

    // public accessor methods
    /** Returns the number of elements in the linked list. */
    public int size() { return size; }
    /** Tests whether the linked list is empty. */
    public boolean isEmpty() { return size == 0; }
    /** Returns the first Position in the linked list (or null, if empty). */
    public Position<Integer> first() { return header.getNext(); }
    /** Returns the last Position in the linked list (or null, if empty). */
    public Position<Integer> last() { return trailer.getPrev(); }
    /** Returns the Position immediately before Position p (or null, if p is first). */
    public Position<Integer> before(Position<Integer> p) throws IllegalArgumentException {
        Node<Integer> node = validate(p);
        return node.getPrev();
    }
    /** Returns the Position immediately after Position p (or null, if p is last). */
    public Position<Integer> after(Position<Integer> p) throws IllegalArgumentException {
        Node<Integer> node = validate(p);
        return node.getNext();
    }
    /** Adds element e to the linked list between the given nodes. */
    private Position<Integer> addBetween(Integer e, Node<Integer> pred, Node<Integer> succ) {
        Node<Integer> newest = new Node<>(e, pred, succ); // create and link a new node
        pred.setNext(newest);
        succ.setPrev(newest);
        size++;
        return newest;
    }

    // public update methods
    /** Inserts element e at the front of the linked list and returns its new Position. */
    public Position<Integer> addFirst(Integer e) {
        return addBetween(e, header, header.getNextNode()); // just after the header
    }
    /** Inserts element e at the back of the linked list and returns its new Position. */
    public Position<Integer> addLast(Integer e) {
        return addBetween(e, trailer.getPrevNode(), trailer); // just before the trailer
    }
    /** Inserts element e immediately before Position p, and returns its new Position. */
    public Position<Integer> addBefore(Position<Integer> p, Integer e) throws IllegalArgumentException {
        Node<Integer> node = validate(p);
        return addBetween(e, node.getPrevNode(), node);
    }
    /** Inserts element e immediately after Position p, and returns its new Position. */
    public Position<Integer> addAfter(Position<Integer> p, Integer e) throws IllegalArgumentException {
        Node<Integer> node = validate(p);
        return addBetween(e, node, node.getNextNode());
    }
    /** Replaces the element stored at Position p and returns the replaced element. */
    public Integer set(Position<Integer> p, Integer e) throws IllegalArgumentException {
        Node<Integer> node = validate(p);
        Integer answer = node.getElement();
        node.setElement(e);
        return answer;
    }
    /** Removes the element stored at Position p and returns it (invalidating p). */
    public Integer remove(Position<Integer> p) throws IllegalArgumentException {
        Node<Integer> node = validate(p);
        Node<Integer> predecessor = node.getPrevNode();
        Node<Integer> successor = node.getNextNode();
        predecessor.setNext(successor);
        successor.setPrev(predecessor);
        size--;
        Integer answer = node.getElement();
        node.setElement(null);
        node.setNext(null);
        node.setPrev(null);
        return answer;
    }
}
class Test{
    public static void main(String[] args){
        LinkedIntPositionalList<Integer> list = new LinkedIntPositionalList<>();
        list.addLast(1);
        list.addLast(0);
        list.addLast(3);
        list.addLast(-36);
        list.addLast(-1);
        Iterator<Position<Integer>> iter = list.iterator();
        while(iter.hasNext()){
            System.out.print(iter.next().getElement() + " ");
        }
    }
}
// The running time of my constructor is O(n^2) because of the insertion sort.
// The running time of the iterator class is also O(n^2) since we are implementing insertion sort and the loop that creates the copy makes it O(n) + O(n^2)
// which is O(n^2)