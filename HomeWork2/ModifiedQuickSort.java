// efficient quickSort class;
import java.util.Arrays;
import java.util.Random;
public class ModifiedQuickSort {
    public static void main(String[] args){
        int[] data = {4,5,6,9,4,2,3,4,8,6,332,1};
        quickSort(data);
        for(int i = 0; i<data.length -1; i++){
            System.out.print(" " + data[i]);
        }
    }
    // changing the quicksort into to a more efficient quicksort by randomizing the pivot elemnt
    public static void quickSort(int[ ] S) {
         int n = S.length;
         Random random = new Random(); // to generate a random number object
         if (n < 2) return;
         int lastELement = S[n-1];
         int randomPivot = random.nextInt(n-1); // random.nextInt() returns a number between (0,n-1)
         S[n-1] = S[randomPivot];
         S[randomPivot] = lastELement; // 11,12,13 is swamping the random pivot with the last pivot in order to proceed with the merge algorithm
         int pivot = S[n-1];
         int m = 0, k = n;
         int[ ] temp = new int[n];
         for (int i = 0; i < n - 1; i++)
             if (S[i] < pivot)
             temp[m++] = S[i];
         else if (S[i] > pivot)
             temp[--k] = S[i];
         int[ ] L = Arrays.copyOfRange(temp, 0, m);
         int[ ] E = new int[k - m];
         Arrays.fill(E, pivot);
         int[ ] G = Arrays.copyOfRange(temp, k, n);
         quickSort(L);
         quickSort(G);
         System.arraycopy(L, 0, S, 0, m);
         System.arraycopy(E, 0, S, m, k - m);
         System.arraycopy(G, 0, S, k, n - k);
         }
}
