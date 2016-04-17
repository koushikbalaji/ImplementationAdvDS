package EulerianTrails;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;


public class FindEulerianTrail {
	
	static List<Edge> findEulerTour(Graph g)  // Return an Euler tour of g
	{
		Iterator<Vertex> it = g.iterator();
		int oddDegreeCounter=0;
		Vertex source;
		int min=Integer.MAX_VALUE;
		while(it.hasNext())
		{
			Vertex u=it.next();
			if(!((u.degree)%2==0))
			{
				oddDegreeCounter++;
				if(u.name<min)
				{
					source=u;
					min=u.name;
				}
			}
		}
		if(oddDegreeCounter!=0||oddDegreeCounter!=2)
			return null;
		
		
		return null;
	}
	static boolean verifyTour(Graph g, List<Edge> tour, Vertex start)  // verify tour is a valid Euler tour
	{
		if(g.numEdges!=tour.size())
		{
			System.out.println("All edges are not traversed");
			return false;
		}
		
		if(!(tour.get(0).From==start||tour.get(0).To==start))
			return false;

		if(!(tour.get(tour.size()-1).From==start||tour.get(tour.size()-1).To==start))
			return false;
		
		Vertex temp=start;
		Vertex curr;
		Edge edge;
		for(int i=0;i<tour.size()-1;i++)
		{
			edge=tour.get(i);
			edge.count++;
			if(edge.count!=1)
				return false;
			
			if(edge.From==temp)
				curr=edge.To;
			else
				curr=edge.From;
			
			edge=tour.get(i+1);
			if(edge.From!=curr&&edge.To!=curr)
				return false;
			
			temp=curr;
			
		}
		
		return false;
	}
	public static void main(String args[]) throws FileNotFoundException
	{
		Scanner in;
//		if (args.length > 0) {
//		    File inputFile = new File(args[0]);
//		    in = new Scanner(inputFile);
//		} else {
//		    in = new Scanner(System.in);
//		}
		in = new Scanner(new File("Input.txt"));
		Graph g = Graph.readGraph(in, false);   // read undirected graph from stream "in"
		Iterator<EulerianTrails.Vertex> it = g.iterator();
		while(it.hasNext())
		{
			System.out.println(it.next().degree);
		}
		List<Edge> eulerTour=findEulerTour(g);
		if(eulerTour!=null)
		{
			for(Edge e:eulerTour)
				System.out.println(e);
		}
		else
		{
			System.out.println("Graph is not Eulerian");
		}
	}
}
