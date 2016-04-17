package MergeAndHeap;

//Author: Koushik Balaji Venkatesan KXV141130

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MergeSort {
	
	private <T extends Comparable<? super T>> void DoMergeSort(List<T> a,int p,int r)
	{
		if(p<r)
		{
			int q=(p+r)/2;  //middle element
			DoMergeSort(a,p,q);    //recursive call on first half 
			DoMergeSort(a,q+1,r);  //recursive call on second half
			merge(a,p,q,r);
		}
	}
	
	
	public <T extends Comparable<? super T>> void merge(List<T> a,int p,int q,int r)
	{
		 List<T> left=new ArrayList<>();//left Sub Array
		 List<T> right=new ArrayList<>();  //right Sub Array
		 
		
		 for(int i=p;i<=q;i++)
			 left.add(a.get(i));
		 for(int i=q+1;i<=r;i++)
			 right.add(a.get(i));
		 
		//since we can't set infinity as sentinel values I have set null (handling the exception and merging the arrays will be faster. Yet To Implement.)
		left.add(null);
		right.add(null);
		
		int i=0;
		int j=0;
		
		for(int k=p;k<=r;k++)
		{
			if(left.get(i) == null)
			{
				a.set(k, right.get(j));
				j++;
			}
			if(right.get(j) == null)
			{
				a.set(k,left.get(i));
				i++;
			}
			if(left.get(i) != null && right.get(j) != null)
			{
				if(left.get(i).compareTo(right.get(j))<=0 )
					{
					a.set(k,left.get(i));
					i++;
					}
				else
				{
					a.set(k, right.get(j));
					j++;
				}
			}
		}

	}
	
	 static<T> void firstTen(List<T> A) 
	 {
	    int n = Math.min(A.size(), 10);
	    for(int i=0; i<n; i++) 
	    {
	    	System.out.print(A.get(i) + " ");
	    }
	    System.out.println();
	 }

	 
	public static void main(String ar[]) throws IOException
	{
		List<Integer> a=new ArrayList<Integer>();
		int n = Integer.parseInt(ar[0]);
		for(int i=n;i>0;i--)
			a.add(i);
		
		firstTen(a);
		long start=System.currentTimeMillis();
		new MergeSort().DoMergeSort(a,0,a.size()-1);
		System.out.println((System.currentTimeMillis()-start));
		firstTen(a);
	}
	
}
