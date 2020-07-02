package Question1;
/* this class also includes functionality for traversing the positions of the tree in preorder, postorder, inorder
        and breadth-rst order traversals, i.e. preorder(), postorder(), inorder(),
        breadthfirst() methods, all of which return an iterable collection of the positions
        of the tree as an integer however inorder is implemented in abstract binary tree*/
import java.util.Iterator;
import java.util.List;         // for use as snapshot iterator
import java.util.ArrayList;    // for use as snapshot iterator

public abstract class AbstractTree<E> implements Tree<E> {
  public boolean isInternal(int j) { return numChildren(j) > 0; }
  public boolean isExternal(int j) { return numChildren(j) == 0; }
  public boolean isRoot(int p) {
    ArrayBinaryTree<E> a = new ArrayBinaryTree<>();
    return a.data[p] == root();
  }
  public int numChildren(int p) {
    int count=0;
    for (Integer child : children(p)) count++;
    return count;
  }
  public int size() {
    int count=0;
    for (Integer p : positions()) count++;
    return count;
  }
  public boolean isEmpty() { return size() == 0; }
  public int depth(int p) throws IllegalArgumentException {
    if (isRoot(p))
      return 0;
    else
      return 1 + depth(parent(p));
  }
  public int height(int p) throws IllegalArgumentException {
    int h = 0;                          // base case if p is external
    for (Integer c : children(p))
      h = Math.max(h, 1 + height(c));
    return h;
  }
  //---------- support for various iterations of a tree ---------
  /* This class adapts the iteration produced by positions() to return elements. */
  private class ElementIterator implements Iterator<E> {
    Iterator<Integer> posIterator = positions().iterator();
    public boolean hasNext() { return posIterator.hasNext(); }
    public E next() {
      ArrayBinaryTree<E> a = new ArrayBinaryTree<>();
      return a.data[posIterator.next()];
    } // return element!
    public void remove() { posIterator.remove(); }
  }
  public Iterator<E> iterator() {
    return new ElementIterator();
  }
  public Iterable<Integer> positions() { return preorder(); }
  private void preorderSubtree(int p, List<Integer> snapshot) {
    snapshot.add(p);                       // for preorder, we add position p before exploring subtrees
    for (Integer c : children(p))
      preorderSubtree(c, snapshot);
  }
  public Iterable<Integer> preorder() {
    List<Integer> snapshot = new ArrayList<>();
    if (!isEmpty())
      preorderSubtree(0, snapshot);   // fill the snapshot recursively
    return snapshot;
  }
  private void postorderSubtree(int p, List<Integer> snapshot) {
    for (Integer c : children(p))
      postorderSubtree(c, snapshot);
    snapshot.add(p);                       // for postorder, we add position p after exploring subtrees
  }
  public Iterable<Integer> postorder() {
    List<Integer> snapshot = new ArrayList<>();
    if (!isEmpty())
      postorderSubtree(0, snapshot);   // fill the snapshot recursively
    return snapshot;
  }
  public Iterable<Integer> breadthfirst() {
    List<Integer> snapshot = new ArrayList<>();
    if (!isEmpty()) {
      Queue<Integer> fringe = new LinkedQueue<>();
      fringe.enqueue(0);                 // start with the root
      while (!fringe.isEmpty()) {
        Integer p = fringe.dequeue();     // remove from front of the queue
        snapshot.add(p);                      // report this position
        for (Integer c : children(p))
          fringe.enqueue(c);                  // add children to back of queue
      }
    }
    return snapshot;
  }
}