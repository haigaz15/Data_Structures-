// The Class finds the peak element in a given array
public class IncreasingDecreasingArray {
    // the given array in the main method is sorted in an increasing Decreasing order
    // it was possible to implement an arraylist if the input size of the array was not given
    public static void main(String args[]){
        int[] arr = {1,2,3,4,5,7,8,4};
        determinePeak(arr,0,7);
    }
    // this function determines the peak element
    //the logic works as follows:
    //if the left element of the peak and the right element
    //are smaller than that specific searched element is the peak
    // it is implemented using binary search algorithm.
    private static void determinePeak(int[] arr,int low,int high) {
        int mid = (low + high) / 2;
        if (arr[mid - 1] < arr[mid] && arr[mid + 1] < arr[mid])
            System.out.println("The peak for the given array is: "+ arr[mid]) ;
        else {
            if (arr[mid - 1] > arr[mid]) {
                 determinePeak(arr, low, mid - 1);
            } else if (arr[mid + 1] > arr[mid]) {
                 determinePeak(arr, mid + 1, high);
            }
        }
    }
}
