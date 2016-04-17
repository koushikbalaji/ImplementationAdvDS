package PermutationsAndCombinations;

/*
 * Code Implemented as a group by
 * Srinath Iyengar smi140130 
 * Srikant Iyengar sxi140530
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;


public class EnumerationPart2 {

	public static int count;   		//count variable used to display the number of permutations
	public static int n=0; 			// The number of input elements
	public static int verbose=0; 

	
	//Computes the permutation using 'Knuth's Algorithm L for lexicographic permutation generation'
	public static void permute(int a[]){
		int j=n-1;
		
		int k;
		visit(a);
		while(a[j]>=a[j+1]){					//Determine index j as per the algorithm.
			j--;
		}
		if(j==0){								// Terminating condition of the recursive function.
			return ;
		}
		int l=n;
		while(a[j]>=a[l]){						//Determine the index l as per the algorithm.
			l--;
		}
		int temp=a[j];							//Swap the elements at the jth position and lth position.
		a[j]=a[l];
		a[l]=temp;
			
		for(k=j+1,l=a.length-1;k<l;k++,l--){	//Reordering the elements after the jth index.
			temp=a[k];
			a[k]=a[l];
			a[l]=temp;
		}
		permute(a);								//Recursive call to the function.
	}
	
	//This method is used to display the permutations and to calculate the number of permutations for the given numbers.
	public static void visit(int a[]){
		int i;
		if(verbose>0){							//Displays the permutations if user selects Verbose 1
		for(i=0;i<=n;i++){
			if(a[i]>0)
				System.out.print(a[i]+" ");
		}
		System.out.println();
		}	
		count++;								//Computes the Number of Permutations.
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
//		
//		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
//		System.out.println("Enter No of Objects and verbose ");
//		n=Integer.parseInt(br.readLine());
//		verbose=Integer.parseInt(br.readLine());
//		int i;
//		int a[] = new int[n+1];
//		//int temp[] = new int [n+1];
//		long starttime = System.currentTimeMillis();
//		System.out.println("Enter the integer values:");
//		for(i=1;i<=n;i++){
//			a[i]=Integer.parseInt(br.readLine());
//		}
		
//		int a[]=new int[4];
//		a={0,1,2,2};
//		//MergeSort(a,temp,0,n);							//To sort the input in the ascending Order.
//		Arrays.sort(a);
//        permute(a); 									//Initial call to the recursive function permute where the array a is now completely sorted.
//		System.out.print(count); 						//Display the final count or number of permutations.
//		//long endtime = System.currentTimeMillis();
		//System.out.println(" " + (endtime-starttime)); 	//Displays the time taken for the program to execute.
	}
}
