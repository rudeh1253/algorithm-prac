import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine().trim());
        int[] goblets = new int[n];

        for (int i = 0; i < n; i++) {
            goblets[i] = Integer.parseInt(br.readLine().trim());
        }

        int answer = new Main().solution(n, goblets);
        System.out.println(answer);

        br.close();
    }

    int solution(int n, int[] goblets) {
        Map<Integer, Integer> maxDict = new HashMap<>();
        Map<Integer, Integer> countDict = new HashMap<>();
        countDict.put(-1, 0);
        countDict.put(-2, 0);
        maxDict.put(-1, 0);
        maxDict.put(-2, 0);

        for (int i = 0; i < n; i++) {
            int curVal = goblets[i];
            int count = countDict.get(i - 1);
            if (count == 2) {
                int first = maxDict.get(i - 3) + goblets[i - 1] + goblets[i];
                int second = maxDict.get(i - 2) + goblets[i];
                int third = maxDict.get(i - 1);

                int max;
                int newCount;
                if (first > second) {
                    if (second > third) {
                        max = first;
                        newCount = 2;
                    } else {
                        if (first > third) {
                            max = first;
                            newCount = 2;
                        } else {
                            max = third;
                            newCount = 0;
                        }
                    }
                } else {
                    if (second > third) {
                        max = second;
                        newCount = 1;
                    } else {
                        max = third;
                        newCount = 0;
                    }
                }

                maxDict.put(i, max);
                countDict.put(i, newCount);
            } else {
                maxDict.put(i, maxDict.get(i - 1) + curVal);
                countDict.put(i, countDict.get(i - 1) + 1);
            }
        }

        return maxDict.get(n - 1);
    }
}