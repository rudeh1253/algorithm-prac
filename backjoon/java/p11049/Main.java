import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine().trim());

        int[][] matrixes = new int[n][];
        for (int i = 0; i < n; i++) {
            String[] split = br.readLine().trim().split(" ");
            matrixes[i] = new int[] { Integer.parseInt(split[0]), Integer.parseInt(split[1]) };
        }
        bw.write(String.valueOf(solution(matrixes)));
        bw.newLine();
        bw.flush();

        bw.close();
        br.close();
    }

    static class Range {
        int left;
        int right;

        Range() {
        }

        Range(int left, int right) {
            this.left = left;
            this.right = right;
        }

        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof Range)) {
                return false;
            }
            Range o = (Range)obj;
            return this.left == o.left && this.right == o.right;
        }

        @Override
        public int hashCode() {
            return this.left + 1000 * this.right;
        }
    }

    static int solution(int[][] input) {
        Map<Range, int[]> dpTable = new HashMap<>();
        for (int i = 0; i < input.length; i++) {
            dpTable.put(new Range(i, i), new int[] { input[i][0], input[i][1], 0 });
        }
        int[] result = dp(0, input.length - 1, dpTable, input); 
        return result[2];
    }

    static int[] dp(int left, int right, Map<Range, int[]> dpTable, int[][] input) {
        Range range = new Range(left, right);
        int[] result = dpTable.get(range);
        if (result == null) {
            int[] min = null;
            for (int i = 0; i < right - left; i++) {
                int[] fromRight = dp(right - i, right, dpTable, input);
                int[] fromLeft = dp(left, right - i - 1, dpTable, input);

                int[] sum = new int[] { fromLeft[0], fromRight[1], fromLeft[0] * fromLeft[1] * fromRight[1] + fromLeft[2] + fromRight[2] };

                if (min == null || sum[2] < min[2]) {
                    min = sum;
                }
            }
            dpTable.put(range, min);
            result = min;
        }
        return result;
    }
}