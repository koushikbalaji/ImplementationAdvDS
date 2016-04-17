package Hashing;

/** SP0Hashing Usage of HashSets and Running Time analysis between HashMap and sorting
 *  @author Satheesh Ganapathy
 *  		Koushik Balaji Venkatesan
 *  		Ajay Vembu
 *  		Harigara Kumar
 *  		G36		
 */
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;

public class SP0H {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] arr = new int[100000000];
		
		Random rand = new Random();
		
		for (int i = 0; i < arr.length; i++) {
			arr[i] = rand.nextInt(99);
		}
		
		System.out.println("Done Generating");
		System.out.println("Input size: " + arr.length);
		System.out.println("Input range: 0-99999");
		
		System.out.println();
		Timer timer = new Timer();
		int mostFrequent = mostFrequentHash(arr);
		System.out.println(mostFrequent);
		timer.end();
		
		System.out.println("Using hashing");
		System.out.println(timer);
		timer.start();
		
		System.out.println();
		mostFrequent = mostFrequentUsingSort(arr);
		System.out.println(mostFrequent);
		timer.end();

		System.out.println("Using array sorting");
		System.out.println(timer);
		

		
		/*
		 * for(int i=0;i<k;i++){ System.out.println(arr[i]); }
		 */
		
		System.out.println();
		System.out.println("Removing Duplicates");
		System.out.println("The number of Distinct Elements are "+findDistinct(arr));
	}

	//finding most frequent element using HashMap
	public static int mostFrequentHash(int[] arr) {
		HashMap<Integer, Integer> counter = new HashMap<>();
		int maxCount = 0;
		int element = 0;

		for (Integer a : arr) {
			Integer count = counter.get(a);
			if (count == null)
				counter.put(a, 1);
			else
				counter.put(a, count + 1);

			if (count != null && count >= maxCount) {
				element = a;
				maxCount = count;
			}
		}

		return element;
	}

	
	/*
	public static int mostFrequent(int[] arr) {
		
		HashMap<Integer, Integer> countMap = new HashMap<>();
		Integer maxInteger = null;
		int max = 0;
		
		
		for (int i = 0; i < arr.length; i++) {
			int localMax = 1;
			
			if (countMap.containsKey(arr[i])) {
				localMax = countMap.get(arr[i]) + 1;
				countMap.put(arr[i], localMax);
			} 
			else {
				countMap.put(arr[i], 1);
			}
			
			if (localMax > max) {
				max = localMax;
				maxInteger = arr[i];
			}
		}
		return maxInteger;
	}
	*/
	
	//finding most frequent element using Sorting and linear counter
	public static int mostFrequentUsingSort(int[] arr) {
		//Dual Pivot QuickSort
		Arrays.sort(arr);
		
		int mostFrequent = arr[0];
		int maxCount = 1;
		int currValue = arr[0];
		int currCount = 1;
		
		for (int i = 1; i < arr.length; i++) {
			
			if (currValue == arr[i]) {
				currCount++;
			} 
			else {
				currCount = 1;
				currValue = arr[i];
			}
			if (currCount > maxCount) {
				mostFrequent = currValue;
				maxCount = currCount;
			}
		}

		return mostFrequent;
	}

	//finding number of distinct elements and pushing them to first K positions
	public static int findDistinct(int[] arr) {
		
		HashSet<Integer> distincts = new HashSet<>();

		//adding elements and duplicates are removed automatically
		for (Integer a : arr)
			distincts.add(a);

		
		Iterator<Integer> it = distincts.iterator();

		//pushing distinct elements to first K positions
		for (int i = 0; i < distincts.size(); i++)
			arr[i] = it.next();

		return distincts.size();
	}
}
