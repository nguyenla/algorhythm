import java.util.*;
 
public class Prim {
    private boolean unsettled[];
    private boolean settled[];
    private int numVertices;
    private int matrix[][];
    private int key[];
    public static final int INFINITE = 999;
    private int parent[];
 
    public Prim(int numVertices) {
        this.numVertices = numVertices;
        unsettled = new boolean[numVertices + 1];
        settled = new boolean[numVertices + 1];
        matrix = new int[numVertices + 1][numVertices + 1];
        key = new int[numVertices + 1];
        parent = new int[numVertices + 1];
    }
 
    public int getUnsettledCount(boolean unsettled[]) {
        int count = 0;
        for (int index = 0; index < unsettled.length; index++) {
            if (unsettled[index]) {
                count++;
            }
        }
        return count;
    }
 
    public void primsAlgorithm(int adjacencyMatrix[][]) {
        int evaluationVertex;
        for (int source = 1; source <= numVertices; source++) {
            for (int destination = 1; destination <= numVertices; destination++) {
                this.matrix[source][destination] = adjacencyMatrix[source][destination];
            }
        }
 
        for (int index = 1; index <= numVertices; index++) {
            key[index] = INFINITE;
        }
        key[1] = 0;
        unsettled[1] = true;
        parent[1] = 1;
 
        while (getUnsettledCount(unsettled) != 0) {
            evaluationVertex = getMimumKeyVertexFromUnsettled(unsettled);
            unsettled[evaluationVertex] = false;
            settled[evaluationVertex] = true;
            evaluateNeighbours(evaluationVertex);
        }
    } 
 
    private int getMimumKeyVertexFromUnsettled(boolean[] unsettled2) {
        int min = Integer.MAX_VALUE;
        int node = 0;
        for (int vertex = 1; vertex <= numVertices; vertex++) {
            if (unsettled[vertex] == true && key[vertex] < min) {
                node = vertex;
                min = key[vertex];
            }
        }
        return node;
    }
 
    public void evaluateNeighbours(int eVertex) {
        for (int d = 1; d <= numVertices; d++) {
            if (settled[d] == false) {
                if (matrix[eVertex][d] != INFINITE) {
                    if (matrix[eVertex][d] < key[d]) {
                        key[d] = matrix[eVertex][d];
                        parent[d] = eVertex;
                    }
                    unsettled[d] = true;
                }
            }
        }
    }
 
    public void printMST() {
        System.out.println("SOURCE  : DESTINATION = WEIGHT");
        for (int v = 2; v <= numVertices; v++) {
            System.out.println(parent[v] + "\t:\t" + v +"\t=\t"+ matrix[parent[v]][v]);
        }
    }
 
    public static void main(String... arg) {
        int adjacency_matrix[][];
        int numVer;
        Scanner scan = new Scanner(System.in);
 
        try {
            System.out.println("Number of vertices");
            numVer = scan.nextInt();
            adjacency_matrix = new int[numVer + 1][numVer + 1];
            System.out.println("Weighted Matrix");
            for (int i = 1; i <= numVer; i++) {
                for (int j = 1; j <= numVer; j++) {
                    adjacency_matrix[i][j] = scan.nextInt();
                    if (i == j) {
                        adjacency_matrix[i][j] = 0;
                        continue;
                    }
                    if (adjacency_matrix[i][j] == 0) {
                        adjacency_matrix[i][j] = INFINITE;
                    }
                }
            }
            Prim prims = new Prim(numVer);
            prims.primsAlgorithm(adjacency_matrix);
            prims.printMST();
        } 
        catch (InputMismatchException inputMismatch) {
            System.out.println("Wrong Input Format");
        }
        scan.close();
    }
}