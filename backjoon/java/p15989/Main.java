import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringBuilder inputBuilder = new StringBuilder();
        char[] inputBuffer = new char[1024];
        while (br.read(inputBuffer) != -1) {
            inputBuilder.append(inputBuffer);
        }

        StringTokenizer st = new StringTokenizer(inputBuilder.toString().trim());
        int n = Integer.parseInt(st.nextToken());

        int[] testCases = new int[n];
        for (int i = 0; i < n; i++) {
            testCases[i] = Integer.parseInt(st.nextToken().trim());
        }

        StringBuilder outputBuilder = new StringBuilder();
        for (int testCase : testCases) {
            long answer = solution(testCase);
            outputBuilder.append(answer).append('\n');
        }
        bw.write(outputBuilder.toString().trim());
        bw.flush();
        bw.close();
        br.close();
    }

    static long solution(int input) {
        Map<Integer, long[]> dpTable = new HashMap<>();
        dpTable.put(1, new long[] { 1, 0, 0 });
        dpTable.put(2, new long[] { 0, 1, 0 });
        dpTable.put(3, new long[] { 2, 0, 1 });
        long[] a = dp(input, dpTable);
        return a[0] + a[1] + a[2];
    }

    static long[] dp(int n, Map<Integer, long[]> dpTable) {
        if (n == 1) {
            return new long[] { 1, 0, 0 };
        } else if (n == 2) {
            return new long[] { 0, 1, 0 };
        } else if (n == 3) {
            return new long[] { 2, 0, 1 };
        }

        for (int num = 4; num < n; num++) {
            long[] m = {
                dpTable.get(num - 1)[0] + dpTable.get(num - 1)[1] + dpTable.get(num - 1)[2],
                dpTable.get(num - 2)[1] + dpTable.get(num - 2)[2],
                num % 3 == 0 ? 1 : 0
            };
            dpTable.put(num, m);
        }
        return new long[] {
            dpTable.get(n - 1)[0] + dpTable.get(n - 1)[1] + dpTable.get(n - 1)[2],
            dpTable.get(n - 2)[1] + dpTable.get(n - 2)[2],
            n % 3 == 0 ? 1 : 0
        };
    }
}