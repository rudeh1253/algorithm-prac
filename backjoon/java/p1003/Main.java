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

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            int input = Integer.parseInt(br.readLine().trim());
            int[] result = fibHelper(input);
            sb.append(String.format("%d %d\n", result[0], result[1]));
        }

        bw.write(sb.toString());
        bw.flush();

        br.close();
        bw.close();
    }

    private static int[] fibHelper(int n) {
        return fib(n, new HashMap<>());
    }

    private static int[] fib(int n, Map<Integer, int[]> dpTable) {
        if (n == 0) {
            return new int[] { 1, 0 };
        } else if (n == 1) {
            return new int[] { 0, 1 };
        } else {
            int[] fromTable = dpTable.get(n);
            if (fromTable == null) {
                int[] prev1 = fib(n - 1, dpTable);
                int[] prev2 = fib(n - 2, dpTable);
                int[] result = new int[] { prev1[0] + prev2[0], prev1[1] + prev2[1] };
                dpTable.put(n, result);
                return result;
            } else {
                return fromTable;
            }
        }
    }
}