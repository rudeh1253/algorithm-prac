import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] sizeInput = Arrays.stream(br.readLine().trim().split(" ")).mapToInt(Integer::parseInt).toArray();
        int r = sizeInput[0];
        int c = sizeInput[1];

        int[][] matrix = new int[r][];
        for (int i = 0; i < r; i++) {
            matrix[i] = Arrays.stream(br.readLine().trim().split("")).mapToInt(Integer::parseInt).toArray();
        }

        int answer = solve(r, c, matrix);

        System.out.println(answer);
    }

    static int solve(int r, int c, int[][] matrix) {
        int maxValue = 0;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                int size = getSize(j, i, matrix);
                maxValue = size > maxValue ? size : maxValue;
            }
        }

        return maxValue;
    }

    static int getSize(int startX, int startY, int[][] matrix) {
        if (matrix[startY][startX] == 0) {
            return 0;
        } else if (startX - 1 < 0 || startX + 1 >= matrix[0].length || startY + 1 >= matrix.length
                || matrix[startY + 1][startX - 1] != 1 || matrix[startY + 1][startX + 1] != 1) {
            return 1;
        }

        int size = 1;

        int maxValue = 1;
        while (startX - size >= 0 && startY + size < matrix.length && matrix[startY + size][startX - size] == 1) {
            if (startY + size + 1 < matrix.length && startX - size + 1 < matrix.length
                    && matrix[startY + size + 1][startX - size + 1] == 1) {
                maxValue = isComplete(startX, startY, size, matrix) ? size + 1 : maxValue;
            }
            size++;
        }
        return maxValue;
    }

    static boolean isComplete(int startX, int startY, int size, int[][] matrix) {
        if (startX - size < 0
                || startX + size >= matrix[0].length
                || startY + 2 * size >= matrix.length) {
            return false;
        }

        for (int i = 0; i < size; i++) {
            if (matrix[startY + i][startX - i] == 0
                    || matrix[startY + i][startX + i] == 0
                    || matrix[startY + size + i][startX - size + i] == 0
                    || matrix[startY + size + i][startX + size - i] == 0) {
                return false;
            }
        }
        return true;
    }
}