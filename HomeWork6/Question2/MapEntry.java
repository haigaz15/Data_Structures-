package Question2;

public  class MapEntry<K,V> implements Entry<K,V> {
    private K k;  // key
    private V v;  // value

    public MapEntry(K key, V value) {
        k = key;
        v = value;
    }

    // public methods of the Entry interface
    public K getKey() {
        return k;
    }

    public V getValue() {
        return v;
    }

    // utilities not exposed as part of the Entry interface
    protected void setKey(K key) {
        k = key;
    }

    protected V setValue(V value) {
        V old = v;
        v = value;
        return old;
    }
}