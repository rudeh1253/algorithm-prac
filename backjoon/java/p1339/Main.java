import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine().trim());
        String[] alphabets = new String[n];
        for (int i = 0; i < n; i++) {
            alphabets[i] = br.readLine().trim();
        }
        int answer = solution(n, alphabets);
        System.out.println(answer);
        br.close();
    }

    static int solution(int n, String[] alphabets) {
        Map<String, Integer> sum = new HashMap<>();
        for (final String alphabet : alphabets) {
            String[] parsed = alphabet.split("");
            int len = parsed.length;
            int pow = (int)Math.pow(10, len - 1);
            for (int i = 0; i < len; i++) {
                sum.put(parsed[i], sum.containsKey(parsed[i]) ? sum.get(parsed[i]) + pow : pow);
                pow /= 10;
            }
        }
        int[] nums = sum.values().stream().sorted((n1, n2) -> n2 - n1).mapToInt((i) -> (int)i).toArray();
        int num = 9;
        int result = 0;
        for (int i = 0; i < nums.length; i++) {
            result += num-- * nums[i];
        }
        return result;
    }
}