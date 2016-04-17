package GenericGraph;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class EulerVertex extends Vertex  {

	EulerVertex(int n) {
		super(n);
		// TODO Auto-generated constructor stub
	}
	
	public static void main(String args[]) throws FileNotFoundException
	{
	Scanner in=new Scanner(new File("Input.txt"));
	Graph<EulerVertex,EulerEdge> g = Graph.readGraph(in, false);
			
		
	}
}
