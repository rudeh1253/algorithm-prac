import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;

public class Main {
    static final int R = 0;
    static final int G = 1;
    static final int B = 2;

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws Exception {
        int N = Integer.parseInt(br.readLine());
        int[][] costs = new int[N + 1][3];

        for (int i = 1; i <= N; i++) {
            String[] line = br.readLine().split(" ");
            costs[i][R] = Integer.parseInt(line[0]);
            costs[i][G] = Integer.parseInt(line[1]);
            costs[i][B] = Integer.parseInt(line[2]);
        }

        int answer = solve1(costs, N);
        bw.write(answer + "\n");
        br.close();
        bw.close();
    }

    private static int solve1(int[][] costs, int N) {
        Map<Integer, int[]> tableRG = new HashMap<>();
        int rg = dp(costs, N, R, tableRG, G);

        Map<Integer, int[]> tableRB = new HashMap<>();
        int rb = dp(costs, N, G, tableRB, B);

        Map<Integer, int[]> tableGR = new HashMap<>();
        int gr = dp(costs, N, B, tableGR, R);

        Map<Integer, int[]> tableGB = new HashMap<>();
        int gb = dp(costs, N, B, tableGB, B);

        Map<Integer, int[]> tableBR = new HashMap<>();
        int br = dp(costs, N, B, tableBR, R);

        Map<Integer, int[]> tableBG = new HashMap<>();
        int bg = dp(costs, N, B, tableBG, G);

        return Math.min(rg,
                Math.min(rb,
                        Math.min(gr,
                                Math.min(gb,
                                        Math.min(br, bg)))));
    }

    private static int dp(int[][] costs, int n, int color, Map<Integer, int[]> table, int firstColor) {
        if (n == 1) {
            return costs[1][firstColor];
        } else if (n == 2) {
            if (table.containsKey(2)) {
                int[] c = table.get(2);
                return c[color];
            } else {
                int costR = Math.min(dp(costs, n - 1, G, table, firstColor), dp(costs, n - 1, B, table, firstColor))
                        + costs[n][R];
                int costG = Math.min(dp(costs, n - 1, R, table, firstColor), dp(costs, n - 1, B, table, firstColor))
                        + costs[n][G];
                int costB = Math.min(dp(costs, n - 1, R, table, firstColor), dp(costs, n - 1, G, table, firstColor))
                        + costs[n][B];
                if (firstColor == R) {
                    costR = 2147483647;
                }
                if (firstColor == G) {
                    costG = 2147483647;
                }
                if (firstColor == B) {
                    costB = 2147483647;
                }
                int[] c = {
                        costR,
                        costG,
                        costB
                };
                table.put(2, c);
                return c[color];
            }
        } else {
            if (table.containsKey(n)) {
                int[] c = table.get(n);
                return c[color];
            } else {
                int costR = Math.min(dp(costs, n - 1, G, table, firstColor), dp(costs, n - 1, B, table, firstColor))
                        + costs[n][R];
                int costG = Math.min(dp(costs, n - 1, R, table, firstColor), dp(costs, n - 1, B, table, firstColor))
                        + costs[n][G];
                int costB = Math.min(dp(costs, n - 1, R, table, firstColor), dp(costs, n - 1, G, table, firstColor))
                        + costs[n][B];
                int[] c = {
                        costR,
                        costG,
                        costB
                };
                table.put(n, c);
                return c[color];
            }
        }
    }
}
