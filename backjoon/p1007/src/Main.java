import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int X = 0;
    static int Y = 1;
    
    public static void main(String[] args) throws Exception {
        int T = Integer.parseInt(br.readLine());
        Map<Integer, int[][]> testCases = new HashMap<>();
        for (int i = 0; i < T; i++) {
            int numOfCases = Integer.parseInt(br.readLine());
            int[][] cases = new int[numOfCases][2];
            for (int j = 0; j < numOfCases; j++) {
                String[] line = br.readLine().split(" ");
                cases[j][X] = Integer.parseInt(line[0]);
                cases[j][Y] = Integer.parseInt(line[1]);
            }
            testCases.put(i + 1, cases);
        }

        for (int i = 0; i < T; i++) {
            System.out.println(solveOneTestCase(testCases.get(i + 1)));
        }
    }

    private static double solveOneTestCase(int[][] testCases) {
        throw new UnsupportedOperationException();
    }
}
