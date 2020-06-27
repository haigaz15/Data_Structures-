// this program finds the number of the elements that are in order such as
// if i < j and arr[i] < arr[j].
import java.util.Arrays;
// the main method prints number of the elements that are in order
public class InOrder {
   // private static int count = 0;
    public static void main(String[] args){
        int[] arr = {2,8,6,3,1,4,5,9,0};
        System.out.println(mergeSort(arr,0));
    }
    // the merge sort algorithm is implemented
    // as sorting the array it counts the number of elements that are in order
    private static int mergeSort(int[] arr,int count){
        int n = arr.length;
        if(n < 2 )
            return 0;
        int mid = n/2;
        int[] firstPart = Arrays.copyOfRange(arr,0,mid);
        int[] secondPart = Arrays.copyOfRange(arr,mid,n);
        return mergeSort(firstPart,count) + mergeSort(secondPart,count) + merge(arr,firstPart,secondPart); //each of them returns count
    }                                                                                                      // their sum is the total sum of the counts
    private static int merge(int[] arr, int[] firstPart, int[] secondPart){                                // thus the total number of the in ordered elements
        int i = 0;
        int j = 0;
        int count = 0;
        while(i+j < arr.length){
            if(j == secondPart.length || i<firstPart.length && firstPart[i]<secondPart[j]) {
               arr[i + j] = firstPart[i++];
               count +=  (secondPart.length - j); // the idea here is that count is incremented each time by the
            }                                    // (secondPart.length - j) which represent the second division of the main
           else                                  // arr which is the merged array
                arr[i + j] = secondPart[j++];
        }
        return count;
    }
}