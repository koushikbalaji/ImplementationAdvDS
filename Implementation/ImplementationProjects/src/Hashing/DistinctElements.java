package Hashing;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Timer;

public class DistinctElements {

	public static <T extends Comparable<? super T>> int findDistinct(T[] arr) {
		HashSet<T> distincts = new HashSet<>();

		for (T a : arr)
			distincts.add(a);

		Iterator<T> it = distincts.iterator();

		for (int i = 0; i < distincts.size(); i++)
			arr[i] = it.next();

		return distincts.size();
	}

	public static int mostFrequentNonHash(int[] arr) {

		Arrays.sort(arr);
		int currCounter = 0;
		int maxCounter = 0;
		int element = 0;

		for (int i = 0; i < arr.length - 1; i++) {
			if (arr[i] == arr[i + 1]) {
				currCounter++;

			} else {
				currCounter++;
				if (currCounter >= maxCounter) {
					maxCounter = currCounter;
					element = arr[i];
				}
				currCounter = 0;
			}
		}
		if (currCounter >= maxCounter) {
			maxCounter = currCounter;
			element = arr[arr.length - 1];
		}

		return element;
	}

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

	public static int mostFrequentUsingSort(int[] arr) {

		Arrays.sort(arr);

		int mostFrequent = arr[0];

		int maxCount = 1;

		int currValue = arr[0];

		int currCount = 1;

		for (int i = 1; i < arr.length; i++) {

			if (currValue == arr[i]) {
				currCount++;

			} else {

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

	public static int mostFrequent(int[] arr) {

		HashMap<Integer, Integer> countMap = new HashMap<>();

		Integer maxInteger = null;

		int max = 0;

		for (int i = 0; i < arr.length; i++) {

			int localMax = 1;

			if (countMap.containsKey(arr[i])) {

				localMax = countMap.get(arr[i]) + 1;

				countMap.put(arr[i], localMax);

			} else {

				countMap.put(arr[i], 1);

			}

			if (localMax > max) {

				max = localMax;

				maxInteger = arr[i];

			}

		}

		return maxInteger;

	}

	public static void main(String ar[]) {

		int[] hari = new int[100000000];
		for (int j = 0; j < hari.length; j++) {
			// hari[j]=5 + (int)(Math.random() * ((10 - 5) + 1));
			Random ran = new Random();
			int x = ran.nextInt(10000);
			hari[j] = x;

		}

//		findDistinct(hari);
		
		long count = System.currentTimeMillis();
		int Hash = mostFrequentNonHash(hari);
		System.out.println("Time for NonHash " + (System.currentTimeMillis() - count));
		System.out.println(Hash);
		
		count = System.currentTimeMillis();
		int nonHash = mostFrequentHash(hari);
		System.out.println("Time for Hash " + (System.currentTimeMillis() - count));
		System.out.println(nonHash);
		
		count = System.currentTimeMillis();
		int HashSathish = mostFrequent(hari);
		System.out.println("Time for Hash Sathish " + (System.currentTimeMillis() - count));
		System.out.println(HashSathish);
		
		count = System.currentTimeMillis();
		int nonHashSathish = mostFrequentUsingSort(hari);
		System.out.println("Time for NonHash Sathish " + (System.currentTimeMillis() - count));
		System.out.println(nonHashSathish);
	}
}
