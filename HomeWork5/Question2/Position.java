package Question2;

public interface Position<E> {
  E getElement() throws IllegalStateException;
}