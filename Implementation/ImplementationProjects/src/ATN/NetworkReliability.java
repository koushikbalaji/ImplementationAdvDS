package ATN;

import java.util.*;

//class to represent link between two nodes
class Linker {
	int u;
	int v;

	Linker(int vertex1, int vertex2) {
		u = vertex1;
		v = vertex2;
	}
}

// class to represent nodes that share a link
class Neighbors {
	int node;
	boolean active;

	Neighbors(int vertex, boolean status) {
		node = vertex;
		active = status;
	}
}

public class NetworkReliability {

	// given no of nodes
	static int nodes = 5;

	// standard adjacency list and linker list similar to graph algorithms
	static ArrayList<ArrayList<Neighbors>> adjList = new ArrayList<ArrayList<Neighbors>>(nodes);
	static ArrayList<Linker> LinkerList = new ArrayList<Linker>();

	// variables to maintain states of nodes
	static boolean flag[] = null;
	static int states[] = null;

	// reliability quotient
	static float reliability;
	static ArrayList<int[]> stateList = new ArrayList<int[]>();
	static ArrayList<Boolean> stateStatus = new ArrayList<Boolean>();

	// checks connectivity of graph using Breadth First Search
	static boolean BFS() {
		Queue<Integer> q = new LinkedList<Integer>();
		Random rand = new Random();
		int element = 0;
		element = rand.nextInt(nodes);
		q.add(element);
		flag[element] = true;
		while (!q.isEmpty()) {
			element = q.remove();
			ArrayList<Neighbors> nList = adjList.get(element);
			for (int i = 0; i < nList.size(); i++) {
				if ((nList.get(i).active == true) && (flag[nList.get(i).node] == false)) {
					q.add(nList.get(i).node);
					flag[nList.get(i).node] = true;
				}
			}
		}
		for (int i = 0; i < flag.length; i++) {
			if (flag[i] == false) {
				return false;
			}
		}

		return true;
	}

	// switch states from 1 to 0 and 0 to 1
	static void switchStates() {
		int i = 0;
		for (i = 0; i < states.length && (states[i] == 1); i++) {
			states[i] = 0;
		}
		if (i < states.length) {
			states[i] = 1;
		}
	}

	// update states depending on state availability
	static void statesUpdate() {
		for (int i = 0; i < states.length; i++) {
			Linker obj = LinkerList.get(i);
			if (states[i] == 1) {
				if (obj.u < obj.v) {
					adjList.get(obj.u).get(obj.v - 1).active = false;
					adjList.get(obj.v).get(obj.u).active = false;
				} else {
					adjList.get(obj.u).get(obj.v).active = false;
					adjList.get(obj.v).get(obj.u - 1).active = false;
				}
			} else {
				if (obj.u < obj.v) {
					adjList.get(obj.u).get(obj.v - 1).active = true;
					adjList.get(obj.v).get(obj.u).active = true;
				} else {
					adjList.get(obj.u).get(obj.v).active = true;
					adjList.get(obj.v).get(obj.u - 1).active = true;
				}
			}
		}
	}

	// clearing methods
	static void clearStates() {
		for (int i = 0; i < states.length; i++) {
			states[i] = 0;
		}
	}

	static void clearflagArr() {
		for (int i = 0; i < flag.length; i++) {
			flag[i] = false;
		}
	}

	// initialize adjacency list
	static void initialize() {
		for (int i = 0; i < nodes; i++) {
			ArrayList<Neighbors> arrList = new ArrayList<Neighbors>();
			for (int j = 0; j < nodes; j++) {
				if (i != j) {
					Neighbors obj = new Neighbors(j, true);
					arrList.add(obj);
				}
			}
			adjList.add(arrList);
		}
		for (int i = 0; i < nodes; i++) {
			for (int j = i + 1; j < nodes; j++) {
				Linker obj = new Linker(i, j);
				LinkerList.add(obj);
			}
		}
	}

	public static void main(String[] args) {

		// ten edges for 5 nodes
		int edges = (nodes * (nodes - 1)) / 2;

		// 1024 possible states
		int noOfStates = (int) Math.pow(2, edges);

		// helper variables
		boolean status = false;
		float prod = 1;
		int[] currentState;
		flag = new boolean[nodes];
		states = new int[edges];
		initialize();
		float p;
		clearflagArr();

		
		System.out.println("Dependency of Reliability on Paramater");
		// switch and update all states
		for (int i = 0; i < noOfStates; i++) {
			currentState = new int[noOfStates];
			currentState = Arrays.copyOf(states, states.length);
			stateList.add(currentState);

			status = BFS();
			stateStatus.add(status);
			switchStates();
			statesUpdate();
		}

		// [0.05, 1] interval
		for (int ctr = 5; ctr <= 100; ctr += 5) {
			p = (float) (ctr / 100.0f);
			String studentID = "2021217155";

			int[] numbers = new int[10];
			for (int i = 0; i < numbers.length; i++) {
				numbers[i] = (int) (Math.random() * 10);
			}

			reliability = 0;
			for (int i = 0; i < noOfStates; i++) {
				prod = 1;
				states = stateList.get(i);
				if (stateStatus.get(i) == true) {
					for (int j = 0; j < states.length; j++) {
						if (states[j] == 0) {
							double diValue = Character.getNumericValue(studentID.charAt(numbers[j])) / 3;
							double power = Math.ceil(diValue);
							prod = (float) (prod * Math.pow(p, power));

						} else {
							double diValue = Character.getNumericValue(studentID.charAt(numbers[j])) / 3;
							double power = Math.ceil(diValue / 3);
							prod = (float) (prod * (1 - Math.pow(p, power)));

						}
					}
					reliability += prod;

				}
			}
			
			
			System.out.print("P: " + p + " R: ");
			System.out.format("%f", reliability);
			System.out.println();
		}
		p = 0.9f;

		
		System.out.println("Dependency of Reliability after flipping k states");
		for (int k = 0; k <= 100; k++) {
			ArrayList<Integer> kList = new ArrayList<Integer>();
			Random rand = new Random();
			int number = 0;
			reliability = 0;

			// Student ID
			String studentID = "2021217155";

			int[] numbers = new int[10];
			for (int i = 0; i < numbers.length; i++) {
				numbers[i] = (int) (Math.random() * 10);
			}

			for (int i = 0; i < 300; i++) {
				kList.clear();

				// Random state
				for (int l = 0; l < k; l++) {
					number = rand.nextInt(1024);
					if (!kList.contains(number)) {
						kList.add(number);
					}
				}

				for (int j = 0; j < noOfStates; j++) {
					prod = 1;
					if (kList.contains(j)) {
						states = stateList.get(j);
						if (stateStatus.get(j) == false) {

							// parameter from student ID
							for (int s = 0; s < states.length; s++) {
								if (states[s] == 0) {
									double diValue = Character.getNumericValue(studentID.charAt(numbers[s])) / 3;
									double power = (float) Math.ceil(diValue);
									prod = prod * (float) Math.pow(p, power);

								} else {
									double diValue = Character.getNumericValue(studentID.charAt(numbers[s])) / 3;
									double power = Math.ceil(diValue / 3);
									prod = (float) (prod * (1 - Math.pow(p, power)));
								}
							}
							reliability += prod;
						}

					} else {
						states = stateList.get(j);
						if (stateStatus.get(j) == true) {
							for (int s = 0; s < states.length; s++) {
								if (states[s] == 0) {
									double diValue = Character.getNumericValue(studentID.charAt(numbers[s])) / 3;
									double power = (float) Math.ceil(diValue);
									prod = prod * (float) Math.pow(p, power);

								} else {
									double diValue = Character.getNumericValue(studentID.charAt(numbers[s])) / 3;
									double power = Math.ceil(diValue / 3);
									prod = (float) (prod * (1 - Math.pow(p, power)));
								}
							}
							reliability += prod;
						}
					}
				}
			}

			
			System.out.print("k: " + k + " " + "R: ");
			System.out.format("%f", (reliability / 300.0));
			System.out.println();
		}
	}

}