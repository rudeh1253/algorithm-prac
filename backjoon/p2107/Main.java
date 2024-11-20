import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.TreeSet;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine().trim());

        int[][] pairs = new int[n][];
        for (int i = 0; i < n; i++) {
            pairs[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        int answer = solution(pairs);
        System.out.println(answer);

        br.close();
    }
    
    static int solution(int[][] input) {
        Arrays.sort(input, (e1, e2) -> e1[0] - e2[0]);
        
        int max = 0;
        for (int i = 0; i < input.length; i++) {
            int[] orig = input[i];
            int count = 0;
            for (int j = i + 1; j < input.length; j++) {
                int[] tar = input[j];
                if (tar[1] < orig[1]) {
                    count++;
                }
                if (tar[0] > orig[1]) {
                    break;
                }
            }
            max = Math.max(max, count);
        }
        return max;

        // PriorityQueue<Pair> pq1 = new PriorityQueue<>((e1, e2) -> e1.left - e2.left);

        // for (int[] singleInput : input) {
        //     pq1.add(new Pair(singleInput[0], singleInput[1]));
        // }
        
        // TreeSet<Pair> retrieved = new TreeSet<>((e1, e2) -> e1.right - e2.right);

        // while (!pq1.isEmpty()) {
        //     Pair pair = pq1.poll();
        //     retrieved.add(pair);

        //     Pair higher = retrieved.higher(pair);
        //     if (higher != null) {
        //         if (higher.parent == null) {
        //             pair.parent = higher;
        //         } else {
        //             pair.parent = higher.parent;
        //         }
        //         pair.parent.childCount++;
        //     }
        // }

        // int max = 0;
        // // int right = -1;
        // for (Pair p : retrieved) {
        //     // if (max < p.childCount) {
        //     //     right = p.right;
        //     // }
        //     max = Math.max(max, p.childCount);
        // }
        // return max;
    }

    static class Pair {
        int left;
        int right;
        int childCount;
        Pair parent;

        Pair(int left, int right) {
            this.left = left;
            this.right = right;
            this.childCount = 0;
            this.parent = null;
        }
    }
}