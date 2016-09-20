package Trie;
/**
 * Authors: Koushik Balaji Venkatesan
 * Satheesh Ganapathy
 * Ajay Vembu
 * Harigarakumar Velayudham
 * 
 * Trie Java implementation
 * Assumptions: all words used has only alphabets and no digits or special characters
 * 
 */


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Trie {

	TrieEntry root = new TrieEntry();

	class TrieEntry {
		boolean inDictionary;
		int depth;
		int prefixCount;
		TrieEntry[] entries;

		public TrieEntry() {
			entries = new TrieEntry[26];
		}
	}

	void add(String word) {
		word.toLowerCase();

		if (contains(word))
			return;

		root.prefixCount++;

		TrieEntry temp = root;
		int pos = 0;

		for (int i = 0; i < word.length(); i++) {
			pos = (int) word.charAt(i) - 'a';

			if (temp.entries[pos] == null) {
				temp.entries[pos] = new TrieEntry();
				temp = temp.entries[pos];
				temp.depth = i + 1;
			} else {
				temp = temp.entries[pos];
			}

			temp.prefixCount++;

			if (i == word.length() - 1)
				temp.inDictionary = true;

		}

	}

	void remove(String word) {
		if (!contains(word))
			return;

		root.prefixCount--;

		TrieEntry temp = root;
		int pos = 0;

		for (int i = 0; i < word.length(); i++) {
			pos = (int) word.charAt(i) - 'a';

			if (temp.entries[pos].prefixCount == 1) {
				temp.entries[pos] = null;
				return;
			}

			temp = temp.entries[pos];
			temp.prefixCount--;

			if (i == word.length() - 1)
				temp.inDictionary = false;
		}

	}

	void importStrings(String file) throws IOException {

		// assuming file has only words separated by spaces and no special
		// characters or numbers
		FileReader fileObj = new FileReader(file);
		BufferedReader br = new BufferedReader(fileObj);
		String a;

		while ((a = br.readLine()) != null) {
			String[] temp = a.split(" ");
			for (String b : temp)
				add(b);
		}

		br.close();
	}

	Boolean contains(String word) {
		TrieEntry temp = root;
		int pos = 0;

		for (int i = 0; i < word.length(); i++) {
			pos = (int) word.charAt(i) - 'a';
			temp = temp.entries[pos];
			if (temp == null)
				return false;
		}
		return temp.inDictionary;

	}

	int prefix(String word) {

		TrieEntry temp = root;
		int pos = 0;

		for (int i = 0; i < word.length(); i++) {
			pos = (int) word.charAt(i) - 'a';
			temp = temp.entries[pos];
			if (temp == null)
				return -1;
		}

		return temp.prefixCount;
	}

	public static void main(String ar[]) throws IOException {

		Trie t = new Trie();
		t.add("word");
		t.add("wo");
		t.add("w");
		System.out.println(t.contains("word"));
		t.remove("wo");
		t.contains("wo");
		System.out.println(t.contains("wo") + " " + t.prefix("w") + t.root.prefixCount);
		t.importStrings("test.txt");
		System.out.println(t.contains("new"));
		System.out.println(t.prefix("n"));

	}
}
