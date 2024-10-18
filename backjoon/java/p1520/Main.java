import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Main {
    
    public static void main(String[] args) throws Exception {
        int m;
        int n;
        int[][] matrix;
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            int[] firstLine = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            m = firstLine[0];
            n = firstLine[1];
            matrix = new int[m][];

            for (int i = 0; i < m; i++) {
                matrix[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            }
        }

        int answer = solution(m, n, matrix);
        System.out.println(answer);
    }

    static int solution(int m, int n, int[][] matrix) {
        return dfs(0, 0, matrix, new HashMap<>());
    }

    static int dfs(int row, int col, int[][] matrix, Map<Coord, Integer> dpTable) {
        if (row == matrix.length - 1 && col == matrix[0].length - 1) {
            return 1;
        }

        Coord coord = new Coord(row, col);
        if (dpTable.containsKey(coord)) {
            return dpTable.get(coord);
        }

        int sum = 0;
        for (int i = 0; i < 4; i++) {
            int nextRow = row + ((i - 1) % 2);
            int nextCol = col + ((i - 2) % 2);
            if (nextRow >= 0 && nextRow < matrix.length
                    && nextCol >= 0 && nextCol < matrix[0].length) {
                if (matrix[nextRow][nextCol] < matrix[row][col]) {
                    sum += dfs(nextRow, nextCol, matrix, dpTable);
                }
            }
        }

        dpTable.put(coord, sum);
        return sum;
    }

    static class Coord {
        int row;
        int col;

        Coord(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Coord)) {
                return false;
            }
            Coord tar = (Coord)obj;
            return this.row == tar.row && this.col == tar.col;
        }

        @Override
        public int hashCode() {
            return Objects.hash(this.row, this.col);
        }
    }
}
