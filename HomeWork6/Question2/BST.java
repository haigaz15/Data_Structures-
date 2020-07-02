package Question2;

import java.util.Comparator;
public class BST<K,V> {
    public LinkedBinaryTree<Entry<K, V>> T = new LinkedBinaryTree<>();
    Comparator<K> comp;
    public BST(Comparator<K> c){
       comp = c;
        Entry e1 = new MapEntry<>(null,null);
        T.addRoot(e1);
    }
    public BST(){
        comp = new DefaultComparator<>();
        Entry e1 = new MapEntry<>(null,null);
        T.addRoot(e1);
    }
    public int size(){
        return ((T.size() - 1)/2); // only to return the size of the internal nodes.
    }
    public void expandExternal(Position<Entry<K,V>> p, Entry<K,V> e){
        T.set(p,e);
       Entry<K, V> e1 = new MapEntry<>(null,null);
       Entry<K, V> e2 = new MapEntry<>(null,null);
        T.addLeft(p,e1);
        T.addRight(p,e2);
    }
    private Position<Entry<K, V>> treeSearch(Position<Entry<K, V>> p, K key){
        if(T.isExternal(p))
            return p;
        if(comp.compare(key,p.getElement().getKey()) < 0)
            return treeSearch(T.left(p),key);
            else if(comp.compare(key,p.getElement().getKey()) == 0)
                return p;
        else
            return treeSearch(T.right(p) , key);
    }

    public V get(K key) throws IllegalArgumentException{
        Position<Entry<K,V>> p = treeSearch(T.root(), key);
        if(T.isExternal(p))
            return null;
        return treeSearch(T.root(), key).getElement().getValue();
    }
    public V put(K key, V value) throws IllegalArgumentException{
        Position<Entry<K,V>> p = treeSearch(T.root(), key);
        if(T.isExternal(p)){
            Entry<K,V> e = new MapEntry<>(key,value);
            expandExternal(p,e);
            return null;
        }
        else{
            V answer = p.getElement().getValue();
            Entry<K,V> e = new MapEntry<>(key,value);
            T.set(p,e);
            return answer;
        }
    }
}
