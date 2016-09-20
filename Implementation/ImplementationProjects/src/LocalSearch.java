import java.util.Arrays;
import java.util.Random;

public class LocalSearch {

	public int[][] nodes;
	public int[][] paths;
	public double[][] distance;

	
	//gemerate random nodes
	public void randomNodes(int noOfNodes) {
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

	
	//calculate geometric distance
	public double calculateDistance(int a, int b) {
		int x1 = nodes[a][0];
		int y1 = nodes[a][1];

		int x2 = nodes[b][0];
		int y2 = nodes[b][1];

		return Math.sqrt(Math.pow((x1 - x2), 2) + Math.pow((y1 - y2), 2));

	}

	public void generatedistance(int noOfNodes) {
		distance = new double[noOfNodes][noOfNodes];
		for (int i = 0; i < noOfNodes; i++) {
			Arrays.fill(distance[i], 0);
		}
		for (int i = 0; i < noOfNodes; i++) {
			for (int j = 0; j < noOfNodes; j++) {
				if (distance[i][j] > 0)
					continue;
				else if (i == j)
					distance[i][j] = 0;
				else
					distance[i][j] = calculateDistance(i, j);
			}
		}
	}

	public int edgeCount(int row[]) {
		int counter = 0;
		for (int i = 0; i < row.length; i++) {
			if (row[i] == 1)
				counter++;
		}
		return counter;
	}

	//find nearest neighbors list
	public void getNeighbours(int noOfNodes) {
		paths = new int[noOfNodes][noOfNodes];
		for (int i = 0; i < noOfNodes; i++) {
			Arrays.fill(paths[i], 0);
		}
		int numOfOnes = 0;
		double[] sortedRow = new double[noOfNodes];
		for (int row = 0; row < noOfNodes; row++) {
			numOfOnes = (edgeCount(paths[row]));
			for (int l = 0; l < noOfNodes; l++)
				sortedRow[l] = distance[row][l];
			Arrays.sort(sortedRow);
			for (int col = row; col < noOfNodes; col++) {
				if (row == col) {
					paths[row][col] = 1;
					numOfOnes += 1;
				} else if (numOfOnes <= 3) {
					int index = 0;
					for (int k = 0; k < sortedRow.length; k++) {
						if (distance[row][col] == sortedRow[k]) {
							index = k;
							break;
						}
					}
					if (index < 4)
						paths[row][col] = paths[col][row] = 1;
					numOfOnes += 1;
				}
			}
		}
	}

	public void diameter(int noOfNodes) {
		int counter = 0, tmp = 0, nextNode = 0;
		boolean flag = false;
		for (int i = 0; i < noOfNodes; i++) {
			for (int j = i + 1; j < noOfNodes; j++) {
				if (paths[i][j] == 1)
					continue;
				counter = 0;
				flag = true;
				tmp = i;
				while (counter <= 3) {
					counter += 1;
					for (int k = 0; k < paths[tmp].length; k++) {
						if (paths[tmp][k] == 1) {
							nextNode = k;
							break;
						}
					}
					if (paths[nextNode][j] == 1) {
						flag = false;
						break;
					}
					tmp = nextNode;
				}
				if (flag) {
					paths[tmp][j] = paths[j][tmp] = 1;
				}
			}
		}
	}

	public void checkNAddNeighbours(int noOfNodes) {
		int index = 0;
		int noOfOnes = 0;
		int k = 0;
		double[] sortedRow;
		for (int i = 0; i < noOfNodes; i++) {
			noOfOnes = edgeCount(paths[i]);
			sortedRow = new double[noOfNodes];
			for (int l = 0; l < noOfNodes; l++)
				sortedRow[l] = distance[i][l];
			Arrays.sort(sortedRow);
			while (noOfOnes <= 3) {
				for (int j = 0; j < noOfNodes; j++) {
					for (k = 0; k < noOfNodes; k++) {
						if (distance[index][k] == sortedRow[j])
							break;
					}
					if (paths[index][k] != 1) {
						noOfOnes += 1;
						paths[index][j] = paths[j][index] = 1;
						break;

					}
				}
			}
			index++;
		}
	}

	public double getCost(int noOfNodes) {
		double cost = 0;
		for (int i = 0; i < noOfNodes; i++) {
			for (int j = i + 1; j < noOfNodes; j++) {
				if (paths[i][j] == 1)
					cost += distance[i][j];
			}
		}
		return cost;
	}

	public static void main(String args[]) {
		int noOfNodes = 25;
		System.out.println("# of Nodes : " + noOfNodes);
		LocalSearch gls = new LocalSearch();
		gls.randomNodes(noOfNodes);
		gls.generatedistance(noOfNodes);
		gls.getNeighbours(noOfNodes);
		gls.checkNAddNeighbours(noOfNodes);
		/*
		 * for(int i=0 ; i < gls.paths.length;i++){ for(int j=0; j <
		 * gls.paths[0].length; j++){ System.out.print(gls.paths[i][j]
		 * + "\t"); } System.out.println(); }
		 */
		double cost = gls.getCost(noOfNodes);
		System.out.println("Cost: " + cost);
	}
}
