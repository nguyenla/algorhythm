import java.util.InputMismatchException;
import java.util.*;
 
public class TSP {
    private int numberOfNodes;
    private Stack<Integer> stack;
 
    public TSP() {
        stack = new Stack<Integer>();
    }
 
    public void tsp(int matrix[][]) {
        numberOfNodes = matrix[1].length - 1;
        int[] visited = new int[numberOfNodes + 1];
        visited[1] = 1;
        stack.push(1);
        int element, dst = 0, i;
        int min = Integer.MAX_VALUE;
        boolean minFlag = false;
        System.out.print(1 + "\t");
        while (!stack.isEmpty()) {
            element = stack.peek();
            i = 1;
            min = Integer.MAX_VALUE;
            while (i <= numberOfNodes) {
                if (matrix[element][i] > 1 && visited[i] == 0) {
                    if (min > matrix[element][i]) {
                        min = matrix[element][i];
                        dst = i;
                        minFlag = true;
                    }
                }
                i++;
            }
            if (minFlag) {
                visited[dst] = 1;
                stack.push(dst);
                System.out.print(dst + "\t");
                minFlag = false;
                continue;
            }
            stack.pop();
        }
    }
 
    public static void main(String... arg) {
        int numNodes;
        Scanner scanner = null;
        try {
            System.out.println("Enter the number of nodes:");
            scanner = new Scanner(System.in);
            numNodes = scanner.nextInt();
            int matrix[][] = new int[numNodes + 1][numNodes + 1];
            System.out.println("Enter the adjacency matrix");
            for (int i = 1; i <= numNodes; i++) {
                for (int j = 1; j <= numNodes; j++) {
                	matrix[i][j] = scanner.nextInt();
                }
            }
            for (int i = 1; i <= numNodes; i++) {
                for (int j = 1; j <= numNodes; j++) {
                    if (matrix[i][j] == 1 && matrix[j][i] == 0) {
                    	matrix[j][i] = 1;
                    }
                }
            }
            System.out.println("The cities are visited as follows: ");
            TSP tspNearestNeighbour = new TSP();
            tspNearestNeighbour.tsp(matrix);
        }
        catch (InputMismatchException inputMismatch) {
            System.out.println("Wrong Input format");
        }
        scanner.close();
    }
}