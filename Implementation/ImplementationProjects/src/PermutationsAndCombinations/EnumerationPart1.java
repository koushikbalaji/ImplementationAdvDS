package PermutationsAndCombinations;

/*
 * Code Implemented as a group by
 * Srinath Iyengar smi140130 
 * Srikant Iyengar sxi140530
 */


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class EnumerationPart1 {
	
	static double count;
	static int n;
	static int r;
	static int verbose;
	
	//This method is used to display the permutations and to calculate the number of permutations for the given numbers.
	public static void VisitPerm(int A[])
	{
		if(verbose==2){								//display permutation if verbose is equal to 2
			for(int i = 0;i<=r;i++)
			if(A[i]>0)
				System.out.print(A[i]+" ");
			System.out.println();
		}
		count++;
	}
	
	//This method is used to display the combinations and to calculate the number of combinations for the given numbers.
	public static void VisitComb(int A[])
	{
		if(verbose==3){								//display combination if verbose is equal to 3
			for(int i = 0;i<=n;i++)
			if(A[i]>0)
				System.out.print(i+" ");
			System.out.println();
		}
		count++;
	}
	
	//Computes the permutation using 'Knuth's Algorithm L for lexicographic permutation generation'
	public static void Permute(int A[]){
		int j,k,l,temp;
		VisitPerm(A);
		for(k=r+1,l=n;k<l;k++,l--){					//reversing the elements from r+1 to n so that ascending 
			temp = A[k];							//order is changed to descending order(to avoid duplication) 
			A[k]=A[l];								
			A[l]=temp;
		}
		for(j=n-1;A[j]>=A[j+1];j--);				//Determine index j as per the algorithm.				
		if(j==0){									// Terminating condition for the method.
			return;
		}
		for(l=n;A[j]>=A[l];l--);					//Determine the index l as per the algorithm.
		
		temp=A[j];									//Swap the elements at the jth position and lth position.
		A[j]=A[l];
		A[l]=temp;
		
		for(k=j+1,l=n;k<l;k++,l--){					//Reordering the elements after the jth index.
			temp = A[k];				
			A[k]=A[l];
			A[l]=temp;
		}
		//Permute(A);								//Used iterative method to counter stack overflow problem
	}
	
	//Computes the combination
	public static void Comb(int A[], int i, int k){		//generate combinations by selecting k more objects from 1..i
		if (k==0)
			VisitComb(A);
		else if (i<k)
			return;
		else{
				A[i] = 1;
				Comb(A,i-1,k-1);						// combinations that include item i
				A[i]=0;
				Comb(A,i-1,k);							// combinations that do not include item i
			}												
	}
	
	//Main method
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int i,A[];
		double fact=1,factnr = 1,f = 1;
		
		// Required inputs
		System.out.println("Enter the total number of elements.");
		n = Integer.parseInt(br.readLine());
		A = new int [n+1];
		System.out.println("Enter r:");
		r = Integer.parseInt(br.readLine());
		System.out.println("Enter the operation to be performed.");
		verbose = Integer.parseInt(br.readLine());
		double starttime = System.currentTimeMillis();			//Start of timer
	if(verbose == 0 || verbose == 2){							//Deciding whether permutation or combination depending on user input													
			for(i=1;i<=n;i++){
				A[i] = i;
			}
			for(i=n;i>0;i--)									//Calculate n!			
				fact=fact*i;
			for(i=(n-r);i>0;i--)								//Calculate (n-r)!
				factnr=factnr*i;
			f = Math.round(fact/factnr);
			for(i=0;i<f;i++)						//Run loop and call call Permute nPr times.
				Permute(A);
		}
		else
			Comb(A,n,r);										//Initial Call for calculating combination						
		
		System.out.print(count);								//Display the number of permutations/combinations
		double endtime = System.currentTimeMillis();			//End timer
		System.out.println(" " + (endtime-starttime));			//Display the time taken to execute
		
	}
}