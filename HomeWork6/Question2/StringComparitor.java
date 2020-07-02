package Question2;

import java.util.Comparator;

public class StringComparitor implements Comparator<String> {
  public int compare(String a, String b) throws ClassCastException {
    if(a.charAt(0) > b.charAt(0))
      return 1;
    else if(a.charAt(0) == b.charAt(0))
      return 0;
    else
      return -1;
  }
}