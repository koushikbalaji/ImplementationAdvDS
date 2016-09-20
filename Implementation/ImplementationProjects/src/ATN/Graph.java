package ATN;

import java.util.Arrays;
import java.util.Random;

public class Graph {
	int[][] network;
	protected int noOfNodes;
	protected int count;
	protected int[] arr;
	public int[][] nodes;
	public int[][] paths;
	public double[][] distance;

	public Graph(int n) {
		network = new int[n][n];
		noOfNodes = n;
		arr = new int[10];
	}

	//edge manipulation add and remove during shrink
	public void addEdge(int source, int target) {
		network[source][target] = 1;
		network[target][source] = 1;
	}

	public void removeEdge(int source, int target) {
		network[source][target] = 0;
		network[target][source] = 0;
	}

	
	//find connectivity of graph
	public int connectivity(int node) {
		int count1 = 0;
		for (int i = 0; i < noOfNodes; i++) {
			if (network[node][i] == 1)
				count1++;
		}
		return count1;
	}

	
	//find number of nodes that satisfy max connectivity
	public void findMaxConnected(int minConnectivity) {
		count = 0;

		for (int i = 0; i < noOfNodes; i++) {
			if (this.connectivity(i) > minConnectivity) {
				arr[count] = i;
				count++;
			}
		}
	}

	public int numberOfEdges() {
		int count_edges = 0;
		for (int i = 0; i < noOfNodes; i++) {
			for (int j = i; j < noOfNodes; j++) {
				if (network[i][j] == 1)
					count_edges++;
			}
		}
		return count_edges;
	}

	
	//shrink two cycles into single one
	public void shrink(int x, int y) {
		int temp1[] = new int[20];
		int[] temp2 = new int[20];
		int count_temp1 = 0, count_temp2 = 0;
		int a = 0, b = 0, flag = 0, i, j;

		for (i = 0; i < noOfNodes; i++) {
			if (this.network[x][i] == 1) {

				temp1[count_temp1] = i;
				count_temp1++;
			}
		}

		for (i = 0; i < noOfNodes; i++) {
			if (this.network[y][i] == 1) {
				temp2[count_temp2] = i;
				count_temp2++;
			}
		}

		for (i = 0; i < count_temp1; i++) {
			for (j = 0; j < count_temp2; j++) {
				if (temp1[i] != temp2[j] && network[temp1[i]][temp2[j]] != 1) {
					a = temp1[i];
					b = temp2[j];
					flag = 1;
					break;
				}
			}
			if (flag == 1)
				break;
		}

		if (flag == 1) {
			this.addEdge(a, b);
			this.removeEdge(x, a);
			this.removeEdge(y, b);
		}
	}

	
	public int dijkstra() {
		int diameter = 0;
		Path[] p = new Path[625];
		int path_count = 0;
		final WeightedGraph t = new WeightedGraph(noOfNodes);
		System.out.println("noOfNode = " + noOfNodes);

		for (int i = 0; i < noOfNodes; i++) {
			for (int j = i; j < noOfNodes; j++) {
				t.addEdge(i, j, network[i][j]);
			}
		}

		for (int i = 0; i < noOfNodes; i++)
			t.setLabel(i, i);

		path_count = 0;
		for (int start = 0; start < noOfNodes; start++) {
			//final int[] pred = dijkstra.dijkstrastra(t, start);
			for (int n = 0; n < noOfNodes; n++) {
				if (start != n) {
					//p[path_count] = dijkstrastra.printPath(t, pred, start, n);
					p[path_count].setSource(start);

					int size_path = p[path_count].getShortestPath().size();
					int dest = p[path_count].getShortestPath().get(size_path - 1);

					p[path_count].setDest(dest);

					path_count++;

				}
			}
		}

		for (int i = 0; i < path_count; i++) {

			if (p[i].getShortestPath().size() > diameter)
				diameter = p[i].getShortestPath().size();
		}

		return diameter;
	}

	public static void main(String args[]) {

		int noOfNodes = 10, minConnectivity = 3, m;
		Graph graph = new Graph(noOfNodes);
		graph.generateRandomNodes(noOfNodes);
		graph.generatedistance(noOfNodes);
		if (minConnectivity == 2) {

			for (int i = 0; i < noOfNodes; i++) {
				graph.addEdge(i, (i + 1) % noOfNodes);
				graph.addEdge((i + 1) % noOfNodes, i);
			}
		}

		else {
			for (int i = 0; i < noOfNodes; i++) {
				for (int j = i; j < noOfNodes; j++) {
					if (i == j)
						graph.removeEdge(i, j);
					else
						graph.addEdge(i, j);
				}
			}

			for (int i = 0; i < noOfNodes; i++) {
				for (int j = 0; j < noOfNodes; j++) {
					graph.removeEdge(i, j);
					if (graph.connectivity(i) < minConnectivity) {
						graph.addEdge(i, j);
					} else if (graph.connectivity(j) < minConnectivity) {
						graph.addEdge(i, j);
					}
				}
			}

			graph.findMaxConnected(minConnectivity);

			int t = 0;
			while (graph.count >= 2 && t < noOfNodes / 2) {

				for (int i = 0; i <= graph.count / 2;) {
					graph.shrink(graph.arr[i], graph.arr[i + 1]);
					i = i + 2;
				}

				graph.findMaxConnected(minConnectivity);

				t++;
			}

		}
		m = graph.numberOfEdges();
		System.out.println("The number of edges = " + m);
		int diameter = graph.dijkstra();
		System.out.println("The diameter is = " + diameter);
		for (int i = 0; i < graph.network.length; i++) {
			for (int j = 0; j < graph.network[0].length; j++) {
				System.out.print(graph.network[i][j] + "\t");
			}
			System.out.println();
		}
		double cost = graph.findTotalCost(noOfNodes, graph.network);
		System.out.println("Total cost : " + cost );
	}
	
	public void generateRandomNodes(int noOfNodes) {
		
		int x = 0, y = 0;
		Random rn = new Random();
		nodes = new int[noOfNodes][2];
		int max = 100;
		for (int i = 0; i < noOfNodes; i++) {
			x = rn.nextInt((max - i) + 1) + i;
			y = rn.nextInt((max - i)) + i;
			nodes[i][0] = x;
			nodes[i][1] = y;

		}
		
	}
	
	public double distanceCalculation(int a, int b){
		int x1 = nodes[a][0];
		int y1 = nodes[a][1];
		
		int x2 = nodes[b][0];
		int y2 = nodes[b][1];
		
		return Math.sqrt(Math.pow((x1-x2),2) + Math.pow((y1-y2),2));
		
	}
	
	public void generatedistance(int noOfNodes){
		distance = new double[noOfNodes][noOfNodes];
		for(int i=0; i < noOfNodes; i++){
			Arrays.fill(distance[i], 0);
		}
		for(int i=0; i < noOfNodes; i++){
			for(int j=0; j < noOfNodes; j++){
				if(distance[i][j] > 0)
					continue;
				else if(i==j)
					distance[i][j] = 0;
				else
					distance[i][j] = distanceCalculation(i,j);
			}
		}
	}
	

	public double findTotalCost(int noOfNodes, int[][] paths){
		double cost =0;
		for(int i=0; i < noOfNodes; i++){
			for(int j=i+1; j < noOfNodes; j++){
				if(paths[i][j] == 1)
					cost += distance[i][j];
			}
		}
		return cost;
	}
}
