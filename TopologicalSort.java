import java.util.*;
 
public class TopologicalSort {
    private Stack<Integer> stack;
    public TopologicalSort() {
        stack = new Stack<Integer>();
    }
 
    public int[] topological(int matrix[][], int s) throws NullPointerException {
        int numNodes = matrix[s].length - 1;
        int[] topological_sort = new int[numNodes + 1];
        int pos = 1;
        int j;
        int visited[] = new int[numNodes + 1];
        int element = s;
        int i = s;
        visited[s] = 1;
        stack.push(s);
 
        while (!stack.isEmpty()) {
            element = stack.peek();
            while (i <= numNodes) {
                if (matrix[element][i] == 1 && visited[i] == 1) {
                    if (stack.contains(i)) {
                        System.out.println("NOT POSSIBLE");
                        return null;
                    }
                }  
                if (matrix[element][i] == 1 && visited[i] == 0) {
                    stack.push(i);
                    visited[i] = 1;
                    element = i;
                    i = 1;
                    continue;
                }
                i++;
            }
            j = stack.pop();	
            topological_sort[pos++] = j;
            i = ++j;
        }
        return topological_sort;
    }	
 
    public static void main(String...arg) {
        int numNodes, source;
        Scanner scanner = null;
        int tsort[]  = null;
        try {
            System.out.println("Enter numNodes:");
            scanner = new Scanner(System.in);
            numNodes = scanner.nextInt();
 
            int adjacency_matrix[][] = new int[numNodes + 1][numNodes + 1];
            System.out.println("Enter the adjacency matrix");
	    
            for (int i = 1; i <= numNodes; i++)
                for (int j = 1; j <= numNodes; j++)
                    adjacency_matrix[i][j] = scanner.nextInt();
 
            System.out.println("Enter source:");
            source = scanner.nextInt();
 
            System.out.println("Topological sort: ");
            TopologicalSort toposort = new TopologicalSort();
            tsort = toposort.topological(adjacency_matrix, source);
            System.out.println();
            for (int i = tsort.length - 1; i > 0; i--) {
                if (tsort[i] != 0)
                    System.out.print(tsort[i]+"\t");
            } 	
        } 
        catch(InputMismatchException inputMismatch) {
             System.out.println("Wrong Input format");
        }
        catch(NullPointerException nullPointer) {
        }
        scanner.close();
    }
}