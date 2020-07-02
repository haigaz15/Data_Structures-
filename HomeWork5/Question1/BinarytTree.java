package Question1;
interface BinaryTree<E> extends Tree<E> {

  int left(int p) throws IllegalArgumentException;

  int right(int p) throws IllegalArgumentException;

  int sibling(int p) throws IllegalArgumentException;
}