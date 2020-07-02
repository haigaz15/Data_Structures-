package Question1;
import java.util.ArrayList;
public class SieveOfEratosthenes {
    public static void main(String[] args){
        ArrayList<Integer> data = new ArrayList<>();
        int k = 2;
        int n = 120;
        do {
            data.add(k);
            k++;
        } while (k != n);
        removeComposite(data);
        for (Integer index : data) {
            System.out.println(index + " ");
        }
    }
    // the logic works as follows
    // marks the prime elements in variable p
    // then check whither the next element are prime or not
    // the check is by % thus if the number is the multiple of the p
    // than it gives zero and removes it from the list
    // therefore the remaining elements are the all the prime numbers.
    private static void removeComposite(ArrayList<Integer> data){
        for(int i = 0; i<data.size(); i++) {
            int p = data.get(i);
            int k = 2;
            for (int j = p; j < data.size(); j++) {
                if (data.get(j) % p == 0){
                    data.remove(j);
                }
             }
         }
     }
}
