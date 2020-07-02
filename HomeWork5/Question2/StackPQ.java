// stack using Sorted priority queue.
package Question2;

public class StackPQ<V> implements Stack<V> {
    private int helper = 0; // an integer so that it can represent the key in the priority queue
    PriorityQueue<Integer, V> pQ = new SortedPriorityQueue<>();

    public void push(V value) {
        pQ.insert(--helper, value); // each time we insert our key is helper and decreases indicating that it is the latest element inserted
    } // we are decreasing so that when we want to remove from the stack that uses the priority queue as an implementation
    //  the minimal element which is alse the last element that went into the stack thus, we did not impede the last in first out principal.
    public V pop(){
        V value = pQ.removeMin().getValue();
        helper++;
        return value;
    }

    public int size() {
        return pQ.size();
    }
    public boolean isEmpty(){
        return pQ.isEmpty();
    }
}
// a small test class.
class Test{
    public static void main(String[] args){
        StackPQ<String> a1 = new StackPQ<>();
        a1.push("s");
        a1.push("4");
        a1.push("69");
        a1.push("what");
        a1.push("m");
        System.out.println(a1.pop());
        System.out.println(a1.pop());
        System.out.println(a1.pop());
        System.out.println(a1.pop());
        System.out.println(a1.pop());
        System.out.println(a1.size());
    }
}
