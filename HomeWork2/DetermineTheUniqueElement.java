// this class finds a unique element in two arrays that have the k paired element and 1 unique element.
import java.util.Arrays;
public class DetermineTheUniqueElement {
    // in the main method meregeSort function is called to implement the program
    // it sorts the program in order to make the comparisons easier
    //also after finding the element it prints it
    public static void main(String[] args){
        String[] firstArray = {"b2","b1","b26","b4","b27"};
        String[] secondArray = {"n2","n26","n1","n4"};
        mergeSort(firstArray);
        mergeSort(secondArray);
        System.out.println("The unique element is: " + FindUniqueElement(firstArray,secondArray));
    }
    // this is the function that finds the unique element
    // the logic works as follows
    // each element in the arrays are compared
    //Integer.parseInt(firstArray[i].substring(1))!= Integer.parseInt(secondArray[i].substring(1)) takes
    // the integer part of the string elements compares them
    // if the both element are not equal to each other and
    //since the first array is the array that contains the unique element
    // the non equal element is defiantly the element that are in the first array
    private static String FindUniqueElement( String[] firstArray,String[] secondArray){
       for(int i = 0; i< secondArray.length; i++){
           if(Integer.parseInt(firstArray[i].substring(1))!= Integer.parseInt(secondArray[i].substring(1)))
               return firstArray[i];
       }
       return firstArray[firstArray.length-1]; // this is only used if the checking process is finished and the element is still
                                               // not found and since it loops on the smaller are size than the element is defiantly the last elemnt of the first array
    }
    // this is just the mergeSort function that sorts the arrays to facilitate the process
    private static void mergeSort(String[] data){
        int n = data.length;
        if(n<2)
            return;
        else{
            int mid = n/2;
            String[] part1 = Arrays.copyOfRange(data,0,mid);
            String[] part2 = Arrays.copyOfRange(data,mid,n);
            mergeSort(part1);
            mergeSort(part2);
            merge(part1,part2,data);
        }
    }
    private static void merge(String[] part1,String[] part2, String[] data){
        int i = 0, j = 0;
        while(i+j <data.length){
            if(j == part2.length || ( i < part1.length &&  Integer.parseInt(part1[i].substring(1)) < Integer.parseInt(part2[j].substring(1)))){
                data[i+j]  = part1[i++];
            }
            else{
                data[i+j] = part2[j++];
            }
        }
    }
}
