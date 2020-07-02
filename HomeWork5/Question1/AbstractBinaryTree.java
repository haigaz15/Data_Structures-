
package Question1;

import java.util.List;
import java.util.ArrayList;

public abstract class AbstractBinaryTree<E> extends AbstractTree<E> implements BinaryTree<E> {
  public int sibling(int p) {
    ArrayBinaryTree<E> a = new ArrayBinaryTree<>();
    int parent = parent(p);
    if (a.data[parent] == null) return -1;                  // p must be the root
    if (p == left(parent))                            // p is a left child
      return right(p);                         // (right child might be null)
    else                                              // p is a right child
      return left(parent);                            // (left child might be null)
  }
  public int numChildren(int p) {
    int count=0;
    ArrayBinaryTree<E> a = new ArrayBinaryTree<>();
    if (a.data[left(p)] != null)
      count++;
    if (a.data[right(p)] != null)
      count++;
    return count;
  }

  public Iterable<Integer> children(int p) {
    List<Integer> snapshot = new ArrayList<>(2);;    // max capacity of 2
    ArrayBinaryTree<Integer> a = new ArrayBinaryTree<>();
    if (a.data[left(p)] != null)
      snapshot.add(a.data[left(p)]);
    if (a.data[right(p)] != null)
      snapshot.add(a.data[right(p)]);
    return snapshot;
  }
  private void inorderSubtree(int p, List<Integer> snapshot) {
    ArrayBinaryTree<Integer> a = new ArrayBinaryTree<>();
    if (a.data[left(p)] != null)
      inorderSubtree(left(p), snapshot);
    snapshot.add(a.data[p]);
    if (a.data[right(p)] != null)
      inorderSubtree(right(p), snapshot);
  }

  public Iterable<Integer> inorder() {
    List<Integer> snapshot = new ArrayList<>();
    if (!isEmpty())
      inorderSubtree(0, snapshot);   // fill the snapshot recursively
    return snapshot;
  }

  public Iterable<Integer> positions() {
    return inorder();
  }
}