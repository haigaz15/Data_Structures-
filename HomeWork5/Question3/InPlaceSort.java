//the class implements inPlaceHeapSort Methods which sorts the entries based on their keys
// the function inPlaceHeapSort calls two additional utility functions:
// heapify which takes the sequnce of elements and makes it into a heap
// the downheap function just corrects the order of the heap that is also inside of our array

package Question3;
import java.util.Arrays;
import java.util.Comparator;

public class InPlaceSort {
    // the logic of heapify is similar to upHeap function's algorithm  however it makes it a heap by calling it for each single node of the binary tree
    // this is expressed in the function inPlaceHeapSort
    public static void heapify(int j, Entry<Integer, ?>[ ] data){
        Comparator<Integer> comp = new DefaultComparator<>();
        while(j>0){
            int parent = (j-1)/2;
            if(comp.compare(data[j].getKey(),data[parent].getKey()) < 0)
                break;
            else {
                Entry<Integer, ?> temp = data[j];
                data[j] = data[parent];
                data[parent] = temp;
                j = parent;
            }
        }
    }
    public static int parent(int j){
        return (j-1)/2;
    }
    public static int left(int j) {
        return 2 * j + 1;
    }
    public static int right(int j) {
        return 2 * j + 2;
    }
    // this function just downheaps the part of our array which represeant a heap but an maxHeap not a minHeap.
    public static void downHeap(int j, Entry<Integer, ?>[ ] data ){
        Comparator<Integer> comp = new DefaultComparator<>();
        while ((left(j)) < data.length) {
            int leftIndex = left(j);
            int bigChildIndex = leftIndex;
            if ((right(j)) < data.length) {
                int rightIndex = right(j);
                if (comp.compare(data[leftIndex].getKey(), data[rightIndex].getKey()) < 0) {
                    bigChildIndex = rightIndex;
                }
            }
            if (comp.compare(data[bigChildIndex].getKey(), data[j].getKey()) <= 0)
                break;
            Entry<Integer, ?> temp = data[j];
            data[j] = data[bigChildIndex];
            data[bigChildIndex] = temp;
            j = bigChildIndex;
        }
    }
    // the function sorts the array without additional data structure.
    public static void inPlaceHeapSort(Entry<Integer, ?>[ ] data ){
        for(int i = 1; i<data.length; i++){
            heapify(i,data);// calling heapify for each node of the complete binary tree so called heap
        }
        // downheaping the part of the array that represent a heap
        for(int i = 1,k = 0; i<data.length; i++, k++){
            Entry<Integer,?> temp = data[0]; //swaping the maxHeapelment with the last element and then calling downHeap again
            data[0] = data[data.length - i]; // so that we do not lose the functionality of the heap
            data[data.length - i] = temp;
            Entry<Integer, ?>[ ] data1 = Arrays.copyOfRange(data,0,data.length - i); // small trick to maintain the heap inside our array.
            downHeap(0,data1);
            System.arraycopy(data1,0,data,0, data1.length);
        }
    }
}
// small testing class.
class Test{
    public static void main (String[] args){
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
        InPlaceSort.inPlaceHeapSort(data);
        for (Entry<Integer, ?> datum : data) {
            System.out.print(datum.getKey() + " ");
        }
    }
}
