package PermutationsAndCombinations;


public class Combination {
	int count = 0;
	void visit(boolean A[]){
		for (int i=1;i<A.length;i++)
			if (A[i])
				System.out.print(i);
		System.out.println();
	}
	
	void combination(boolean[] A,int n,int k){
		if (k > n)
			return;
		else if (k==0)
			visit(A);
		else{
			combination(A,n-1,k);
			A[n] = true;
			combination(A,n-1,k-1);
			A[n] = false;
		}
	}
	
	void permute (int[] A, int i){
		if (i == 0) 
			visit(A);
		else{
			for(int j=1;j<=i;j++){
				swap(A,j,i);
				permute(A,i-1);
				swap(A,j,i);
			}
		}
	}
	
	void permuteByHeaps (int[] A, int n){
		if (n == 1) 
			visit(A);
		else{
			for(int j=1;j<=n;j++){
				permuteByHeaps(A,n-1);
				if (n%2==0)
					swap(A,j,n);
				else
					swap(A,1,n);
			}
		}
	}

		
	void swap(int[] A,int j,int i){
		int c = A[j];
		A[j] = A[i];
		A[i] = c;
	}
	
	void visit(int[] A){
		for (int a : A)
			if (a != 0){count++;}
				//System.out.print(a+" ");
		//System.out.println();
	}
	public long factorial(long num) {
        if (num == 1) return 1;
        return num * factorial(num - 1);
	}
	
	public static void main(String[] args){
		//boolean A[] = new boolean[21];
		int A[] = new int[14];
		
		for(int i=0;i<A.length;i++)
			A[i] = i;
		Combination c = new Combination();
		//c.combination(A,20,10);
		//System.out.println();
		//System.out.println(c.count);
		long startTime = System.currentTimeMillis();
		c.permuteByHeaps (A,A.length-1);
        long endTime = System.currentTimeMillis();
		
		System.out.println("Time taken for Permutation is  : "+ (endTime - startTime) +" ms ");
		System.out.println("The total calls to visit method is "+c.count);
	}
}
