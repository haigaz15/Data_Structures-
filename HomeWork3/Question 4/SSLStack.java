// the class SSLSTack takes two nonempty stacks S and T and a doubly linked
//list Dmodify S to store all the
//original elements of both S and T
// and resides S on top of T and leaving T empty.
// the functionality starts at line 24.
//a)
public  class SSLStack<E> implements Stack<E> {
    SinglyLinkedList<E> list = new SinglyLinkedList<>();
    SSLStack(){
    }
    public int size(){
        return list.size();
    }
    public void push(E e){
        list.addFirst(e);
    }
    public E pop(){
        return list.removeFirst();
    }
}
class Test {
    public static void main (String[] args){
        mergedStack();
    }
    public static  void mergedStack() {
        SSLStack<Integer> s = new SSLStack<>();
        SSLStack<Integer> t = new SSLStack<>();
        DoublyLinkedList<Integer> dll = new DoublyLinkedList<>();
        // filling the s stack.
        s.push(1);
        s.push(2);
        s.push(3);
        s.push(4);
        // filling the t stack.
        t.push(5);
        t.push(6);
        t.push(7);
        t.push(8);
        while(s.size() != 0){
            Integer current = s.pop(); // removing each element from the stack s and inserting it in the doubly linked list
            dll.addLast(current); // using the add last instead of add first so that  during the refill in stack s
        }                         // it reverses the sequence.
        while(t.size() != 0){
            Integer current = t.pop(); // the same process here is done as the process of the stack s.
            dll.addLast(current);     // I added t into the doubly linked list after the s so that during the reverse s sits on
        }                             // top of t.
        while(dll.size() != 0) {
           s.push(dll.removeLast()); // after filling the array list with both of the stack s and t as follows {4,3,2,1,8,7,6,5}
        }                            // using the removeLast so that we can print the sequence stack t's elements than stack s's
        while(s.size() != 0)        // as there original order and s being on top of t.
            System.out.println(s.pop() + " ");
    }
}
/* b) The running time complexity of the function mergedStack is O(n)"linear" since each while loop. loops linear time. functions like s.push and
t.push or dll.addlast all run on aconstant time and according to big O rules we drop the lowest term thus making linear
*/