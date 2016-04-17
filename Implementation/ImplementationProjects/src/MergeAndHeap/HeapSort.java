package MergeAndHeap;

//Author: Koushik Balaji Venkatesan KXV141130


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class HeapSort {

	private <T extends Comparable<? super T>> void DoHeapSort(List<T> a)
	{
		PriorityQueue<T> pq=new PriorityQueue<>(a);
		int i=0;
		while(!pq.isEmpty())
		{
			a.set(i,pq.remove());
			i++;
		}
	}
	
	private static<T> void firstTen(List<T> A) 
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
		n=123;
		for(int i=n;i>0;i--)
			a.add(i);
		
		firstTen(a);
		long start=System.currentTimeMillis();
		new HeapSort().DoHeapSort(a);
		System.out.println((System.currentTimeMillis()-start));
		firstTen(a);
	}
}
