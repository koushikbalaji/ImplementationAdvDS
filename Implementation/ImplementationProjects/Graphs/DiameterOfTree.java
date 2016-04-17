package Graphs;

import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.Scanner;

public class DiameterOfTree {

	/**
	 * @author Satheesh Ganapathy
	 * Koushik Balaji Venkatesan
	 * Ajay Vembu
	 * Harigarakumar Velayudam
	 * @param t
	 * @return diameter of the graph t if t is a tree else -1 
	 * @variables
	 * startingVertex - Random vertex chosen from the graph
	 * maxDistanceVertex - Used to store the vertex farthest away from the source vertex after a BFS
	 * bfsQueue - queue used in BFS
	 * numberOfEdges - keeps count of the number of edges in the graph to check if its a tree
	 * 
	 */
	/**
	 * @author Satheesh Ganapathy
	 * @param t
	 * @return diameter of the graph t if t is a tree else -1 
	 * @variables
	 * startingVertex - Random vertex chosen from the graph
	 * maxDistanceVertex - Used to store the vertex farthest away from the source vertex after a BFS
	 * bfsQueue - queue used in BFS
	 * numberOfEdges - keeps count of the number of edges in the graph to check if its a tree
	 * 
	 */
	public static int diameter(Graph t) {
		Random rand = new Random();
		Vertex startingVertex = t.verts.get(rand.nextInt(t.verts.size()-1) + 1);
		Vertex maxDistanceVertex = startingVertex;
		Queue<Vertex> bfsQueue = new LinkedList<>();
		bfsQueue.add(startingVertex);
		int numberOfEdges = 0;
		/**
		 * vertex - the current vertex being seen in BFS
		 */
		while (!bfsQueue.isEmpty()) {
			Vertex vertex = bfsQueue.remove();
			if (vertex.distance > maxDistanceVertex.distance) {
				maxDistanceVertex = vertex;
			}
			vertex.seen = true;
			/**
			 * adjEdge - current adjacent edge of vertex being checked
			 */
			for (Edge adjEdge : vertex.Adj) {
				Vertex adjVertex = adjEdge.otherEnd(vertex);
				if (!adjVertex.seen){
					numberOfEdges++;
					adjVertex.parent=vertex;
					adjVertex.distance =  vertex.distance + adjEdge.Weight;
					bfsQueue.add(adjVertex);
				}else if(vertex.parent!=adjVertex){
					return -1;   // Cycle is present as we are able to reach an already seen vertex 
									//from a different vertex other than it's parent
				}
			}

		}
		
		if (numberOfEdges != t.numNodes - 1) {
			return -1;    // Not a tree as the number of edges is not equal to the required level to form a tree
		} else {
			for(Vertex vertex : t){
				vertex.distance = 0;
				vertex.seen = false;
			}
			bfsQueue.add(maxDistanceVertex);
			/**
			 * vertex - the current vertex being seen in BFS
			 */
			while (!bfsQueue.isEmpty()) {
				Vertex vertex = bfsQueue.remove();
				if (vertex.distance > maxDistanceVertex.distance) {
					maxDistanceVertex = vertex;
				}
				vertex.seen = true;
				/**
				 * adjEdge - current adjacent edge of vertex being checked
				 */
				for (Edge adjEdge : vertex.Adj) {
					Vertex adjVertex = adjEdge.otherEnd(vertex);
					if (!adjVertex.seen){
						numberOfEdges++;
						adjVertex.distance =  vertex.distance + adjEdge.Weight;
						bfsQueue.add(adjVertex);
					}
				}

			}
			
			return maxDistanceVertex.distance;
		}
	}
	
	
	public static void main(String ar[]) throws FileNotFoundException
	{
		
		Graph g = Graph.readGraph(
				new Scanner(System.in),
				false);
		System.out.println(diameter(g));
		
	}
	
}
