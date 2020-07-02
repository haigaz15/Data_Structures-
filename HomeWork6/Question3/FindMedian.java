package Question3;

import Question1.Entry;
import java.util.ArrayList;
public class FindMedian {
   private static ArrayList<Entry<Integer,String>> a = new ArrayList<>();
    public static <K,V> K findMedian(LinkedBinaryTree.Node<Question3.Entry<Integer,String>> root){
        if(root.getLeft() != null)
            return findMedian(root.getLeft());
        a.add((Entry<Integer, String>) root.getElement());
        if(root.getRight() != null)
            return findMedian(root.getRight());
        int eveMedian = (((a.size() + 1) / 2) + (a.size()/2)) / 2;
        int oddMedian = (a.size() + 1) / 2;
        Position<Entry<K,V>> p1 = (Position<Entry<K,V>>) a.get(eveMedian);
        Position<Entry<K,V>> p2 = (Position<Entry<K,V>>) a.get(oddMedian);
        if(a.size() % 2 == 0)
            return p1.getElement().getKey();
        else
            return p2.getElement().getKey();
    }
}
class Test{
   public static void main(String[] args){
       BST<Integer,String> t = new BST<>();
       t.put(2,"sd");
       t.put(3,"sd");
       t.put(7,"sd");
       t.put(9,"sd");
       t.put(0,"sd");
       t.put(2,"sd");
       FindMedian m = new FindMedian();
       m.findMedian(t.T.root);
       System.out.println();
   }

}
