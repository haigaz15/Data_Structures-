package Question2;

public interface Stack<E> {
    int size();
    void push(E e);
    E pop();
    boolean isEmpty();
}
