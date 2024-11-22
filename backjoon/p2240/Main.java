import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] firstLine = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int t = firstLine[0];
        int w = firstLine[1];

        int[] times = new int[t];
        for (int i = 0; i < t; i++) {
            times[i] = Integer.parseInt(br.readLine().trim());
        }

        int answer = solution(t, w, times);
        System.out.println(answer);

        br.close();
    }

    static int solution(int t, int w, int[] times) {
        int curPos = 1;
        int acc = 0;
        List<Integer> availableWithoutMovement = new ArrayList<>();
        if (times[0] != 1) {
            availableWithoutMovement.add(0);
            curPos = 2;
        }
        for (int i = 0; i < times.length; i++) {
            if (i == times.length - 1) {
                if (curPos != times[i]) {
                    availableWithoutMovement.add(acc);
                    availableWithoutMovement.add(1);
                } else {
                    availableWithoutMovement.add(acc + 1);
                }
                continue;
            }

            if (curPos != times[i]) {
                availableWithoutMovement.add(acc);
                curPos = times[i];
                acc = 1;
            } else {
                acc++;
            }
        }

        return dp(0, w, availableWithoutMovement, new HashMap<>());
    }

    static int dp(int idx, int leftMovement, List<Integer> availableWithoutMovement, Map<Memo, Integer> dpTable) {
        if (idx >= availableWithoutMovement.size()) {
            return 0;
        }

        if (idx == availableWithoutMovement.size() - 1) {
            return availableWithoutMovement.get(idx);
        }

        Memo memo = new Memo(idx, leftMovement);
        if (dpTable.containsKey(memo)) {
            return dpTable.get(memo);
        }

        int max = dp(idx + 2, leftMovement, availableWithoutMovement, dpTable);

        if (leftMovement > 0) {
            int fromOther = dp(idx + 1, leftMovement - 1, availableWithoutMovement, dpTable);
            max = Math.max(max, fromOther);
        }

        int sum = availableWithoutMovement.get(idx) + max;
        dpTable.put(memo, sum);
        return sum;
    }

    static class Memo {
        int idx;
        int leftMovement;

        Memo(int idx, int leftMovement) {
            this.idx = idx;
            this.leftMovement = leftMovement;
        }

        @Override
        public boolean equals(Object obj) {
            Memo tar = (Memo)obj;
            return this.idx == tar.idx && this.leftMovement == tar.leftMovement;
        }

        @Override
        public int hashCode() {
            return Objects.hash(this.idx, this.leftMovement);
        }
    }
}