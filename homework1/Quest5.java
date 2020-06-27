public class Quest5{
	public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int size = n*4-3
		String[][] matrix = new String[size][size];
		for(int c = 0; c<matrix.length; c++){
			for(int r = 0; r<matrix[c].length; r++){
				System.out.println(Rec(matrix,0,matrix.length-1)[c][r]);
			}
		}
	}

	public static String[][] Rec(String[][] matrix, int i, int j ){
	    if(i > matrix.length/2){
	    return matrix;
	    }else{
	    for(int c = i; c <= j ; j++){
            matrix[i][c] = "*";
	    }
	    for(int r = i+1; r<= j; r++){
	    	matrix[r][j] = "*";
	    }
	    for(int c = j - 1; c>=i; c--){
	    	matrix[j][c] = "*";
	    }
	    for(int r = j-1;r>i; r--){
	    	matrix[r][i] = "*";
	    }
	  }
	  return Rec(matrix,i+2,j-2);
	}
}