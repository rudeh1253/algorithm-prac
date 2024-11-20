import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine().trim());

        int[] w = new int[t];
        int[] n = new int[t];
        int[][] x_i = new int[t][];
        int[][] w_i = new int[t][];

        for (int i = 0; i < t; i++) {
            int[] input1 = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            w[i] = input1[0];
            n[i] = input1[1];

            x_i[i] = new int[input1[1]];
            w_i[i] = new int[input1[1]];
            for (int j = 0; j < input1[1]; j++) {
                int[] input2 = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
                x_i[i][j] = input2[0];
                w_i[i][j] = input2[1];
            }
        }

        int[] answer = solution(t, w, n, x_i, w_i);

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for (int a : answer) {
            bw.write(String.valueOf(a));
            bw.newLine();
        }
        bw.flush();

        bw.close();
        br.close();
    }

    static int[] solution(int t, int[] w, int[] n, int[][] x_i, int[][] w_i) {
        int[] answer = new int[t];
        for (int i = 0; i < t; i++) {
            answer[i] = singleSolution(w[i], n[i], x_i[i], w_i[i]);
        }
        return answer;
    }

    static int singleSolution(int w, int n, int[] x_i, int[] w_i) {
        int currentWeight = 0;
        int aggr = 0;
        
        for (int i = 0; i < n; i++) {
            int weightSum = currentWeight + w_i[i];
            if (weightSum >= w) {
                if (weightSum == w) {
                    if (i == n - 1) {
                        continue;
                    } else {
                        aggr += x_i[i] * 2;
                        currentWeight = 0;
                    }
                } else {
                    aggr += x_i[i] * 2;
                    currentWeight = w_i[i];
                }
            } else {
                currentWeight += w_i[i];
            }
        }
        return aggr + x_i[x_i.length - 1] * 2;
    }
}