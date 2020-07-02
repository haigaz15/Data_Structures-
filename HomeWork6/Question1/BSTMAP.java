// Implementation of a Map using a binary Search tree
package Question1;
import java.util.ArrayList;
import java.util.Comparator;
public class BSTMAP<K,V> extends AbstractSortedMap<K,V> {
    private LinkedBinaryTree<Entry<K, V>> T = new LinkedBinaryTree<>();
    public BSTMAP(Comparator<K> c){
        super(c);
        Entry e1 = new MapEntry<>(null,null);
        T.addRoot(e1);
    }
    public BSTMAP(){
        super();
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
        if(compare(key,p.getElement().getKey()) < 0)
            return treeSearch(T.left(p),key);
            else if(compare(key,p.getElement().getKey()) == 0)
                return p;
        else
            return treeSearch(T.right(p) , key);
    }

    public V get(K key) throws IllegalArgumentException{
        checkKey(key);
        Position<Entry<K,V>> p = treeSearch(T.root(), key);
        if(T.isExternal(p))
            return null;
        return treeSearch(T.root(), key).getElement().getValue();
    }
    public V put(K key, V value) throws IllegalArgumentException{
        checkKey(key);
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
    private ArrayList<Position<Entry<K,V>>> inOrder(Position<Entry<K, V>> p, ArrayList<Position<Entry<K,V>>> snapshot){
        if(T.left(p) != null)
            return inOrder(T.left(p), snapshot);
        snapshot.add(p);
        if(T.right(p) != null)
            return inOrder(T.right(p), snapshot);
        return snapshot;
    }
    public V remove(K key){
        checkKey(key);
        Position<Entry<K,V>> p = treeSearch(T.root(), key);
        V answer = p.getElement().getValue();
        if(T.isExternal(T.left(p))){
            if(T.right(p) != null)
                T.set(T.parent(p), T.right(p).getElement());
            T.remove(p);
            T.remove(T.left(p));
            return answer;
        }else {
            ArrayList<Position<Entry<K,V>>> snapshot = inOrder(p, new ArrayList<>());
            Position<Entry<K,V>> p1 = treeSearch(snapshot.get(1),snapshot.get(1).getElement().getKey());
            //V answer1 = p1.getElement().getValue();
            Entry<K,V> temp = p.getElement();
            T.set(p,p1.getElement());
            T.set(p1,temp);
            if(T.right(p1) != null)
                T.set(T.parent(p1), T.right(p1).getElement());
            T.remove(p1);
            T.remove(T.left(p1));
        }
        return answer;
    }

    @Override
    public Iterable<Entry<K, V>> entrySet() {
        return null;
    }

    @Override
    public Entry<K, V> firstEntry() {
        return T.root().getElement();
    }

    @Override
    public Entry<K, V> lastEntry() {
        ArrayList<Position<Entry<K,V>>> snapshot =  inOrder(T.root(), new ArrayList<>());
        Position<Entry<K,V>> lastPosition = snapshot.get(snapshot.size() - 1);
        return lastPosition.getElement();
    }

    @Override
    public Entry<K, V> ceilingEntry(K key) throws IllegalArgumentException {
        Position<Entry<K,V>> p = treeSearch(T.root(),key);
        if(T.isInternal(p))
            return p.getElement();
        else if(T.isExternal(p) && compare(key, T.parent(p).getElement().getKey()) > 0) {
            while(T.parent(p) != null)
                p = T.parent(p);
            return p.getElement();
        }
        else
            return null;
    }

    @Override
    public Entry<K, V> floorEntry(K key) throws IllegalArgumentException {
        Position<Entry<K,V>> p = treeSearch(T.root(),key);
        if(T.isInternal(p))
            return p.getElement();
        else if(compare(key, T.parent(p).getElement().getKey()) < 0) {
           return T.parent(T.parent(T.parent(p))).getElement();
        }
        else
            return null;
    }

    @Override
    public Entry<K, V> lowerEntry(K key) throws IllegalArgumentException {
        Position<Entry<K,V>> p =  treeSearch(T.root(), key);

    }

    @Override
    public Entry<K, V> higherEntry(K key) throws IllegalArgumentException {
        Position<Entry<K,V>> p =  treeSearch(T.root(), key);
        ArrayList<Position<Entry<K,V>>> snapshot =  inOrder(p, new ArrayList<>());
        return snapshot.get(1).getElement();
    }

    @Override
    public Iterable<Entry<K, V>> subMap(K fromKey, K toKey) throws IllegalArgumentException {
        return null;
    }
}
