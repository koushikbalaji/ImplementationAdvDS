package longproject0;

import java.util.List;

public class SimpleDoublyLinkedList<T> {

	Node<T> header;
	Node<T> tail;
	int size;
	public SimpleDoublyLinkedList(){
		header = new Node<>(null);
		tail = new Node<>(null);
		header.next=tail;
		tail.prev = header;
	}
	
	public T getFirst(){
		return header.next.x;
	}
	
	private Node<T> getFirstNode(){
		return header.next;
	}
	
	private Node<T> getLastNode(){
		return tail.prev;
	}
	
	public Index add(T x){
		size++;
		Node<T> node = new Node(x);
		tail.prev.next= node;
		node.prev = tail.prev;
		tail.prev=node;
		node.next = tail;
		Index<Node<T>> index = new Index<>();
		index.setIndex(node);
		return index;	
	}
	
	public T remove(Index<T> index){
		size--;
		Node<T> node = index.getIndex();
		node.prev.next = node.next;
		node.next.prev = node.prev;
		return node.x;
	}
	
	public void merge(Index<T> index, SimpleDoublyLinkedList<T> toBeMerged){
		Node<T> node = index.getIndex();
		node.prev.next = toBeMerged.getFirstNode();
		toBeMerged.getFirstNode().prev = node.prev;
		node.prev = toBeMerged.getLastNode();
		toBeMerged.getLastNode().next = node;
		size += toBeMerged.size;
		
	}
	public void printlist(){
		Node<T> temp = header.next;
		int count =0;
		while(temp!=tail){
			System.out.println(temp.x);
			temp=temp.next;
			count++;
		}
		//System.out.println(count);
	}
	public boolean isEmpty(){
		if(header.next==tail)
			return true;
		else
			return false;
	}
}
