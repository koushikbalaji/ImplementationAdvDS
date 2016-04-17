package longproject0;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

import Hashing.Timer;
public class EulerUtil {

	/**
	 * @param args
	 * @throws FileNotFoundException
	 */
	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(
				new File(
						"D:\\Spring 2016\\Implementation of Advanced Data Structures\\lp0\\lp0-big\\lp0-big.txt"));
		Graph g = Graph.readGraph(in, false);
		System.out.println("Read the stupid graph");
		int isEuleraian = checkEulerian(g);
		SimpleDoublyLinkedList<Edge> eulerTour = null;
		if (isEuleraian == 2 /* || isEuleraian == 1 */) {
			Timer timer = new Timer();
			eulerTour = findEulerTour(g);
			timer.end();
			System.out.println(timer);
		}else{
			System.out.println("Not a tour babes");
		}
		System.out.println("Number of edges is "  + eulerTour.size);
		System.out.println(verifyEuler(g, eulerTour, g.verts.get(3)));
	}

	public static int checkEulerian(Graph g) {

		int numberOfOddDegree = 0;

		for (Vertex v : g) {
			if (v.Adj.size() % 2 != 0) {
				numberOfOddDegree++;
			}
		}
		if (numberOfOddDegree == 2) {
			return 1;
		} else if (numberOfOddDegree == 0) {
			return 2;
		} else {
			return 0;
		}
	}

	public static SimpleDoublyLinkedList<Edge> findEulerTour(Graph g) {
		int count = 0;
		Vertex curr = g.verts.get(1);
		SimpleDoublyLinkedList<Edge> eulerTour = new SimpleDoublyLinkedList<>();
		SimpleDoublyLinkedList<Vertex> verticesWithUnusedEdgesInTour = new SimpleDoublyLinkedList<>();
		curr.indexInCurrTour = verticesWithUnusedEdgesInTour.add(curr);
		int numberOfEdgesProcessed=0;
		while (!verticesWithUnusedEdgesInTour.isEmpty()) {
			
			count++;
			curr = verticesWithUnusedEdgesInTour.getFirst();
			Vertex start = curr;
			Index<Edge> startOfNewTourIndex = start.indexOfEdgeInTour;

			SimpleDoublyLinkedList<Edge> localTour = new SimpleDoublyLinkedList<>();
			while (true) {
				Edge e = curr.unusedEdges.getFirst();
				if(curr.indexOfEdgeInTour==null){
					curr.indexOfEdgeInTour = localTour.add(e);
				}else{
					localTour.add(e);
				}
				++numberOfEdgesProcessed;
				Vertex u = e.From;
				Vertex v = e.To;
				if (curr == u) {
					curr = v;
				} else {
					curr = u;
				}
				
				u.unusedEdges.remove(e.indexInFromVertexList);
				v.unusedEdges.remove(e.indexInToVertexList);
				checkForDoneVertices(e.From, verticesWithUnusedEdgesInTour);
				checkForDoneVertices(e.To, verticesWithUnusedEdgesInTour);
				if (curr==start) {
					break;
				}
				
			}
			
			if (count > 1) {
				eulerTour.merge(startOfNewTourIndex, localTour);
			} else {
				eulerTour = localTour;
			}
		}
		return eulerTour;
	}

	public static boolean verifyEuler(Graph g, SimpleDoublyLinkedList<Edge> tour, Vertex start){
		Vertex temp = start;
		Node<Edge> e = temp.indexOfEdgeInTour.getIndex();
		int size = tour.size;
		//Add size check condition
		for(int i=0;i<size;i++){
			if(e.x.seen){
				return false;
			}
			Vertex u = e.x.From;
			Vertex v = e.x.To;
			e.x.seen=true;
			if(u==temp){
				temp = e.x.otherEnd(u);
			}else if(v==temp){
				temp = e.x.otherEnd(v);
			}else{
				return false;
			}
			if(e.next==tour.tail){
				e = tour.header.next;
			}else{
				e = e.next;
			}
		}
		if(temp==start){
			/*for(Vertex v : g){
				for(Edge f:v.Adj){
					if(!f.seen){
						return false;
					}
				}
			}
			*/
			return true;
		}
		else
			return false;
	}
	public static void checkForDoneVertices(Vertex u,
			SimpleDoublyLinkedList<Vertex> verticesWithUnusedEdges) {
		if (!u.unusedEdges.isEmpty() && u.indexInCurrTour == null) {
			u.indexInCurrTour = verticesWithUnusedEdges.add(u);
		} else if (u.unusedEdges.isEmpty() && u.indexInCurrTour != null) {
			verticesWithUnusedEdges.remove(u.indexInCurrTour);
		}
	}
}
