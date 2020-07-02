package Question4;

public class PQEntry<K,V> implements Entry<K,V> {
    private K k;  // key
    private V v;  // value

    public PQEntry(K key, V value) {
        k = key;
        v = value;
    }

    // methods of the Entry interface
    public K getKey() { return k; }
    public V getValue() { return v; }

    // utilities not exposed as part of the Entry interface
    protected void setKey(K key) { k = key; }
    protected void setValue(V value) { v = value; }
}