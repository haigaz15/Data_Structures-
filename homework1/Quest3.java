import java.util.Scanner;

public class Quest3 {
    public static int NumOfWays (int [] arr, int arrlength, int price) {
    	if(price == 0) {
    		return 1;
    	}
    	if(price < 0) {
    		return 0 ;
    	}
    	if(arrlength<= 0 && price>=1) {
    		return 0;
    	}
    	return NumOfWays(arr,arrlength-1,price) + NumOfWays(arr,arrlength,price - arr[arrlength-1]);
    }
    public static void main(String args[]) {
    	Scanner sc = new Scanner(System.in);
    	int[] arr = new int[3];
    	System.out.println("Enter your chocolate bar's prices :");
    	for(int i = 0 ; i< arr.length ; i++) {
    		arr[i] = sc.nextInt();
    	}
    	System.out.println("Enter the price");
    	int price = sc.nextInt();
    	int NumOfWays = NumOfWays(arr,arr.length,price);
    	System.out.println(NumOfWays);
    }
}
