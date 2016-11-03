package SkipLists;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

// Skeleton for skip list implementation.

public class SkipListImpl<T extends Comparable<? super T>> implements SkipList<T> {
	public int size, maxLevel;
	Node<T> head, tail;
	class Node<T> {
		int element;
		List<Node<T>> next;

		Node(int value, int level) {
			element = value;
			next = new ArrayList<Node<T>>();			
		}
	}

	SkipListImpl() {
		maxLevel = (int) Math.log(1000);
		head = new Node<T>(0, maxLevel);
		tail = new Node<T>(Integer.MAX_VALUE, maxLevel);		
	}

	public List<Node<T>> find(T x) {
		Node<T> p = head;	
		List<Node<T>> prev = new ArrayList<Node<T>>();
		for (int i = maxLevel - 1; i >= 0; i--) {
			while (x.equals(p.next.get(i))) {
				p = p.next.get(i);
			}
			prev.add(i,p);
		}
		return prev;
	}

	public int choice(int maxLevel) {
		int levelCount = 0;
		boolean b = false;
		Random random = new Random();
		for (int i = maxLevel - 1; i >= 0; i--) {
			b = random.nextBoolean();
			if (b) {
				levelCount++;
			}
		}
		int j = (levelCount == 0) ? 1 : levelCount;
		return j;
	}

	@Override
	public boolean add(T x) {
		List<Node<T>> prev = find(x);		
		if (prev.get(0).next.get(0)== x) {
			prev.get(0).next.get(0).element=(int) x;
			return false;
		}
			else{			
		int lev = choice(maxLevel);
		Node<T> n = new Node<T>((int) x, lev);
		for (int i = 0; i < lev; i++) {
			n.next.add(i,prev.get(i).next.get(i));
			prev.get(i).next.add(i,n);
		}
		return true;
	}
	}

	@Override
	public T ceiling(T x) {
		return null;
	}

	@Override
	public boolean contains(T x) {
		return false;
	}

	@Override
	public T findIndex(int n) {
		return null;
	}

	@Override
	public T first() {
		return null;
	}

	@Override
	public T floor(T x) {
		return null;
	}

	@Override
	public boolean isEmpty() {
		return false;
	}

	@Override
	public Iterator<T> iterator() {
		return null;
	}

	@Override
	public T last() {
		return null;
	}

	@Override
	public void rebuild() {

	}

	@Override
	public T remove(T x) {
		return null;
	}

	@Override
	public int size() {
		return 0;
	}

}