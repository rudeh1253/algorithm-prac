import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int testcase = Integer.parseInt(br.readLine().trim());

        for (int i = 0; i < testcase; i++) {
            int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int[][] matrix = new int[input[1]][input[0]];
            int k = input[2];
            for (int j = 0; j < k; j++) {
                int[] coord = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
                matrix[coord[1]][coord[0]] = 1;
            }

            int answer = solution(matrix);
            System.out.println(answer);
        }
        br.close();
    }

    static int solution(int[][] matrix) {
        int count = 0;
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                if (matrix[row][col] == 1) {
                    count++;
                    dfs(matrix, row, col);
                }
            }
        }
        return count;
    }

    static void dfs(int[][] matrix, int row, int col) {
        matrix[row][col] = 0;
        for (int direction = 0; direction < 4; direction++) {
            int rowDir = (direction - 1) % 2;
            int colDir = (direction - 2) % 2;
            int nextRow = row + rowDir;
            int nextCol = col + colDir;
            if (isSearchable(matrix, nextRow, nextCol)) {
                dfs(matrix, nextRow, nextCol);
            }
        }
    }

    static boolean isSearchable(int[][] matrix, int row, int col) {
        return withinRange(matrix.length, row) && withinRange(matrix[row].length, col)
                && matrix[row][col] != 0;
    }

    static boolean withinRange(int arrLen, int idx) {
        return idx >= 0 && idx < arrLen;
    }
}
