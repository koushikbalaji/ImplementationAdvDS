package PermutationsAndCombinations;

public class KnuthsLexicographicalOrdering {

	public static int n = 0;
	public static int count = 0;
	public static int i = 0;

	static void permuteByHeaps(int[] A, int n) {
		if (n == 1)
			visit(A);
		else {
			for (int j = 1; j <= n; j++) {
				permuteByHeaps(A, n - 1);
				if (n % 2 == 0)
					swap(A, j, n);
				else
					swap(A, 1, n);
			}
		}
	}

	static void swap(int[] A, int j, int i) {
		int c = A[j];
		A[j] = A[i];
		A[i] = c;
	}

	// permutation using 'Knuth's L Algorithm L for lexicographic ordering
	// RT o(n*no. of distinct permutations*visit time)
	public static void permuteKnuth(int a[]) {
		int j = n - 1;

		int k;
		visit(a);
		while (a[j] >= a[j + 1]) { // max value of j a[j]<a[j+1]
			j--;
		}
		if (j == 0) {
			return; // exit condition
		}
		int l = n;
		while (a[j] >= a[l]) { // max value of l a[j]<a[l]
			l--;
		}
		int temp = a[j]; // exchange a[j] and a[l]
		a[j] = a[l];
		a[l] = temp;

		for (k = j + 1, l = a.length - 1; k < l; k++, l--) { // reverse
																// a[j]..a[n]
			temp = a[k];
			a[k] = a[l];
			a[l] = temp;
		}
		permuteByHeaps(a, i); // recursive call
	}

	// print permutations and find count
	public static void visit(int a[]) {

		if (a.length < 5) {
			for (int i = 0; i <= n; i++) {
				if (a[i] > 0)
					System.out.print(a[i] + " ");
			}
			System.out.println();
		}
		count++; // Computes the Number of Permutations.
	}

	public static void main(String ar[]) {
		n = 10;
		int a[] = { 0, 1, 2, 2, 3, 4, 4, 5, 5, 6, 6 };
		i = 9;
		// a[0] sentinel value
		permuteKnuth(a);
		System.out.println(count);
	}
}
