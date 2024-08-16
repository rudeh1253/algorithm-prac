import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int n = input[0];
        int l = input[1];
        String answer = solution(n, l);
        bw.write(answer);

        br.close();
        bw.flush();
        bw.close();
    }

    static String solution(int n, int l) {
        if (l > 100) {
            return "-1";
        }
        int divided = n / l;
        int modulo = n % l;

        if (l % 2 == 0) {
            if (modulo != (l - 2) / 2 + 1 || divided - (l - 2) / 2 < 0) {
                return solution(n, l + 1);
            } else {
                StringBuilder sb = new StringBuilder();
                for (int i = divided - (l - 2) / 2; i <= divided + (l - 2) / 2 + 1; i++) {
                    sb.append(i).append(' ');
                }
                return sb.toString().trim();
            }
        } else {
            if (modulo != 0 || divided - (l - 1) / 2 < 0) {
                return solution(n, l + 1);
            } else {
                StringBuilder sb = new StringBuilder();
                for (int i = divided - (l - 1) / 2; i <= divided + (l - 1) / 2; i++) {
                    sb.append(i).append(' ');
                }
                return sb.toString().trim();
            }
        }
    }
}