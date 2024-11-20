import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        List<String> solved = solution(input[0], input[1]);
        for (String line : solved) {
            bw.write(line.trim());
            bw.newLine();
        }

        bw.flush();
        br.close();
        bw.close();
    }

    static List<String> solution(int n, int m) {
        List<String> store = new LinkedList<>();
        
        get(n, m, 1, new HashSet<>(), "", store);

        return store;
    }

    static void get(int n, int m, int cur, Set<Integer> used, String accumulator, List<String> store) {
        for (int i = 1; i <= n; i++) {
            if (used.contains(i)) {
                continue;
            }
            if (m == cur) {
                store.add(accumulator + " " + i);
            } else {
                used.add(i);
                get(n, m, cur + 1, used, accumulator + " " + i, store);
                used.remove(i);
            }
        }
    }
}