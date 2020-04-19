import java.util.*;
 
public class FloydWarshall {
    private int graph[][];
    private int numVertices;
    public static final int INFINITY = Integer.MAX_VALUE;
    
    public FloydWarshall(int[][] matrix) {
    	graph = matrix;
    	numVertices = graph.length - 1;
    }

    // Run Floyd-Warshall algorithm
    public void floydwarshall() {
       for (int i = 1; i <= numVertices; i++) { // intermediate vertex
            for (int s = 1; s <= numVertices; s++) {
                for (int d = 1; d <= numVertices; d++) {
                    if (graph[s][i] != INFINITY && graph[i][d] != INFINITY && 
                    		graph[s][i] + graph[i][d] < graph[s][d])
                    	graph[s][d] = graph[s][i] + graph[i][d];
                }
            }
        }
        
       // Print out the matrix
        for (int s = 1; s <= numVertices; s++) {
            System.out.print("\t" + s);
        }
        System.out.println();
        for (int s = 1; s <= numVertices; s++) {
            System.out.print(s + "\t");
            for (int d = 1; d <= numVertices; d++) {
            	if (graph[s][d] == INFINITY) {
            		System.out.print(-1 + "\t");
            	}
            	else {
            		System.out.print(graph[s][d] + "\t");
            	}
            }
            System.out.println();
        }
    }
 
    public static void main(String... arg) {
        Scanner s = new Scanner(System.in);
        System.out.println("Enter the number of vertices and the number of edges:");
        int numVertices = s.nextInt();
        int numEdges = s.nextInt();   
        int[][] graph = new int[numVertices + 1][numVertices + 1];
        
        for (int i = 1; i <= numVertices; i++) {
            Arrays.fill(graph[i], INFINITY);
        }
        
        for (int i = 1; i <= numEdges; i++) {
        	int v1 = s.nextInt();
        	int v2 = s.nextInt();
        	graph[v1][v2] = s.nextInt();
//        	graph[v2][v1] = graph[v1][v2]; // only if undirected graph
        }
        
        System.out.println("All pairs shortest paths");
        FloydWarshall f = new FloydWarshall(graph);
        f.floydwarshall();
        s.close();
    }
}