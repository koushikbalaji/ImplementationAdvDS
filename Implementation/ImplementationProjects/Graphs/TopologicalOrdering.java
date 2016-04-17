package Graphs;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

/*
 * Authors:
 * Koushik Balaji Venkatesan
 * Ajay Vembu
 * Harigarakumar Velayudam
 * Satheesh Ganapathy
 * SP2 A
Class to find the Topological ordering of a given DAG.
If given an undirected or cyclic graph the program outputs "the given graph is not a DAG"*/

public class TopologicalOrdering {

	//finds the topological ordering using a Queue
	public static List<Vertex> topologicalOrder1(Graph g) {
		
		//final topologically sorted list of vertices
		List<Vertex> topList = new ArrayList<>();
		
//		if (!g.directed)
//			return null;
		
		Iterator<Vertex> it = g.iterator();
		
		//Queue of Vertices with zero incoming degree
		Queue<Vertex> zeroDegreeVertices = new LinkedList<>();
		
		Vertex curr = null;

		//Adding vertices with zero incoming degree to Queue
		while (it.hasNext()) {
			curr = it.next();
			if (curr.incomingDegree == 0)
				zeroDegreeVertices.add(curr);
		}

		while (!zeroDegreeVertices.isEmpty()) {
			
			curr = zeroDegreeVertices.remove();
			topList.add(curr);
			List<Edge> adjList = curr.Adj;
			
			Iterator<Edge> neighbouringEdges = adjList.iterator();
			
			while (neighbouringEdges.hasNext()) {
				//Loop Invariant: If there are edges left to be processed from parent node (curr) the loop continues
				Edge temp = neighbouringEdges.next();
				Vertex neighbour = temp.To;
				//reducing the incoming degree of vertex considering the edge from parent node
				//after deletion if degree becomes zero it is added to queue
				neighbour.incomingDegree--;
				if (neighbour.incomingDegree == 0)
					zeroDegreeVertices.add(neighbour);
			}
		}

		//condition to check if graph has a cycle
		if (topList.size() != g.numNodes)
			return null;

		return topList;
	}
	
	
	//finds the topological ordering using DFS
	public static Stack<Vertex> topologicalOrder2(Graph g) {
		
		//Stack of vertices with leaf node added first and root at last
		Stack<Vertex> topStack = new Stack<>();
		
		if (!g.directed)
			return null;

		Iterator<Vertex> it = g.iterator();

		while (it.hasNext()) {
			Vertex u = it.next();
			if (!u.seen&&u!=null)
			{
				//calling DFS visit on unseen nodes in iterator order 
				if(DFSVisit(u, topStack)==-1)
					return null; //Returns null if graph has a cycle
			}
		}
			
		return topStack;
	}

	public static int DFSVisit(Vertex u, Stack<Vertex> topStack) {
		u.seen = true;
		u.processed=true;// attribute of a vertex to keep track of processed nodes to find cycle. Similar to Color in DFS

		List<Edge> adjList = u.Adj;

		Iterator<Edge> neighbouringEdges = adjList.iterator();
		
		while (neighbouringEdges.hasNext()) {
			//Loop Invariant: If there are edges left to be processed from parent node (curr) the loop continues
			Edge temp = neighbouringEdges.next();
			Vertex v = temp.To;
			if(v.processed)
				return -1;//if the graph has a cycle
			else
				v.processed=true;
			
			if (!v.seen) {
				v.parent = u;
				if(DFSVisit(v, topStack)==-1)//Recursive Call
					return -1;//cycle detected
			}
			
			v.processed=false;
			
		}
		u.processed=false;//setting parent node to false
		
		topStack.push(u);
		return 0;
	}

	public static void main(String ar[]) throws FileNotFoundException {
		Graph g = Graph.readGraph(
				new Scanner(System.in),
				true);
		 
		
		try {
			List<Vertex> topList = topologicalOrder1(g);
			for (Vertex v : topList)
				System.out.println(v);
		}
		catch (NullPointerException e) {
			System.out.println("Given graph is not a DAG");
		}
		System.out.println();
		try
		{
			Stack<Vertex> topStack = topologicalOrder2(g);
			while (!topStack.isEmpty())
				System.out.println(topStack.pop());
		} catch (NullPointerException e) {
			System.out.println("Given graph is not a DAG");
		}
	}
}
