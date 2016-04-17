package MultiDimensionalSearch;

import java.util.HashMap;
import java.util.HashSet;
import java.util.TreeSet;

public class Check {

	public static void main(String ar[])
	{
		TreeSet<Long> check1=new TreeSet<>();
		TreeSet<Long> check2=new TreeSet<>();
		
		for(long i=0;i<10;i++)
		{
			check1.add(i);
			check2.add(i);
		}
		
		HashSet<TreeSet<Long>> check3=new HashSet<>();
		check3.add(check1);
		check3.add(check2);
		
		HashSet<Integer> check4=new HashSet<>();

		
		Integer a=new Integer(2);
		Integer b=new Integer(2);
		
		check4.add(a);
		check4.add(b);
		
		HashMap<Integer,Integer> check5=new HashMap<>();
		System.out.println(check5.put(1, 10));
		System.out.println(check5.put(1, 11));
		
		
		System.out.println(a.toString()+" "+b.toString()+" "+check4.size());
		
		
	}
	
}
