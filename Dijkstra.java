import java.util.*;

public class Dijkstra {
	private int distances[];
	private Set<Integer> settled;
	private PriorityQueue<Node> priorityQueue;
	private int numNodes;
	private int matrix[][]; // adjacency matrix representation

	// initialize all fields
	public Dijkstra(int number_of_nodes) {
		this.numNodes = number_of_nodes;
		distances = new int[numNodes + 1];
		settled = new HashSet<Integer>();
		priorityQueue = new PriorityQueue<Node>(numNodes, new Node());
		matrix = new int[numNodes + 1][numNodes + 1];
	}

	public void dijkstra_algorithm(int adjacency_matrix[][], int source) {
		int evaluationNode;
		// read graph in
		for (int i = 1; i <= numNodes; i++)
			for (int j = 1; j <= numNodes; j++)
				matrix[i][j] = adjacency_matrix[i][j];

		// initialized distance array
		for (int i = 1; i <= numNodes; i++) {
			distances[i] = Integer.MAX_VALUE;
		}

		priorityQueue.add(new Node(source, 0));
		distances[source] = 0;
		
		while (!priorityQueue.isEmpty()) {
			evaluationNode = getNodeWithMinimumDistanceFromPriorityQueue();
			settled.add(evaluationNode);
			evaluateNeighbours(evaluationNode);
		}
	} 

	private int getNodeWithMinimumDistanceFromPriorityQueue() {
		int node = priorityQueue.remove().node;
		return node;
	}

	private void evaluateNeighbours(int eNode) { //evaluationNode
		int edgeDistance = -1;
		int newDistance = -1;

		for (int dNode = 1; dNode <= numNodes; dNode++) {
			if (!settled.contains(dNode)) {
				if (matrix[eNode][dNode] != Integer.MAX_VALUE) {
					edgeDistance = matrix[eNode][dNode];
					newDistance = distances[eNode] + edgeDistance;
					if (newDistance < distances[dNode]) {
						distances[dNode] = newDistance;
					}
					priorityQueue.add(new Node(dNode,distances[dNode]));
				}   
			}
		}
	}

	public static void main(String... arg) {
		int adjacency_matrix[][];
		int numVertices;
		int source = 0;
		Scanner scan = new Scanner(System.in);
		try {
			System.out.println("Enter the number of vertices");
			numVertices = scan.nextInt();
			adjacency_matrix = new int[numVertices + 1][numVertices + 1];

			System.out.println("Enter the Weighted Matrix for the graph");
			for (int i = 1; i <= numVertices; i++) {
				for (int j = 1; j <= numVertices; j++) {
					adjacency_matrix[i][j] = scan.nextInt();
					if (i == j) {
						adjacency_matrix[i][j] = 0;
						continue;
					}
					if (adjacency_matrix[i][j] == 0) {
						adjacency_matrix[i][j] =  Integer.MAX_VALUE;
					}
				}
			}
			System.out.println("Enter the source ");
			source = scan.nextInt();
			Dijkstra dPQueue = new Dijkstra(numVertices);
			dPQueue.dijkstra_algorithm(adjacency_matrix, source);
			System.out.println("Shortest paths:");
			for (int i = 1; i <= dPQueue.distances.length - 1; i++) {
				System.out.println(source + " to " + i + " is " + dPQueue.distances[i]);
			}
		} catch (InputMismatchException inputMismatch) {
			System.out.println("Wrong Input Format");
		}
	}	
	
	private class Edge {
	    int v;
	    long cost;
	    
	    public Edge(int a, int c) {
	        v = a;
	        cost = c;
	    }
	}
}



//class Node implements Comparable<Node>{
//    int vertex;
//    long distance;
//    
//    public Node(int v, long d) {
//        vertex = v;
//        distance = d;
//    }
//    
//    public int compareTo(Node b) {
//        if (this.distance < b.distance) return -1;
//        else return 1;
//    }
//}

// A class representing a node
// This class implements Comparator in order to be used in priority queue
class Node implements Comparator<Node> {
	public int node;
	public int cost;
	public Node() {}
	public Node(int node, int cost) {
		this.node = node;
		this.cost = cost;
	}

	public int compare(Node node1, Node node2) {
		if (node1.cost < node2.cost)
			return -1;
		if (node1.cost > node2.cost)
			return 1;
		return 0;
	}
}