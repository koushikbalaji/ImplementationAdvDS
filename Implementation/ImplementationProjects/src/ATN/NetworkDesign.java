package ATN;

/* Total cost and density computing algorithm implementation of shortest path fast solution constructed network with required network parameters */

import java.util.HashSet;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import static javax.swing.text.html.HTML.Attribute.N;

public class NetworkDesign {

	int N; 
	public int size = 1024;
	int b[][] = new int[size][size]; 
	int a[][] = new int[size][size]; 
	int c[][] = new int[size][size]; 
	int d[] = new int[size]; 
	int prev[] = new int[size]; 
	int visited[] = new int[size]; 
	public static double total_edge;
	private static Scanner userinput;

	public static void main(String[] args) {
		NetworkDesign expt = new NetworkDesign(); 
		int k;
		int flag = 1;
		userinput = new Scanner(System.in);
		System.out.println("Enter the Total number of nodes in the network \n");
		expt.N = userinput.nextInt(); 
		for (int i = 1; i <= expt.N; i++) {
			for (int j = 1; j <= expt.N; j++) {
				expt.a[i][j] = 300;
			}
		}

		System.out.println("Enter the number of 'K' nodes in the graph\n");
		k = userinput.nextInt();

		if (k < 3 || k > 15) {
			System.out.println("Provide 'K' value between  3 and 15 to run the experiment \n");

		} else {
			expt.DesignNetwork(k, flag); 
			System.out.print("\n Emulation of the algorithm: \n");
			System.out.println("k \t\t Cost \t\t Density\n");
			System.out.println(k + "\t\t" + expt.findCost() + "\t\t" + expt.findDensity());
			flag = 0;
			System.out.println(
					"\n Output of computed data of the shortest path constructed graph with different 'K' values: \n");
			System.out.println("\n k \t\t Cost \t\t Density\n");
			for (int i = 3; i <= 15; i++) {
				expt.DesignNetwork(i, flag);
				System.out.println(i + "\t\t" + expt.findCost() + "\t\t" + expt.findDensity());
			}

		}
	}

	private void DesignNetwork(int k, int check) {
		
		int[] num=new int[1024];
		
		num[1]=num[11]=num[21]=2;
		num[2]=num[12]=num[22]=0;
		num[3]=num[13]=num[23]=2;
		num[4]=num[14]=num[24]=1;
		num[5]=num[15]=num[25]=2;
		num[6]=num[16]=num[26]=1;
		num[7]=num[17]=num[27]=7;
		num[8]=num[18]=num[28]=1;
		num[9]=num[19]=num[29]=5;
		num[10]=num[20]=num[30]=5;
		
		
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				b[i][j] = Math.abs(num[i]-num[j]);
			}
		}
		Set<Integer> set = new HashSet<Integer>();
		while (set.size() < k) {
			set.add((int)(Math.random()*k));
		}
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if (set.contains(j)) {
					a[i][j] = 1;
				} else {
					a[i][j] = 300;
				}
			}
		}

		for (int i = 1; i <= N; i++) {
			if (check == 1) {
				System.out.print(" \n \n The cost of the path chosen by the source node " + i + "\n");
			}
			dijkstra(i, check);
		}

	}

	private void dijkstra(int s, int tag) {
		int select; 
		for (int i = 1; i <= N; i++) {
			d[i] = size; 
			prev[i] = -1; 
			visited[i] = 0; 
		}
		d[s] = 0; 
		for (int k = 1; k <= N; k++) {
			select = -1;
			for (int i = 1; i <= N; i++)
				if ((visited[i] == 0) && ((select == -1) || (d[i] < d[select])))
					select = i;
			visited[select] = 1;
			for (int i = 1; i <= N; ++i)
				if (a[select][i] != 0)
					if (d[select] + a[select][i] < d[i]) {
						d[i] = d[select] + a[select][i]; 
						prev[i] = select; 
					}
		}

		for (int j = 1; j <= N; j++)
			findPath(j, tag);
	}

	private void findPath(int node, int tag) {

		if (prev[node] != -1)
			findPath(prev[node], tag);
		if (prev[node] > -1) {
			c[prev[node]][node] += a[prev[node]][node] * b[prev[node]][node];
			if (tag == 1) {
				System.out.print("\n For reaching node\t" + node);
				System.out.print("\t is \t" + c[prev[node]][node]);
			}

		}

	}

	private int findCost() {
		int totalCost = 0;
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if (b[i][j] != 0) {
					totalCost += a[i][j] * b[i][j];
				}
			}
		}
		return totalCost;
	}

	private double findDensity() {
		double total_edge = 0.0;
		double density = 0.0;
		for (int i = 1; i <= N; i++)
			for (int j = 1; j <= N; j++) {
				if (c[i][j] != 0) {
					total_edge += 1;

				}

			}
		density = total_edge / (N * N - 1);
		return density;

	}
}
