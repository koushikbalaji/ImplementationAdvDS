package ShortestPaths;

/*
 * LP3 Level 1
 * Authors
 * Koushik Balaji Venkatesan
 * Satheesh Ganapathy
 * Harigarakumar Velayudham
 * Ajay Vembu
 * Finds shortest path from source to all other vertices and uses one of the four algorithms (BFS,DAG,Dijkstra and 
 * Bellman-Ford) in accordance to the input graphs
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class ShortestPaths {

	
	//BFS used when all edges are of equal and non negative weights
	public void BFS(Graph g)
	{
		
		Vertex source=g.verts.get(1);
		initialize(g,source);
		Queue<Vertex>  BFSQueue=new LinkedList<>();
		BFSQueue.add(source);
		source.seen=true;
		
		while(!BFSQueue.isEmpty())
		{
			Vertex u=BFSQueue.remove();
			for(Edge e:u.Adj)
			{
				Vertex v=e.To;
				if(!v.seen)
				{
					v.distance=u.distance+1;
					v.parent=u;
					v.seen=true;
					BFSQueue.add(v);
				}
			}
			
		}
		
		
	}
	
	
	//DAG used when the Graph is Directed and Acyclic
	public void DAG(Graph g,List<Vertex> topOrder)
	{
		
		Vertex source=g.verts.get(1);
		initialize(g,source);
		Vertex u=null;
		
		for(Vertex v:topOrder)
			for(Edge e:v.Adj)
			{
				u=e.To; 
				relax(v,u,e);
		
			}
	}
	
	//finds topological order of a DAG
	public static List<Vertex> topologicalOrder1(Graph g) {
		
		//final topologically sorted list of vertices
		List<Vertex> topList = new ArrayList<>();
		
		if (!g.directed)
			return null;
		
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

	
	//initialize graph with all the fields, seen and parent initialized during vertex creation
	public void initialize(Graph g,Vertex s)
	{
	
		Iterator<Vertex> it=g.iterator();
		it.next();
		Vertex u=null;
		while(it.hasNext())
		{
			u=it.next();
			u.distance=Integer.MAX_VALUE;
		}
		s.distance=0;
	}
	
	
	//relax function used in all algorithms. Relaxes edges based on their weights
	public Boolean relax(Vertex u,Vertex v,Edge e)
	{
		int totalWeight = (u.distance==Integer.MAX_VALUE)? u.distance:(u.distance+e.Weight); 
		if(v.distance > totalWeight )
		{

			v.distance=u.distance+e.Weight;
			v.parent=u;
			return true;
		}
		return false;
	}
	
	
	//Dijkstra's Algorithm used on graphs without negative edge weights
	public void dijkstra(Graph g)
	{ 
		Vertex src = g.verts.get(1);
		Vertex[] vertexArray = new Vertex[g.numNodes+1];
		Comparator<Vertex> comp = new BinaryHeap.VertexMinComparator() ;
		int i = 1;
		for (Vertex u : g.verts){
    	if (u != null){
        	u.distance = Integer.MAX_VALUE;
        	u.index = i;
        	vertexArray[i++] = u;	
    		}
		}
		
		src.distance = 0;
    
		//indexedPriorityqueue Build Heap using Percolate down is faster because location of vertex in array is known
		IndexedHeap<Vertex> indexedPriorityqueue = new IndexedHeap<Vertex>(vertexArray,comp);
		 
		 while(!indexedPriorityqueue.isEmpty())
		 {
			 Vertex u=indexedPriorityqueue.remove();
			 u.seen=true;
			 
			 for(Edge e:u.Adj)
			 {
				 Vertex v=e.To;
					if(v.distance > u.distance+e.Weight&&!v.seen)
					{
						v.distance=u.distance+e.Weight;
						v.parent=u;
						
						//calls percolate up on the index of vertex
						indexedPriorityqueue.decreaseKey(v);
					}
			 }
			 
		 }
		
	}
	
	
	//Used when all other cases are failed. Returns false on graph with negative cycle
	public Boolean bellmanFord(Graph g)
	{
		
		Vertex s=g.verts.get(1);
		initialize(g,s);
		s.seen=true;
		
		Queue<Vertex>  BFQueue=new LinkedList<>();
		BFQueue.add(s);
		
		
		while(!BFQueue.isEmpty())
		{
			Vertex u=BFQueue.remove();
			u.seen=false;
			u.count++;
			
			if(u.count>=g.numNodes)
				return false;

			for(Edge e:u.Adj)
			{
				Vertex v=e.To;
				if(relax(u,v,e))
					if(!(v.seen))
					{
						BFQueue.add(v);
						v.seen=true;
					}
			}
		}
		
		return true;
		
	}
	
	//prints Algo Used, sum of distances from source, parent and distance from source for all vertices 
	public void printDistances(Graph g, String algoUsed)
	{
		Iterator<Vertex> it=g.iterator();
		Vertex v=null;
		long sum=0;
		while(it.hasNext())
		{
			v=it.next();
			if(v.distance!=Integer.MAX_VALUE)
				sum=sum+v.distance;
		}
		
		System.out.println(algoUsed+" "+sum);
		it=g.iterator();
		
		if(g.verts.size()<=100)
		while(it.hasNext())
		{
			v=it.next();
			System.out.println(v.name+" "+((v.distance!=Integer.MAX_VALUE)?v.distance:"INF")+" "+((v.parent!=null)?v.parent:"-"));
		}
		
	}
	
	//driver program for LP3 Level 1
	public static void main(String ar[]) throws FileNotFoundException
	{
		
		Graph g = Graph.readGraph(
				new Scanner(new File("test.txt")),
				true);
//		Graph g = Graph.readGraph(
//				new Scanner(System.in),
//				true);
		String algoUsed;
		
		List<Vertex> topOrder;
		
		//checks the type of graph given as input and calls the requisite algorithm accordingly
		if(g.equalWeights&&g.positiveWeithts)
		{
			new ShortestPaths().BFS(g);
			algoUsed="BFS";
		}
		else if((topOrder=topologicalOrder1(g))!=null)
		{
				new ShortestPaths().DAG(g,topOrder);
				algoUsed="DAG";
		}
		else if(g.positiveWeithts)
		{
			new ShortestPaths().dijkstra(g);
			algoUsed="Dij";
		}
		else if(new ShortestPaths().bellmanFord(g))
		{
			algoUsed="B-F";
		}
		else
		{
			System.out.println("Unable to solve problem. Graph has a negative cycle");
			return;
		}
		
		new ShortestPaths().printDistances(g,algoUsed);
	}
	
}
