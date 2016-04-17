package Graphs;

import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;


/*
 * Authors:
 * Koushik Balaji Venkatesan
 * Ajay Vembu
 * Harigarakumar Velayudam
 * Satheesh Ganapathy
 * SP2 C
class to find the strongly connected component of a directed graph
Uses Kosaraju's algorithm with a DFS run on G and another DFS run on G^T
*/
public class StronglyConnectedComponents {

	
	public static Stack<Vertex> DFS(Graph g) {
		
		//Stack of vertices with leaf node added first and root at last
		Stack<Vertex> topStack = new Stack<>();
		int counter=0;
		
		if (!g.directed)
			return null;

		Iterator<Vertex> it = g.iterator();

		while (it.hasNext()) {
			Vertex u = it.next();
			if (!u.seen&&u!=null)
				DFSVisit(u, topStack, true,counter);//calling DFS visit on every unseen node. True represents DFS on G (First DFS)
		}
			
		return topStack;
	}

	public static void DFSVisit(Vertex u, Stack<Vertex> topStack, boolean transpose,int counter) {
		
		u.seen = true;
		u.scc=counter;
		
		List<Edge> adjList;
		
		//transpose differentiates DFS calls for G and G^T
		if(transpose)
			adjList = u.Adj;
		else
			adjList = u.revAdj;//G^T
		
		Iterator<Edge> neighbouringEdges = adjList.iterator();
		
		while (neighbouringEdges.hasNext()) {
			
			//Loop Invariant: If there are edges left to be processed from parent node (curr) the loop continues
			Edge temp = neighbouringEdges.next();
			Vertex v;
			
			if(transpose)
				v = temp.To;
			else
				v=temp.From;//G^T
			
			if (!v.seen) {
				v.parent = u;
				DFSVisit(v, topStack,transpose,counter);//recursive call
				}
			}
		
			topStack.push(u);
			
	}
	
	 static int findStronglyConnectedComponents(Graph g,Stack<Vertex> topStack) 
	 {
		 int counter=0;
		 // New stack for G^T Second DFS visit
		 Stack<Vertex> newStack = new Stack<>();
		 
		 //resetting all vertices to false after previous DFS run
		 for(Vertex v:g.verts)
			  if(v!=null)
				  v.seen=false;
		 
		 //iterating nodes on Stack from first DFS order
		 while (!topStack.empty()) {
				Vertex u = topStack.pop();
				if (!u.seen&&u!=null)
				{
					DFSVisit(u, newStack,false,counter);
					counter++;//counter that finds the number of strongly connected components 
				}
			}
		 
		 return counter;
	 }
	 
	 public static void main(String ar[]) throws FileNotFoundException {
			Graph g = Graph.readGraph(
					new Scanner(System.in),
					true);
			try
			{
				Stack<Vertex> topStack = DFS(g);
				System.out.println(findStronglyConnectedComponents(g, topStack));
				
//				for(Vertex v:g.verts)
//					  if(v!=null)
//					 System.out.println(v.scc);
//				
			} catch (NullPointerException e) {
				System.out.println("Given graph is not a DAG");
			}
		}
}
