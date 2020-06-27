// An ArrayDeque class implementing Deque interface using circular arrays.
public class ArrayDeque<E> implements Deque<E>{
    public E[] data;
    private int frontIndex = 0;
    private int size = 0;
    public ArrayDeque(int capacity) {
        data = (E[]) new Object[capacity];
    }
    public int size() {
        return size;
    }
    // returns a boolean value based on the condition.
    public boolean isEmpty() {
        return (size == 0);
    }
    // returns first element.
    public E first() {
        if(isEmpty())
            return null;
        return data[frontIndex];
    }
    // returns last element.
    public E last() {
        if(isEmpty())
            return null;
        return data[(frontIndex + size - 1) % data.length]; // frontIndex + size - 1 gives the last element and the % is used to wrap around the array.
    }
    // adds to the front of the deque.
    public void addFirst(E e) throws IllegalStateException {
        if(size == data.length) throw new IllegalStateException("The Deque is full");
        int avail = ((frontIndex - 1) + data.length) % data.length; // frontIndex - 1 gives the index before the frontIndex and (+data.length) is used to avoid negative numbers.
        data[avail] = e;
        size++;
    }
    // adds to after the end of the deque.
    public void addLast(E e) throws IllegalStateException {
        if(size == data.length) throw new IllegalStateException("The Deque is full");
        int avail = (frontIndex + size) % data.length;
        data[avail] = e;
        size++;
    }
    // remove the first element in the deque.
    public E removeFirst() {
        if(isEmpty())
            return null;
        E removedElement = data[frontIndex];
        data[frontIndex] = null;
        frontIndex = (frontIndex + 1) % data.length ; // advances the frontIndex by 1
        size--;
        return removedElement;
    }
    public E removeLast() {
        if(isEmpty())
            return null;
        E removedElement = data[((frontIndex + size) - 1) % data.length]; // since the removed element is the last element, the frontIndex + size - 1 gives the last element and the % is used to wrap around.
        data[((frontIndex + size) - 1) % data.length] = null;
        size--;
        return removedElement;
    }
}
class Test {
    public static void main(String[] args) {
        ArrayDeque<Integer> d = new ArrayDeque<>(50);
        d.addLast(1);
        d.addLast(2);
        d.addLast(3);
        d.addLast(4);
        d.addLast(5);
        d.addLast(6);
        d.addLast(7);
        System.out.println(d.removeLast());
    }
}
