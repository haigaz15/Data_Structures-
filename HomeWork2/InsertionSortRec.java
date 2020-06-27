// the recursive version of the insertion sort
public class InsertionSortRec {
    public static void main (String[] args){
        int[] data = {4,5,6,9,2,3,1,5,7};
        insertionSort(data,1);
        for(int i = 0; i<data.length; i++){
            System.out.print(" " + data[i]);
        }
    }
    // the function takes an array and it sorts it as an insertion sort recursively
    public static void insertionSort(int[ ] data,int k) { // keeping the index k to call the function data.length times starting from k = 1
        int n = data.length;
        if (k == n)
            return;
        else {
            int cur = data[k]; //                // time to insert cur=data[k]
            int j = k;                           // find correct index j for cur
            while (j > 0 && data[j - 1] > cur) { // thus, data[j-1] must go after cur
                data[j] = data[j - 1];           // slide data[j-1] rightward
                j--;                             // and consider previous j for cur
            }
            data[j] = cur;
        }
        insertionSort(data, k+1);
    }
}
