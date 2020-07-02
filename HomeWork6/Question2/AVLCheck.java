package Question2;

import java.util.Comparator;

public class AVLCheck  {
    public   LinkedBinaryTree<Entry<String, Integer>> T = new LinkedBinaryTree<>();
    AVLCheck(){
        Entry e1 = new AbstractMap.MapEntry<>(null,null);
        T.addRoot(e1);
    }
    public void expandExternal(Position<Entry<String,Integer>> p, Entry<String,Integer> e){
        T.set(p,e);
        Entry<String, Integer> e1 = new AbstractMap.MapEntry<>(null,null);
        Entry<String, Integer> e2 = new AbstractMap.MapEntry<>(null,null);
        T.addLeft(p,e1);
        T.addRight(p,e2);
    }
    private Position<Entry<String, Integer>> treeSearch(Position<Entry<String, Integer>> p, String key, Comparator<String> comp){
        if(T.isExternal(p))
            return p;
        if(comp.compare(key,p.getElement().getKey()) < 0)
            return treeSearch(T.left(p),key,comp);
        else if(comp.compare(key,p.getElement().getKey()) == 0)
            return p;
        else
            return treeSearch(T.right(p) , key,comp);
    }
    public Integer put(String key, Integer value, Comparator<String> comp) throws IllegalArgumentException{
        Position<Entry<String,Integer>> p = treeSearch(T.root(), key, comp);
        if(T.isExternal(p)){
            Entry<String,Integer> e = new AbstractMap.MapEntry<>(key,value);
            expandExternal(p,e);
            return null;
        }
        else{
            Integer answer = p.getElement().getValue();
            Entry<String,Integer> e = new AbstractMap.MapEntry<>(key,value);
            T.set(p,e);
            return answer;
        }
    }
/*
    public void inOrderTrav(){
        inOrderTravUtil(T.root());
    }
    public boolean inOrderTravUtil(Position<Entry<String, Integer>> p) {
        if (T.left(p) != null)
            inOrderTravUtil(T.left(p));
        //System.out.println(p.getElement().getValue());
        if (T.height(T.left(p)) - T.height(T.right(p)) >= 1)
            return false;
        if (T.right(p) != null) {
            inOrderTravUtil(T.right(p));
        }
        return true;
    }
*/
    public boolean isBalanced(Position<Entry<String,Integer>> p){
        if(p == null)
            return true;
        int left = T.height(T.left(p));
        int right = T.height(T.right(p));
        if(Math.abs(left - right) <= 1 && isBalanced(T.left(p))){
            return true;
        }
        else
            return false;
    }
    public boolean checkIfAvl(LinkedBinaryTree.Node<Entry<String,Integer>> node, Comparator<String> comp){
        put("ast",1,comp);
        put("ANV", 3,comp);
        put("BCV", 4,comp);
        put("okkm",6,comp);
        put("!", 9,comp);
        put(" ", 2,comp);
        return isBalanced(node);
    }
}
 class Test{
    public static void main(String[] args){
        AVLCheck t = new AVLCheck();
        Comparator<String> comp = new StringComparitor();
        //t.inOrderTrav();
        System.out.println(t.checkIfAvl(t.T.root, comp));
    }
}
