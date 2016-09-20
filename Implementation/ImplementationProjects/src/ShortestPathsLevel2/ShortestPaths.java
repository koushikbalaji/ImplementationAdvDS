package ShortestPathsLevel2;

/*
 * LP3 Level 2
 * Authors
 * Koushik Balaji Venkatesan
 * Satheesh Ganapathy
 * Harigarakumar Velayudham
 * Ajay Vembu
 * Find number of Shortest Paths from source to all vertices by shrinking the parent graph into an
 * acyclic graph by using the property u.distance+e.wiegtht=v.distance
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

	// BFS used when all edges are of equal and non negative weights
	public void BFS(Graph g) {

		Vertex source = g.verts.get(1);
		initialize(g, source);
		Queue<Vertex> BFSQueue = new LinkedList<>();
		BFSQueue.add(source);
		source.seen = true;

		while (!BFSQueue.isEmpty()) {
			Vertex u = BFSQueue.remove();
			for (Edge e : u.Adj) {
				Vertex v = e.To;
				if (!v.seen) {
					v.distance = u.distance + 1;
					v.parent = u;
					v.seen = true;
					BFSQueue.add(v);
				}
			}

		}

	}

	// DAG used when the Graph is Directed and Acyclic
	public void DAG(Graph g, List<Vertex> topOrder) {

		Vertex source = g.verts.get(1);
		initialize(g, source);
		Vertex u = null;

		for (Vertex v : topOrder)
			for (Edge e : v.Adj) {
				u = e.To;
				relax(v, u, e);

			}
	}

	// finds topological order of a DAG
	public static List<Vertex> topologicalOrder1(Graph g) {

		// final topologically sorted list of vertices
		List<Vertex> topList = new ArrayList<>();

		if (!g.directed)
			return null;

		Iterator<Vertex> it = g.iterator();

		// Queue of Vertices with zero incoming degree
		Queue<Vertex> zeroDegreeVertices = new LinkedList<>();

		Vertex curr = null;

		// Adding vertices with zero incoming degree to Queue
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

				// Loop Invariant: If there are edges left to be processed from
				// parent node (curr) the loop continues
				Edge temp = neighbouringEdges.next();

				Vertex neighbour = temp.To;
				// reducing the incoming degree of vertex considering the edge
				// from parent node
				// after deletion if degree becomes zero it is added to queue
				neighbour.incomingDegree--;
				if (neighbour.incomingDegree == 0)
					zeroDegreeVertices.add(neighbour);
			}
		}

		// condition to check if graph has a cycle
		if (topList.size() != g.numNodes)
			return null;

		return topList;
	}

	// initialize graph with all the fields, seen and parent initialized during
	// vertex creation
	public void initialize(Graph g, Vertex s) {

		Iterator<Vertex> it = g.iterator();
		it.next();
		Vertex u = null;
		while (it.hasNext()) {
			u = it.next();
			u.distance = Integer.MAX_VALUE;
		}
		s.distance = 0;
	}

	// relax function used in all algorithms. Relaxes edges based on their
	// weights
	public Boolean relax(Vertex u, Vertex v, Edge e) {
		int totalWeight = (u.distance == Integer.MAX_VALUE) ? u.distance : (u.distance + e.Weight);
		if (v.distance > totalWeight) {

			v.distance = u.distance + e.Weight;
			v.parent = u;
			return true;
		}
		return false;
	}

	// Dijkstra's Algorithm used on graphs without negative edge weights
	public void dijkstra(Graph g) {
		Vertex src = g.verts.get(1);
		Vertex[] vertexArray = new Vertex[g.numNodes + 1];
		
		Comparator<Vertex> comp = new BinaryHeap.VertexMinComparator();
		
		int i = 1;
		for (Vertex u : g.verts) {
			if (u != null) {
				u.distance = Integer.MAX_VALUE;
				u.index = i;
				vertexArray[i++] = u;
			}
		}

		src.distance = 0;

		// indexedPriorityqueue Build Heap using Percolate down is faster
		// because location of vertex in array is known
		IndexedHeap<Vertex> indexedPriorityqueue = new IndexedHeap<Vertex>(vertexArray, comp);

		while (!indexedPriorityqueue.isEmpty()) {
			Vertex u = indexedPriorityqueue.remove();
			u.seen = true;

			for (Edge e : u.Adj) {
				Vertex v = e.To;
				if (v.distance > u.distance + e.Weight && !v.seen) {
					v.distance = u.distance + e.Weight;
					v.parent = u;

					// calls percolate up on the index of vertex
					indexedPriorityqueue.decreaseKey(v);
				}
			}

		}

	}

	// Used when all other cases are failed. Returns false on graph with
	// negative cycle
	public Boolean bellmanFord(Graph g) {

		Vertex s = g.verts.get(1);
		initialize(g, s);
		s.seen = true;

		Queue<Vertex> BFQueue = new LinkedList<>();
		BFQueue.add(s);

		while (!BFQueue.isEmpty()) {
			Vertex u = BFQueue.remove();
			u.seen = false;
			u.count++;

			if (u.count >= g.numNodes) {
				System.out.println(u);
				Vertex temp = u;
				while (u.parent != temp) {
					System.out.print(u + " " + u.parent);
					System.out.println();
					u = u.parent;

				}
				//negative cycle detected
				return false;

			}
			for (Edge e : u.Adj) {
				Vertex v = e.To;
				if (relax(u, v, e))
					if (!(v.seen)) {
						BFQueue.add(v);
						v.seen = true;
					}
			}
		}

		return true;

	}

	
	//prints sum of all shortest distances and all shortest paths from source to all destinations
	public void printShortestPaths(Graph g) {
		
		
		Long sum=0l;
		
		for(int j=1;j<g.paths.length;j++)
			sum=sum+g.paths[j];
		
		System.out.println(sum);
		
		if(g.verts.size()<=100)
		for (int i=1;i<g.verts.size();i++)
		{
			if(g.verts.get(i).distance!=Integer.MAX_VALUE)
				System.out.println(i+" "+g.verts.get(i).distance+" "+g.paths[i]);
			else
				System.out.println(i+" "+"INF"+" "+0);
		}
		
	}

	public void findDGraph(Graph g) {
		Vertex u, v;

		for (Edge e : g.edges) {
			u = e.From;
			v = e.To;
			if (u.distance + e.Weight == v.distance)
				e.flag = true;
			else
				e.flag=false;
			
		}

	}


	//counts number of shortest paths between source and all vertices in D grpah
	public long countAll(Graph g, Vertex src, Vertex u)
	{
		if(u==src)
			return 1;
		else if(g.paths[u.name]!=0){
			return g.paths[u.name];
		}
		else{
			for(Edge e : u.revAdj){
				if(e.flag==true) //D graph obtained from findDGraph method
				g.paths[u.name] += countAll(g,src,e.From);
			}
			return g.paths[u.name];
		}
	}

	// to check if a cycle exist 
		List<Edge> checkIfCycleExists(Graph g){
			List<Edge> edgesInCycle = new ArrayList<Edge>();
			checkIfCycleExists(g.verts.get(1),g,edgesInCycle);
			return edgesInCycle;
		}

		
		/*
		 * to check if there is a cycle in a graph 
		 * runs in O(V+E)
		 */
		
		
	// to check if a cycle exist 
	Vertex checkIfCycleExists(Vertex u,Graph g,List<Edge> edgesInCycle){
		if (u.seen){
			// if the below condition is met then a cycle is found
			if (u.cycleTestInd)
				return u;
			else
				return null;
		}
		else{
			u.seen = true;
			// set the index to check for cycle
			u.cycleTestInd = true;
			Vertex cycledVertex = null;
			// to check for all the adjacent vertex of a corresponding vertex
			for (Edge e : u.Adj){
				if(e.flag==true)
				{
				Vertex v = e.otherEnd(u);
				cycledVertex = checkIfCycleExists(v,g,edgesInCycle);
				// print the cycle of vertices by back tracking
				if (cycledVertex!= null && cycledVertex.name != u.name){
					edgesInCycle.add(e);
					//System.out.println(e);
					break;
				}
				else{
					// to print the first vertex in the cycle
					if (cycledVertex!= null && cycledVertex.name == u.name){
						//System.out.println(e);
						edgesInCycle.add(e);
						g.foundCycle = true;
						cycledVertex =  null;
						break;
					}
					// break if a single cycle is found 
					if (g.foundCycle)
						break;
				}
				}
			}
			// reset the index
			
			u.cycleTestInd = false;
			return cycledVertex;
		}
	}

		
	//calls the count paths method from source to all vertices
	public void countShortestPaths(Graph g, Vertex src) {
		for (int i = g.verts.size() - 1; i >= 1; i--) {
			if (g.paths[g.verts.get(i).name] == 0)
				g.paths[g.verts.get(i).name] = countAll(g, src, g.verts.get(i));
		}
	}

	
	//driver program for LP3 level 2
	public static void main(String ar[]) throws FileNotFoundException {

		Graph g = Graph.readGraph(new Scanner(new File("no2.txt")), true);
		
		List<Vertex> topOrder=null;

		// checks the type of graph given as input and calls the requisite
		// algorithm accordingly
		if (g.equalWeights && g.positiveWeithts) {
			new ShortestPaths().BFS(g);
		} else if ((topOrder = topologicalOrder1(g)) != null) {
			new ShortestPaths().DAG(g, topOrder);
		} else if (g.positiveWeithts) {
			new ShortestPaths().dijkstra(g);
		} else if (new ShortestPaths().bellmanFord(g)) {
		} else {
			System.out.println("Non-positive cycle in graph. DAC is not applicable");
			List <Edge>cycleEdges=new ShortestPaths().checkIfCycleExists(g);
			for(Edge e:cycleEdges)
				System.out.println(e);
			
			return;
		}

		
		new ShortestPaths().findDGraph(g);
		
		List <Edge>cycleEdges=new ShortestPaths().checkIfCycleExists(g);
		
		if(!g.foundCycle)
		{
		new ShortestPaths().countShortestPaths(g, g.verts.get(1));
		new ShortestPaths().printShortestPaths(g);
		}
		else
		{
			System.out.println("Non-positive cycle in graph. DAC is not applicable");
			for(Edge e:cycleEdges)
				System.out.println(e);
		}
		
	}

}
