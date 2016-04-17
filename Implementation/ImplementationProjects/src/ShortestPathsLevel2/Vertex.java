package ShortestPathsLevel2;





/**
 * Class to represent a vertex of a graph
 * 
 *
 */

import java.util.*;

public class Vertex implements Index {
    public int name; // name of the vertex
    public boolean seen; // flag to check if the vertex has already been visited
    public Vertex parent; // parent of the vertex
    public int distance; // distance to the vertex from the source vertex
    public List<Edge> Adj, revAdj; // adjacency list; use LinkedList or ArrayList
    public int incomingDegree;
    public boolean processed;//keeping track of processed nodes to find cycle 
    public int scc;//Strongly Connected Components
    public int count;//to keep track of vertex visited count in Bellman-Ford
    int index;
    public boolean cycleTestInd; 
    
    /**
     * Constructor for the vertex
     * 
     * @param n
     *            : int - name of the vertex
     */
    Vertex(int n) {
	name = n;
	seen = false;
	parent = null;
	Adj = new ArrayList<Edge>();
	revAdj = new ArrayList<Edge>();   /* only for directed graphs */
	incomingDegree=0;
	processed=false;
	count=0;
	parent=null;
    }

    /**
     * Method to represent a vertex by its name
     */
    public String toString() {
	return Integer.toString(name);
    }

    public int compare(Vertex u, Vertex v){
    	return u.distance - v.distance;
    }
    
    public void putIndex(int index){
    	this.index = index;
    }
    
    public int getIndex(){
    	return this.index;
    }
}