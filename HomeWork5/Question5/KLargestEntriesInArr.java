package Question5;

import java.util.Comparator;
// this class includes a function which returns the k largest entries in an array
public class KLargestEntriesInArr {
    public static void main(String[] args){
        Entry<Integer, ?>[ ] data = (Entry<Integer, ?>[]) new Entry[6];
        Entry<Integer,?> a1 = new PQEntry<>(2,null);
        Entry<Integer,?> a2 = new PQEntry<>(5,null);
        Entry<Integer,?> a3 = new PQEntry<>(6,null);
        Entry<Integer,?> a4 = new PQEntry<>(4,null);
        Entry<Integer,?> a5 = new PQEntry<>(7,null);
        Entry<Integer,?> a6 = new PQEntry<>(9,null);
        data[0] = a1;
        data[1] = a2;
        data[2] = a3;
        data[3] = a4;
        data[4] = a5;
        data[5] = a6;
        Entry<Integer, ?>[ ]  data1 = kLargestEntries(data,new DefaultComparator<>());
        for(int i = 0; i<data1.length - 1; i++)
            System.out.println(data1[i].getKey() + " ");
    }
    // this function returns the K larges entries based on their keys and a default comparator.
    //it calls an other utility method which sorts the entries based on maxHeap data Structure.
    public static <E> Entry<E,?>[] kLargestEntries(Entry<E,?>[] S, Comparator<E> comp) {
        return pqSort(S, new HeapPriorityQueue<>(comp), 3);
    }
    // this method sorts the given array using maxHeap then returns the kth largest entries as an array.
    public static <E> Entry<E,?>[] pqSort(Entry<E,?>[] S, HeapPriorityQueue<E,?> P, int k) {
        int n = S.length;
        Entry<E,?>[] S1 = (Entry<E,?>[]) new Entry[6];
        for (int j=0; j < n; j++) {
             Entry<E,?> element = S[j];
             P.insert(element.getKey(), null); // element is key; null value
        }
        for (int j=0; j < n; j++) {
            Entry<E,?> element = P.removeMax( );
                S[j] = element;
        }
        for(int i = 0; i<k ; i++)
            S1[i] = S[i];
        return S1;
    }
}
