package Question1;

public interface Position<E> {
  E getElement() throws IllegalStateException;
}