package Question4;
import java.util.Comparator;
//  class  that includes a check function which checks whether the given array is a heap or not
public class Check {
    public static void main(String[] args) {
        PQEntry[] data = new PQEntry[13];
        data[0] = new PQEntry<Integer, Character>(4, 's');
        data[1] = new PQEntry<Integer, Character>(5, 'v');
        data[2] = new PQEntry<Integer, Character>(6, 'l');
        data[3] = new PQEntry<Integer, Character>(15, 'm');
        data[4] = new PQEntry<Integer, Character>(9, 'a');
        data[5] = new PQEntry<Integer, Character>(7, 'm');
        data[6] = new PQEntry<Integer, Character>(20, 'a');
        data[7] = new PQEntry<Integer, Character>(16, '6');
        data[8] = new PQEntry<Integer, Character>(25, 'a');
        data[9] = new PQEntry<Integer, Character>(14, 'n');
        data[10] = new PQEntry<Integer, Character>(12, 'x');
        data[11] = new PQEntry<Integer, Character>(11, 'q');
        data[12] = new PQEntry<Integer, Character>(13, 'o');
        System.out.println(checkHeap(data, new DefaultComparator<>()));
    }
    public static int left(int j) {
        return 2 * j + 1;
    }
    public static int right(int j) {
        return 2 * j + 2;
    }
    // this method checks if the given array of entries is an heap or not
    // the algorithm works similar to the downHeap
    // a minor change and the is it checks for every single entry
    // that is implemented in the part when i update the j to be j++ instead of j = smallChildIndex
    public static boolean checkHeap(PQEntry[] data, Comparator<Object> comp) {
        int count = 0;
        int j = 0;
        for (int i = 0; i < data.length; i++) {
            if (data[i] != null)
                count++;
        }
        if (count == data.length) {
            while ((left(j)) < data.length) {
                int leftIndex = left(j);
                int smallChildIndex = leftIndex;
                if ((right(j)) < data.length) {
                    int rightIndex = right(j);
                    if (comp.compare(data[leftIndex].getKey(), data[rightIndex].getKey()) > 0) {
                        smallChildIndex = rightIndex;
                    }
                }
                if (comp.compare(data[smallChildIndex].getKey(), data[j].getKey()) <= 0)
                    return false;
                j++;
            }
            return true;
        } else {
            return false;
        }
    }
}

