import java.util.Scanner;
 
public class BellmanFord {
    private int distances[];
    private int numVertices;
    public static final int MAX_VALUE = 999;
 
    public BellmanFord(int numVertices) {
        this.numVertices = numVertices;
        distances = new int[numVertices + 1];
    }
 
    public void BellmanFordEvaluation(int source, int destination, int adjacencymatrix[][]) {
        for (int node = 1; node <= numVertices; node++) {
            distances[node] = MAX_VALUE;
        }
        distances[source] = 0;
        for (int node = 1; node <= numVertices - 1; node++) {
            for (int snode = 1; snode <= numVertices; snode++) {
                for (int dnode = 1; dnode <= numVertices; dnode++) {
                    if (adjacencymatrix[snode][dnode] != MAX_VALUE) {
                        if (distances[dnode] > distances[snode] + adjacencymatrix[snode][dnode])
                            distances[dnode] = distances[snode] + adjacencymatrix[snode][dnode];
                    }
                }
            }
        }
        
        for (int snode = 1; snode <= numVertices; snode++) {
            for (int dnode = 1; dnode <= numVertices; dnode++) {
                if (adjacencymatrix[snode][dnode] != MAX_VALUE) {
                    if (distances[dnode] > distances[snode] + adjacencymatrix[snode][dnode])
                        System.out.println("The Graph contains negative egde cycle");
                }
            }
        }
        for (int vertex = 1; vertex <= numVertices; vertex++) {
            if (vertex == destination)
                System.out.println("distance of source  " + source + " to " + vertex + " is " + distances[vertex]);
        }
    }
 
    public static void main(String... arg) {
        int numVertices = 0;
        int source, destination;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of vertices");
        numVertices = scanner.nextInt();
        int adjacencymatrix[][] = new int[numVertices + 1][numVertices + 1];
        System.out.println("Enter the adjacency matrix");
        
        for (int snode = 1; snode <= numVertices; snode++) {
            for (int dnode = 1; dnode <= numVertices; dnode++) {
                adjacencymatrix[snode][dnode] = scanner.nextInt();
                if (snode == dnode) {
                    adjacencymatrix[snode][dnode] = 0;
                    continue;
                }
                if (adjacencymatrix[snode][dnode] == 0) {
                    adjacencymatrix[snode][dnode] = MAX_VALUE;
                }
            }
        }
        System.out.println("Enter the source vertex");
        source = scanner.nextInt();
        System.out.println("Enter the destination vertex: ");
        destination = scanner.nextInt();
        BellmanFord bellmanford = new BellmanFord(numVertices);
        bellmanford.BellmanFordEvaluation(source, destination, adjacencymatrix);
        scanner.close();
    }
}