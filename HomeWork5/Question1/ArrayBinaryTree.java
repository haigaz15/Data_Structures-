package Question1;
public class ArrayBinaryTree<E> extends AbstractBinaryTree<E> {
  protected  E[] data;
  private int size = 0;
  //a- an array representations of a binary tree those are the two constructors
  ArrayBinaryTree(){
      data = (E[]) new Object[10];
    } // no arg with default capacity
    ArrayBinaryTree(int c){
    data = (E[]) new Object[c];
    } // given capacity
    //-a
  protected E root = null;     // root of the tree
  public int size() {
    return size;
  }
  public E root() {
    return root;
  }
  public int parent(int j)  {
    return (j-1)/2;
  }// numbering the positions parent , left and right child
  public int left(int j) {
    return 2*j + 1;
  }
  public int right(int j) {
    return 2*j + 2;
  }
  public E addRoot(E e) {
    data[0] = e;
    size = 1;
    return data[0];
  }
  //methods addRoot(e), addLeft(p; e), addRight(p; e), remove(p) similar to the
  //corresponding methods for the LinkedBinaryTree class, but rather with integer not Positions
  public int addLeft(int j, E e) {
    int parent = parent(j);
    if (data[left(j)] != null)
      throw new IllegalArgumentException("p already has a left child");
    E child = e;
    data[left(j)] = child;
    size++;
    return left(j);
  }
  public int addRight(int j, E e) {
    if (data[right(j)] != null)
      throw new IllegalArgumentException("p already has a right child");
    E child = e;
    data[right(j)] = e;
    size++;
    return right(j);
  }
  public E set(int j, E e) {
    E temp = data[j];
    data[j] = e;
    return temp;
  }
  public E remove(int j) {
    E temp = data[j];
    if (numChildren(j) == 2)
      throw new IllegalArgumentException("p has two children");
    E child = (data[left(j)] != null ? data[left(j)] : data[right(j)] );
    if (child != null)
      data[j] = data[parent(j)];  // child's grandparent becomes its parent
    if (data[j] == root)
      root = child;                       // child becomes root
    else {
      int parent = parent(j);
      if (j == left(parent))
        data[j] = child;
      else
        data[j] = child;
    }
    size --;
    return temp;
  }
}