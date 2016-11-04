import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
 
public class FordFulkerson {
    private int[] parent;
    private Queue<Integer> queue;
    private int numVertices;
    private boolean[] visited;
 
    public FordFulkerson(int numVertices) {
        this.numVertices = numVertices;
        this.queue = new LinkedList<Integer>();
        parent = new int[numVertices + 1];
        visited = new boolean[numVertices + 1];		
    }
 
    public boolean bfs(int s, int goal, int graph[][]) {
        boolean pathFound = false;
        int d, element;
 
        for(int vertex = 1; vertex <= numVertices; vertex++) {
            parent[vertex] = -1;
            visited[vertex] = false;
        }
        queue.add(s);
        parent[s] = -1;
        visited[s] = true;
 
        while (!queue.isEmpty()) { 
            element = queue.remove();
            d = 1;
 
            while (d <= numVertices) {
                if (graph[element][d] > 0 &&  !visited[d]) {
                    parent[d] = element;
                    queue.add(d);
                    visited[d] = true;
                }
                d++;
            }
        }
        if(visited[goal]) {
            pathFound = true;
        }
        return pathFound;
    }
 
    public int fordFulkerson(int graph[][], int s, int d) {
        int u, v;
        int maxFlow = 0;
        int pathFlow;
 
        int[][] residualGraph = new int[numVertices + 1][numVertices + 1];
        for (int sVertex = 1; sVertex <= numVertices; sVertex++) {
            for (int dVertex = 1; dVertex <= numVertices; dVertex++) {
                residualGraph[sVertex][dVertex] = graph[sVertex][dVertex];
            }
        }
 
        while (bfs(s ,d, residualGraph)) {
            pathFlow = Integer.MAX_VALUE;
            for (v = d; v != s; v = parent[v]) {
                u = parent[v];
                pathFlow = Math.min(pathFlow, residualGraph[u][v]);
            }
            for (v = d; v != s; v = parent[v]) {
                u = parent[v];
                residualGraph[u][v] -= pathFlow;
                residualGraph[v][u] += pathFlow;
            }
            maxFlow += pathFlow;	
        }
        return maxFlow;
    }
 
    public static void main(String...arg) {
        int[][] graph;
        int numNodes;
        int source;
        int sink;
        int maxFlow;
 
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of nodes");
        numNodes = scanner.nextInt();
        graph = new int[numNodes + 1][numNodes + 1];
 
        System.out.println("Enter the graph matrix");
        for (int sVertex = 1; sVertex <= numNodes; sVertex++) {
           for (int dVertex = 1; dVertex <= numNodes; dVertex++) {
               graph[sVertex][dVertex] = scanner.nextInt();
           }
        }
        System.out.println("Source:");
        source= scanner.nextInt();
        System.out.println("Sink:");
        sink = scanner.nextInt();
        FordFulkerson fordFulkerson = new FordFulkerson(numNodes);
        maxFlow = fordFulkerson.fordFulkerson(graph, source, sink);
        System.out.println("The Max Flow is " + maxFlow);
        scanner.close();
    }
}